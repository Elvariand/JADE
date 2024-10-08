package fr.isika.cda27.teamJADE.view.mainMember;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.*;

import fr.isika.cda27.teamJADE.view.mainIntern.RepetitivePane;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.Colors.*;

public class MembersRemovePane extends RepetitivePane {
	

	private Label labelError;
	private String[] gridPaneLabelsList = {"","","","","",""};

	public MembersRemovePane() {

		super();
		this.titleLabel.setText("Êtes vous sûr(e) de vouloir \nsupprimer le stagiaire suivant ?");
		

		super.getChildren().remove(super.gridPane);
		this.gridPane = createFormGridPane(gridPaneLabelsList);
		super.getChildren().add(1, this.gridPane);

		this.buttonBox.setPrefSize(BTN_BOX_WIDTH,(BTN_BOX_HEIGHT - LABEL_ERROR_HEIGHT * 2));
		
		this.leftButton.setText("Non");

		this.rightButton.setText("Oui");
		this.rightButton.setDisable(true);

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

		// On retire l'ancien GridPane
		this.getChildren().remove(this.gridPane);

		// On crée un nouveau GridPane
		this.gridPane = createFormGridPane(gridPaneLabelsList);

		// On ajoute le nouveau GridPane à la VBox
		this.getChildren().add(1, this.gridPane);

	}

	public String getTextFamilyName() {
		return ((Label) gridPane.getChildren().get(1)).getText().substring(1).trim();
		
	}

	public String getTextFirstName() {
		return ((Label) gridPane.getChildren().get(3)).getText().substring(1).trim();
		
	}

	public String getTextCounty() {
		return ((Label) gridPane.getChildren().get(5)).getText().substring(1).trim();
		
	}

	public String getTextCursus() {
		return ((Label) gridPane.getChildren().get(7)).getText().substring(1).trim();
		
	}

	public String getTextYearIn() {
		return ((Label) gridPane.getChildren().get(9)).getText().substring(1).trim();
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
		for (int i = 0; i < 5; i++) {
			RowConstraints rowConstraints = new RowConstraints();
			rowConstraints.setPrefHeight(50);
			gridPane.getRowConstraints().add(rowConstraints);

			// label
			Label label = new Label("             " + LABEL_TEXTS_MEMBERS[i]);
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
