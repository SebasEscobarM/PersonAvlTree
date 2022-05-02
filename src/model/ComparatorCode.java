package model;

import java.util.Comparator;

public class ComparatorCode implements Comparator<Person>{

	@Override
	public int compare(Person o1, Person o2) {
		return o1.getCode().compareTo(o2.getCode());
	}

}
