package fr.isika.cda27.teamJADE.view;

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
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class StackPaneMenubarAVANT extends StackPane {
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
	private String text;
	private GridPane gridPane;
	private Label label;
	private HBox hbox;

	
	public StackPaneMenubarAVANT(String orangeFile, String greyFile, String text) {
		this.text = text;

		this.btnWidth = 1;
		this.btnHeight = 87;
		this.imgSize = 46;

		
		this.orangeFile = orangeFile;
		this.greyFile = greyFile;


		this.largeSvgPath = new SVGPath();
		largeSvgPath.setContent("M0,0 H300 V87 H0 Z");
		largeSvgPath.setFill(Color.web("#DD734C"));
		
		this.smallSvgPath = new SVGPath();
		smallSvgPath.setContent("M0,0 H100 V87 H0 Z");
		smallSvgPath.setFill(Color.web("#DD734C"));
		smallSvgPath.setVisible(false);

		this.button = new Button();
		button.setPrefSize(btnWidth, btnHeight);
		button.setStyle("-fx-background-color: transparent;");

		this.tooltip = new Tooltip(text);
		Tooltip.install(button, tooltip);
		tooltip.setShowDelay(Duration.seconds(0.1));

		this.btnStackPane = new StackPane();
				

		this.btnOrangeImageView = new ImageView(new Image("file:src/main/resources/img/" + orangeFile));
		btnOrangeImageView.setFitWidth(imgSize);
		btnOrangeImageView.setFitHeight(imgSize);
		btnOrangeImageView.setVisible(false);

		this.btnGreyImageView = new ImageView(new Image("file:src/main/resources/img/" + greyFile));
		btnGreyImageView.setFitWidth(imgSize);
		btnGreyImageView.setFitHeight(imgSize);

		btnStackPane.getChildren().addAll(btnOrangeImageView, btnGreyImageView);

//		this.gridPane = createMenuGridPane();
		this.hbox = new HBox();
		
		this.label = new Label("     " + text);
		label.setFont(Font.font("Krona One", 18));
		hbox.getChildren().add(label);
		hbox.getChildren().add(btnStackPane);
		hbox.setAlignment(Pos.CENTER_RIGHT);
		HBox.setMargin(btnStackPane, new Insets(0, 30, 0, 30));

		
		// on translate
		largeSvgPath.setTranslateX(-100);
		hbox.setTranslateX(-100);
		button.setTranslateX(-100);
		this.setTranslateX(-100);
		
		this.getChildren().addAll(largeSvgPath, smallSvgPath, hbox, button);

	}


	// pour la croix / fleche
	public StackPaneMenubarAVANT(String greyFile, String orangeFile, String path, boolean visibility, int imgSize) {
		this.btnWidth = 100;
		this.btnHeight = 87;
		this.imgSize = imgSize;

		this.visibility = visibility;

		this.greyFile = greyFile;



		this.smallSvgPath = new SVGPath();
		smallSvgPath.setContent(path);
		smallSvgPath.setFill(Color.web("#DD734C"));

		this.button = new Button();
		button.setPrefSize(btnWidth, btnHeight);
		button.setStyle("-fx-background-color: transparent;");
		this.btnStackPane = new StackPane();

		// la flèche
		this.btnOrangeImageView = new ImageView(new Image("file:src/main/resources/img/" + orangeFile));
		btnOrangeImageView.setFitWidth(imgSize);
		btnOrangeImageView.setFitHeight(imgSize);

		// la croix
		this.btnGreyImageView = new ImageView(new Image("file:src/main/resources/img/" + greyFile));
		btnGreyImageView.setFitWidth(imgSize);
		btnGreyImageView.setFitHeight(imgSize);
		btnGreyImageView.setVisible(visibility);

		btnStackPane.getChildren().addAll(smallSvgPath, btnOrangeImageView, btnGreyImageView);
		this.setTranslateX(-100);
		this.getChildren().addAll(btnStackPane, button);
	}

	// Pour le bouton déconnexion
	public StackPaneMenubarAVANT(String orangeFile, String greyFile, String text, String largePath, String smallPath) {
		this.text = text;

		this.btnWidth = 300;
		this.btnHeight = 87;
		this.imgSize = 46;

		this.orangeFile = orangeFile;
		this.greyFile = greyFile;


		this.largeSvgPath = new SVGPath();
		largeSvgPath.setContent(largePath);
		largeSvgPath.setFill(Color.web("#DD734C"));
		
		this.smallSvgPath = new SVGPath();
		smallSvgPath.setContent(smallPath);
		smallSvgPath.setFill(Color.web("#DD734C"));
		smallSvgPath.setVisible(false);
		
		this.button = new Button();
		button.setPrefSize(btnWidth, btnHeight);
		button.setStyle("-fx-background-color: transparent;");

		this.tooltip = new Tooltip(text);
		Tooltip.install(button, tooltip);
		tooltip.setShowDelay(Duration.seconds(0.1));

		this.btnStackPane = new StackPane();

		this.btnOrangeImageView = new ImageView(new Image("file:src/main/resources/img/" + orangeFile));
		btnOrangeImageView.setFitWidth(imgSize);
		btnOrangeImageView.setFitHeight(imgSize);
		btnOrangeImageView.setVisible(false);

		this.btnGreyImageView = new ImageView(new Image("file:src/main/resources/img/" + greyFile));
		btnGreyImageView.setFitWidth(imgSize);
		btnGreyImageView.setFitHeight(imgSize);

		btnStackPane.getChildren().addAll(btnOrangeImageView, btnGreyImageView);
		
		this.gridPane = createMenuGridPane();
		
//		this.label = new Label("     " + text);
//		label.setFont(Font.font("Krona One", 18));
//		gridPane.add(label, 0, 0);
//		gridPane.add(btnStackPane, 1, 0);
		
		// on translate
		largeSvgPath.setTranslateX(-100);
		gridPane.setTranslateX(-100);
		button.setTranslateX(-100);
		this.setTranslateX(-100);
		
		this.getChildren().addAll(largeSvgPath, smallSvgPath, gridPane, button);
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
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
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
	public GridPane getGridPane() {
		return gridPane;
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
	public void setGridPane(GridPane gridPane) {
		this.gridPane = gridPane;
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


	private GridPane createMenuGridPane() {
		
		GridPane gridPane = new GridPane();
		
		// 2col 200 et 100
		// première colonne
		ColumnConstraints col1Constraints = new ColumnConstraints();
		col1Constraints.setPrefWidth(200);
		gridPane.getColumnConstraints().add(col1Constraints);

		// deuxième colonne
		ColumnConstraints col2Constraints = new ColumnConstraints();
		col2Constraints.setPrefWidth(100);
		gridPane.getColumnConstraints().add(col2Constraints);
		
		// 1 ligne
		RowConstraints rowConstraints = new RowConstraints();
		rowConstraints.setPrefHeight(87);
		gridPane.getRowConstraints().add(rowConstraints);
		
		return gridPane;
	}
}
