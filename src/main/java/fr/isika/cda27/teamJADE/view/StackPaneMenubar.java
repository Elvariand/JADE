package fr.isika.cda27.teamJADE.view;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;

public class StackPaneMenubar extends StackPane {
	private StackPane stackPane;
	private StackPane btnStackPane;
	private SVGPath svgPath;
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
	
	
	/**
	 * @param stackPane
	 * @param svgPath
	 * @param button
	 * @param btnOrangeImageView
	 * @param btnGreyImageView
	 * @param orangeFile
	 * @param greyFile
	 * @param btnWidth
	 * @param btnHeight
	 * @param imgSize
	 * @param visibility
	 */
	public StackPaneMenubar(String orangeFile, String greyFile, String text) {
		this.text = text;
		
		this.btnWidth = 100;
		this.btnHeight = 87;
		this.imgSize = 46;
		
		this.orangeFile = orangeFile;
		this.greyFile = greyFile;
		
		this.stackPane =  new StackPane();
		
		this.svgPath = new SVGPath();
		svgPath.setContent("M0,0 H100 V87 H0 Z");
    	svgPath.setFill(Color.web("#DD734C"));
    	
		this.button =  new Button();
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
		
	    btnStackPane.getChildren().addAll(svgPath, btnOrangeImageView, btnGreyImageView);
	    stackPane.getChildren().addAll(btnStackPane,button);
	    
//	    button.toFront();
	}
	
	
	public StackPaneMenubar(String greyFile,String path, boolean visibility,int imgSize ) {
		this.btnWidth = 100;
		this.btnHeight = 87;
		this.imgSize = imgSize;
		
		this.visibility = visibility;
		
		this.greyFile = greyFile;
		
		this.stackPane =  new StackPane();
		
		this.svgPath = new SVGPath();
		svgPath.setContent(path);
    	svgPath.setFill(Color.web("#DD734C"));
    	
		this.button =  new Button();
		button.setPrefSize(btnWidth, btnHeight);
	    button.setStyle("-fx-background-color: transparent;");
	    this.btnStackPane = new StackPane();
	    
		this.btnGreyImageView = new ImageView(new Image("file:src/main/resources/img/" + greyFile));
		btnGreyImageView.setFitWidth(imgSize);
	    btnGreyImageView.setFitHeight(imgSize);
	    btnGreyImageView.setVisible(visibility);
	    
	    btnStackPane.getChildren().addAll(svgPath, btnGreyImageView);
	    stackPane.getChildren().addAll(btnStackPane,button);
	}
	
	public StackPaneMenubar(String orangeFile, String greyFile, String text, String path) {
		this.text = text;
		
		this.btnWidth = 100;
		this.btnHeight = 87;
		this.imgSize = 46;
		
		this.orangeFile = orangeFile;
		this.greyFile = greyFile;
		
		this.stackPane =  new StackPane();
		
		this.svgPath = new SVGPath();
		svgPath.setContent(path);
    	svgPath.setFill(Color.web("#DD734C"));
    	
		this.button =  new Button();
		button.setPrefSize(btnWidth, btnHeight);
	    button.setStyle("-fx-background-color: transparent;");
	    
	    this.tooltip= new Tooltip(text);
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
		
	    btnStackPane.getChildren().addAll(svgPath, btnOrangeImageView, btnGreyImageView);
	    stackPane.getChildren().addAll(btnStackPane,button);
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
	 * @return the stackPane
	 */
	public StackPane getStackPane() {
		return stackPane;
	}

	/**
	 * @param stackPane the stackPane to set
	 */
	public void setStackPane(StackPane stackPane) {
		this.stackPane = stackPane;
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
	public SVGPath getSvgPath() {
		return svgPath;
	}

	/**
	 * @param svgPath the svgPath to set
	 */
	public void setSvgPath(SVGPath svgPath) {
		this.svgPath = svgPath;
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
	
	
	
	
//	svgPath.setContent("M0,0 H100 V85 H0 Z");
//	svgPath.setFill(Color.web("#DD734C"));
//	stackPane.getChildren().addAll(svgPath, button);
}
