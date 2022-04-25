package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.UUID;

public class ControllerAddPeople implements Initializable{
	
	@FXML
    private Button addPersonBTN;

    @FXML
    private Button backBTM;

    @FXML
    private DatePicker dateBirthPersonDTP;

    @FXML
    private ComboBox<String> genrePersonCMB;

    @FXML
    private TextField heightPersonTF;

    @FXML
    private TextField lastNamePersonTF;

    @FXML
    private TextField namePersonTF;

    @FXML
    private TextField nationalityPersonTF;

    @FXML
    void AddPerson(ActionEvent event) {
    	String codePerson = UUID.randomUUID().toString();
    	String namePerson = namePersonTF.getText();
    	String lastNamePerson = lastNamePersonTF.getText();
    	String sexPerson = genrePersonCMB.getSelectionModel().getSelectedItem();
    	LocalDate dateBirthPerson = dateBirthPersonDTP.getValue();
    	
    	int heightPerson = Integer.parseInt(heightPersonTF.getText());
    	String nationalityPerson = nationalityPersonTF.getText();
    	
    }

    @FXML
    void backAct(ActionEvent event) {
    	Stage stage1 = (Stage) this.backBTM.getScene().getWindow();
        stage1.close();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> list = FXCollections.observableArrayList();
		list.add("Masculino");
		list.add("Femenino");
		genrePersonCMB.setItems(list);
	}
	
}
