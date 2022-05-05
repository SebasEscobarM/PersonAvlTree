package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.Main;
import model.Database;
import model.Person;
import model.PersonData;

public class ControllerEditPeople implements Initializable{
	
	@FXML
    private Button backBTM;
	
	@FXML
    private Text codePerson;

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
    	if(nameActualPersonTF.getText() != toEdit.getName() || lastNameActualPersonTF.getText() != toEdit.getLastName() || sexActualPersonTF.getText() != toEdit.isSex() || dateBirthActualPersonTF.getText() != "" || heightActualPersonTF.getText() != (toEdit.getHeight()+"") || nationalityActualPersonTF.getText() != toEdit.getNationality()) {
    		Person nwP=new Person(toEdit.getCode(), nameActualPersonTF.getText(), lastNameActualPersonTF.getText(), sexActualPersonTF.getText(), LocalDate.parse(dateBirthActualPersonTF.getText()), Integer.parseInt(heightActualPersonTF.getText()), nationalityActualPersonTF.getText());
    		PersonData.person.remove(toEdit);
    		PersonData.person.add(nwP);
    		Database.delete(toEdit);
    		Database.add(nwP);
    		
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
		codePerson.setText(toEdit.getCode()+".");
		nameActualPersonTF.setText(toEdit.getName());
    	lastNameActualPersonTF.setText(toEdit.getLastName());
    	heightActualPersonTF.setText(toEdit.getHeight()+"");
    	nationalityActualPersonTF.setText(toEdit.getNationality());
    	dateBirthActualPersonTF.setText(toEdit.getDateBirth().toString());
    	sexActualPersonTF.setText(toEdit.isSex());
	}
	
}
