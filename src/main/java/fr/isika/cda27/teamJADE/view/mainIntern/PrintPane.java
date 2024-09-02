package fr.isika.cda27.teamJADE.view.mainIntern;

import com.itextpdf.text.BaseColor;

import fr.isika.cda27.teamJADE.utilz.FadingErrorLabel;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.text.Font;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.Colors.GREY_COLOR;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.*;

public class PrintPane extends RepetitivePane {

	private FadingErrorLabel labelError;
	protected Button button;
	protected TableView tableView;
	protected BaseColor borderColor = new BaseColor(39, 39, 39);
	protected BaseColor orangeColor = new BaseColor(221, 115, 76);
	protected String saveDirectory = "";
	protected String fileName = "";

	/**
	 * Crée le panneau 'Imprimer'
	 * 
	 * @param tableView La `TableView` contenant les données à exporter.
	 */
	public PrintPane(TableView tableView) {
		super();
		this.setSpacing(40);
		this.titleLabel.setText("Impression de l'annuaire");
		this.titleLabel.setPadding(new Insets(0, 0, 100, 0));

		this.tableView = tableView;

		this.labelError = new FadingErrorLabel("Veuilez sélectionner un dossier cible");
		this.labelError.setPrefSize(LABEL_ERROR_WIDTH, LABEL_ERROR_HEIGHT);
		this.labelError.setFont(Font.font("Krona One", 14));
		this.labelError.setStyle("-fx-alignment: center;");
		this.labelError.setTextFill(GREY_COLOR);
		this.labelError.setVisible(false);

		CustomPrintBox customPrintBox = new CustomPrintBox(tableView, labelError);

		this.getChildren().set(1, customPrintBox);
		this.getChildren().remove(2);
	}

}
