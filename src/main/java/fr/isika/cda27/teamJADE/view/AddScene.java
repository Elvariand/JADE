package fr.isika.cda27.teamJADE.view;

import fr.isika.cda27.teamJADE.utilz.CustomButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class AddScene extends VBox {
	private InnerShadow innerShadow;
	private HBox buttonBox;
	private Button leftButton;
	private Button rightButton;
	private Label titleLabel;
	private Label labelError;
	private String[] gridPaneLabelsList;
	private GridPane gridPane;

	/**
	 * @param innerShadow
	 */
	public AddScene() {
		this.innerShadow = new InnerShadow();
		innerShadow.setRadius(10);
		innerShadow.setOffsetX(5.0);
		innerShadow.setOffsetY(5.0);
		innerShadow.setColor(Color.web("#ffffff", 0.16));

		this.setPrefSize(690, 720);
		this.setAlignment(Pos.CENTER);
		this.setTranslateX(100);
		VBox.setMargin(this, new Insets(0, 0, 0, 100));

		this.titleLabel = new Label("Ajout d'un stagiaire");
		titleLabel.setPrefSize(680, 100);
		titleLabel.setFont(Font.font("Krona One", 25));
		titleLabel.setStyle("-fx-alignment: center;");
		titleLabel.setTextFill(Color.web("#272727"));

		this.gridPaneLabelsList = new String[5];
		gridPaneLabelsList[0] = "test1";
		gridPaneLabelsList[1] = "test2";
		gridPaneLabelsList[2] = "test3";
		gridPaneLabelsList[3] = "test4";
		gridPaneLabelsList[4] = "test5";
		this.gridPane = createFormGridPane(gridPaneLabelsList);

		this.buttonBox = new HBox(100);
		buttonBox.setAlignment(javafx.geometry.Pos.CENTER);
		buttonBox.setPrefSize(680, 50);

		this.leftButton = new CustomButton("Annuler");

		this.rightButton = new CustomButton("Ajouter");

		buttonBox.getChildren().addAll(leftButton, rightButton);

		// Penser a mettre les textes des 2 conditions
		// Veuilez entrer tous les champs avant d'ajouter le stagiaire
		this.labelError = new Label();
		labelError.setPrefSize(680, 100);
		labelError.setFont(Font.font("Krona One", 14));
		labelError.setStyle("-fx-alignment: center;");
		labelError.setTextFill(Color.web("#272727"));
		labelError.setVisible(false);

		this.getChildren().addAll(titleLabel, gridPane, labelError, buttonBox);
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

	/**
	 * @return the gridPaneLabelsList
	 */
	public String[] getGridPaneLabelsList() {
		return gridPaneLabelsList;
	}

	/**
	 * @param gridPaneLabelsList the gridPaneLabelsList to set
	 */
	public void setGridPaneLabelsList(String[] gridPaneLabelsList) {
		this.gridPaneLabelsList = gridPaneLabelsList;
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
	public void setGridPane(String[] gridPaneLabelsList) {

		// Retirer l'ancien GridPane
		this.getChildren().remove(this.gridPane);

		// Créer et définir un nouveau GridPane
		this.gridPane = createFormGridPane(gridPaneLabelsList);

		// Ajouter le nouveau GridPane à la VBox
		this.getChildren().add(1, this.gridPane);

	}

	public GridPane createFormGridPane(String[] gridPaneLabelsList) {

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
			Label label2 = new Label(gridPaneLabelsList[i]);
			System.out.println(gridPaneLabelsList[i]);
			label2.setFont(Font.font("Krona One", 16));
			label2.setTextFill(Color.web("#272727"));

//			label2.setPrefHeight(35);
//			label2.setStyle("-fx-background-color: #DD734C; " + "-fx-background-radius: 13; "
//					+ "-fx-border-radius: 13; " + "-fx-border-color: transparent transparent #704739 transparent;");
//			label2.setEffect(new InnerShadow(23.93, 2.0, 2.0, Color.web("#000000", 0.1)));
			GridPane.setMargin(label2, new Insets(0, 40, 0, 0));

			// ajouter tout au GridPane
			gridPane.add(label, 0, i);
			gridPane.add(label2, 1, i);
		}

		return gridPane;
	}
}
