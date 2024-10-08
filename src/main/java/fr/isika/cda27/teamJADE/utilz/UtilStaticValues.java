package fr.isika.cda27.teamJADE.utilz;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.Colors.GREY_COLOR;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
/**
 * Cette classe contient des valeurs statiques utilisées à travers l'application.
 * Les valeurs sont regroupées en sous-classes pour faciliter leur organisation.
 */
public class UtilStaticValues {

	public static class Colors {

		public static final String SET_BG_GREY_COLOR = "-fx-background-color: #272727;";
		public static final String SET_BG_ORANGE_COLOR = "-fx-background-color: #DD734C;";
		public static final Color HOVER_ORANGE_COLOR = Color.web("#F39471");
		public static final Color HOVER_GREY_COLOR = Color.web("#4E4E4E");
		public static final String STRING_ORANGE_COLOR = "#DD734C";
		public static final String STRING_GREY_COLOR = "#272727";
		public static final Color ORANGE_COLOR = Color.web(STRING_ORANGE_COLOR);
		public static final Color GREY_COLOR = Color.web(STRING_GREY_COLOR);
	}

	public static class MainSceneValues {

		public static final int TOX_SMALL_MENU = -1035;
		public static final int TOX_MEDIUM_MENU = -850;
		public static final int TOX_LARGE_MENU = -350;

		public static final int MENUBAR_WIDTH = 100;
		public static final int MENUBAR_HEIGHT = 720;
		public static final int TOX_MENUBAR = 200;

		public static final int TOX_SMALL_BTN = 0;
		public static final int TOX_LARGE_BTN = -100;

		public static final int BTN_HEIGHT = 87;
		public static final int BTN_LARGE_WIDTH = 300;
		public static final int BTN_SMALL_WIDTH = 100;

		public static final String PATH_TOP = "M98 69.5002C98 23.8722 61 -7.5 0.5 0L0.5 97.0002L98 97.0002L98 69.5002Z";
		public static final String LARGE_PATH_BOT = "M5.02681e-06 0L300 4.58562e-07L300 19.5L300 47.5C300 49 299.838 52.4703 299 57.5C298 63.5 295 73 292 78C289 83 286.5 88 280 94.5C273.5 101 264.5 107 253.5 111C244.7 114.2 237 115 231 115L5.02681e-06 115L5.02681e-06 0Z";
		public static final String SMALL_PATH_BOT = "M5.02681e-06 0L100 0L100 19.5L100 47.5C100 49 99.8383 52.4704 99 57.5C98 63.5 95 73 92 78C89 83 86.5 88 80 94.5C73.5 101 64.5 107 53.5 111C44.7 114.2 37 115 31 115L5.02681e-06 115L5.02681e-06 0Z";

		public static final Duration DURATION_TIME = Duration.millis(500);
	}

	public static class MenuVboxValues {


		public static final String[] LABEL_TEXTS = { "Nom de famille", "Prénom", "Département", "Formation suivie",
				"Année d'entrée" };
		public static final String LABEL_ERROR_FAM_NAME = "Cet espace est limité à 30 caractères. Seuls les lettres avec ou sans accent, les traits d'union et les espaces sont autorisés.";
		public static final String LABEL_ERROR_FIRST_NAME = "Cet espace est limité à 30 caractères. Seuls les lettres avec ou sans accent, les traits d'union et les espaces sont autorisés.";
		public static final String LABEL_ERROR_COUNTY = "Cet espace est limité à 3 caractères. Seuls les chiffres sont autorisés.";
		public static final String LABEL_ERROR_CURSUS = "Cet espace est limité à 10 caractères. Seuls les lettres avec ou sans accent, les espaces et les chiffres sont autorisés.";
		public static final String LABEL_ERROR_YEARIN = "Cet espace est limité à 4 caractères. Seuls les chiffres sont autorisés.";
		public static final String[] LABEL_ERRORS = { LABEL_ERROR_FAM_NAME, LABEL_ERROR_FIRST_NAME, LABEL_ERROR_COUNTY,
				LABEL_ERROR_CURSUS, LABEL_ERROR_YEARIN };


		public static final String[] LABEL_TEXTS_MEMBERS = { "Nom de famille", "Prénom", "Pseudo", "Mail", "Administrateur", "Mot de passe"};
		public static final String LABEL_ERROR_ALIAS = "Cet espace est limité à 15 caractères. Seuls les lettres avec ou sans accent, les traits d'union et les chiffres sont autorisés.";
		public static final String LABEL_ERROR_EMAIL = "L'adresse entrée n'est pas valide ou dépasse le nombre maximal de caractères autorisés.";
		public static final String LABEL_ERROR_PASSWORD = "Cet espace est limité à 15 caractères. Seuls les lettres avec ou sans accent, les traits d'union et les chiffres sont autorisés.";
		public static final String[] LABEL_ERRORS_MEMBERS = { LABEL_ERROR_FAM_NAME, LABEL_ERROR_FIRST_NAME, LABEL_ERROR_ALIAS,
				LABEL_ERROR_EMAIL, LABEL_ERROR_PASSWORD };
		
		public static final int VBOX_WIDTH = 690;
		public static final int VBOX_HEIGHT = 720;
		public static final int TOX_VBOX = 200;

		public static final int TITLE_WIDTH = 680;
		public static final int TITLE_HEIGHT = 100;

		public static final int BTN_BOX_WIDTH = 680;
		public static final int BTN_BOX_HEIGHT = 192;
		public static final int SPACE_BETWEEN_BTNS = 100;

		public static final int GRIDPANE_WIDTH = 680;
		public static final int GRIDPANE_HEIGHT = 340;
		public static final int COL1_WIDTH = 270;
		public static final int COL2_WIDTH = 408;
		public static final int ROW_HEIGHT = 50;
		public static final int NBR_OF_LINES = 10;

		public static final int LABEL_ERROR_WIDTH = 680;
		public static final int LABEL_ERROR_HEIGHT = 50;

		public static GridPane createFormGridPane() {
			// GridPane
			GridPane gridPane = new GridPane();
			gridPane.setPrefSize(GRIDPANE_WIDTH, GRIDPANE_HEIGHT);
			gridPane.setVgap(10);
			gridPane.setHgap(10);

			// marges pour le GridPane
			VBox.setMargin(gridPane, new Insets(0, 20, 0, 20));

			// première colonne
			ColumnConstraints col1Constraints = new ColumnConstraints();
			col1Constraints.setPrefWidth(COL1_WIDTH);
			gridPane.getColumnConstraints().add(col1Constraints);

			// deuxième colonne
			ColumnConstraints col2Constraints = new ColumnConstraints();
			col2Constraints.setPrefWidth(COL2_WIDTH);
			gridPane.getColumnConstraints().add(col2Constraints);

			// lignes du GridPane
			for (int i = 0; i < NBR_OF_LINES; i++) {
				RowConstraints rowConstraints = new RowConstraints();

				if (i % 2 == 1) {
					rowConstraints.setPrefHeight(ROW_HEIGHT / 2);
					// labels des erreurs
					Label labelError = new Label(LABEL_ERRORS[i / 2]);
					labelError.setFont(Font.font("Krona One", 8));
					labelError.setTextFill(GREY_COLOR);
					labelError.setMaxWidth(COL2_WIDTH);
					labelError.setMinHeight(25);
					labelError.setWrapText(true);
					labelError.setVisible(false);

					GridPane.setMargin(labelError, new Insets(0, 0, 20, 0));

					gridPane.add(labelError, 1, i);

				} else {

					rowConstraints.setPrefHeight(CustomTextFieldValues.TF_HEIGHT + 5);

					// texte de droite
					CustomTextField textField = new CustomTextField();
					if (i/2 == 2) {
						textField.setMaxChars(3);
					} else if (i/2 == 3) {
						textField.setMaxChars(10);
					} else if (i/2 == 4) {
						textField.setMaxChars(4);
					}

					GridPane.setMargin(textField, new Insets(0, 40, 0, 0));
					
					// label
					Label label = new Label(LABEL_TEXTS[i / 2]);
					label.setFont(Font.font("Krona One", 16));
					label.setTextFill(GREY_COLOR);
					GridPane.setMargin(label, new Insets(0, 30, 0, 40));

					gridPane.add(label, 0, i);
					gridPane.add(textField, 1, i);
				}
				gridPane.getRowConstraints().add(rowConstraints);
			}

			gridPane.setPadding(new Insets(0,0,40,0));
			return gridPane;
		}
		
		public static GridPane createFormGridPaneMembers() {
			// GridPane
			GridPane gridPane = new GridPane();
			gridPane.setPrefSize(GRIDPANE_WIDTH, GRIDPANE_HEIGHT);
			gridPane.setVgap(10);
			gridPane.setHgap(10);

			// marges pour le GridPane
			VBox.setMargin(gridPane, new Insets(0, 20, 0, 20));

			// première colonne
			ColumnConstraints col1Constraints = new ColumnConstraints();
			col1Constraints.setPrefWidth(COL1_WIDTH);
			gridPane.getColumnConstraints().add(col1Constraints);

			// deuxième colonne
			ColumnConstraints col2Constraints = new ColumnConstraints();
			col2Constraints.setPrefWidth(COL2_WIDTH);
			gridPane.getColumnConstraints().add(col2Constraints);

			// lignes du GridPane
			for (int i = 0; i < 8; i++) {
				RowConstraints rowConstraints = new RowConstraints();

				if (i % 2 == 1) {
					rowConstraints.setPrefHeight(ROW_HEIGHT / 2);
					// labels des erreurs
					Label labelError = new Label(LABEL_ERRORS_MEMBERS[i / 2]);
					labelError.setFont(Font.font("Krona One", 8));
					labelError.setTextFill(GREY_COLOR);
					labelError.setMaxWidth(COL2_WIDTH);
					labelError.setMinHeight(25);
					labelError.setWrapText(true);
					labelError.setVisible(false);

					GridPane.setMargin(labelError, new Insets(0, 0, 20, 0));

					gridPane.add(labelError, 1, i);

				} else {

					rowConstraints.setPrefHeight(CustomTextFieldValues.TF_HEIGHT + 5);

					// texte de droite
					CustomTextField textField = new CustomTextField();

					GridPane.setMargin(textField, new Insets(0, 40, 0, 0));
					
					// label
					Label label = new Label(LABEL_TEXTS_MEMBERS[i / 2]);
					label.setFont(Font.font("Krona One", 16));
					label.setTextFill(GREY_COLOR);
					GridPane.setMargin(label, new Insets(0, 30, 0, 40));

					gridPane.add(label, 0, i);
					gridPane.add(textField, 1, i);
				}
				gridPane.getRowConstraints().add(rowConstraints);
			}
			
			// Ligne 7
			RowConstraints rowConstraints7 = new RowConstraints();
			rowConstraints7.setPrefHeight(ROW_HEIGHT);
			// label
			Label label5 = new Label(LABEL_TEXTS_MEMBERS[4]);
			label5.setFont(Font.font("Krona One", 16));
			label5.setTextFill(GREY_COLOR);
			GridPane.setMargin(label5, new Insets(0, 30, 0, 40));
			// à droite on met les radioboutons
			CustomRadioButton radioBtnHbox = new CustomRadioButton();
			// ajouter tout au GridPane
			gridPane.add(label5, 0, 8);
			gridPane.add(radioBtnHbox, 1, 8);
			gridPane.getRowConstraints().add(rowConstraints7);
			
			// Ligne 8
			RowConstraints rowConstraints8 = new RowConstraints();
			rowConstraints8.setPrefHeight(ROW_HEIGHT);
			// label
			Label label6 = new Label(LABEL_TEXTS_MEMBERS[5]);
			label6.setFont(Font.font("Krona One", 16));
			label6.setTextFill(GREY_COLOR);
			GridPane.setMargin(label6, new Insets(0, 30, 0, 40));
			// à droite on met le PasswordField
			CustomPasswordField passwordField = new CustomPasswordField();
			GridPane.setMargin(passwordField, new Insets(0, 40, 0, 0));
			// ajouter tout au GridPane
			gridPane.add(label6, 0, 9);
			gridPane.add(passwordField, 1, 9);
			gridPane.getRowConstraints().add(rowConstraints8);
			
			// Message d'erreur ligne 9
			RowConstraints rowConstraints9 = new RowConstraints();
			rowConstraints9.setPrefHeight(ROW_HEIGHT / 2);
			// labels des erreurs
			Label labelError = new Label(LABEL_ERRORS_MEMBERS[4]);
			labelError.setFont(Font.font("Krona One", 8));
			labelError.setTextFill(GREY_COLOR);
			labelError.setMaxWidth(COL2_WIDTH);
			labelError.setMinHeight(25);
			labelError.setWrapText(true);
			labelError.setVisible(false);
			GridPane.setMargin(labelError, new Insets(0, 0, 20, 0));
			// ajouter au GridPane
			gridPane.add(labelError, 1, 10);
			gridPane.getRowConstraints().add(rowConstraints9);
			
			
			
			return gridPane;
		}
	}

	public static class CustomButtonValues {

		public static final int BTN_WIDTH = 190;
		public static final int BTN_HEIGHT = 40;
	}

	public static class CustomTextFieldValues {

		public static final int TF_HEIGHT = 35;
		public static final int TF_RADIUS = 24;
		public static final int TF_OFFSETX = 2;
		public static final int TF_OFFSETY = 2;
		public static final Color TF_COLOR = Color.web("#000000", 0.1);

	}

	public static class ShadowSet {

		public static final InnerShadow INNER_SHADOW_WHITE;

		static {
			InnerShadow innerShadow = new InnerShadow();
			innerShadow.setRadius(10);
			innerShadow.setOffsetX(5);
			innerShadow.setOffsetY(5);
			innerShadow.setColor(Color.web("#ffffff", 0.16));

			INNER_SHADOW_WHITE = innerShadow;
		}

		public static final InnerShadow INNER_SHADOW_BLACK;

		static {
			InnerShadow innerShadow = new InnerShadow();
			innerShadow.setRadius(10);
			innerShadow.setOffsetX(5);
			innerShadow.setOffsetY(5);
			innerShadow.setColor(Color.web("#000000", 0.16));

			INNER_SHADOW_BLACK = innerShadow;
		}

		public static final DropShadow DROP_SHADOW;

		static {
			DropShadow dropShadow = new DropShadow();
			dropShadow.setRadius(37);
			dropShadow.setWidth(73);
			dropShadow.setHeight(73);
			dropShadow.setOffsetX(10.0);
			dropShadow.setColor(Color.web("#000000", 0.7));
			DROP_SHADOW = dropShadow;
		}
	}

	public static class TreeNodeValues {
		// values for both
		public static final int INDEX_SIZE = 4;
		public static final int OCTETS_TOOK_BY_CHAR = 2;

		// Intern
		public static final int MAX_CHAR_NAMES = 31;
		public static final int OCTETS_TOOK_BY_COUNTY = 4;
		public static final int MAX_CHAR_CURSUS = 10;
		public static final int OCTETS_TOOK_BY_YEARIN = 4;
		public static final int INTERN_SIZE = ((MAX_CHAR_NAMES + MAX_CHAR_NAMES + MAX_CHAR_CURSUS)
				* OCTETS_TOOK_BY_CHAR) + OCTETS_TOOK_BY_COUNTY + OCTETS_TOOK_BY_YEARIN;
		public static final int INTERN_NODE_SIZE = INTERN_SIZE + INDEX_SIZE + INDEX_SIZE + INDEX_SIZE;

		// Member
		public static final int MAX_CHAR_ALIAS = 31;
		public static final int MAX_CHAR_PASSWORD = 31;
		public static final int MAX_CHAR_FAMILYNAME = 31;
		public static final int MAX_CHAR_NAME = 31;
		public static final int MAX_CHAR_EMAIL = 40;
		public static final int OCTETS_TOOK_BY_ADMIN = 1;
		public static final int MEMBER_SIZE = ((MAX_CHAR_ALIAS + MAX_CHAR_PASSWORD + MAX_CHAR_FAMILYNAME + MAX_CHAR_NAME
				+ MAX_CHAR_EMAIL) * OCTETS_TOOK_BY_CHAR) + OCTETS_TOOK_BY_ADMIN;
		public static final int MEMBER_NODE_SIZE = MEMBER_SIZE + INDEX_SIZE + INDEX_SIZE + INDEX_SIZE;
	}

	public static class HelpTextValues {

		public static final String INTRODUCTION = "Introduction";
		public static final String INTRODUCTION_TEXT = "Cet annuaire est un outil destiné à centraliser et gérer les informations des stagiaires de manière simple et efficace." + "\n"
				+ "Il s'adresse au personnel du centre de formation." + "\n"
				+ "Une authentification à l'aide d'un nom d'utilisateur et d'un mot de passe est nécessaire pour accéder à l'annuaire.";
		public static final String RECHERCHE = "Recherche Avancée";
		public static final String RECHERCHE_TEXT = "Pour rechercher un stagiaire, il vous suffit de suivre les instructions suivantes:" + "\n"
				+ "\n"
				+ "1/ Pour accéder à la page de recherche, cliquez sur l'icône 'rechercher' (représenté par une loupe) dans la barre de menu situé à gauche de l'écran." + "\n"
				 + "\n"
				+ "2/ Pour affiner votre recherche et trouver le stagiaire qui correspond à vos critères, complétez les différents filtres disponibles, à savoir le nom de famille, et/ou le prénom, et/ou le département, et/ou la formation suivie, et/ou l'année d'entrée en formation." + "\n"
				 + "\n"
				+ "3/ Une fois vos critères de recherche définis, cliquez sur le bouton 'Rechercher'. La liste de stagiaires correspondant à vos filtres sera affichée sur votre ecran sous forme de tableau.";
		public static final String AJOUT_STAGIAIRE = "Ajouter un stagiaire";
		public static final String AJOUT_STAGIAIRE_TEXT = "Pour ajouter un stagiaire, il vous suffit de suivre les instructions suivantes :" + "\n"
				 + "\n"
				+ "1/ Pour accéder à la page d'ajout, cliquez sur l'icône 'ajouter' (représenté par un stagiaire avec un +) situé dans la barre du menu à gauche de l'écran." + "\n"
				 + "\n"
				+ "2/ Complétez les champs du formulaire d'ajout sans vous soucier des majuscules." + "\n"
				 + "\n"
				+ "3/ Après vérification des informations saisies, cliquez sur le bouton 'Ajouter' pour valider l'ajout." + "\n"
				 + "\n"
				+ "Il est obligatoire de remplir tous les champs pour effectuer l'ajout." + "\n"
				+ "Il est possible d'arrêter l'ajout à tout moment en cliquant sur le bouton 'Annuler'." + "\n"
				+ "Le nouveau stagiaire sera automatiquement ajouté par ordre alphabétique de son nom de famille. ";
		public static final String MODIFICATION_STAGIAIRE = "Modifier un stagiaire";
		public static final String MODIFICATION_STAGIAIRE_TEXT = "Pour modifier un stagiaire, il vous suffit de suivre les instructions suivantes :" + "\n"
				 + "\n"
				+ "1/ Pour sélectionner le stagiaire à modifier, vous pouvez soit le selectionner directement dans la liste en cliquant sur la ligne correspondant au stagiaire ou alors ouvrez la page de modifiation en cliquant sur l'icône 'Modifer' (représenté par un stagiaire avec un stylo), recherchez le stagiaire à modifier à l'aide des critères de recherche proposés." + "\n"
				 + "\n"
				+ "2/ Cliquez sur l'icône 'modifier' (représenté par un stagiaire tenant un stylo) situé dans la barre du menu à gauche de l'écran."
				 + "\n"
				+ "3/ Modifiez le(s) champ(s) du formulaire souhaité(s)" + "\n"
				 + "\n"
				+ "4/ Cliquez sur le bouton 'Modifier' pour valider la modification." + "\n"
				 + "\n"
				+ "Il est obligatoire de remplir tous les champs pour effectuer la modification." + "\n"
				+ "Il est possible d'arrêter la modification à tout moment en cliquant sur le bouton 'Annuler'.";
		public static final String SUPPRESSION_STAGIAIRE = "Supprimer un stagaire";
		public static final String SUPRESSION_STAGAIRE_TEXT = "Pour supprimer un stagiaire, il vous suffit de suivre les instructions suivantes :" + "\n"
				 + "\n"
				+ "1/ Selectionnez le stagiaire à supprimer en le selectionnant directement dans la liste." + "\n"
				 + "\n"
				+ "2/ Pour accéder à la page de suppression, cliquez sur l'icône 'supprimer' (représenté par un stagiaire avec un -) situé dans la barre du menu à gauche de l'écran. Les informations du stagiaires sont automatiquement remplies." + "\n"
				 + "\n"
				+ "3/ Après avoir vérifier qu'il s'agit du bon stagiaire à supprimer, cliquez sur le bouton 'Oui' ou annulez la suppression en appuyant sur le bouton 'Non'.";
		public static final String MEMBRES = "Espace Membres";
		public static final String MEMBRES_TEXT = "L'espace membre permet d'afficher la liste des membres ainsi que les informations associées, à savoir le nom, prénom, nom d'utilisateur, adresse mail et le statut conférant des droits d'accès différents." + "\n"
				+ "\n"
				+ "Pour rechercher un membre, cliquez sur l'icône 'rechercher' (représenté par une loupe) et complétez les différents filtres disponibles en fonction de votre recherche. Une fois vos critères de recherche définis, cliquez sur le bouton 'Rechercher'. La liste de membres correspondant à vos filtres sera affichée sur votre ecran sous forme de tableau." + "\n" 
				+ "\n" 
				+ "Pour ajouter un membre, cliquez sur l'icône 'ajouter' (représenté par un membre avec un +) puis complétez tous les champs du formulaire d'ajout. Après vérification des informations saisies, cliquez sur le bouton 'Ajouter' pour valider l'ajout." + "\n" 
				+ "\n" 
				+ "Pour supprimer un membre, sélectionnez le membre à supprimer puis cliquez sur l'icône 'supprimer' (représenté par un membre avec un -). Après avoir vérifier qu'il s'agit du bon membre à supprimer, cliquez sur le bouton 'Oui' ou annulez la suppression en appuyant sur le bouton 'Non'." + "\n" 
				+ "\n" 
				+ "Pour modifier un membre, sélectionnez le dans la liste en cliquant sur la ligne correspondant au membre puis cliquez sur l'icône 'modifier' (représenté par un membre tenant un stylo), effectuez votre modification puis cliquez sur le bouton 'Modifier' pour valider la modification."; 
		public static final String IMPRESSION = "Impression"; 
		public static final String IMPRESSION_TEXT = " La fonctionnalité 'Imprimer' de l'annuaire vous permet de générer une version imprimable des informations listées dans l'annuaire (stagiaire et membre). Si vous ne souhaitez pas imprimer tous les stagiaires ou tous les membres, utilisez les filtres pour trouver les stagiaires ou les membres que vous souhaitez inclure dans l'impression. Une fois votre selection faite, cliquez sur l'icône impression situé dans la barre du menu située à gauche de l'écran."+ "\n"  
				 + "\n"
				+ "Un titre de document avec la date du jour vous êtes directement proposé, vous pouvez le modifier en saisissant directement un nouveau titre. Selectionnez ensuite le lieu de stockage sur votre disque local à l'aide du bouton 'Dossier Cible' puis finalez l'export en cliquant sur 'Exporter'. Votre document se trouve alors dans le dossier sélectionné précédemment et un message de confirmation s'affiche. "; 
			
		public static final String DECONNEXION = "Déconnexion"; 
		public static final String DECONNEXION_TEXT="Lorsque vous avez terminez d'utiliser l'annuaire, déconnectez vous en cliquant sur l'icone'Déconnecter'(représenté par un bouton éteindre). Vous serez alors redirigé vers la page de connexion. Il vous faudra de nouveau saisir votre nom d'utilisateur et mot de passe pour accéder à l'annuaire."; 
		
		

	}

}
