package fr.isika.cda27.teamJADE.view;

import fr.isika.cda27.teamJADE.utilz.CustomButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.*;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.Colors.*;

public class RemoveScene extends VBox {
	private HBox buttonBox;
	private Button leftButton;
	private Button rightButton;
	private Label titleLabel;
	private Label labelError;
	private String[] gridPaneLabelsList;
	private GridPane gridPane;

	public RemoveScene() {

		this.setPrefSize(VBOX_WIDTH, VBOX_HEIGHT);
		this.setAlignment(Pos.CENTER);
		this.setTranslateX(TOX_VBOX);

		this.titleLabel = new Label("Etes vous sûr de vouloir \nsupprimer le stagiaire suivant ?");
		titleLabel.setPrefSize(TITLE_WIDTH, TITLE_HEIGHT);
		titleLabel.setFont(Font.font("Krona One", 25));
		titleLabel.setStyle("-fx-alignment: center;");
		titleLabel.setTextFill(GREY_COLOR);

		this.gridPaneLabelsList = new String[5];
		gridPaneLabelsList[0] = "";
		gridPaneLabelsList[1] = "";
		gridPaneLabelsList[2] = "";
		gridPaneLabelsList[3] = "";
		gridPaneLabelsList[4] = "";
		
		this.gridPane = createFormGridPane(gridPaneLabelsList);

		this.buttonBox = new HBox(SPACE_BETWEEN_BTNS);
		buttonBox.setAlignment(javafx.geometry.Pos.CENTER);
		buttonBox.setPrefSize(BTN_BOX_WIDTH,(BTN_BOX_HEIGHT - LABEL_ERROR_HEIGHT * 2));
		this.leftButton = new CustomButton("Non");

		this.rightButton = new CustomButton("Oui");

		buttonBox.getChildren().addAll(leftButton, rightButton);

		this.labelError = new Label("Veuilez sélectionner un stagiaire à supprimer");
		labelError.setPrefSize(LABEL_ERROR_WIDTH, LABEL_ERROR_HEIGHT);
		labelError.setFont(Font.font("Krona One", 14));
		labelError.setStyle("-fx-alignment: center;");
		labelError.setTextFill(GREY_COLOR);
		labelError.setVisible(false);
		
		this.getChildren().addAll(titleLabel, gridPane, labelError, buttonBox);
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

		// GridPane
		GridPane gridPane = new GridPane();
		gridPane.setPrefSize(GRIDPANE_WIDTH, GRIDPANE_HEIGHT);
		gridPane.setVgap(15);
		gridPane.setHgap(10);

		// première colonne
		ColumnConstraints col1Constraints = new ColumnConstraints();
		col1Constraints.setPrefWidth(COL1_WIDTH + 65);
		gridPane.getColumnConstraints().add(col1Constraints);

		// deuxième colonne
		ColumnConstraints col2Constraints = new ColumnConstraints();
		col2Constraints.setPrefWidth(COL2_WIDTH - 65);
		gridPane.getColumnConstraints().add(col2Constraints);

		// lignes du GridPane
		for (int i = 0; i < NBR_OF_LINES; i++) {
			RowConstraints rowConstraints = new RowConstraints();
			rowConstraints.setPrefHeight(50);
			gridPane.getRowConstraints().add(rowConstraints);

			// label
			Label label = new Label("             " + LABEL_TEXTS[i]);
			label.setFont(Font.font("Krona One", 16));
			label.setTextFill(GREY_COLOR);
			GridPane.setMargin(label, new Insets(0, 30, 0, 40));

			// texte de droite
			Label label2 = new Label(":       " + gridPaneLabelsList[i]);
			label2.setFont(Font.font("Krona One", 16));
			label2.setTextFill(GREY_COLOR);

			GridPane.setMargin(label2, new Insets(0, 40, 0, 0));

			// ajouter tout au GridPane
			gridPane.add(label, 0, i);
			gridPane.add(label2, 1, i);
		}

		return gridPane;
	}
}
