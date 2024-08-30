package fr.isika.cda27.teamJADE.view;


import java.util.ArrayList;

import fr.isika.cda27.teamJADE.model.Intern;
import fr.isika.cda27.teamJADE.model.InternDao;
import fr.isika.cda27.teamJADE.model.Member;
import fr.isika.cda27.teamJADE.model.MemberDao;
import fr.isika.cda27.teamJADE.model.TreeNodeDao;
import fr.isika.cda27.teamJADE.view.login.CustomLoginScene;
import fr.isika.cda27.teamJADE.view.mainIntern.CustomMainScene;
import fr.isika.cda27.teamJADE.view.mainMember.MembersMainScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	private final static String INTERN_DON_FILE = "src/main/resources/data/TEST_STAGIAIRES.DON";
	private final static String INTERN_BIN_FILE = "src/main/resources/data/TEST_STAGIAIRES.bin";
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

		// mainscene
//		CustomLoginScene root = new CustomLoginScene();
//		CustomMainScene root = new CustomMainScene();
		MembersMainScene root = new MembersMainScene();

		// scene
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
		
		Image icon = new Image(getClass().getResourceAsStream("/img/logo.jpg"));
				
		stage.setResizable(false);
		stage.setScene(scene);
		stage.getIcons().add(icon);
		stage.setTitle("Annuaire d'ISIKA");
		stage.show();

//    	Intern intern1 = new Intern ("JACQUIER", "Delphine", "73", "CDA 27", "2024"); 
//    	Intern intern2 = new Intern ("ROY", "Alexia", "31", "CDA 27", "2024"); 
//    	Intern intern3 = new Intern ("REVILLARD", "Jason", "60", "CDA 27", "2024"); 

	}

	public static void main(String[] args) {

//		InternDao test_intern = new InternDao();
//		test_intern.deleteBinary();
//		test_intern.addFromDon();
////		test_intern.sortView(0);
//		System.out.println("======================");
//		Intern PY = new Intern("ROIGNANT", "Pierre-Yves", 77, "AI 95", 2015);
//		test_intern.insert(PY);
//		test_intern.readBinary();
//		test_intern.delete(new Intern("CHAVENEAU", "Kim Anh", 92, "ATOD 22", 2014));
//		System.out.println("======================");
//		test_intern.readBinary();
//
//		System.out.println("======================");
//		System.out.println("======================");
//		MemberDao test_member = new MemberDao();
//		test_member.deleteBinary();
//		Member User1 = new Member("jacqueline","mdpdedelphine","JACQUIER", "delphine","maildelphine@gmail.com" );
//		Member User2 = new Member("elChipoteur","mdpdejason","REVILLARD", "jason","mailjason@gmail.com" );
//		Member User3 = new Member("alexiadu81", "mdpdealexia","ROY", "alexia","mailalexia@gmail.com");
//		
//		test_member.addFirstMember();
//		test_member.insert(User1);
//		test_member.insert(User2);
//		test_member.insert(User3);
//		
//		System.out.println("======================");
////		test_member.sortView(0);
//		test_member.readBinary();
		
		
		launch();
	}

}