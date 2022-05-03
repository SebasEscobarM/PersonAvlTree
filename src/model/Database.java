package model;

import java.util.ArrayList;

import javafx.collections.ObservableList;

public class Database {
	
	public static Tree<Person> codes=new Tree<>(new ComparatorCode(), new SearchCode());
	public static Tree<Person> names=new Tree<>(new ComparatorName(), new SearchName());
	public static Tree<Person> lastnames=new Tree<>(new ComparatorLastname(), new SearchLastname());
	public static Tree<Person> fullnames=new Tree<>(new ComparatorFullname(), new SearchFullname());
	
	public Database() {
		
	}
	
	public static void add(Person toAdd) {
		codes.add(new Node<>(toAdd));
		names.add(new Node<>(toAdd));
		lastnames.add(new Node<>(toAdd));
		fullnames.add(new Node<>(toAdd));
	}

	public static ArrayList<Person> search(String sortSearch, String actTxt) {
		ArrayList<Person> rsl=new ArrayList<>();
		if(actTxt.length()==0 || sortSearch==null) {	 
			 return rsl;
		}
		
		if(sortSearch.equals("Nombre")) {
			return names.searchCoincidences(new Person(null, actTxt ,null, null, null, 0, null));
		}else if(sortSearch.equals("Apellido")){
			return lastnames.searchCoincidences(new Person(null, null,actTxt, null, null, 0, null));
		}else if(sortSearch.equals("Nombre y Apellido")){
			String[] dt=actTxt.split(" ");
			return fullnames.searchCoincidences(new Person(null, dt[0] ,dt[1], null, null, 0, null));
		}else if(sortSearch.equals("Código")){
			return codes.searchCoincidences(new Person(actTxt, null ,null, null, null, 0, null));
		}else {
			return rsl;
		}
	}
}
