
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
