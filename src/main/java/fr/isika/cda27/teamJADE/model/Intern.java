package fr.isika.cda27.teamJADE.model;

public class Intern {

	private String familyName;
	private String firstName;
	private String county;
	private String cursus;
	private String yearIn;
	
	
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




	
	
}
