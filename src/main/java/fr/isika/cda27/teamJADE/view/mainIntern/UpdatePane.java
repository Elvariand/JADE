package fr.isika.cda27.teamJADE.view.mainIntern;

import javafx.geometry.Insets;

public class UpdatePane extends RepetitivePane {

	public UpdatePane() {
		super();

		this.titleLabel.setText("Modification du stagiaire");
		this.titleLabel.setPadding(new Insets(20,0,0,0));
		
		this.leftButton.setText("Annuler");

		this.rightButton.setText("Modifier");

	}

}
