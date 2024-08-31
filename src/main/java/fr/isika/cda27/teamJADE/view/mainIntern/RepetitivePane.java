package fr.isika.cda27.teamJADE.view.mainIntern;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.Colors.GREY_COLOR;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.BTN_BOX_HEIGHT;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.BTN_BOX_WIDTH;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.SPACE_BETWEEN_BTNS;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.TITLE_HEIGHT;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.TITLE_WIDTH;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.TOX_VBOX;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.VBOX_HEIGHT;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.VBOX_WIDTH;

import fr.isika.cda27.teamJADE.utilz.CustomButton;
import fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class RepetitivePane extends VBox {
	protected HBox buttonBox;
	protected Button leftButton;
	protected Button rightButton;
	protected Label titleLabel;
	protected GridPane gridPane;
	protected Label familyNameErrorLabel;
	protected Label firstNameErrorLabel;
	protected Label countyErrorLabel;
	protected Label cursusErrorLabel;
	protected Label yearInErrorLabel;
	
	public RepetitivePane() {
		this.setPrefSize(VBOX_WIDTH, VBOX_HEIGHT);
		this.setAlignment(Pos.CENTER);
		this.setTranslateX(TOX_VBOX);

		this.titleLabel = new Label("XXXXXXXXXXXXXXX");
		this.titleLabel.setPrefSize(TITLE_WIDTH, TITLE_HEIGHT);
		this.titleLabel.setFont(Font.font("Krona One", 25));
		this.titleLabel.setStyle("-fx-alignment: center;");
		VBox.setMargin(this.titleLabel, new Insets(50, 0, 50, 0));
		this.titleLabel.setTextFill(GREY_COLOR);

		this.gridPane = MenuVboxValues.createFormGridPane();
		
		this.familyNameErrorLabel = (Label) gridPane.getChildren().get(2);
		this.firstNameErrorLabel = (Label) gridPane.getChildren().get(5);
		this.countyErrorLabel = (Label) gridPane.getChildren().get(8);
		this.cursusErrorLabel = (Label) gridPane.getChildren().get(11);
		this.yearInErrorLabel = (Label) gridPane.getChildren().get(14);
		

		this.buttonBox = new HBox(SPACE_BETWEEN_BTNS);
		this.buttonBox.setAlignment(Pos.CENTER);
		this.buttonBox.setPrefSize(BTN_BOX_WIDTH, BTN_BOX_HEIGHT);

		this.leftButton = new CustomButton("GAUCHE");

		this.rightButton = new CustomButton("DROITE");


		this.buttonBox.getChildren().addAll(leftButton, rightButton);
		this.getChildren().addAll(titleLabel, gridPane, buttonBox);
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
		this.titleLabel.setText(titleLabelText);;
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

	public TextField getGridPaneFirstName() {
		TextField textField = (TextField) gridPane.getChildren().get(4);
		return textField;
	}

	public TextField getGridPaneCounty() {
		TextField textField = (TextField) gridPane.getChildren().get(7);
		return textField;
	}

	public TextField getGridPaneCursus() {
		TextField textField = (TextField) gridPane.getChildren().get(10);
		return textField;
	}

	public TextField getGridPaneYearIn() {
		TextField textField = (TextField) gridPane.getChildren().get(13);
		return textField;
	}


	public String getTextFamilyName() {
		return ((TextField) gridPane.getChildren().get(1)).getText();
		
	}

	public String getTextFirstName() {
		return ((TextField) gridPane.getChildren().get(4)).getText();
		
	}

	public String getTextCounty() {
		return ((TextField) gridPane.getChildren().get(7)).getText();
		
	}

	public String getTextCursus() {
		return ((TextField) gridPane.getChildren().get(10)).getText();
		
	}

	public String getTextYearIn() {
		return ((TextField) gridPane.getChildren().get(13)).getText();
	}

	/**
	 * @return the familyNameErrorLabel
	 */
	public Label getFamilyNameErrorLabel() {
		return familyNameErrorLabel;
	}

	/**
	 * @param familyNameErrorLabel the familyNameErrorLabel to set
	 */
	public void setFamilyNameErrorLabel(Label familyNameErrorLabel) {
		this.familyNameErrorLabel = familyNameErrorLabel;
	}

	/**
	 * @return the firstNameErrorLabel
	 */
	public Label getFirstNameErrorLabel() {
		return firstNameErrorLabel;
	}

	/**
	 * @param firstNameErrorLabel the firstNameErrorLabel to set
	 */
	public void setFirstNameErrorLabel(Label firstNameErrorLabel) {
		this.firstNameErrorLabel = firstNameErrorLabel;
	}

	/**
	 * @return the countyErrorLabel
	 */
	public Label getCountyErrorLabel() {
		return countyErrorLabel;
	}

	/**
	 * @param countyErrorLabel the countyErrorLabel to set
	 */
	public void setCountyErrorLabel(Label countyErrorLabel) {
		this.countyErrorLabel = countyErrorLabel;
	}

	/**
	 * @return the cursusErrorLabel
	 */
	public Label getCursusErrorLabel() {
		return cursusErrorLabel;
	}

	/**
	 * @param cursusErrorLabel the cursusErrorLabel to set
	 */
	public void setCursusErrorLabel(Label cursusErrorLabel) {
		this.cursusErrorLabel = cursusErrorLabel;
	}

	/**
	 * @return the yearInErrorLabel
	 */
	public Label getYearInErrorLabel() {
		return yearInErrorLabel;
	}

	/**
	 * @param yearInErrorLabel the yearInErrorLabel to set
	 */
	public void setYearInErrorLabel(Label yearInErrorLabel) {
		this.yearInErrorLabel = yearInErrorLabel;
	}

}
