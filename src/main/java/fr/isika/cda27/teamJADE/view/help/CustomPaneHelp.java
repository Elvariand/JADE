package fr.isika.cda27.teamJADE.view.help;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.text.Font;

public class CustomPaneHelp extends TitledPane {

	private String paneTitle;
	private String paneText;

	public CustomPaneHelp(String paneTitle, String paneText) {
		super();

		Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/KronaOne-Regular.ttf"), 24);

		this.paneTitle = paneTitle; 
		this.paneText = paneText; 
		
		
		this.setText(paneTitle); 
		this.setStyle("-fx-font-family : 'Krona One'; -fx-font-size: 16px; -fx-text-fill : #FFFFFF; -fx-background-color: #454443; -fx-padding: 10px; -fx-background-radius: 13px;");
		
		TextArea textArea = new TextArea(paneText); 
		textArea.setWrapText(true); 
		textArea.textProperty().addListener(new ChangeListener<String>() {
	            @Override
	            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	                // Ajuster la hauteur du TextArea en fonction de son contenu
	                textArea.setPrefHeight(calculateTextAreaHeight(textArea));
	                
	            }
	        });
		
		this.setContent(textArea); 
		this.getContent().setStyle("-fx-font-family : 'Krona One'; -fx-font-size: 12px; -fx-text-fill : #272727; -fx-background-color: #DD734C; -fx-control-inner-background:#DD734C; -fx-padding: 10px; -fx-background-radius: 13px;");
		

	}
	  
	 private double calculateTextAreaHeight(TextArea textArea) {
	        // Taille de la ligne et du nombre de lignes
	        int numberOfLines = textArea.getText().split("\n").length;
	        double lineHeight = textArea.getFont().getSize() + 2; // Ajouter un petit padding
	        return numberOfLines * lineHeight + 20; // 20 pixels de padding supplémentaire
	    }

}
