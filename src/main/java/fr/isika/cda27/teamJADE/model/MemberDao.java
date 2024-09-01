package fr.isika.cda27.teamJADE.model;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.TreeNodeValues.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

import fr.isika.cda27.teamJADE.view.App;

public class MemberDao extends TreeNodeDao<Member>{

	/**
	 * @param memberToAdd Le premier objet de type Member à ajouter 
	 */
	public void addFirstMember() {

		insert(new Member("admin", "pass", "test", "test", "test@isika.fr", true));
	}
	
	/**
	 * @param memberToAdd L'objet type Member à ajouter dans le fichier binaire
	 * @param raf Le RandomAccessFile 
	 */
	@Override
	protected void writeSpecificFields(Member memberToAdd, RandomAccessFile raf) {
		try {
			raf.writeChars(memberToAdd.getAliasLong());
			raf.writeChars(memberToAdd.getPasswordLong());
			raf.writeChars(memberToAdd.getFamilyNameLong());
			raf.writeChars(memberToAdd.getNameLong());
			raf.writeChars(memberToAdd.getEmailLong());
			raf.writeBoolean(memberToAdd.isAdmin());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * @param cursorPosition La position du curseur en octet. Doit être au niveau du
	 *                       début du noeud.
	 * @return Retourne un objet de type Member stocké à la position du curseur dans
	 *         le fichier binaire.
	 */
	protected Member readObjectFromBinary(long cursorPosition) {
		String alias = "";
		String password = "";
		String familyName = ""; 
		String name = ""; 
		String email = ""; 
		Boolean admin = false;

		try {
			RandomAccessFile raf = new RandomAccessFile(App.getMemberBinFile(), "rw");

			// On met le curseur à la position demandée
			raf.seek(cursorPosition);

			// On lit tous les caractères du pseudonyme du Membre incluant les
			// espaces supplémentaires
			for (int i = 0; i < MAX_CHAR_ALIAS; i++) {
				alias += raf.readChar();
			}
			// On lit tous les caractères du mot de passe du Membre incluant les espaces
			// supplémentaires
			for (int i = 0; i < MAX_CHAR_PASSWORD; i++) {
				password += raf.readChar();
			}
			// On lit tous les caractères du nom de famille du Membre incluant les espaces
			// supplémentaires
			for (int i = 0; i < MAX_CHAR_FAMILYNAME; i++) {
				familyName += raf.readChar();
			}
			// On lit tous les caractères du prénom du Membre incluant les espaces
						// supplémentaires
			for (int i = 0; i < MAX_CHAR_NAME; i++) {
				name += raf.readChar();
			}
			// On lit tous les caractères de l'adresse mail du Membre incluant les espaces
						// supplémentaires
			for (int i = 0; i < MAX_CHAR_EMAIL; i++) {
				email += raf.readChar();
			}
			
			// On lit tous les caractères d'Admin du Membre incluant les espaces
			// supplémentaires
			admin = raf.readBoolean();
		

			// On coupe le flux d'échanges de données entre l'application et le fichier afin
			// de libérer de la mémoire pour l'ordinateur.
			raf.close();
		} catch (IOException e) {

			// S'il y a une erreur, on affiche dans la console d'où elle vient afin de
			// pouvoir la régler
			e.printStackTrace();
		}
		return new Member(alias.trim(), password.trim(), familyName.trim(), name.trim(), email.trim() ,admin);
	}

	/**
	 * Lit le fichier binaire et l'affiche dans la console
	 */
	public void readBinary() {
		try {
			RandomAccessFile raf = new RandomAccessFile(App.getMemberBinFile(), "rw");
			for (long cursor = 0; cursor < this.getBinarySize(); cursor++) {
				Member member = this.readObjectFromBinary(cursor);
				int indexLeft = this.readLeftChildFromBinary(cursor);
				int indexRight = this.readRightChildFromBinary(cursor);
				int indexTwin = this.readTwinFromBinary(cursor);
				System.out.println(
						member.getAlias() + "\t" + member.getPassword()
								+ "\t" + member.getFamilyName() + "\t" + member.getName() + "\t" + member.getEmail() + "\t" + member.isAdmin() + "\t" + indexLeft + "\t" + indexRight + "\t" + indexTwin);
				cursor += MEMBER_NODE_SIZE - 1;
			}
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected String readKeyFromBinary(long cursorPosition) {
		String alias = "";
		
		try {
			RandomAccessFile raf = new RandomAccessFile(App.getMemberBinFile(), "rw");
			
			// On met le curseur à la position demandée
			raf.seek(cursorPosition);
			
			// On lit tous les caractères du pseudo incluant les
			// espaces supplémentaires
			for (int i = 0; i < MAX_CHAR_ALIAS; i++) {
				alias += raf.readChar();
			}
			
			// On coupe le flux d'échanges de données entre l'application et le fichier afin
			// de libérer de la mémoire pour l'ordinateur.
			raf.close();
		} catch (IOException e) {
			
			// S'il y a une erreur, on affiche dans la console d'où elle vient afin de
			// pouvoir la régler
			e.printStackTrace();
		}
		return alias.trim();
		
	}

	@Override
	protected int getNodeSize() {
		return MEMBER_NODE_SIZE;
	}

	@Override
	protected String getKey(Member member) {
		return member.getAlias();
	}


	@Override
	protected int getObjectSize() {
		return MEMBER_SIZE;
	}


	@Override
	protected String getBinFile() {
		return App.getMemberBinFile();
	}
	
	
	
	// Trouver le Pseudo
	public Member findByAlias(String alias, String password) {
		Member member = findByAlias(alias, password, 0);
		return member;
	}
	
	public Member findByAlias(String alias, String password, long cursorPosition) {

		long binarySize = this.getBinarySize();

		// Si le fichier binaire est vide
		if (binarySize == 0) {
			// Alors on return
			System.out.println("Fichier binaire vide");
			return null;
		}
		String nodeKey = this.readKeyFromBinary(cursorPosition);

		// Si la valeur du Pseudo est plus petite que
		// celle du Pseudo courant dans le fichier binaire.
		if (alias.compareTo(nodeKey) < 0) {

			long newCursorPosition = readLeftChildFromBinary(cursorPosition);

			// Si il n'y a pas de fils gauche, c'est donc que l'information sur le fichier
			// binaire vaut -1
			if (newCursorPosition < 0) {

				/*
				 * Dans ce cas ça veut dire que le Pseudo entré n'existe pas dans le fichier
				 */
				System.out.println("Aucune correspondance trouvée 1");
				return null;

				// Si le fils gauche existe déjà, alors nous déplaçons notre curseur à la
				// position correspondante dans le fichier binaire
				// et nous relançons une recherche.
			} else {
				return this.findByAlias(alias, password, newCursorPosition * getNodeSize());
				
			}
		}

		// Si la valeur du Pseudo est plus grande que
		// celle du Pseudo courant lu dans le fichier binaire.
		else if (alias.compareTo(nodeKey) > 0) {

			long newCursorPosition = readRightChildFromBinary(cursorPosition);

			// Si il n'y a pas de fils droit, c'est donc que l'information sur le fichier
			// binaire vaut -1
			if (newCursorPosition < 0) {

				/*
				 * Dans ce cas ça veut dire que le Pseudo entré n'existe pas dans le fichier
				 */
				System.out.println("Aucune correspondance trouvée 2");
				return null;

				// Si le fils droit existe déjà, alors nous déplaçons notre curseur à la
				// position correspondante dans le fichier binaire
				// et nous relançons une recherche.
			} else {
				return this.findByAlias(alias, password, newCursorPosition * getNodeSize());
				
			}
		}

		// Si la valeur du Pseudo entrée est égale à celle du Pseudo
		// courant lu dans le fichier binaire, alors on regarde si il y a des doublons
		else {
			int read = 1;

			/*
			 * Pour le fichier binaire nous cherchons maintenant le mdp
			 */
			while (read > 0) {
				Member member = this.readObjectFromBinary(cursorPosition);
				// Si le mdp est égal au mdp du membre courant
				if (password.equals(this.readObjectFromBinary(cursorPosition).getPassword())) {
					System.out.println("Le mdp à été trouvé 1 !");
					return member;
				}
				read = readTwinFromBinary(cursorPosition);
				if (read > 0)
					cursorPosition = read * getNodeSize();
			}
		}
			System.out.println("Aucune correspondance trouvée 3 !");
			return null;

	}
}

