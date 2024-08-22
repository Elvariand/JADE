package fr.isika.cda27.teamJADE.model;

//import java.util.LinkedList;

public class TreeNode {
	
public Intern familyName; 
public TreeNode rightChild; 
public TreeNode leftChild; 
//public LinkedList<Intern> twins;

public TreeNode(Intern familyName, TreeNode rightChild, TreeNode leftChild) {
	this.familyName = familyName;
	this.rightChild = rightChild;
	this.leftChild = leftChild;
//	this.twins = twins;
}

public Intern getFamilyName() {
	return familyName;
}

public void setFamilyName(Intern familyName) {
	this.familyName = familyName;
}

public TreeNode getRightChild() {
	return rightChild;
}

public void setRightChild(TreeNode rightChild) {
	this.rightChild = rightChild;
}

public TreeNode getLeftChild() {
	return leftChild;
}

public void setLeftChild(TreeNode leftChild) {
	this.leftChild = leftChild;
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
