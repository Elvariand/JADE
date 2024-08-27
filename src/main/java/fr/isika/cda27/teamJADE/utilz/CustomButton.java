package fr.isika.cda27.teamJADE.utilz;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CustomButton extends Button {
	private InnerShadow innerShadow;
	private Button button;
	private String text;
	
	/**
	 * @param innerShadow
	 * @param button
	 */
	public CustomButton(String text) {
		this.text = text;
		
		this.innerShadow =  new InnerShadow();
		innerShadow.setRadius(10);
		innerShadow.setOffsetX(5.0);
		innerShadow.setOffsetY(5.0);
		innerShadow.setColor(Color.web("#ffffff", 0.16));
		
		this.setPrefSize(190, 40);
		this.setStyle("-fx-background-color: #272727; -fx-background-radius: 13;");
		this.setTextFill(Color.web("#DD734C"));
		this.setFont(Font.font("Krona One", 18));
		this.setEffect(innerShadow);
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
	 * @return the innerShadow
	 */
	public InnerShadow getInnerShadow() {
		return innerShadow;
	}
	/**
	 * @param innerShadow the innerShadow to set
	 */
	public void setInnerShadow(InnerShadow innerShadow) {
		this.innerShadow = innerShadow;
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
