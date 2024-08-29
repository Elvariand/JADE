package fr.isika.cda27.teamJADE.view;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;

public class StackPaneMenubar3 extends StackPane {
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
	private HBox hbox;


	// Pour le bouton déconnexion
	public StackPaneMenubar3(String orangeFile, String greyFile, String tooltipText, String svgSmallPath, String svgLargePath) {
		this(orangeFile, greyFile, tooltipText, svgSmallPath, svgLargePath, 46);
	}
	
	// Pour les boutons classiques
	public StackPaneMenubar3(String orangeFile, String greyFile, String tooltipText) {
		this(orangeFile, greyFile, tooltipText, "M0,0 H100 V87 H0 Z", "M0,0 H300 V87 H0 Z", 46);
	}
	
	// pour la croix / fleche
		public StackPaneMenubar3(String greyFile, String orangeFile, String svgSmallPath, int imgSize) {
			this(orangeFile, greyFile, "fleche", svgSmallPath, "none", imgSize);
		}
		
	// Constructeur complet
	public StackPaneMenubar3(String orangeFile, String greyFile, String tooltipText, String svgSmallPath,
			String svgLargePath, int imgSize) {
		this.tooltipText = tooltipText;
		this.btnWidth = 100;
		this.btnHeight = 87;
		this.imgSize = imgSize;
		this.orangeFile = orangeFile;
		this.greyFile = greyFile;

		this.smallSvgPath = new SVGPath();
		smallSvgPath.setContent(svgSmallPath);
		smallSvgPath.setFill(Color.web("#DD734C"));
		smallSvgPath.setVisible(false);

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
		if (!tooltipText.equals("fleche")) btnOrangeImageView.setVisible(true);

		// Sert à la croix
		this.btnGreyImageView = new ImageView(new Image("file:src/main/resources/img/" + greyFile));
		btnGreyImageView.setFitWidth(imgSize);
		btnGreyImageView.setFitHeight(imgSize);

		if (tooltipText.equals("fleche")) {
			btnGreyImageView.setVisible(true); 
			btnStackPane.getChildren().addAll(smallSvgPath, btnOrangeImageView, btnGreyImageView);
//			this.setTranslateX(-100);
			this.getChildren().addAll(btnStackPane, button);
			return;
		}
		
		btnStackPane.getChildren().addAll(btnOrangeImageView, btnGreyImageView);


		// on translate
//		largeSvgPath.setTranslateX(-100);
//		button.setTranslateX(-100);
//		this.setTranslateX(-100);

		this.getChildren().addAll(largeSvgPath, smallSvgPath, button);
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
	 * @return the text
	 */
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

}
