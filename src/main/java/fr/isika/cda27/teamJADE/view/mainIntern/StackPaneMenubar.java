package fr.isika.cda27.teamJADE.view.mainIntern;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class StackPaneMenubar extends StackPane {
	private StackPane btnStackPane;
	private SVGPath smallSvgPath;
	private SVGPath largeSvgPath;
	private Button button;
	private ImageView btnOrangeImageView;
	private ImageView btnGreyImageView;
	private String orangeFile;
	private String greyFile;
	private int btnWidth;
	private int btnHeight;
	private int imgSize;
	private boolean visibility = true;
	private Tooltip tooltip;
	private String tooltipText;
	private HBox btnContainer;
	private Label label;
	private HBox hbox;
	private VBox leftSubcontainer;
	private VBox rightSubcontainer;
	private HBox container;

	// Pour le bouton déconnexion
	public StackPaneMenubar(String orangeFile, String greyFile, String tooltipText, String svgSmallPath, String svgLargePath) {
		this(orangeFile, greyFile, tooltipText, svgSmallPath, svgLargePath, 46, 300);
	}
	
	// Pour les boutons classiques
	public StackPaneMenubar(String orangeFile, String greyFile, String tooltipText) {
		this(orangeFile, greyFile, tooltipText, "M0,0 H100 V87 H0 Z", "M0,0 H300 V87 H0 Z", 46, 300);
	}
	
	// pour la croix / fleche
		public StackPaneMenubar(String greyFile, String orangeFile, String svgSmallPath, int imgSize) {
			this(orangeFile, greyFile, "fleche", svgSmallPath, "none", imgSize, 100);
		}
		
	// Constructeur complet
	public StackPaneMenubar(String orangeFile, String greyFile, String tooltipText, String svgSmallPath,
			String svgLargePath, int imgSize, int btnWidth) {
		this.tooltipText = tooltipText;
		this.btnWidth = btnWidth;
		this.btnHeight = 87;
		this.imgSize = imgSize;
		this.orangeFile = orangeFile;
		this.greyFile = greyFile;
		
		this.smallSvgPath = new SVGPath();
		smallSvgPath.setContent(svgSmallPath);
		smallSvgPath.setFill(Color.web("#DD734C"));
//		smallSvgPath.setVisible(false);


		this.button = new Button();
		button.setPrefSize(btnWidth, btnHeight);
		button.setStyle("-fx-background-color: transparent;");

		if (!tooltipText.equals("fleche")) {
			this.tooltip = new Tooltip(this.tooltipText);
			Tooltip.install(button, tooltip);
			tooltip.setShowDelay(Duration.seconds(0.1));

			this.largeSvgPath = new SVGPath();
			largeSvgPath.setContent(svgLargePath);
			largeSvgPath.setFill(Color.web("#DD734C"));
		}

		this.btnStackPane = new StackPane();

		// Sert à la fleche
		this.btnOrangeImageView = new ImageView(new Image("file:src/main/resources/img/" + orangeFile));
		btnOrangeImageView.setFitWidth(imgSize);
		btnOrangeImageView.setFitHeight(imgSize);
		if (!tooltipText.equals("fleche")) btnOrangeImageView.setVisible(false);

		// Sert à la croix
		this.btnGreyImageView = new ImageView(new Image("file:src/main/resources/img/" + greyFile));
		btnGreyImageView.setFitWidth(imgSize);
		btnGreyImageView.setFitHeight(imgSize);

		if (tooltipText.equals("fleche")) {
			btnGreyImageView.setVisible(false); 
			btnStackPane.getChildren().addAll(smallSvgPath, btnOrangeImageView, btnGreyImageView);
			this.setTranslateX(100);
			this.getChildren().addAll(btnStackPane, button);
			return;
		}
		btnStackPane.getChildren().addAll(btnOrangeImageView, btnGreyImageView);

		


		this.label = new Label("     " + this.tooltipText);

		label.setFont(Font.font("Krona One", 18));

		this.btnContainer = new HBox();
		btnContainer.setAlignment(Pos.CENTER);
		// 2VBox dans la HBox
//		btnContainer.setStyle("-fx-border-color: red;");
		this.rightSubcontainer = new VBox();
		rightSubcontainer.setPrefWidth(100);
		rightSubcontainer.setAlignment(Pos.CENTER);
		
		this.leftSubcontainer = new VBox();
		leftSubcontainer.setPrefWidth(200);
		leftSubcontainer.setAlignment(Pos.CENTER_LEFT);
		
		rightSubcontainer.getChildren().add(btnStackPane);
		leftSubcontainer.getChildren().add(label);
		
		btnContainer.getChildren().addAll(leftSubcontainer,rightSubcontainer);


		this.getChildren().addAll(largeSvgPath , btnContainer, button);
	}


	/**
	 * @return the tooltip
	 */
	public Tooltip getTooltip() {
		return tooltip;
	}

	/**
	 * @param tooltip the tooltip to set
	 */
	public void setTooltip(Tooltip tooltip) {
		this.tooltip = tooltip;
	}

	/**
	 * @param tooltiptext the tooltiptext to set to the tooltip
	 */
	public void setTooltipText(String tooltiptext) {
		this.tooltip.setText(tooltiptext);
	}
	
	/**
	 * @param tooltiptext the tooltiptext to set to the tooltip
	 */
	public void setLabelText(String tooltiptext) {
		this.label.setText("     " + tooltiptext);
	}
	
	public String getText() {
		return tooltipText;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {

		this.tooltipText = text;
	}

	/**
	 * @return the btnStackPane
	 */
	public StackPane getBtnStackPane() {
		return btnStackPane;
	}

	/**
	 * @param btnStackPane the btnStackPane to set
	 */
	public void setBtnStackPane(StackPane btnStackPane) {
		this.btnStackPane = btnStackPane;
	}

	/**
	 * @return the svgPath
	 */
	public SVGPath getLargeSvgPath() {
		return largeSvgPath;
	}

	/**
	 * @param svgPath the svgPath to set
	 */
	public void setLargeSvgPath(SVGPath largeSvgPath) {
		this.largeSvgPath = largeSvgPath;
	}

	/**
	 * @return the svgPath
	 */
	public SVGPath getSmallSvgPath() {
		return smallSvgPath;
	}

	/**
	 * @param svgPath the svgPath to set
	 */
	public void setSmallSvgPath(SVGPath smallSvgPath) {
		this.smallSvgPath = smallSvgPath;
	}

	/**
	 * @return the button
	 */
	public Button getButton() {
		return button;
	}

	/**
	 * @param button the button to set
	 */
	public void setButton(Button button) {
		this.button = button;
	}

	/**
	 * @return the btnOrangeImageView
	 */
	public ImageView getBtnOrangeImageView() {
		return btnOrangeImageView;
	}

	/**
	 * @param btnOrangeImageView the btnOrangeImageView to set
	 */
	public void setBtnOrangeImageView(ImageView btnOrangeImageView) {
		this.btnOrangeImageView = btnOrangeImageView;
	}

	/**
	 * @return the btnGreyImageView
	 */
	public ImageView getBtnGreyImageView() {
		return btnGreyImageView;
	}

	/**
	 * @param btnGreyImageView the btnGreyImageView to set
	 */
	public void setBtnGreyImageView(ImageView btnGreyImageView) {
		this.btnGreyImageView = btnGreyImageView;
	}

	/**
	 * @return the orangeFile
	 */
	public String getOrangeFile() {
		return orangeFile;
	}

	/**
	 * @param orangeFile the orangeFile to set
	 */
	public void setOrangeFile(String orangeFile) {
		this.orangeFile = orangeFile;
	}

	/**
	 * @return the greyFile
	 */
	public String getGreyFile() {
		return greyFile;
	}

	/**
	 * @param greyFile the greyFile to set
	 */
	public void setGreyFile(String greyFile) {
		this.greyFile = greyFile;
	}

	/**
	 * @return the btnWidth
	 */
	public int getBtnWidth() {
		return btnWidth;
	}

	/**
	 * @param btnWidth the btnWidth to set
	 */
	public void setBtnWidth(int btnWidth) {
		this.btnWidth = btnWidth;
	}

	/**
	 * @return the btnHeight
	 */
	public int getBtnHeight() {
		return btnHeight;
	}

	/**
	 * @param btnHeight the btnHeight to set
	 */
	public void setBtnHeight(int btnHeight) {
		this.btnHeight = btnHeight;
	}

	/**
	 * @return the imgWidth
	 */
	public int getImgSize() {
		return imgSize;
	}

	/**
	 * @param imgSize the imgSize to set
	 */
	public void setImgSize(int imgSize) {
		this.imgSize = imgSize;
	}

	/**
	 * @return the gridPane
	 */
	public HBox getBtnContainer() {
		return btnContainer;
	}

	/**
	 * @return the hbox
	 */
	public HBox getHbox() {
		return hbox;
	}

	/**
	 * @param hbox the hbox to set
	 */
	public void setHbox(HBox hbox) {
		this.hbox = hbox;
	}

	/**
	 * @param gridPane the gridPane to set
	 */
	public void setBtnContainer(HBox btnContainer) {
		this.btnContainer = btnContainer;
	}

	/**
	 * @return the visibility
	 */
	public boolean isVisibility() {
		return visibility;
	}

	/**
	 * @param visibility the visibility to set
	 */
	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

	/**
	 * @return the label
	 */
	public Label getLabel() {
		return label;
	}


	/**
	 * @param label the label to set
	 */
	public void setLabel(Label label) {
		this.label = label;
	}

	public VBox getLeftSubcontainer() {
		return leftSubcontainer;
	}

	public VBox getRightSubcontainer() {
		return rightSubcontainer;
	}
	
//	private HBox createMenuBtnContainer() {
//
//		this.container = new HBox();
//		container.setStyle("-fx-border-color: red;");
//		container.setAlignment(Pos.CENTER);
//		// 2VBox dans la HBox
//	
//		this.rightSubcontainer = new VBox();
//		rightSubcontainer.setPrefWidth(100);
//		rightSubcontainer.setAlignment(Pos.CENTER);
//		
//		this.leftSubcontainer = new VBox();
//		leftSubcontainer.setPrefWidth(200);
//		leftSubcontainer.setAlignment(Pos.CENTER_LEFT);
//		
//		rightSubcontainer.getChildren().add(btnStackPane);
//		leftSubcontainer.getChildren().add(label);
//		
//		container.getChildren().addAll(leftSubcontainer,rightSubcontainer);
//
//		return container;
//	}
}
