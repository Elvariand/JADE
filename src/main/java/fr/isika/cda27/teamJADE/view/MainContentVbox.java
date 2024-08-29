package fr.isika.cda27.teamJADE.view;

import java.util.ArrayList;

import fr.isika.cda27.teamJADE.utilz.CustomButton;
import fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.Colors.*;


public class MainContentVbox extends VBox {

	private VBox mainContentVbox;
	private HBox buttonBox;
	private Button leftButton;
	private Button rightButton;
	private Label titleLabel;
	private VBox scopeScene;
	private ArrayList<String> gridPaneLabelsList;

	
	public MainContentVbox() {
		this.mainContentVbox = new VBox();
		mainContentVbox.setPrefSize(690, 720);
		mainContentVbox.setAlignment(Pos.CENTER);
		mainContentVbox.setTranslateX(200);

		this.titleLabel = new Label();
		titleLabel.setPrefSize(680, 100);
		titleLabel.setFont(Font.font("Krona One", 25));
		titleLabel.setStyle("-fx-alignment: center;");
		titleLabel.setTextFill(GREY_COLOR);

		this.buttonBox = new HBox(100);
		buttonBox.setAlignment(javafx.geometry.Pos.CENTER);
		buttonBox.setPrefSize(680, 192);

		this.leftButton = new CustomButton("");

		this.rightButton = new CustomButton("");

		this.gridPaneLabelsList = new ArrayList<>();
		
		}

	/**
	 * @return the gridPaneLabelsList
	 */
	public ArrayList<String> getGridPaneLabelsList() {
		return gridPaneLabelsList;
	}

	/**
	 * @param gridPaneLabelsList the gridPaneLabelsList to set
	 */
	public void setGridPaneLabelsList(ArrayList<String> gridPaneLabelsList) {
		this.gridPaneLabelsList = gridPaneLabelsList;
	}

	
	/**
	 * @return the mainContentVbox
	 */
	public VBox getMainContentVbox() {
		return mainContentVbox;
	}

	/**
	 * @param mainContentVbox the mainContentVbox to set
	 */
	public void setMainContentVbox(VBox mainContentVbox) {
		this.mainContentVbox = mainContentVbox;
	}

	/**
	 * @return the buttonBox
	 */
	public HBox getButtonBox() {
		return buttonBox;
	}

	/**
	 * @param buttonBox the buttonBox to set
	 */
	public void setButtonBox(HBox buttonBox) {
		this.buttonBox = buttonBox;
	}

	/**
	 * @return the leftButton
	 */
	public Button getLeftButton() {
		return leftButton;
	}

	/**
	 * @param leftButton the leftButton to set
	 */
	public void setLeftButton(Button leftButton) {
		this.leftButton = leftButton;
	}

	/**
	 * @return the rightButton
	 */
	public Button getRightButton() {
		return rightButton;
	}

	/**
	 * @param rightButton the rightButton to set
	 */
	public void setRightButton(Button rightButton) {
		this.rightButton = rightButton;
	}

	/**
	 * @return the titleLabel
	 */
	public Label getTitleLabel() {
		return titleLabel;
	}

	/**
	 * @param titleLabel the titleLabel to set
	 */
	public void setTitleLabel(String titleLabel) {
		this.titleLabel.setText(titleLabel);
	}

}
