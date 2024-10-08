package fr.isika.cda27.teamJADE.view.mainMember;

import fr.isika.cda27.teamJADE.utilz.CustomRadioButton;
import fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues;
import fr.isika.cda27.teamJADE.view.mainIntern.RepetitivePane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class MembersRepetitivePane extends RepetitivePane {
//	private PasswordField passwordField;
	protected Label nameErrorLabel;
	protected Label aliasErrorLabel;
	protected Label emailErrorLabel;
	protected Label passwordErrorLabel;

	public MembersRepetitivePane() {

		this.gridPane = MenuVboxValues.createFormGridPaneMembers();

		this.familyNameErrorLabel = (Label) gridPane.getChildren().get(2);
		this.nameErrorLabel = (Label) gridPane.getChildren().get(5);
		this.aliasErrorLabel = (Label) gridPane.getChildren().get(8);
		this.emailErrorLabel = (Label) gridPane.getChildren().get(11);
		this.passwordErrorLabel = (Label) gridPane.getChildren().get(16);

		super.getChildren().set(1, this.gridPane);

	}

	/**
	 * @return the buttonBox
	 */
	public HBox getButtonBox() {
		return buttonBox;
	}

	/**
	 * @param buttonBox the buttonBox to set
	 */
	public void setButtonBox(HBox buttonBox) {
		this.buttonBox = buttonBox;
	}

	/**
	 * @return the leftButton
	 */
	public Button getLeftButton() {
		return leftButton;
	}

	/**
	 * @param leftButton the leftButton to set
	 */
	public void setLeftButton(Button leftButton) {
		this.leftButton = leftButton;
	}

	/**
	 * @return the rightButton
	 */
	public Button getRightButton() {
		return rightButton;
	}

	/**
	 * @param rightButton the rightButton to set
	 */
	public void setRightButton(Button rightButton) {
		this.rightButton = rightButton;
	}

	/**
	 * @return the titleLabel
	 */
	public Label getTitleLabel() {
		return titleLabel;
	}

	/**
	 * @param titleLabel the titleLabel to set
	 */
	public void setTitleLabel(Label titleLabel) {
		this.titleLabel = titleLabel;
	}

	/**
	 * @param titleLabelText the titleLabelText to set
	 */
	public void setTitleLabelText(String titleLabelText) {
		this.titleLabel.setText(titleLabelText);
		;
	}

	/**
	 * @return the gridPane
	 */
	public GridPane getGridPane() {
		return gridPane;
	}

	/**
	 * @param gridPane the gridPane to set
	 */
	public void setGridPane(GridPane gridPane) {
		this.gridPane = gridPane;
	}

	public TextField getGridPaneFamilyName() {
		TextField textField = (TextField) gridPane.getChildren().get(1);
		return textField;
	}

	public TextField getGridPaneName() {
		TextField textField = (TextField) gridPane.getChildren().get(4);
		return textField;
	}

	public TextField getGridPaneAlias() {
		TextField textField = (TextField) gridPane.getChildren().get(7);
		return textField;
	}

	public TextField getGridPaneEmail() {
		TextField textField = (TextField) gridPane.getChildren().get(10);
		return textField;
	}

	public String getTextFamilyName() {
		return ((TextField) gridPane.getChildren().get(1)).getText();

	}

	public String getTextName() {
		return ((TextField) gridPane.getChildren().get(4)).getText();

	}

	public String getTextAlias() {
		return ((TextField) gridPane.getChildren().get(7)).getText();

	}

	public String getTextEmail() {
		return ((TextField) gridPane.getChildren().get(10)).getText();

	}

	public PasswordField getPasswordField() {
		return ((PasswordField) gridPane.getChildren().get(15));
	}

	public String getTextPasswordField() {
		return ((PasswordField) gridPane.getChildren().get(15)).getText();
	}

	public boolean isAdminSelected() {
		CustomRadioButton radioButton = (CustomRadioButton) (this.gridPane.getChildren().get(13));
		if (radioButton.isTrueBtnSelected()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isNotAdminSelected() {
		CustomRadioButton radioButton = (CustomRadioButton) (this.gridPane.getChildren().get(13));
		if (radioButton.isFalseBtnSelected()) {
			return true;
		} else {
			return false;
		}
	}

	public void setRadioButton(Boolean isAdmin) {
		CustomRadioButton radioButton = (CustomRadioButton) (this.gridPane.getChildren().get(13));
		if (isAdmin) {
			radioButton.getTrueBtn().setSelected(true);
		} else {
			radioButton.getFalseBtn().setSelected(true);
		}
	}

	public void refreshRadioButtons() {
		CustomRadioButton radioButton = (CustomRadioButton) (this.gridPane.getChildren().get(13));
		radioButton.getTrueBtn().setSelected(false);
		radioButton.getFalseBtn().setSelected(false);
	}

	public CustomRadioButton getCustomRadioButton() {
		return (CustomRadioButton) (this.gridPane.getChildren().get(13));
	}

	/**
	 * @return the nameErrorLabel
	 */
	public Label getNameErrorLabel() {
		return nameErrorLabel;
	}

	/**
	 * @param nameErrorLabel the nameErrorLabel to set
	 */
	public void setNameErrorLabel(Label nameErrorLabel) {
		this.nameErrorLabel = nameErrorLabel;
	}

	/**
	 * @return the aliasErrorLabel
	 */
	public Label getAliasErrorLabel() {
		return aliasErrorLabel;
	}

	/**
	 * @param aliasErrorLabel the aliasErrorLabel to set
	 */
	public void setAliasErrorLabel(Label aliasErrorLabel) {
		this.aliasErrorLabel = aliasErrorLabel;
	}

	/**
	 * @return the emailErrorLabel
	 */
	public Label getEmailErrorLabel() {
		return emailErrorLabel;
	}

	/**
	 * @param emailErrorLabel the emailErrorLabel to set
	 */
	public void setEmailErrorLabel(Label emailErrorLabel) {
		this.emailErrorLabel = emailErrorLabel;
	}

	/**
	 * @return the passwordErrorLabel
	 */
	public Label getPasswordErrorLabel() {
		return passwordErrorLabel;
	}

	/**
	 * @param passwordErrorLabel the passwordErrorLabel to set
	 */
	public void setPasswordErrorLabel(Label passwordErrorLabel) {
		this.passwordErrorLabel = passwordErrorLabel;
	}

}
