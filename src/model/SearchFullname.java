package model;

import java.util.Comparator;

public class SearchFullname implements Comparator<Person>{

	@Override
	public int compare(Person arg0, Person arg1) {
		int x=arg0.getName().length();
		int y=arg1.getName().length();
		int ans=0;
		if(x<y) {
			ans=arg0.getName().compareTo(arg1.getName());
		}else {
			String name1=arg0.getName().substring(0, y);
			ans=name1.compareTo(arg1.getName());
		}
		if(ans==0) {
			x=arg0.getLastName().length();
			y=arg1.getLastName().length();
			if(x<y) {
				return arg0.getLastName().compareTo(arg1.getLastName());
			}else {
				String lstname1=arg0.getLastName().substring(0, y);
				return lstname1.compareTo(arg1.getLastName());
			}
		}else {
			return ans;
		}
	}

}
