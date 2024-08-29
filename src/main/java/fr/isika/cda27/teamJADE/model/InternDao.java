package fr.isika.cda27.teamJADE.model;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.TreeNodeValues.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

import fr.isika.cda27.teamJADE.view.App;

public class InternDao extends TreeNodeDao<Intern>{

	/**
	 * Lit le fichier .DON et ajoute tous les stagiaires s'y trouvant dans l'arbre
	 * binaire
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
	 * @param memberToAdd L'objet type Member à ajouter dans le fichier binaire
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
	 * Lit le fichier binaire et l'affiche dans la console
	 */
	public void readBinary() {
		try {
			RandomAccessFile raf = new RandomAccessFile(getBinFile(), "rw");
			for (long cursor = 0; cursor < this.getBinarySize(); cursor++) {
				Intern intern = this.readObjectFromBinary(cursor);
				int indexLeft = this.readLeftChildFromBinary(cursor);
				int indexRight = this.readRightChildFromBinary(cursor);
				int indexTwin = this.readTwinFromBinary(cursor);
				System.out.println(
						intern.getFamilyNameLong().substring(0, 10) + "\t" + intern.getFirstNameLong().substring(0, 11)
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
	 * @param cursorPosition La position du curseur en octet. Doit être au niveau du
	 *                       début du noeud.
	 * @return
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

	@Override
	protected int getNodeSize() {
		return INTERN_NODE_SIZE;
	}

	@Override
	protected String getKey(Intern intern) {
		return intern.getFamilyName();
	}


	@Override
	protected int getObjectSize() {
		return INTERN_SIZE;
	}

	@Override
	protected String getBinFile() {
		return App.getInternBinFile();
	}


}

