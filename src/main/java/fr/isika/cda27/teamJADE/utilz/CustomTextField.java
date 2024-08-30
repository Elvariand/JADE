package fr.isika.cda27.teamJADE.utilz;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.CustomTextFieldValues.*;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.LABEL_ERROR_HEIGHT;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.LABEL_ERROR_WIDTH;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.ShadowSet.INNER_SHADOW_BLACK;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.Colors.*;

public class CustomTextField extends TextField {

	private InnerShadow innerShadow;
	private int MaxChars = 30;
	private HBox hboxError; 


	/**
	 * @param textField
	 * @param innerShadow
	 */
	public CustomTextField() {
		super();
		this.setPrefHeight(TF_HEIGHT);

		this.setStyle(SET_BG_ORANGE_COLOR + "-fx-background-radius: 13; " + "-fx-border-radius: 13; "
				+ "-fx-border-color: transparent transparent #704739 transparent;");
		this.setFont(Font.font("Krona One", 16));

		this.setEffect(INNER_SHADOW_BLACK);

		// L'utilisateur ne peut pas saisir plus de 30 caractères
		this.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue.length() > MaxChars) {
					setText(newValue.substring(0, MaxChars));
					if (hboxError != null) {
							hboxError.setVisible(true); 
					}
				}
			}

		});

	}



	public int getMaxChars() {
		return MaxChars;
	}



	public void setMaxChars(int maxChars) {
		MaxChars = maxChars;
	}



	public HBox getHboxError() {
		return hboxError;
	}

	public void setHboxError(HBox hboxError) {
		this.hboxError = hboxError;
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
