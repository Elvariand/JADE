package fr.isika.cda27.teamJADE.view.mainIntern;

import javafx.geometry.Insets;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.Colors.GREY_COLOR;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.LABEL_ERROR_HEIGHT;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.LABEL_ERROR_WIDTH;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class UpdatePane extends RepetitivePane {
	private Label labelError;

	public UpdatePane() {
		super();

		this.titleLabel.setText("Modification du stagiaire");
//		this.titleLabel.setPadding(new Insets(20,0,0,0));
		
		this.leftButton.setText("Annuler");

		this.rightButton.setText("Modifier");
		this.rightButton.setDisable(true);

		
		this.labelError = new Label("Veuillez entrer tous les champs afin de modifier le stagiaire");
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

}
