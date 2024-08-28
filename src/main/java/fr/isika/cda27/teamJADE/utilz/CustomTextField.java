package fr.isika.cda27.teamJADE.utilz;

import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.CustomTextFieldValues.*;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.ShadowSet.INNER_SHADOW;

public class CustomTextField extends TextField {

	private TextField textField;
	private InnerShadow innerShadow;

	/**
	 * @param textField
	 * @param innerShadow
	 */
	public CustomTextField() {
		this.setPrefHeight(TF_HEIGHT);
		this.setStyle("-fx-background-color : #DD734C; " + "-fx-background-radius: 13; " + "-fx-border-radius: 13; "
				+ "-fx-border-color: transparent transparent #704739 transparent;");
		this.setFont(Font.font("Krona One", 18)); 

		this.setEffect(INNER_SHADOW);

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



}
