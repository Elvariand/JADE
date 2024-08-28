package fr.isika.cda27.teamJADE.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.*;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.Colors.*;

public class RemoveScene extends RepetitiveScene {
	
	private Label labelError;
	private String[] gridPaneLabelsList;


	public RemoveScene() {

		this.titleLabel.setText("Êtes vous sûr(e) de vouloir \nsupprimer le stagiaire suivant ?");

		this.gridPaneLabelsList = new String[5];
		gridPaneLabelsList[0] = "";
		gridPaneLabelsList[1] = "";
		gridPaneLabelsList[2] = "";
		gridPaneLabelsList[3] = "";
		gridPaneLabelsList[4] = "";
		
		this.gridPane = createFormGridPane(gridPaneLabelsList);

		this.buttonBox.setPrefSize(BTN_BOX_WIDTH,(BTN_BOX_HEIGHT - LABEL_ERROR_HEIGHT * 2));
		
		this.leftButton.setText("Non");

		this.rightButton.setText("Oui");


		this.labelError = new Label("Veuilez sélectionner un stagiaire à supprimer");
		labelError.setPrefSize(LABEL_ERROR_WIDTH, LABEL_ERROR_HEIGHT);
		labelError.setFont(Font.font("Krona One", 14));
		labelError.setStyle("-fx-alignment: center;");
		labelError.setTextFill(GREY_COLOR);
		labelError.setVisible(false);
		
		this.getChildren().add(labelError);
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
