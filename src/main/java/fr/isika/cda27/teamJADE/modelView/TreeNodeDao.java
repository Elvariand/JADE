package fr.isika.cda27.teamJADE.modelView;

import fr.isika.cda27.teamJADE.model.TreeNode;

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
	
	

}
