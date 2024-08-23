package fr.isika.cda27.teamJADE.model;

import java.util.LinkedList;

public class TreeNode {

	private String familyName;
	private TreeNode rightChild;
	private TreeNode leftChild;
	private LinkedList<Intern> twins;
	private static final int SIZE_NODE = Intern.getSizeIntern()+4 +4 +4;

	/**
	 * @param intern Le Stagiaire dont l'info sera dans ce noeud
	 */
	public TreeNode(Intern intern) {
		this.familyName = intern.getFamilyName();
		this.rightChild = null;
		this.leftChild = null;
		this.twins = new LinkedList<Intern>();
		this.twins.add(intern);
	}



	/**
	 * @return the familyName
	 */
	public String getFamilyName() {
		return familyName;
	}



	/**
	 * @param familyName the familyName to set
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}



	/**
	 * @return the rightChild
	 */
	public TreeNode getRightChild() {
		return rightChild;
	}



	/**
	 * @param rightChild the rightChild to set
	 */
	public void setRightChild(TreeNode rightChild) {
		this.rightChild = rightChild;
	}



	/**
	 * @return the leftChild
	 */
	public TreeNode getLeftChild() {
		return leftChild;
	}



	/**
	 * @param leftChild the leftChild to set
	 */
	public void setLeftChild(TreeNode leftChild) {
		this.leftChild = leftChild;
	}



	/**
	 * @return the twins
	 */
	public LinkedList<Intern> getTwins() {
		return twins;
	}



	/**
	 * @param twins the twins to set
	 */
	public void setTwins(LinkedList<Intern> twins) {
		this.twins = twins;
	}



	/**
	 * @return the sizeNode
	 */
	public static int getSizeNode() {
		return SIZE_NODE;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TreeNode [");
		if (familyName != null) {
			builder.append("familyName=");
			builder.append(familyName);
			builder.append(", ");
		}
		if (rightChild != null) {
			builder.append("rightChild=");
			builder.append(rightChild);
			builder.append(", ");
		}
		if (leftChild != null) {
			builder.append("leftChild=");
			builder.append(leftChild);
		}
		builder.append("]");
		return builder.toString();
	}

	

}
