package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.concurrent.ThreadLocalRandom;

import java.util.UUID;
import javafx.application.Platform;
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
import model.ComparatorFullname;
import model.ComparatorLastname;
import model.ComparatorName;
import model.Database;
import model.Node;
import model.Person;
import model.PersonData;
import model.Tree;

public class ControllerFirstWindow{

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

	public ControllerFirstWindow() {

	}

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
		Thread thrd = new Thread(new thread());
		thrd.start();
	}

	@SuppressWarnings("resource")
	public void loadNames() throws IOException {
		
		long timeSec = System.currentTimeMillis();
		Random randMixDocuments = new Random();

		int amountPeople = Integer.parseInt(peopleAddTF.getText());
		
		long countFile = 0;
		boolean exitRead = false;
		
		String[] code = new String[amountPeople];
		String[] names = new String[amountPeople];
		String[] sexPeople = new String[amountPeople];
		String[] lastName = new String[amountPeople];
		String[] nationality = new String[amountPeople];
		int [] height = new int[amountPeople];
		LocalDate[] birthDay = new LocalDate[amountPeople];	
		
		BufferedReader bufferLectura = null;
		FileReader readFile = new FileReader("Data//babynames-clean.csv");
		bufferLectura = new BufferedReader(readFile);
		String line = "";
		String data = "";
		while ((line = bufferLectura.readLine()) != null) {
			data += line + ",";
		}
		String[] namesGen = data.split(",");
		
		for (int i = 0;i < amountPeople; i++) {
			int namesGenArr = 1;
			while(namesGenArr % 2 != 0) {
				namesGenArr = (int) (Math.random() * namesGen.length);
			}
			if (namesGenArr % 2 == 0) {
				names[i] = namesGen[namesGenArr];
				sexPeople[i] = namesGen[namesGenArr+1];
			}
		}
		
		Platform.runLater(()->{
			loadPeoplePGB.setProgress(0.1);
		});

		BufferedReader bufferLectura2 = null; 
		FileReader readFile2 = new
		FileReader("Data//Names_2010Census.csv"); 
		bufferLectura2 = new BufferedReader(readFile2); 
		String line2 = ""; 
		String data2 = ""; 
		long countRead = 0;
		if(amountPeople < 500) {
			countRead =  amountPeople*10;
		}
		line2 = bufferLectura2.readLine();
		while ((line2 = bufferLectura2.readLine()) != null && exitRead == false) { 
			data2 += line2 + ","; 
			countFile++;
			if(countFile == countRead) {
				exitRead = true;
			}
		}
		String[] tryes2 = data2.split(",");
		
		Platform.runLater(()->{
			loadPeoplePGB.setProgress(0.4);
		});
		
		for (int i = 0;i < amountPeople; i++) {
			int tryesArr = 1;
			while(tryesArr % 11 != 0) {
				tryesArr = (int) (Math.random() * tryes2.length);
			}
			if (tryesArr % 11 == 0) {
				lastName[i] = tryes2[tryesArr];
			}
		}

		Platform.runLater(()->{
			loadPeoplePGB.setProgress(0.65);
		});
		
		BufferedReader bufferLectura3 = null;
		FileReader readFile3 = new FileReader("Data//PAISESAMERICA.csv");
		bufferLectura3 = new BufferedReader(readFile3);
		String line3 = "";
		String data3 = "";
		line3 = bufferLectura3.readLine();
		while ((line3 = bufferLectura3.readLine()) != null) {
			data3 += line3 + ",";
		}
		String[] countryPopulation = data3.split(",");
		for(int u=0;u<countryPopulation.length;u++) {
			int check = randMixDocuments.nextInt(countryPopulation.length);
			if(check % 4 == 0 && u % 4 == 0){
				String temp = countryPopulation[u];
				countryPopulation[u] = countryPopulation[check];
				countryPopulation[check] = temp;
				countryPopulation[check+1] = countryPopulation[u+1];
				countryPopulation[check+2] = countryPopulation[u+2];
				countryPopulation[check+3] = countryPopulation[u+3];
			}
		}
		
		ArrayList<String> nationYear = new ArrayList<>();
		
		for(int i=0;i<countryPopulation.length;i++) {
			if(i % 4 == 0) {
				nationYear.add(countryPopulation[i]);
			}	
		}
		
		Platform.runLater(()->{
			loadPeoplePGB.setProgress(0.8);
		});
		
		LocalDate getDateRandomStart;
		LocalDate getDateRandomEnd = LocalDate.of(2020, 12, 31);
		LocalDate Date;
		
		double minValueCountry = 0.04;
		double maxValueCountry = 33.5;
		
		Random r = new Random();
		
		double minValueBirth = 12.0;
		double maxValueBirth = 40.29;
		
		Person person;
		
		for(int y=0;y < amountPeople;y++) {
			code[y] = UUID.randomUUID().toString();

			double percentageCountry = minValueCountry + (maxValueCountry - minValueCountry) * r.nextDouble();

			double percentageBirth = minValueBirth + (maxValueBirth - minValueBirth) * r.nextDouble();
			
			if(percentageCountry > 21.0 && percentageCountry < 50.1) {
				nationality[y] = "Estadounidense";
			}else if(percentageCountry < 21.0 && percentageCountry > 12.4) {
				nationality[y] = "Brasileño";
			}else if(percentageCountry < 12.4 && percentageCountry > 5.0) {
				nationality[y] = "Mexicano";
			}else if(percentageCountry < 5.0 && percentageCountry > 4.44) {
				nationality[y] = "Colombiano";
			}else if (percentageCountry < 4.44 && percentageCountry > 3.37) {
				nationality[y] = "Argentino";
			}else if(percentageCountry < 3.37 && percentageCountry > 3.24) {
				nationality[y] = "Canadiense";
			}else if(percentageCountry < 3.24 && percentageCountry > 2.78) {
				nationality[y] = "Peruano";
			}else if(percentageCountry < 2.78 && percentageCountry > 1.87) {
				nationality[y] = "Venezolano";
			}else if(percentageCountry < 1.87 && percentageCountry > 1.75) {
				nationality[y] = "Chileno";
			}else if(percentageCountry < 1.75 && percentageCountry > 1.72) {
				nationality[y] = "Guatemalteco";
			}else if(percentageCountry < 1.72 && percentageCountry > 1.14) {
				nationality[y] = "Ecuatoriano";
			}else if(percentageCountry < 1.14 && percentageCountry > 1.117) {
				nationality[y] = "Boliviano";
			}else if(percentageCountry < 1.117 && percentageCountry > 1.110) {
				nationality[y] = "Haitiano";
			}else if(percentageCountry < 1.110 && percentageCountry > 1.06) {
				nationality[y] = "Cubano";
			}else if(percentageCountry < 1.06 && percentageCountry > 0.97) {
				nationality[y] = "Dominicano";
			}else if(percentageCountry < 0.97 && percentageCountry > 0.69) {
				nationality[y] = "Hondureño";
			}else if(percentageCountry < 0.69 && percentageCountry > 0.64) {
				nationality[y] = "Paraguayo";
			}else if(percentageCountry < 0.64 && percentageCountry > 0.63) {
				nationality[y] = "Nicaragüense";
			}else if(percentageCountry < 0.63 && percentageCountry > 0.49) {
				nationality[y] = "Salvadoreño";
			}else if(percentageCountry < 0.49 && percentageCountry > 0.42) {
				nationality[y] = "Costarricense";
			}else if(percentageCountry < 0.42 && percentageCountry > 0.34) {
				nationality[y] = "Panameño";
			}else if(percentageCountry < 0.34 && percentageCountry > 0.29) {
				nationality[y] = "Uruguayo";
			}else if(percentageCountry < 0.29 && percentageCountry > 0.2804) {
				nationality[y] = "Jamaiquino";
			}else if(percentageCountry < 0.2804 && percentageCountry > 0.13) {
				nationality[y] = "Puertorriqueño";
			}else if(percentageCountry < 0.13 && percentageCountry > 0.077) {
				nationality[y] = "Trinitense";
			}else if(percentageCountry < 0.077 && percentageCountry > 0.057) {
				nationality[y] = "Guyanés";
			}else if(percentageCountry < 0.057 && percentageCountry > 0.0389) {
				nationality[y] = "Surinamés";
			}else if(percentageCountry < 0.0389 && percentageCountry > 0.0385) {
				nationality[y] = "Beliceño";
			}else if(percentageCountry < 0.0385 && percentageCountry > 0.0281) {
				nationality[y] = "Bahameño";
			}else if(percentageCountry < 0.0281 && percentageCountry > 0.0180001) {
				nationality[y] = "Barbadense";
			}else if(percentageCountry < 0.0180001 && percentageCountry > 0.01103) {
				nationality[y] = "Santalucense";
			}else if(percentageCountry < 0.01103 && percentageCountry > 0.01087) {
				nationality[y] = "Granadino";
			}else if(percentageCountry < 0.01087 && percentageCountry > 0.00959) {
				nationality[y] = "Sanvicentino";
			}else if(percentageCountry < 0.00959 && percentageCountry > 0.00705) {
				nationality[y] = "Antiguano";
			}else if(percentageCountry < 0.00705 && percentageCountry > 0.005214) {
				nationality[y] = "Dominiqués";
			}else if(percentageCountry < 0.005214 && percentageCountry > 0.0024) {
				nationality[y] = "Sancristobaleño";
			}
			
			if(percentageBirth < 12.94 && percentageBirth > 6.5) {
				getDateRandomEnd = LocalDate.of(1965, 1, 1);
				getDateRandomStart = LocalDate.of(1956, 1, 1);
				Date = calculateDate(getDateRandomStart, getDateRandomEnd, amountPeople);
				birthDay[y] = Date;
				int heigthPerson = 165 + r.nextInt(185 - 165)+1;
				height[y] = heigthPerson;
			}else if(percentageBirth > 12.95 && percentageBirth < 13.12) {
				getDateRandomEnd = LocalDate.of(2005, 1, 1);
				getDateRandomStart = LocalDate.of(1996, 1, 1);
				Date = calculateDate(getDateRandomStart, getDateRandomEnd, amountPeople);
				birthDay[y] = Date;
				int heigthPerson = 160 + r.nextInt(185 - 160)+1;
				height[y] = heigthPerson;
			}else if(percentageBirth > 13.13 && percentageBirth < 16.03) {
				getDateRandomEnd = LocalDate.of(1955, 1, 1);
				getDateRandomStart = LocalDate.of(1920, 1, 1);
				Date = calculateDate(getDateRandomStart, getDateRandomEnd, amountPeople);
				birthDay[y] = Date;
				int heigthPerson = 165 + r.nextInt(193 - 165)+1;
				height[y] = heigthPerson;
			}else if(percentageBirth > 16.04 && percentageBirth < 18.62) {
				getDateRandomEnd = LocalDate.of(2020, 1, 1);
				getDateRandomStart = LocalDate.of(2006, 1, 1);
				Date = calculateDate(getDateRandomStart, getDateRandomEnd, amountPeople);
				birthDay[y] = Date;
				if(Date.isAfter(LocalDate.of(2018, 1, 1))) {
					int heigthPerson = 80 + r.nextInt(90 - 80)+1;
					height[y] = heigthPerson;
				}else if(Date.isAfter(LocalDate.of(2016, 1, 1)) && Date.isBefore(LocalDate.of(2018, 1, 1))) {
					int heigthPerson = 85 + r.nextInt(105 - 85)+1;
					height[y] = heigthPerson;
				}else if(Date.isAfter(LocalDate.of(2014, 1, 1)) && Date.isBefore(LocalDate.of(2016, 1, 1))) {
					int heigthPerson = 105 + r.nextInt(125 - 105)+1;
					height[y] = heigthPerson;
				}else if(Date.isAfter(LocalDate.of(2012, 1, 1)) && Date.isBefore(LocalDate.of(2014, 1, 1))) {
					int heigthPerson = 120 + r.nextInt(140 - 120)+1;
					height[y] = heigthPerson;
				}else if(Date.isAfter(LocalDate.of(2010, 1, 1)) && Date.isBefore(LocalDate.of(2016, 1, 1))) {
					int heigthPerson = 120 + r.nextInt(155 - 120)+1;
					height[y] = heigthPerson;
				}else if(Date.isAfter(LocalDate.of(2008, 1, 1)) && Date.isBefore(LocalDate.of(2010, 1, 1))) {
					int heigthPerson = 135 + r.nextInt(165 - 135)+1;
					height[y] = heigthPerson;
				}else if(Date.isAfter(LocalDate.of(2006, 1, 1)) && Date.isBefore(LocalDate.of(2008, 1, 1))) {
					int heigthPerson = 145 + r.nextInt(171 - 145)+1;
					height[y] = heigthPerson;
				}
			}else if(percentageBirth > 18.63 && percentageBirth < 50.8) {
				getDateRandomEnd = LocalDate.of(1995, 1, 1);
				getDateRandomStart = LocalDate.of(1966, 1, 1);
				Date = calculateDate(getDateRandomStart, getDateRandomEnd, amountPeople);
				birthDay[y] = Date;
				int heigthPerson = 170 + r.nextInt(195 - 170)+1;
				height[y] = heigthPerson;
			}
			
			person = new Person(code[y],names[y],lastName[y],sexPeople[y],birthDay[y],height[y],nationality[y]);
			System.out.println(person.getName());
			PersonData.person.add(person);
			PersonData.showPerson.add(person);
			//Pq no se pone person en el add?
			Database.add(new Person(code[y],names[y],lastName[y],sexPeople[y],birthDay[y],height[y],nationality[y]));
		}
		
		Platform.runLater(()->{
			loadPeoplePGB.setProgress(1);
		});
		
		long timeEnd = System.currentTimeMillis();
		double realTime = (timeEnd - timeSec)/1000;
		String showTime = realTime + "";
		timeProgressBarTX.setText(showTime+"s");
	}
	
	public LocalDate calculateDate(LocalDate startDate,LocalDate endDate,int amountPeople) {
		long randomEpochDay = 0;
		long start = startDate.toEpochDay();
		long end = endDate.toEpochDay();
		randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();
		LocalDate date = LocalDate.ofEpochDay(randomEpochDay);
		return date;
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

}
