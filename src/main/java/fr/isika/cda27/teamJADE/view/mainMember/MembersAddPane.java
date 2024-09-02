package fr.isika.cda27.teamJADE.view.mainMember;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.*;
import fr.isika.cda27.teamJADE.utilz.FadingErrorLabel;

public class MembersAddPane extends MembersRepetitivePane {
	private FadingErrorLabel labelError;

	public MembersAddPane() {
		super();

		this.titleLabel.setText("Ajout d'un membre");

		this.buttonBox.setPrefSize(BTN_BOX_WIDTH, (BTN_BOX_HEIGHT - LABEL_ERROR_HEIGHT * 1.5));

		this.leftButton.setText("Annuler");

		this.rightButton.setText("Ajouter");

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
