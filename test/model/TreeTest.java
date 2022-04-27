package model;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class TreeTest {
	
	private static Tree<Person> tree;
	
	public void setupStage1() {
		tree=new Tree<>(new ComparatorName());
	}
	
	@Test
	void testAddBalance() {
		//Se agregaran tres personas primero la raiz y las dos siguientes a toda la derecha,
		//debe rotarse adecuadamente y dejar a Emilia como root , Camilo si hijo izquierdo y 
		//Ondres como hijo derecho.
		setupStage1();
		tree.add(new Node<Person>(new Person("1","Camilo","Alba", "Male", LocalDate.now(), 67, "EEUU")));
		tree.add(new Node<Person>(new Person("2","Emilia","Escobar", "Female", LocalDate.now(), 56, "Argentina")));
		tree.add(new Node<Person>(new Person("3","Ondres","Canete", "Male", LocalDate.now(), 59, "Chile")));
		
		assertEquals(tree.getRoot().getItem().compareTo(new Person("2","Emilia","Escobar", "Female", LocalDate.now(), 56, "Argentina")), 0);
		assertEquals(tree.getRoot().getLeft().getItem().compareTo(new Person("1","Camilo","Alba", "Male", LocalDate.now(), 67, "EEUU")), 0);
		assertEquals(tree.getRoot().getRight().getItem().compareTo(new Person("3","Ondres","Canete", "Male", LocalDate.now(), 59, "Chile")), 0);
	}
	
	@Test
	void testAddBalance2() {
		setupStage1();
		tree.add(new Node<Person>(new Person("2","Emilia","Escobar", "Female", LocalDate.now(), 56, "Argentina")));
		//Se agrega como hijo Izquierdo del anterior
		tree.add(new Node<Person>(new Person("1","Camilo","Alba", "Male", LocalDate.now(), 67, "EEUU")));
		//Se agreda como hijo derecho del anterior y se balancea con doble rotacion der
		tree.add(new Node<Person>(new Person("3","Diana","Lopez", "Female", LocalDate.now(), 47, "Peru")));
		
		
		assertEquals(tree.getRoot().getItem().compareTo(new Person("3","Diana","Lopez", "Female", LocalDate.now(), 47, "Peru")), 0);
		assertEquals(tree.getRoot().getLeft().getItem().compareTo(new Person("1","Camilo","Alba", "Male", LocalDate.now(), 67, "EEUU")), 0);
		assertEquals(tree.getRoot().getRight().getItem().compareTo(new Person("2","Emilia","Escobar", "Female", LocalDate.now(), 56, "Argentina")), 0);
	}
	
	@Test
	void testDeleteRoot() {
		setupStage1();
		
		tree.add(new Node<Person>(new Person("2","Camilo","Alba", "Male", LocalDate.now(), 67, "EEUU")));
		tree.add(new Node<Person>(new Person("4","Omar","Lopez", "Male", LocalDate.now(), 63, "Panama")));
		tree.add(new Node<Person>(new Person("3","Andres","Canete", "Male", LocalDate.now(), 59, "Chile")));
		tree.add(new Node<Person>(new Person("1","Emilia","Escobar", "Female", LocalDate.now(), 56, "Argentina")));
		tree.add(new Node<Person>(new Person("6","Barbara","Marin", "Female", LocalDate.now(), 53, "Colombia")));
		tree.delete(new Person("2","Camilo","Alba", "Male", LocalDate.now(), 67, "EEUU"));
		
		
		assertEquals(tree.getRoot().getItem().compareTo(new Person("1","Emilia","Escobar", "Female", LocalDate.now(), 56, "Argentina")), 0);
		assertEquals(tree.getRoot().getLeft().getItem().compareTo(new Person("3","Andres","Canete", "Male", LocalDate.now(), 59, "Chile")), 0);
		assertEquals(tree.getRoot().getLeft().getRight().getItem().compareTo(new Person("6","Barbara","Marin", "Female", LocalDate.now(), 53, "Colombia")), 0);
		assertEquals(tree.getRoot().getRight().getItem().compareTo(new Person("4","Omar","Lopez", "Male", LocalDate.now(), 63, "Panama")), 0);
	}
	
	@Test
	void testSearch() {
		setupStage1();
		
		tree.add(new Node<Person>(new Person("2","Camilo","Alba", "Male", LocalDate.now(), 67, "EEUU")));
		tree.add(new Node<Person>(new Person("4","Omar","Lopez", "Male", LocalDate.now(), 63, "Panama")));
		tree.add(new Node<Person>(new Person("3","Andres","Canete", "Male", LocalDate.now(), 59, "Chile")));
		tree.add(new Node<Person>(new Person("1","Emilia","Escobar", "Female", LocalDate.now(), 56, "Argentina")));
		tree.add(new Node<Person>(new Person("6","Barbara","Marin", "Female", LocalDate.now(), 53, "Colombia")));
		Node<Person> nd=tree.search(new Person("6","Barbara","Marin", "Female", LocalDate.now(), 53, "Colombia"));
		
		assertEquals(nd.getItem().compareTo(new Person("6","Barbara","Marin", "Female", LocalDate.now(), 53, "Colombia")), 0);
	}

}
