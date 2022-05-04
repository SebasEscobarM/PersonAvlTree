package controller;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;
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
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import main.Main;
import model.Database;
import model.Person;
import model.PersonData;

public class ControllerSearchPeople implements Initializable{
	
	@FXML
    private Button backBTM;

    @FXML
    private Button deletePerson;
    
    @FXML
    private Button editPerson;
    
    @FXML
    private ComboBox<String> sortByCMB;
    
    @FXML
    private ListView<Person> listShowPersonLV;

    @FXML
    private TextField wantedPersonTF;
    
    private Person stClicked;
    
    private ObservableList<Person> toShow;
    
    @FXML
    void backAct(ActionEvent event) throws IOException {
    	Stage stage1 = (Stage) this.backBTM.getScene().getWindow();
        stage1.close();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/FirstWindow.fxml"));
		loader.setController(new ControllerFirstWindow());
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void deletePerson(ActionEvent event) {
		deletePerson.setStyle("-fx-background-color: #e80202");
		deletePerson.setStyle("-fx-text-fill: white");
    	if(stClicked != null) {
    		Database.delete(stClicked);
			PersonData.person.remove(stClicked);
			PersonData.showPerson.remove(stClicked);
			toShow.remove(stClicked);
    	}
    }
    
    @FXML
    void editPerson(ActionEvent event) throws IOException {
		editPerson.setStyle("-fx-background-color: #e1d904");
		editPerson.setStyle("-fx-text-fill: white");
    	if(stClicked != null) {
    		
    		Stage stage1 = (Stage) this.editPerson.getScene().getWindow();
            stage1.close();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/editPeople.fxml"));
    		loader.setController(new ControllerEditPeople(stClicked));
    		Parent parent = loader.load();
    		Scene scene = new Scene(parent);
    		Stage stage = new Stage();
    		stage.setScene(scene);
    		stage.show();
    	}
    }
    
    
    public void searchList() {
    	String actTxt = wantedPersonTF.getText();
    	String sortSearch = sortByCMB.getSelectionModel().getSelectedItem();
    	System.out.println(actTxt+" || "+sortSearch);
    	ArrayList<Person> a=new ArrayList<>();
    	a.addAll(Database.search(sortSearch, actTxt));
    	toShow=FXCollections.observableArrayList(a);
    	listShowPersonLV.setItems(toShow);
    	
//    	if(a.size()!=0) {
//    		for(Person p:a) {
//    			if(p!=null)
//    				System.out.println(p.toString());
//        	}
//    	}
    	
    	//toShow=FXCollections.observableArrayList();
    	//Buscar
//    	if(actTxt.equals("") || sortSearch==null) {
//    		listShowPersonLV.setItems(PersonData.showPerson);
//    	}else {
//    		ObservableList<Person> obs=
//    		PersonData.showPerson.setAll(Database.search(sortSearch, actTxt));
//        	listShowPersonLV.setItems(PersonData.showPerson);
//    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		wantedPersonTF.textProperty().addListener(new ChangeListener<String>(){
			@Override
			public void changed(ObservableValue<? extends String> observableValue, String s, String s2) {
				searchList();
			}
		});
		ObservableList<String> list = FXCollections.observableArrayList();
		list.add("Nombre");
		list.add("Apellido");
		list.add("Nombre y Apellido");
		list.add("Código");
		
		sortByCMB.setItems(list);
		
		toShow=FXCollections.observableArrayList(PersonData.person);
		listShowPersonLV.setItems(toShow);
		
		listShowPersonLV.setOnMouseClicked(event ->{
			stClicked = listShowPersonLV.getSelectionModel().getSelectedItem();
		});
	}	

}



