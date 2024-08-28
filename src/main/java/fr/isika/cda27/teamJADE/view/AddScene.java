package fr.isika.cda27.teamJADE.view;

import fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.*;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.Colors.*;

public class AddScene extends RepetitiveScene {
	private Label labelError;

	public AddScene() {
		

		this.titleLabel.setText("Ajout d'un stagiaire");


		this.gridPane = MenuVboxValues.createFormGridPane();

		this.buttonBox.setPrefSize(BTN_BOX_WIDTH,(BTN_BOX_HEIGHT - LABEL_ERROR_HEIGHT * 2));

		this.leftButton.setText("Annuler");

		this.rightButton.setText("Ajouter");

		
		this.labelError = new Label("Veuilez entrer tous les champs avant d'ajouter le stagiaire");
		this.labelError.setPrefSize(LABEL_ERROR_WIDTH, LABEL_ERROR_HEIGHT);
		this.labelError.setFont(Font.font("Krona One", 14));
		this.labelError.setStyle("-fx-alignment: center;");
		this.labelError.setTextFill(GREY_COLOR);
		this.labelError.setVisible(false);

		this.getChildren().add(labelError);
	}

	/**
	 * @return the labelError
	 */
	public Label getLabelError() {
		return labelError;
	}

	/**
	 * @param labelError the labelError to set
	 */
	public void setLabelError(Label labelError) {
		this.labelError = labelError;
	}

	/**
	 * @param gridPane the gridPane to set
	 */
	public void setGridPane(String[] gridPaneLabelsList) {

		// Retirer l'ancien GridPane
		this.getChildren().remove(this.gridPane);

		// Créer et définir un nouveau GridPane
		this.gridPane = createFormGridPane();

		// Ajouter le nouveau GridPane à la VBox
		this.getChildren().add(1, this.gridPane);

	}
}
