package fr.isika.cda27.teamJADE.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.isika.cda27.teamJADE.model.Intern;
import fr.isika.cda27.teamJADE.utilz.ButtonActions;
import fr.isika.cda27.teamJADE.utilz.CustomButton;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class CustomMainScene extends AnchorPane {

	/**
	 * 
	 */
	public CustomMainScene() {
		// AnchorPane
		this.setPrefSize(1280, 720);

		// police
		Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/KronaOne-Regular.ttf"), 24);

		// InnerShadow
		InnerShadow innerShadow = new InnerShadow();
		innerShadow.setRadius(10);
		innerShadow.setOffsetX(5.0);
		innerShadow.setOffsetY(5.0);
		innerShadow.setColor(Color.web("#ffffff", 0.16));

		// DropShadow
		DropShadow dropShadow = new DropShadow();
		dropShadow.setRadius(37);
		dropShadow.setWidth(73);
		dropShadow.setHeight(73);
		dropShadow.setOffsetX(10.0);
		dropShadow.setColor(Color.web("#000000", 0.7));

		// StackPane qui va contenir la TableView et menuHbox
		StackPane bgStackPane = new StackPane();
		bgStackPane.setPrefSize(1280, 720);
		bgStackPane.setStyle("-fx-background-color: #272727;");

		// TableView
		// données d'exemple
		ArrayList<Intern> list = new ArrayList<Intern>();
		list.add(new Intern("LACROIX", "Pascale", "91", "BOBI 5", "2008"));
		list.add(new Intern("LACROIX", "Pascale", "91", "BOBI 5", "2009"));
		list.add(new Intern("CHAVENEAU", "Kim Anh", "92", "ATOD 22", "2014"));
		list.add(new Intern("GARIJO", "Rosie", "75", "AI 79", "2011"));
		list.add(new Intern("POTIN", "Thomas", "75", "ATOD 21", "2014"));
		list.add(new Intern("AUGEREAU", "Kévin", "76", "AI 78", "2010"));
		list.add(new Intern("UNG", "Jet-Ming", "75", "ATOD 16 CP", "2012"));
		list.add(new Intern("ROIGNANT", "Pierre-Yves", "75", "Al", "2015"));
		list.add(new Intern("CHONE", "Martin", "92", "ATOD 24 CP", "2015"));

		ObservableList<Intern> observableInterns = FXCollections.observableArrayList(list);
		FilteredList<Intern> filteredInterns = new FilteredList<>(observableInterns, p -> true);
		TableView tableView = createTableView(filteredInterns);

		// HBox du menu
		HBox menuHbox = new HBox();
		menuHbox.setMaxWidth(890);
		menuHbox.setMaxHeight(720);
		menuHbox.setStyle("-fx-background-color: #DD734C; -fx-background-radius: 70;");
		menuHbox.setEffect(dropShadow);
//				menuHbox.setTranslateX(-300);
		menuHbox.setTranslateX(-985);

		// VBox avec les contenu pringipaux à gauche du menu
		ScopeScene scopeContentVbox = new ScopeScene();
		VBox addContentVbox = new MainContentVbox().createAddVbox();
		RemoveScene removeContentVbox = new RemoveScene();
		VBox updateContentVbox = new MainContentVbox().createUpdateVbox();
		VBox printContentVbox = new MainContentVbox().createPrintVbox();
		VBox seeMembersContentVbox = new MainContentVbox().createSeeMembersVbox();
		VBox quitContentVbox = new MainContentVbox().createQuitVbox();

		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Intern>() {
			@Override
			public void changed(ObservableValue<? extends Intern> observableValue, Intern oldValue, Intern newValue) {
				String[] gridPaneLabelsList = new String[5];

				gridPaneLabelsList[0] = newValue.getFamilyName();
				gridPaneLabelsList[1] = newValue.getFirstName();
				gridPaneLabelsList[2] = newValue.getCounty();
				gridPaneLabelsList[3] = newValue.getCursus();
				gridPaneLabelsList[4] = newValue.getYearIn();

				removeContentVbox.setGridPane(gridPaneLabelsList);
			}
		});

		// VBox avec les boutons du menu
		VBox menubarVBox = new VBox();
		menubarVBox.setPrefSize(100, 720);
		menubarVBox.setTranslateX(100);

		// On crée la croix du haut
		String pathTop = new String("M98 69.5002C98 23.8722 61 -7.5 0.5 0L0.5 97.0002L98 97.0002L98 69.5002Z");
		StackPaneMenubar closeBtn = new StackPaneMenubar("croix.png","fleche.png", pathTop, false, 30);

		// On crée les autres boutons
		StackPaneMenubar scopeBtn = new StackPaneMenubar("loupe_orange.png", "loupe_grise.png", "Rechercher");
		StackPaneMenubar addBtn = new StackPaneMenubar("ajout_stagiaire_orange.png", "ajout_stagiaire_gris.png",
				"Ajouter");
		StackPaneMenubar removeBtn = new StackPaneMenubar("suppr_stagiaire_orange.png", "suppr_stagiaire_gris.png",
				"Supprimer");
		StackPaneMenubar updateBtn = new StackPaneMenubar("modif_stagiaire_orange.png", "modif_stagiaire_gris.png",
				"Modifier");
		StackPaneMenubar printBtn = new StackPaneMenubar("imprimante_orange.png", "imprimante_grise.png", "Imprimer");
		StackPaneMenubar seeMemberBtn = new StackPaneMenubar("voir_membre_orange.png", "voir_membre_gris.png",
				"Membres");

		// On crée le bouton quitter

		String pathBot = new String(
				"M5.02681e-06 0L300 4.58562e-07L300 19.5L300 47.5C300 49 299.838 52.4703 299 57.5C298 63.5 295 73 292 78C289 83 286.5 88 280 94.5C273.5 101 264.5 107 253.5 111C244.7 114.2 237 115 231 115L5.02681e-06 115L5.02681e-06 0Z");
		StackPaneMenubar quitBtn = new StackPaneMenubar("deconnexion_orange.png", "deconnexion_gris.png", "Déconnexion",
				pathBot);

		// On ajoute la croix (invisible pour le moment)
		menubarVBox.getChildren().add(closeBtn.getStackPane());
		// On ajoute tous les boutons dans le VBox
		menubarVBox.getChildren().addAll(scopeBtn.getStackPane(), addBtn.getStackPane(), removeBtn.getStackPane(),
				updateBtn.getStackPane(), printBtn.getStackPane(), seeMemberBtn.getStackPane());
		// On ajoute le bouton quitter
		menubarVBox.getChildren().add(quitBtn.getStackPane());

		// On ajoute le contenu de gauche et de droite a la HBox du menu
		menuHbox.getChildren().addAll(scopeContentVbox, menubarVBox);

		// On ajoute la tableview et menuhbox a la stackpane
		bgStackPane.getChildren().addAll(tableView, menuHbox);

		// On ajoute la stackpane
		this.getChildren().add(bgStackPane);

		/* ---------------------------------------------------------------- */

		/* BOUTONS ACCTIONS */

		TranslateTransition moveTransition = new TranslateTransition();
		ButtonActions buttonActions = new ButtonActions();

		List<StackPaneMenubar> allButtons = Arrays.asList(closeBtn, scopeBtn, addBtn, removeBtn, updateBtn, printBtn, seeMemberBtn,quitBtn);

		List<StackPaneMenubar> scopeBtnConfig = Arrays.asList(addBtn,removeBtn ,updateBtn, printBtn, seeMemberBtn,	quitBtn);

		List<StackPaneMenubar> addBtnConfig = Arrays.asList(scopeBtn, removeBtn, updateBtn, printBtn, seeMemberBtn,
				quitBtn);

		List<StackPaneMenubar> removeBtnConfig = Arrays.asList(scopeBtn, addBtn, updateBtn, printBtn, seeMemberBtn,
				quitBtn);

		List<StackPaneMenubar> printBtnConfig = Arrays.asList(scopeBtn, addBtn, removeBtn, updateBtn, seeMemberBtn,
				quitBtn);

		List<StackPaneMenubar> updateBtnConfig = Arrays.asList(scopeBtn, addBtn, removeBtn, printBtn, seeMemberBtn,
				quitBtn);

		List<StackPaneMenubar> seeMemberBtnConfig = Arrays.asList(scopeBtn, addBtn, removeBtn, updateBtn, printBtn,
				quitBtn);

		List<StackPaneMenubar> quitBtnBtnConfig = Arrays.asList(scopeBtn, addBtn, removeBtn, updateBtn, printBtn,
				seeMemberBtn);

		// On configure les actions pour chaque bouton
		
		buttonActions.configureButtonAction(scopeBtn, scopeContentVbox, bgStackPane, closeBtn, scopeBtnConfig);
		buttonActions.configureButtonAction(addBtn, addContentVbox, bgStackPane, closeBtn, addBtnConfig);
		buttonActions.configureButtonAction(removeBtn, removeContentVbox, bgStackPane, closeBtn, removeBtnConfig);
		buttonActions.configureButtonAction(updateBtn, updateContentVbox, bgStackPane, closeBtn, updateBtnConfig);
		buttonActions.configureButtonAction(printBtn, printContentVbox, bgStackPane, closeBtn, printBtnConfig);
		buttonActions.configureButtonAction(seeMemberBtn, seeMembersContentVbox, bgStackPane, closeBtn,
				seeMemberBtnConfig);
		buttonActions.configureButtonAction(quitBtn, quitContentVbox, bgStackPane, closeBtn, quitBtnBtnConfig);
System.out.println(menuHbox.getTranslateX());
		


	// quand on clique sur le bouton fleche/croix
		buttonActions.configureCloseBtn(menuHbox, allButtons);
		closeBtn.getButton().setOnAction(event -> {
			
			RotateTransition rotateTransition = new RotateTransition(Duration.millis(500), closeBtn.getBtnOrangeImageView());
			
			if((int)(menuHbox.getTranslateX())== -985) {
			// si la menuHbox est à -985 on ouvre un peu
			moveTransition.setDuration(Duration.millis(500));
			moveTransition.setNode(menuHbox);
			moveTransition.setToX(-800);
			moveTransition.play();
			// on fait pivoter l'image de 180°
			rotateTransition.setByAngle(180);
			rotateTransition.play();
			
			}else if((int)(menuHbox.getTranslateX())== -800) {
			// sinon si la menuHbox est à -600 on referme
			moveTransition.setDuration(Duration.millis(500));
			moveTransition.setNode(menuHbox);
			moveTransition.setToX(-985);
			moveTransition.play();
			// on fait pivoter l'image de 180°
			rotateTransition.setByAngle(180);
			rotateTransition.play();
			
			}else {
			// sinon on ferme
			moveTransition.setDuration(Duration.millis(500));
			moveTransition.setNode(menuHbox);
			moveTransition.setToX(-985);
			moveTransition.play();

			closeBtn.getBtnGreyImageView().setVisible(false);
			closeBtn.getBtnOrangeImageView().setVisible(true);

			changeToOrange(scopeBtn);
			changeToOrange(addBtn);
			changeToOrange(removeBtn);
			changeToOrange(updateBtn);
			changeToOrange(printBtn);
			changeToOrange(seeMemberBtn);
			changeToOrange(quitBtn);
			}
		});

		buttonActions.configureRefreshBtn(scopeContentVbox);

		buttonActions.configureSearchBtn(menuHbox, scopeContentVbox, closeBtn, allButtons, filteredInterns);

	}

	private TableView createTableView(FilteredList<Intern> filteredInterns) {

		TableView<Intern> tableView = new TableView<>(filteredInterns);

		// On met les colones
		double columnWidth = 1025 / 5;

		TableColumn<Intern, String> column1 = new TableColumn<>("Nom de famille");
		column1.setCellValueFactory(new PropertyValueFactory<>("familyName"));
		column1.setPrefWidth(columnWidth);

		TableColumn<Intern, String> column2 = new TableColumn<>("Prénom");
		column2.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		column2.setPrefWidth(columnWidth);

		TableColumn<Intern, String> column3 = new TableColumn<>("Région");
		column3.setCellValueFactory(new PropertyValueFactory<>("county"));
		column3.setPrefWidth(columnWidth);

		TableColumn<Intern, String> column4 = new TableColumn<>("Formation suivie");
		column4.setCellValueFactory(new PropertyValueFactory<>("cursus"));
		column4.setPrefWidth(columnWidth);

		TableColumn<Intern, String> column5 = new TableColumn<>("Année");
		column5.setCellValueFactory(new PropertyValueFactory<>("yearIn"));
		column5.setPrefWidth(columnWidth);

		tableView.getColumns().addAll(column1, column2, column3, column4, column5);

		StackPane.setMargin(tableView, new Insets(70, 70, 70, 170));
		return tableView;
	}

	/* ---------------------------------------------------------------- */

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
