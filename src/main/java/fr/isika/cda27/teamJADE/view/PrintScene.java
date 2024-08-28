package fr.isika.cda27.teamJADE.view;

public class PrintScene extends RepetitiveScene {

	public PrintScene() {
		super();

		this.titleLabel.setText("Souhaitez vous imprimer\nl'annuaire au format PDF ?");
		
		this.gridPane.setManaged(false);
		this.gridPane.setVisible(false);

		this.leftButton.setText("Non");

		this.rightButton.setText("Oui");
	}

}
