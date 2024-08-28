package fr.isika.cda27.teamJADE.model;

import java.util.Objects;

public class Intern {

	private String familyName; // 62 octets
	private String firstName; // 62
	private int county; // 4 octets
	private String cursus; // 20
	private int yearIn; // 4 octets
	private static final int OCTETS_TOOK_BY_CHAR = 2;
	private static final int MAX_CHAR_NAMES = 31;
	private static final int MAX_CHAR_COUNTY = 3;
	private static final int MAX_CHAR_CURSUS = 10;
	private static final int MAX_CHAR_YEARIN = 4;
	private static final int SIZE_INDEX = 4;
	private static final int SIZE_INTERN = (MAX_CHAR_NAMES + MAX_CHAR_NAMES + MAX_CHAR_COUNTY + MAX_CHAR_CURSUS + MAX_CHAR_YEARIN) * OCTETS_TOOK_BY_CHAR;
	private static final int SIZE_NODE = SIZE_INTERN + SIZE_INDEX + SIZE_INDEX + SIZE_INDEX;
	
	
	/**
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
	 * @return the familyName
	 */
	public String getFamilyName() {
		return familyName;
	}

	/**
	 * @param familyName the familyName to set
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the county
	 */
	public int getCounty() {
		return county;
	}

	/**
	 * @return the county in String
	 */
	public String getCountyString() {
		return Integer.toString(county);
	}
	
	/**
	 * @param county the county to set
	 */
	public void setCounty(int county) {
		this.county = county;
	}

	/**
	 * @return the cursus
	 */
	public String getCursus() {
		return cursus;
	}

	/**
	 * @param cursus the cursus to set
	 */
	public void setCursus(String cursus) {
		this.cursus = cursus;
	}

	/**
	 * @return the yearIn
	 */
	public int getYearIn() {
		return yearIn;
	}

	/**
	 * @return the yearIn in String
	 */
	public String getYearInString() {
		return Integer.toString(yearIn);
	}
	
	
	/**
	 * @param yearIn the yearIn to set
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

	
	@Override
	public int hashCode() {
		return Objects.hash(county, cursus, familyName, firstName, yearIn);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Intern other = (Intern) obj;
		return Objects.equals(county, other.county) && Objects.equals(cursus, other.cursus)
				&& Objects.equals(familyName, other.familyName) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(yearIn, other.yearIn);
	}



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
	
	
//	public String getCountyLong() {
//		String countyLong = this.county;
//		if (countyLong.length() < MAX_CHAR_COUNTY) {
//			
//			for (int i = this.county.length(); i < MAX_CHAR_COUNTY; i++) {
//				countyLong += " ";
//			}
//		} else {
//			countyLong = countyLong.substring(0,MAX_CHAR_COUNTY);
//		}
//		return countyLong;
//	}
	
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
