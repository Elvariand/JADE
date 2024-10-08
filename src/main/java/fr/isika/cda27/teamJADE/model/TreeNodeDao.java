package fr.isika.cda27.teamJADE.model;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.TreeNodeValues.*;

public abstract class TreeNodeDao<T> {

	/**
	 * Cette méthode permet d'écrire un objet de type T dans le fichier binaire en
	 * le parcourant de manière efficace. La position du curseur est au début du
	 * fichier.
	 * 
	 * @param objet L'information sous forme d'objet de type T qui sera stockée dans
	 *              le fichier binaire.
	 */
	public void insert(T object) {
		insert(object, 0);
	}

	/**
	 * Cette méthode permet d'écrire un objet de type T dans le fichier binaire en
	 * le parcourant de manière efficace.
	 * 
	 * @param objet          L'information sous forme d'objet de type T qui sera
	 *                       stockée dans le fichier binaire.
	 * @param cursorPosition La position en Long du curseur dans le fichier binaire.
	 * 
	 */
	public void insert(T object, long cursorPosition) {

		long binarySize = this.getBinarySize();

		// Si le fichier binaire est vide
		if (binarySize == 0) {
			// Alors nous écrivons le noeud au début
			this.writeInBinary(object, 0);
			return;
		}
		String nodeKey = this.readKeyFromBinary(cursorPosition);

		// Si la valeur de la clé de comparaison de l'objet à écrire est plus petite que
		// la valeur de la clé de comparaison de l'objet courant lue dans le fichier
		// binaire.
		if (getKey(object).compareTo(nodeKey) < 0) {

			long newCursorPosition = readLeftChildFromBinary(cursorPosition);

			// Si il n'y a pas de fils gauche, c'est donc que l'information sur le fichier
			// binaire vaut -1
			if (newCursorPosition < 0) {

				/*
				 * Dans ce cas nous remplaçons dans le fichier binaire le -1 correspondant au
				 * fils gauche par la valeur de la position de l'objet que l'on va écrire à la
				 * fin du fichier binaire
				 */
				this.writeIntInBinary(this.getNumberNodeInBinary(), cursorPosition + getObjectSize());
				this.writeInBinary(object);
				return;

				// Si le fils gauche existe déjà, alors nous déplaçons notre curseur à la
				// position correspondante dans le fichier binaire
				// et nous relançons une comparaison d'insertion.
			} else {
				this.insert(object, newCursorPosition * getNodeSize());
				return;
			}
		}

		// Si la valeur de la clé de comparaison de l'objet à écrire est plus grande que
		// la valeur de la clé de comparaison de l'objet courant lu dans le fichier
		// binaire.

		else if (getKey(object).compareTo(nodeKey) > 0) {

			long newCursorPosition = readRightChildFromBinary(cursorPosition);

			// S'il n'y a pas de fils droit, c'est donc que l'information sur le fichier
			// binaire vaut -1
			if (newCursorPosition < 0) {

				/*
				 * Dans ce cas nous remplaçons dans le fichier binaire le -1 correspondant au
				 * fils droit par la valeur de la position de l'objet que l'on va écrire à la
				 * fin du fichier binaire
				 */
				this.writeIntInBinary(this.getNumberNodeInBinary(), cursorPosition + getObjectSize() + INDEX_SIZE);
				this.writeInBinary(object);
				return;

				// Si le fils droit existe déjà, alors nous déplaçons notre curseur à la
				// position correspondante dans le fichier binaire
				// et nous relançons une comparaison d'insertion.
			} else {
				this.insert(object, newCursorPosition * getNodeSize());
				return;
			}
		}

		// Si la valeur de la clé de comparaison de l'objet à écrire est égale
		// à la valeur de la clé de comparaison de l'objet courant lu dans le fichier
		// binaire.
		else {
			int read = 1;

			/*
			 * Pour le fichier binaire nous lisons la valeur de position du doublon de
			 * l'Objet que nous sommes en train de lire. Tant que nous n'obtenons pas -1
			 * c'est que nous ne sommes pas au bout des doublons alors nous déplaçons notre
			 * curseur vers la position du doublon suivant dans le fichier et nous
			 * recommençons à lire la valeur de position du noeud suivant
			 */
			while (read > 0) {
				// Si l'objet est déjà présent
				if (object.equals(this.readObjectFromBinary(cursorPosition))) {
					return;
				}
				read = readTwinFromBinary(cursorPosition);
				if (read > 0)
					cursorPosition = read * getNodeSize();
			}

			/*
			 * Une fois le bout de liste des doublons atteint, nous remplaçons dans le
			 * fichier binaire le -1 correspondant suivant dans la liste par la valeur de la
			 * position de l'Objet que l'on va ajouter à la fin du fichier binaire
			 */
			this.writeIntInBinary(this.getNumberNodeInBinary(),
					cursorPosition + getObjectSize() + INDEX_SIZE + INDEX_SIZE);
			this.writeInBinary(object, binarySize);
		}
		return;

	}

	/**
	 * Met à jour un objet en comparant sa clé à celle d'un ancien objet.
	 *
	 * @param newObject Le nouvel objet à mettre à jour.
	 * @param oldObject L'ancien objet à comparer pour déterminer l'action à
	 *                  effectuer.
	 */
	public void update(T newObject, T oldObject) {
		if (getKey(oldObject).compareTo(getKey(newObject)) == 0) {
			this.update(newObject, oldObject, 0);
		} else {
			this.delete(oldObject);
			this.insert(newObject);
		}

	}

	/**
	 * Met à jour un objet dans une structure binaire à partir d'une position
	 * donnée.
	 * 
	 * Cette méthode permet de parcourir un fichier binaire pour localiser et
	 * remplacer un ancien objet par un nouvel objet, en utilisant une structure
	 * arborescente. La recherche est effectuée en fonction de la comparaison des
	 * clés des objets.
	 * 
	 * @param newObject      Le nouvel objet qui remplacera l'ancien objet s'il est
	 *                       trouvé.
	 * @param oldObject      L'ancien objet à rechercher dans la structure pour le
	 *                       mettre à jour.
	 * @param cursorPosition La position actuelle du curseur dans le fichier
	 *                       binaire.
	 */
	public void update(T newObject, T oldObject, long cursorPosition) {
		long binarySize = this.getBinarySize();

		// Si le fichier binaire est vide
		if (binarySize == 0) {
			// Alors nous écrivons le noeud au début
			System.err.println("Erreur : La base de données est vide.");
			return;
		}

		String nodeKey = this.readKeyFromBinary(cursorPosition);

		// Si la valeur de la clé de comparaison de l'objet à écrire est plus petite que
		// la valeur de la clé de comparaison de l'objet courante lue dans le fichier
		// binaire.
		if (getKey(oldObject).compareTo(nodeKey) < 0) {

			long newCursorPosition = readLeftChildFromBinary(cursorPosition);

			if (newCursorPosition < 0) {
				System.err.println("Erreur : L'objet à modifier n'a pas été pas trouvé.\n");
				return;

			} else {
				this.update(newObject, oldObject, newCursorPosition * getNodeSize());
				return;
			}
		}
		// Si la valeur de la clé de comparaison de l'objet à écrire est plus grande que
		// la valeur de la clé de comparaison de l'objet courant lu dans le fichier
		// binaire.

		else if (getKey(oldObject).compareTo(nodeKey) > 0) {

			long newCursorPosition = readRightChildFromBinary(cursorPosition);

			if (newCursorPosition < 0) {
				System.err.println("Erreur : L'objet à modifier n'a pas été pas trouvé.\n");
				return;

			} else {
				this.update(newObject, oldObject, newCursorPosition * getNodeSize());
				return;
			}
		}

		// Si la valeur de la clé de comparaison de l'objet à écrire est égale à
		// la valeur de la clé de comparaison de l'objet courant lu dans le fichier
		// binaire.
		else {

			int read = 1;

			while (read > 0) {
				// Si l'objet est déjà présent
				if (oldObject.equals(this.readObjectFromBinary(cursorPosition))) {
					this.writeInBinary(newObject, cursorPosition);
					return;
				}
				read = readTwinFromBinary(cursorPosition);
				if (read > 0)
					cursorPosition = read * getNodeSize();
			}

			if (read < 0 && !oldObject.equals(this.readObjectFromBinary(cursorPosition))) {
				System.err.println("Erreur : L'objet à modifier n'a pas été pas trouvé.\n");
			}
		}
		return;

	}

	/**
	 * Cette méthode parcourt le fichier binaire de manière efficace pour trouver
	 * l'emplacement de l'objet à effacer dans le fichier binaire. Elle l'efface et
	 * modifie les parents, enfants et doublons en conséquence. Elle parcourt le
	 * fichier depuis le début.
	 * 
	 * @param intern L'objet de type Objet que nous voulons effacer du fichier
	 *               binaire
	 * 
	 *
	 */
	public void delete(T object) {
		this.delete(object, 0, 0, false);
	}

	/**
	 * Cette méthode parcourt le fichier binaire de manière efficace pour trouver
	 * l'emplacement de l'objet à effacer dans le fichier binaire. Elle l'efface et
	 * modifie les parents, enfants et doublons en conséquence. Elle parcourt le
	 * fichier depuis childCursorPosition.
	 * 
	 * @param intern               L'objet de type Intern que nous voulons effacer
	 *                             du fichier binaire
	 * @param parentCursorPosition La position en Long du parent de l'objet
	 *                             actuellement lu dans le fichier Binaire
	 * @param childCursorPosition  La position en Long de l'objet actuellement lu
	 *                             dans le fichier Binaire
	 * @param isFromLeft           Booléen indiquand si l'objet actuellement lu est
	 *                             un fils gauche ou non de son parent
	 * 
	 *
	 */
	public void delete(T object, long parentCursorPosition, long childCursorPosition, boolean isFromLeft) {

		String nodeKey = this.readKeyFromBinary(childCursorPosition);
		long buffer = childCursorPosition;

		// Si la valeur de la clé de comparaison de l'objet à écrire est plus petite que
		// la valeur de la clé de comparaison de l'objet courant lu dans le fichier
		// binaire.

		if (getKey(object).compareTo(nodeKey) < 0) {

			childCursorPosition = readLeftChildFromBinary(childCursorPosition) * getNodeSize();

			// Si l'on doit aller vers le fils gauche pour trouver l'objet que l'on
			// doit effacer et que l'objet lu dans le fichier binaire n'en a pas (et
			// donc que la valeur égale à -1) alors on a fait une erreur quelque part et on
			// l'affiche
			if (childCursorPosition < 0) {
				System.err.println(
						"************************************\nErreur lors du parcours de l'arbre lors de la suppression. Enfant gauche inexistant. On veut supprimer\n"
								+ object + "/net nous sommes en train de lire\n"
								+ this.readObjectFromBinary(parentCursorPosition)
								+ "\n*******************************************");
				return;
			}

			// S'il n'y a pas d'erreur, on relance la fonction en positionnant le curseur au
			// niveau de l'enfant gauche
			this.delete(object, buffer, childCursorPosition, true);
			return;
		}

		// Si la valeur de la clé de comparaison de l'objet à écrire est plus grande que
		// la valeur de la clé de comparaison de l'objet courant lu dans le fichier
		// binaire.
		else if (getKey(object).compareTo(nodeKey) > 0) {

			childCursorPosition = readRightChildFromBinary(childCursorPosition) * getNodeSize();

			// Si l'on doit aller vers le fils droit pour trouver l'objet que l'on
			// doit effacer et que l'objet lu dans le fichier binaire n'en a pas (et
			// donc que la valeur égale à -1) alors on a fait une erreur quelque part et on
			// l'affiche
			if (childCursorPosition < 0) {
				System.err.println(
						"************************************\nErreur lors du parcours de l'arbre lors de la suppression. Enfant droit inexistant. On veut supprimer\n"
								+ object + "/net nous sommes en train de lire\n"
								+ this.readObjectFromBinary(parentCursorPosition)
								+ "\n*******************************************");
				return;
			}

			// S'il n'y a pas d'erreur, on relance la fonction en positionnant le curseur au
			// niveau de l'enfant droit
			this.delete(object, buffer, childCursorPosition, false);
			return;
			// Si la valeur de la clé de comparaison de l'objet à écrire est égale à
			// la valeur de la clé de comparaison de l'objet courant lu dans le fichier
			// binaire.

		} else {

			// Si on a plusieurs homonymes et donc que la valeur de doublon suivant dans le
			// fichier binaire au niveau de l'objet courant est différente de -1
			if (this.readTwinFromBinary(childCursorPosition) != -1) {

				// On parcourt la suite de doublons jusqu'à trouver celui qui correspond à
				// l'objet que l'on veut supprimer
				while (!object.equals(this.readObjectFromBinary(childCursorPosition)) && childCursorPosition > 0) {
					parentCursorPosition = childCursorPosition;
					childCursorPosition = readTwinFromBinary(childCursorPosition) * getNodeSize();

					// Si nous atteignons le bout de la liste c'est que nous n'avons pas trouver
					// l'objet à supprimer et donc qu'il y a une erreur et on l'affiche.
					if (childCursorPosition < 0) {
						System.err.println(
								"************************************\nErreur lors du parcours de l'arbre lors de la suppression. Doublon inexistant. On veut supprimer\n"
										+ object + "/net nous sommes en train de lire\n"
										+ this.readObjectFromBinary(parentCursorPosition)
										+ "\n*******************************************");
						return;
					}
				}

				// On efface l'objet cible dans notre fichier binaire et met à jour la
				// valeur du suivant dans la liste des doublons chez le parent ou -1 s'il n'y a
				// pas de suivant
				this.writeIntInBinary(this.readTwinFromBinary(childCursorPosition), parentCursorPosition);
				this.eraseFromBinary(childCursorPosition);
				return;

				// S'il n'y a pas de doublon/ d'homonyme
			} else {

				// Si l'objet à supprimer est une feuille et donc le seul cas où la valeur
				// des enfants gauche et droits sont égales
				if (this.readLeftChildFromBinary(childCursorPosition) == this
						.readRightChildFromBinary(childCursorPosition)) {

					// On remplace dans le parent de l'objet qu'on efface, le fils gauche ou le
					// fils droit suivant duquel il est issu, par -1
					this.writeIntInBinary(-1, isFromLeft ? parentCursorPosition + getObjectSize()
							: parentCursorPosition + getObjectSize() + INDEX_SIZE);

					// On efface l'objet voulu dans le fichier binaire
					this.eraseFromBinary(childCursorPosition);
					return;

				} else {

					// Nous parcourons le fichier binaire jusqu'à trouver le bon objet qui va
					// prendre la place de l'objet que l'on veut supprimer
					long[] result = findSubstitute(childCursorPosition);
					long substitute = result[0], parentSubstituteCursor = result[1];

					if (childCursorPosition == 0) {
						T bufferObject = this.readObjectFromBinary(substitute);
						int bufferLeft = this.readLeftChildFromBinary(0);
						int bufferRight = this.readRightChildFromBinary(0);
						int bufferTwin = this.readTwinFromBinary(substitute);
						this.delete(bufferObject);
						this.writeInBinary(bufferObject, 0, bufferLeft, bufferRight, bufferTwin);
						return;
					}

					// On remplace dans le parent de l'objet qu'on efface, le fils gauche ou le
					// fils droit suivant duquel il est issu, par la valeur de position du substitut
					// que l'on a trouvé
					this.writeIntInBinary((int) substitute / getNodeSize(),
							isFromLeft ? parentCursorPosition + getObjectSize()
									: parentCursorPosition + getObjectSize() + INDEX_SIZE);

					// Alors nous remplaçons la valeur de l'enfant gauche du parent du substitut par
					// -1
					this.writeIntInBinary(-1, parentSubstituteCursor + getObjectSize());

					// On transmet au substitut les valeurs des enfants de l'objet que l'on
					// supprime
					this.writeIntInBinary(this.readLeftChildFromBinary(childCursorPosition),
							substitute + getObjectSize());
					this.writeIntInBinary(this.readRightChildFromBinary(childCursorPosition),
							substitute + getObjectSize() + INDEX_SIZE);

					// On efface l'objet
					this.eraseFromBinary(childCursorPosition);
					return;
				}
			}

		}

	}

	/**
	 * 
	 * Cette méthode permet de trouver dans le fichier binaire, un substitut à un
	 * objet que l'on veut supprimer et qui a au moins un enfant
	 * 
	 * @param cursorPosition La position du curseur en long. Doit être au niveau du
	 *                       début de l'objet dont on veut trouver le substitut
	 * @return Un tableau de long avec la position du substitut trouvé ainsi que
	 *         celle de son parent.
	 */
	private long[] findSubstitute(long cursorPosition) {
		int leftChild = this.readLeftChildFromBinary(cursorPosition);
		int rightChild = this.readRightChildFromBinary(cursorPosition);
		// Si il n'y a pas d'enfant gauche
		long[] tabCursorPosition = new long[2];
		if (leftChild == -1 && rightChild != -1) {
			tabCursorPosition[0] = rightChild * getNodeSize();
			tabCursorPosition[1] = cursorPosition;

			// Si il n'y a pas d'enfant droit
		} else if (rightChild == -1 && leftChild != -1) {
			tabCursorPosition[0] = leftChild * getNodeSize();
			tabCursorPosition[1] = cursorPosition;

			// Si il y a deux enfants
		} else if (rightChild != -1 && leftChild != -1) {
			tabCursorPosition = findTwoChildSubstitute(rightChild * getNodeSize());
		} else {
			System.err.println("*****************\nL'objet recherché n'a pas d'enfant.\nL'objet recherché est :\n"
					+ this.readObjectFromBinary(cursorPosition) + "\n************************************");
		}
		return tabCursorPosition;
	}

	/**
	 * Cette méthode permet de trouver dans le fichier binaire, un substitut à un
	 * objet que l'on veut supprimer et qui a exactement 2 enfants.
	 * 
	 * @param cursorPosition La position du curseur en long. Doit être au niveau du
	 *                       début de l'objet dont on veut trouver le substitut
	 * @return Un tableau de long avec la position du substitut trouvé ainsi que
	 *         celle de son parent.
	 */
	private long[] findTwoChildSubstitute(long cursorPosition) {
		// On cherche la valeur minimale du sous arbre droit
		long substitute = cursorPosition, parent = cursorPosition;
		while (cursorPosition > 0) {
			parent = substitute;
			substitute = cursorPosition;
			cursorPosition = this.readLeftChildFromBinary(cursorPosition) * getNodeSize();
		}
		long[] result = { substitute, parent };
		return result;
	}

	/**
	 * @return retourne un long correspondant à la taille du fichier binaire en
	 *         octets
	 */
	public long getBinarySize() {
		long binSize = 0;
		try {
			RandomAccessFile raf = new RandomAccessFile(getBinFile(), "rw");

			binSize = raf.length();

			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return binSize;
	}

	/**
	 * Ajoute le noeud à la fin du fichier binaire en tant que feuille: fils gauche
	 * vaut -1, fils droit vaut -1 et suivant liste chainée vaut -1 aussi.
	 * 
	 * @param objetToAdd L'objet type T à ajouter dans le fichier binaire
	 * 
	 */
	public void writeInBinary(T objectToAdd) {
		writeInBinary(objectToAdd, this.getBinarySize(), -1, -1, -1);
	}

	/**
	 * Ajoute un noeud dans le fichier binaire à une position spécifique en tant que
	 * feuille.
	 * 
	 * @param objectToAdd    L'objet type T à ajouter dans le fichier binaire
	 * @param cursorPosition La position du curseur en octet. Doit être au niveau du
	 *                       début du noeud. Ajoute le noeud en tant que feuille:
	 *                       fils gauche vaut -1, fils droit vaut -1 et suivant
	 *                       liste chainée vaut -1 aussi.
	 */
	public void writeInBinary(T objectToAdd, long cursorPosition) {
		writeInBinary(objectToAdd, cursorPosition, -1, -1, -1);
	}

	/**
	 * Ajoute un noeud dans le fichier binaire à une position spécifique avec des
	 * références aux fils gauche, droit et au noeud suivant dans une liste chaînée.
	 * 
	 * @param objectToAdd    L'objet type T à ajouter dans le fichier binaire
	 * @param cursorPosition La position du curseur en octet. Doit être au niveau du
	 *                       début du noeud.
	 * @param leftChildInt   L'entier correspondant à la position du fils gauche
	 *                       dans le fichier binaire
	 * @param rightChildInt  L'entier correspondant à la position du fils droit dans
	 *                       le fichier binaire
	 * @param twinInt        L'entier correspondant à la position du noeud suivant
	 *                       dans la liste chainée dans le fichier binaire
	 */
	public void writeInBinary(T objectToAdd, long cursorPosition, int leftChildInt, int rightChildInt, int twinInt) {
		try {
			RandomAccessFile raf = new RandomAccessFile(getBinFile(), "rw");

			raf.seek(cursorPosition);

			// Permet pour chaque classe d'écrire les attributs spécifiques
			writeSpecificFields(objectToAdd, raf);

			raf.writeInt(leftChildInt);
			raf.writeInt(rightChildInt);
			raf.writeInt(twinInt);

			raf.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Méthode abstraite à implémenter pour définir la manière dont les attributs
	 * spécifiques de l'objet sont écrits dans le fichier binaire
	 * 
	 * @param object L'objet type T à ajouter dans le fichier binaire
	 * @param raf    Le RandomAccessFile
	 */
	protected abstract void writeSpecificFields(T object, RandomAccessFile raf);

	/**
	 * Ecrit un entier à une position spécifique dans le fichier binaire.
	 * 
	 * @param twinOrChild    L'entier à inscrire dans le fichier binaire.
	 * @param cursorPosition La position du curseur en octet. Doit être au niveau du
	 *                       début du fils gauche, droit ou du noeud suivant dans la
	 *                       liste chainée.
	 */
	public void writeIntInBinary(int twinOrChild, long cursorPosition) {
		try {
			RandomAccessFile raf = new RandomAccessFile(getBinFile(), "rw");

			raf.seek(cursorPosition);
			raf.writeInt(twinOrChild);

			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Lit un noeud à partir du fichier binaire à une position donnée.
	 * 
	 * @param cursorPosition La position du curseur en octet. Doit être au niveau du
	 *                       début du noeud.
	 * @return Retourne un objet de type Objet stocké à la position du curseur dans
	 *         le fichier binaire.
	 */
	protected String readNodeFromBinary(long cursorPosition) {
		return this.readObjectFromBinary(cursorPosition).toString() + " " + readLeftChildFromBinary(cursorPosition)
				+ " " + readRightChildFromBinary(cursorPosition) + " " + readTwinFromBinary(cursorPosition);
	}

	/**
	 * Méthode abstraite à implémenter pour lire l'objet de type T à une position
	 * donnée dans le fichier binaire.
	 * 
	 * @param cursorPosition La position du curseur en octet. Doit être au niveau du
	 *                       début du noeud.
	 * @return Retourne un objet de type T stocké à la position du curseur dans le
	 *         fichier binaire.
	 */
	protected abstract T readObjectFromBinary(long cursorPosition);

	/**
	 * Lit la position du fils gauche d'un noeud à partir du fichier binaire.
	 * 
	 * @param cursorPosition La position du curseur en octet. Doit être au niveau du
	 *                       début du noeud.
	 * @return Retourne un entier correspondant à la place dans le fichier binaire
	 *         du noeud fils gauche dans l'abre binaire. Retourne -1 s'il n'y a pas
	 *         de noeud fils gauche.
	 */
	public int readLeftChildFromBinary(long cursorPosition) {
		cursorPosition += getObjectSize();
		int leftChildInt = -99;
		try {
			RandomAccessFile raf = new RandomAccessFile(getBinFile(), "rw");
			raf.seek(cursorPosition);
			if (raf.readChar() == ' ') {
				raf.close();
				return -99;
			}
			raf.seek(cursorPosition);
			leftChildInt = raf.readInt();
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return leftChildInt;
	}

	/**
	 * Lit la position du fils droit d'un noeud à partir du fichier binaire.
	 * 
	 * @param cursorPosition La position du curseur en octet. Doit être au niveau du
	 *                       début du noeud.
	 * @return Retourne un entier correspondant à la place dans le fichier binaire
	 *         du noeud fils droit dans l'abre binaire. Retourne -1 s'il n'y a pas
	 *         de noeud fils droit.
	 */
	public int readRightChildFromBinary(long cursorPosition) {
		cursorPosition += getObjectSize() + INDEX_SIZE;
		int rightChildInt = -99;
		try {
			RandomAccessFile raf = new RandomAccessFile(getBinFile(), "rw");
			raf.seek(cursorPosition);
			if (raf.readChar() == ' ') {
				raf.close();
				return -99;
			}
			raf.seek(cursorPosition);
			rightChildInt = raf.readInt();
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rightChildInt;
	}

	/**
	 * Lit la position du noeud suivant dans la liste chaînée à partir du fichier
	 * binaire.
	 * 
	 * @param cursorPosition La position du curseur en octet. Doit être au niveau du
	 *                       début du noeud.
	 * @return Retourne un entier correspondant à la place dans le fichier binaire
	 *         du noeud suivant dans la liste chainée. Retourne -1 s'il n'y a pas de
	 *         noeud suivant dans cette liste.
	 */
	public int readTwinFromBinary(long cursorPosition) {
		cursorPosition += getObjectSize() + INDEX_SIZE + INDEX_SIZE;
		int TwinInt = -99;
		try {
			RandomAccessFile raf = new RandomAccessFile(getBinFile(), "rw");
			raf.seek(cursorPosition);
			if (raf.readChar() == ' ') {
				raf.close();
				return -99;
			}
			raf.seek(cursorPosition);
			TwinInt = raf.readInt();
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return TwinInt;
	}

	/**
	 * Lit et retourne le nom de famille à une position donnée dans le fichier
	 * binaire.
	 * 
	 * @param cursorPosition La position du curseur en octet. Doit être au niveau du
	 *                       début du noeud.
	 * @return
	 */
	public String readFamilyNameFromBinary(long cursorPosition) {
		String familyName = "";

		try {
			RandomAccessFile raf = new RandomAccessFile(getBinFile(), "rw");

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
	 * Calcule le nombre de noeuds écrits dans le fichier binaire.
	 * 
	 * @return Retourne un int correspondant au nombre de noeuds écrits dans le
	 *         fichier binaire.
	 */
	public int getNumberNodeInBinary() {
		return (int) (this.getBinarySize() / getNodeSize());
	}

	/**
	 * Efface l'objet à la position cursorPosition sur le fichier Binaire et le
	 * remplace par des espaces.
	 * 
	 * @param cursorPosition long - La position du curseur en octet. Doit être au
	 *                       niveau du début du noeud.
	 */
	public void eraseFromBinary(long cursorPosition) {
		try {
			String white = "";
			for (int i = 0; i < getNodeSize() / 2; i++) {
				white += " ";
			}
			RandomAccessFile raf = new RandomAccessFile(getBinFile(), "rw");
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
		File bin = new File(getBinFile());
		bin.delete();
	}

	/**
	 * Cette méthode lit et affiche l'arbre suivant l'ordre infixe.
	 * 
	 * @param cursorPosition La position du curseur en long. Doit être au niveau du
	 *                       début de l'objet depuis lequel on veut démarrer la
	 *                       lecture de l'arbre.
	 */
	public ArrayList<T> sortView(long cursorPosition, ArrayList<T> list) {

		// Si le position du curseur est négative, c'est une erreur, on ne peut rien
		// lire et on interrompt immédiatement la fonction
		if (cursorPosition < 0) {
			System.err.println("Position du curseur négative");
			return list;
		}

		int leftChild = this.readLeftChildFromBinary(cursorPosition);
		int rightChild = this.readRightChildFromBinary(cursorPosition);

		// Si l'objet lu dans le fichier binaire possède un enfant gauche
		// alors on lit celui-ci en priorité
		if (leftChild > 0)
			sortView(leftChild * getNodeSize(), list);

		// On affiche l'objet lu

		list.add(this.readObjectFromBinary(cursorPosition));

		// S'il y a des homonymes, on les affiche tous
		long twinPosition = this.readTwinFromBinary(cursorPosition) * getNodeSize();
		while (twinPosition > 0) {

			list.add(this.readObjectFromBinary(twinPosition));
			twinPosition = this.readTwinFromBinary(twinPosition) * getNodeSize();
		}

		// Puis on s'intéresse au fils droit de l'objet lu
		if (rightChild > 0)
			sortView(this.readRightChildFromBinary(cursorPosition) * getNodeSize(), list);
		return list;
	}

	/**
	 * Lit le fichier binaire et l'affiche dans la console.
	 */
	protected abstract void readBinary();

	/**
	 * Retourne la taille en octets d'un nœud dans le fichier binaire.
	 */
	protected abstract int getNodeSize();

	/**
	 * Retourne la clé unique d'un objet.
	 */
	protected abstract String getKey(T object);

	/**
	 * Lit la clé d'un objet à partir d'une position spécifiée dans le fichier
	 * binaire.
	 */
	protected abstract String readKeyFromBinary(long cursorPosition);

	/**
	 * Retourne la taille en octets d'un objet dans le fichier binaire.
	 */
	protected abstract int getObjectSize();

	/**
	 * Retourne le chemin du fichier binaire utilisé pour stocker les données.
	 */
	protected abstract String getBinFile();
}
