
package main;

import controller.ControllerFirstWindow;
import controller.ControllerSearchPeople;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ComparatorFullname;
import model.ComparatorLastname;
import model.ComparatorName;
import model.Node;
import model.Person;
import model.Tree;

public class Main extends Application{

	public static void main(String[] args) {
//		Tree<Person> primerTree=new Tree<>(new ComparatorName());
//		Tree<Person> segTree=new Tree<>(new ComparatorLastname());
//		Tree<Person> terTree=new Tree<>(new ComparatorFullname());
		//Personas de prueba.
//		System.out.println("Holaaa");
//		
//		primerTree.add(new Node<Person>(new Person("1","Camilo","Alba", "Male", null, 45, "Colombian")));
//		primerTree.add(new Node<Person>(new Person("2","Emilia","Escobar","Female", null, 23, "EUA")));
//		primerTree.add(new Node<Person>(new Person("3","Andres","Canete","Male", null, 34, "No")));
//		
//		segTree.add(new Node<Person>(new Person("1","Camilo","Alba", "Male", null, 45, "Colombian")));
//		segTree.add(new Node<Person>(new Person("2","Emilia","Escobar","Female", null, 23, "EUA")));
//		segTree.add(new Node<Person>(new Person("3","Andres","Canete","Male", null, 34, "No")));
//		
//		terTree.add(new Node<Person>(new Person("1","Camilo","Alba", "Male", null, 45, "Colombian")));
//		terTree.add(new Node<Person>(new Person("2","Emilia","Escobar","Female", null, 23, "EUA")));
//		terTree.add(new Node<Person>(new Person("3","Andres","Canete","Male", null, 34, "No")));
//		terTree.add(new Node<Person>(new Person("2","Emilia","Bueno","Female", null, 23, "EUA")));
//		terTree.add(new Node<Person>(new Person("3","Andres","Zamar","Male", null, 34, "No")));
//				
//		primerTree.delete(new Person("1","Camilo","Alba", "Male", null, 45, "Colombian"));
//		terTree.delete(new Person("2","Emilia","Bueno","Female", null, 23, "EUA"));
//		
//		System.out.println("Terminooo.");
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/FirstWindow.fxml"));
		loader.setController(new ControllerFirstWindow());
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}

}
