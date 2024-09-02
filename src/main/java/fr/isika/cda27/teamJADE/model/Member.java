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
	 * Crée l'objet Membre utilisateur
	 * 
	 * @param alias      Le nom d'utilisateur du Membre
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
	 * Crée l'objet Membre administrateur
	 * 
	 * @param alias      Le nom d'utilisateur du Membre
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
	 * @return Le nom d'utilisateur
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @param alias Le nom d'utilisateur à définir
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * @return Le mot de passe
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password Le mot de passe à définir
	 */
	public void setPassword(String password) {
		this.password = password;
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
	public String getName() {
		return name;
	}

	/**
	 * @param name Le prénom à définir
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return L'adresse mail
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email L'adresse mail à définir
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Vérifie si l'utilisateur est un administrateur.
	 * 
	 * @return {@code true} si l'utilisateur est un administrateur, {@code false}
	 *         sinon.
	 */
	public boolean isAdmin() {
		return admin;
	}

	/**
	 * Retourne une chaîne de caractères représentant le statut d'administrateur de
	 * l'utilisateur.
	 * 
	 * @return Une chaîne de caractères indiquant le statut d'administrateur de
	 *         l'utilisateur.
	 */
	public String getAdmin() {
		if (this.isAdmin()) {
			return "administrateur";
		} else {
			return "non administrateur";
		}
	}

	/**
	 * @param admin L'administrateur à définir
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	/**
	 * Retourne une représentation sous forme de chaîne de caractères de l'objet
	 * {@code Member}.
	 * 
	 * @return Une chaîne de caractères représentant l'état de l'objet
	 *         {@code Member}.
	 */
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

	/**
	 * Retourne le code de hachage de l'objet {@code Member}.
	 * 
	 * Le code de hachage est calculé en fonction des attributs {@code alias} et
	 * {@code password}. Cette méthode est utilisée pour l'implémentation correcte
	 * des collections basées sur les hachages, telles que {@code HashSet} et
	 * {@code HashMap}.
	 * 
	 * @return Le code de hachage de l'objet {@code Member}.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(alias, password);
	}

	/**
	 * Détermine si l'objet spécifié est égal à l'objet actuel.
	 * 
	 * @param obj L'objet à comparer avec l'objet actuel.
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
		Member other = (Member) obj;
		return Objects.equals(alias, other.alias) && Objects.equals(password, other.password);
	}

	/**
	 * Authentifie un utilisateur en vérifiant si l'alias et le mot de passe fournis
	 * correspondent aux valeurs stockées dans l'objet.
	 * 
	 * @param alias    Le nom d'utilisateur à vérifier. Ne doit pas être
	 *                 {@code null}.
	 * @param password Le mot de passe à vérifier. Ne doit pas être {@code null}.
	 * @return {@code true} si l'alias et le mot de passe correspondent aux valeurs
	 *         stockées, sinon {@code false}.
	 */
	public boolean authenticate(String alias, String password) {
		return this.alias.equals(alias) && this.password.equals(password);
	}

	/**
	 * Obtient le nom d'utilisateur avec une longueur fixe en ajoutant des espaces
	 * ou en le tronquant.
	 * 
	 * @return Le nom d'utilisateur ajusté avec une longueur fixe. Le nom
	 *         d'utilisateur est soit étendu avec des espaces, soit tronqué pour
	 *         correspondre à {@code MAX_CHAR_ALIAS}.
	 */
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

	/**
	 * Obtient le mot de passe avec une longueur fixe en ajoutant des espaces ou en
	 * le tronquant.
	 * 
	 * @return Le mot de passe ajusté avec une longueur fixe. Le mot de passe est
	 *         soit étendu avec des espaces, soit tronqué pour correspondre à
	 *         {@code MAX_CHAR_PASSWORD}.
	 */
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

	/**
	 * Obtient le nom de famille du membre avec une longueur fixe en ajoutant des
	 * espaces ou en le tronquant.
	 * 
	 * @return Le nom de famille du membre ajusté avec une longueur fixe. Le nom de
	 *         famille du membre est soit étendu avec des espaces, soit tronqué pour
	 *         correspondre à {@code MAX_CHAR_FAMILYNAME}.
	 */
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

	/**
	 * Obtient le prénom du membre avec une longueur fixe en ajoutant des espaces ou
	 * en le tronquant.
	 * 
	 * @return Le prénom du membre ajusté avec une longueur fixe. Le prénom du
	 *         membre est soit étendu avec des espaces, soit tronqué pour
	 *         correspondre à {@code MAX_CHAR_NAME}.
	 */
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

	/**
	 * Obtient l'adresse mail du membre avec une longueur fixe en ajoutant des
	 * espaces ou en le tronquant.
	 * 
	 * @return L'adresse mail du membre ajusté avec une longueur fixe. L'adresse
	 *         mail du membre est soit étendu avec des espaces, soit tronqué pour
	 *         correspondre à {@code MAX_CHAR_EMAIL}.
	 */
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
