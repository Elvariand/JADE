package fr.isika.cda27.teamJADE.model;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.TreeNodeValues.*;

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

	public boolean authenticate (String alias, String password) {
		return this.alias.equals(alias) && this.password.equals(password); 
	}
	
	
}
