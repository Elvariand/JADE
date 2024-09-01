package fr.isika.cda27.teamJADE.view.help;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
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
		
		TextArea textArea = new TextArea(paneText); 
		textArea.setWrapText(true);
		textArea.setEditable(false);
		
		double heightTA = calculateTextAreaHeight(paneText);
		textArea.setMinHeight(heightTA);
         
        
        this.setContent(textArea);
	}
	
	private double calculateTextAreaHeight(String paneText) {
        double lineHeight = 25; 
        int chunkSize = 100; 
        List<String> chunks = splitString(paneText, chunkSize);
        int numberOfLines = chunks.size();
        return lineHeight * numberOfLines;
    }
	
	public static List<String> splitString(String text, int chunkSize) {
		List<String> chunks = new ArrayList<>();
        
        int length = text.length();
        for (int i = 0; i < length; i += chunkSize) {
            chunks.add(text.substring(i, Math.min(length, i + chunkSize)));
        }
        
        return chunks;
    }

}
