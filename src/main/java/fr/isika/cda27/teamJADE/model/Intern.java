package fr.isika.cda27.teamJADE.model;

import java.util.Objects;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.TreeNodeValues.*;

public class Intern {

	private String familyName; // 62 octets
	private String firstName; // 62
	private int county; // 4 octets
	private String cursus; // 20
	private int yearIn; // 4 octets

	
	
	/**
	 * Crée un objet Stagiaire
	 * 
	 * @param familyName Le nom de famille du Stagiaire
	 * @param firstName Le prénom du Stagiaire
	 * @param county Le numéro de département du Stagiaire
	 * @param cursus Le nom de la formation du Stagiaire
	 * @param yearIn L'année d'entrée en formation
	 */
	public Intern(String familyName, String firstName, int county, String cursus, int yearIn) {
		this.familyName = familyName;
		this.firstName = firstName;
		this.county = county;
		this.cursus = cursus;
		this.yearIn = yearIn;
	}
		
	/**
	 * @return Le nom de famille
	 */
	public String getFamilyName() {
		return familyName;
	}

	/**
	 * @param familyName Le nom de famille à définir
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	/**
	 * @return Le prénom
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName Le prénom à définir
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return Le numéro de département
	 */
	public int getCounty() {
		return county;
	}

	/**
	 * @return Le numéro de département en chaine de caractère
	 */
	public String getCountyString() {
		return Integer.toString(county);
	}
	
	/**
	 * @param county Le numéro de département à définir
	 */
	public void setCounty(int county) {
		this.county = county;
	}

	/**
	 * @return Le nom de la formation
	 */
	public String getCursus() {
		return cursus;
	}

	/**
	 * @param cursus Le nom de la formation à définir
	 */
	public void setCursus(String cursus) {
		this.cursus = cursus;
	}

	/**
	 * @return L'année d'entrée en formation
	 */
	public int getYearIn() {
		return yearIn;
	}

	/**
	 * @return L'année d'entrée en formation en chaine de caractère
	 */
	public String getYearInString() {
		return Integer.toString(yearIn);
	}
	
	
	/**
	 * @param yearIn  L'année d'entrée en formation à définir
	 */
	public void setYearIn(int yearIn) {
		this.yearIn = yearIn;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Intern [");
		if (familyName != null) {
			builder.append("familyName=");
			builder.append(familyName);
			builder.append(", ");
		}
		if (firstName != null) {
			builder.append("firstName=");
			builder.append(firstName);
			builder.append(", ");
		}
		if (county != 0) {
			builder.append("county=");
			builder.append(county);
			builder.append(", ");
		}
		if (cursus != null) {
			builder.append("cursus=");
			builder.append(cursus);
			builder.append(", ");
		}
		if (yearIn != 0) {
			builder.append("yearIn=");
			builder.append(yearIn);
		}
		builder.append("]");
		return builder.toString();
	}

	/**
	 * Génère un code de hachage pour cet objet basé sur ses champs. Le code de hachage est 
	 * calculé à l'aide de {@link java.util.Objects#hash(Object...)} qui combine 
	 * ces champs pour produire un résultat entier unique, garantissant que les objets 
	 * ayant les mêmes valeurs de champs auront le même code de hachage.
	 *
	 * @return un entier représentant le code de hachage de cet objet.
	 * @see java.util.Objects#hash(Object...)
	 */
	@Override
	public int hashCode() {
		return Objects.hash(county, cursus, familyName, firstName, yearIn);
	}
	/**
	 * Vérifie si cet objet est égal à un autre objet. Deux objets de type 
	 * {@code Intern} sont considérés égaux s'ils ont les mêmes valeurs pour les 
	 * champs `county`, `cursus`, `familyName`, `firstName` et `yearIn`.
	 *
	 * @param obj L'objet à comparer avec cet objet.
	 * @return {@code true} si les objets sont égaux, sinon {@code false}.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Intern other = (Intern) obj;
		return county == other.county && Objects.equals(cursus, other.cursus)
				&& Objects.equals(familyName, other.familyName) && Objects.equals(firstName, other.firstName)
				&& yearIn == other.yearIn;
	}


	/**
	 * Obtient le nom de famille sous une forme longue, avec un formatage pour atteindre une longueur
	 * de caractères spécifiée.
	 * @return le nom de famille formaté pour être exactement de longueur {@code MAX_CHAR_NAMES}. 
	 *         Si le nom de famille original est plus court, des espaces sont ajoutés à la fin. Si le nom de famille original est plus long,
	 *         il est tronqué.
	 */
	public String getFamilyNameLong() {
		String familyNameLong = this.familyName;
		if (familyNameLong.length() < MAX_CHAR_NAMES) {

			for (int i = this.familyName.length(); i < MAX_CHAR_NAMES; i++) {
				familyNameLong += " ";
			}
		} else {
			familyNameLong = familyNameLong.substring(0,MAX_CHAR_NAMES);
		}
		return familyNameLong;
	}
	/**
	 * Obtient le prénom sous une forme longue, avec un formatage pour atteindre une longueur
	 * de caractères spécifiée.
	 * @return le prénom formaté pour être exactement de longueur {@code MAX_CHAR_NAMES}. 
	 *         Si le prénom original est plus court, des espaces sont ajoutés à la fin. Si le prénom original est plus long,
	 *         il est tronqué.
	 */
	public String getFirstNameLong() {
		String firstNameLong = this.firstName;
		if (firstNameLong.length() < MAX_CHAR_NAMES) {
			
			for (int i = this.firstName.length(); i < MAX_CHAR_NAMES; i++) {
				firstNameLong += " ";
			}
		} else {
			firstNameLong = firstNameLong.substring(0,MAX_CHAR_NAMES);
		}
		return firstNameLong;
	}
	
	
	/**
	 * Obtient le nom de la formation sous une forme longue, en formatant le texte pour atteindre une longueur
	 * de caractères spécifiée.
	 *  
	 * @return Le nom de la formation formaté pour être exactement de longueur {@code MAX_CHAR_CURSUS}. 
	 *         Si le nom de la formation original est plus court, des espaces sont ajoutés à la fin. Si le nom de la formation original est plus long,
	 *         il est tronqué à la longueur maximale.
	 */
	public String getCursusLong() {
		String cursusLong = this.cursus;
		if (cursusLong.length() < MAX_CHAR_CURSUS) {
			
			for (int i = this.cursus.length(); i < MAX_CHAR_CURSUS; i++) {
				cursusLong += " ";
			}
		} else {
			cursusLong = cursusLong.substring(0,MAX_CHAR_CURSUS);
		}
		return cursusLong;
	}
	
}
