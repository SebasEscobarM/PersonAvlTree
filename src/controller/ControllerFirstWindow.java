package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

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

		String[] names = new String[amountPeople];
		String[] sexPeople = new String[amountPeople];
		String[] lastName = new String[amountPeople];
		String[] nationality = new String[amountPeople];
		int [] heigh = new int[amountPeople];
		String[] birthDay = new String[amountPeople];	
		
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
		
		
		/* La única idea que tengo es que el contador alcance la cantidad de personas, porque está el caso en el
		 * que la cantidad ingresada es mayor a la del arreglo, entonces lo que hacía era reemplazar el valor de la cantidad de personas a generar
		 * por la del arreglo, por lo que siempre tenía un límite. Lo que busco es mirar si puedo hacer un for hasta el tamaño del arreglo y luego 
		 * volver a hacer ese for sin que se reemplacen los valores ya ingresados, sino s se puede toca cambiar todo los métodos de generar
		if(amountPeople > namesGen.length) {
			do {
				for (int i = 0; i < namesGen.length; i++) {
					if (i % 2 == 0) {
						//System.out.println(namesGen[i]);
						names[times] = namesGen[i];
						times++;
						System.out.println(times);
					}
				}
			}while(times != amountPeople);
		}else {
			for (int i = 0;i<namesGen.length && exitFor == false; i++) {
				if (i % 2 == 0) {
					//System.out.println(namesGen[i]);
					names[times] = namesGen[i];
					times++;
					if (times == amountPeople) {
						exitFor = true;
					}
				}
			}
		}*/

		for (int i = 0;i<namesGen.length && exitFor == false; i++) {
			if (i % 2 == 0) {
				//System.out.println(namesGen[i]);
				names[times] = namesGen[i];
				times++;
				if (times == amountPeople) {
					exitFor = true;
				}
			}
		}
		
		Platform.runLater(()->{
			loadPeoplePGB.setProgress(0.1);
		});
		
		System.out.println("---------------------------------------");
		
		times = 0;
		exitFor = false;
		
		/*
		if(amountPeople > namesGen.length) {
			do {
				for (int i = 0;i<namesGen.length; i++) {
					if (i % 2 == 1) {
						//System.out.println(namesGen[i]);
						sexPeople[times] = namesGen[i];
						times++;
						System.out.println(times);
					}
				}
			}while (times != amountPeople);
		}else{
			for (int i = 0;i<namesGen.length && exitFor == false; i++) {
				if (i % 2 == 1) {
					//System.out.println(namesGen[i]);
					sexPeople[times] = namesGen[i];
					times++;
					if (times == amountPeople) {
						exitFor = true;
					}
				}
			}
		}*/
		
		for (int i = 0;i<namesGen.length && exitFor == false; i++) {
			if (i % 2 == 1) {
				//System.out.println(namesGen[i]);
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
		long countRead = 0;
		if(amountPeople > 3100) {
			countRead = 30888;
		}else if(amountPeople < 500) {
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
		for(int u=0;u<tryes2.length;u++) {
			int check = randMixDocuments.nextInt(tryes2.length);
			if(check % 11 == 0 && u % 11 == 0){
				String temp = tryes2[u];
				tryes2[u] = tryes2[check];
				tryes2[check] = temp;
			}
		}
		
		Platform.runLater(()->{
			loadPeoplePGB.setProgress(0.4);
		});
		
		times = 0;
		exitFor = false;
		 
		for (int i=0;exitFor == false;i+=11) { 
			System.out.println(tryes2[i]);
			lastName[times] = tryes2[i]; 
			times++;
			if (times == amountPeople) {
				exitFor = true;
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
		long Date;
		
		times = 0;
		exitFor = false;
		
		double minValueCountry = 0.04;
		double maxValueCountry = 33.5;
		
		Random r = new Random();
		
		double minValueBirth = 12.0;
		double maxValueBirth = 40.29;
		
		for(int y=0;exitFor == false;y++) {
			//double percentageCountry = Math.random() * minValueCountry + (maxValueCountry - minValueCountry);
			double percentageCountry = r.nextDouble() * maxValueCountry + 21;
			System.out.println(percentageCountry);
			//double percentageBirth = Math.random() * minValueBirth + (maxValueBirth - minValueBirth);
			double percentageBirth = r.nextDouble() * maxValueBirth + 8;
			System.out.println(percentageBirth);
			
			if(percentageCountry > 21.0 && percentageCountry < 50.1) {
				System.out.println("USA");
			}else if(percentageCountry < 21.0 && percentageCountry > 12.4) {
				System.out.println("Brasil");
			}else if(percentageCountry < 12.4 && percentageCountry > 5.0) {
				System.out.println("Mexico");
			}else if(percentageCountry < 5.0 && percentageCountry > 4.44) {
				System.out.println("Colombia");
			}else if (percentageCountry < 4.44 && percentageCountry > 3.37) {
				System.out.println("Argentina");
			}else if(percentageCountry < 3.37 && percentageCountry > 3.24) {
				System.out.println("Canadá");
			}else if(percentageCountry < 3.24 && percentageCountry > 2.78) {
				System.out.println("Perú");
			}else if(percentageCountry < 2.78 && percentageCountry > 1.87) {
				System.out.println("Venezuela");
			}else if(percentageCountry < 1.87 && percentageCountry > 1.75) {
				System.out.println("Chile");
			}else if(percentageCountry < 1.75 && percentageCountry > 1.72) {
				System.out.println("Guatemala");
			}else if(percentageCountry < 1.72 && percentageCountry > 1.14) {
				System.out.println("Ecuador");
			}else if(percentageCountry < 1.14 && percentageCountry > 1.117) {
				System.out.println("Bolivia");
			}else if(percentageCountry < 1.117 && percentageCountry > 1.110) {
				System.out.println("Haití");
			}else if(percentageCountry < 1.110 && percentageCountry > 1.06) {
				System.out.println("Cuba");
			}else if(percentageCountry < 1.06 && percentageCountry > 0.97) {
				System.out.println("República Dominicana");
			}else if(percentageCountry < 0.97 && percentageCountry > 0.69) {
				System.out.println("Honduras");
			}else if(percentageCountry < 0.69 && percentageCountry > 0.64) {
				System.out.println("Paraguay");
			}else if(percentageCountry < 0.64 && percentageCountry > 0.63) {
				System.out.println("Nicaragua");
			}else if(percentageCountry < 0.63 && percentageCountry > 0.49) {
				System.out.println("El salvador");
			}else if(percentageCountry < 0.49 && percentageCountry > 0.42) {
				System.out.println("Costa Rica");
			}else if(percentageCountry < 0.42 && percentageCountry > 0.34) {
				System.out.println("Panamá");
			}else if(percentageCountry < 0.34 && percentageCountry > 0.29) {
				System.out.println("Uruguay");
			}else if(percentageCountry < 0.29 && percentageCountry > 0.2804) {
				System.out.println("Jamaica");
			}else if(percentageCountry < 0.2804 && percentageCountry > 0.13) {
				System.out.println("Puerto Rica");
			}else if(percentageCountry < 0.13 && percentageCountry > 0.077) {
				System.out.println("Trinidad y Tobago");
			}else if(percentageCountry < 0.077 && percentageCountry > 0.057) {
				System.out.println("Guyana");
			}else if(percentageCountry < 0.057 && percentageCountry > 0.0389) {
				System.out.println("Surinam");
			}else if(percentageCountry < 0.0389 && percentageCountry > 0.0385) {
				System.out.println("Belice");
			}else if(percentageCountry < 0.0385 && percentageCountry > 0.0281) {
				System.out.println("Las Bahamas");
			}else if(percentageCountry < 0.0281 && percentageCountry > 0.0180001) {
				System.out.println("Barbados");
			}else if(percentageCountry < 0.0180001 && percentageCountry > 0.01103) {
				System.out.println("Santa Lucia");
			}else if(percentageCountry < 0.01103 && percentageCountry > 0.01087) {
				System.out.println("Granada");
			}else if(percentageCountry < 0.01087 && percentageCountry > 0.00959) {
				System.out.println("San Vicente y las Granadinas");
			}else if(percentageCountry < 0.00959 && percentageCountry > 0.00705) {
				System.out.println("Antigua Barbuda");
			}else if(percentageCountry < 0.00705 && percentageCountry > 0.005214) {
				System.out.println("Dominica");
			}else if(percentageCountry < 0.005214 && percentageCountry > 0.0024) {
				System.out.println("San Cristóbal y Nieves");
			}
			
			
			if(percentageBirth < 12.94 && percentageBirth > 6.5) {
				getDateRandomEnd = LocalDate.of(1965, 1, 1);
				getDateRandomStart = LocalDate.of(1956, 1, 1);
				Date = calculateDate(getDateRandomStart, getDateRandomEnd, amountPeople);
			}else if(percentageBirth > 12.95 && percentageBirth < 13.12) {
				getDateRandomEnd = LocalDate.of(2005, 1, 1);
				getDateRandomStart = LocalDate.of(1996, 1, 1);
				Date = calculateDate(getDateRandomStart, getDateRandomEnd, amountPeople);
			}else if(percentageBirth > 13.13 && percentageBirth < 16.03) {
				getDateRandomEnd = LocalDate.of(1955, 1, 1);
				getDateRandomStart = LocalDate.of(1920, 1, 1);
				Date = calculateDate(getDateRandomStart, getDateRandomEnd, amountPeople);
			}else if(percentageBirth > 16.04 && percentageBirth < 18.62) {
				getDateRandomEnd = LocalDate.of(2020, 1, 1);
				getDateRandomStart = LocalDate.of(2006, 1, 1);
				Date = calculateDate(getDateRandomStart, getDateRandomEnd, amountPeople);
			}else if(percentageBirth > 18.63 && percentageBirth < 50.8) {
				getDateRandomEnd = LocalDate.of(1995, 1, 1);
				getDateRandomStart = LocalDate.of(1966, 1, 1);
				Date = calculateDate(getDateRandomStart, getDateRandomEnd, amountPeople);
			}
			
			times++;
			if(times == amountPeople) {
				exitFor = true;
			}
		}
		
		Platform.runLater(()->{
			loadPeoplePGB.setProgress(1);
		});
		
		long timeEnd = System.currentTimeMillis();
		double realTime = (timeEnd - timeSec)/1000;
		String showTime = realTime + "";
		timeProgressBarTX.setText(showTime+"s");
	}
	
	public long calculateDate(LocalDate startDate,LocalDate endDate,int amountPeople) {
		long randomEpochDay = 0;
		long start = startDate.toEpochDay();
		long end = endDate.toEpochDay();
		randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();
		System.out.println(LocalDate.ofEpochDay(randomEpochDay));
		return randomEpochDay;
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
