package fr.isika.cda27.teamJADE.view.mainIntern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import fr.isika.cda27.teamJADE.model.Intern;
import fr.isika.cda27.teamJADE.model.InternDao;
import fr.isika.cda27.teamJADE.model.Member;
import fr.isika.cda27.teamJADE.utilz.CustomTextField;
import fr.isika.cda27.teamJADE.view.help.HelpSceneAdmin;
import fr.isika.cda27.teamJADE.view.help.HelpSceneNotAdmin;
import fr.isika.cda27.teamJADE.view.help.StackPaneHelp;
import fr.isika.cda27.teamJADE.view.mainMember.MembersRepetitivePane;
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
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
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

public class InternsMainScene extends AnchorPane {

	private ArrayList<Intern> list;
	private ObservableList<Intern> observableInterns;
	private FilteredList<Intern> filteredInterns;
	private TableView<Intern> tableView;
	private Intern selected;
	private boolean showAdminView;

	/**
	 * Crée une instance de `InternsMainScene` en configurant l'interface
	 * utilisateur pour voir la table des stagiaires. Configure les actions des
	 * boutons pour gérer les événements comme les clics et les transitions.
	 *
	 * @param connectedMember Le membre actuellement connecté. Utilisé pour
	 *                        déterminer si la vue administrateur doit être
	 *                        affichée.
	 */
	public InternsMainScene(Member connectedMember) {

		boolean showAdminView = connectedMember.isAdmin();

		// AnchorPane
		this.setPrefSize(1280, 720);

		// police
		Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/KronaOne-Regular.ttf"), 24);

		// StackPane qui va contenir la TableView, la menuHbox et la StackPane Help
		StackPane bgStackPane = new StackPane();
		bgStackPane.setPrefSize(1280, 720);
		bgStackPane.setStyle("-fx-background-color: #272727;");

		StackPaneHelp stackPaneHelp = new StackPaneHelp("help.png", "help_hover.png");
		StackPane.setAlignment(stackPaneHelp, Pos.TOP_RIGHT);

		stackPaneHelp.setMaxSize(75, 75);

		stackPaneHelp.getButton().setOnAction(event -> {
			Stage stage = ((Stage) InternsMainScene.this.getScene().getWindow());
			Scene scene;
			// Si on est admin
			if (showAdminView) {
				scene = new Scene(new HelpSceneAdmin(new InternsMainScene(connectedMember)));
			} else {
				scene = new Scene(new HelpSceneNotAdmin(new InternsMainScene(connectedMember)));
			}
			scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
			stage.setScene(scene);
		});

		// TableView
		// données d'exemple
		InternDao internDao = new InternDao();
		this.list = new ArrayList<Intern>();
		this.list = internDao.sortView(0, list);

		this.observableInterns = FXCollections.observableArrayList(this.list);
		this.filteredInterns = new FilteredList<>(this.observableInterns, p -> true);
		this.tableView = createTableView(this.filteredInterns);

		// HBox du menu
		HBox menuHbox = new HBox();
		menuHbox.setMaxWidth(990);
		menuHbox.setMaxHeight(720);
		menuHbox.setStyle("-fx-background-color: #DD734C; -fx-background-radius: 70;");
		menuHbox.setEffect(DROP_SHADOW);
		menuHbox.setTranslateX(TOX_SMALL_MENU);

		// VBox avec les contenu pringipaux à gauche du menu
		ScopePane scopeContentVbox = new ScopePane();
		AddPane addContentVbox = new AddPane();
		RemovePane removeContentVbox = new RemovePane();
		UpdatePane updateContentVbox = new UpdatePane();
		PrintPane printContentVbox = new PrintPane(tableView);
		SeeMembersPane seeMembersContentVbox = new SeeMembersPane(connectedMember);
		QuitPane quitContentVbox = new QuitPane();

		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Intern>() {
			@Override
			public void changed(ObservableValue<? extends Intern> observableValue, Intern oldValue, Intern newValue) {
				String[] gridPaneLabelsList = new String[5];
				selected = newValue;

				gridPaneLabelsList[0] = newValue == null ? " " : newValue.getFamilyName();
				gridPaneLabelsList[1] = newValue == null ? " " : newValue.getFirstName();
				gridPaneLabelsList[2] = newValue == null ? " " : newValue.getCountyString();
				gridPaneLabelsList[3] = newValue == null ? " " : newValue.getCursus();
				gridPaneLabelsList[4] = newValue == null ? " " : newValue.getYearInString();

				removeContentVbox.setGridPane(gridPaneLabelsList);
				updateContentVbox.getGridPaneFamilyName().setText(gridPaneLabelsList[0]);
				updateContentVbox.getGridPaneFirstName().setText(gridPaneLabelsList[1]);
				updateContentVbox.getGridPaneCounty().setText(gridPaneLabelsList[2]);
				updateContentVbox.getGridPaneCursus().setText(gridPaneLabelsList[3]);
				updateContentVbox.getGridPaneYearIn().setText(gridPaneLabelsList[4]);

				removeContentVbox.getRightButton().setDisable(false);
			}
		});

		// VBox avec les boutons du menu
		VBox menubarVBox = new VBox();

		// On crée la croix/fleche du haut
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

		// Si on est pas admin on rend les boutons removeBtn, updateBtn et seeMemberBtn
		// invisibles
		if (!showAdminView) {
			// on passe btnContainer en pos 0
			removeBtn.getChildren().remove(1);
			removeBtn.getChildren().add(0, removeBtn.getBtnContainer());

			updateBtn.getChildren().remove(1);
			updateBtn.getChildren().add(0, updateBtn.getBtnContainer());

			seeMemberBtn.getChildren().remove(1);
			seeMemberBtn.getChildren().add(0, seeMemberBtn.getBtnContainer());

			// on enleve le tooltip
			removeBtn.getButton().setTooltip(null);
			updateBtn.getButton().setTooltip(null);
			seeMemberBtn.getButton().setTooltip(null);
		}

		// On crée le bouton quitter
		StackPaneMenubar quitBtn = new StackPaneMenubar("deconnexion_orange.png", "deconnexion_gris.png", "Déconnexion",
				SMALL_PATH_BOT, LARGE_PATH_BOT);

		// On ajoute la croix (invisible pour le moment)
		menubarVBox.getChildren().add(closeBtn);

		// On ajoute tous les boutons dans le VBox
		menubarVBox.getChildren().addAll(scopeBtn, printBtn, addBtn, removeBtn, updateBtn, seeMemberBtn);

		// On ajoute le bouton quitter
		menubarVBox.getChildren().add(quitBtn);

		// On ajoute le contenu de gauche et de droite a la HBox du menu
		menuHbox.getChildren().addAll(scopeContentVbox, menubarVBox);

		// On ajoute la tableview et menuhbox a la stackpane
		bgStackPane.getChildren().addAll(tableView, menuHbox, stackPaneHelp);

		// On ajoute la stackpane
		this.getChildren().add(bgStackPane);

		/* ---------------------------------------------------------------- */

		/* BOUTONS ACCTIONS */

		TranslateTransition moveTransition = new TranslateTransition();

		/*
		 * On configure les listes de boutons (qui sont des StackPaneMenubar) dans
		 * chaque cas de figure Ex : si le bouton cliqué est scopeBtn, alors
		 * scopeBtnConfig sera tous les autres boutons de la VBox. Et on fait ça pour
		 * chaque bouton afin de mettre tous les autres boutons en gris et le bouton
		 * cliqué en orange. Et de faire apparaite la croix du menu et disparaitre la
		 * fleche.
		 */
		List<StackPaneMenubar> listMenuBtn = Arrays.asList(closeBtn, scopeBtn, addBtn, printBtn, removeBtn, updateBtn,
				seeMemberBtn, quitBtn);

		/*
		 * On configure les actions pour chaque bouton en donnant en argument : 1) le
		 * bouton cliqué 2) le contenu à afficher 3) la HBox du menu 4) la liste des
		 * autres boutons
		 */
		configureButtonAction(scopeBtn, scopeContentVbox, menuHbox, listMenuBtn, showAdminView);
		configureButtonAction(printBtn, printContentVbox, menuHbox, listMenuBtn, showAdminView);
		configureButtonAction(addBtn, addContentVbox, menuHbox, listMenuBtn, showAdminView);

		// Si on est admin alors on configure l'action des boutons
		if (showAdminView) {
			configureButtonAction(removeBtn, removeContentVbox, menuHbox, listMenuBtn, showAdminView);
			configureButtonAction(updateBtn, updateContentVbox, menuHbox, listMenuBtn, showAdminView);
			configureButtonAction(seeMemberBtn, seeMembersContentVbox, menuHbox, listMenuBtn, showAdminView);
		}

		configureButtonAction(quitBtn, quitContentVbox, menuHbox, listMenuBtn, showAdminView);

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
				closeMenu(menubarVBox, showAdminView);

			}
		});

		/* SCOPE CONTENT : configuration des boutons search et refresh */

		// Search button
		Button scopeContentValidateBtn = scopeContentVbox.getRightButton();

		scopeContentValidateBtn.setOnAction(event -> {
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
					filters = filters && intern.getCountyString().contains(countyFilter);
				}

				if (cursusFilter != null) {
					filters = filters && intern.getCursus().toUpperCase().contains(cursusFilter.toUpperCase());
				}

				if (yearInFilter != null) {
					filters = filters && intern.getYearInString().contains(yearInFilter);
				}

				return filters;
			});

			closeMenu(menubarVBox, showAdminView);

		});

		// Refresh button
		Button scopeContentCancelBtn = scopeContentVbox.getLeftButton();
		scopeContentCancelBtn.setOnAction(event -> {
			refreshPane(scopeContentVbox);
		});

		/* ADD CONTENT : configuration des boutons annuler et ajouter */

		// ajouter
		Button addContentAddBtn = addContentVbox.getRightButton();

		addContentAddBtn.setOnAction(event -> {
			String[] data = grabInfos(addContentVbox);
			InternDao dao = new InternDao();

			boolean[] good = this.areAllFieldsCorrectlyFilled(addContentVbox);

			if (good[0] && good[1] && good[2] && good[3] && good[4]) {

				dao.insert(new Intern(data[0].toUpperCase(), data[1].toUpperCase().charAt(0) + data[1].substring(1),
						Integer.parseInt(data[2]), data[3].toUpperCase(), Integer.parseInt(data[4])));
				ArrayList<Intern> suppr = new ArrayList<Intern>();
				this.observableInterns.setAll(internDao.sortView(0, suppr));

				refreshPane(addContentVbox);
				closeMenu(menubarVBox, showAdminView);
			} else {
				addContentVbox.getLabelError().show();
			}

		});

		// Annuler button
		Button addcontentCancelBtn = addContentVbox.getLeftButton();
		addcontentCancelBtn.setOnAction(event -> {
			refreshPane(addContentVbox);
		});

		// Les actions en écrivant
		this.actionOnTyping(addContentVbox);

		/* REMOVE CONTENT : configuration des boutons annuler et valider */

		// valider suppression
		Button removeContentValidateBtn = removeContentVbox.getRightButton();
		removeContentValidateBtn.setOnAction(event -> {
			String[] data = grabInfos(removeContentVbox);
			InternDao dao = new InternDao();
			dao.delete(new Intern(data[0].toUpperCase(), data[1].toUpperCase().charAt(0) + data[1].substring(1),
					Integer.parseInt(data[2]), data[3].toUpperCase(), Integer.parseInt(data[4])));
			ArrayList<Intern> suppr = new ArrayList<Intern>();
			this.observableInterns.setAll(internDao.sortView(0, suppr));
			removeContentValidateBtn.setDisable(true);
			closeMenu(menubarVBox, showAdminView);
		});

		// Annuler button
		Button removeContentCancelBtn = removeContentVbox.getLeftButton();
		removeContentCancelBtn.setOnAction(event -> {
			this.tableView.getSelectionModel().clearSelection();
			removeContentValidateBtn.setDisable(true);
		});

		/* UPDATE CONTENT : configuration des boutons annuler et valider */

		// valider mise à jour
		Button updateContentUpdateBtn = updateContentVbox.getRightButton();
		updateContentUpdateBtn.setOnAction(event -> {
			Intern oldIntern = selected;
			String[] data = grabInfos(updateContentVbox);
			InternDao dao = new InternDao();
			boolean[] good = this.areAllFieldsCorrectlyFilled(updateContentVbox);

			if (good[0] && good[1] && good[2] && good[3] && good[4]) {

				dao.update(
						new Intern(data[0].toUpperCase(), data[1].toUpperCase().charAt(0) + data[1].substring(1),
								Integer.parseInt(data[2]), data[3].toUpperCase(), Integer.parseInt(data[4])),
						oldIntern);

				this.tableView.getSelectionModel().clearSelection();
				refreshPane(updateContentVbox);
				this.closeMenu(menubarVBox, showAdminView);
				updateContentUpdateBtn.setDisable(true);
			} else {
				updateContentVbox.getLabelError().setVisible(true);
			}
		});

		// Annuler button
		Button updateContentCancelBtn = updateContentVbox.getLeftButton();
		updateContentCancelBtn.setOnAction(event -> {
			this.tableView.getSelectionModel().clearSelection();
			refreshPane(updateContentVbox);
			updateContentUpdateBtn.setDisable(true);
		});

		// Les actions en écrivant
		this.actionOnTyping(updateContentVbox);

		/* QUIT CONTENT : configuration du bouton annuler */
		Button quitContentCancelBtn = quitContentVbox.getLeftButton();
		quitContentCancelBtn.setOnAction(event -> {
			closeMenu(menubarVBox, showAdminView);
		});

		/* SEE MEMBER CONTENT : configuration du bouton annuler */
		Button seeMembersContentCancelBtn = seeMembersContentVbox.getLeftButton();
		seeMembersContentCancelBtn.setOnAction(event -> {
			closeMenu(menubarVBox, showAdminView);
		});

	}

	/**
	 * Ajoute un comportement à un champ de texte personnalisé
	 * ({@link CustomTextField}) pour vérifier sa validité à chaque saisie de
	 * l'utilisateur en fonction d'un type spécifique.Si un type non reconnu est
	 * fourni, un message d'erreur est affiché dans la console.
	 * 
	 * @param tf    le champ de texte personnalisé à surveiller
	 * @param error le label utilisé pour afficher les messages d'erreur
	 * @param type  le type de validation à appliquer ("name", "cursus", ou "int")
	 */
	private void actionOnTyping(RepetitivePane pane) {
		Label[] paneErrorLabels = this.getPaneErrorLabel(pane);
		TextField[] paneTextFields = new TextField[6];
		Button paneRightBtn = pane.getRightButton();

		String[] types = { "name", "name", "int", "cursus", "int", "skip" };
		
		paneTextFields = this.getPaneTextField(pane);
		
		for (int i = 0; i < paneTextFields.length; i++) {
			CustomTextField tf = (CustomTextField) paneTextFields[i];
			
			Label error = paneErrorLabels[i];
			String type = types[i];

			tf.setOnKeyReleased(event -> {

				boolean[] good = areAllFieldsCorrectlyFilled(pane);
				if (good[0] && good[1] && good[2] && good[3] && good[4]) {
					paneRightBtn.setDisable(false);

				} else {
					paneRightBtn.setDisable(true);
				}

				switch (type) {
				case "name":
					if (isStringNameCorrect(tf)) {
						error.setVisible(false);
						tf.setStyle(SET_BG_ORANGE_COLOR + "-fx-background-radius: 13; " + "-fx-border-radius: 13; "
								+ "-fx-border-color: transparent transparent #704739 transparent;");
					} else {
						error.setVisible(true);
						tf.setStyle(SET_BG_ORANGE_COLOR + "-fx-background-radius: 13; " + "-fx-border-radius: 13; "
								+ "-fx-border-color: red;");
					}
					break;
				case "cursus":
					if (isStringCursusCorrect(tf)) {
						error.setVisible(false);
						tf.setStyle(SET_BG_ORANGE_COLOR + "-fx-background-radius: 13; " + "-fx-border-radius: 13; "
								+ "-fx-border-color: transparent transparent #704739 transparent;");
					} else {
						error.setVisible(true);
						tf.setStyle(SET_BG_ORANGE_COLOR + "-fx-background-radius: 13; " + "-fx-border-radius: 13; "
								+ "-fx-border-color: red;");
					}
					break;
				case "int":
					if (isStringIntCorrect(tf)) {
						error.setVisible(false);
						tf.setStyle(SET_BG_ORANGE_COLOR + "-fx-background-radius: 13; " + "-fx-border-radius: 13; "
								+ "-fx-border-color: transparent transparent #704739 transparent;");
					} else {
						error.setVisible(true);
						tf.setStyle(SET_BG_ORANGE_COLOR + "-fx-background-radius: 13; " + "-fx-border-radius: 13; "
								+ "-fx-border-color: red;");
					}
					break;
				case "skip":
					break;
				default:
					System.err.println(
							"Veuillez renseigner un string type de valeur\"name\", \"cursus\" \"int\" \"skip\" \"mail\" \"admin\"  ou \"password\" . ");
					break;
				}
			});
		}
	}

	/**
	 * Récupère les informations saisies dans les différents champs de texte d'un
	 * {@link RepetitivePane} et les retourne sous forme de tableau de chaînes de
	 * caractères.
	 * 
	 * @param Pane L'objet {@link RepetitivePane} contenant les champs de texte à
	 *             extraire
	 * @return Un tableau de chaînes de caractères contenant les valeurs des champs
	 *         de texte dans l'ordre suivant : nom de famille, prénom, département,
	 *         formation suivie, année d'entrée
	 */
	private String[] grabInfos(RepetitivePane Pane) {
		// on récupère tous les textfield
		String familyName = Pane.getTextFamilyName().trim();
		String firstName = Pane.getTextFirstName().trim();
		String county = Pane.getTextCounty().trim();
		String cursus = Pane.getTextCursus().trim();
		String yearIn = Pane.getTextYearIn().trim();

		String[] data = { familyName, firstName, county, cursus, yearIn };
		return data;
	}

	/**
	 * Actualise le {@link RepetitivePane} spécifié en mettant à jour ses différents
	 * panneaux de grille. Cette méthode appelle la méthode {@link #refresh(TextField)}
	 * pour chaque panneau de grille associé au {@code RepetitivePane} fourni.
	 * 
	 * @param pane L'instance de {@link RepetitivePane} dont les panneaux de grille
	 *             doivent être actualisés. Ne doit pas être null.
	 */
	private void refreshPane(RepetitivePane pane) {
		refresh(pane.getGridPaneFamilyName());
		refresh(pane.getGridPaneFirstName());
		refresh(pane.getGridPaneCounty());
		refresh(pane.getGridPaneCursus());
		refresh(pane.getGridPaneYearIn());
	}
	
	/**
	 * Réinitialise le texte d'un champ de texte en le vidant.
	 *
	 * @param textField Le champ de texte dont le texte doit être réinitialisé. Cet
	 *                  objet ne doit pas être {@code null}.
	 */
	private void refresh(TextField textField) {
		textField.setText("");
	}

	/**
	 * Retourne un tableau de champs de texte associés à l'objet `RepetitivePane`.
	 *
	 * @param pane L'objet `RepetitivePane` dont les champs de texte doivent être
	 *             récupérés. Cet objet ne doit pas être {@code null}.
	 * @return Un tableau de champs de texte associés au `RepetitivePane`.
	 */
	private TextField[] getPaneTextField(RepetitivePane pane) {
		TextField[] tf = { pane.getGridPaneFamilyName(), pane.getGridPaneFirstName(), pane.getGridPaneCounty(),
				pane.getGridPaneCursus(), pane.getGridPaneYearIn() };
		return tf;
	}

	/**
	 * Retourne un tableau de champs de texte associés à un objet
	 * `MembersRepetitivePane`.
	 *
	 * @param pane L'objet `MembersRepetitivePane` dont les champs de texte doivent
	 *             être récupérés. Cet objet ne doit pas être {@code null}.
	 * @return Un tableau de champs de texte associés au `MembersRepetitivePane`.
	 */
	private TextField[] getMemberPaneTextField(MembersRepetitivePane pane) {
		TextField[] tf = { pane.getGridPaneFamilyName(), pane.getGridPaneName(), pane.getGridPaneAlias(),
				pane.getGridPaneEmail(), pane.getGridPaneEmail(), pane.getPasswordField() };
		return tf;
	}

	/**
	 * Retourne un tableau d'étiquettes d'erreur associées à un objet
	 * `RepetitivePane`.
	 *
	 * @param pane L'objet `RepetitivePane` dont les étiquettes d'erreur doivent
	 *             être récupérées. Cet objet ne doit pas être {@code null}.
	 * @return Un tableau d'étiquettes d'erreur associées au `RepetitivePane`.
	 */
	private Label[] getPaneErrorLabel(RepetitivePane pane) {

		Label[] labels = { pane.getFamilyNameErrorLabel(), pane.getFirstNameErrorLabel(), pane.getCountyErrorLabel(),
				pane.getCursusErrorLabel(), pane.getYearInErrorLabel() };
		return labels;
	}

	/**
	 * Configure l'action du bouton cliqué dans une interface utilisateur en
	 * modifiant le contenu affiché et en ajustant l'apparence des boutons.
	 * 
	 * @param buttonClicked     Le bouton qui a été cliqué. Ne doit pas être null.
	 * @param mainContentToShow Le contenu à afficher dans la HBox. Ne doit pas être
	 *                          null.
	 * @param menuHbox          La {@code HBox} dont le contenu sera modifié. Ne
	 *                          doit pas être null.
	 * @param otherButtons      La liste des autres boutons à modifier. Ne doit pas
	 *                          être null. et doit contenir au moins 7 éléments.
	 */
	public void configureButtonAction(StackPaneMenubar buttonClicked, RepetitivePane mainContentToShow, HBox menuHbox,
			List<StackPaneMenubar> listButtons, boolean showAdminView) {

		List<StackPaneMenubar> otherButtons = new ArrayList<StackPaneMenubar>();
		otherButtons.addAll(listButtons);
		otherButtons.remove(buttonClicked);

		buttonClicked.getButton().setOnAction(event -> {

			// On remplace le contenu de la HBox
			menuHbox.getChildren().set(0, mainContentToShow);

			// On rend les boutons du contenu en bouton par défaut (ceux qui réagissent
			// quand one appuie sur Entrée et échap)
			mainContentToShow.getLeftButton().setCancelButton(true);
			mainContentToShow.getRightButton().setDefaultButton(true);

			VBox menubarVBox = (VBox) menuHbox.getChildren().get(1);

			TranslateTransition moveTransition = new TranslateTransition(Duration.millis(500), menuHbox);
			moveTransition.setToX(TOX_LARGE_MENU);
			moveTransition.play();

			// on fait apparaitre la croix et disparaitre la fleche
			otherButtons.get(0).getBtnGreyImageView().setVisible(true);
			otherButtons.get(0).getBtnOrangeImageView().setVisible(false);

			// on diminue le bouton cliqué
			setSmaller(buttonClicked);

			// on diminue tous les boutons
			if (showAdminView) {
				for (int i = 1; i < 7; i++) {
					// on diminue tous les boutons
					setSmaller(otherButtons.get(i));

					// on passe tous les boutons à gris
					changeToGrey(otherButtons.get(i));
				}
			} else {
				for (int i = 1; i < 4; i++) {
					setSmaller(otherButtons.get(i));
					changeToGrey(otherButtons.get(i));
				}
				// 3 derniers sont removeBtn, updateBtn,seeMemberBtn
				for (int i = 4; i < 7; i++) {
					setSmallerNotAdminCase(otherButtons.get(i));
					changeToGreyNotAdminCase(otherButtons.get(i));
				}

			}

			// On set maxwidth de la menubarVBox à 100
			menubarVBox.setPrefWidth(100);
			menubarVBox.setTranslateX(200);
			otherButtons.get(0).setTranslateX(0);

			// on met à jour la couleur du bouton cliqué en orange
			changeToOrange(buttonClicked);

		});

		addHoverEffect(buttonClicked);

	}

	/**
	 * Ferme le menu en ajustant la taille et la visibilité des boutons dans le
	 * panneau de menu.
	 * 
	 * @param menubarVBox le conteneur {@link VBox} contenant les boutons du menu à
	 *                    fermer. Ne doit pas être null.
	 */
	public void closeMenu(VBox menubarVBox, boolean showAdminView) {
		TranslateTransition moveTransition = new TranslateTransition();

		moveTransition.setDuration(DURATION_TIME);
		moveTransition.setNode(menubarVBox.getParent());
		moveTransition.setToX(TOX_SMALL_MENU);
		moveTransition.play();
		
		StackPaneMenubar closeBtn = (StackPaneMenubar) menubarVBox.getChildren().get(0);
		StackPaneMenubar scopeBtn = (StackPaneMenubar) menubarVBox.getChildren().get(1);
		StackPaneMenubar printBtn = (StackPaneMenubar) menubarVBox.getChildren().get(2);
		StackPaneMenubar addBtn = (StackPaneMenubar) menubarVBox.getChildren().get(3);
		StackPaneMenubar removeBtn = (StackPaneMenubar) menubarVBox.getChildren().get(4);
		StackPaneMenubar updateBtn = (StackPaneMenubar) menubarVBox.getChildren().get(5);
		StackPaneMenubar seeMemberBtn = (StackPaneMenubar) menubarVBox.getChildren().get(6);
		StackPaneMenubar quitBtn = (StackPaneMenubar) menubarVBox.getChildren().get(7);

		closeBtn.getBtnGreyImageView().setVisible(false);
		closeBtn.getBtnOrangeImageView().setVisible(true);
		
		// On set maxwidth de la menubarVBox à 300
		menubarVBox.setPrefWidth(300);
		menubarVBox.setTranslateX(0);
		closeBtn.setTranslateX(100);

		// Changer la couleur des boutons en orange
		changeToOrange(scopeBtn);
		changeToOrange(addBtn);
		changeToOrange(printBtn);
		changeToOrange(quitBtn);

		if (!showAdminView) {
			changeToOrangeNotAdminCase(seeMemberBtn);
			changeToOrangeNotAdminCase(removeBtn);
			changeToOrangeNotAdminCase(updateBtn);
		} else {
			changeToOrange(seeMemberBtn);
			changeToOrange(removeBtn);
			changeToOrange(updateBtn);
		}

		// Agrandir tous les boutons
		setLarger(scopeBtn);
		setLarger(addBtn);
		setLarger(printBtn);
		setLarger(quitBtn);

		if (!showAdminView) {
			setLargerNotAdminCase(removeBtn);
			setLargerNotAdminCase(updateBtn);
			setLargerNotAdminCase(seeMemberBtn);
		} else {
			setLarger(removeBtn);
			setLarger(updateBtn);
			setLarger(seeMemberBtn);
		}

		InternDao internDao = new InternDao();
		ArrayList<Intern> suppr = new ArrayList<Intern>();
		this.observableInterns.setAll(internDao.sortView(0, suppr));
	}

	/**
	 * Réduit la taille et ajuste les propriétés visuelles du bouton représenté par
	 * l'objet {@link StackPaneMenubar}.
	 * 
	 * @param stackPaneMenubar L'objet {@link StackPaneMenubar} dont la taille et
	 *                         les propriétés visuelles doivent être modifiées.
	 */
	private void setSmaller(StackPaneMenubar stackPaneMenubar) {
		changeToOrange(stackPaneMenubar);

		// on change la taille du bouton à 100
		stackPaneMenubar.getButton().setPrefSize(BTN_SMALL_WIDTH, BTN_HEIGHT);
		stackPaneMenubar.getButton().setTranslateX(TOX_SMALL_BTN);

		// on remplace largeSvg par smallSvg
		stackPaneMenubar.getChildren().set(0, stackPaneMenubar.getSmallSvgPath());

		// on enlève le children leftSubcontainer de la HBox
		stackPaneMenubar.getBtnContainer().getChildren().remove(stackPaneMenubar.getLeftSubcontainer());
//		stackPaneMenubar.getBtnContainer().setMaxWidth(100);

	}

	/**
	 * Ajuste la taille et l'apparence du `StackPaneMenubar` pour l'utilisateur non
	 * administrateur (bouton plus petit).
	 *
	 * @param stackPaneMenubar L'instance de `StackPaneMenubar` qui sera modifiée.
	 *                         Cet objet ne doit pas être {@code null}.
	 */
	private void setSmallerNotAdminCase(StackPaneMenubar stackPaneMenubar) {
		changeToOrangeNotAdminCase(stackPaneMenubar);

		// on change la taille du bouton à 100
		stackPaneMenubar.getButton().setPrefSize(BTN_SMALL_WIDTH, BTN_HEIGHT);
		stackPaneMenubar.getButton().setTranslateX(TOX_SMALL_BTN);

		// on remplace largeSvg par smallSvg
		stackPaneMenubar.getChildren().set(1, stackPaneMenubar.getSmallSvgPath());

		// on enlève le children leftSubcontainer de la HBox
		stackPaneMenubar.getBtnContainer().getChildren().remove(stackPaneMenubar.getLeftSubcontainer());
//		stackPaneMenubar.getBtnContainer().setMaxWidth(100);

	}

	/**
	 * Agrandit la taille et ajuste les propriétés visuelles du bouton représenté
	 * par l'objet {@link StackPaneMenubar}.
	 * 
	 * @param stackPaneMenubar l'objet {@link StackPaneMenubar} dont la taille et
	 *                         les propriétés visuelles doivent être modifiées.
	 */
	private void setLarger(StackPaneMenubar stackPaneMenubar) {
		// on change la taille du bouton à 300
		stackPaneMenubar.getButton().setPrefSize(BTN_LARGE_WIDTH, BTN_HEIGHT);
		stackPaneMenubar.getBtnContainer().setMaxWidth(300);

		// on met le LeftSubcontainer en 0 de la HBox et le rightSubcontainer en 1
		stackPaneMenubar.getBtnContainer().getChildren().set(0, stackPaneMenubar.getLeftSubcontainer());
		stackPaneMenubar.getBtnContainer().getChildren().add(1, stackPaneMenubar.getRightSubcontainer());

		// on fait remplace smallSvg par LargeSvg
		stackPaneMenubar.getChildren().set(0, stackPaneMenubar.getLargeSvgPath());
		stackPaneMenubar.getChildren().set(1, stackPaneMenubar.getBtnContainer());

	}

	/**
	 * Ajuste la taille et l'apparence du `StackPaneMenubar` pour un utilisateur non
	 * administrateur (bouton plus grand).
	 *
	 * @param stackPaneMenubar L'instance de `StackPaneMenubar` qui sera modifiée.
	 *                         Cet objet ne doit pas être {@code null}.
	 */
	private void setLargerNotAdminCase(StackPaneMenubar stackPaneMenubar) {
		// on change la taille du bouton à 300
		stackPaneMenubar.getButton().setPrefSize(BTN_LARGE_WIDTH, BTN_HEIGHT);
		stackPaneMenubar.getBtnContainer().setMaxWidth(300);

		// on met le LeftSubcontainer en 0 de la HBox et le rightSubcontainer en 1
		stackPaneMenubar.getBtnContainer().getChildren().set(0, stackPaneMenubar.getLeftSubcontainer());
		stackPaneMenubar.getBtnContainer().getChildren().add(1, stackPaneMenubar.getRightSubcontainer());

		// on fait remplace smallSvg par LargeSvg
		stackPaneMenubar.getChildren().set(1, stackPaneMenubar.getLargeSvgPath());
		stackPaneMenubar.getChildren().set(0, stackPaneMenubar.getBtnContainer());

	}

	/**
	 * Ajoute un effet de survol au bouton représenté par l'objet
	 * {@link StackPaneMenubar}.
	 * 
	 * @param buttonSP L'objet {@link StackPaneMenubar} auquel l'effet de survol
	 *                 doit être appliqué. Ne doit pas être {@code null}.
	 */
	private void addHoverEffect(StackPaneMenubar buttonSP) {

		// Variables pour stocker les couleurs
		String[] initialColorHex = new String[2];

		// Appliquer l'effet de survol
		buttonSP.getButton().setOnMouseEntered(event -> {

			// On récup et stocke la couleur initiale au survol de la souris
			initialColorHex[0] = getCurrentColor((SVGPath) buttonSP.getChildren().get(0));
			if (initialColorHex[0] != null) {
				Color currentColor = Color.web(initialColorHex[0]);

				if (currentColor.equals(ORANGE_COLOR)) {
					((SVGPath) buttonSP.getChildren().get(0)).setFill(HOVER_ORANGE_COLOR);
				} else if (currentColor.equals(GREY_COLOR)) {
					((SVGPath) buttonSP.getChildren().get(0)).setFill(HOVER_GREY_COLOR);
				}
			}
		});

		// On restaure la couleur initiale à la sortie de la souris
		buttonSP.getButton().setOnMouseExited(event -> {

			initialColorHex[1] = getCurrentColor(((SVGPath) buttonSP.getChildren().get(0)));

			if (initialColorHex[1].equals(STRING_ORANGE_COLOR)) { // le bouton a été cliqué
				((SVGPath) buttonSP.getChildren().get(0)).setFill(ORANGE_COLOR);
			} else {

				if (initialColorHex[0].equals(STRING_ORANGE_COLOR)) {
					((SVGPath) buttonSP.getChildren().get(0)).setFill(ORANGE_COLOR);
				} else if (initialColorHex[0].equals(STRING_GREY_COLOR)) {
					((SVGPath) buttonSP.getChildren().get(0)).setFill(GREY_COLOR);

				}
			}
		});
	}

	/**
	 * Crée et configure une {@link TableView} pour afficher une liste filtrée
	 * d'internes.
	 * 
	 * @param filteredInterns la liste filtrée des internes à afficher dans la
	 *                        table. Ne doit pas être {@code null}.
	 * @return une instance de {@link TableView} configurée avec les colonnes et les
	 *         données fournies.
	 */
	private TableView<Intern> createTableView(FilteredList<Intern> filteredInterns) {

		TableView<Intern> tableView = new TableView<>(filteredInterns);

		// On met les colones
		double columnWidth = 1000 / 5;

		TableColumn<Intern, String> column1 = new TableColumn<>("Nom de famille");
		column1.setCellValueFactory(new PropertyValueFactory<>("familyName"));
		column1.setPrefWidth(columnWidth);

		TableColumn<Intern, String> column2 = new TableColumn<>("Prénom");
		column2.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		column2.setPrefWidth(columnWidth);

		TableColumn<Intern, String> column3 = new TableColumn<>("Département");
		column3.setCellValueFactory(new PropertyValueFactory<>("county"));
		column3.setPrefWidth(columnWidth);

		TableColumn<Intern, String> column4 = new TableColumn<>("Formation suivie");
		column4.setCellValueFactory(new PropertyValueFactory<>("cursus"));
		column4.setPrefWidth(columnWidth);

		TableColumn<Intern, String> column5 = new TableColumn<>("Année d'entrée");
		column5.setCellValueFactory(new PropertyValueFactory<>("yearIn"));
		column5.setPrefWidth(columnWidth);

		tableView.getColumns().addAll(column1, column2, column3, column4, column5);

		StackPane.setMargin(tableView, new Insets(70, 70, 70, 170));
		return tableView;
	}

	/**
	 * Change la couleur du bouton représenté par l'objet {@link StackPaneMenubar}
	 * en orange.
	 * 
	 * @param button l'objet {@link StackPaneMenubar} dont la couleur doit être
	 *               changée. Ne doit pas être {@code null}.
	 */
	private void changeToOrange(StackPaneMenubar button) {
		// on change la couleur du SVGPath
		applyColorTransition((SVGPath) button.getChildren().get(0), ORANGE_COLOR);
		((SVGPath) button.getChildren().get(0)).setFill(ORANGE_COLOR);

		button.getBtnOrangeImageView().setVisible(false);
		button.getBtnGreyImageView().setVisible(true);
	}

	/**
	 * Change l'apparence du bouton pour un utilisateur non-administrateur en le
	 * configurant avec une couleur orange.
	 *
	 * @param button L'instance de `StackPaneMenubar` dont l'apparence sera
	 *               modifiée. Cet objet ne doit pas être {@code null}.
	 */
	private void changeToOrangeNotAdminCase(StackPaneMenubar button) {
		// on change la couleur du SVGPath
		applyColorTransition((SVGPath) button.getChildren().get(1), ORANGE_COLOR);
		((SVGPath) button.getChildren().get(1)).setFill(ORANGE_COLOR);

		button.getBtnOrangeImageView().setVisible(false);
		button.getBtnGreyImageView().setVisible(true);
	}

	/**
	 * Change la couleur du bouton représenté par l'objet {@link StackPaneMenubar}
	 * en gris.
	 * 
	 * @param button l'objet {@link StackPaneMenubar} dont la couleur doit être
	 *               changée. Ne doit pas être {@code null}.
	 */
	private void changeToGrey(StackPaneMenubar button) {
		// on change la couleur du SVGPath
		applyColorTransition((SVGPath) button.getChildren().get(0), GREY_COLOR);
		((SVGPath) button.getChildren().get(0)).setFill(GREY_COLOR);

		button.getBtnOrangeImageView().setVisible(true);
		button.getBtnGreyImageView().setVisible(false);
	}

	/**
	 * Modifie l'apparence du bouton pour un utilisateur non-administrateur en le
	 * configurant avec une couleur grise.
	 *
	 * @param button L'instance de `StackPaneMenubar` dont l'apparence sera
	 *               modifiée. Cet objet ne doit pas être {@code null}.
	 */
	private void changeToGreyNotAdminCase(StackPaneMenubar button) {
		// on change la couleur du SVGPath
		applyColorTransition((SVGPath) button.getChildren().get(1), GREY_COLOR);
		((SVGPath) button.getChildren().get(1)).setFill(GREY_COLOR);

		button.getBtnOrangeImageView().setVisible(true);
		button.getBtnGreyImageView().setVisible(false);
	}

	/**
	 * Applique une transition de couleur à un objet {@link SVGPath}.
	 * 
	 * @param svgPath  l'objet {@link SVGPath} dont la couleur de remplissage doit
	 *                 être animée. Ne doit pas être {@code null}.
	 * @param newColor la nouvelle couleur vers laquelle la transition doit être
	 *                 effectuée. Ne doit pas être {@code null}.
	 */
	private void applyColorTransition(SVGPath svgPath, Color newColor) {
		Color initialColor = Color.web(getCurrentColor(svgPath));
		Color finalColor = newColor;

		// transition de couleur
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.ZERO, new KeyValue(svgPath.fillProperty(), initialColor)),
				new KeyFrame(Duration.millis(500), new KeyValue(svgPath.fillProperty(), finalColor)));
		timeline.play();
	}

	/**
	 * Vérifie si tous les champs d'un {@link RepetitivePane} sont correctement
	 * remplis.
	 * 
	 * @param scene L'objet {@link RepetitivePane} contenant les champs à vérifier.
	 *              Ne doit pas être {@code null}.
	 * @return Un tableau de booléens où chaque élément indique si le champ
	 *         correspondant est correctement rempli. La première valeur vérifie le
	 *         nom de famille, la deuxième vérifie le prénom, la troisième vérifie
	 *         le département , la quatrième vérifie la formation suivie et la
	 *         cinquième vérifie l'année d'entrée.
	 */
	private boolean[] areAllFieldsCorrectlyFilled(RepetitivePane scene) {

		boolean[] tab = { isStringNameCorrect((CustomTextField) scene.getGridPaneFamilyName()),
				isStringNameCorrect((CustomTextField) scene.getGridPaneFirstName()),
				isStringIntCorrect((CustomTextField) scene.getGridPaneCounty()),
				isStringCursusCorrect((CustomTextField) scene.getGridPaneCursus()),
				isStringIntCorrect((CustomTextField) scene.getGridPaneYearIn()) };
		return tab;
	}

	/**
	 * Vérifie si le texte d'un champ {@link CustomTextField} est correctement
	 * formaté pour un nom.
	 * 
	 * Cette méthode effectue les vérifications suivantes sur le texte du champ :
	 * <ul>
	 * <li>Le texte ne doit pas être vide ou uniquement composé d'espaces.</li>
	 * <li>Le texte ne doit contenir que des lettres, des tirets et des
	 * espaces.</li>
	 * <li>La longueur du texte ne doit pas dépasser le nombre maximal de caractères
	 * autorisé.</li>
	 * </ul>
	 * 
	 * @param field le champ {@link CustomTextField} dont le texte doit être
	 *              vérifié. Ne doit pas être {@code null}.
	 * @return {@code true} si le texte du champ est valide selon les critères
	 *         spécifiés ; {@code false} sinon.
	 */
	private boolean isStringNameCorrect(CustomTextField field) {
		String text = field.getText().trim();
		return !(text.length() <= 0 || Pattern.compile("[^\\p{L}-\\s]").matcher(text).find()
				|| text.length() > field.getMaxChars());
	}

	/**
	 * Vérifie si le texte d'un champ {@link CustomTextField} est correctement
	 * formaté pour un cursus.
	 * 
	 * Cette méthode effectue les vérifications suivantes sur le texte du champ :
	 * <ul>
	 * <li>Le texte ne doit pas être vide ou uniquement composé d'espaces.</li>
	 * <li>Le texte ne doit contenir que des lettres, des tirets, des espaces et des
	 * chiffres.</li>
	 * <li>La longueur du texte ne doit pas dépasser le nombre maximal de caractères
	 * autorisé.</li>
	 * </ul>
	 * 
	 * @param field le champ {@link CustomTextField} dont le texte doit être
	 *              vérifié. Ne doit pas être {@code null}.
	 * @return {@code true} si le texte du champ est valide selon les critères
	 *         spécifiés ; {@code false} sinon.
	 */
	private boolean isStringCursusCorrect(CustomTextField field) {
		String text = field.getText().trim();
		return !(text.length() <= 0 || Pattern.compile("[^\\p{L}-\\s\\d]").matcher(text).find()
				|| text.length() > field.getMaxChars());
	}

	/**
	 * Vérifie si le texte d'un champ {@link CustomTextField} est correctement
	 * formaté pour un entier.
	 * 
	 * Cette méthode effectue les vérifications suivantes sur le texte du champ :
	 * <ul>
	 * <li>Le texte ne doit pas être vide ou uniquement composé d'espaces.</li>
	 * <li>Le texte ne doit contenir que des chiffres.</li>
	 * <li>La longueur du texte ne doit pas dépasser le nombre maximal de caractères
	 * autorisé.</li>
	 * </ul>
	 * 
	 * @param field le champ {@link CustomTextField} dont le texte doit être
	 *              vérifié. Ne doit pas être {@code null}.
	 * @return {@code true} si le texte du champ est valide selon les critères
	 *         spécifiés ; {@code false} sinon.
	 */
	private boolean isStringIntCorrect(CustomTextField field) {
		String text = field.getText().trim();
		return !(text.length() <= 0 || Pattern.compile("[\\D]").matcher(text).find()
				|| text.length() > field.getMaxChars());
	}

	/**
	 * Récupère la couleur actuelle de remplissage d'un objet {@link SVGPath} sous
	 * forme de code hexadécimal.
	 * 
	 * @param svgPath l'objet {@link SVGPath} dont la couleur de remplissage doit
	 *                être récupérée. Ne doit pas être {@code null}.
	 * @return la couleur de remplissage de l'objet {@link SVGPath} sous forme de
	 *         chaîne hexadécimale (par exemple, "#FF5733").
	 */
	private String getCurrentColor(SVGPath svgPath) {
		// couleur actuelle de remplissage de l'SVGPath
		Paint fill = svgPath.getFill();

		Color color = (Color) fill;
		return String.format("#%02X%02X%02X", (int) (color.getRed() * 255), (int) (color.getGreen() * 255),
				(int) (color.getBlue() * 255));

	}

}
