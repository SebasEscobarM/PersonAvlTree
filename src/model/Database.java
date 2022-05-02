package model;

public class Database {
	
	public static Tree<Person> codes=new Tree<>(new ComparatorCode(), new SearchCode());
	public static Tree<Person> names=new Tree<>(new ComparatorName(), new SearchName());
	public static Tree<Person> lastnames=new Tree<>(new ComparatorLastname(), new SearchLastname());
	public static Tree<Person> fullnames=new Tree<>(new ComparatorFullname(), new SearchFullname());
	
	public Database() {
		
	}
	
	public void add(Person toAdd) {
		codes.add(new Node<>(toAdd));
		names.add(new Node<>(toAdd));
		lastnames.add(new Node<>(toAdd));
		fullnames.add(new Node<>(toAdd));
	}
}
