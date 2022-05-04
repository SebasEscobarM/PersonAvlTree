package model;

import java.util.Comparator;

public class SearchLastname implements Comparator<Person>{

	@Override
	public int compare(Person arg0, Person arg1) {
		int x=arg0.getLastName().length();
		int y=arg1.getLastName().length();
		if(x<y) {
			return arg0.getLastName().compareToIgnoreCase(arg1.getLastName());
		}else {
			String lstname1=arg0.getLastName().substring(0, y);
			return lstname1.compareToIgnoreCase(arg1.getLastName());
		}
	}

}
