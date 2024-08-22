package fr.isika.cda27.teamJADE.model;

public class TreeNodeDao {

	public TreeNode root;

	public TreeNodeDao() {
		this.root = null;
	}

	public TreeNodeDao(TreeNode root) {
		this.root = root;
	}

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

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
		for (Intern intern : node.twins) {
			System.out.println(intern);

		}
		sortView(node.getRightChild());
	}
}
