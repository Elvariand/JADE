package fr.isika.cda27.teamJADE.view;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.CustomButtonValues.BTN_HEIGHT;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.CustomButtonValues.BTN_WIDTH;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.ShadowSet.INNER_SHADOW_WHITE;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.HelpTextValues.*;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HelpScene extends AnchorPane {
	
	private TitledPane pane; 
	private TextArea textArea; 
	

   
    public HelpScene() {
    	this.setPrefSize(1280, 720);
    	
    	
    	
    	Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/KronaOne-Regular.ttf"), 24);
    	       
    	// Créez les éléments 
    	
//        TitledPane pane5 = new TitledPane("Support et Assistance", 
//                new Label("Pour toute aide, demandez à Jason")); 
//        
//        TitledPane pane6 = new TitledPane("Sécurité et confidentialité", 
//                new Label("L'annuaire est sécurisé et restreints aux membres autorisés."+ "\n" + 
//                		"Les informations des stagiaires sont soumis à notre politique de confidentialité")); 
//        
//        TitledPane pane7 = new TitledPane("Mises à jour", 
//                new Label("Notifications et impacts des mises à jour : les mises à jours vous seront notifié par e-mail la veille, ce qui implquera un accès réduit à l'annuaire.")); 
//        
//        TitledPane pane8 = new TitledPane("Mentions légales", 
//                new Label("Droits des utilisateurs et limites de responsabilité"));
        

        // Créez un Accordion pour contenir les éléments TitledPane
     
    	CustomPaneHelp paneIntroduction = new CustomPaneHelp(INTRODUCTION, INTRODUCTION_TEXT);
        CustomPaneHelp paneGestionStagiaire = new CustomPaneHelp(GESTION_STAGIAIRES, null); 
        CustomPaneHelp paneAjoutStagiaire = new CustomPaneHelp(AJOUT_STAGIAIRE, AJOUT_STAGIAIRE_TEXT); 
        CustomPaneHelp paneSuppressionStagiaire = new CustomPaneHelp(SUPPRESSION_STAGIAIRE, SUPRESSION_STAGAIRE_TEXT); 
        CustomPaneHelp paneModificationStagiaire = new CustomPaneHelp(MODIFICATION_STAGIAIRE, MODIFICATION_STAGIAIRE_TEXT); 
        CustomPaneHelp paneMembres = new CustomPaneHelp(MEMBRES, MEMBRES_TEXT); 
        CustomPaneHelp paneImpression= new CustomPaneHelp(IMPRESSION, IMPRESSION_TEXT); 
    	
    	
    	// On cré le sous accordeon Gestion des stagiaire
    	 Accordion gestionAccordion = new Accordion(); 
    	 
    	 // On ajouter les 3 custumPaneHelp à l'accordeon principal
    	 
    	 gestionAccordion.getPanes().addAll(paneAjoutStagiaire, paneSuppressionStagiaire, paneModificationStagiaire); 	
    	 paneGestionStagiaire.setContent(gestionAccordion); 
    	 
    	 // On crée l'accordeon principal
    	 Accordion accordionHelp = new Accordion(); 
    	 accordionHelp.getPanes().addAll(paneIntroduction, paneGestionStagiaire, paneMembres, paneImpression); 
    	
    	         
        Label bienvenue = new Label("Bienvenue dans le centre d'aide de votre annuaire en ligne"); 
        bienvenue.setStyle("-fx-font-family : 'Krona One'; -fx-font-size : 24px; -fx-text-fill : 'FFFFFF'; -fx-alignment: center; -fx-padding: 10px;");
       
                              
        HBox hboxBtnRetourHelp = new HBox (); 
        Button btnRetourHelp = new Button("Retour");
        
        btnRetourHelp.setPrefSize(BTN_WIDTH, BTN_HEIGHT);
        btnRetourHelp.setStyle("-fx-background-color: #DD734C; -fx-background-radius: 13; fx-text-fill : '#272727'; -fx-font-family : 'Krona One'; -fx-font-size : 18px;");
        btnRetourHelp.setEffect(INNER_SHADOW_WHITE);
        hboxBtnRetourHelp.getChildren().add(btnRetourHelp); 
        hboxBtnRetourHelp.setAlignment(Pos.BOTTOM_RIGHT);
        
       // Hover Effect sur le bouton Retour
        btnRetourHelp.setOnMouseEntered(e -> btnRetourHelp.setStyle("-fx-background-color: #F39471; -fx-background-radius: 13; fx-text-fill : '#272727'; -fx-font-family : 'Krona One'; -fx-font-size : 18px;")); 
        btnRetourHelp.setOnMouseExited(e -> btnRetourHelp.setStyle("-fx-background-color: #DD734C; -fx-background-radius: 13; fx-text-fill : '#272727'; -fx-font-family : 'Krona One'; -fx-font-size : 18px;")); 
       
        // Action du bouton Retour : retourne à la mainScene
        btnRetourHelp.setOnAction(event -> {
        	Stage stage = ((Stage) HelpScene.this.getScene().getWindow()); 
        	Scene scene = new Scene(new CustomMainScene()); 
        	scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
			stage.setScene(scene);
        }); 
           
       // Mettre l'Accordion dans une VBox pour l'ajuster dans la scène
       VBox vboxHelp = new VBox(20);
       vboxHelp.setPrefSize(1080, 720); 
       vboxHelp.getChildren().addAll(bienvenue,accordionHelp, hboxBtnRetourHelp); 
       vboxHelp.setStyle("-fx-background-color:#272727"); 
   
        StackPane StackPaneHelp = new StackPane();
        StackPaneHelp.setPrefSize(1280, 720);
        StackPaneHelp.setStyle("-fx-background-color: #272727;");
        StackPaneHelp.getChildren().addAll(vboxHelp); 
      
        this.getChildren().add(StackPaneHelp); 
        
    }

	
}


      