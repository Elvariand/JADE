package fr.isika.cda27.teamJADE.view;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MainSceneValues.LARGE_PATH_BOT;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MainSceneValues.MENUBAR_HEIGHT;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MainSceneValues.MENUBAR_WIDTH;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MainSceneValues.PATH_TOP;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MainSceneValues.SMALL_PATH_BOT;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MainSceneValues.TOX_MENUBAR;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class MenuIconsVBox extends VBox {

	private StackPaneMenubar3 closeBtn;
	private StackPaneMenubar3 scopeBtn;
	private StackPaneMenubar3 addBtn;
	private StackPaneMenubar3 removeBtn;
	private StackPaneMenubar3 updateBtn;
	private StackPaneMenubar3 printBtn;
	private StackPaneMenubar3 seeMemberBtn;
	private StackPaneMenubar3 quitBtn;
	
	public MenuIconsVBox() {
		super();
		
		this.setPrefSize(MENUBAR_WIDTH, MENUBAR_HEIGHT);
//		this.setTranslateX(TOX_MENUBAR);
		this.setStyle("-fx-background-color: transparent; -fx-border-color: green; -fx-border-width: 5;");
		this.setAlignment(Pos.CENTER_RIGHT);
		
		// On crée la croix du haut
		this.closeBtn = new StackPaneMenubar3("croix.png", "fleche.png", PATH_TOP, 30);

		// On crée les autres boutons
		this.scopeBtn = new StackPaneMenubar3("loupe_orange.png", "loupe_grise.png", "Rechercher");
		this.addBtn = new StackPaneMenubar3("ajout_stagiaire_orange.png", "ajout_stagiaire_gris.png",
				"Ajouter");
		this.removeBtn = new StackPaneMenubar3("suppr_stagiaire_orange.png", "suppr_stagiaire_gris.png",
				"Supprimer");
		this.updateBtn = new StackPaneMenubar3("modif_stagiaire_orange.png", "modif_stagiaire_gris.png",
				"Modifier");
		this.printBtn = new StackPaneMenubar3("imprimante_orange.png", "imprimante_grise.png", "Imprimer");
		this.seeMemberBtn = new StackPaneMenubar3("voir_membre_orange.png", "voir_membre_gris.png",
				"Membres");

		// On crée le bouton quitter
		this.quitBtn = new StackPaneMenubar3("deconnexion_orange.png", "deconnexion_gris.png", "Déconnexion",
				SMALL_PATH_BOT, LARGE_PATH_BOT);
		
		// On ajoute la croix (invisible pour le moment)
		this.getChildren().add(closeBtn);
		// On ajoute tous les boutons dans le VBox
		this.getChildren().addAll(scopeBtn, addBtn, removeBtn, updateBtn, printBtn, seeMemberBtn);
		// On ajoute le bouton quitter
		this.getChildren().add(quitBtn);
	}

	/**
	 * @return the closeBtn
	 */
	public StackPaneMenubar3 getCloseBtn() {
		return closeBtn;
	}

	/**
	 * @param closeBtn the closeBtn to set
	 */
	public void setCloseBtn(StackPaneMenubar3 closeBtn) {
		this.closeBtn = closeBtn;
	}

	/**
	 * @return the scopeBtn
	 */
	public StackPaneMenubar3 getScopeBtn() {
		return scopeBtn;
	}

	/**
	 * @param scopeBtn the scopeBtn to set
	 */
	public void setScopeBtn(StackPaneMenubar3 scopeBtn) {
		this.scopeBtn = scopeBtn;
	}

	/**
	 * @return the addBtn
	 */
	public StackPaneMenubar3 getAddBtn() {
		return addBtn;
	}

	/**
	 * @param addBtn the addBtn to set
	 */
	public void setAddBtn(StackPaneMenubar3 addBtn) {
		this.addBtn = addBtn;
	}

	/**
	 * @return the removeBtn
	 */
	public StackPaneMenubar3 getRemoveBtn() {
		return removeBtn;
	}

	/**
	 * @param removeBtn the removeBtn to set
	 */
	public void setRemoveBtn(StackPaneMenubar3 removeBtn) {
		this.removeBtn = removeBtn;
	}

	/**
	 * @return the updateBtn
	 */
	public StackPaneMenubar3 getUpdateBtn() {
		return updateBtn;
	}

	/**
	 * @param updateBtn the updateBtn to set
	 */
	public void setUpdateBtn(StackPaneMenubar3 updateBtn) {
		this.updateBtn = updateBtn;
	}

	/**
	 * @return the printBtn
	 */
	public StackPaneMenubar3 getPrintBtn() {
		return printBtn;
	}

	/**
	 * @param printBtn the printBtn to set
	 */
	public void setPrintBtn(StackPaneMenubar3 printBtn) {
		this.printBtn = printBtn;
	}

	/**
	 * @return the seeMemberBtn
	 */
	public StackPaneMenubar3 getSeeMemberBtn() {
		return seeMemberBtn;
	}

	/**
	 * @param seeMemberBtn the seeMemberBtn to set
	 */
	public void setSeeMemberBtn(StackPaneMenubar3 seeMemberBtn) {
		this.seeMemberBtn = seeMemberBtn;
	}

	/**
	 * @return the quitBtn
	 */
	public StackPaneMenubar3 getQuitBtn() {
		return quitBtn;
	}

	/**
	 * @param quitBtn the quitBtn to set
	 */
	public void setQuitBtn(StackPaneMenubar3 quitBtn) {
		this.quitBtn = quitBtn;
	}
}
