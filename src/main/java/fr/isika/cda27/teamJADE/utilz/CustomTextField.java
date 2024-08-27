package fr.isika.cda27.teamJADE.utilz;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CustomTextField extends TextField {

	private TextField textField;
	private InnerShadow innerShadow;
//	private PasswordField passwordField; 

	/**
	 * @param textField
	 * @param innerShadow
	 */
	public CustomTextField() {
//		this.setPasswordField(new PasswordField()); 
		this.setPrefHeight(35);
		this.setStyle("-fx-background-color : #DD734C; " + "-fx-background-radius: 13; " + "-fx-border-radius: 13; "
				+ "-fx-border-color: transparent transparent #704739 transparent;");
		this.setFont(Font.font("Krona One", 18)); 


		this.innerShadow = new InnerShadow();
		innerShadow.setRadius(23.93);
		innerShadow.setOffsetX(2.0);
		innerShadow.setOffsetY(2.0);
		innerShadow.setColor(Color.web("#000000", 0.1));
		this.setEffect(innerShadow);

	}
	
	

	/**
	 * @return the textField
	 */
	public TextField getTextField() {
		return textField; 
	}

	/**
	 * @param textField the textField to set
	 */
	public void setTextField(TextField textField) {
		this.textField = textField;
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
		this.setEffect(innerShadow);
	}



//	public PasswordField getPasswordField() {
//		return passwordField;
//	}
//
//
//
//	public void setPasswordField(PasswordField passwordField) {
//		this.passwordField = passwordField;
//	}
	

}
