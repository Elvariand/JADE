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
	 * @param internToAdd L'objet de type Intern à ajouter à la fin du fichier
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
	 * @return Retourne un objet de type Intern stocké à la position du curseur dans
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

			// On lit tous les caractères du pseudo du Membre incluant les
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
}

