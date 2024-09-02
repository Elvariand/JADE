package fr.isika.cda27.teamJADE.view.mainIntern;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.Colors.SET_BG_ORANGE_COLOR;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.BTN_BOX_WIDTH;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import fr.isika.cda27.teamJADE.model.Intern;
import fr.isika.cda27.teamJADE.model.Member;
import fr.isika.cda27.teamJADE.utilz.CustomButton;
import fr.isika.cda27.teamJADE.utilz.CustomTextField;
import fr.isika.cda27.teamJADE.utilz.FadingErrorLabel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class CustomPrintBox extends VBox {

	private FadingErrorLabel labelError;
	protected CustomButton exportButton;
	protected CustomButton chooseDirectoryButton;
	protected TableView tableView;
	protected BaseColor borderColor = new BaseColor(39, 39, 39);
	protected BaseColor orangeColor = new BaseColor(221, 115, 76);
	protected String saveDirectory = "";
	protected String fileName = "";

	/**
	 * Gère l'exportation des données d'une TableView vers un fichier PDF.
	 * 
	 * @param tableView  La `TableView` contenant les données à exporter.
	 * @param labelError Le `Label` utilisé pour afficher les messages d'erreur ou
	 *                   de succès.
	 */
	public CustomPrintBox(TableView tableView, FadingErrorLabel labelError) {
		this.tableView = tableView;
		this.setSpacing(40);
		this.setAlignment(Pos.CENTER);
		this.setPrefSize(BTN_BOX_WIDTH, 200);

		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd_MM_yyyy");
		String formattedDate = currentDate.format(formatter);

		CustomTextField fileNameField = new CustomTextField();
		fileNameField.setMaxChars(200);

		Object item = tableView.getItems().get(1);

		if (item instanceof Intern) {
			fileNameField.setText("Table_des_stagiaires_" + formattedDate + ".pdf");

		} else if (item instanceof Member) {
			fileNameField.setText("Table_des_membres_" + formattedDate + ".pdf");
		}
		fileNameField.setStyle(SET_BG_ORANGE_COLOR + "-fx-background-radius: 13; " + "-fx-border-radius: 13; "
				+ "-fx-border-color: transparent transparent #704739 transparent; -fx-text-fill: #454443;");
		fileNameField.setMaxWidth(540);

		CustomButton chooseDirectoryButton = new CustomButton("Dossier cible");
		chooseDirectoryButton.setPrefWidth(220);

		// si on clique dans le fileNameField
		fileNameField.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				// Le TextField a élé cliqué
				fileNameField.setStyle(SET_BG_ORANGE_COLOR + "-fx-background-radius: 13; " + "-fx-border-radius: 13; "
						+ "-fx-border-color: transparent transparent #704739 transparent; "
						+ "-fx-text-fill: #272727;");
			}
		});

		chooseDirectoryButton.setOnAction(e -> {
			DirectoryChooser directoryChooser = new DirectoryChooser();
			File selectedDirectory = directoryChooser.showDialog((Stage) CustomPrintBox.this.getScene().getWindow());
			if (selectedDirectory != null) {
				saveDirectory = selectedDirectory.getAbsolutePath();
			}
		});

		this.labelError = labelError;
		this.labelError.setPadding(new Insets(50, 0, 0, 0));

		this.exportButton = new CustomButton("Exporter");
		exportButton.setOnAction(e -> {
			fileName = fileNameField.getText();
			if (saveDirectory.isEmpty() || fileName.isEmpty()) {
				this.labelError.show();
				return;
			} else {
				this.labelError.setText("L'annuaire a bien été exporté");
				this.labelError.show();
				exportToPDF(tableView);
			}
		});

		this.getChildren().addAll(fileNameField, chooseDirectoryButton, labelError, exportButton);

	}

	/**
	 * Exporte le contenu d'un {@link TableView} vers un fichier PDF. Le fichier PDF
	 * est créé dans un répertoire de sauvegarde spécifique avec un nom de fichier
	 * défini. Le PDF comprend un titre centré en fonction du type des éléments
	 * contenus dans la table (par exemple "Liste des Stagiaires" pour des éléments
	 * de type {@code Intern} ou "Liste des Membres" pour des éléments de type
	 * {@code Member}). La table est également exportée sous forme de tableau PDF.
	 *
	 * <p>
	 * Cette méthode utilise la bibliothèque iText pour générer le fichier PDF.
	 * <p>
	 *
	 * @param tableView le {@link TableView} dont les données doivent être exportées
	 *                  au format PDF
	 */
	private void exportToPDF(TableView tableView) {
		File file = new File(saveDirectory, fileName);
		try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
			Document document = new Document();
			PdfWriter.getInstance(document, fileOutputStream);
			document.setMargins(50, 50, 50, 50);

			document.open();

			BaseFont baseFont = BaseFont.createFont("src/main/resources/fonts/KronaOne-Regular.ttf",
					BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font titleFont = new Font(baseFont, 24, Font.BOLD);

			// ajout du titre
			Object item = tableView.getItems().get(1);

			if (item instanceof Intern) {
				Paragraph title = new Paragraph("Liste des Stagiaires", titleFont);
				title.setAlignment(Element.ALIGN_CENTER);
				document.add(title);

			} else if (item instanceof Member) {
				Paragraph title = new Paragraph("Liste des Membres", titleFont);
				title.setAlignment(Element.ALIGN_CENTER);
				document.add(title);
			}

			// retour à la ligne
			document.add(new Paragraph("\n\n\n"));

			// ajout de la table
			PdfPTable pdfTable = new PdfPTable(tableView.getColumns().size());
			pdfTable.setWidthPercentage(100);

			// table du header
			addTableHeader(pdfTable, tableView);

			// ajout des lignes
			addRows(pdfTable);

			document.add(pdfTable);
			document.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Ajoute un en-tête à la table PDF à partir des colonnes d'un
	 * {@link TableView}.
	 * 
	 * <p>
	 * Pour chaque colonne du {@link TableView}, une cellule d'en-tête est créée
	 * avec un texte correspondant au titre de la colonne. La cellule est
	 * personnalisée avec une couleur d'arrière-plan et une hauteur fixe. La bordure
	 * de la cellule est également définie avec une couleur spécifique.
	 * </p>
	 * 
	 * @param pdfTable  le {@link PdfPTable} à laquelle les en-têtes des colonnes
	 *                  seront ajoutés
	 * @param tableView le {@link TableView} à partir duquel les titres des colonnes
	 *                  seront récupérés
	 */

	private void addTableHeader(PdfPTable pdfTable, TableView<?> tableView) {
		for (TableColumn<?, ?> column : tableView.getColumns()) {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(orangeColor);
			header.setFixedHeight(20);

			String columnTitle = column.getText();
			header.setPhrase(new Phrase(columnTitle));

			header.setBorderColor(borderColor);
			pdfTable.addCell(header);
		}
	}

	/**
	 * Ajoute des lignes de données à un tableau PDF en fonction des éléments
	 * présents dans la tableView. Cette méthode gère deux types d'objets :
	 * {@code Intern} et {@code Member}. Pour chaque élément, elle extrait les
	 * informations pertinentes et les ajoute sous forme de cellules au tableau PDF.
	 * 
	 * @param pdfTable Le tableau PDF (de type {@code PdfPTable}) auquel les lignes
	 *                 de données seront ajoutées.
	 */
	private void addRows(PdfPTable pdfTable) {
		for (Object item : tableView.getItems()) {

			if (item instanceof Intern) {

				Intern intern = (Intern) item;
				pdfTable.addCell(createCell(intern.getFamilyName(), borderColor));
				pdfTable.addCell(createCell(intern.getFirstName(), borderColor));
				pdfTable.addCell(createCell(String.valueOf(intern.getCounty()), borderColor));
				pdfTable.addCell(createCell(intern.getCursus(), borderColor));
				pdfTable.addCell(createCell(String.valueOf(intern.getYearIn()), borderColor));

			} else if (item instanceof Member) {
				Member member = (Member) item;
				pdfTable.addCell(createCell(member.getFamilyName(), borderColor));
				pdfTable.addCell(createCell(member.getName(), borderColor));
				pdfTable.addCell(createCell(member.getAlias(), borderColor));
				pdfTable.addCell(createCell(member.getEmail(), borderColor));
				pdfTable.addCell(createCell(member.getAdmin(), borderColor));
			}
		}
	}

	/**
	 * Crée une cellule PDF avec un contenu textuel et une bordure colorée.
	 * 
	 * <p>
	 * Cette méthode génère une instance de {@link PdfPCell} contenant le texte
	 * spécifié.
	 * </p>
	 * 
	 * @param content     le texte à afficher dans la cellule PDF
	 * @param borderColor la couleur de la bordure de la cellule
	 * @return une nouvelle instance de {@link PdfPCell} avec le contenu et les
	 *         attributs spécifiés
	 */
	private PdfPCell createCell(String content, BaseColor borderColor) {
		PdfPCell cell = new PdfPCell(new Phrase(content));
		cell.setBorderColor(borderColor);
		cell.setBorderWidth(1);
		return cell;
	}
}
