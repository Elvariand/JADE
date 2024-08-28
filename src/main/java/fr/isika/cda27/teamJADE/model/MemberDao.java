package fr.isika.cda27.teamJADE.model;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.TreeNodeValues.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

import fr.isika.cda27.teamJADE.view.App;

public class MemberDao {

	/**
	 * @param intern L'information sous forme d'objet de type Intern qui sera
	 *               stockée dans le fichier binaire.
	 * 
	 *               Cette fonction permet d'écrire un objet de type Intern dans le
	 *               fichier binaire en le parcourant de manière efficace. La
	 *               position du curseur est au début du fichier.
	 */
	public void insert(Member member) {
		insert(member, 0);
	}

	/**
	 * @param intern         L'information sous forme d'objet de type Intern qui
	 *                       sera stockée dans le fichier binaire.
	 * @param cursorPosition La position en Long du curseur dans le fichier binaire.
	 * 
	 *                       Cette fonction permet d'écrire un objet de type Intern
	 *                       dans le fichier binaire en le parcourant de manière
	 *                       efficace.
	 */
	public void insert(Member member, long cursorPosition) {

		long binarySize = this.getBinarySize();

		// Si le fichier binaire est vide
		if (binarySize == 0) {
			// Alors nous écrivons le noeud au début
			this.writeInBinary(member, 0);
			return;
		}

		String nodeAlias = this.readAliasFromBinary(cursorPosition);

		// Si la valeur du nom de famille du Stagiaire à écrire est plus petite que
		// celle du nom de famille du Stagiaire courant lu dans le fichier binaire.
		if (member.getAlias().compareTo(nodeAlias) < 0) {

			long newCursorPosition = readLeftChildFromBinary(cursorPosition);

			// Si il n'y a pas de fils gauche, c'est donc que l'information sur le fichier
			// binaire vaut -1
			if (newCursorPosition < 0) {

				/*
				 * Dans ce cas nous remplaçons dans le fichier binaire le -1 correspondant au
				 * fils gauche par la valeur de la position du Stagiaire que l'on va écrire à la
				 * fin du fichier binaire
				 */
				this.writeIntInBinary(this.getNumberNodeInBinary(), cursorPosition + MEMBER_SIZE);
				this.writeInBinary(member);
				return;

				// Si le fils gauche existe déjà, alors nous déplaçons notre curseur à la
				// position correspondante dans le fichier binaire
				// et nous relançons une comparaison d'insertion.
			} else {
				this.insert(member, newCursorPosition * MEMBER_NODE_SIZE);
				return;
			}
		}

		// Si la valeur du nom de famille du Stagiaire à écrire est plus grande que
		// celle
		// du nom de famille du Stagiaire courant lu dans le fichier binaire.
		else if (member.getAlias().compareTo(nodeAlias) > 0) {

			long newCursorPosition = readRightChildFromBinary(cursorPosition);

			// Si il n'y a pas de fils droit, c'est donc que l'information sur le fichier
			// binaire vaut -1
			if (newCursorPosition < 0) {

				/*
				 * Dans ce cas nous remplaçons dans le fichier binaire le -1 correspondant au
				 * fils droit par la valeur de la position du Stagiaire que l'on va écrire à la
				 * fin du fichier binaire
				 */
				this.writeIntInBinary(this.getNumberNodeInBinary(),
						cursorPosition + MEMBER_SIZE + INDEX_SIZE);
				this.writeInBinary(member);
				return;

				// Si le fils droit existe déjà, alors nous déplaçons notre curseur à la
				// position correspondante dans le fichier binaire
				// et nous relançons une comparaison d'insertion.
			} else {
				this.insert(member, newCursorPosition * MEMBER_NODE_SIZE);
				return;
			}
		}

		// Si la valeur du nom de famille du Stagiaire à écrire est égale à celle du nom
		// de famille de l'Intern courant lu dans le fichier binaire.
		else {

			int read = 1;

			/*
			 * Pour le fichier binaire nous lisons la valeur de position du doublon du
			 * Stagiaire que nous sommes en train de lire. Tant que nous n'obtenons pas -1
			 * c'est que nous ne sommes pas au bout des doublons alors nous déplaçons notre
			 * curseur vers la position du doublon suivant dans le fichier et nous
			 * recommençons à lire la valeur de position du noeud suivant
			 */
			while (read > 0) {
				// Si le Stagiaire est déjà présent
				if (member.equals(this.readMemberFromBinary(cursorPosition))) {
					System.out.println("Le stagiaire est déjà présent dans la base de données");
					return;
				}
				read = readTwinFromBinary(cursorPosition);
				if (read > 0)
					cursorPosition = read * MEMBER_NODE_SIZE;
			}

			/*
			 * Une fois le bout de liste des doublons atteint, nous remplaçons dans le
			 * fichier binaire le -1 correspondant suivant dans la liste par la valeur de la
			 * position du Stagiaire que l'on va ajouter à la fin du fichier binaire
			 */
			this.writeIntInBinary(this.getNumberNodeInBinary(),
					cursorPosition + MEMBER_SIZE + INDEX_SIZE + INDEX_SIZE);
			this.writeInBinary(member, binarySize);
		}
		return;

	}

	/**
	 * @param intern L'objet de type Intern que nous voulons effacer du fichier
	 *               binaire
	 * 
	 *               Cette fonction parcourt le fichier binaire de manière efficace
	 *               pour trouver l'emplacement du Stagiaire à effacer dans le
	 *               fichier binaire. Elle l'efface et modifie les parents, enfants
	 *               et doublons en conséquence. Elle parcourt le fichier depuis le
	 *               début.
	 */
	public void delete(Intern intern) {
		this.delete(intern, 0, 0, false);
	}

	/**
	 * @param intern               L'objet de type Intern que nous voulons effacer
	 *                             du fichier binaire
	 * @param parentCursorPosition La position en Long du parent du Stagiaire
	 *                             actuellement lu dans le fichier Binaire
	 * @param childCursorPosition  La position en Long du Stagiaire actuellement lu
	 *                             dans le fichier Binaire
	 * @param isFromLeft           Booléen indiquand si le Stagiaire actuellement lu
	 *                             est un fils gauche ou non de son parent
	 * 
	 *                             Cette fonction parcourt le fichier binaire de
	 *                             manière efficace pour trouver l'emplacement du
	 *                             Stagiaire à effacer dans le fichier binaire. Elle
	 *                             l'efface et modifie les parents, enfants et
	 *                             doublons en conséquence. Elle parcourt le fichier
	 *                             depuis childCursorPosition.
	 */
	public void delete(Intern intern, long parentCursorPosition, long childCursorPosition, boolean isFromLeft) {

		String nodeFamilyName = this.readAliasFromBinary(childCursorPosition);

		// Si la valeur du nom de famille du Stagiaire à écrire est plus petite que
		// celle du nom de famille du Stagiaire lu dans le fichier binaire.
		if (intern.getFamilyName().compareTo(nodeFamilyName) < 0) {

			childCursorPosition = readLeftChildFromBinary(childCursorPosition) * MEMBER_NODE_SIZE;

			// Si l'on doit aller vers le fils gauche pour trouver le Stagiaire que l'on
			// doit effacer et que le Stagiaire lu dans le fichier binaire n'en a pas (et
			// donc que la valeur égale à -1) alors on a fait une erreur quelque part et on
			// l'affiche
			if (childCursorPosition < 0) {
				System.err.println(
						"************************************\nErreur lors du parcours de l'arbre lors de la suppression. Enfant gauche inexistant. On veut supprimer\n"
								+ intern + "/net nous sommes en train de lire\n"
								+ this.readMemberFromBinary(parentCursorPosition)
								+ "\n*******************************************");
				return;
			}

			// S'il n'y a pas d'erreur, on relance la fonction en positionnant le curseur au
			// niveau de l'enfant gauche
			this.delete(intern, parentCursorPosition, childCursorPosition, true);
			return;
		}

		// Si la valeur du nom de famille du Stagiaire à écrire est plus grande que
		// celle du nom de famille du Stagiaire lu dans le fichier binaire.
		else if (intern.getFamilyName().compareTo(nodeFamilyName) > 0) {

			childCursorPosition = readRightChildFromBinary(childCursorPosition) * MEMBER_NODE_SIZE;

			// Si l'on doit aller vers le fils droit pour trouver le Stagiaire que l'on
			// doit effacer et que le Stagiaire lu dans le fichier binaire n'en a pas (et
			// donc que la valeur égale à -1) alors on a fait une erreur quelque part et on
			// l'affiche
			if (childCursorPosition < 0) {
				System.err.println(
						"************************************\nErreur lors du parcours de l'arbre lors de la suppression. Enfant droit inexistant. On veut supprimer\n"
								+ intern + "/net nous sommes en train de lire\n"
								+ this.readMemberFromBinary(parentCursorPosition)
								+ "\n*******************************************");
				return;
			}

			// S'il n'y a pas d'erreur, on relance la fonction en positionnant le curseur au
			// niveau de l'enfant droit
			this.delete(intern, parentCursorPosition, childCursorPosition, false);
			return;

			// Si la valeur du nom de famille du Stagiaire à écrire est égale à
			// celle du nom de famille du Stagiaire lu dans le fichier binaire.
		} else {

			// Si on a plusieurs homonymes et donc que la valeur de doublon suivant dans le
			// fichier binaire au niveau du stagiaire courant est différente de -1
			if (this.readTwinFromBinary(childCursorPosition) != -1) {

				// On parcourt la suite de doublons jusqu'à trouver celui qui correspond au
				// Stagiaire que l'on veut supprimer
				while (!intern.equals(this.readMemberFromBinary(childCursorPosition)) && childCursorPosition > 0) {
					parentCursorPosition = childCursorPosition;
					childCursorPosition = readTwinFromBinary(childCursorPosition) * MEMBER_NODE_SIZE;

					// Si nous atteignons le bout de la liste c'est que nous n'avons pas trouver le
					// stagiaire à supprimer et donc qu'il y a une erreur et on l'affiche.
					if (childCursorPosition < 0) {
						System.err.println(
								"************************************\nErreur lors du parcours de l'arbre lors de la suppression. Doublon inexistant. On veut supprimer\n"
										+ intern + "/net nous sommes en train de lire\n"
										+ this.readMemberFromBinary(parentCursorPosition)
										+ "\n*******************************************");
						return;
					}
				}

				// On efface le Stagiaire cible dans notre fichier binaire et met à jour la
				// valeur du suivant dans la liste des doublons chez le parent ou -1 s'il n'y a
				// pas de suivant
				this.writeIntInBinary(this.readTwinFromBinary(childCursorPosition), parentCursorPosition);
				this.eraseFromBinary(childCursorPosition);
				return;

				// S'il n'y a pas de doublon/ d'homonyme
			} else {

				// Si le Stagiaire à supprimer est une feuille et donc le seul cas où la valeur
				// des enfants gauche et droits sont égales
				if (this.readLeftChildFromBinary(childCursorPosition) == this
						.readRightChildFromBinary(childCursorPosition)) {

					// On remplace dans le parent du Stagiaire qu'on efface, le fils gauche ou le
					// fils droit suivant duquel il est issu, par -1
					this.writeIntInBinary(-1, isFromLeft ? parentCursorPosition + MEMBER_SIZE
							: parentCursorPosition + MEMBER_SIZE + INDEX_SIZE);

					// On efface le Stagiaire voulu dans le fichier binaire
					this.eraseFromBinary(childCursorPosition);
					return;

				} else {

					// Nous parcourons le fichier binaire jusqu'à trouver le bon Stagiaire qui va
					// prendre la place du Stagiaire que l'on veut supprimer
					long[] result = findSubstitute(childCursorPosition);
					long substitute = result[0], parentSubstituteCursor = result[1];

					// On remplace dans le parent du Stagiaire qu'on efface, le fils gauche ou le
					// fils droit suivant duquel il est issu, par la valeur de position du substitut
					// que l'on a trouvé
					this.writeIntInBinary((int) substitute / MEMBER_NODE_SIZE,
							isFromLeft ? parentCursorPosition + MEMBER_SIZE
									: parentCursorPosition + MEMBER_SIZE + INDEX_SIZE);

					// Alors nous remplaçons la valeur de l'enfant gauche du parent du substitut par
					// -1
					this.writeIntInBinary(-1, parentSubstituteCursor + MEMBER_SIZE);

					// On transmet au substitut les valeurs des enfants du Stagiaire que l'on
					// supprime
					this.writeIntInBinary(this.readLeftChildFromBinary(childCursorPosition),
							substitute + MEMBER_SIZE);
					this.writeIntInBinary(this.readRightChildFromBinary(childCursorPosition),
							substitute + MEMBER_SIZE + INDEX_SIZE);

					// On efface le stagiaire
					this.eraseFromBinary(childCursorPosition);
					return;
				}
			}

		}

	}

	/**
	 * @param cursorPosition La position du curseur en long. Doit être au niveau du
	 *                       début du stagiaire dont on veut trouver le substitut
	 * @return Un tableau de long avec la position du substitut trouvé ainsi que
	 *         celle de son parent.
	 * 
	 *         Cette fonction permet de trouver dans le fichier binaire, un
	 *         substitut à un stagiaire que l'on veut supprimer et qui a au moins un
	 *         enfant
	 */
	private long[] findSubstitute(long cursorPosition) {
		int leftChild = this.readLeftChildFromBinary(cursorPosition);
		int rightChild = this.readRightChildFromBinary(cursorPosition);
		// Si il n'y a pas d'enfant gauche
		long[] tabCursorPosition = new long[2];
		if (leftChild == -1 && rightChild != -1) {
			tabCursorPosition[0] = rightChild * MEMBER_NODE_SIZE;
			tabCursorPosition[1] = cursorPosition;

			// Si il n'y a pas d'enfant droit
		} else if (rightChild == -1 && leftChild != -1) {
			tabCursorPosition[0] = leftChild * MEMBER_NODE_SIZE;
			tabCursorPosition[1] = cursorPosition;

			// Si il y a deux enfants
		} else if (rightChild != -1 && leftChild != -1) {
			tabCursorPosition = findTwoChildSubstitute(rightChild * MEMBER_NODE_SIZE);
		} else {
			System.err.println(
					"*****************\nLe Stagiaire recherché n'a pas d'enfant.\nLe stagiaire recherché est :\n"
							+ this.readMemberFromBinary(cursorPosition) + "\n************************************");
		}
		return tabCursorPosition;
	}

	/**
	 * @param cursorPosition La position du curseur en long. Doit être au niveau du
	 *                       début du stagiaire dont on veut trouver le substitut
	 * @return Un tableau de long avec la position du substitut trouvé ainsi que
	 *         celle de son parent.
	 * 
	 *         Cette fonction permet de trouver dans le fichier binaire, un
	 *         substitut à un stagiaire que l'on veut supprimer et qui a exactement
	 *         2 enfants
	 */
	private long[] findTwoChildSubstitute(long cursorPosition) {
		// On cherche la valeur minimale du sous arbre droit
		long substitute = cursorPosition, parent = cursorPosition;
		while (cursorPosition > 0) {
			parent = substitute;
			substitute = cursorPosition;
			cursorPosition = this.readLeftChildFromBinary(cursorPosition) * MEMBER_NODE_SIZE;
		}
		long[] result = { substitute, parent };
		return result;
	}

	/**
	 * @param cursorPosition La position du curseur en long. Doit être au niveau du
	 *                       début du stagiaire depuis lequel on veut démarrer la
	 *                       lecture de l'arbre
	 * 
	 *                       Cette fonction lit et affiche l'arbre suivant l'ordre
	 *                       infixe
	 */
	public void sortView(long cursorPosition) {

		// Si le position du curseur est négative, c'est une erreur, on ne peut rien
		// lire et on interrompt immédiatement la fonction
		if (cursorPosition < 0) {
			System.err.println("Position du curseur négative");
			return;
		}

		int leftChild = this.readLeftChildFromBinary(cursorPosition);
		int rightChild = this.readRightChildFromBinary(cursorPosition);

		// Si le Stagiaire lu dans le fichier binaire possède un enfant gauche
		// alors on lit celui-ci en priorité
		if (leftChild != -1)
			sortView(leftChild * MEMBER_NODE_SIZE);

		// On affiche le Stagiaire lu
		System.out.println(this.readMemberFromBinary(cursorPosition));

		// S'il y a des homonymes, on les affiche tous
		long twinPosition = this.readTwinFromBinary(cursorPosition) * MEMBER_NODE_SIZE;
		while (twinPosition > 0) {
			System.out.println(this.readMemberFromBinary(twinPosition));
			twinPosition = this.readTwinFromBinary(twinPosition) * MEMBER_NODE_SIZE;
		}

		// Puis on s'intéresse au fils droit du Stagiaire lu
		if (rightChild != -1)
			sortView(this.readRightChildFromBinary(cursorPosition) * MEMBER_NODE_SIZE);
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
			String alias = "";
			String password = "";
			String admin = "";
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
					alias = text;
					break;

				// Dans le cas où nous sommes sur la deuxième ligne, nous stockons cette ligne
				// de texte dans une autre variable.
				case 2:
					password = text;
					break;

				// Idem pour les lignes 3, 4 et 5
				case 3:
					admin = text;
					break;
				
				// Dans le cas où nous sommes sur la sixieme ligne, qui correspond à une ligne
				// avec un astérisque
				default:

					// Nous créons un objet de type Intern avec les variables dans lesquelles nous
					// avons stocké les informations des 5 lignes précédentes
					// et nous ajoutons immédiatement cet objet de type Intern à l'arbre binaire
					this.insert(new Member(alias, password, Boolean.parseBoolean(admin)));

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
	public void writeInBinary(Member memberToAdd) {
		writeInBinary(memberToAdd, this.getBinarySize(), -1, -1, -1);
	}

	/**
	 * @param internToAdd    L'objet type Intern à ajouter dans le fichier binaire
	 * @param cursorPosition La position du curseur en octet. Doit être au niveau du
	 *                       début du noeud. Ajoute le noeud en tant que feuille:
	 *                       fils gauche vaut -1, fils droit vaut -1 et suivant
	 *                       liste chainée vaut -1 aussi.
	 */
	public void writeInBinary(Member memberToAdd, long cursorPosition) {
		writeInBinary(memberToAdd, cursorPosition, -1, -1, -1);
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
	public void writeInBinary(Member memberToAdd, long cursorPosition, int leftChildInt, int rightChildInt,
			int twinInt) {
		try {
			RandomAccessFile raf = new RandomAccessFile(App.getFichierBin(), "rw");

			raf.seek(cursorPosition);
			raf.writeChars(memberToAdd.getAlias());
			raf.writeChars(memberToAdd.getPassword());
			raf.writeChars(memberToAdd.getAdmin());
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
	public Member readMemberFromBinary(long cursorPosition) {
		String alias = "";
		String password = "";
		String admin = "";

		try {
			RandomAccessFile raf = new RandomAccessFile(App.getFichierBin(), "rw");

			// On met le curseur à la position demandée
			raf.seek(cursorPosition);

			// On lit tous les caractères du pseudo du Membre incluant les
			// espaces supplémentaires
			for (int i = 0; i < MAX_CHAR_ALIAS; i++) {
				alias += raf.readChar();
			}
			// On lit tous les caractères du mot de passe du Membre incluant les espaces
			// supplémentaires
			for (int i = 0; i < MAX_CHAR_PASSWORD; i++) {
				password += raf.readChar();
			}
			// On lit tous les caractères d'Admin du Membre incluant les espaces
			// supplémentaires
			for (int i = 0; i < MAX_CHAR_ADMIN; i++) {
				admin += raf.readChar();
			}

			// On coupe le flux d'échanges de données entre l'application et le fichier afin
			// de libérer de la mémoire pour l'ordinateur.
			raf.close();
		} catch (IOException e) {

			// S'il y a une erreur, on affiche dans la console d'où elle vient afin de
			// pouvoir la régler
			e.printStackTrace();
		}
		return new Member(alias.trim(), password.trim(), Boolean.parseBoolean(admin.trim()));
	}

	/**
	 * @param cursorPosition La position du curseur en octet. Doit être au niveau du
	 *                       début du noeud.
	 * @return Retourne un entier correspondant à la place dans le fichier binaire
	 *         du noeud fils gauche dans l'abre binaire. Retourne -1 s'il n'y a pas
	 *         de noeud fils gauche.
	 */
	public int readLeftChildFromBinary(long cursorPosition) {
		cursorPosition += MEMBER_SIZE;
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
		cursorPosition += MEMBER_SIZE + INDEX_SIZE;
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
		cursorPosition += MEMBER_SIZE + INDEX_SIZE + INDEX_SIZE;
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
	 * @param cursorPosition La position du curseur en octet. Doit être au niveau du
	 *                       début du noeud.
	 * @return
	 */
	public String readAliasFromBinary(long cursorPosition) {
		String familyName = "";

		try {
			RandomAccessFile raf = new RandomAccessFile(App.getFichierBin(), "rw");

			// On met le curseur à la position demandée
			raf.seek(cursorPosition);

			// On lit tous les caractères du Nom de famille du Stagiaire incluant les
			// espaces supplémentaires
			for (int i = 0; i < MAX_CHAR_NAMES; i++) {
				familyName += raf.readChar();
			}

			// On coupe le flux d'échanges de données entre l'application et le fichier afin
			// de libérer de la mémoire pour l'ordinateur.
			raf.close();
		} catch (IOException e) {

			// S'il y a une erreur, on affiche dans la console d'où elle vient afin de
			// pouvoir la régler
			e.printStackTrace();
		}
		return familyName.trim();
	}

	/**
	 * @return Retourn un int correspondant au nombre de noeuds écrits dans le
	 *         fichier binaire.
	 */
	public int getNumberNodeInBinary() {
		return (int) (this.getBinarySize() / MEMBER_NODE_SIZE);
	}

	/**
	 * @param cursorPosition long - La position du curseur en octet. Doit être au
	 *                       niveau du début du noeud.
	 * 
	 *                       Efface le Stagiaire à la position cursorPosition sur le
	 *                       fichier Binaire et le remplace par des espaces.
	 */
	public void eraseFromBinary(long cursorPosition) {
		try {
			String white = "";
			for (int i = 0; i < MEMBER_NODE_SIZE / 2; i++) {
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
				Member member = this.readMemberFromBinary(cursor);
				int indexLeft = this.readLeftChildFromBinary(cursor);
				int indexRight = this.readRightChildFromBinary(cursor);
				int indexTwin = this.readTwinFromBinary(cursor);
				System.out.println(
						member.getAlias().substring(0, 10) + "\t" + member.getPassword().substring(0, 11)
								+ "\t" + member.getAdmin() + "\t" + indexLeft + "\t" + indexRight + "\t" + indexTwin);
				cursor += MEMBER_NODE_SIZE - 1;
			}
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

