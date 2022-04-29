package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.Main;

public class ControllerFirstWindow implements Initializable {

	@FXML
	private Button generatePeopleBTN;

	@FXML
	private Button addPeopleBTN;

	@FXML
	private ProgressBar loadPeoplePGB;

	@FXML
	private Button offAppBTN;

	@FXML
	private TextField peopleAddTF;

	@FXML
	private Button searchPeopleBTN;

	@FXML
	private Text timeProgressBarTX;

	@FXML
	void AddPeople(ActionEvent event) throws IOException {
		FXMLLoader loader2 = new FXMLLoader(Main.class.getResource("../ui/AddPeople.fxml"));
		loader2.setController(new ControllerAddPeople());
		Parent parent2 = loader2.load();
		Scene scene2 = new Scene(parent2);
		Stage stage2 = new Stage();
		stage2.setScene(scene2);
		stage2.show();
		Stage stage1 = (Stage) this.addPeopleBTN.getScene().getWindow();
		stage1.close();
	}

	@FXML
	void offApp(ActionEvent event) {

	}

	@FXML
	void searchPeople(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/SearchPeople.fxml"));
		loader.setController(new ControllerSearchPeople());
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		Stage stage1 = (Stage) this.searchPeopleBTN.getScene().getWindow();
		stage1.close();
	}

	@FXML
	void generatePeople(ActionEvent event) throws IOException {
		thread thrd = new thread();
		thrd.run();
	}

	@SuppressWarnings("resource")
	public void loadNames() throws IOException {
		Random randMixDocuments = new Random();

		int amountPeople = Integer.parseInt(peopleAddTF.getText());

		String[] names = new String[amountPeople];
		String[] sexPeople = new String[amountPeople];
		String[] lastName = new String[amountPeople];
		BufferedReader bufferLectura = null;
		FileReader readFile = new FileReader("Data//babynames-clean.csv");
		bufferLectura = new BufferedReader(readFile);
		String line = "";
		String data = "";
		while ((line = bufferLectura.readLine()) != null) {
			data += line + ",";
		}
		String[] namesGen = data.split(",");
		for(int u=0;u<namesGen.length;u++) {
			int check = randMixDocuments.nextInt(namesGen.length);
			if(check % 2 == 0 && u % 2 == 0){
				String temp = namesGen[u];
				namesGen[u] = namesGen[check];
				namesGen[check] = temp;
				namesGen[check+1] = namesGen[u+1];
			}
		}
		int times = 0;
		boolean exitFor = false;

		if (amountPeople > namesGen.length) {
			amountPeople = namesGen.length;
		}

		for (int i = 0; i < namesGen.length && exitFor == false; i++) {
			if (i % 2 == 0) {
				System.out.println(namesGen[i]);
				names[times] = namesGen[i];
				times++;
				if (times == amountPeople) {
					exitFor = true;
				}
			}
		}
		
		times = 0;
		exitFor = false;
		
		for (int i = 0; i < namesGen.length && exitFor == false; i++) {
			if (i % 2 == 1) {
				System.out.println(namesGen[i]);
				sexPeople[times] = namesGen[i];
				times++;
				if (times == amountPeople) {
					exitFor = true;
				}
			}
		}

		BufferedReader bufferLectura2 = null; 
		FileReader readFile2 = new
		FileReader("Data//Names_2010Census.csv"); 
		bufferLectura2 = new BufferedReader(readFile2); 
		String line2 = ""; 
		String data2 = ""; 
		while ((line2 = bufferLectura2.readLine()) != null) { 
			data2 += line2 + ","; 
		}
		String[] tryes2 = data2.split(",");
		for(int u=0;u<tryes2.length;u++) {
			int check = randMixDocuments.nextInt(tryes2.length);
			if(check % 2 == 0 && u % 2 == 0){
				String temp = namesGen[u];
				namesGen[u] = namesGen[check];
				namesGen[check] = temp;
				namesGen[check+1] = namesGen[u+1];
			}
		}
		
		if (amountPeople > tryes2.length) { 
			amountPeople = tryes2.length; 
		}
		
		times = 0;
		exitFor = false;
		 
		for (int i=11;i<tryes2.length && exitFor == false;i+=11) { 
			System.out.println("entre 3");
			System.out.println(tryes2[i]); 
			lastName[times] = tryes2[i]; 
			times++;
			if (times == amountPeople) {
				exitFor = true;
			}
		}
		
		

	}

	class thread implements Runnable {
		@Override
		public void run() {
			try {
				loadNames();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
