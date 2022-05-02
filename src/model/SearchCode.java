package model;

import java.util.Comparator;

public class SearchCode implements Comparator<Person>{

	@Override
	public int compare(Person o1, Person o2) {
		int x=o1.getCode().length();
		int y=o2.getCode().length();
		if(x<y) {
			return o1.getCode().compareTo(o2.getCode());
		}else {
			String code1=o1.getCode().substring(0, y);
			return code1.compareTo(o2.getCode());
		}
	}
	
}
