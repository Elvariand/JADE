package fr.isika.cda27.teamJADE.utilz;

import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class FadingErrorLabel extends Label {

	private static final double FADE_DURATION = 3.0;
	private static final double FADE_START = 1.0;
	private static final double FADE_END = 0.0;

	/**
	 * Initialise le label avec le texte spécifié et configure les paramètres
	 * d'affichage pour l'effet de fondu.
	 * 
	 * @param text Le texte à afficher sur le label.
	 */
	public FadingErrorLabel(String text) {
		super(text);
		initialize();
	}

	/**
	 * Initialise les paramètres d'affichage du label.
	 */
	private void initialize() {
		setVisible(false);
		setOpacity(FADE_START);
	}

	/**
	 * Affiche le label avec un effet de fondu.
	 * 
	 * Le label devient visible et s'estompe progressivement pour devenir
	 * complètement transparent. Une fois le fondu terminé, le label devient
	 * invisible.
	 */
	public void show() {
		setVisible(true);
		FadeTransition fadeOut = new FadeTransition(Duration.seconds(FADE_DURATION), this);
		fadeOut.setFromValue(FADE_START);
		fadeOut.setToValue(FADE_END);
		fadeOut.setOnFinished(event -> setVisible(false));
		fadeOut.play();
	}
}
