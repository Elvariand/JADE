package fr.isika.cda27.teamJADE.view.login;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.PasswordField;
import javafx.scene.control.skin.TextFieldSkin;
import javafx.scene.shape.SVGPath;

public class VisiblePasswordFieldSkin extends TextFieldSkin {

	public static final char BULLET = '\u2022';
	private final Button actionButton = new Button("View");
	private final SVGPath actionIcon = new SVGPath();

	private boolean mask = true;

	/**
	 * Crée un bouton qui permet d'afficher ou de masquer le contenu du mot de
	 * passe. Le bouton d'action est représenté par
	 * 
	 * @param textField Le champ du mot de passe.
	 */
	public VisiblePasswordFieldSkin(PasswordField textField) {
		super(textField);
		actionButton.setId("actionButton");
		actionButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		actionButton.setPrefSize(30, 30);
		actionButton.setFocusTraversable(false);
		actionButton.setStyle("-fx-background-color: transparent;");

		getChildren().add(actionButton);
		actionButton.setCursor(Cursor.HAND);
		actionButton.toFront();

		actionIcon.setContent(Icons.VIEWER.getContent());
		actionButton.setGraphic(actionIcon);

		actionButton.setOnMouseClicked(event -> {

			if (mask) {
				actionIcon.setContent(Icons.VIEWER_OFF.getContent());
				mask = false;
			} else {
				actionIcon.setContent(Icons.VIEWER.getContent());
				mask = true;
			}
			textField.setText(textField.getText());

			textField.end();

		});

	}

	/**
	 * Dispose les enfants de la skin dans la zone spécifiée, en ajustant leur
	 * position et leur taille.
	 * 
	 * @param x La coordonnée x de la position de départ pour la mise en page des
	 *          enfants. C'est la position horizontale de la zone de mise en page.
	 * @param y La coordonnée y de la position de départ pour la mise en page des
	 *          enfants. C'est la position verticale de la zone de mise en page.
	 * @param w La largeur disponible pour la mise en page des enfants. Cette valeur
	 *          définit la largeur totale du conteneur pour la disposition des
	 *          enfants.
	 * @param h La hauteur disponible pour la mise en page des enfants. Cette valeur
	 *          définit la hauteur totale du conteneur pour la disposition des
	 *          enfants.
	 */
	@Override
	protected void layoutChildren(double x, double y, double w, double h) {
		super.layoutChildren(x, y, w, h);

		layoutInArea(actionButton, x, y, w, h, 0, HPos.RIGHT, VPos.CENTER);
	}

	/**
	 * Masque le mot de passe saisi avec des caractères de remplacement.
	 *
	 * @param txt Le texte à masquer.
	 * @return Le texte masqué si le champ de texte est un mot de passe et le masque
	 *         est activé, sinon le texte d'origine est retourné.
	 */
	@Override
	protected String maskText(String txt) {
		if (getSkinnable() instanceof PasswordField && mask) {
			int n = txt.length();
			StringBuilder passwordBuilder = new StringBuilder(n);
			for (int i = 0; i < n; i++) {
				passwordBuilder.append(BULLET);
			}
			return passwordBuilder.toString();
		} else {

			return txt;
		}
	}
}

/**
 * Enumération représentant des icônes vectorielles sous forme de chaînes de
 * caractères SVG.
 * 
 * Cette enumération contient des constantes pour différentes icônes. Chaque
 * icône est représentée par une chaîne de caractères contenant des données SVG.
 * Les icônes incluent, par exemple, des états comme "VIEWER_OFF" et "VIEWER",
 * qui peuvent être utilisés dans une interface utilisateur pour afficher des
 * icônes correspondant à ces états.
 * 
 * Les icônes sont définies comme des chaînes de caractères SVG, ce qui permet
 * de les rendre dans une interface utilisateur ou de les manipuler
 * graphiquement.
 * 
 * @see #VIEWER_OFF
 * @see #VIEWER
 */
enum Icons {
	/**
	 * Icône représentant un état où le visionneur est désactivé.
	 */
	VIEWER_OFF("M12 6c3.79 0 7.17 2.13 8.82 5.5-.59 1.22-1.42 2.27-2."
			+ "41 3.12l1.41 1.41c1.39-1.23 2.49-2.77 3.18-4.53C21.27 7.11 17 4 12 4c-1.27 "
			+ "0-2.49.2-3.64.57l1.65 1.65C10.66 6.09 11.32 6 12 6zm-1.07 1.14L13 9.21c.57.25 1.03.71 "
			+ "1.28 1.28l2.07 2.07c.08-.34.14-.7.14-1.07C16.5 9.01 14.48 7 12 7c-.37 0-.72.05-1.07."
			+ "14zM2.01 3.87l2.68 2.68C3.06 7.83 1.77 9.53 1 11.5 2.73 15.89 7 19 12 19c1.52 0 2.98-.29 "
			+ "4.32-.82l3.42 3.42 1.41-1.41L3.42 2.45 2.01 3.87zm7.5 7.5l2.61 2.61c-.04.01-.08.02-.12.02-1.38 "
			+ "0-2.5-1.12-2.5-2.5 0-.05.01-.08.01-.13zm-3.4-3.4l1.75 1.75c-.23.55-.36 1.15-.36 1.78 0 2.48 2.02 "
			+ "4.5 4.5 4.5.63 0 1.23-.13 1.77-.36l.98.98c-.88.24-1.8.38-2.75.38-3.79 0-7.17-2.13-8.82-5.5.7-1.43 1.72-2.61 2.93-3.53z"),
	/**
	 * Icône représentant un état où le visionneur est actif.
	 */
	VIEWER("M12 4.5C7 4.5 2.73 7.61 1 12c1.73 4.39 6 7.5 11 7.5s9.27-3.11 11-7.5c-1.73-4.39-6-7."
			+ "5-11-7.5zM12 17c-2.76 0-5-2.24-5-5s2.24-5 5-5 5 2.24 5 5-2.24 5-5 5zm0-8c-1.66 0-3 1.34-3 3s1.34 3 3 3 3-1.34 3-3-1.34-3-3-3z");

	private String content;

	/**
	 * Constructeur de l'énumération {@code Icons}.
	 * 
	 * @param content La chaîne de caractères SVG représentant l'icône.
	 */
	Icons(String content) {
		this.content = content;
	}

	/**
	 * Obtient le contenu SVG de l'icône.
	 * 
	 * @return La chaîne de caractères SVG de l'icône.
	 */
	public String getContent() {
		return content;
	}
}
