package fr.isika.cda27.teamJADE.view;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MainSceneValues.LARGE_PATH_BOT;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MainSceneValues.MENUBAR_HEIGHT;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MainSceneValues.MENUBAR_WIDTH;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MainSceneValues.PATH_TOP;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MainSceneValues.SMALL_PATH_BOT;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MainSceneValues.TOX_MENUBAR;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MainSceneValues.TOX_SMALL_MENU;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.ShadowSet.DROP_SHADOW;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MenuPane extends HBox {

	private HBox contentBox;
	private VBox labelsBox;
	private VBox iconsBox;

	public MenuPane() {
		this.contentBox = new MenuContentHBox();
		this.iconsBox = new MenuIconsVBox();
		this.labelsBox = new MenuLabelsVBox();



	}

	/**
	 * @return the contentBox
	 */
	public HBox getContentBox() {
		return contentBox;
	}

	/**
	 * @param contentBox the contentBox to set
	 */
	public void setContentBox(HBox contentBox) {
		this.contentBox = contentBox;
	}

	/**
	 * @return the labelsBox
	 */
	public VBox getLabelsBox() {
		return labelsBox;
	}

	/**
	 * @param labelsBox the labelsBox to set
	 */
	public void setLabelsBox(VBox labelsBox) {
		this.labelsBox = labelsBox;
	}

	/**
	 * @return the iconsBox
	 */
	public VBox getIconsBox() {
		return iconsBox;
	}

	/**
	 * @param iconsBox the iconsBox to set
	 */
	public void setIconsBox(VBox iconsBox) {
		this.iconsBox = iconsBox;
	}

}
