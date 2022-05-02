package model;

import java.util.Comparator;

public class SearchName implements Comparator<Person>{

	@Override
	public int compare(Person arg0, Person arg1) {
		int x=arg0.getName().length();
		int y=arg1.getName().length();
		if(x<y) {
			return arg0.getName().compareTo(arg1.getName());
		}else {
			String name1=arg0.getName().substring(0, y);
			return name1.compareTo(arg1.getName());
		}
	}

}
