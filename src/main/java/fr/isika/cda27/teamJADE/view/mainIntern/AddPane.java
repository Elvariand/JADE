package fr.isika.cda27.teamJADE.view.mainIntern;

import fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.*;

import fr.isika.cda27.teamJADE.utilz.FadingErrorLabel;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.Colors.*;

public class AddPane extends RepetitivePane {
	private FadingErrorLabel labelError;

	public AddPane() {
		super();

		this.titleLabel.setText("Ajout d'un stagiaire");


//		this.buttonBox.setPrefSize(BTN_BOX_WIDTH,(BTN_BOX_HEIGHT - LABEL_ERROR_HEIGHT * 2));

		this.leftButton.setText("Annuler");

		this.rightButton.setText("Ajouter");
		this.rightButton.setDisable(true);
		
		this.labelError = new FadingErrorLabel("Veuillez entrer tous les champs avant d'ajouter le stagiaire");
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
	public FadingErrorLabel getLabelError() {
		return labelError;
	}

	/**
	 * @param labelError the labelError to set
	 */
	public void setLabelError(FadingErrorLabel labelError) {
		this.labelError = labelError;
	}
}