package fr.isika.cda27.teamJADE.view;

import java.io.IOException;
import java.io.RandomAccessFile;

import fr.isika.cda27.teamJADE.model.Intern;
import fr.isika.cda27.teamJADE.model.TreeNodeDao;
import fr.isika.cda27.teamJADE.model.InternDao;
import fr.isika.cda27.teamJADE.model.TreeNode;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
	
	private final static String FICHIER_DON = "src/main/resources/data/TEST_STAGIAIRES.DON";
	private final static String FICHIER_BIN = "src/main/resources/data/TEST_STAGIAIRES.bin";

	/**
	 * @return le fichier .don
	 */
	public static String getFichierDon() {
		return FICHIER_DON;
	}

	/**
	 * @return le fichier binaire
	 */
	public static String getFichierBin() {
		return FICHIER_BIN;
	}

	@Override
	public void start(Stage stage) {
		var javaVersion = SystemInfo.javaVersion();
		var javafxVersion = SystemInfo.javafxVersion();

		var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
		var scene = new Scene(new StackPane(label), 640, 480);
		stage.setScene(scene);
//        stage.show();

//    	Intern intern1 = new Intern ("JACQUIER", "Delphine", "73", "CDA 27", "2024"); 
//    	Intern intern2 = new Intern ("ROY", "Alexia", "31", "CDA 27", "2024"); 
//    	Intern intern3 = new Intern ("REVILLARD", "Jason", "60", "CDA 27", "2024"); 

	}

	public static void main(String[] args) {
		TreeNodeDao test = new TreeNodeDao();
		test.deleteBinary();
		test.addFromDon();
//    	test.sortView(test.getRoot());

		try {
			RandomAccessFile raf = new RandomAccessFile(App.getFichierBin(), "rw");
			for (long cursor = 0; cursor < test.getBinarySize(); cursor++) {
				Intern intern = test.readInternFromBinary(cursor);
				int indexLeft = test.readLeftChildFromBinary(cursor);
				int indexRight = test.readRightChildFromBinary(cursor);
				int indexTwin = test.readTwinFromBinary(cursor);
				System.out.println(intern.getFamilyNameLong().substring(0, 10) + "\t" + intern.getFirstNameLong().substring(0, 11) + "\t" + intern.getCounty() + "\t"
						+ intern.getCursusLong() + "\t" + intern.getYearIn() + "\t" + indexLeft + "\t" + indexRight + "\t" + indexTwin);
				cursor += TreeNode.getSizeNode() - 1;
			}
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		launch();
	}

}