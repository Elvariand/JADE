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

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.ShadowSet.*;

public class MainContentVbox extends VBox {

	private VBox mainContentVbox;
	private HBox buttonBox;
	private Button leftButton;
	private Button rightButton;
	private Label titleLabel;
	private VBox scopeScene;
	private ArrayList<String> gridPaneLabelsList;

	
	public MainContentVbox() {
		this.mainContentVbox = new VBox();
		mainContentVbox.setPrefSize(690, 720);
		mainContentVbox.setAlignment(Pos.CENTER);
		mainContentVbox.setTranslateX(200);

		this.titleLabel = new Label();
		titleLabel.setPrefSize(680, 100);
		titleLabel.setFont(Font.font("Krona One", 25));
		titleLabel.setStyle("-fx-alignment: center;");
		titleLabel.setTextFill(Color.web("#272727"));

		this.buttonBox = new HBox(100);
		buttonBox.setAlignment(javafx.geometry.Pos.CENTER);
		buttonBox.setPrefSize(680, 192);

		this.leftButton = new CustomButton("");

		this.rightButton = new CustomButton("");

		this.gridPaneLabelsList = new ArrayList();
		
		}

	/**
	 * @return the gridPaneLabelsList
	 */
	public ArrayList<String> getGridPaneLabelsList() {
		return gridPaneLabelsList;
	}

	/**
	 * @param gridPaneLabelsList the gridPaneLabelsList to set
	 */
	public void setGridPaneLabelsList(ArrayList<String> gridPaneLabelsList) {
		this.gridPaneLabelsList = gridPaneLabelsList;
	}

	
	/**
	 * @return the mainContentVbox
	 */
	public VBox getMainContentVbox() {
		return mainContentVbox;
	}

	/**
	 * @param mainContentVbox the mainContentVbox to set
	 */
	public void setMainContentVbox(VBox mainContentVbox) {
		this.mainContentVbox = mainContentVbox;
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
	public void setTitleLabel(String titleLabel) {
		this.titleLabel.setText(titleLabel);
	}

	
	
	
	
	
	/*
	 * 
	 * 
	 * LES FONCTIONS QU'IL RESTE A CHANGER EN CLASSES
	 * 
	 * 
	 * */
	
	
	
	
	public VBox createUpdateVbox() {

		// Label
		this.setTitleLabel("Modification du stagiaire");

		// GridPane
		GridPane gridPane = createFormGridPane();

		// button
		this.getLeftButton().setText("Annuler");
		this.getRightButton().setText("Modifier");

		buttonBox.getChildren().addAll(leftButton, rightButton);
		mainContentVbox.getChildren().addAll(titleLabel, gridPane, buttonBox);

		return mainContentVbox;
	}

	public VBox createPrintVbox() {

		// Label
		this.setTitleLabel("Souhaitez vous imprimer\nl'annuaire au format PDF ?");

		// button
		this.getLeftButton().setText("Non");
		this.getRightButton().setText("Oui");

		buttonBox.getChildren().addAll(leftButton, rightButton);
		mainContentVbox.getChildren().addAll(titleLabel, buttonBox);

		return mainContentVbox;
	}

	public VBox createSeeMembersVbox() {

		// Label
		this.setTitleLabel("Recherche avancée");

		// GridPane
		GridPane gridPane = createFormGridPane();

		// button
		this.getLeftButton().setText("Réinitialiser");
		this.getRightButton().setText("Rechercher");

		buttonBox.getChildren().addAll(leftButton, rightButton);
		mainContentVbox.getChildren().addAll(titleLabel, gridPane, buttonBox);

		return mainContentVbox;
	}

	public VBox createQuitVbox() {

		// Label
		this.setTitleLabel("Vous êtres sur le point de vous\ndéconnecter, souhaitez vous\npoursuivre ?");

		// button
		this.getLeftButton().setText("Non");
		this.getRightButton().setText("Oui");

		buttonBox.getChildren().addAll(leftButton, rightButton);
		mainContentVbox.getChildren().addAll(titleLabel, buttonBox);

		return mainContentVbox;
	}

	public GridPane createFormGridPane() {

		String[] labelTexts = { "Nom", "Prénom", "Adresse", "Ville", "Code postal" };

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
