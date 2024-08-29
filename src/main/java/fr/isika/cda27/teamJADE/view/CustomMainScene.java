package fr.isika.cda27.teamJADE.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import fr.isika.cda27.teamJADE.model.Intern;
import fr.isika.cda27.teamJADE.model.InternDao;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;


import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MainSceneValues.*;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.ShadowSet.*;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.Colors.*;

public class CustomMainScene extends AnchorPane {

	public CustomMainScene() {
		// AnchorPane
		this.setPrefSize(1280, 720);

		// police
		Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/KronaOne-Regular.ttf"), 24);

		// StackPane qui va contenir la TableView et menuHbox
		StackPane bgStackPane = new StackPane();
		bgStackPane.setPrefSize(1280, 720);
		bgStackPane.setStyle("-fx-background-color: #272727;");
		
		
		StackPaneHelp stackPaneHelp = new StackPaneHelp ("help.png", "help_hover.png"); 
		StackPane.setAlignment(stackPaneHelp, Pos.TOP_RIGHT); 

		stackPaneHelp.setMaxSize(75, 75);

		
		

		stackPaneHelp.getButton().setOnAction(event -> {
			Stage stage = ((Stage)CustomMainScene.this.getScene().getWindow());
			Scene scene = new Scene(new HelpScene()); 
			scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
			stage.setScene(scene);
		}); 
		

		// TableView
		// données d'exemple
		ArrayList<Intern> list = new ArrayList<Intern>();
		list.add(new Intern("LACROIX", "Pascale", 91, "BOBI 5", 2008));
		list.add(new Intern("LACROIX", "Pascale", 91, "BOBI 5", 2009));
		list.add(new Intern("CHAVENEAU", "Kim Anh", 92, "ATOD 22", 2014));
		list.add(new Intern("GARIJO", "Rosie", 75, "AI 79", 2011));
		list.add(new Intern("POTIN", "Thomas", 75, "ATOD 21", 2014));
		list.add(new Intern("AUGEREAU", "Kévin", 76, "AI 78", 2010));
		list.add(new Intern("UNG", "Jet-Ming", 75, "ATOD 16 CP", 2012));
		list.add(new Intern("ROIGNANT", "Pierre-Yves", 75, "Al", 2015));
		list.add(new Intern("CHONE", "Martin", 92, "ATOD 24 CP", 2015));

		ObservableList<Intern> observableInterns = FXCollections.observableArrayList(list);
		FilteredList<Intern> filteredInterns = new FilteredList<>(observableInterns, p -> true);
		TableView<Intern> tableView = createTableView(filteredInterns);

		// HBox du menu
		HBox menuHbox = new HBox();
		menuHbox.setMaxWidth(990);
		menuHbox.setMaxHeight(720);
		menuHbox.setStyle("-fx-background-color: #DD734C; -fx-background-radius: 70;");
		menuHbox.setEffect(DROP_SHADOW);
		menuHbox.setTranslateX(TOX_SMALL_MENU);

		// VBox avec les contenu pringipaux à gauche du menu
		ScopeScene scopeContentVbox = new ScopeScene();
		AddScene addContentVbox = new AddScene();
		RemoveScene removeContentVbox = new RemoveScene();
		UpdateScene updateContentVbox = new UpdateScene();
		PrintScene printContentVbox = new PrintScene();
		SeeMembersScene seeMembersContentVbox = new SeeMembersScene();
		QuitScene quitContentVbox = new QuitScene();

		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Intern>() {
			@Override
			public void changed(ObservableValue<? extends Intern> observableValue, Intern oldValue, Intern newValue) {
				String[] gridPaneLabelsList = new String[5];

				gridPaneLabelsList[0] = newValue.getFamilyName();
				gridPaneLabelsList[1] = newValue.getFirstName();
				gridPaneLabelsList[2] = newValue.getCountyString();
				gridPaneLabelsList[3] = newValue.getCursus();
				gridPaneLabelsList[4] = newValue.getYearInString();

				removeContentVbox.setGridPane(gridPaneLabelsList);
			}
		});

		// VBox avec les boutons du menu
		VBox menubarVBox = new VBox();
		menubarVBox.setPrefSize(MENUBAR_WIDTH, MENUBAR_HEIGHT);
		menubarVBox.setTranslateX(TOX_MENUBAR);
//		menubarVBox.setStyle("-fx-background-color: transparent; -fx-border-color: green; -fx-border-width: 5;");

		// On crée la croix du haut

		StackPaneMenubar closeBtn = new StackPaneMenubar("croix.png", "fleche.png", PATH_TOP, 30);

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
		StackPaneMenubar quitBtn = new StackPaneMenubar("deconnexion_orange.png", "deconnexion_gris.png", "Déconnexion", SMALL_PATH_BOT,
				LARGE_PATH_BOT);

		// On ajoute la croix (invisible pour le moment)
		menubarVBox.getChildren().add(closeBtn);
		// On ajoute tous les boutons dans le VBox
		menubarVBox.getChildren().addAll(scopeBtn, addBtn, removeBtn, updateBtn, printBtn, seeMemberBtn);
		// On ajoute le bouton quitter
		menubarVBox.getChildren().add(quitBtn);

		StackPaneMenubar[] menuChildren = { closeBtn, scopeBtn, addBtn, removeBtn, updateBtn, printBtn, seeMemberBtn,
				quitBtn };

		// On ajoute le contenu de gauche et de droite a la HBox du menu
		menuHbox.getChildren().addAll(scopeContentVbox, menubarVBox);

		// On ajoute la tableview et menuhbox a la stackpane
		bgStackPane.getChildren().addAll(tableView, menuHbox, stackPaneHelp);

		// On ajoute la stackpane
		this.getChildren().add(bgStackPane);

		/* ---------------------------------------------------------------- */

		/* BOUTONS ACCTIONS */

		TranslateTransition moveTransition = new TranslateTransition();
		FadeTransition fadeTransition = new FadeTransition();

		/*
		 * On configure les listes de boutons (qui sont des StackPaneMenubar) dans
		 * chaque cas de figure Ex : si le bouton cliqué est scopeBtn, alors
		 * scopeBtnConfig sera tous les autres boutons de la VBox. Et on fait ça pour
		 * chaque bouton afin de mettre tous les autres boutons en gris et le bouton
		 * cliqué en orange. Et de faire apparaite la croix du menu et disparaitre la
		 * fleche.
		 */
		List<StackPaneMenubar> scopeBtnConfig = Arrays.asList(closeBtn, addBtn, removeBtn, updateBtn, printBtn,
				seeMemberBtn, quitBtn);

		List<StackPaneMenubar> addBtnConfig = Arrays.asList(closeBtn, scopeBtn, removeBtn, updateBtn, printBtn,
				seeMemberBtn, quitBtn);

		List<StackPaneMenubar> removeBtnConfig = Arrays.asList(closeBtn, scopeBtn, addBtn, updateBtn, printBtn,
				seeMemberBtn, quitBtn);

		List<StackPaneMenubar> printBtnConfig = Arrays.asList(closeBtn, scopeBtn, addBtn, removeBtn, updateBtn,
				seeMemberBtn, quitBtn);

		List<StackPaneMenubar> updateBtnConfig = Arrays.asList(closeBtn, scopeBtn, addBtn, removeBtn, printBtn,
				seeMemberBtn, quitBtn);

		List<StackPaneMenubar> seeMemberBtnConfig = Arrays.asList(closeBtn, scopeBtn, addBtn, removeBtn, updateBtn,
				printBtn, quitBtn);

		List<StackPaneMenubar> quitBtnBtnConfig = Arrays.asList(closeBtn, scopeBtn, addBtn, removeBtn, updateBtn,
				printBtn, seeMemberBtn);

		/*
		 * On configure les actions pour chaque bouton en donnant en argument : 1) le
		 * bouton cliqué 2) le contenu à afficher 3) la HBox du menu 4)
		 */

		configureButtonAction(scopeBtn, scopeContentVbox, menuHbox, scopeBtnConfig);
		configureButtonAction(addBtn, addContentVbox, menuHbox, addBtnConfig);
		configureButtonAction(removeBtn, removeContentVbox, menuHbox, removeBtnConfig);
		configureButtonAction(updateBtn, updateContentVbox, menuHbox, updateBtnConfig);
		configureButtonAction(printBtn, printContentVbox, menuHbox, printBtnConfig);
		configureButtonAction(seeMemberBtn, seeMembersContentVbox, menuHbox, seeMemberBtnConfig);
		configureButtonAction(quitBtn, quitContentVbox, menuHbox, quitBtnBtnConfig);

		// quand on clique sur le bouton fleche/croix
		closeBtn.getButton().setOnAction(event -> {

			RotateTransition rotateTransition = new RotateTransition(Duration.millis(500),
					closeBtn.getBtnOrangeImageView());

			if ((int) (menuHbox.getTranslateX()) == TOX_SMALL_MENU) {
				// si la menuHbox est à -985 on ouvre un peu
				moveTransition.setDuration(DURATION_TIME);
				moveTransition.setNode(menuHbox);
				moveTransition.setToX(TOX_MEDIUM_MENU);
				moveTransition.play();

				// on fait pivoter l'image de 180°
				rotateTransition.setByAngle(180);
				rotateTransition.play();

			} else if ((int) (menuHbox.getTranslateX()) == TOX_MEDIUM_MENU) {
				// sinon si la menuHbox est à -600 on referme
				moveTransition.setDuration(DURATION_TIME);
				moveTransition.setNode(menuHbox);
				moveTransition.setToX(TOX_SMALL_MENU);
				moveTransition.play();

				// on fait pivoter l'image de 180°
				rotateTransition.setByAngle(180);
				rotateTransition.play();

			} else {
				// sinon on ferme
				moveTransition.setDuration(DURATION_TIME);
				moveTransition.setNode(menuHbox);
				moveTransition.setToX(TOX_SMALL_MENU);
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

				// on agrandi tous les boutons
				setLarger(scopeBtn);
				setLarger(addBtn);
				setLarger(removeBtn);
				setLarger(updateBtn);
				setLarger(printBtn);
				setLarger(seeMemberBtn);
				setLarger(quitBtn);
			}
		});

		/* SCOPE CONTENT : configuration des boutons search et refresh */

		// Search button
		Button searchBtn = scopeContentVbox.getRightButton();
		searchBtn.setOnAction(event -> {
			// on récupère tous les textfield
			String[] data = grabInfos(scopeContentVbox);
			String familyNameFilter = data[0];
			String firstNameFilter = data[1];
			String countyFilter = data[2];
			String cursusFilter = data[3];
			String yearInFilter = data[4];

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
					filters = filters && intern.getCountyString().toUpperCase().contains(countyFilter.toUpperCase());
				}

				if (cursusFilter != null) {
					filters = filters && intern.getCursus().toUpperCase().contains(cursusFilter.toUpperCase());
				}

				if (yearInFilter != null) {
					filters = filters && intern.getYearInString().toUpperCase().contains(yearInFilter.toUpperCase());
				}

				return filters;
			});

			moveTransition.setDuration(DURATION_TIME);
			moveTransition.setNode(menuHbox);
			moveTransition.setToX(TOX_SMALL_MENU);
			moveTransition.play();

			closeBtn.getBtnGreyImageView().setVisible(false);
			closeBtn.getBtnOrangeImageView().setVisible(true);

			changeToOrange(addBtn);
			changeToOrange(removeBtn);
			changeToOrange(updateBtn);
			changeToOrange(printBtn);
			changeToOrange(seeMemberBtn);
			changeToOrange(quitBtn);

			// on agrandi tous les boutons
			setLarger(scopeBtn);
			setLarger(addBtn);
			setLarger(removeBtn);
			setLarger(updateBtn);
			setLarger(printBtn);
			setLarger(seeMemberBtn);
			setLarger(quitBtn);

		});

		// Refresh button
		Button refreshBtn = scopeContentVbox.getLeftButton();
		refreshBtn.setOnAction(event -> {
			refresh(scopeContentVbox.getGridPaneFamilyName());
			refresh(scopeContentVbox.getGridPaneFirstName());
			refresh(scopeContentVbox.getGridPaneCounty());
			refresh(scopeContentVbox.getGridPaneCursus());
			refresh(scopeContentVbox.getGridPaneYearIn());
		});

		/* ADD CONTENT : configuration des boutons annuler et ajouter */

		// ajouter
		Button addContentAddBtn = addContentVbox.getRightButton();
		addContentAddBtn.setOnAction(event -> {
			String[] data = grabInfos(addContentVbox);
			InternDao dao = new InternDao();
			dao.insert(new Intern(data[0], data[1], Integer.parseInt(data[2]), data[3], Integer.parseInt(data[4])));
		});
		
		// Annuler button
		Button addcontentCancelBtn = addContentVbox.getLeftButton();
		addcontentCancelBtn.setOnAction(event -> {
			refresh(addContentVbox.getGridPaneFamilyName());
			refresh(addContentVbox.getGridPaneFirstName());
			refresh(addContentVbox.getGridPaneCounty());
			refresh(addContentVbox.getGridPaneCursus());
			refresh(addContentVbox.getGridPaneYearIn());
		});
	}
	
		
	private String[] grabInfos(RepetitiveScene scene) {
		// on récupère tous les textfield
		String familyName = scene.getGridPaneFamilyName().getText();
		String firstName = scene.getGridPaneFirstName().getText();
		String county = scene.getGridPaneCounty().getText();
		String cursus = scene.getGridPaneCursus().getText();
		String yearIn = scene.getGridPaneYearIn().getText();
		String[] data = {familyName, firstName, county, cursus, yearIn};
		return data;
	}

	private void refresh(TextField textField) {
		textField.setText("");
	}

	public void configureButtonAction(StackPaneMenubar buttonClicked, VBox mainContentToShow, HBox menuHbox,
			List<StackPaneMenubar> otherButtons) {

		buttonClicked.getButton().setOnAction(event -> {

			// On remplace le contenu de la HBox
			menuHbox.getChildren().set(0, mainContentToShow);

			TranslateTransition moveTransition = new TranslateTransition(Duration.millis(500), menuHbox);
			moveTransition.setToX(TOX_LARGE_MENU);
			moveTransition.play();

			// on fait apparaitre la croix et disparaitre la fleche
			otherButtons.get(0).getBtnGreyImageView().setVisible(true);
			otherButtons.get(0).getBtnOrangeImageView().setVisible(false);

			// on passe tous les boutons à gris
			changeToGrey(otherButtons.get(1));
			changeToGrey(otherButtons.get(2));
			changeToGrey(otherButtons.get(3));
			changeToGrey(otherButtons.get(4));
			changeToGrey(otherButtons.get(5));
			changeToGrey(otherButtons.get(6));

			// on met à jour la couleur du bouton cliqué en orange
			changeToOrange(buttonClicked);

			// on diminue tous les boutons
			setSmaller(buttonClicked);
			setSmaller(otherButtons.get(1));
			setSmaller(otherButtons.get(2));
			setSmaller(otherButtons.get(3));
			setSmaller(otherButtons.get(4));
			setSmaller(otherButtons.get(5));
			setSmaller(otherButtons.get(6));

		});

		addHoverEffect(buttonClicked);

	}


	private void setSmaller(StackPaneMenubar stackPaneMenubar) {
		// on change la taille du bouton à 100
		stackPaneMenubar.getButton().setPrefSize(BTN_SMALL_WIDTH, BTN_HEIGHT);
		stackPaneMenubar.getButton().setTranslateX(TOX_SMALL_BTN);

		// on fait disparaitre largeSvg et apparaitre smallSvg
		stackPaneMenubar.getSmallSvgPath().setVisible(true);
		stackPaneMenubar.getLargeSvgPath().setVisible(false);

		// on fait disparaitre le label
		stackPaneMenubar.getLabel().setVisible(false);
	}

	private void setLarger(StackPaneMenubar stackPaneMenubar) {
		// pause de 1 sec
		PauseTransition pause = new PauseTransition(DURATION_TIME);

		// après la pause :
		pause.setOnFinished(event -> {
			stackPaneMenubar.getButton().setPrefSize(BTN_LARGE_WIDTH, BTN_HEIGHT);
			stackPaneMenubar.getButton().setTranslateX(TOX_LARGE_BTN);
			// on fait disparaitre smallSvg et apparaitre LargeSvg
			stackPaneMenubar.getSmallSvgPath().setVisible(false);
			stackPaneMenubar.getLargeSvgPath().setVisible(true);

			// on fait apparaitre le label
			stackPaneMenubar.getLabel().setVisible(true);
		});

		// on démarre
		pause.play();
	}

	private void addHoverEffect(StackPaneMenubar buttonSP) {

		// Variables pour stocker les couleurs
		String[] initialColorHex = new String[2];

		// Appliquer l'effet de survol
		buttonSP.getButton().setOnMouseEntered(event -> {

			// On récup et stocke la couleur initiale au survol de la souris
			initialColorHex[0] = getCurrentColor(buttonSP.getLargeSvgPath());

			if (initialColorHex[0] != null) {
				Color currentColor = Color.web(initialColorHex[0]);

				if (currentColor.equals(ORANGE_COLOR)) {
					buttonSP.getLargeSvgPath().setFill(HOVER_ORANGE_COLOR);
					buttonSP.getSmallSvgPath().setFill(HOVER_ORANGE_COLOR);
				} else {
					buttonSP.getLargeSvgPath().setFill(HOVER_GREY_COLOR);
					buttonSP.getSmallSvgPath().setFill(HOVER_GREY_COLOR);
				}
			}
		});

		// On restaure la couleur initiale à la sortie de la souris
		buttonSP.getButton().setOnMouseExited(event -> {

			initialColorHex[1] = getCurrentColor(buttonSP.getLargeSvgPath());

			if (initialColorHex[1].equals(STRING_ORANGE_COLOR)) { // le bouton a été cliqué
				buttonSP.getLargeSvgPath().setFill(ORANGE_COLOR);
				buttonSP.getSmallSvgPath().setFill(ORANGE_COLOR);
			} else {

				if (initialColorHex[0].equals(STRING_ORANGE_COLOR)) {
					buttonSP.getLargeSvgPath().setFill(ORANGE_COLOR);
					buttonSP.getSmallSvgPath().setFill(ORANGE_COLOR);
				} else {
					buttonSP.getLargeSvgPath().setFill(GREY_COLOR);
					buttonSP.getSmallSvgPath().setFill(GREY_COLOR);
				}
			}
		});
	}

	private TableView<Intern> createTableView(FilteredList<Intern> filteredInterns) {

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
		applyColorTransition(button.getLargeSvgPath(), ORANGE_COLOR);
		button.getLargeSvgPath().setFill(ORANGE_COLOR);

		applyColorTransition(button.getSmallSvgPath(), ORANGE_COLOR);
		button.getSmallSvgPath().setFill(ORANGE_COLOR);

		button.getBtnOrangeImageView().setVisible(false);
		button.getBtnGreyImageView().setVisible(true);
	}

	private void changeToGrey(StackPaneMenubar button) {
		applyColorTransition(button.getLargeSvgPath(), GREY_COLOR);
		button.getLargeSvgPath().setFill(GREY_COLOR);

		applyColorTransition(button.getSmallSvgPath(), GREY_COLOR);
		button.getSmallSvgPath().setFill(GREY_COLOR);

		button.getBtnOrangeImageView().setVisible(true);
		button.getBtnGreyImageView().setVisible(false);
	}

	private void applyColorTransition(SVGPath svgPath, Color newColor) {
		Color initialColor = Color.web(getCurrentColor(svgPath));
		Color finalColor = newColor;

		// transition de couleur
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.ZERO, new KeyValue(svgPath.fillProperty(), initialColor)),
				new KeyFrame(Duration.millis(500), new KeyValue(svgPath.fillProperty(), finalColor)));
		timeline.play();
	}

	private String getCurrentColor(SVGPath svgPath) {
		// couleur actuelle de remplissage de l'SVGPath
		Paint fill = svgPath.getFill();

		Color color = (Color) fill;
		return String.format("#%02X%02X%02X", (int) (color.getRed() * 255), (int) (color.getGreen() * 255),
				(int) (color.getBlue() * 255));

	}
}
