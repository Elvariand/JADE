package fr.isika.cda27.teamJADE.utilz;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.ShadowSet.INNER_SHADOW_BLACK;

import fr.isika.cda27.teamJADE.view.login.VisiblePasswordFieldSkin;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.CustomTextFieldValues.*;

public class CustomPasswordField extends PasswordField {

	private int MaxChars = 30;
	private HBox hboxError;
	/**
	 * Crée le champ du mot de passe.
	 * 
	 * Si l'utilisateur tente de saisir plus de caractères, le texte est tronqué et une zone d'erreur 
	 * (si disponible) est rendue visible.
	 */
	public CustomPasswordField() {

		this.setPrefHeight(TF_HEIGHT);

		this.setStyle("-fx-background-color : #DD734C; " + "-fx-background-radius: 13; " + "-fx-border-radius: 13; "
				+ "-fx-border-color: transparent transparent #704739 transparent;");
		this.setFont(Font.font("Krona One", 18));

		this.setEffect(INNER_SHADOW_BLACK);

		this.setSkin(new VisiblePasswordFieldSkin(this));

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

}
