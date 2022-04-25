package controller;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import main.Main;
import model.Person;
import model.PersonData;

public class ControllerSearchPeople implements Initializable{
	
	@FXML
    private TableColumn<Person, String> codeCOL;

    @FXML
    private TableColumn<Person, LocalDate> dateBirthCOL;

    @FXML
    private Button deletePerson;

    @FXML
    private TableColumn<Person, Integer> heightCOL;

    @FXML
    private TableColumn<Person, String> lastNameCOL;

    @FXML
    private TableColumn<Person, String> nameCOL;

    @FXML
    private TableColumn<Person, String> nationalityCOL;

    @FXML
    private TableView<Person> personTV;

    @FXML
    private TableColumn<Person, String> sexCOL;
    
    @FXML
    private ComboBox<String> sortByCMB;

    @FXML
    private TextField wantedPersonTF;
    
    Person stClicked;

    @FXML
    void addPerson(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/AddPeople.fxml"));
		loader.setController(new ControllerAddPeople());
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void deletePerson(ActionEvent event) {
    	if(stClicked != null) {
    		deletePerson.setStyle("-fx-background-color: #e80202; ");
    	}
    	PersonData.person.remove(stClicked);
    	PersonData.showPerson.remove(stClicked);
    }

    @FXML
    void editPerson(ActionEvent event) {

    }
    
    @FXML
    public void getKeyPressed(KeyEvent event) {
    	String keyPressedTF = wantedPersonTF.getText();
    	String sortSearch = sortByCMB.getSelectionModel().getSelectedItem();
    	
    }
    
    @FXML
    void exitApp(ActionEvent event) {
    	Platform.exit();
    	System.exit(0);
    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> list = FXCollections.observableArrayList();
		list.add("Nombre");
		list.add("Apellido");
		list.add("Nombre y Apellido");
		list.add("Código");
		sortByCMB.setItems(list);
		
		nameCOL.setCellValueFactory(new PropertyValueFactory<>("code"));
		nameCOL.setCellValueFactory(new PropertyValueFactory<>("name"));
		nameCOL.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		nameCOL.setCellValueFactory(new PropertyValueFactory<>("sex"));
		nameCOL.setCellValueFactory(new PropertyValueFactory<>("dateBirth"));
		nameCOL.setCellValueFactory(new PropertyValueFactory<>("height"));
		nameCOL.setCellValueFactory(new PropertyValueFactory<>("nationality"));
		
		personTV.setItems(PersonData.showPerson);
		
		personTV.setOnMouseClicked(event ->{
			stClicked = personTV.getSelectionModel().getSelectedItem();
		});

		
	}

}

