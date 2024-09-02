package fr.isika.cda27.teamJADE.utilz;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.CustomTextFieldValues.*;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.ShadowSet.INNER_SHADOW_BLACK;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.Colors.*;

public class CustomTextField extends TextField {

	private int MaxChars = 30;
	private HBox hboxError; 


	/**
	 * 
	 * Crée un champ de texte personnalisé 
	 * Si l'utilisateur saisit plus de caractères, le texte est tronqué à la longueur maximale 
	 * et une zone d'erreur (si disponible) est rendue visible.
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


	/**
	 * @return Le MaxChars
	 */
	public int getMaxChars() {
		return MaxChars;
	}


	/**
	 * @param maxChars Le maxChars à définir
	 */
	public void setMaxChars(int maxChars) {
		MaxChars = maxChars;
	}


	/**
	 * @return La hboxError
	 */
	public HBox getHboxError() {
		return hboxError;
	}
	/**
	 * @param hboxError Le hboxError à définir
	 */
	public void setHboxError(HBox hboxError) {
		this.hboxError = hboxError;
	}

}
