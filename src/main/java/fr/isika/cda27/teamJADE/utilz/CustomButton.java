package fr.isika.cda27.teamJADE.utilz;

import javafx.scene.control.Button;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.CustomButtonValues.*;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.ShadowSet.*;

public class CustomButton extends Button {
	private Button button;
	private String text;
	
	/**
	 * @param innerShadow
	 * @param button
	 */
	public CustomButton(String text) {
		this.text = text;
		
		this.setPrefSize(BTN_WIDTH, BTN_HEIGHT);
		this.setStyle("-fx-background-color: #272727; -fx-background-radius: 13;");
		this.setTextFill(Color.web("#DD734C"));
		this.setFont(Font.font("Krona One", 18));
		this.setEffect(INNER_SHADOW);
		this.setText(text);
		
		this.setOnMouseEntered(event -> {
			this.setStyle("-fx-background-color: #333232; -fx-background-radius: 13;");
			this.setTextFill(Color.web("#EC8A66"));
		});
		
		this.setOnMouseExited(event -> {
			this.setStyle("-fx-background-color: #272727; -fx-background-radius: 13;");
			this.setTextFill(Color.web("#DD734C"));
		});
		
	}

	/**
	 * @return the button
	 */
	public Button getButton() {
		return button;
	}
	/**
	 * @param button the button to set
	 */
	public void setButton(Button button) {
		this.button = button;
	}
	
}
