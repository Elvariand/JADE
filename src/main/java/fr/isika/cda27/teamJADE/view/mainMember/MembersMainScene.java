package fr.isika.cda27.teamJADE.view.mainMember;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import com.itextpdf.text.log.SysoCounter;
import fr.isika.cda27.teamJADE.model.Intern;
import fr.isika.cda27.teamJADE.model.InternDao;
import fr.isika.cda27.teamJADE.model.Member;
import fr.isika.cda27.teamJADE.model.MemberDao;
import fr.isika.cda27.teamJADE.utilz.FadingErrorLabel;
import fr.isika.cda27.teamJADE.view.help.HelpSceneNotAdmin;
import fr.isika.cda27.teamJADE.view.help.StackPaneHelp;
import fr.isika.cda27.teamJADE.view.mainIntern.AddPane;
import fr.isika.cda27.teamJADE.view.mainIntern.CustomMainScene;
import fr.isika.cda27.teamJADE.view.mainIntern.PrintPane;
import fr.isika.cda27.teamJADE.view.mainIntern.QuitPane;
import fr.isika.cda27.teamJADE.view.mainIntern.RemovePane;
import fr.isika.cda27.teamJADE.view.mainIntern.RepetitivePane;
import fr.isika.cda27.teamJADE.view.mainIntern.ScopePane;
import fr.isika.cda27.teamJADE.view.mainIntern.SeeMembersPane;
import fr.isika.cda27.teamJADE.view.mainIntern.StackPaneMenubar;
import fr.isika.cda27.teamJADE.view.mainIntern.UpdatePane;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MainSceneValues.*;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.ShadowSet.*;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.Colors.*;

public class MembersMainScene extends AnchorPane {

	private ArrayList<Member> list;
	private ObservableList<Member> observableMembers;
	private FilteredList<Member> filteredMembers;
	private TableView<Member> tableView;
	private Member selected;

	public MembersMainScene(Member connectedMember) {

		boolean showAdminView = connectedMember.isAdmin();

		// AnchorPane
		this.setPrefSize(1280, 720);

		// police
		Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/KronaOne-Regular.ttf"), 24);

		// StackPane qui va contenir la TableView et menuHbox
		StackPane bgStackPane = new StackPane();
		bgStackPane.setPrefSize(1280, 720);
		bgStackPane.setStyle("-fx-background-color: #272727;");

		StackPaneHelp stackPaneHelp = new StackPaneHelp("help.png", "help_hover.png");
		StackPane.setAlignment(stackPaneHelp, Pos.TOP_RIGHT);

		stackPaneHelp.setMaxSize(75, 75);

		stackPaneHelp.getButton().setOnAction(event -> {
			Stage stage = ((Stage) MembersMainScene.this.getScene().getWindow());
//			Scene scene = new Scene(new HelpSceneAdmin(new MembersMainScene()));
			Scene scene = new Scene(new HelpSceneNotAdmin(new MembersMainScene(connectedMember)));
			scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
			stage.setScene(scene);
		});

		// TableView
		// données d'exemple
		MemberDao memberDao = new MemberDao();
		this.list = new ArrayList<Member>();
		this.list = memberDao.sortView(0, list);

		this.observableMembers = FXCollections.observableArrayList(this.list);
		this.filteredMembers = new FilteredList<>(this.observableMembers, p -> true);
		this.tableView = createTableView(this.filteredMembers);

		// HBox du menu
		HBox menuHbox = new HBox();
		menuHbox.setMaxWidth(990);
		menuHbox.setMaxHeight(720);
		menuHbox.setStyle("-fx-background-color: #DD734C; -fx-background-radius: 70;");
		menuHbox.setEffect(DROP_SHADOW);
		menuHbox.setTranslateX(TOX_SMALL_MENU);

		// VBox avec les contenu pringipaux à gauche du menu
		MembersScopePane scopeContentVbox = new MembersScopePane();

		MembersAddPane addContentVbox = new MembersAddPane();

		MembersRemovePane removeContentVbox = new MembersRemovePane();
		MembersUpdatePane updateContentVbox = new MembersUpdatePane();
		PrintPane printContentVbox = new PrintPane(tableView);
		SeeMembersPane seeMembersContentVbox = new SeeMembersPane(connectedMember);
		QuitPane quitContentVbox = new QuitPane();

		// On change le Label du titre de seeMembersContentVbox
		seeMembersContentVbox.setTitleLabelText("Souhaitez vous voir \nla liste des stagiaires ?");

		// On récupère les données de la ligne sélectionnée dans la TableView
		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Member>() {
			@Override
			public void changed(ObservableValue<? extends Member> observableValue, Member oldValue, Member newValue) {
				String[] gridPaneLabelsList = new String[5];
				selected = newValue;

				gridPaneLabelsList[0] = newValue == null ? " " : newValue.getFamilyName();
				gridPaneLabelsList[1] = newValue == null ? " " : newValue.getName();
				gridPaneLabelsList[2] = newValue == null ? " " : newValue.getAlias();
				gridPaneLabelsList[3] = newValue == null ? " " : newValue.getEmail();

				gridPaneLabelsList[4] = newValue == null ? " " : newValue.getAdmin();

				removeContentVbox.setGridPane(gridPaneLabelsList);
				updateContentVbox.getGridPaneFamilyName().setText(gridPaneLabelsList[0]);
				updateContentVbox.getGridPaneName().setText(gridPaneLabelsList[1]);
				updateContentVbox.getGridPaneAlias().setText(gridPaneLabelsList[2]);
				updateContentVbox.getGridPaneEmail().setText(gridPaneLabelsList[3]);
				if (newValue != null) {
					updateContentVbox.setRadioButton(newValue.isAdmin());
					updateContentVbox.getPasswordField().setText(newValue.getPassword());
				}
			}
		});

		// VBox avec les boutons du menu
		VBox menubarVBox = new VBox();
//		menubarVBox.setPrefSize(MENUBAR_WIDTH, MENUBAR_HEIGHT);
//		menubarVBox.setStyle("-fx-border-color: green;");
//		menubarVBox.setStyle("-fx-background-color: transparent; -fx-border-color: green; -fx-border-width: 5;");

		// On crée la croix du haut

		StackPaneMenubar closeBtn = new StackPaneMenubar("croix.png", "fleche.png", PATH_TOP, 30);

		// On crée les autres boutons
		StackPaneMenubar scopeBtn = new StackPaneMenubar("loupe_orange.png", "loupe_grise.png", "Rechercher");
		StackPaneMenubar addBtn = new StackPaneMenubar("ajout_membre_orange.png", "ajout_membre_gris.png", "Ajouter");
		StackPaneMenubar removeBtn = new StackPaneMenubar("suppr_membre_orange.png", "suppr_membre_gris.png",
				"Supprimer");
		StackPaneMenubar updateBtn = new StackPaneMenubar("modif_membre_orange.png", "modif_membre_gris.png",
				"Modifier");
		StackPaneMenubar printBtn = new StackPaneMenubar("imprimante_orange.png", "imprimante_grise.png", "Imprimer");
		StackPaneMenubar seeMemberBtn = new StackPaneMenubar("voir_stagiaire_orange.png", "voir_stagiaire_gris.png",
				"Membres");
		seeMemberBtn.setTooltipText("Stagiaires");
		seeMemberBtn.setLabelText("Stagiaires");

		// On crée le bouton quitter
		StackPaneMenubar quitBtn = new StackPaneMenubar("deconnexion_orange.png", "deconnexion_gris.png", "Déconnexion",
				SMALL_PATH_BOT, LARGE_PATH_BOT);

		// On ajoute la croix (invisible pour le moment)
		menubarVBox.getChildren().add(closeBtn);
		// On ajoute tous les boutons dans le VBox
		menubarVBox.getChildren().addAll(scopeBtn, printBtn, addBtn, removeBtn, updateBtn, seeMemberBtn);

		// Si on est pas admin on rend les boutons removeBtn, updateBtn et seeMemberBtn
		// invisibles
		if (!showAdminView) {
			// on rend l'image invisible pour les 3 boutons
			removeBtn.getBtnOrangeImageView().setVisible(false);
			removeBtn.getBtnGreyImageView().setVisible(false);
			updateBtn.getBtnOrangeImageView().setVisible(false);
			updateBtn.getBtnGreyImageView().setVisible(false);
			seeMemberBtn.getBtnOrangeImageView().setVisible(false);
			seeMemberBtn.getBtnGreyImageView().setVisible(false);
		}

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
		 * bouton cliqué 2) le contenu à afficher 3) la HBox du menu 4) la liste des
		 * autres boutons
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

				closeMenu(menuHbox);

			}
		});

		/* SCOPE CONTENT : configuration des boutons search et refresh */

		// Search button
		Button scopeContentValidateBtn = scopeContentVbox.getRightButton();

		scopeContentValidateBtn.setOnAction(event -> {
			// on récupère tous les textfield
			String[] data = grabInfos(scopeContentVbox);
			boolean isAdminBtnSelected = scopeContentVbox.isAdminSelected();
			boolean isNotAdminBtnSelected = scopeContentVbox.isNotAdminSelected();

			String familyNameFilter = data[0];
			String nameFilter = data[1];
			String aliasFilter = data[2];
			String emailFilter = data[3];

			// on filtre la liste de la TableView en fonction
			filteredMembers.setPredicate(intern -> {

				// pour chaque textfield on doit vérifier si il n'est pas null
				// si il ne l'est pas, on ajoute la condition au prédicat

				// on return true si aucun champ n'est rempli
				boolean filters = true;

				// sinon on ajoute à chaque fois le filtre au return

				if (familyNameFilter != null) {
					filters = filters && intern.getFamilyName().toUpperCase().contains(familyNameFilter.toUpperCase());
				}

				if (nameFilter != null) {
					filters = filters && intern.getName().toUpperCase().contains(nameFilter.toUpperCase());
				}

				if (aliasFilter != null) {
					filters = filters && intern.getAlias().contains(aliasFilter);
				}

				if (emailFilter != null) {
					filters = filters && intern.getEmail().contains(emailFilter);
				}

				if (isAdminBtnSelected) {
					filters = filters && intern.isAdmin();
				} else if (isNotAdminBtnSelected) {
					filters = filters && !intern.isAdmin();
				}

				return filters;
			});

			closeMenu(menuHbox);

		});

		// Refresh button
		Button scopeContentCancelBtn = scopeContentVbox.getLeftButton();
		scopeContentCancelBtn.setOnAction(event -> {
			refreshPane(scopeContentVbox);
			scopeContentVbox.refreshRadioButtons();
		});

		/* ADD CONTENT : configuration des boutons annuler et ajouter */

		// ajouter
		Button addContentAddBtn = addContentVbox.getRightButton();
		addContentAddBtn.setOnAction(event -> {
			String[] data = grabInfos(addContentVbox);
			String passwordData = addContentVbox.getTextPasswordField();
			MemberDao dao = new MemberDao();

			boolean good = this.areAllFieldsCorrectlyFilled(addContentVbox);
			boolean isAdminBtnSelected = scopeContentVbox.isAdminSelected();

			if (good) {
				dao.insert(new Member(data[2], passwordData, data[0].toUpperCase(),
						data[1].toUpperCase().charAt(0) + data[1].substring(1), data[3], isAdminBtnSelected));
				ArrayList<Member> suppr = new ArrayList<Member>();
				this.observableMembers.setAll(memberDao.sortView(0, suppr));
				refreshPane(addContentVbox);
				closeMenu(menuHbox);
			} else {
				addContentVbox.getLabelError().show();
			}

		});
		// Annuler button
		Button addcontentCancelBtn = addContentVbox.getLeftButton();
		addcontentCancelBtn.setOnAction(event -> {
			refreshPane(addContentVbox);
		});

		/* REMOVE CONTENT : configuration des boutons annuler et valider */

		// valider suppression
		Button removeContentValidateBtn = removeContentVbox.getRightButton();
		removeContentValidateBtn.setOnAction(event -> {
			String[] data = grabRemoveInfos(removeContentVbox);
			boolean admin;
			if (data[4].equals("administrateur")) {
				admin = true;
			} else {
				admin = false;
			}
			Member selectedMember = this.tableView.getSelectionModel().getSelectedItem();
			String password = selectedMember.getPassword();

			MemberDao dao = new MemberDao();

			dao.delete(new Member(data[2], password, data[0].toUpperCase(),
					data[1].toUpperCase().charAt(0) + data[1].substring(1), data[3], admin));

			ArrayList<Member> suppr = new ArrayList<Member>();
			this.observableMembers.setAll(memberDao.sortView(0, suppr));

			closeMenu(menuHbox);
		});

		// Annuler button
		Button removeContentCancelBtn = removeContentVbox.getLeftButton();
		removeContentCancelBtn.setOnAction(event -> {
			this.tableView.getSelectionModel().clearSelection();
		});

		/* UPDATE CONTENT : configuration des boutons annuler et valider */

		Button updateContentUpdateBtn = updateContentVbox.getRightButton();
		updateContentUpdateBtn.setOnAction(event -> {
			Member oldMember = selected;
//			Member oldMember = this.tableView.getSelectionModel().getSelectedItem();
			System.out.println("-------------------" + oldMember);
			String[] data = grabInfos(updateContentVbox);
			String password = updateContentVbox.getTextPasswordField();
			MemberDao dao = new MemberDao();

			boolean isAdmin = updateContentVbox.isAdminSelected();
//			updateContentVbox.setRadioButton(isAdmin);
			boolean good = this.areAllFieldsCorrectlyFilled(updateContentVbox);

			if (good) {

				dao.update(new Member(data[2], password, data[0].toUpperCase(),
						data[1].toUpperCase().charAt(0) + data[1].substring(1), data[3],
						updateContentVbox.isAdminSelected()), oldMember);

				this.tableView.getSelectionModel().clearSelection();
				refreshPane(updateContentVbox);
			} else {
				System.err.println("erreur");
				updateContentVbox.getLabelError().setVisible(true);
			}
			closeMenu(menuHbox);
		});

		// Annuler button
		Button updateContentCancelBtn = updateContentVbox.getLeftButton();
		updateContentCancelBtn.setOnAction(event -> {
			this.tableView.getSelectionModel().clearSelection();
			refreshPane(updateContentVbox);
		});

		/* QUIT CONTENT : configuration du bouton annuler */
		Button quitContentCancelBtn = quitContentVbox.getLeftButton();
		quitContentCancelBtn.setOnAction(event -> {
			closeMenu(menuHbox);
		});

		/* SEE MEMBER CONTENT : configuration du bouton annuler */
		// Annuler button
		Button seeMembersContentCancelBtn = seeMembersContentVbox.getLeftButton();
		seeMembersContentCancelBtn.setOnAction(event -> {
			closeMenu(menuHbox);
		});
	}

	private String[] grabRemoveInfos(MembersRemovePane Pane) {
		// on récupère tous les textfield
		String familyName = Pane.getTextFamilyName().trim();
		String name = Pane.getTextFirstName().trim();
		String alias = Pane.getTextCounty().trim();
		String email = Pane.getTextCursus().trim();
		String admin = Pane.getTextYearIn().trim();

		String[] data = { familyName, name, alias, email, admin };
		return data;
	}

	private String[] grabInfos(MembersRepetitivePane Pane) {
		// on récupère tous les textfield
		String familyName = Pane.getTextFamilyName();
		String name = Pane.getTextName();
		String alias = Pane.getTextAlias();
		String email = Pane.getTextEmail();

		String[] data = { familyName, name, alias, email };
		return data;
	}

	private void refreshPane(MembersRepetitivePane pane) {
		refresh(pane.getGridPaneFamilyName());
		refresh(pane.getGridPaneName());
		refresh(pane.getGridPaneAlias());
		refresh(pane.getGridPaneEmail());
		refresh(pane.getPasswordField());
		// on décoche les radiobutton
		pane.refreshRadioButtons();
	}

	private void refresh(TextField textField) {
		textField.setText("");
	}

	public void configureButtonAction(StackPaneMenubar buttonClicked, RepetitivePane mainContentToShow, HBox menuHbox,
			List<StackPaneMenubar> otherButtons) {

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

			// on diminue tous les boutons
			setSmaller(buttonClicked);
			setSmaller(otherButtons.get(1));
			setSmaller(otherButtons.get(2));
			setSmaller(otherButtons.get(3));
			setSmaller(otherButtons.get(4));
			setSmaller(otherButtons.get(5));
			setSmaller(otherButtons.get(6));

			// On set maxwidth de la menubarVBox à 100
			menubarVBox.setPrefWidth(100);
			menubarVBox.setTranslateX(200);
			otherButtons.get(0).setTranslateX(0);

			// on passe tous les boutons à gris
			changeToGrey(otherButtons.get(1));
			changeToGrey(otherButtons.get(2));
			changeToGrey(otherButtons.get(3));
			changeToGrey(otherButtons.get(4));
			changeToGrey(otherButtons.get(5));
			changeToGrey(otherButtons.get(6));

			// on met à jour la couleur du bouton cliqué en orange
			changeToOrange(buttonClicked);

		});

		addHoverEffect(buttonClicked);

	}

//	private void configureButtonAction(StackPaneMenubar buttonClicked, MembersRepetitivePane mainContentToShow, HBox menuHbox,
//			List<StackPaneMenubar> otherButtons) {
//		buttonClicked.getButton().setOnAction(event -> {
//
//			// On remplace le contenu de la HBox
//			menuHbox.getChildren().set(0, mainContentToShow);
//
//			// On rend les boutons du contenu en bouton par défaut (ceux qui réagissent
//			// quand one appuie sur Entrée et échap)
//			mainContentToShow.getLeftButton().setCancelButton(true);
//			mainContentToShow.getRightButton().setDefaultButton(true);
//
//			VBox menubarVBox = (VBox) menuHbox.getChildren().get(1);
//
//			TranslateTransition moveTransition = new TranslateTransition(Duration.millis(500), menuHbox);
//			moveTransition.setToX(TOX_LARGE_MENU);
//			moveTransition.play();
//
//			// on fait apparaitre la croix et disparaitre la fleche
//			otherButtons.get(0).getBtnGreyImageView().setVisible(true);
//			otherButtons.get(0).getBtnOrangeImageView().setVisible(false);
//
//			// on diminue tous les boutons
//			setSmaller(buttonClicked);
//			setSmaller(otherButtons.get(1));
//			setSmaller(otherButtons.get(2));
//			setSmaller(otherButtons.get(3));
//			setSmaller(otherButtons.get(4));
//			setSmaller(otherButtons.get(5));
//			setSmaller(otherButtons.get(6));
//
//			// On set maxwidth de la menubarVBox à 100
//			menubarVBox.setPrefWidth(100);
//			menubarVBox.setTranslateX(200);
//			otherButtons.get(0).setTranslateX(0);
//
//			// on passe tous les boutons à gris
//			changeToGrey(otherButtons.get(1));
//			changeToGrey(otherButtons.get(2));
//			changeToGrey(otherButtons.get(3));
//			changeToGrey(otherButtons.get(4));
//			changeToGrey(otherButtons.get(5));
//			changeToGrey(otherButtons.get(6));
//
//			// on met à jour la couleur du bouton cliqué en orange
//			changeToOrange(buttonClicked);
//
//		});
//
//		addHoverEffect(buttonClicked);
//		
//	}

	public void closeMenu(HBox menuHbox) {
		TranslateTransition moveTransition = new TranslateTransition();

		moveTransition.setDuration(DURATION_TIME);
		moveTransition.setNode(menuHbox);
		moveTransition.setToX(TOX_SMALL_MENU);
		moveTransition.play();

		VBox menubarVBox = (VBox) (menuHbox.getChildren().get(1));

		StackPaneMenubar closeBtn = (StackPaneMenubar) menubarVBox.getChildren().get(0);
		StackPaneMenubar scopeBtn = (StackPaneMenubar) menubarVBox.getChildren().get(1);
		StackPaneMenubar addBtn = (StackPaneMenubar) menubarVBox.getChildren().get(2);
		StackPaneMenubar removeBtn = (StackPaneMenubar) menubarVBox.getChildren().get(3);
		StackPaneMenubar updateBtn = (StackPaneMenubar) menubarVBox.getChildren().get(4);
		StackPaneMenubar printBtn = (StackPaneMenubar) menubarVBox.getChildren().get(5);
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
		changeToOrange(removeBtn);
		changeToOrange(updateBtn);
		changeToOrange(printBtn);
		changeToOrange(seeMemberBtn);
		changeToOrange(quitBtn);

// Agrandir tous les boutons
		setLarger(scopeBtn);
		setLarger(addBtn);
		setLarger(removeBtn);
		setLarger(updateBtn);
		setLarger(printBtn);
		setLarger(seeMemberBtn);
		setLarger(quitBtn);

		MemberDao memberDao = new MemberDao();
		ArrayList<Member> suppr = new ArrayList<Member>();
		this.observableMembers.setAll(memberDao.sortView(0, suppr));
	}

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
				} else {
					System.out.println("couleur : " + initialColorHex[0]);
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
				} else {
					System.out.println("couleur : " + initialColorHex[0]);
				}
			}
		});
	}

	private TableView<Member> createTableView(FilteredList<Member> filteredInterns) {

		TableView<Member> tableView = new TableView<>(filteredMembers);

		// On met les colones
		double columnWidth = 1005 / 5;

		TableColumn<Member, String> column1 = new TableColumn<>("Nom de Famille");
		column1.setCellValueFactory(new PropertyValueFactory<>("familyName"));
		column1.setPrefWidth(columnWidth);

		TableColumn<Member, String> column2 = new TableColumn<>("Prénom");
		column2.setCellValueFactory(new PropertyValueFactory<>("name"));
		column2.setPrefWidth(columnWidth);

		TableColumn<Member, String> column3 = new TableColumn<>("Pseudo");
		column3.setCellValueFactory(new PropertyValueFactory<>("alias"));
		column3.setPrefWidth(columnWidth);

		TableColumn<Member, String> column4 = new TableColumn<>("Mail");
		column4.setCellValueFactory(new PropertyValueFactory<>("email"));
		column4.setPrefWidth(columnWidth);

		TableColumn<Member, String> column5 = new TableColumn<>("Status");
		column5.setCellValueFactory(cellData -> {
			boolean isAdmin = cellData.getValue().isAdmin();
			return new SimpleStringProperty(isAdmin ? "administrateur" : "non administrateur");
		});
		column5.setPrefWidth(columnWidth);

		tableView.getColumns().addAll(column1, column2, column3, column4, column5);

		StackPane.setMargin(tableView, new Insets(70, 70, 70, 170));
		return tableView;
	}

	/* ---------------------------------------------------------------- */

	private void changeToOrange(StackPaneMenubar button) {
		// on change la couleur du SVGPath
		applyColorTransition((SVGPath) button.getChildren().get(0), ORANGE_COLOR);
		((SVGPath) button.getChildren().get(0)).setFill(ORANGE_COLOR);

		button.getBtnOrangeImageView().setVisible(false);
		button.getBtnGreyImageView().setVisible(true);
	}

	private void changeToGrey(StackPaneMenubar button) {
		// on change la couleur du SVGPath
		applyColorTransition((SVGPath) button.getChildren().get(0), GREY_COLOR);
		((SVGPath) button.getChildren().get(0)).setFill(GREY_COLOR);

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

	private boolean areAllFieldsCorrectlyFilled(MembersRepetitivePane pane) {
		boolean filled = true;

		String familyName = pane.getTextFamilyName().trim();
		String name = pane.getTextName().trim();
		String alias = pane.getTextAlias().trim();
		String email = pane.getTextEmail().trim();
		String password = pane.getTextPasswordField().trim();

		System.out.println(familyName + " " + name + " " + alias + " " + email + " " + password);
		if (familyName.length() == 0 || name.length() == 0 || alias.length() == 0 || email.length() == 0
				|| password.length() == 0) {
			filled = false;
			return filled;
		}

		if (Pattern.compile("[^(\\p{L}-\\s)]").matcher(familyName).find()
				|| Pattern.compile("[^(\\p{L}-\\s)]").matcher(name).find()
				|| Pattern.compile("[^(\\p{L}-\\s\\d)]").matcher(alias).find()
				|| Pattern.compile("[^(\\p{L}-\\s\\d@.)]").matcher(email).find()
				|| Pattern.compile("[^(\\p{L}-\\s\\d)]").matcher(password).find()) {
			filled = false;
		}

		return filled;
	}

	private String getCurrentColor(SVGPath svgPath) {
		// couleur actuelle de remplissage de l'SVGPath
		Paint fill = svgPath.getFill();

		Color color = (Color) fill;
		return String.format("#%02X%02X%02X", (int) (color.getRed() * 255), (int) (color.getGreen() * 255),
				(int) (color.getBlue() * 255));

	}

}
