package fr.isika.cda27.teamJADE.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class InternDao {

	private ArrayList<Intern> listInterns;

	
	
	public InternDao() {
		super();
		this.listInterns = new ArrayList<Intern>();
	}

	
	
	/**
	 * @return the listInterns
	 */
	public ArrayList<Intern> getListInterns() {
		return listInterns;
	}

	/**
	 * @param listInterns the listInterns to set
	 */
	public void setListInterns(ArrayList<Intern> listInterns) {
		this.listInterns = listInterns;
	}

	/**
	 * @param intern le Stagiaire à ajouter
	 */
	public void addIntern(Intern intern) {
		listInterns.add(intern);
	}

	/**
	 * @param intern le Stagiaire à supprimer
	 */
	public void deleteIntern(Intern intern) {
		System.out.println(this.listInterns.remove(intern) ? "Stagiaire supprimé de la liste"
				: "Stagiaire non trouvé dans la liste");
	}

	
	/**
	 * @param intern Le Stagiaire à modifier
	 * @param familyName le nouveau nom du Stagiaire, laisser null si inchangé.
	 * @param firstName le nouveau prénom du Stagiaire, laisser null si inchangé.
	 * @param county le nouveau département du Stagiaire, laisser null si inchangé.
	 * @param cursus la nouvelle formation du Stagiaire, laisser null si inchangée.
	 * @param yearIn la nouvelle année d'entrée du Stagiaire, laisser null si inchangée.
	 */
	public void updateIntern(Intern intern, String familyName, String firstName, String county, String cursus,
			String yearIn) {
		if (familyName != null) {
			intern.setFamilyName(familyName);
		}
		if (firstName != null) {
			intern.setFirstName(firstName);
		}
		if (county != null) {
			intern.setCounty(county);
		}
		if (cursus != null) {
			intern.setCursus(cursus);
		}
		if (yearIn != null) {
			intern.setYearIn(yearIn);
		}
	}
	
}
