package model;

import java.time.LocalDate;

public class Person implements Comparable<Person>{
	//Attributes
	private String code;
	private String name;
	private String lastName;
	private String sex;
	private LocalDate dateBirth;
	private int height;
	private String nationality;
	
	//Methods
	public Person(String code, String name, String lastName, String sex, LocalDate dateBirth, int height,String nationality) {
		this.code = code;
		this.name = name;
		this.lastName = lastName;
		this.sex = sex;
		this.dateBirth = dateBirth;
		this.height = height;
		this.nationality = nationality;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String isSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public LocalDate getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(LocalDate dateBirth) {
		this.dateBirth = dateBirth;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Override
	public int compareTo(Person arg0) {
		int ans=this.name.compareTo(arg0.name);
		if(ans==0) {
			ans=this.lastName.compareTo(arg0.lastName);
		}
		return ans;
	}
	
	public String toString() {
		return name+" "+lastName;
	}
}
