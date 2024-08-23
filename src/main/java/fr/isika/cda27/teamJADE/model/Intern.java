package fr.isika.cda27.teamJADE.model;

public class Intern {

	private String familyName; // 62 octets
	private String firstName; // 62
	private String county; // 6 octets
	private String cursus; // 20
	private String yearIn; // 8 octets
	private static final int TAILLE_NAMES = 31;
	private static final int TAILLE_COUNTY = 3;
	// + 12 octets
	
	
	/**
	 * @param familyName Le nom de famille du Stagiaire
	 * @param firstName Le prénom du Stagiaire
	 * @param county Le numéro de département du Stagiaire
	 * @param cursus Le nom de la formation du Stagiaire
	 * @param yearIn L'année d'entrée en formation
	 */
	public Intern(String familyName, String firstName, String county, String cursus, String yearIn) {
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
	public String getCounty() {
		return county;
	}

	/**
	 * @param county the county to set
	 */
	public void setCounty(String county) {
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
	public String getYearIn() {
		return yearIn;
	}

	/**
	 * @param yearIn the yearIn to set
	 */
	public void setYearIn(String yearIn) {
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
		if (county != null) {
			builder.append("county=");
			builder.append(county);
			builder.append(", ");
		}
		if (cursus != null) {
			builder.append("cursus=");
			builder.append(cursus);
			builder.append(", ");
		}
		if (yearIn != null) {
			builder.append("yearIn=");
			builder.append(yearIn);
		}
		builder.append("]");
		return builder.toString();
	}

	
	public String getFamilyNameLong() {
		String familyNameLong = this.familyName;
		if (familyNameLong.length() < TAILLE_NAMES) {

			for (int i = this.familyName.length(); i < TAILLE_NAMES; i++) {
				familyNameLong += " ";
			}
		} else {
			familyNameLong = familyNameLong.substring(0,TAILLE_NAMES);
		}
		return familyNameLong;
	}
	
	public String getFirstNameLong() {
		String firstNameLong = this.firstName;
		if (firstNameLong.length() < TAILLE_NAMES) {
			
			for (int i = this.firstName.length(); i < TAILLE_NAMES; i++) {
				firstNameLong += " ";
			}
		} else {
			firstNameLong = firstNameLong.substring(0,TAILLE_NAMES);
		}
		return firstNameLong;
	}
	
	
	public String getCountyLong() {
		String countyLong = this.county;
		if (countyLong.length() < TAILLE_COUNTY) {
			
			for (int i = this.county.length(); i < TAILLE_COUNTY; i++) {
				countyLong += " ";
			}
		} else {
			countyLong = countyLong.substring(0,TAILLE_COUNTY);
		}
		return countyLong;
	}
	
}
