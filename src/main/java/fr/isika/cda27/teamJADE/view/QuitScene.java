package fr.isika.cda27.teamJADE.view;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class QuitScene extends RepetitiveScene {

	public QuitScene() {
		super();

		this.titleLabel.setText("Vous êtres sur le point de vous\ndéconnecter, souhaitez vous\npoursuivre ?");
		
		this.gridPane.setManaged(false);
		this.gridPane.setVisible(false);

		this.leftButton.setText("Non");

		this.rightButton.setText("Oui");
		
		rightButton.setOnAction(event -> {
        	Stage stage = ((Stage) QuitScene.this.getScene().getWindow()); 
        	Scene scene = new Scene(new CustomLoginScene()); 
        	scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
			stage.setScene(scene);
			
	}); 

}
}
