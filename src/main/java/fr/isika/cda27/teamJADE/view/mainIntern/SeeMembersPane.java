package fr.isika.cda27.teamJADE.view.mainIntern;

import fr.isika.cda27.teamJADE.model.Member;
import fr.isika.cda27.teamJADE.view.mainMember.MembersMainScene;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SeeMembersPane extends RepetitivePane {

	Member connectedMember;

	/**
	 * Crée le panneau de confirmation pour afficher la liste des membres.
	 * 
	 * @param connectedMember
	 */
	public SeeMembersPane(Member connectedMember) {
		super();
		this.connectedMember = connectedMember;
		this.titleLabel.setText("Souhaitez vous voir \nla liste des membres ?");

		this.gridPane.setManaged(false);
		this.gridPane.setVisible(false);

		this.leftButton.setText("Non");

		this.rightButton.setText("Oui");

		rightButton.setOnAction(event -> {
			// On récupère le root
			Parent root = this.getScene().getRoot();

			Scene newScene;
			// Si root est une instance de CustomMainScene on passe à MembersMainScene
			if (root instanceof InternsMainScene) {
				newScene = new Scene(new MembersMainScene(connectedMember));

				// sinon on passe à CustomMainScene
			} else {
				newScene = new Scene(new InternsMainScene(connectedMember));
			}

			Stage stage = ((Stage) SeeMembersPane.this.getScene().getWindow());

			newScene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
			stage.setScene(newScene);
		});
	}

}
