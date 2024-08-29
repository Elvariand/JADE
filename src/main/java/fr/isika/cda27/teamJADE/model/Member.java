package fr.isika.cda27.teamJADE.model;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.TreeNodeValues.*;

import java.util.Objects;

public class Member {

	private String alias;  // 60 octets
	private String password; // 60 octets
	private boolean admin; // 60 octets
	
	/**
	 * @param alias Le pseudonyme du Membre
	 * @param password Le mot de passe du Membre
	 * @param admin Qui définit si le Membre est administrateur (true) ou pas (false par défault)
	 */	
	public Member(String alias, String password) {
		this.alias = alias;
		this.password = password;
		this.admin = false;
	}
	
	/**
	 * @param alias Le pseudonyme du Membre
	 * @param password Le mot de passe du Membre
	 * @param admin Qui définit si le Membre est administrateur (true) ou pas (false)
	 */	
	public Member(String alias, String password, Boolean admin) {
		this.alias = alias;
		this.password = password;
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
			return "true";
		}else {
			return "false";
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
		builder.append("admin=");
		builder.append(admin);
		builder.append(", ");
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

	
	public boolean authenticate (String alias, String password) {
		return this.alias.equals(alias) && this.password.equals(password); 
	}
	
	public String getAliasLong() {
		String aliasLong = this.alias;
		if (aliasLong.length() < MAX_CHAR_ALIAS) {

			for (int i = this.alias.length(); i < MAX_CHAR_ALIAS; i++) {
				aliasLong += " ";
			}
		} else {
			aliasLong = aliasLong.substring(0,MAX_CHAR_ALIAS);
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
			passwordLong = passwordLong.substring(0,MAX_CHAR_PASSWORD);
		}
		return passwordLong;
	}

	
	
	
}
