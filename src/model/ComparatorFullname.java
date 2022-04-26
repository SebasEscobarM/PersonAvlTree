package model;

import java.util.Comparator;

public class ComparatorFullname implements Comparator<Person>{

	@Override
	public int compare(Person o1, Person o2) {
		
		if(o1.getName().compareTo(o2.getName())==0) {
			return o1.getLastName().compareTo(o2.getLastName());
		}else {
			return o1.getName().compareTo(o2.getName());
		}
	}

}
