package fr.isika.cda27.teamJADE.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

import fr.isika.cda27.teamJADE.view.App;

public class TreeNodeDao {

	private TreeNode root;

	public TreeNodeDao() {
		this.root = null;
	}

	/**
	 * @param root
	 */
	public TreeNodeDao(TreeNode root) {
		this.root = root;
	}

	/**
	 * @return
	 */
	public TreeNode getRoot() {
		return root;
	}

	/**
	 * @param root
	 */
	public void setRoot(TreeNode root) {
		this.root = root;
	}

	/**
	 * @param intern
	 */
	public void addIntern(Intern intern) {
		if (root == null) {
			this.root = new TreeNode(intern);
			this.writeInBinary(intern, this.getBinarySize());
		} else {
			this.root = insert(intern, root);
		}

	}

	/**
	 * @param intern   l'information sous forme d'objet de type Intern qui sera
	 *                 stockée dans le noeud à ajouter à l'arbre binaire.
	 * @param treeNode l'objet de type TreeNode correspondant au noeud de l'arbre
	 *                 que nous voulons parcourir. Renseigner la Racine de l'arbre
	 *                 au départ.
	 * @return Le noeud suivant dans le parcourt de l'arbre ou le noeud ajouté si
	 *         aucun noeud suivant n'existe.
	 * 
	 *         Cette fonction permet d'ajouter un Noeud à l'arbre binaire en le
	 *         parcourant de manière efficace ainsi que de l'écrire dans le fichier
	 *         binaire. La position du curseur est au début du fichier.
	 */
	public TreeNode insert(Intern intern, TreeNode treeNode) {
		return insert(intern, treeNode, 0);
	}

	/**
	 * @param intern         l'information sous forme d'objet de type Intern qui
	 *                       sera stockée dans le noeud à ajouter à l'arbre binaire.
	 * @param treeNode       l'objet de type TreeNode correspondant au noeud de
	 *                       l'arbre que nous voulons parcourir. Renseigner la
	 *                       Racine de l'arbre au départ.
	 * @param cursorPosition la position en Long du curseur dans le fichier binaire.
	 * @return Le noeud suivant dans le parcourt de l'arbre ou le noeud ajouté si
	 *         aucun noeud suivant n'existe.
	 * 
	 *         Cette fonction permet d'ajouter un Noeud à l'arbre binaire en le
	 *         parcourant de manière efficace ainsi que de l'écrire dans le fichier
	 *         binaire.
	 */
	public TreeNode insert(Intern intern, TreeNode treeNode, long cursorPosition) {

		// Si le noeud courant est null
		if (treeNode == null) {

			// Alors nous écrivons le noeud à cet endroit dans le fichier binaire
			this.writeInBinary(intern, cursorPosition);
			return new TreeNode(intern);
		}

		// Si la valeur du noeud à insérer est plus petite que celle du noeud courant
		if (intern.getFamilyName().compareTo(treeNode.getFamilyName()) < 0) {

			// On lit depuis le fichier binaire l'information du fils gauche du noeud actuel
			// Afin de pouvoir y placer le curseur sur le fichier binaire
			long newCursorPosition = readLeftChildFromBinary(cursorPosition) * TreeNode.getSizeNode();

			// Si il n'y a pas de fils gauche, c'est donc que l'information sur le fichier
			// binaire vaut -1
			if (newCursorPosition < 0) {

				/*
				 * Dans ce cas nous allons créer un fils gauche au noeud courant et nous
				 * remplaçons dans le fichier binaire le -1 correspondant au fils gauche par la
				 * valeur de la position du noeud que l'on va ajouter à la fin du fichier
				 * binaire
				 */
				this.writeIntInBinary(this.getNumberNodeInBinary(), cursorPosition + Intern.getSizeIntern());
				treeNode.setLeftChild(insert(intern, treeNode.getLeftChild(), this.getBinarySize()));
			} else {

				// Si le fils gauche existe déjà, alors nous déplaçons notre curseur à la
				// position correspondante dans le fichier binaire
				// et nous relançons une comparaison d'insertion.
				treeNode.setLeftChild(insert(intern, treeNode.getLeftChild(), newCursorPosition));
			}
		}

		// Si la valeur du noeud à insérer est plus grande que celle du noeud courant
		else if (intern.getFamilyName().compareTo(treeNode.getFamilyName()) > 0) {

			// On lit depuis le fichier binaire l'information du fils droit du noeud actuel
			// Afin de pouvoir y placer le curseur sur le fichier binaire
			long newCursorPosition = readRightChildFromBinary(cursorPosition) * TreeNode.getSizeNode();

			// Si il n'y a pas de fils droit, c'est donc que l'information sur le fichier
			// binaire vaut -1
			if (newCursorPosition < 0) {

				/*
				 * Dans ce cas nous allons créer un fils droit au noeud courant et nous
				 * remplaçons dans le fichier binaire le -1 correspondant au fils droit par la
				 * valeur de la position du noeud que l'on va ajouter à la fin du fichier
				 * binaire
				 */
				this.writeIntInBinary(this.getNumberNodeInBinary(), cursorPosition + Intern.getSizeIntern() + 4);
				treeNode.setRightChild(insert(intern, treeNode.getRightChild(), this.getBinarySize()));
			} else {
				// Si le fils droit existe déjà, alors nous déplaçons notre curseur à la
				// position correspondante dans le fichier binaire
				// et nous relançons une comparaison d'insertion.
				treeNode.setRightChild(insert(intern, treeNode.getRightChild(), newCursorPosition));
			}
		}

		// Si la valeur du noeud à insérer est égale à celle du noeud courant
		else {
			// Si le noeud est déjà présent
			if (treeNode.getTwins().contains(intern)) {
				System.out.println("Le stagiaire est déjà présent dans la base de données");
			} else {

				// Sinon nous ajoutons l'objet de type Intern à la liste chaînée du noeud
				// courant
				treeNode.getTwins().add(intern);
				int read = 1;

				/*
				 * Pour le fichier binaire nous lisons la valeur de position du noeud suivant le
				 * noeud courant. Tant que nous n'obtenons pas -1 c'est que nous ne sommes pas
				 * au bout de la liste chaînée alors nous déplaçons notre curseur vers la
				 * position du noeud suivant et nous recommençons à lire la valeur de position
				 * du noeud suivant
				 */
				while (read > 0) {
					read = readTwinFromBinary(cursorPosition);
					if (read > 0)
						cursorPosition = read * TreeNode.getSizeNode();
				}

				/*
				 * Une fois le bout de liste chaînée atteint, nous ajoutons le noeud en bout de
				 * liste et nous remplaçons dans le fichier binaire le -1 correspondant suivant
				 * dans la liste chainée par la valeur de la position du noeud que l'on va
				 * ajouter à la fin du fichier binaire
				 */
				this.writeIntInBinary(this.getNumberNodeInBinary(), cursorPosition + Intern.getSizeIntern() + 4 + 4);
				this.writeInBinary(intern, this.getBinarySize());
			}
		}
		return treeNode;

	}

	public void removeIntern(Intern intern) {
		if (root == null) {
			System.out.println("Impossible de supprimer le stagiaire");
		} else {
			root = delete(intern, root);
		}
	}

	public TreeNode delete(Intern intern, TreeNode treeNode) {
		return this.delete(intern, treeNode, 0, 0, false);
	}

	public TreeNode delete(Intern intern, TreeNode treeNode, long parentCursorPosition, long childCursorPosition,
			boolean isFromLeft) {

		// Si le noeud courant est null
		if (treeNode == null) {
			return treeNode;
		}
		long buffer = childCursorPosition;

		// Si la valeur du noeud à supprimer est plus petite que celle du noeud courant
		if (intern.getFamilyName().compareTo(treeNode.getFamilyName()) < 0) {
			childCursorPosition = readLeftChildFromBinary(childCursorPosition) * TreeNode.getSizeNode();
			treeNode.setLeftChild(delete(intern, treeNode.getLeftChild(), buffer, childCursorPosition, true));
		}

		// Si la valeur du noeud à supprimer est plus grande que celle du noeud courant
		else if (intern.getFamilyName().compareTo(treeNode.getFamilyName()) > 0) {

			childCursorPosition = readRightChildFromBinary(childCursorPosition) * TreeNode.getSizeNode();
			treeNode.setRightChild(delete(intern, treeNode.getRightChild(), buffer, childCursorPosition, false));		

		// Si la valeur du noeud à supprimer est égale à celle du noeud courant
		} else {
			// Si on a une LinkedList > 1 donc si on a plusieurs homonymes
			if (treeNode.getTwins().size() > 1) {
				int index = treeNode.getTwins().indexOf(intern);
				for (int i = 0; i < index; i++) {
					buffer = childCursorPosition;
					childCursorPosition = readTwinFromBinary(childCursorPosition) * TreeNode.getSizeNode();
					if (i == index - 1) {
						this.writeIntInBinary(
								index + 1 >= treeNode.getTwins().size() - 1 ?
										-1 : readTwinFromBinary(childCursorPosition),
								buffer + Intern.getSizeIntern() + 4 + 4);
					}
				}
				this.eraseFromBinary(childCursorPosition);
				treeNode.getTwins().remove(intern); // on efface dans la liste
				return treeNode;
				// Sinon
			} else {
				TreeNode substitute = deleteNode(treeNode);
				this.writeIntInBinary(-1, isFromLeft ? parentCursorPosition + Intern.getSizeIntern()
						: parentCursorPosition + Intern.getSizeIntern() + 4);
				this.eraseFromBinary(childCursorPosition);
				return substitute;
			}

		}
		return treeNode;

	}

	private TreeNode deleteNode(TreeNode treeNode) {
		// Si il n'y a pas d'enfants gauche
		if (treeNode.getLeftChild() == null && treeNode.getLeftChild() != null) {
			return treeNode.getRightChild();

		// Si il n'y a pas d'enfants droit
		} else if (treeNode.getRightChild() == null && treeNode.getRightChild() != null) {
			return treeNode.getLeftChild();

		// Si il y a deux enfants
		} else if (treeNode.getRightChild() != null && treeNode.getRightChild() != null) {
		TreeNode substitute = findSubstitute(treeNode.getRightChild());
		treeNode.setFamilyName(substitute.getFamilyName());
		treeNode.setTwins(substitute.getTwins());

		treeNode.setRightChild(delete(substitute.getTwins().getFirst(), treeNode.getRightChild()));
		
		}
		// S'il n'y a ni d'enfant gauche ni d'enfant droit

		return treeNode;
	}

	private TreeNode findSubstitute(TreeNode minNode) {
		// On cherche la valeur minimale du sous arbre droit
		while (minNode.getLeftChild() != null) {
			minNode = minNode.getLeftChild();
		}
		return minNode;
	}

	public void sortView(TreeNode node) {

		if (node == null) {
			return;
		}

		sortView(node.getLeftChild());
		for (Intern intern : node.getTwins()) {
			System.out.println(intern.getFamilyName());
		}
		sortView(node.getRightChild());
	}

	/**
	 * Lit le fichier .DON et ajoute tous les stagiaires s'y trouvant dans l'arbre
	 * binaire
	 */
	public void addFromDon() {

		try {
			// Le fichier .DON à lire
			FileReader fr = new FileReader(App.getFichierDon());

			// Le buffered reader, l'objet qui permet de lire toute une ligne d'un coup dans
			// un fichier texte (ici le .DON)
			BufferedReader br = new BufferedReader(fr);
			String text = "";
			String familyName = "";
			String firstName = "";
			String county = "";
			String cursus = "";
			String yearIn = "";
			int counter = 0;

			// Tant qu'il y a une ligne à lire nous allons effectuer la série d'action
			// suivante:
			while (br.ready()) {

				// Nous incrémentons le compteur pour savoir à quelle ligne nous sommes
				counter++;

				// Nous stockons l'information de la ligne (qui est un String) dans une
				// variable.
				text = br.readLine();

				// En fonction de la ligne à laquelle nous sommes nous allons faire une action
				// différente
				switch (counter) {

				// Dans le cas où nous sommes sur la première ligne, il s'agit du Nom de famille
				// du Stagiaire
				// alors nous stockons entièrement en majuscule cette ligne de texte dans une
				// variable.
				case 1:
					familyName = text.toUpperCase();
					break;

				// Dans le cas où nous sommes sur la deuxième ligne, nous stockons cette ligne
				// de texte dans une autre variable.
				case 2:
					firstName = text;
					break;

				// Idem pour les lignes 3, 4 et 5
				case 3:
					county = text;
					break;
				case 4:
					cursus = text;
					break;
				case 5:
					yearIn = text;
					break;

				// Dans le cas où nous sommes sur la sixieme ligne, qui correspond à une ligne
				// avec un astérisque
				default:

					// Nous créons un objet de type Intern avec les variables dans lesquelles nous
					// avons stocké les informations des 5 lignes précédentes
					// et nous ajoutons immédiatement cet objet de type Intern à l'arbre binaire
					this.addIntern(new Intern(familyName, firstName, county, cursus, yearIn));

					// Et nous remettons le compteur à zéro afin de pouvoir repartir sur la création
					// d'un nouveau stagiaire
					counter = 0;
					break;
				}
			}

			// Une fois arrivés ici, nous avons parcouru tout le texte,
			// nous n'avons donc plus besoin de lire le fichier alors nous coupons le flux
			// d'échange de données.
			br.close();
			fr.close();
		} catch (IOException e) {
			// S'il y a une erreur, on affiche dans la console d'où elle vient afin de
			// pouvoir la régler
			e.printStackTrace();
		}

	}

	/**
	 * @param internToAdd L'objet de type Intern à ajouter à la fin du fichier .DON
	 */
	public void addToDon(Intern internToAdd) {

		FileWriter fw;

		try {
			fw = new FileWriter(App.getFichierDon(), true);
			fw.write(internToAdd.getFamilyName().toUpperCase() + "\n" + internToAdd.getFirstName() + "\n"
					+ internToAdd.getCounty() + "\n" + internToAdd.getCursus() + "\n" + internToAdd.getYearIn()
					+ "\n*\n");

			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return retourne un long correspondant à la taille du fichier binaire en
	 *         octets
	 */
	public long getBinarySize() {
		long binSize = 0;
		try {
			RandomAccessFile raf = new RandomAccessFile(App.getFichierBin(), "rw");

			binSize = raf.length();

			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return binSize;
	}

	/**
	 * @param internToAdd L'objet type Intern à ajouter dans le fichier binaire
	 *                    Ajoute le noeud à la fin du fichier binaire en tant que
	 *                    feuille: fils gauche vaut -1, fils droit vaut -1 et
	 *                    suivant liste chainée vaut -1 aussi.
	 */
	public void writeInBinary(Intern internToAdd) {
		writeInBinary(internToAdd, this.getBinarySize(), -1, -1, -1);
	}

	/**
	 * @param internToAdd    L'objet type Intern à ajouter dans le fichier binaire
	 * @param cursorPosition La position du curseur en octet. Doit être au niveau du
	 *                       début du noeud. Ajoute le noeud en tant que feuille:
	 *                       fils gauche vaut -1, fils droit vaut -1 et suivant
	 *                       liste chainée vaut -1 aussi.
	 */
	public void writeInBinary(Intern internToAdd, long cursorPosition) {
		writeInBinary(internToAdd, cursorPosition, -1, -1, -1);
	}

	/**
	 * @param internToAdd    L'objet type Intern à ajouter dans le fichier binaire
	 * @param cursorPosition La position du curseur en octet. Doit être au niveau du
	 *                       début du noeud.
	 * @param leftChildInt   L'entier correspondant à la position du fils gauche
	 *                       dans le fichier binaire
	 * @param rightChildInt  L'entier correspondant à la position du fils droit dans
	 *                       le fichier binaire
	 * @param twinInt        L'entier correspondant à la position du noeud suivant
	 *                       dans la liste chainée dans le fichier binaire
	 */
	public void writeInBinary(Intern internToAdd, long cursorPosition, int leftChildInt, int rightChildInt,
			int twinInt) {
		try {
			RandomAccessFile raf = new RandomAccessFile(App.getFichierBin(), "rw");

			raf.seek(cursorPosition);
			raf.writeChars(internToAdd.getFamilyNameLong());
			raf.writeChars(internToAdd.getFirstNameLong());
			raf.writeChars(internToAdd.getCountyLong());
			raf.writeChars(internToAdd.getCursusLong());
			raf.writeChars(internToAdd.getYearIn());
			raf.writeInt(leftChildInt);
			raf.writeInt(rightChildInt);
			raf.writeInt(twinInt);

			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param twinOrChild    L'entier à inscrire dans le fichier binaire.
	 * @param cursorPosition La position du curseur en octet. Doit être au niveau du
	 *                       début du fils gauche, droit ou du noeud suivant dans la
	 *                       liste chainée.
	 */
	public void writeIntInBinary(int twinOrChild, long cursorPosition) {
		try {
			RandomAccessFile raf = new RandomAccessFile(App.getFichierBin(), "rw");

			raf.seek(cursorPosition);
			raf.writeInt(twinOrChild);

			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param cursorPosition La position du curseur en octet. Doit être au niveau du
	 *                       début du noeud.
	 * @return Retourne un objet de type Intern stocké à la position du curseur dans
	 *         le fichier binaire.
	 */
	public Intern readInternFromBinary(long cursorPosition) {
		String familyName = "";
		String firstName = "";
		String county = "";
		String cursus = "";
		String yearIn = "";

		try {
			RandomAccessFile raf = new RandomAccessFile(App.getFichierBin(), "rw");

			// On met le curseur à la position demandée
			raf.seek(cursorPosition);

			// On lit tous les caractères du Nom de famille du Stagiaire incluant les
			// espaces supplémentaires
			for (int i = 0; i < Intern.getMaxCharNames(); i++) {
				familyName += raf.readChar();
			}
			// On lit tous les caractères du Prénom du Stagiaire incluant les espaces
			// supplémentaires
			for (int i = 0; i < Intern.getMaxCharNames(); i++) {
				firstName += raf.readChar();
			}
			// On lit tous les caractères du Département du Stagiaire incluant les espaces
			// supplémentaires
			for (int i = 0; i < Intern.getMaxCharCounty(); i++) {
				county += raf.readChar();
			}
			// On lit tous les caractères de la Formation du Stagiaire incluant les espaces
			// supplémentaires
			for (int i = 0; i < Intern.getMaxCharCursus(); i++) {
				cursus += raf.readChar();
			}
			// On lit tous les caractères du l'année du Stagiaire. Ici il n'y a pas d'espace
			// supplémentaire.
			for (int i = 0; i < Intern.getMaxCharYearIn(); i++) {
				yearIn += raf.readChar();
			}

			// On coupe le flux d'échanges de données entre l'application et le fichier afin
			// de libérer de la mémoire pour l'ordinateur.
			raf.close();
		} catch (IOException e) {

			// S'il y a une erreur, on affiche dans la console d'où elle vient afin de
			// pouvoir la régler
			e.printStackTrace();
		}
		return new Intern(familyName.trim(), firstName.trim(), county.trim(), cursus.trim(), yearIn.trim());
	}

	/**
	 * @param cursorPosition La position du curseur en octet. Doit être au niveau du
	 *                       début du noeud.
	 * @return Retourne un entier correspondant à la place dans le fichier binaire
	 *         du noeud fils gauche dans l'abre binaire. Retourne -1 s'il n'y a pas
	 *         de noeud fils gauche.
	 */
	public int readLeftChildFromBinary(long cursorPosition) {
		cursorPosition += Intern.getSizeIntern();
		int leftChildInt = -1;
		try {
			RandomAccessFile raf = new RandomAccessFile(App.getFichierBin(), "rw");
			raf.seek(cursorPosition);
			leftChildInt = raf.readInt();
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return leftChildInt;
	}

	/**
	 * @param cursorPosition La position du curseur en octet. Doit être au niveau du
	 *                       début du noeud.
	 * @return Retourne un entier correspondant à la place dans le fichier binaire
	 *         du noeud fils droit dans l'abre binaire. Retourne -1 s'il n'y a pas
	 *         de noeud fils droit.
	 */
	public int readRightChildFromBinary(long cursorPosition) {
		cursorPosition += Intern.getSizeIntern() + 4;
		int rightChildInt = -1;
		try {
			RandomAccessFile raf = new RandomAccessFile(App.getFichierBin(), "rw");
			raf.seek(cursorPosition);
			rightChildInt = raf.readInt();
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rightChildInt;
	}

	/**
	 * @param cursorPosition La position du curseur en octet. Doit être au niveau du
	 *                       début du noeud.
	 * @return Retourne un entier correspondant à la place dans le fichier binaire
	 *         du noeud suivant dans la liste chainée. Retourne -1 s'il n'y a pas de
	 *         noeud suivant dans cette liste.
	 */
	public int readTwinFromBinary(long cursorPosition) {
		cursorPosition += Intern.getSizeIntern() + 4 + 4;
		int TwinInt = -1;
		try {
			RandomAccessFile raf = new RandomAccessFile(App.getFichierBin(), "rw");
			raf.seek(cursorPosition);
			TwinInt = raf.readInt();
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return TwinInt;
	}

	/**
	 * @return Retourn un int correspondant au nombre de noeuds écrits dans le
	 *         fichier binaire.
	 */
	public int getNumberNodeInBinary() {
		return (int) (this.getBinarySize() / TreeNode.getSizeNode());
	}

	public void eraseFromBinary(long cursorPosition) {
		try {
			String white = "";
			for (int i = 0; i < TreeNode.getSizeNode() / 2; i++) {
				white += " ";
			}
			RandomAccessFile raf = new RandomAccessFile(App.getFichierBin(), "rw");
			raf.seek(cursorPosition);
			raf.writeChars(white);
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Supprime le fichier binaire
	 */
	public void deleteBinary() {
		File bin = new File(App.getFichierBin());
		bin.delete();
	}

	/**
	 * Lit le fichier binaire et l'affiche dans la console
	 */
	public void readBinary() {
		try {
			RandomAccessFile raf = new RandomAccessFile(App.getFichierBin(), "rw");
			for (long cursor = 0; cursor < this.getBinarySize(); cursor++) {
				Intern intern = this.readInternFromBinary(cursor);
				int indexLeft = this.readLeftChildFromBinary(cursor);
				int indexRight = this.readRightChildFromBinary(cursor);
				int indexTwin = this.readTwinFromBinary(cursor);
				System.out.println(
						intern.getFamilyNameLong().substring(0, 10) + "\t" + intern.getFirstNameLong().substring(0, 11)
								+ "\t" + intern.getCounty() + "\t" + intern.getCursusLong() + "\t" + intern.getYearIn()
								+ "\t" + indexLeft + "\t" + indexRight + "\t" + indexTwin);
				cursor += TreeNode.getSizeNode() - 1;
			}
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
