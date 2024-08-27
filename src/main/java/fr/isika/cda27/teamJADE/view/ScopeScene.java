package fr.isika.cda27.teamJADE.view;

import java.util.ArrayList;

import fr.isika.cda27.teamJADE.utilz.CustomButton;
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

public class ScopeScene extends VBox {
	private InnerShadow innerShadow;
	private HBox buttonBox;
	private Button leftButton;
	private Button rightButton;
	private Label titleLabel;
	private GridPane gridPane;

	/**
	 * @param innerShadow
	 */
	public ScopeScene() {
		this.innerShadow = new InnerShadow();
		innerShadow.setRadius(10);
		innerShadow.setOffsetX(5.0);
		innerShadow.setOffsetY(5.0);
		innerShadow.setColor(Color.web("#ffffff", 0.16));

		this.setPrefSize(690, 720);
		this.setAlignment(Pos.CENTER);
		this.setTranslateX(100);
		VBox.setMargin(this, new Insets(0, 0, 0, 100));

		this.titleLabel = new Label("Recherche avancée");
		titleLabel.setPrefSize(680, 100);
		titleLabel.setFont(Font.font("Krona One", 25));
		titleLabel.setStyle("-fx-alignment: center;");
		titleLabel.setTextFill(Color.web("#272727"));

		this.gridPane = createFormGridPane();

		this.buttonBox = new HBox(100);
		buttonBox.setAlignment(javafx.geometry.Pos.CENTER);
		buttonBox.setPrefSize(680, 192);

		this.leftButton = new CustomButton("Réinitialiser");

		this.rightButton = new CustomButton("Rechercher");

		buttonBox.getChildren().addAll(leftButton, rightButton);
		this.getChildren().addAll(titleLabel, gridPane, buttonBox);
	}

	/**
	 * @return the innerShadow
	 */
	public InnerShadow getInnerShadow() {
		return innerShadow;
	}

	/**
	 * @param innerShadow the innerShadow to set
	 */
	public void setInnerShadow(InnerShadow innerShadow) {
		this.innerShadow = innerShadow;
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

		String[] labelTexts = { "Nom de famille", "Prénom", "Région", "Formation suivie", "Année" };

		// GridPane
		GridPane gridPane = new GridPane();
		gridPane.setPrefSize(680, 340);
		gridPane.setVgap(15);
		gridPane.setHgap(10);

		// marges pour le GridPane
		VBox.setMargin(gridPane, new Insets(0, 20, 0, 20));

		// première colonne
		ColumnConstraints col1Constraints = new ColumnConstraints();
		col1Constraints.setPrefWidth(270);
		gridPane.getColumnConstraints().add(col1Constraints);

		// deuxième colonne
		ColumnConstraints col2Constraints = new ColumnConstraints();
		col2Constraints.setPrefWidth(408);
		gridPane.getColumnConstraints().add(col2Constraints);

		// lignes du GridPane
		for (int i = 0; i < 5; i++) {
			RowConstraints rowConstraints = new RowConstraints();
			rowConstraints.setPrefHeight(50);
			gridPane.getRowConstraints().add(rowConstraints);

			// label
			Label label = new Label(labelTexts[i]);
			label.setFont(Font.font("Krona One", 16));
			label.setTextFill(Color.web("#272727"));
			GridPane.setMargin(label, new Insets(0, 30, 0, 40));

			// texte de droite
			TextField textField = new TextField();
			textField.setPrefHeight(35);
			textField.setStyle("-fx-background-color: #DD734C; " + "-fx-background-radius: 13; "
					+ "-fx-border-radius: 13; " + "-fx-border-color: transparent transparent #704739 transparent;");
			textField.setEffect(new InnerShadow(23.93, 2.0, 2.0, Color.web("#000000", 0.1)));
			GridPane.setMargin(textField, new Insets(0, 40, 0, 0));

			// ajouter tout au GridPane
			gridPane.add(label, 0, i);
			gridPane.add(textField, 1, i);
		}

		return gridPane;
	}

}
