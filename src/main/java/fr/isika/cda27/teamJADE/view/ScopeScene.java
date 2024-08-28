package fr.isika.cda27.teamJADE.view;

import fr.isika.cda27.teamJADE.utilz.CustomButton;
import fr.isika.cda27.teamJADE.utilz.CustomTextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.*;

public class ScopeScene extends VBox {
	private HBox buttonBox;
	private Button leftButton;
	private Button rightButton;
	private Label titleLabel;
	private GridPane gridPane;

	public ScopeScene() {
		this.setPrefSize(VBOX_WIDTH, VBOX_HEIGHT);
		this.setAlignment(Pos.CENTER);
		this.setTranslateX(TOX_VBOX);

		this.titleLabel = new Label("Recherche avancée");
		titleLabel.setPrefSize(TITLE_WIDTH, TITLE_HEIGHT);
		titleLabel.setFont(Font.font("Krona One", 25));
		titleLabel.setStyle("-fx-alignment: center;");
		titleLabel.setTextFill(Color.web("#272727"));

		this.gridPane = createFormGridPane();

		this.buttonBox = new HBox(SPACE_BETWEEN_BTNS);
		buttonBox.setAlignment(javafx.geometry.Pos.CENTER);
		buttonBox.setPrefSize(BTN_BOX_WIDTH, BTN_BOX_HEIGHT);

		this.leftButton = new CustomButton("Réinitialiser");

		this.rightButton = new CustomButton("Rechercher");

		buttonBox.getChildren().addAll(leftButton, rightButton);
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
		TextField textField = (TextField) gridPane.getChildren().get(3);
		return textField;
	}

	public TextField getGridPaneCounty() {
		TextField textField = (TextField) gridPane.getChildren().get(5);
		return textField;
	}

	public TextField getGridPaneCursus() {
		TextField textField = (TextField) gridPane.getChildren().get(7);
		return textField;
	}

	public TextField getGridPaneYearIn() {
		TextField textField = (TextField) gridPane.getChildren().get(9);
		return textField;
	}

	public GridPane createFormGridPane() {

		// GridPane
		GridPane gridPane = new GridPane();
		gridPane.setPrefSize(GRIDPANE_WIDTH, GRIDPANE_HEIGHT);
		gridPane.setVgap(15);
		gridPane.setHgap(10);

		// marges pour le GridPane
		VBox.setMargin(gridPane, new Insets(0, 20, 0, 20));

		// première colonne
		ColumnConstraints col1Constraints = new ColumnConstraints();
		col1Constraints.setPrefWidth(COL1_WIDTH);
		gridPane.getColumnConstraints().add(col1Constraints);

		// deuxième colonne
		ColumnConstraints col2Constraints = new ColumnConstraints();
		col2Constraints.setPrefWidth(COL2_WIDTH);
		gridPane.getColumnConstraints().add(col2Constraints);

		// lignes du GridPane
		for (int i = 0; i < NBR_OF_LINES; i++) {
			RowConstraints rowConstraints = new RowConstraints();
			rowConstraints.setPrefHeight(ROW_HEIGHT);
			gridPane.getRowConstraints().add(rowConstraints);

			// label
			Label label = new Label(LABEL_TEXTS[i]);
			label.setFont(Font.font("Krona One", 16));
			label.setTextFill(Color.web("#272727"));
			GridPane.setMargin(label, new Insets(0, 30, 0, 40));

			// texte de droite
			CustomTextField textField = new CustomTextField();
			
			GridPane.setMargin(textField, new Insets(0, 40, 0, 0));

			// ajouter tout au GridPane
			gridPane.add(label, 0, i);
			gridPane.add(textField, 1, i);
		}

		return gridPane;
	}

}
