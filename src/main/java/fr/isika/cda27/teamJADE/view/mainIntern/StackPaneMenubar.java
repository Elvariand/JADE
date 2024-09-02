package fr.isika.cda27.teamJADE.view.mainIntern;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
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
	/**
	 * Initialise le bouton déconnexion
	 * 
	 * @param orangeFile
	 * @param greyFile
	 * @param tooltipText
	 * @param svgSmallPath
	 * @param svgLargePath
	 */
	public StackPaneMenubar(String orangeFile, String greyFile, String tooltipText, String svgSmallPath,
			String svgLargePath) {
		this(orangeFile, greyFile, tooltipText, svgSmallPath, svgLargePath, 46, 300);
	}

	// Pour les boutons classiques
	/**
	 * Initialise les boutons classiques
	 * 
	 * @param orangeFile
	 * @param greyFile
	 * @param tooltipText
	 */
	public StackPaneMenubar(String orangeFile, String greyFile, String tooltipText) {
		this(orangeFile, greyFile, tooltipText, "M0,0 H100 V87 H0 Z", "M0,0 H300 V87 H0 Z", 46, 300);
	}

	// pour la croix / fleche
	/**
	 * Initialise la croix et la flèche
	 * 
	 * @param greyFile
	 * @param orangeFile
	 * @param svgSmallPath
	 * @param imgSize
	 */
	public StackPaneMenubar(String greyFile, String orangeFile, String svgSmallPath, int imgSize) {
		this(orangeFile, greyFile, "fleche", svgSmallPath, "none", imgSize, 100);
	}

	// Constructeur complet
	/**
	 * @param orangeFile
	 * @param greyFile
	 * @param tooltipText
	 * @param svgSmallPath
	 * @param svgLargePath
	 * @param imgSize
	 * @param btnWidth
	 */
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

		this.button = new Button();
		button.setPrefSize(btnWidth, btnHeight);
		button.setStyle("-fx-background-color: transparent;");

		if (!tooltipText.equals("fleche")) {
			this.tooltip = new Tooltip(this.tooltipText);
			tooltip.setShowDelay(Duration.seconds(0.1));
			button.setTooltip(tooltip);

			this.largeSvgPath = new SVGPath();
			largeSvgPath.setContent(svgLargePath);
			largeSvgPath.setFill(Color.web("#DD734C"));
		}

		this.btnStackPane = new StackPane();

		// Sert à la fleche
		this.btnOrangeImageView = new ImageView(new Image("file:src/main/resources/img/" + orangeFile));
		btnOrangeImageView.setFitWidth(imgSize);
		btnOrangeImageView.setFitHeight(imgSize);
		if (!tooltipText.equals("fleche"))
			btnOrangeImageView.setVisible(false);

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

		this.rightSubcontainer = new VBox();
		rightSubcontainer.setPrefWidth(100);
		rightSubcontainer.setAlignment(Pos.CENTER);

		this.leftSubcontainer = new VBox();
		leftSubcontainer.setPrefWidth(200);
		leftSubcontainer.setAlignment(Pos.CENTER_LEFT);

		rightSubcontainer.getChildren().add(btnStackPane);
		leftSubcontainer.getChildren().add(label);

		btnContainer.getChildren().addAll(leftSubcontainer, rightSubcontainer);

		this.getChildren().addAll(largeSvgPath, btnContainer, button);
	}

	/**
	 * @return le tooltip
	 */
	public Tooltip getTooltip() {
		return tooltip;
	}

	/**
	 * @param tooltip le tooltip à définir
	 */
	public void setTooltip(Tooltip tooltip) {
		this.tooltip = tooltip;
	}

	/**
	 * @param tooltiptext the tooltiptext à définir dans the tooltip
	 */
	public void setTooltipText(String tooltiptext) {
		this.tooltip.setText(tooltiptext);
	}

	/**
	 * @param tooltiptext le tooltiptext à définir dans the tooltip
	 */
	public void setLabelText(String tooltiptext) {
		this.label.setText("     " + tooltiptext);
	}

	/**
	 * @return le texte du tooltip
	 */
	public String getText() {
		return tooltipText;
	}

	/**
	 * @param text le text à définir
	 */
	public void setText(String text) {

		this.tooltipText = text;
	}

	/**
	 * @return le btnStackPane
	 */
	public StackPane getBtnStackPane() {
		return btnStackPane;
	}

	/**
	 * @param btnStackPane le btnStackPane à définir
	 */
	public void setBtnStackPane(StackPane btnStackPane) {
		this.btnStackPane = btnStackPane;
	}

	/**
	 * @return le svgPath
	 */
	public SVGPath getLargeSvgPath() {
		return largeSvgPath;
	}

	/**
	 * @param svgPath le svgPath à définir
	 */
	public void setLargeSvgPath(SVGPath largeSvgPath) {
		this.largeSvgPath = largeSvgPath;
	}

	/**
	 * @return le svgPath
	 */
	public SVGPath getSmallSvgPath() {
		return smallSvgPath;
	}

	/**
	 * @param svgPath le svgPath à définir
	 */
	public void setSmallSvgPath(SVGPath smallSvgPath) {
		this.smallSvgPath = smallSvgPath;
	}

	/**
	 * @return le button
	 */
	public Button getButton() {
		return button;
	}

	/**
	 * @param button le button à définir
	 */
	public void setButton(Button button) {
		this.button = button;
	}

	/**
	 * @return le btnOrangeImageView
	 */
	public ImageView getBtnOrangeImageView() {
		return btnOrangeImageView;
	}

	/**
	 * @param btnOrangeImageView le btnOrangeImageView à définir
	 */
	public void setBtnOrangeImageView(ImageView btnOrangeImageView) {
		this.btnOrangeImageView = btnOrangeImageView;
	}

	/**
	 * @return le btnGreyImageView
	 */
	public ImageView getBtnGreyImageView() {
		return btnGreyImageView;
	}

	/**
	 * @param btnGreyImageView le btnGreyImageView à définir
	 */
	public void setBtnGreyImageView(ImageView btnGreyImageView) {
		this.btnGreyImageView = btnGreyImageView;
	}

	/**
	 * @return le orangeFile
	 */
	public String getOrangeFile() {
		return orangeFile;
	}

	/**
	 * @param orangeFile le orangeFile à définir
	 */
	public void setOrangeFile(String orangeFile) {
		this.orangeFile = orangeFile;
	}

	/**
	 * @return le greyFile
	 */
	public String getGreyFile() {
		return greyFile;
	}

	/**
	 * @param greyFile le greyFile à définir
	 */
	public void setGreyFile(String greyFile) {
		this.greyFile = greyFile;
	}

	/**
	 * @return le btnWidth
	 */
	public int getBtnWidth() {
		return btnWidth;
	}

	/**
	 * @param btnWidth le btnWidth à définir
	 */
	public void setBtnWidth(int btnWidth) {
		this.btnWidth = btnWidth;
	}

	/**
	 * @return le btnHeight
	 */
	public int getBtnHeight() {
		return btnHeight;
	}

	/**
	 * @param btnHeight le btnHeight à définir
	 */
	public void setBtnHeight(int btnHeight) {
		this.btnHeight = btnHeight;
	}

	/**
	 * @return le imgWidth
	 */
	public int getImgSize() {
		return imgSize;
	}

	/**
	 * @param imgSize le imgSize à définir
	 */
	public void setImgSize(int imgSize) {
		this.imgSize = imgSize;
	}

	/**
	 * @return le gridPane
	 */
	public HBox getBtnContainer() {
		return btnContainer;
	}

	/**
	 * @return le hbox
	 */
	public HBox getHbox() {
		return hbox;
	}

	/**
	 * @param hbox le hbox à définir
	 */
	public void setHbox(HBox hbox) {
		this.hbox = hbox;
	}

	/**
	 * @param gridPane le gridPane à définir
	 */
	public void setBtnContainer(HBox btnContainer) {
		this.btnContainer = btnContainer;
	}

	/**
	 * @return le visibility
	 */
	public boolean isVisibility() {
		return visibility;
	}

	/**
	 * @param visibility le visibility à définir
	 */
	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

	/**
	 * @return le label
	 */
	public Label getLabel() {
		return label;
	}

	/**
	 * @param label le label à définir
	 */
	public void setLabel(Label label) {
		this.label = label;
	}

	/**
	 * @return le sous-conteneur gauche
	 */
	public VBox getLeftSubcontainer() {
		return leftSubcontainer;
	}

	/**
	 * @return le sous-conteneur droit
	 */
	public VBox getRightSubcontainer() {
		return rightSubcontainer;
	}

}
