package fr.isika.cda27.teamJADE.view;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.Colors.GREY_COLOR;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MainSceneValues.TOX_SMALL_MENU;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.COL1_WIDTH;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.COL2_WIDTH;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.GRIDPANE_HEIGHT;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.GRIDPANE_WIDTH;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.LABEL_TEXTS;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.NBR_OF_LINES;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.ShadowSet.DROP_SHADOW;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;

public class MenuContentHBox extends HBox {

	private Pane pane;

	public MenuContentHBox() {
		super();

		this.setMaxWidth(990);
		this.setMaxHeight(720);
		this.setStyle("-fx-background-color: #DD734C; -fx-background-radius: 70;");
		this.setEffect(DROP_SHADOW);
		this.setTranslateX(TOX_SMALL_MENU);
		this.setStyle("-fx-background-color: transparent; -fx-border-color: blue; -fx-border-width: 5;");

	}

	public MenuContentHBox(Pane pane) {
		super();
		this.pane = pane;

		this.setMaxWidth(990);
		this.setMaxHeight(720);
		this.setStyle("-fx-background-color: #DD734C; -fx-background-radius: 70;");
		this.setEffect(DROP_SHADOW);
		this.setTranslateX(TOX_SMALL_MENU);
		this.setStyle("-fx-background-color: transparent; -fx-border-color: blue; -fx-border-width: 5;");
	}

	/**
	 * @return the pane
	 */
	public Pane getPane() {
		return pane;
	}

	
	/**
	 * @param gridPane the gridPane to set
	 */
	public void setPane(String[] gridPaneLabelsList) {

		// Retirer l'ancien GridPane
		this.getChildren().remove(this.pane);

		// Créer et définir un nouveau GridPane
		this.pane = createFormGridPane(gridPaneLabelsList);

		// Ajouter le nouveau GridPane à la VBox
		this.getChildren().add(1, this.pane);

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
