package model;

import java.util.ArrayList;

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
	
	public static void delete(Person toDel) {
		codes.delete(toDel);
		names.delete(toDel);
		lastnames.delete(toDel);
		fullnames.delete(toDel);
	}

	public static ArrayList<Person> search(String sortSearch, String actTxt) {
		//Arreglar search
		ArrayList<Person> rsl=new ArrayList<>();
		if(actTxt.length()==0 || sortSearch==null) {	 
			rsl=PersonData.person; 
			return rsl;
		}
		
		if(sortSearch.equals("Nombre")) {
			rsl.addAll(names.searchCoincidences(new Person(null, actTxt ,null, null, null, 0, null)));
		}else if(sortSearch.equals("Apellido")){
			rsl.addAll(lastnames.searchCoincidences(new Person(null, null,actTxt, null, null, 0, null)));
		}else if(sortSearch.equals("Nombre y Apellido")){
			if(actTxt.indexOf(" ")==-1) {
				rsl.addAll(names.searchCoincidences(new Person(null, actTxt ,null, null, null, 0, null)));
			}else {
				String[] dt=actTxt.split(" ");
				rsl.addAll(fullnames.searchCoincidences(new Person(null, dt[0] ,dt[1], null, null, 0, null)));
			}
		}else if(sortSearch.equals("C�digo")){
			rsl.addAll(codes.searchCoincidences(new Person(actTxt, null ,null, null, null, 0, null)));
		}else {
			rsl=PersonData.person; 
		}
		return rsl;
	}
	
	public static void prueba() {
		System.out.println("Names: "+names.getWeight());
		System.out.println("lastnames: "+lastnames.getWeight());
		System.out.println("fullnames: "+fullnames.getWeight());
		System.out.println("codes: "+codes.getWeight());
	}
}
