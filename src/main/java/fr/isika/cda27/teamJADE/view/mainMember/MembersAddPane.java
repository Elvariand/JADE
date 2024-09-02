package fr.isika.cda27.teamJADE.view.mainMember;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.Colors.GREY_COLOR;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.*;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.ShadowSet.INNER_SHADOW_BLACK;

import fr.isika.cda27.teamJADE.utilz.FadingErrorLabel;
import fr.isika.cda27.teamJADE.view.login.VisiblePasswordFieldSkin;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MembersAddPane extends MembersRepetitivePane {
	private FadingErrorLabel labelError;

	public MembersAddPane() {
		super();

		this.titleLabel.setText("Ajout d'un membre");

		this.buttonBox.setPrefSize(BTN_BOX_WIDTH, (BTN_BOX_HEIGHT - LABEL_ERROR_HEIGHT * 1.5));

		this.leftButton.setText("Annuler");

		this.rightButton.setText("Ajouter");
//
//		this.labelError = new FadingErrorLabel("Veuillez entrer tous les champs avant d'ajouter le stagiaire");
//		this.labelError.setPrefSize(LABEL_ERROR_WIDTH, LABEL_ERROR_HEIGHT*2);
//		this.labelError.setFont(Font.font("Krona One", 14));
//		this.labelError.setStyle("-fx-alignment: center;");
//		this.labelError.setTextFill(GREY_COLOR);
//		this.labelError.setVisible(false);
//
//		this.getChildren().set(2,labelError);
//		this.getChildren().add(3,super.getButtonBox());
		

		
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
