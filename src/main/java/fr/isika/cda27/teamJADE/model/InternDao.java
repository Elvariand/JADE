package fr.isika.cda27.teamJADE.model;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.TreeNodeValues.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.regex.Pattern;

import fr.isika.cda27.teamJADE.view.App;

public class InternDao extends TreeNodeDao<Intern>{

	/**
	 * Lit le fichier .DON et ajoute tous les stagiaires s'y trouvant dans l'arbre
	 * binaire. Les stagiaires sont ajoutés à l'arbre binaire après chaque ligne marquée par un astérisque (*).
	 */
	public void addFromDon() {

		try {
			// Le fichier .DON à lire
			FileReader fr = new FileReader(App.getInternDonFile());

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
					if (text.length() <= 0 || Pattern.compile("[\\D]").matcher(text).find()) county = "999";
					break;
				case 4:
					cursus = text;
					break;
				case 5:
					yearIn = text;
					if (text.length() <= 0 || Pattern.compile("[\\D]").matcher(text).find()) yearIn = "9999";
					break;

				// Dans le cas où nous sommes sur la sixieme ligne, qui correspond à une ligne
				// avec un astérisque
				default:

					// Nous créons un objet de type Intern avec les variables dans lesquelles nous
					// avons stocké les informations des 5 lignes précédentes
					// et nous ajoutons immédiatement cet objet de type Intern à l'arbre binaire
					this.insert(new Intern(familyName, firstName, Integer.parseInt(county), cursus, Integer.parseInt(yearIn)));

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
	 *  Ajoute un objet {@code Intern} à la fin du fichier .DON.
	 * @param internToAdd L'objet de type Intern à ajouter à la fin du fichier .DON
	 */
	public void addToDon(Intern internToAdd) {

		FileWriter fw;

		try {
			fw = new FileWriter(App.getInternDonFile(), true);
			fw.write(internToAdd.getFamilyName().toUpperCase() + "\n" + internToAdd.getFirstName() + "\n"
					+ internToAdd.getCounty() + "\n" + internToAdd.getCursus() + "\n" + internToAdd.getYearIn()
					+ "\n*\n");

			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Écrit les champs spécifiques d'un objet {@code Intern} dans un fichier binaire.
     * Les champs sont écrits dans l'ordre suivant :
     * - Nom de famille
     * - Prénom
     * - Le numéro de département
     * - Le nom de la formation
     * - L'année d'entrée en formation
     * 
	 * @param internToAdd L'objet {@code Intern} à écrire dans le fichier binaire.
	 * @param raf Le RandomAccessFile 
	 */
	@Override
	protected void writeSpecificFields(Intern internToAdd, RandomAccessFile raf) {
		try {
			raf.writeChars(internToAdd.getFamilyNameLong());
			raf.writeChars(internToAdd.getFirstNameLong());
			raf.writeInt(internToAdd.getCounty());
			raf.writeChars(internToAdd.getCursusLong());
			raf.writeInt(internToAdd.getYearIn());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lit un objet {@code Intern} à partir du fichier binaire à la position du curseur spécifiée.
     * @param cursorPosition La position du curseur en octet. Doit être au niveau du
	 *                       début du noeud.
	 * @return Retourne un objet de type Intern stocké à la position du curseur dans
	 *         le fichier binaire.
	 */
	public Intern readObjectFromBinary(long cursorPosition) {
		String familyName = "";
		String firstName = "";
		int county = 0;
		String cursus = "";
		int yearIn = 0;

		try {
			RandomAccessFile raf = new RandomAccessFile(getBinFile(), "rw");

			// On met le curseur à la position demandée
			raf.seek(cursorPosition);

			// On lit tous les caractères du Nom de famille du Stagiaire incluant les
			// espaces supplémentaires
			for (int i = 0; i < MAX_CHAR_NAMES; i++) {
				familyName += raf.readChar();
			}
			if (familyName.trim().length() == 0) {
				raf.close();
				return new Intern("", "", -99, "", -99);
			}
			
			// On lit tous les caractères du Prénom du Stagiaire incluant les espaces
			// supplémentaires
			for (int i = 0; i < MAX_CHAR_NAMES; i++) {
				firstName += raf.readChar();
			}
			// On lit tous les caractères du Département du Stagiaire incluant les espaces
			// supplémentaires
			county = raf.readInt();
			
			// On lit tous les caractères de la Formation du Stagiaire incluant les espaces
			// supplémentaires
			for (int i = 0; i < MAX_CHAR_CURSUS; i++) {
				cursus += raf.readChar();
			}
			// On lit tous les caractères du l'année du Stagiaire. Ici il n'y a pas d'espace
			// supplémentaire.
			yearIn = raf.readInt();

			// On coupe le flux d'échanges de données entre l'application et le fichier afin
			// de libérer de la mémoire pour l'ordinateur.
			raf.close();
		} catch (IOException e) {

			// S'il y a une erreur, on affiche dans la console d'où elle vient afin de
			// pouvoir la régler
			e.printStackTrace();
		}
		return new Intern(familyName.trim(), firstName.trim(), county, cursus.trim(), yearIn);
	}

	/**
	 * Lit le fichier binaire et l'affiche dans la console.
	 * Les informations affichées incluent :
     * - Index du noeud
     * - Nom de famille
     * - Prénom
     * - Le numéro de département
     * - Le nom de la formation
     * - L'année d'entrée en formation
     * - Index des enfants gauche, droit et jumeau
	 */
	public void readBinary() {
		try {
			RandomAccessFile raf = new RandomAccessFile(getBinFile(), "rw");
			for (long cursor = 0; cursor < this.getBinarySize(); cursor++) {
				int indexLeft = -99;
				int indexRight = -99;
				int indexTwin = -99;
				
				Intern intern = this.readObjectFromBinary(cursor);
				
				if (intern.getFamilyName() != "") {
				indexLeft = this.readLeftChildFromBinary(cursor);
				indexRight = this.readRightChildFromBinary(cursor);
				indexTwin = this.readTwinFromBinary(cursor);
				}
				System.out.println(
						cursor/INTERN_NODE_SIZE +" : " + intern.getFamilyNameLong().substring(0, 10) + "\t" + intern.getFirstNameLong().substring(0, 11)
								+ "\t" + intern.getCounty() + "\t" + intern.getCursusLong() + "\t" + intern.getYearIn()
								+ "\t" + indexLeft + "\t" + indexRight + "\t" + indexTwin);
				cursor += INTERN_NODE_SIZE - 1;
			}
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Lit la clé (nom de famille) d'un objet {@code Intern} à partir du fichier binaire à la position du curseur spécifiée.
	 * @param cursorPosition La position du curseur en octet. Doit être au niveau du
	 *                       début du noeud.
	 * @return La clé (nom de famille) lue à partir du fichier binaire.
	 */
	public String readKeyFromBinary(long cursorPosition) {
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
	 * Retourne la taille du nœud utilisé dans la structure de données.
	 * 
	 * @return La taille du nœud pour les objets {@code Intern}.
	 */
	@Override
	protected int getNodeSize() {
		return INTERN_NODE_SIZE;
	}
	/**
	 * Retourne la clé associée à un objet {@code Intern}.
	 * 
	 * @param intern L'objet {@code Intern} dont la clé doit être extraite.
	 * @return La clé associée à l'objet {@code Intern}, qui est le nom de famille sans espaces.
	 */
	@Override
	protected String getKey(Intern intern) {
		return intern.getFamilyName().trim();
	}

	/**
	 * Retourne la taille de l'objet {@code Intern} en mémoire.
	 * 
	 * @return La taille de l'objet {@code Intern} en mémoire.
	 */
	@Override
	protected int getObjectSize() {
		return INTERN_SIZE;
	}
	/**
	 * Retourne le chemin du fichier binaire associé aux objets {@code Intern}.
	 * 
	 * @return Le chemin du fichier binaire pour les objets {@code Intern}.
	 */
	@Override
	protected String getBinFile() {
		return App.getInternBinFile();
	}


}

