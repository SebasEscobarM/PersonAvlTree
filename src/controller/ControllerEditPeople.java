package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import model.Person;

public class ControllerEditPeople implements Initializable{
	
	@FXML
    private Button backBTM;

    @FXML
    private TextField dateBirthActualPersonTF;

    @FXML
    private TextField heightActualPersonTF;

    @FXML
    private TextField lastNameActualPersonTF;

    @FXML
    private TextField nameActualPersonTF;

    @FXML
    private TextField nationalityActualPersonTF;

    @FXML
    private Button saveChangesBTN;

    @FXML
    private TextField sexActualPersonTF;
    
    private Person toEdit;

    public ControllerEditPeople(Person toEdit) {
    	this.toEdit=toEdit;
    }
    @FXML
    void backAct(ActionEvent event) throws IOException {
    	Stage stage1 = (Stage) this.backBTM.getScene().getWindow();
        stage1.close();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/SearchPeople.fxml"));
		loader.setController(new ControllerSearchPeople());
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void saveChanges(ActionEvent event) throws IOException {
    	if(nameActualPersonTF.getText() != "" || lastNameActualPersonTF.getText() != "" || sexActualPersonTF.getText() != "" || dateBirthActualPersonTF.getText() != "" || heightActualPersonTF.getText() != "" || nationalityActualPersonTF.getText() != "") {
    		//Se guardan los cambios como lo vayas a hacer
    		
    		Stage stage1 = (Stage) this.backBTM.getScene().getWindow();
            stage1.close();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/SearchPeople.fxml"));
    		loader.setController(new ControllerSearchPeople());
    		Parent parent = loader.load();
    		Scene scene = new Scene(parent);
    		Stage stage = new Stage();
    		stage.setScene(scene);
    		stage.show();
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nameActualPersonTF.setText(toEdit.getName());
		lastNameActualPersonTF.setText(toEdit.getLastName());
		heightActualPersonTF.setText(toEdit.getHeight()+"");
		nationalityActualPersonTF.setText(toEdit.getNationality());
		sexActualPersonTF.setText(toEdit.isSex());
		dateBirthActualPersonTF.setText(toEdit.getDateBirth().toString());
	}
	
}
