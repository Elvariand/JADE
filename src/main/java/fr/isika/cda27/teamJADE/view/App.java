package fr.isika.cda27.teamJADE.view;

import fr.isika.cda27.teamJADE.model.Intern;
import fr.isika.cda27.teamJADE.model.TreeNodeDao;
import fr.isika.cda27.teamJADE.model.InternDao;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        var scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
        stage.show();
        
        InternDao test2 = new InternDao();
        TreeNodeDao test = test2.addFromDon();
//    	Intern intern1 = new Intern ("JACQUIER", "Delphine", "73", "CDA 27", "2024"); 
//    	Intern intern2 = new Intern ("ROY", "Alexia", "31", "CDA 27", "2024"); 
//    	Intern intern3 = new Intern ("REVILLARD", "Jason", "60", "CDA 27", "2024"); 
//    	
//    	test.addIntern(intern1);
//    	test.addIntern(intern2);
//    	test.addIntern(intern3);
        
     
        
        test.sortView(test.getRoot());
        
//        test.getRoot().parcoursInfixe(test.getRoot());
    }
    
    

    public static void main(String[] args) {
        
    	
    	launch();
    }

}