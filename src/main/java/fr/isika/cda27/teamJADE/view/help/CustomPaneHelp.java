package fr.isika.cda27.teamJADE.view.help;

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
		this.paneText =paneText; 
		
		
		this.setText(paneTitle); 
		this.setStyle("-fx-font-family : 'Krona One'; -fx-font-size: 18px; -fx-text-fill : #FFFFFF; -fx-background-color: #454443; -fx-padding: 10px; -fx-background-radius: 13px;");
		
		TextArea textArea = new TextArea(paneText); 
		textArea.setWrapText(true); 
		
		this.setContent(textArea); 
				this.getContent().setStyle("-fx-font-family : 'Krona One'; -fx-font-size: 13px; -fx-text-fill : #272727; -fx-background-color: #DD734C; -fx-padding: 10px; -fx-background-radius: 13px;");
		

	}
	  
	

}
