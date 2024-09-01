package fr.isika.cda27.teamJADE.view.mainMember;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.Colors.GREY_COLOR;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.BTN_BOX_HEIGHT;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.BTN_BOX_WIDTH;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.SPACE_BETWEEN_BTNS;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.TITLE_HEIGHT;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.TITLE_WIDTH;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.TOX_VBOX;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.VBOX_HEIGHT;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.VBOX_WIDTH;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.ShadowSet.INNER_SHADOW_BLACK;

import fr.isika.cda27.teamJADE.utilz.CustomButton;
import fr.isika.cda27.teamJADE.utilz.CustomRadioButton;
import fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues;
import fr.isika.cda27.teamJADE.view.login.VisiblePasswordFieldSkin;
import fr.isika.cda27.teamJADE.view.mainIntern.RepetitivePane;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MembersRepetitivePane extends RepetitivePane {
	private PasswordField passwordField;

	public MembersRepetitivePane() {

		this.gridPane = MenuVboxValues.createFormGridPaneMembers();

		super.getChildren().set(1, this.gridPane);

		// Création du passwordField
		this.passwordField = new PasswordField();
		this.passwordField.setPrefWidth(200);
		this.passwordField.setPrefHeight(35);
		this.passwordField.setStyle("-fx-background-color : #DD734C; " + "-fx-background-radius: 13; "
				+ "-fx-border-radius: 13; " + "-fx-border-color: transparent transparent #704739 transparent;");
		passwordField.setFont(Font.font("Krona One", 18));
		passwordField.setEffect(INNER_SHADOW_BLACK);
		passwordField.setSkin(new VisiblePasswordFieldSkin(passwordField));

		
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
		TextField textField = (TextField) gridPane.getChildren().get(3);
		return textField;
	}

	public TextField getGridPaneAlias() {
		TextField textField = (TextField) gridPane.getChildren().get(5);
		return textField;
	}

	public TextField getGridPaneEmail() {
		TextField textField = (TextField) gridPane.getChildren().get(7);
		return textField;
	}

	public String getTextFamilyName() {
		return ((TextField) gridPane.getChildren().get(1)).getText();

	}

	public String getTextName() {
		return ((TextField) gridPane.getChildren().get(3)).getText();

	}

	public String getTextAlias() {
		return ((TextField) gridPane.getChildren().get(5)).getText();

	}

	public String getTextEmail() {
		return ((TextField) gridPane.getChildren().get(7)).getText();

	}

	public PasswordField getPasswordField() {
		return ((PasswordField) gridPane.getChildren().get(11));
	}
	
	public String getTextPasswordField() {
		return ((PasswordField) gridPane.getChildren().get(11)).getText();
	}
	
	public boolean isAdminSelected() {
		CustomRadioButton radioButton = (CustomRadioButton) (this.gridPane.getChildren().get(9));
		if (radioButton.isTrueBtnSelected()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isNotAdminSelected() {
		CustomRadioButton radioButton = (CustomRadioButton) (this.gridPane.getChildren().get(9));
		if (radioButton.isFalseBtnSelected()) {
			return true;
		} else {
			return false;
		}
	}

	public void setRadioButton(Boolean isAdmin) {
		CustomRadioButton radioButton = (CustomRadioButton) (this.gridPane.getChildren().get(9));
		if (isAdmin) {
			radioButton.getTrueBtn().setSelected(true);
		} else {
			radioButton.getFalseBtn().setSelected(true);
		}
	}

	public void refreshRadioButtons() {
		CustomRadioButton radioButton = (CustomRadioButton) (this.gridPane.getChildren().get(9));
		radioButton.getTrueBtn().setSelected(false);
		radioButton.getFalseBtn().setSelected(false);
	}
	
	public CustomRadioButton getCustomRadioButton() {
		return(CustomRadioButton) (this.gridPane.getChildren().get(9));
	}

}
