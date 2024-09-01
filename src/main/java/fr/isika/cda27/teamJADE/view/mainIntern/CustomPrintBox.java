package fr.isika.cda27.teamJADE.view.mainIntern;

import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.Colors.GREY_COLOR;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.Colors.SET_BG_ORANGE_COLOR;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.BTN_BOX_WIDTH;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.LABEL_ERROR_HEIGHT;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.LABEL_ERROR_WIDTH;

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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Label;
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

			// ajout du soustitre
//			Font normalFont = new Font(baseFont, 14, Font.UNDERLINE);
//			Paragraph subtitle = new Paragraph("Critères de sélection :", normalFont);
//			document.add(subtitle);
//
//			// retour à la ligne
//			document.add(new Paragraph("\n\n"));

			/* SI ON A LE TEMPS */
//			Font textFont = new Font(baseFont, 12);
//			
//			// ajout des lignes de critères
//			if (item instanceof Intern) {
//				document.add(new Paragraph("Nom de famille : ", textFont));
//				document.add(new Paragraph("Prénom : ", textFont));
//				document.add(new Paragraph("Région : ", textFont));
//				document.add(new Paragraph("Formation suivie : ", textFont));
//				document.add(new Paragraph("Année : ", textFont));
//
//			} else if (item instanceof Member) {
//				document.add(new Paragraph("Nom de famille : ", textFont));
//				document.add(new Paragraph("Prénom : ", textFont));
//				document.add(new Paragraph("Pseudo : ", textFont));
//				document.add(new Paragraph("Mail : ", textFont));
//				document.add(new Paragraph("Status : ", textFont));
//			}
//
//			// retour à la ligne
//			document.add(new Paragraph("\n\n"));

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

	private PdfPCell createCell(String content, BaseColor borderColor) {
		PdfPCell cell = new PdfPCell(new Phrase(content));
		cell.setBorderColor(borderColor);
		cell.setBorderWidth(1);
		return cell;
	}
}
