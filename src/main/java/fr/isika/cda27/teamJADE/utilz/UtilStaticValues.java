package fr.isika.cda27.teamJADE.utilz;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.Colors.GREY_COLOR;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.Colors.SET_BG_ORANGE_COLOR;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.COL1_WIDTH;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.COL2_WIDTH;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.GRIDPANE_HEIGHT;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.GRIDPANE_WIDTH;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.LABEL_TEXTS;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.NBR_OF_LINES;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.ROW_HEIGHT;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

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

		public static final String[] LABEL_TEXTS = { "Nom de famille", "Prénom", "Région", "Formation suivie",
				"Année" };

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
		public static final int NBR_OF_LINES = 5;

		public static final int LABEL_ERROR_WIDTH = 680;
		public static final int LABEL_ERROR_HEIGHT = 50;

		public static GridPane createFormGridPane() {
			// GridPane
			GridPane gridPane = new GridPane();
			gridPane.setPrefSize(GRIDPANE_WIDTH, GRIDPANE_HEIGHT);
			gridPane.setVgap(15);
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
				rowConstraints.setPrefHeight(ROW_HEIGHT);
				gridPane.getRowConstraints().add(rowConstraints);

				// label
				Label label = new Label(LABEL_TEXTS[i]);
				label.setFont(Font.font("Krona One", 16));
				label.setTextFill(GREY_COLOR);
				GridPane.setMargin(label, new Insets(0, 30, 0, 40));

				// texte de droite
				CustomTextField textField = new CustomTextField();

				GridPane.setMargin(textField, new Insets(0, 40, 0, 0));

				// ajouter tout au GridPane
				gridPane.add(label, 0, i);
				gridPane.add(textField, 1, i);
			}

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
		public static final int OCTETS_TOOK_BY_ADMIN = 1;
		public static final int MEMBER_SIZE = ((MAX_CHAR_ALIAS + MAX_CHAR_PASSWORD)
				* OCTETS_TOOK_BY_CHAR) + OCTETS_TOOK_BY_ADMIN;
		public static final int MEMBER_NODE_SIZE = MEMBER_SIZE + INDEX_SIZE + INDEX_SIZE + INDEX_SIZE;
	}

	public static class HelpTextValues {
		
		public static final String INTRODUCTION="Introduction"; 
		public static final String INTRODUCTION_TEXT = "Cet annuaire est un outil destiné à centraliser et gérer les informations des stagiaires de manière simple et efficace." + "\n"
				+                		"Il s'adresse aux administrateurs du centre de formation." + "\n"
				+                 		"Une authentification à l'aide d'un nom d'utilisateur et d'un mot de passe est nécessaire pour accéder à l'annuaire." + "\n"
				+                 		"L'annuaire est accessible sur ordinateur uniquement."; 
		public static final String GESTION_STAGIAIRES = "Gestion des stagiaires"; 
		public static final String AJOUT_STAGIAIRE = "Comment ajouter un stagiaire?"; 
		public static final String AJOUT_STAGIAIRE_TEXT = "La procédure pour ajouter un stagiaire est la suivante :" + "\n"
				+ "    1/ Cliquez sur l'icône 'Ajouter' situé dans la barre du menu à gauche de l'écran puis" + "\n"
				+ "    2/ Complétez les champs du formulaire d'ajout : Nom de famille, Prénom, Département, Formation suivie, Année d'entrée en formation" + "\n"
				+ "    3/ Cliquez sur le bouton 'Ajouter' située en bas à droite pour valider l'ajout." + "\n"
				+ "Il est obligatoire de remplir tous les champs pour effectuer l'ajout." + "\n"
				+ "Il est possible d'arrêter l'ajout à tout moment en cliquant sur le bouton 'Annuler'."; 
		public static final String MODIFICATION_STAGIAIRE= "Comment modifier un stagiaire?"; 
		public static final String MODIFICATION_STAGIAIRE_TEXT = "La procédure pour modifier un stagiaire est la suivante :" + "\n" 
				+                 		"	1/ Selectionnez le stagiaire à modifier en le selectionnant directement dans la liste puis"  + "\n"
				+                		"	2/ Cliquez sur l'icône 'modifier' (représenté par un stagiaire tenant un stylo) situé dans la barre du menu à gauche de l'écran puis" + "\n"
				+                 		"	3/ Modifiez le(s) champ(s) du formulaire souhaité(s)" + "\n"
				+                 		"	4/ Cliquez sur le bouton 'Modifier' située en bas à droite pour valider la modification." + "\n" 
				+                 		"Il est obligatoire de remplir tous les champs pour effectuer l'ajout." + "\n"
				+                		"Il est possible d'arrêter l'ajout à tout moment en cliquant sur le bouton 'Annuler'."; 
		public static final String SUPPRESSION_STAGIAIRE = "Comment supprimer un stagaire?"; 
		public static final String SUPRESSION_STAGAIRE_TEXT = "La procédure pour supprime un stagiaire est la suivante :" + "\n"
				+                  		"	1/ Selectionnez le stagiaire à supprimer en le selectionnant directement dans la liste" + "\n"
				+                 		"	2/ Cliquez sur l'icône 'supprimer' (représenté par un stagiaire avec un petit moins) situé dans la barre du menu à gauche de l'écran. Les informations du stagiaires sont automatiquement remplies" + "\n"
				+                 		"	3/ Après avoir vérifier qu'il s'agit du bon stagiaire à supprimer, cliquez sur le bouton 'Oui' ou annulez la suppression en appuyant sur le bouton 'Non'."; 
		public static final String MEMBRES = "Espace Membres"; 
		public static final String MEMBRES_TEXT = "L'espace membre permet de voir XXXX. Pour modifier XXX. Pour ajouter un membres, pour supprimer un membre.  Pour se déconnecter de l'annuaire, il suffit de cliquer sur l'icone 'déconnexion' situé ???"; 
		public static final String IMPRESSION = "Impression"; 
		public static final String IMPRESSION_TEXT = "Accès à la fonctionnalité : Pour imprimer la liste des stagiaires, cliquez sur l'icône impression (représenté par une imprimante) situé en bas à gauche dans la barre du menu."+ "\n"  
				+               		"OPTIONS D'IMPRESSION" + "\n" 
				+               		"Il possible d'exporter la liste des stagiaires en format PDF avant impression."; 
		
		
		
		
	}
	
	
}


