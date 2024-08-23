package fr.isika.cda27.teamJADE.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.io.RandomAccessFile;

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
			this.root = insert(intern, root);
		}

	}

	public TreeNode insert(Intern intern, TreeNode treeNode) {
		// Si le noeud courant est null
		if (treeNode == null) {
			return new TreeNode(intern);
		}

		// Si la valeur du noeud à insérer est + petite que le noeud courant
		if (intern.getFamilyName().compareTo(treeNode.getFamilyName()) < 0) {
			treeNode.setLeftChild(insert(intern, treeNode.getLeftChild()));
		}

		// Si la valeur du noeud à insérer est plus grand que le noeud courant
		else if (intern.getFamilyName().compareTo(treeNode.getFamilyName()) > 0) {
			treeNode.setRightChild(insert(intern, treeNode.getRightChild()));
		}

		// Si la valeur du noeud à insérer est égale
		else {
			treeNode.getTwins().add(intern);
		}

		return treeNode;

	}

	public void removeIntern(Intern intern) {
		if (root == null) {
			System.out.println("Impossible de supprimer le stagiaire");
		} else {
			root = delete(intern, root);
		}
	}

	public TreeNode delete(Intern intern, TreeNode treeNode) {

		// Si le noeud courant est null
		if (treeNode == null) {
			System.out.println("Impossible de supprimer car le stagiaire n'a pas été trouvé");
			return treeNode;
		}

		// Si la valeur du noeud à supprimer est plus petite que le noeud courant
		if (intern.getFamilyName().compareTo(treeNode.getFamilyName()) < 0) {
			treeNode.setLeftChild(delete(intern, treeNode.getLeftChild()));
		}

		// Si la valeur du noeud à supprimer est plus grande que le noeud courant
		else if (intern.getFamilyName().compareTo(treeNode.getFamilyName()) > 0) {
			treeNode.setRightChild(delete(intern, treeNode.getRightChild()));
		}

		// Si la valeur du noeud à supprimer est égale
		else {
			// Si on a une LinkedList > 1 - Si on a plusieurs homonymes
			if (treeNode.getTwins().size() > 1) {
				treeNode.getTwins().remove(intern); // on efface dans la list
				return treeNode;
			// Sinon
			} else {
				return deleteNode(treeNode);
			}

		}
		return treeNode;

	}

	private TreeNode deleteNode(TreeNode treeNode) {
		// Si il n'y a pas d'enfants gauche
		if (treeNode.getLeftChild() == null)
            return treeNode.getRightChild();
		
		// Si il n'y a pas d'enfants droit
        if (treeNode.getRightChild() == null)
            return treeNode.getLeftChild();
        
        // Si il y a deux enfants
        TreeNode substitute = findSubstitute(treeNode.getRightChild());
        treeNode.setFamilyName(substitute.getFamilyName()); 
        treeNode.setTwins(substitute.getTwins()); 
        
        treeNode.setRightChild(delete(substitute.getTwins().get(0),treeNode.getRightChild() ));
        
        return treeNode;
	}
	

	private TreeNode findSubstitute(TreeNode minNode) {
		// On cherche la valeur minimale du sous arbre droit
		while (minNode.getLeftChild() != null) {
			minNode = minNode.getLeftChild();
		}
		return minNode;
	}

	public void sortView(TreeNode node) {

		if (node == null) {
			return;
		}

		sortView(node.getLeftChild());
		for (Intern intern : node.getTwins()) {
//			System.out.println(intern.getFamilyName());
		}
		sortView(node.getRightChild());
	}

	public void addFromDon() {

		try {
			FileReader fr = new FileReader("src/main/resources/data/TEST_STAGIAIRES.DON");
			BufferedReader br = new BufferedReader(fr); // Prends forcément un flux entrant en argument
			String text = "";
			String familyName = "";
			String firstName = "";
			String county = "";
			String cursus = "";
			String yearIn = "";
			int counter = 0;

			while (br.ready()) {
				counter++;
				text = br.readLine();
				switch (counter) {
				case 1:
					familyName = text.toUpperCase();
					break;
				case 2:
					firstName = text;
					break;
				case 3:
					county = text;
					break;
				case 4:
					cursus = text;
					break;
				case 5:
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
			fw.write(internToAdd.getFamilyName().toUpperCase() + "\n" + internToAdd.getFirstName() + "\n"
					+ internToAdd.getCounty() + "\n" + internToAdd.getCursus() + "\n" + internToAdd.getYearIn()
					+ "\n*\n");

			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addToBinary(Intern internToAdd) {
		try {
			RandomAccessFile raf = new RandomAccessFile("data/TEST_STAGIAIRES.bin", "rw");
			long pos = raf.length();
			raf.close();
			this.addToBinary(internToAdd, pos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public long addToBinary(Intern internToAdd, long cursorPosition) {

		long newCursorPosition = cursorPosition;
		try {
			RandomAccessFile raf = new RandomAccessFile("data/TEST_STAGIAIRES.bin", "rw");
			
			raf.writeChars(internToAdd.getFamilyNameLong()
					+ internToAdd.getFirstNameLong()
					+ internToAdd.getCountyLong()
					+ internToAdd.getCursus()
					+ internToAdd.getYearIn() );
			raf.writeInt(-1);
			raf.writeInt(-1);
			raf.writeInt(-1);
			
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return newCursorPosition;
	}
}
