package fr.isika.cda27.teamJADE.view;

import java.io.IOException;
import java.io.RandomAccessFile;

import fr.isika.cda27.teamJADE.view.CustomMainScene;
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

		// mainscene
//		CustomLoginScene root = new CustomLoginScene();
		CustomMainScene root = new CustomMainScene();

		// scene
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
		

		stage.setResizable(false);
		stage.setScene(scene);
		stage.setTitle("Annuaire");
		stage.show();
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
		Intern PY = new Intern("ROIGNANT", "Pierre-Yves", "77", "AI 95", "2015");
		test.addIntern(PY);
		test.addIntern(PY);
		test.addIntern(PY);
		test.addIntern(PY);
		test.readBinary();
		test.removeIntern(PY);
		System.out.println("======================");
		test.readBinary();
		System.out.println("======================");
		test.addIntern(PY);
		test.readBinary();

		launch();
	}

}