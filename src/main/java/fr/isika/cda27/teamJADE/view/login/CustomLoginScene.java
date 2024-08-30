package fr.isika.cda27.teamJADE.view.login;

import fr.isika.cda27.teamJADE.model.Member;
import fr.isika.cda27.teamJADE.utilz.CustomButton;
import fr.isika.cda27.teamJADE.utilz.CustomTextField;
import fr.isika.cda27.teamJADE.view.mainIntern.CustomMainScene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CustomLoginScene extends AnchorPane{
	
	private Member member = new Member("user", "pass");


	public CustomLoginScene() {
	

		
		// Police de texte
		Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/KronaOne-Regular.ttf"), 24);

		// AnchorPane Login
				
		this.setPrefSize(1280, 720);

		// Image Fond de connexion

		ImageView fondConnexion = new ImageView("file:src/main/resources/img/fondConnexion.png");
		fondConnexion.setFitWidth(1280);
		fondConnexion.setFitHeight(720);
		fondConnexion.setPreserveRatio(true);

		// Création VBoxRight
		VBox vboxRight = new VBox();
		vboxRight.setPadding(new Insets(40, 40, 10, 20));
		vboxRight.setSpacing(60);

		Label labelConnexion = new Label("Connexion");
		labelConnexion.setStyle("-fx-font-family : 'Krona One'; -fx-font-size : 64px; -fx-text-fill :#272727; ");
		labelConnexion.setAlignment(Pos.CENTER);

		// Création HBox Invalide - Conditionnel
		HBox hboxInvalide = new HBox(15);
		ImageView imageInvalide = new ImageView(new Image("file:src/main/resources/img/logoAttention.png"));
		imageInvalide.setFitHeight(40);
		imageInvalide.setFitWidth(40);
		imageInvalide.setPreserveRatio(true);
		Label labelInvalide = new Label("Nom d'utilisateur ou mot de passe invalide");
		labelInvalide.setStyle("-fx-font-family : 'Krona One'; " + "-fx-font-size:18px; " + "-fx-text-fill :#272727; ");
		hboxInvalide.getChildren().addAll(imageInvalide, labelInvalide);
		hboxInvalide.setAlignment(Pos.CENTER_LEFT);
		hboxInvalide.setVisible(false);

		// Création VBox Alias
		VBox vboxAlias = new VBox(5);
		Label labelAlias = new Label("Nom d'utilisateur");
		labelAlias.setStyle("-fx-font-family : 'Krona One'; -fx-font-size : 18px; -fx-text-fill :#272727; ");
		CustomTextField aliasField = new CustomTextField();
		aliasField.setPrefWidth(300);
		vboxAlias.getChildren().addAll(labelAlias, aliasField);

		// Création VBox Password
		VBox vboxPassword = new VBox(5);
		vboxPassword.setPadding(new Insets(0, 0, 60, 0));
		Label labelPassword = new Label("Mot de passe");
		labelPassword.setStyle("-fx-font-family : 'Krona One'; -fx-font-size : 18px; -fx-text-fill :#272727; ");

		// Création du passwordField
		PasswordField passwordField = new PasswordField();
//		passwordField.setSkin(new VisiblePasswordFieldSkin(passwordField));
		passwordField.setPrefWidth(300);
		passwordField.setPrefHeight(35);
		passwordField.setStyle("-fx-background-color : #DD734C; " + "-fx-background-radius: 13; "
				+ "-fx-border-radius: 13; " + "-fx-border-color: transparent transparent #704739 transparent;");
		passwordField.setFont(Font.font("Krona One", 18));
		InnerShadow innerShadowPassword = new InnerShadow();
		innerShadowPassword.setRadius(23.93);
		innerShadowPassword.setOffsetX(2.0);
		innerShadowPassword.setOffsetY(2.0);
		innerShadowPassword.setColor(Color.web("#000000", 0.1));
		passwordField.setEffect(innerShadowPassword);
		passwordField.setSkin(new VisiblePasswordFieldSkin(passwordField));

		vboxPassword.getChildren().addAll(labelPassword, passwordField);

		// Valider via la touche Entrée, après avoir renseigné le password !
		passwordField.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				handleLogin(aliasField.getText(), passwordField.getText(), hboxInvalide);
			}
		});

		// Création bouton Valider

		CustomButton btnValider = new CustomButton("Valider");

		// Gestionnaire d'évènement pour le bouton Valider
		btnValider
				.setOnAction(event -> handleLogin(aliasField.getText(), passwordField.getText(), hboxInvalide));

//	btnValider.setDefaultButton(true); 

		vboxRight.setAlignment(Pos.CENTER);
		vboxRight.getChildren().addAll(labelConnexion, hboxInvalide, vboxAlias, vboxPassword, btnValider);
		this.getChildren().addAll(fondConnexion, vboxRight);

		AnchorPane.setTopAnchor(vboxRight, 30.0);
		AnchorPane.setRightAnchor(vboxRight, 20.0);
			}

	public void handleLogin(String alias, String password, HBox hboxInvalide) {

		if (member.authenticate(alias, password)) {
			Stage stage = ((Stage)CustomLoginScene.this.getScene().getWindow());
			Scene scene = new Scene(new CustomMainScene()); 
			scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
			stage.setScene(scene);
		} else {
			hboxInvalide.setVisible(true);
		}
	}

	


}
