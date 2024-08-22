package fr.isika.cda27.teamJADE.model;

import java.util.LinkedList;

public class TreeNode {

	public String familyName;
	public TreeNode rightChild;
	public TreeNode leftChild;
	public LinkedList<Intern> twins;

	public TreeNode(Intern intern) {
		this.familyName = intern.getFamilyName();
		this.rightChild = null;
		this.leftChild = null;
		this.twins = new LinkedList<Intern>();
		this.twins.add(intern);
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
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

	public void insert(Intern intern) {
		// Si la valeur du noeud à insérer est + petite que le noeud courant
		if (intern.getFamilyName().compareTo(familyName) < 0) {
			// Si le noeud est une feuille
			if (this.leftChild == null) {
				this.leftChild = new TreeNode(intern);

				// Si le noeud a un enfant gauche
			} else {
				this.leftChild.insert(intern);
			}
		}

		// Si la valeur du noeud à insérer est plus grand que le noeud courant

		else if (intern.getFamilyName().compareTo(familyName) > 0)
			// si le noeud a une feuille
			if (this.rightChild == null) {
				this.rightChild = new TreeNode(intern);
			} else {
				this.rightChild.insert(intern);
			}

		// Si la valeur du noeud à insérer est égale
		else {
			this.twins.add(intern);
		}

	}

	public TreeNode delete(Intern intern) {
		// Si la valeur du noeud à supprimer est + petite que le noeud courant

		if (intern.getFamilyName().compareTo(familyName) < 0) {
			// Si le noeud est une feuille
			if (this.leftChild != null) {
				this.leftChild = this.leftChild.delete(intern);

				// Si le noeud a un enfant gauche
			} else {
				System.out.println("Impossible de supprimer car l'interne n'a pas été trouvé");

			}
		}

		// Si la valeur du noeud à supprimer est plus grand que le noeud courant
		else if (intern.getFamilyName().compareTo(familyName) > 0) {
			if (this.rightChild != null) {
				this.rightChild = this.rightChild.delete(intern);
			} else {
				System.out.println("Impossible de supprimer car l'interne n'a pas été trouvé");
			}

		}

		// Si la valeur du noeud à supprimer est égale
		else {
			// Si on a une LinkedList > 1 - Si on a plusieurs homonymes
			if (this.twins.size() > 1) {
				this.twins.remove(intern);
				return this;
			}

			// Si tu n'as pas d'enfants

			if (this.rightChild == null && this.leftChild == null) {
				return null;
			}

			// Si il y a un seul enfant
			if (this.leftChild != null) {
				return this.leftChild;
			} else if (this.rightChild != null) {
				return this.rightChild;
			}

		}

		// S'il y a deux enfants
		
		
	
	return this; 
	}

}
