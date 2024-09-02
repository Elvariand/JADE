package fr.isika.cda27.teamJADE.view.login;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.Colors.GREY_COLOR;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.LABEL_ERROR_HEIGHT;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.LABEL_ERROR_WIDTH;
import fr.isika.cda27.teamJADE.model.MemberDao;
import fr.isika.cda27.teamJADE.model.Member;
import fr.isika.cda27.teamJADE.utilz.CustomButton;
import fr.isika.cda27.teamJADE.utilz.CustomTextField;
import fr.isika.cda27.teamJADE.view.mainIntern.InternsMainScene;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.util.Duration;

public class CustomLoginScene extends AnchorPane {

	private MemberDao memberDao = new MemberDao();
	private final int maxChars = 30;

	/**
	 * Crée la scène de connexion de l'application.
	 * 
	 * Lorsqu'un utilisateur entre des informations dans les champs, la méthode
	 * vérifie si les longueurs des champs dépassent la longueur maximale autorisée
	 * et ajuste le texte en conséquence. Les erreurs sont affichées si les
	 * informations de connexion sont invalides ou si la longueur des champs dépasse
	 * les limites.
	 *
	 */
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

		// Création VBoxRight qui contient l'image de fond et la Vbox de droite
		VBox vboxRight = new VBox();
		vboxRight.setPadding(new Insets(40, 0, 10, 20));
		vboxRight.setSpacing(40);
		vboxRight.setMaxWidth(600);
		vboxRight.setMaxHeight(720);

		Label labelConnexion = new Label("Connexion");
		labelConnexion.setStyle("-fx-font-family : 'Krona One'; -fx-font-size : 64px; -fx-text-fill :#272727; ");
		labelConnexion.setAlignment(Pos.CENTER);

		// Création HBox Authentification Invalide - Conditionnel
		HBox hboxInvalide = new HBox(15);
		ImageView imageInvalide = new ImageView(new Image("file:src/main/resources/img/logoAttention.png"));
		imageInvalide.setFitHeight(40);
		imageInvalide.setFitWidth(40);
		imageInvalide.setPreserveRatio(true);
		Label labelInvalide = new Label("Nom d'utilisateur ou mot de passe invalide");
		labelInvalide.setStyle("-fx-font-family : 'Krona One'; " + "-fx-font-size:16px; " + "-fx-text-fill :#272727; ");
		hboxInvalide.getChildren().addAll(imageInvalide, labelInvalide);
		hboxInvalide.setAlignment(Pos.CENTER_LEFT);
		hboxInvalide.setVisible(false);

		// Création VBox Alias
		VBox vboxAlias = new VBox(5);
		Label labelAlias = new Label("Nom d'utilisateur");
		labelAlias.setStyle("-fx-font-family : 'Krona One'; -fx-font-size : 16px; -fx-text-fill :#272727; ");
		CustomTextField aliasField = new CustomTextField();
		aliasField.setPrefWidth(100);

		// Création VBox Password
		VBox vboxPassword = new VBox(5);
		Label labelPassword = new Label("Mot de passe");
		labelPassword.setStyle("-fx-font-family : 'Krona One'; -fx-font-size : 16px; -fx-text-fill :#272727; ");

		// Création du passwordField
		PasswordField passwordField = new PasswordField();
		passwordField.setPrefWidth(300);
		passwordField.setPrefHeight(35);
		passwordField.setStyle("-fx-background-color : #DD734C; " + "-fx-background-radius: 13; "
				+ "-fx-border-radius: 13; " + "-fx-border-color: transparent transparent #704739 transparent;");
		passwordField.setFont(Font.font("Krona One", 16));
		InnerShadow innerShadowPassword = new InnerShadow();
		innerShadowPassword.setRadius(23.93);
		innerShadowPassword.setOffsetX(2.0);
		innerShadowPassword.setOffsetY(2.0);
		innerShadowPassword.setColor(Color.web("#000000", 0.1));
		passwordField.setEffect(innerShadowPassword);
		passwordField.setSkin(new VisiblePasswordFieldSkin(passwordField));

		// Création HBox ErrorChars composé d'une image et d'un texte - Conditionnel
		HBox hboxErrorChars = new HBox();
		ImageView imageAttention = new ImageView(new Image("file:src/main/resources/img/logoAttention.png"));
		imageAttention.setFitHeight(40);
		imageAttention.setFitWidth(40);
		imageAttention.setPreserveRatio(true);
		Label labelErrorChars = new Label("Le nombre de caractères autorisé est dépassé");
		labelErrorChars.setPrefSize(LABEL_ERROR_WIDTH, LABEL_ERROR_HEIGHT);
		labelErrorChars.setStyle("-fx-font-family : 'Krona One'; " + "-fx-font-size:16px; " + "-fx-text-fill :#272727; "
				+ "-fx-alignment: center;");
		labelErrorChars.setTextFill(GREY_COLOR);
		hboxErrorChars.getChildren().addAll(imageAttention, labelErrorChars);
		hboxErrorChars.setAlignment(Pos.CENTER_LEFT);
		hboxErrorChars.setVisible(false);

		aliasField.setHboxError(hboxErrorChars);

		// Ajout des éléments
		vboxAlias.getChildren().addAll(labelAlias, aliasField);
		vboxPassword.getChildren().addAll(labelPassword, passwordField);

		// L'utilisateur ne peut pas saisir plus de 30 caractères pour le mot de passe
		// et le nom d'utilisateur
		passwordField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue.length() > maxChars) {
					passwordField.setText(newValue.substring(0, maxChars));
				}
				checkIfError(hboxErrorChars, aliasField.getText(), newValue);
			}
		});
		aliasField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue.length() > maxChars) {
					aliasField.setText(newValue.substring(0, maxChars));
				}
				checkIfError(hboxErrorChars, newValue, passwordField.getText());
			}
		});

		// Création bouton Valider
		CustomButton btnValider = new CustomButton("Valider");

		// Gestionnaire d'évènement pour le bouton Valider
		btnValider.setOnAction(event -> handleLogin(aliasField.getText(), passwordField.getText(), hboxInvalide));

		vboxRight.setAlignment(Pos.CENTER);
		vboxRight.getChildren().addAll(labelConnexion, hboxInvalide, vboxAlias, vboxPassword, hboxErrorChars,
				btnValider);

		// Valider via la touche Entrée, après avoir renseigné le password !
		passwordField.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				handleLogin(aliasField.getText(), passwordField.getText(), hboxInvalide);
			}
		});

		this.getChildren().addAll(fondConnexion, vboxRight);

		AnchorPane.setTopAnchor(vboxRight, 30.0);
		AnchorPane.setRightAnchor(vboxRight, 30.0);
	}

	/**
	 * Vérifie si le nom d'utilisateur ou le mot de passe dépassent la longueur
	 * maximale autorisée et met à jour la visibilité d'un conteneur d'erreurs en
	 * conséquence.
	 * 
	 * @param hboxErrorChars Le conteneur d'erreurs dont la visibilité sera modifiée
	 *                       en fonction de la longueur du nom d'utilisateur et du
	 *                       mot de passe.
	 * @param alias          Le nom d'utilisateur dont la longueur sera vérifiée.
	 * @param password       Le mot de passe dont la longueur sera vérifiée.
	 */
	public void checkIfError(HBox hboxErrorChars, String alias, String password) {
		boolean aliasTooLong = alias.length() > maxChars - 1;
		boolean passwordTooLong = password.length() > maxChars - 1;

		if (aliasTooLong || passwordTooLong) {
			hboxErrorChars.setVisible(true);
		} else {
			hboxErrorChars.setVisible(false);
		}
	}

	/**
	 * Gère le processus de connexion en vérifiant les informations d'identification
	 * fournies et en mettant à jour l'interface utilisateur en conséquence.
	 *
	 * @param alias        Le nom d'utilisateur fourni pour la connexion.
	 * @param password     Le mot de passe fourni pour la connexion.
	 * @param hboxInvalide Le conteneur d'erreurs à afficher si les informations de
	 *                     connexion sont invalides.
	 */
	public void handleLogin(String alias, String password, HBox hboxInvalide) {
		Member member = memberDao.findByAlias(alias, password);
		// si member non null alors on affiche la mainScene
		if (member != null) {
			Stage stage = ((Stage) CustomLoginScene.this.getScene().getWindow());
			Scene scene = new Scene(new InternsMainScene(member));
			scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
			stage.setScene(scene);
		} else {
			hboxInvalide.setVisible(true);
			FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), hboxInvalide);
			fadeOut.setFromValue(1);
			fadeOut.setToValue(0);
			fadeOut.setOnFinished(event -> hboxInvalide.setVisible(false));
			fadeOut.play();
		}
	}

}
