package fr.isika.cda27.teamJADE.model;

import java.util.LinkedList;
import java.util.Objects;

public class TreeNode {

	private String familyName;
	private TreeNode rightChild;
	private TreeNode leftChild;
	private LinkedList<Intern> twins;
	private static final int SIZE_INDEX = 4;
	private static final int SIZE_NODE = Intern.getSizeIntern() + TreeNode.getSizeIndex() + TreeNode.getSizeIndex() + TreeNode.getSizeIndex();

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
	 * @return the sizeIndex
	 */
	public static int getSizeIndex() {
		return SIZE_INDEX;
	}



	/**
	 * @return the sizeNode
	 */
	public static int getSizeNode() {
		return SIZE_NODE;
	}



	@Override
	public String toString() {
		return "TreeNode [familyName=" + familyName + ", rightChild=" + rightChild + ", leftChild=" + leftChild
				+ ", twins=" + twins + "]";
	}





	

}
