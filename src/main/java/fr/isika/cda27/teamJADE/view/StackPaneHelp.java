package fr.isika.cda27.teamJADE.view;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class StackPaneHelp extends StackPane {

	
	private Button button;
	private ImageView btnHelpImageView;
	private ImageView btnHelpHoverImageView;
	private String helpFile;
	private String helpHoverFile;
	private int size;
	private boolean visibility = true;
	private Tooltip tooltip;
	

public StackPaneHelp (String helpFile, String helpHoverFile) {
		
		this.size = 50;

		this.helpFile = helpFile;
		this.helpHoverFile = helpHoverFile;
		

		this.button = new Button();
		button.setPrefSize(size, size);
		button.setStyle("-fx-background-color: transparent;");
		

		this.tooltip = new Tooltip("Aide");
		Tooltip.install(button, tooltip);
		tooltip.setShowDelay(Duration.seconds(0.1));

		this.btnHelpImageView = new ImageView(new Image("file:src/main/resources/img/" + helpFile));
		btnHelpImageView.setFitWidth(size);
		btnHelpImageView.setFitHeight(size);
		

		this.btnHelpHoverImageView = new ImageView(new Image("file:src/main/resources/img/" + helpHoverFile));
		btnHelpHoverImageView.setFitWidth(size);
		btnHelpHoverImageView.setFitHeight(size);
		btnHelpHoverImageView.setVisible(false);
		
	button.setOnMouseEntered(event -> {
			// HelpImage deviennent invisible et mon HelpHoverImage deviennent visible
		btnHelpImageView.setVisible(false); 
		btnHelpHoverImageView.setVisible(true); 
								
}); 
		//
		button.setOnMouseExited(event -> {
			btnHelpHoverImageView.setVisible(false); 
			btnHelpImageView.setVisible(true); 
		}); 

		this.getChildren().addAll(btnHelpImageView, btnHelpHoverImageView, button);

	// set on Action 
}


public ImageView getBtnHelpImageView() {
	return btnHelpImageView;
}


public void setBtnHelpImageView(ImageView btnHelpImageView) {
	this.btnHelpImageView = btnHelpImageView;
}


public ImageView getBtnHelpHoverImageView() {
	return btnHelpHoverImageView;
}


public void setBtnHelpHoverImageView(ImageView btnHelpHoverImageView) {
	this.btnHelpHoverImageView = btnHelpHoverImageView;
}


public Button getButton() {
	return button;
}


public void setButton(Button button) {
	this.button = button;
}

}
