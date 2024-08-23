package fr.isika.cda27.teamJADE.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TreeNodeDao {

	private TreeNode root;

	
	
	public TreeNodeDao() {
		this.root = null;
	}

	/**
	 * @param root
	 */
	public TreeNodeDao(TreeNode root) {
		this.root = root;
	}

	
	
	
	/**
	 * @return
	 */
	public TreeNode getRoot() {
		return root;
	}

	/**
	 * @param root
	 */
	public void setRoot(TreeNode root) {
		this.root = root;
	}

	/**
	 * @param intern
	 */
	public void addIntern(Intern intern) {
		if (root == null) {
			this.root = new TreeNode(intern);
		} else {

			this.root.insert(intern);
		}

	}

	public void removeIntern(Intern intern) {
		if (root == null) {
			System.out.println("Impossible de supprimer le stagiaire");
		}else {
			this.root= this.root.delete(intern); 
		}
	}
	
	public void sortView(TreeNode node) {

		if (node == null) {
			return;
		}

		sortView(node.getLeftChild());
		for (Intern intern : node.getTwins()) {
			System.out.println(intern.getFamilyName());
		}
		sortView(node.getRightChild());
	}
	

	public void addFromDon() {
   
		try {
			FileReader fr = new FileReader("src/main/resources/data/STAGIAIRES.DON");
			BufferedReader br = new BufferedReader(fr); // Prends forcément un flux entrant en argument
			String text = "";
			String familyName = "";
			String firstName = "";
			String county = "";
			String cursus = "";
			String yearIn = "";
			int counter = 0;

			while(br.ready()) {
				counter++;
				text = br.readLine();
				switch(counter) {
					case 1 :
						familyName = text.toUpperCase();
						break;
					case 2 :
						firstName = text;
						break;
					case 3 :
						county = text;
						break;
					case 4 :
						cursus = text;
						break;
					case 5 :
						yearIn = text;
						break;
					default:
						this.addIntern(new Intern(familyName, firstName, county, cursus, yearIn));
						counter = 0;
						break;
				}
			}

			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void addToDon(Intern internToAdd) {
		FileWriter fw;
		
		try {
			fw = new FileWriter("/data/STAGIAIRES.DON", true);
			fw.write(internToAdd.getFamilyName().toUpperCase() + "\n"
			+ internToAdd.getFirstName() + "\n"
			 + internToAdd.getCounty() + "\n"
			 + internToAdd.getCursus() + "\n"
			 + internToAdd.getYearIn() + "\n*\n");
			
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
