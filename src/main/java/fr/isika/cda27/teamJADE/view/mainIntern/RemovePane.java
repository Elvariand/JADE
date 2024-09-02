package fr.isika.cda27.teamJADE.view.mainIntern;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.*;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.Colors.*;

public class RemovePane extends RepetitivePane {

	private Label labelError;
	private String[] gridPaneLabelsList = { "", "", "", "", "" };

	/**
	 * Crée le panneau 'Supprimer'
	 */
	public RemovePane() {

		super();
		this.titleLabel.setText("Êtes vous sûr(e) de vouloir \nsupprimer le stagiaire suivant ?");

		super.getChildren().remove(super.gridPane);
		this.gridPane = createFormGridPane(gridPaneLabelsList);
		super.getChildren().add(1, this.gridPane);

		this.buttonBox.setPrefSize(BTN_BOX_WIDTH, (BTN_BOX_HEIGHT - LABEL_ERROR_HEIGHT * 2));

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
	 * @return le labelError
	 */
	public Label getLabelError() {
		return labelError;
	}

	/**
	 * @param labelError le labelError à définir
	 */
	public void setLabelError(Label labelError) {
		this.labelError = labelError;
	}

	/**
	 * @return le gridPaneLabelsList
	 */
	public String[] getGridPaneLabelsList() {
		return gridPaneLabelsList;
	}

	/**
	 * @param gridPaneLabelsList le gridPaneLabelsList à définir
	 */
	public void setGridPaneLabelsList(String[] gridPaneLabelsList) {
		this.gridPaneLabelsList = gridPaneLabelsList;
	}

	/**
	 * @param gridPane le gridPane à définir
	 */
	public void setGridPane(String[] gridPaneLabelsList) {

		// On retire l'ancien GridPane
		this.getChildren().remove(this.gridPane);

		// On crée un nouveau GridPane
		this.gridPane = createFormGridPane(gridPaneLabelsList);

		// On ajoute le nouveau GridPane à la VBox
		this.getChildren().add(1, this.gridPane);

	}

	/**
	 * @return Le texte correspondant au nom de famille, sans le premier caractère
	 *         et sans espaces superflus.
	 */
	public String getTextFamilyName() {
		return ((Label) gridPane.getChildren().get(1)).getText().substring(1).trim();

	}

	/**
	 * @return Le texte correspondant au prénom, sans le premier caractère et sans
	 *         espaces superflus.
	 */
	public String getTextFirstName() {
		return ((Label) gridPane.getChildren().get(3)).getText().substring(1).trim();

	}

	/**
	 * @return Le texte correspondant au département, sans le premier caractère et
	 *         sans espaces superflus.
	 */
	public String getTextCounty() {
		return ((Label) gridPane.getChildren().get(5)).getText().substring(1).trim();

	}

	/**
	 * @return Le texte correspondant à la formation suivie, sans le premier
	 *         caractère et sans espaces superflus.
	 */
	public String getTextCursus() {
		return ((Label) gridPane.getChildren().get(7)).getText().substring(1).trim();

	}

	/**
	 * @return Le texte correspondant à l'année d'entrée, sans le premier caractère
	 *         et sans espaces superflus.
	 */
	public String getTextYearIn() {
		return ((Label) gridPane.getChildren().get(9)).getText().substring(1).trim();
	}

	/**
	 * Crée et configure le GridPane utilisé pour afficher un formulaire
	 * 
	 * @param gridPaneLabelsList
	 * @return Un objet {@link GridPane} configuré avec des étiquettes de texte
	 *         disposées en deux colonnes.
	 */
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
		for (int i = 0; i < NBR_OF_LINES / 2; i++) {
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
