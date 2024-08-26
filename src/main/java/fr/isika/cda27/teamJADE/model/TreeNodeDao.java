package fr.isika.cda27.teamJADE.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
			this.writeInBinary(intern, this.getBinarySize());
		} else {
			this.root = insert(intern, root);
		}

	}

	public TreeNode insert(Intern intern, TreeNode treeNode) {
		return insert(intern, treeNode, 0);
	}

	public TreeNode insert(Intern intern, TreeNode treeNode, long cursorPosition) {

		// Si le noeud courant est null
		if (treeNode == null) {
			this.writeInBinary(intern, cursorPosition);
			return new TreeNode(intern);
		}

		// Si la valeur du noeud à insérer est + petite que le noeud courant
		if (intern.getFamilyName().compareTo(treeNode.getFamilyName()) < 0) {
			long newCursorPosition = readLeftChildFromBinary(cursorPosition) * TreeNode.getSizeNode();
			if (newCursorPosition < 0) {
				this.writeIntInBinary((int) (this.getBinarySize() / (TreeNode.getSizeNode())),
						cursorPosition + Intern.getSizeIntern());
				treeNode.setLeftChild(insert(intern, treeNode.getLeftChild(), this.getBinarySize()));
			} else {
				treeNode.setLeftChild(insert(intern, treeNode.getLeftChild(), newCursorPosition));
			}
		}

		// Si la valeur du noeud à insérer est plus grand que le noeud courant
		else if (intern.getFamilyName().compareTo(treeNode.getFamilyName()) > 0) {
			long newCursorPosition = readRightChildFromBinary(cursorPosition) * TreeNode.getSizeNode();
			if (newCursorPosition < 0) {
				this.writeIntInBinary((int) (this.getBinarySize() / (TreeNode.getSizeNode())),
						cursorPosition + Intern.getSizeIntern() + 4);
				treeNode.setRightChild(insert(intern, treeNode.getRightChild(), this.getBinarySize()));
			} else {
				treeNode.setRightChild(insert(intern, treeNode.getRightChild(), newCursorPosition));
			}
		}

		// Si la valeur du noeud à insérer est égale
		else {
			treeNode.getTwins().add(intern);
			int read = 1;
			int counter = 0;
			while (read > 0 && counter < 20) {
				counter++;
				read = readTwinFromBinary(cursorPosition);
				if (read > 0)
					cursorPosition = read * TreeNode.getSizeNode();
			}
			int calc = (int) (this.getBinarySize() / TreeNode.getSizeNode());
			double calcDouble = (double) this.getBinarySize() / (double) TreeNode.getSizeNode();
			this.writeIntInBinary(calc, cursorPosition + Intern.getSizeIntern() + 4 + 4);
			this.writeInBinary(intern, this.getBinarySize());
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

		treeNode.setRightChild(delete(substitute.getTwins().get(0), treeNode.getRightChild()));

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
			System.out.println(intern.getFamilyName());
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
			fw = new FileWriter("src/main/resources/data/TEST_STAGIAIRES.DON", true);
			fw.write(internToAdd.getFamilyName().toUpperCase() + "\n" + internToAdd.getFirstName() + "\n"
					+ internToAdd.getCounty() + "\n" + internToAdd.getCursus() + "\n" + internToAdd.getYearIn()
					+ "\n*\n");

			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public long getBinarySize() {
		long binSize = 0;
		try {
			RandomAccessFile raf = new RandomAccessFile("src/main/resources/data/TEST_STAGIAIRES.bin", "rw");

			binSize = raf.length();

			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return binSize;
	}

	public void writeInBinary(Intern internToAdd, long cursorPosition) {
		writeInBinary(internToAdd, cursorPosition, -1, -1, -1);
	}

	public void writeInBinary(Intern internToAdd, long cursorPosition, int leftChildInt, int rightChildInt,
			int twinInt) {
		try {
			RandomAccessFile raf = new RandomAccessFile("src/main/resources/data/TEST_STAGIAIRES.bin", "rw");

			raf.seek(cursorPosition);
			raf.writeChars(internToAdd.getFamilyNameLong());
			raf.writeChars(internToAdd.getFirstNameLong());
			raf.writeChars(internToAdd.getCountyLong());
			raf.writeChars(internToAdd.getCursusLong());
			raf.writeChars(internToAdd.getYearIn());
			raf.writeInt(leftChildInt);
			raf.writeInt(rightChildInt);
			raf.writeInt(twinInt);

			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeIntInBinary(int twinOrChild, long cursorPosition) {
		try {
			RandomAccessFile raf = new RandomAccessFile("src/main/resources/data/TEST_STAGIAIRES.bin", "rw");

			raf.seek(cursorPosition);
			raf.writeInt(twinOrChild);
			
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Intern readInternFromBinary(long cursorPosition) {
		String familyName = "";
		String firstName = "";
		String county = "";
		String cursus = "";
		String yearIn = "";

		try {
			RandomAccessFile raf = new RandomAccessFile("src/main/resources/data/TEST_STAGIAIRES.bin", "rw");
			raf.seek(cursorPosition);
			for (int i = 0; i < Intern.getMaxCharNames(); i++) {
				familyName += raf.readChar();
			}
			for (int i = 0; i < Intern.getMaxCharNames(); i++) {
				firstName += raf.readChar();
			}
			for (int i = 0; i < Intern.getMaxCharCounty(); i++) {
				county += raf.readChar();
			}
			for (int i = 0; i < Intern.getMaxCharCursus(); i++) {
				cursus += raf.readChar();
			}
			for (int i = 0; i < Intern.getMaxCharYearIn(); i++) {
				yearIn += raf.readChar();
			}
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Intern(familyName.trim(), firstName.trim(), county.trim(), cursus.trim(), yearIn.trim());
	}

	public int readLeftChildFromBinary(long cursorPosition) {
		cursorPosition += Intern.getSizeIntern();
		int leftChildInt = -1;
		try {
			RandomAccessFile raf = new RandomAccessFile("src/main/resources/data/TEST_STAGIAIRES.bin", "rw");
			raf.seek(cursorPosition);
			leftChildInt = raf.readInt();
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return leftChildInt;
	}

	public int readRightChildFromBinary(long cursorPosition) {
		cursorPosition += Intern.getSizeIntern() + 4;
		int rightChildInt = -1;
		try {
			RandomAccessFile raf = new RandomAccessFile("src/main/resources/data/TEST_STAGIAIRES.bin", "rw");
			raf.seek(cursorPosition);
			rightChildInt = raf.readInt();
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rightChildInt;
	}

	public int readTwinFromBinary(long cursorPosition) {
		cursorPosition += Intern.getSizeIntern() + 4 + 4;
		int TwinInt = -1;
		try {
			RandomAccessFile raf = new RandomAccessFile("src/main/resources/data/TEST_STAGIAIRES.bin", "rw");
			raf.seek(cursorPosition);
			TwinInt = raf.readInt();
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return TwinInt;
	}

	public void deleteBinary() {
		File bin = new File("src/main/resources/data/TEST_STAGIAIRES.bin");
		bin.delete();
	}
}
