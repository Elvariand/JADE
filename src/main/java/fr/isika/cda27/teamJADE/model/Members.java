package fr.isika.cda27.teamJADE.model;

public class Members {

	private String alias; 
	private String password;
	public Members(String alias, String password) {
		this.alias = alias;
		this.password = password;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	} 
	
	public boolean authenticate (String alias, String password) {
		return this.alias.equals(alias) && this.password.equals(password); 
	}
	
	
}
