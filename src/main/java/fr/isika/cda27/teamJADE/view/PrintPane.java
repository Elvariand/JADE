package fr.isika.cda27.teamJADE.view;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.Font;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import fr.isika.cda27.teamJADE.model.Intern;
import fr.isika.cda27.teamJADE.utilz.CustomButton;
import fr.isika.cda27.teamJADE.utilz.CustomTextField;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.scene.Scene;
import javafx.stage.Stage;


import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.Colors.GREY_COLOR;
import static fr.isika.cda27.teamJADE.utilz.UtilStaticValues.MenuVboxValues.*;


public class PrintPane extends RepetitivePane {

	protected Button button;
	protected TableView tableView;
	protected BaseColor borderColor = new BaseColor(39, 39, 39);
	protected BaseColor orangeColor = new BaseColor(221, 115, 76);
	protected String saveDirectory = "";
	protected String fileName = "";
	
	public PrintPane(TableView tableView) {
		super();
		
		this.titleLabel.setText("Impression de l'annuaire");
		
		this.tableView = tableView;
		
		LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd_MM_yyyy");
        String formattedDate = currentDate.format(formatter);
        
        CustomTextField fileNameField = new CustomTextField();
        fileNameField.setText("Table_des_stagiaires_" + formattedDate + ".pdf");
        fileNameField.setMaxWidth(540);
        
        CustomButton chooseDirectoryButton = new CustomButton("Dossier cible");
        chooseDirectoryButton.setPrefWidth(220);
        chooseDirectoryButton.setOnAction(e -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog((Stage)PrintPane.this.getScene().getWindow());
            if (selectedDirectory != null) {
                saveDirectory = selectedDirectory.getAbsolutePath();
            }
        });
        

		this.button = new CustomButton("Exporter");
		button.setOnAction(e -> {
            fileName = fileNameField.getText();
            if (saveDirectory.isEmpty() || fileName.isEmpty()) {
                
                return;
            }
			exportToPDF(tableView);
        });
		
		VBox buttonBox = new VBox(40);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setPrefSize(BTN_BOX_WIDTH, 400);
		
		buttonBox.getChildren().addAll(fileNameField,chooseDirectoryButton);
		
		this.setSpacing(40);
		
		this.getChildren().set(1, buttonBox);
		this.getChildren().set(2, button);
	}


	private void exportToPDF(TableView tableView) {
		File file = new File(saveDirectory, fileName);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
        	Document document = new Document();
            PdfWriter.getInstance(document,fileOutputStream);
            document.setMargins(50, 50, 50, 50);
            
            document.open();

            BaseFont baseFont = BaseFont.createFont("src/main/resources/fonts/KronaOne-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font titleFont = new Font(baseFont, 24, Font.BOLD);
            
            // ajout du titre
            Paragraph title = new Paragraph("Liste des Stagiaires", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            
            // retour à la ligne
            document.add(new Paragraph("\n\n\n"));
            
            // ajout du soustitre
            Font normalFont = new Font(baseFont, 14,Font.UNDERLINE);
            Paragraph subtitle = new Paragraph("Critères de sélection :", normalFont);
            document.add(subtitle);
            
            // retour à la ligne
            document.add(new Paragraph("\n\n"));
            
            Font textFont = new Font(baseFont, 12);
            // ajout des lignes de critères 
            document.add(new Paragraph("Nom de famille : ", textFont));
            document.add(new Paragraph("Prénom : ", textFont));
            document.add(new Paragraph("Région : ", textFont));
            document.add(new Paragraph("Formation suivie : ", textFont));
            document.add(new Paragraph("Année : ", textFont));

            // retour à la ligne
            document.add(new Paragraph("\n\n"));
            
            // ajout de la table
            PdfPTable pdfTable = new PdfPTable(tableView.getColumns().size());
            pdfTable.setWidthPercentage(100);
                        
            // table du header
            addTableHeader(pdfTable, tableView);

            // ajout des lignes
            addRows(pdfTable);

            document.add(pdfTable);
            document.close();
            System.out.println("PDF created successfully!");
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
	        Intern intern = (Intern) item;
	        
	        pdfTable.addCell(createCell(intern.getFamilyName(), borderColor));
	        pdfTable.addCell(createCell(intern.getFirstName(), borderColor));
	        pdfTable.addCell(createCell(String.valueOf(intern.getCounty()), borderColor));
	        pdfTable.addCell(createCell(intern.getCursus(), borderColor));
	        pdfTable.addCell(createCell(String.valueOf(intern.getYearIn()), borderColor));
	    }
	}
	
    private PdfPCell createCell(String content, BaseColor borderColor) {
        PdfPCell cell = new PdfPCell(new Phrase(content));
        cell.setBorderColor(borderColor);
        cell.setBorderWidth(1); 
        return cell;
    }

}
