package fr.isika.cda27.teamJADE.view.help;

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
	private Tooltip tooltip;

	/**
	 * Crée le bouton d'aide
	 * 
	 * @param helpFile      Le nom du fichier d'image pour l'état normal du bouton.
	 * @param helpHoverFile Le nom du fichier d'image pour l'état de survol du
	 *                      bouton.
	 */
	public StackPaneHelp(String helpFile, String helpHoverFile) {

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

	}

	/**
	 * @return L'image d'aide du bouton, une instance de {@link ImageView}
	 */
	public ImageView getBtnHelpImageView() {
		return btnHelpImageView;
	}

	/**
	 * @param btnHelpImageView L'image d'aide du bouton à définir
	 */
	public void setBtnHelpImageView(ImageView btnHelpImageView) {
		this.btnHelpImageView = btnHelpImageView;
	}

	/**
	 * @return L'image d'aide du bouton pour l'état de survol, une instance de
	 *         {@link ImageView}
	 */
	public ImageView getBtnHelpHoverImageView() {
		return btnHelpHoverImageView;
	}

	/**
	 * @param btnHelpHoverImageView L'image d'aide du bouton pour l'état de survol à
	 *                              définir
	 */
	public void setBtnHelpHoverImageView(ImageView btnHelpHoverImageView) {
		this.btnHelpHoverImageView = btnHelpHoverImageView;
	}

	/**
	 * @return Le bouton
	 */
	public Button getButton() {
		return button;
	}

	/**
	 * @param bouton Le bouton à définir
	 */
	public void setButton(Button button) {
		this.button = button;
	}

}
