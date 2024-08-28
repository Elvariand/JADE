package fr.isika.cda27.teamJADE.view;

public class QuitScene extends RepetitiveScene {

	public QuitScene() {
		super();

		this.titleLabel.setText("Vous êtres sur le point de vous\ndéconnecter, souhaitez vous\npoursuivre ?");
		
		this.gridPane.setManaged(false);
		this.gridPane.setVisible(false);

		this.leftButton.setText("Non");

		this.rightButton.setText("Oui");
	}

}
