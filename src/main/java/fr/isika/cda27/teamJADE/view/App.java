package fr.isika.cda27.teamJADE.view;

import fr.isika.cda27.teamJADE.model.InternDao;
import fr.isika.cda27.teamJADE.model.Member;
import fr.isika.cda27.teamJADE.view.login.CustomLoginScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	private final static String INTERN_DON_FILE = "src/main/resources/data/STAGIAIRES.DON";
	private final static String INTERN_BIN_FILE = "src/main/resources/data/STAGIAIRES.bin";
	private final static String MEMBER_BIN_FILE = "src/main/resources/data/MEMBRES.bin";

	/**
	 * @return le fichier .don
	 */
	public static String getInternDonFile() {
		return INTERN_DON_FILE;
	}

	/**
	 * @return le fichier binaire
	 */
	public static String getInternBinFile() {
		return INTERN_BIN_FILE;
	}

	/**
	 * @return le fichier binaire
	 */
	public static String getMemberBinFile() {
		return MEMBER_BIN_FILE;
	}

	@Override
	public void start(Stage stage) {

		Member member = new Member("alexia", "mdp", "ROY", "alexia", "mailalexia@gmail.com");

		CustomLoginScene root = new CustomLoginScene();

		// scene
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

		Image icon = new Image(getClass().getResourceAsStream("/img/logo.jpg"));

		stage.setResizable(false);
		stage.setScene(scene);
		stage.getIcons().add(icon);
		stage.setTitle("Annuaire d'ISIKA");
		stage.show();

	}

	public static void main(String[] args) {

//		InternDao test_intern = new InternDao();
//		test_intern.deleteBinary();
//		test_intern.addFromDon();

		launch();
	}

}