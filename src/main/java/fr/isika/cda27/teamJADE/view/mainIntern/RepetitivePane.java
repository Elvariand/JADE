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

	/**
	 * Crée le panneau répétitive
	 */
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
	 * @return le buttonBox
	 */
	public HBox getButtonBox() {
		return buttonBox;
	}

	/**
	 * @param buttonBox le buttonBox à définir
	 */
	public void setButtonBox(HBox buttonBox) {
		this.buttonBox = buttonBox;
	}

	/**
	 * @return le bouton de gauche
	 */
	public Button getLeftButton() {
		return leftButton;
	}

	/**
	 * @param leftButton le bouton de gauche à définir
	 */
	public void setLeftButton(Button leftButton) {
		this.leftButton = leftButton;
	}

	/**
	 * @return le bouton de droite
	 */
	public Button getRightButton() {
		return rightButton;
	}

	/**
	 * @param rightButton le rightButton à définir
	 */
	public void setRightButton(Button rightButton) {
		this.rightButton = rightButton;
	}

	/**
	 * @return le titleLabel
	 */
	public Label getTitleLabel() {
		return titleLabel;
	}

	/**
	 * @param titleLabel le titleLabel à définir
	 */
	public void setTitleLabel(Label titleLabel) {
		this.titleLabel = titleLabel;
	}

	/**
	 * @param titleLabelText le titleLabelText à définir
	 */
	public void setTitleLabelText(String titleLabelText) {
		this.titleLabel.setText(titleLabelText);
		;
	}

	/**
	 * @return le gridPane
	 */
	public GridPane getGridPane() {
		return gridPane;
	}

	/**
	 * @param gridPane le gridPane à définir
	 */
	public void setGridPane(GridPane gridPane) {
		this.gridPane = gridPane;
	}

	/**
	 * @return le champ de texte à l'index 1 du {@link GridPane}, représentant le
	 *         nom de famille.
	 */
	public TextField getGridPaneFamilyName() {
		TextField textField = (TextField) gridPane.getChildren().get(1);
		return textField;
	}

	/**
	 * @return le champ de texte à l'index 4 du {@link GridPane}, représentant le
	 *         prénom.
	 */
	public TextField getGridPaneFirstName() {
		TextField textField = (TextField) gridPane.getChildren().get(4);
		return textField;
	}

	/**
	 * @return le champ de texte à l'index 7 du {@link GridPane}, représentant le
	 *         département.
	 */
	public TextField getGridPaneCounty() {
		TextField textField = (TextField) gridPane.getChildren().get(7);
		return textField;
	}

	/**
	 * @return le champ de texte à l'index 10 du {@link GridPane}, représentant la
	 *         formation suivie.
	 */
	public TextField getGridPaneCursus() {
		TextField textField = (TextField) gridPane.getChildren().get(10);
		return textField;
	}

	/**
	 * @return le champ de texte à l'index 13 du {@link GridPane}, représentant
	 *         l'année.
	 */
	public TextField getGridPaneYearIn() {
		TextField textField = (TextField) gridPane.getChildren().get(13);
		return textField;
	}

	/**
	 * @return le texte du champ saisi par l'utilisateur à l'index 1 du
	 *         {@link GridPane}, représentant le nom de famille.
	 */
	public String getTextFamilyName() {
		return ((TextField) gridPane.getChildren().get(1)).getText();

	}

	/**
	 * @return le texte du champ saisi par l'utilisateur à l'index 4 du
	 *         {@link GridPane}, représentant le prénom.
	 */
	public String getTextFirstName() {
		return ((TextField) gridPane.getChildren().get(4)).getText();

	}

	/**
	 * @return le texte du champ saisi par l'utilisateur à l'index 7 du
	 *         {@link GridPane}, représentant le département.
	 */
	public String getTextCounty() {
		return ((TextField) gridPane.getChildren().get(7)).getText();

	}

	/**
	 * @return le texte du champ saisi par l'utilisateur à l'index 10 du
	 *         {@link GridPane}, représentant la formation suivie.
	 */
	public String getTextCursus() {
		return ((TextField) gridPane.getChildren().get(10)).getText();

	}

	/**
	 * @return le texte du champ saisi par l'utilisateur à l'index 13 du
	 *         {@link GridPane}, représentant l'année.
	 */
	public String getTextYearIn() {
		return ((TextField) gridPane.getChildren().get(13)).getText();
	}

	/**
	 * @return le familyNameErrorLabel
	 */
	public Label getFamilyNameErrorLabel() {
		return familyNameErrorLabel;
	}

	/**
	 * @param familyNameErrorLabel le familyNameErrorLabel à définir
	 */
	public void setFamilyNameErrorLabel(Label familyNameErrorLabel) {
		this.familyNameErrorLabel = familyNameErrorLabel;
	}

	/**
	 * @return le firstNameErrorLabel
	 */
	public Label getFirstNameErrorLabel() {
		return firstNameErrorLabel;
	}

	/**
	 * @param firstNameErrorLabel le firstNameErrorLabel à définir
	 */
	public void setFirstNameErrorLabel(Label firstNameErrorLabel) {
		this.firstNameErrorLabel = firstNameErrorLabel;
	}

	/**
	 * @return le countyErrorLabel
	 */
	public Label getCountyErrorLabel() {
		return countyErrorLabel;
	}

	/**
	 * @param countyErrorLabel le countyErrorLabel à définir
	 */
	public void setCountyErrorLabel(Label countyErrorLabel) {
		this.countyErrorLabel = countyErrorLabel;
	}

	/**
	 * @return le cursusErrorLabel
	 */
	public Label getCursusErrorLabel() {
		return cursusErrorLabel;
	}

	/**
	 * @param cursusErrorLabel le cursusErrorLabel à définir
	 */
	public void setCursusErrorLabel(Label cursusErrorLabel) {
		this.cursusErrorLabel = cursusErrorLabel;
	}

	/**
	 * @return le yearInErrorLabel
	 */
	public Label getYearInErrorLabel() {
		return yearInErrorLabel;
	}

	/**
	 * @param yearInErrorLabel le yearInErrorLabel à définir
	 */
	public void setYearInErrorLabel(Label yearInErrorLabel) {
		this.yearInErrorLabel = yearInErrorLabel;
	}

}
