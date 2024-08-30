package fr.isika.cda27.teamJADE.model;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.TreeNodeValues.*;

import java.util.Objects;

public class Member {

	private String alias; // 60 octets
	private String password; // 60 octets
	private String familyName; // 60 octets
	private String name; // 60 octets
	private String email; // 80 octets
	private boolean admin; // 60 octets

	/**
	 * @param alias      Le pseudonyme du Membre
	 * @param password   Le mot de passe du Membre
	 * @param familyName Le nom de famille du Membre
	 * @param name       Le prénom du Membre
	 * @param email      L'adresse mail du Membre
	 * @param admin      Qui définit si le Membre est administrateur (true) ou pas
	 *                   (false par défaut)
	 * 
	 */
	public Member(String alias, String password, String familyName, String name, String email) {
		this.alias = alias;
		this.password = password;
		this.familyName = familyName;
		this.name = name;
		this.email = email;
		this.admin = false;
	}

	/**
	 * @param alias      Le pseudonyme du Membre
	 * @param password   Le mot de passe du Membre
	 * @param familyName Le nom de famille du Membre
	 * @param name       Le prénom du Membre
	 * @param email      L'adresse mail du Membre
	 * @param admin      Qui définit si le Membre est administrateur (true) ou pas
	 *                   (false)
	 */
	public Member(String alias, String password, String familyName, String name, String email, Boolean admin) {
		this.alias = alias;
		this.password = password;
		this.familyName = familyName;
		this.name = name;
		this.email = email;
		this.admin = admin;
	}

	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the admin
	 */
	public boolean isAdmin() {
		return admin;
	}

	/**
	 * @return the admin
	 */
	public String getAdmin() {
		if (isAdmin()) {
			return "administrateur";
		} else {
			return "non administrateur";
		}
	}

	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Member [");
		if (alias != null) {
			builder.append("alias=");
			builder.append(alias);
			builder.append(", ");
		}
		if (password != null) {
			builder.append("password=");
			builder.append(password);
			builder.append(", ");
		}
		if (familyName != null) {
			builder.append("familyName=");
			builder.append(familyName);
			builder.append(", ");
		}
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (email != null) {
			builder.append("email=");
			builder.append(email);
			builder.append(", ");
		}
		builder.append("admin=");
		builder.append(admin);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(alias, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		return Objects.equals(alias, other.alias) && Objects.equals(password, other.password);
	}

	public boolean authenticate(String alias, String password) {
		return this.alias.equals(alias) && this.password.equals(password);
	}

	public String getAliasLong() {
		String aliasLong = this.alias;
		if (aliasLong.length() < MAX_CHAR_ALIAS) {

			for (int i = this.alias.length(); i < MAX_CHAR_ALIAS; i++) {
				aliasLong += " ";
			}
		} else {
			aliasLong = aliasLong.substring(0, MAX_CHAR_ALIAS);
		}
		return aliasLong;
	}

	public String getPasswordLong() {
		String passwordLong = this.password;
		if (passwordLong.length() < MAX_CHAR_PASSWORD) {

			for (int i = this.password.length(); i < MAX_CHAR_PASSWORD; i++) {
				passwordLong += " ";
			}
		} else {
			passwordLong = passwordLong.substring(0, MAX_CHAR_PASSWORD);
		}
		return passwordLong;
	}

	public String getFamilyNameLong() {
		String familyNameLong = this.familyName;
		if (familyNameLong.length() < MAX_CHAR_FAMILYNAME) {

			for (int i = this.familyName.length(); i < MAX_CHAR_FAMILYNAME; i++) {
				familyNameLong += " ";
			}
		} else {
			familyNameLong = familyNameLong.substring(0, MAX_CHAR_FAMILYNAME);
		}
		return familyNameLong;
	}
	
	public String getNameLong() {
		String nameLong = this.name;
		if (nameLong.length() < MAX_CHAR_NAME) {

			for (int i = this.name.length(); i < MAX_CHAR_NAME; i++) {
				nameLong += " ";
			}
		} else {
			nameLong = nameLong.substring(0, MAX_CHAR_NAME);
		}
		return nameLong;
	}
	public String getEmailLong() {
		String emailLong = this.email;
		if (emailLong.length() < MAX_CHAR_EMAIL) {

			for (int i = this.email.length(); i < MAX_CHAR_EMAIL; i++) {
				emailLong += " ";
			}
		} else {
			emailLong = emailLong.substring(0, MAX_CHAR_EMAIL);
		}
		return emailLong;
	}
}
