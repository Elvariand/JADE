package fr.isika.cda27.teamJADE.view.help;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.text.Font;

public class CustomPaneHelp extends TitledPane {

	private String paneTitle;
	private String paneText;

	/**
	 * Crée un panneau personnalisé d'aide
	 * 
	 * @param paneTitle Le titre à afficher sur le panneau.
	 * @param paneText  Le texte à afficher dans la zone de texte du panneau.
	 */
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

	/**
	 * Calcule la hauteur nécessaire pour afficher le texte donné dans une zone de
	 * texte.
	 *
	 * @param paneText Le texte pour lequel la hauteur doit être calculée.
	 * @return La hauteur totale requise pour afficher le texte, basée sur le nombre
	 *         de lignes et une hauteur de ligne fixe.
	 */
	private double calculateTextAreaHeight(String paneText) {
		double lineHeight = 25;
		int chunkSize = 100;
		List<String> chunks = splitString(paneText, chunkSize);
		int numberOfLines = chunks.size();
		return lineHeight * numberOfLines;
	}

	/**
	 * Divise une chaîne de caractères en plusieurs morceaux de taille spécifiée.
	 *
	 * @param text      La chaîne de caractères à diviser.
	 * @param chunkSize La taille maximale de chaque morceau.
	 * @return Une liste de chaînes de caractères, chacune représentant un morceau
	 *         de la chaîne d'origine.
	 */
	public static List<String> splitString(String text, int chunkSize) {
		List<String> chunks = new ArrayList<>();

		int length = text.length();
		for (int i = 0; i < length; i += chunkSize) {
			chunks.add(text.substring(i, Math.min(length, i + chunkSize)));
		}

		return chunks;
	}

}
