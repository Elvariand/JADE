package fr.isika.cda27.teamJADE.utilz;

import java.util.List;

import fr.isika.cda27.teamJADE.model.*;
import fr.isika.cda27.teamJADE.view.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;

public class ButtonActions {
	private TranslateTransition moveTransition = new TranslateTransition();

	/* METHODS */
	public void configureCloseBtn(HBox menuHbox,List<StackPaneMenubar> allButtons) {
		allButtons.get(0).getButton().setOnAction(event -> {
			moveTransition.setDuration(Duration.millis(500));
			moveTransition.setNode(menuHbox);
			moveTransition.setToX(-985);
			moveTransition.play();

			allButtons.get(0).getBtnGreyImageView().setVisible(false);

			changeToOrange(allButtons.get(1));
			changeToOrange(allButtons.get(2));
			changeToOrange(allButtons.get(3));
			changeToOrange(allButtons.get(4));
			changeToOrange(allButtons.get(5));
			changeToOrange(allButtons.get(6));
			changeToOrange(allButtons.get(7));

		});
	}
	
	public void configureRefreshBtn(ScopeScene scopeContentVbox) {
		Button refreshBtn = scopeContentVbox.getLeftButton();
		refreshBtn.setOnAction(event -> {
			System.out.println("On efface tout le contenu des textfield");
			refresh(scopeContentVbox.getGridPaneFamilyName());
			refresh(scopeContentVbox.getGridPaneFirstName());
			refresh(scopeContentVbox.getGridPaneCounty());
			refresh(scopeContentVbox.getGridPaneCursus());
			refresh(scopeContentVbox.getGridPaneYearIn());
		});
	}

	private void refresh(TextField textField) {
		textField.setText("");
	}

	public void configureButtonAction(StackPaneMenubar buttonClicked, VBox mainContentToShow, StackPane bgStackPane,
			StackPaneMenubar closeBtn, List<StackPaneMenubar> otherButtons) {

		HBox menuHbox = (HBox) (bgStackPane.getChildren().get(1));
//		TableView tableView = ((TableView) (bgStackPane.getChildren().get(0)));

		buttonClicked.getButton().setOnAction(event -> {

			// On remplace le contenu de la HBox
			menuHbox.getChildren().set(0, mainContentToShow);

			TranslateTransition moveTransition = new TranslateTransition(Duration.millis(500), menuHbox);
			moveTransition.setToX(-300);
			moveTransition.play();

			closeBtn.getBtnGreyImageView().setVisible(true);
			closeBtn.getBtnOrangeImageView().setVisible(false);
			
			changeToGrey(otherButtons.get(0));
			changeToGrey(otherButtons.get(1));
			changeToGrey(otherButtons.get(2));
			changeToGrey(otherButtons.get(3));
			changeToGrey(otherButtons.get(4));
			changeToGrey(otherButtons.get(5));

			// On met à jour la couleur du bouton cliqué en orange
			changeToOrange(buttonClicked);

		});

		addHoverEffect(buttonClicked);

	}

	public void configureSearchBtn(HBox menuHbox, ScopeScene scopeContentVbox, StackPaneMenubar closeBtn, List<StackPaneMenubar> otherButtons, FilteredList<Intern> filteredInterns ) {
		Button searshBtn = scopeContentVbox.getRightButton();
		searshBtn.setOnAction(event -> {
			System.out.println("Recherche en cours ...");

			// on récupère tous les textfield
			String familyNameFilter = scopeContentVbox.getGridPaneFamilyName().getText();
			String firstNameFilter = scopeContentVbox.getGridPaneFirstName().getText();
			String countyFilter = scopeContentVbox.getGridPaneCounty().getText();
			String cursusFilter = scopeContentVbox.getGridPaneCursus().getText();
			String yearInFilter = scopeContentVbox.getGridPaneYearIn().getText();

			System.out.println("Nom de famille : " + familyNameFilter + "\nPrénom : " + firstNameFilter + "\nRégion : "
					+ countyFilter + "\nFormation suivie : " + cursusFilter + "\nAnnée : " + yearInFilter);

			// on filtre la liste de la TableView en fonction
			filteredInterns.setPredicate(intern -> {
				// pour chaque textfield on doit vérifier si il n'est pas null
				// si il ne l'est pas, on ajoute la condition au prédicat

				// on return true si aucun champ n'est rempli
				boolean filters = true;

				// sinon on ajoute à chaque fois le filtre au return

				if (familyNameFilter != null) {
					filters = filters && intern.getFamilyName().toUpperCase().contains(familyNameFilter.toUpperCase());
				}

				if (firstNameFilter != null) {
					filters = filters && intern.getFirstName().toUpperCase().contains(firstNameFilter.toUpperCase());
				}

				if (countyFilter != null) {
					filters = filters && intern.getCounty().toUpperCase().contains(countyFilter.toUpperCase());
				}

				if (cursusFilter != null) {
					filters = filters && intern.getCursus().toUpperCase().contains(cursusFilter.toUpperCase());
				}

				if (yearInFilter != null) {
					filters = filters && intern.getYearIn().toUpperCase().contains(yearInFilter.toUpperCase());
				}

				return filters;
			});

			moveTransition.setDuration(Duration.millis(500));
			moveTransition.setNode(menuHbox);
			moveTransition.setToX(-985);
			moveTransition.play();

			closeBtn.getBtnGreyImageView().setVisible(false);

			changeToOrange(otherButtons.get(0));
			changeToOrange(otherButtons.get(1));
			changeToOrange(otherButtons.get(2));
			changeToOrange(otherButtons.get(3));
			changeToOrange(otherButtons.get(4));
			changeToOrange(otherButtons.get(5));

			System.out.println("Filtrage terminé.");
		});
		
	}
	
	private void addHoverEffect(StackPaneMenubar buttonSP) {

		// Variables pour stocker les couleurs
		String[] initialColorHex = new String[2];

		// Couleurs de survol
		Color hoverColorOrange = Color.web("#F39471");
		Color hoverColorGrey = Color.web("#4E4E4E");

		// Appliquer l'effet de survol
		buttonSP.getButton().setOnMouseEntered(event -> {

			// On récup et stocke la couleur initiale au survol de la souris
			initialColorHex[0] = getCurrentColor(buttonSP.getSvgPath());

			if (initialColorHex[0] != null) {
				Color currentColor = Color.web(initialColorHex[0]);

				if (currentColor.equals(Color.web("#DD734C"))) {
					buttonSP.getSvgPath().setFill(hoverColorOrange);
				} else if (currentColor.equals(Color.web("#272727"))) {
					buttonSP.getSvgPath().setFill(hoverColorGrey);
				} else {
					System.out.println("Couleur non reconnue");
				}
			}
		});

		// On restaure la couleur initiale à la sortie de la souris
		buttonSP.getButton().setOnMouseExited(event -> {

			initialColorHex[1] = getCurrentColor(buttonSP.getSvgPath());

			if (initialColorHex[1].equals("#DD734C")) { // le bouton a été cliqué
				buttonSP.getSvgPath().setFill(Color.web("#DD734C"));
			} else {

				if (initialColorHex[0].equals("#DD734C")) {
					buttonSP.getSvgPath().setFill(Color.web("#DD734C"));
				} else if (initialColorHex[0].equals("#272727")) {
					buttonSP.getSvgPath().setFill(Color.web("#272727"));
				} else {
					System.out.println("Couleur non reconnue");
				}
			}
		});
	}

	private void changeToOrange(StackPaneMenubar button) {
		applyColorTransition(button.getSvgPath(), "#DD734C");
		button.getSvgPath().setFill(Color.web("#DD734C"));
		button.getBtnOrangeImageView().setVisible(false);
		button.getBtnGreyImageView().setVisible(true);
	}

	private void changeToGrey(StackPaneMenubar button) {
		applyColorTransition(button.getSvgPath(), "#272727");
		button.getSvgPath().setFill(Color.web("#272727"));
		button.getBtnOrangeImageView().setVisible(true);
		button.getBtnGreyImageView().setVisible(false);
	}

	private void applyColorTransition(SVGPath svgPath, String newColor) {
		Color initialColor = Color.web(getCurrentColor(svgPath));
		Color finalColor = Color.web(newColor);

		// transition de couleur
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.ZERO, new KeyValue(svgPath.fillProperty(), initialColor)),
				new KeyFrame(Duration.millis(500), new KeyValue(svgPath.fillProperty(), finalColor)));
		timeline.play();
	}

	private String getCurrentColor(SVGPath svgPath) {
		// couleur actuelle de remplissage de l'SVGPath
		Paint fill = svgPath.getFill();

		if (fill instanceof Color) {
			Color color = (Color) fill;
			return String.format("#%02X%02X%02X", (int) (color.getRed() * 255), (int) (color.getGreen() * 255),
					(int) (color.getBlue() * 255));
		}

		// Si la couleur n'est pas du type Color, retourne une valeur par défaut
		return "#000000";
	}


}
