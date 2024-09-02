package fr.isika.cda27.teamJADE.view.help;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.CustomButtonValues.BTN_HEIGHT;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.CustomButtonValues.BTN_WIDTH;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.ShadowSet.INNER_SHADOW_WHITE;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.HelpTextValues.*;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HelpSceneAdmin extends AnchorPane {

	private TitledPane pane;
	private TextArea textArea;
	private AnchorPane rootForReturnBtn;

	/**
	 * Crée une scène d'aide pour l'administrateur
	 * 
	 * @param rootForReturnBtn La racine de la scène à afficher lorsque
	 *                         l'utilisateur clique sur le bouton "Retour".
	 */
	public HelpSceneAdmin(AnchorPane rootForReturnBtn) {
		this.setPrefSize(1280, 720);

		this.rootForReturnBtn = rootForReturnBtn;

		Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/KronaOne-Regular.ttf"), 24);

		// Création des éléments de l'accordeon
		CustomPaneHelp paneIntroduction = new CustomPaneHelp(INTRODUCTION, INTRODUCTION_TEXT);
		CustomPaneHelp paneRecherche = new CustomPaneHelp(RECHERCHE, RECHERCHE_TEXT);
		CustomPaneHelp paneAjoutStagiaire = new CustomPaneHelp(AJOUT_STAGIAIRE, AJOUT_STAGIAIRE_TEXT);
		CustomPaneHelp paneSuppressionStagiaire = new CustomPaneHelp(SUPPRESSION_STAGIAIRE, SUPRESSION_STAGAIRE_TEXT);
		CustomPaneHelp paneModificationStagiaire = new CustomPaneHelp(MODIFICATION_STAGIAIRE,
				MODIFICATION_STAGIAIRE_TEXT);
		CustomPaneHelp paneMembres = new CustomPaneHelp(MEMBRES, MEMBRES_TEXT);
		CustomPaneHelp paneImpression = new CustomPaneHelp(IMPRESSION, IMPRESSION_TEXT);
		CustomPaneHelp paneDeconnexion = new CustomPaneHelp(DECONNEXION, DECONNEXION_TEXT);

		// On crée l'accordeon
		Accordion accordionHelp = new Accordion();
		accordionHelp.getPanes().addAll(paneIntroduction, paneRecherche, paneImpression, paneAjoutStagiaire,
				paneSuppressionStagiaire, paneModificationStagiaire, paneMembres, paneDeconnexion);
		accordionHelp.setStyle("-fx-background-color: #272727;");

		ScrollPane scrollPane = new ScrollPane(accordionHelp);
		scrollPane.setPrefHeight(500);
		scrollPane.setFitToWidth(true);
		scrollPane.setFitToHeight(true);

		Label bienvenue = new Label("Bienvenue dans le centre d'aide de votre annuaire en ligne");
		bienvenue.setStyle("-fx-font-family : 'Krona One'; -fx-font-size : 24px; -fx-text-fill : 'FFFFFF'");
		bienvenue.setAlignment(Pos.CENTER);
		bienvenue.setPadding(new Insets(0, 0, 30, 0));

		// Création du bouton Retour
		HBox hboxBtnRetourHelp = new HBox();
		Button btnRetourHelp = new Button("Retour");
		// Stylisation du bouton Retour
		btnRetourHelp.setPrefSize(BTN_WIDTH, BTN_HEIGHT);
		btnRetourHelp.setStyle(
				"-fx-background-color: #DD734C; -fx-background-radius: 13; fx-text-fill : '#272727'; -fx-font-family : 'Krona One'; -fx-font-size : 18px;");
		btnRetourHelp.setEffect(INNER_SHADOW_WHITE);
		hboxBtnRetourHelp.setAlignment(Pos.BOTTOM_RIGHT);
		hboxBtnRetourHelp.getChildren().add(btnRetourHelp);
		VBox.setMargin(hboxBtnRetourHelp, new Insets(0, 10, 0, 0));

		// Hover Effect sur le bouton Retour
		btnRetourHelp.setOnMouseEntered(e -> btnRetourHelp.setStyle(
				"-fx-background-color: #F39471; -fx-background-radius: 13; fx-text-fill : '#272727'; -fx-font-family : 'Krona One'; -fx-font-size : 18px;"));
		btnRetourHelp.setOnMouseExited(e -> btnRetourHelp.setStyle(
				"-fx-background-color: #DD734C; -fx-background-radius: 13; fx-text-fill : '#272727'; -fx-font-family : 'Krona One'; -fx-font-size : 18px;"));

		// Action du bouton Retour : retourne à la mainScene
		btnRetourHelp.setOnAction(event -> {
			Stage stage = ((Stage) HelpSceneAdmin.this.getScene().getWindow());
			Scene scene = new Scene(rootForReturnBtn);
			scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
			stage.setScene(scene);
		});

		// Mettre l'Accordion dans une VBox pour l'ajuster dans la scène
		VBox vboxHelp = new VBox(30);
		vboxHelp.setPrefSize(900, 720);
		vboxHelp.getChildren().addAll(bienvenue, scrollPane, hboxBtnRetourHelp);
		vboxHelp.setStyle("-fx-background-color:#272727;");
		vboxHelp.setAlignment(Pos.CENTER);

		// Création de la StackPane qui contient la VBox
		StackPane StackPaneHelp = new StackPane();
		StackPaneHelp.setPrefSize(1280, 720);
		StackPaneHelp.setPadding(new Insets(30, 20, 30, 30));
		StackPaneHelp.setStyle("-fx-background-color: #272727;");
		StackPaneHelp.getChildren().addAll(vboxHelp);

		this.getChildren().add(StackPaneHelp);

	}

}
