package fr.isika.cda27.teamJADE.utilz;

import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class FadingErrorLabel extends Label {
	
	 private static final double FADE_DURATION = 3.0;
	 private static final double FADE_START = 1.0;
	 private static final double FADE_END = 0.0;

	    public FadingErrorLabel(String text) {
	        super(text);
	        initialize();
	    }

	    private void initialize() {
	        setVisible(false);
	        setOpacity(FADE_START);
	    }

	    public void show() {
	        setVisible(true);
	        FadeTransition fadeOut = new FadeTransition(Duration.seconds(FADE_DURATION), this);
	        fadeOut.setFromValue(FADE_START);
	        fadeOut.setToValue(FADE_END);
	        fadeOut.setOnFinished(event -> setVisible(false));
	        fadeOut.play();
	    }
}
