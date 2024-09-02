package fr.isika.cda27.teamJADE.utilz;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.CustomButtonValues.*;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.ShadowSet.*;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.Colors.*;

public class CustomButton extends Button {
	private Button button;
	private String text;

	/**
	 * Crée un groupe de bouton classique
	 *
	 * @param text Le texte à afficher sur le bouton.
	 */
	public CustomButton(String text) {
		this.text = text;

		this.setPrefSize(BTN_WIDTH, BTN_HEIGHT);

		this.setStyle(SET_BG_GREY_COLOR + " -fx-background-radius: 13;");
		this.setTextFill(ORANGE_COLOR);
		this.setFont(Font.font("Krona One", 18));
		this.setEffect(INNER_SHADOW_WHITE);

		this.setText(this.text);

		this.setOnMouseEntered(event -> {
			this.setStyle("-fx-background-color: #333232; -fx-background-radius: 13;");
			this.setTextFill(Color.web("#EC8A66"));
		});

		this.setOnMouseExited(event -> {
			this.setStyle("-fx-background-color: #272727; -fx-background-radius: 13;");
			this.setTextFill(ORANGE_COLOR);
		});

	}

	/**
	 * @return Le bouton 
	 */
	public Button getButton() {
		return button;
	}

	/**
	 * @param button Le bouton à définir
	 */
	public void setButton(Button button) {
		this.button = button;
	}

}
