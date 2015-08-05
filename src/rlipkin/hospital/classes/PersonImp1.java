package rlipkin.hospital.classes;

import java.util.Date;

import rlipkin.hospital.misc.Functions;

public abstract class PersonImp1 implements Person {
	private String fName;
	private String lName;
	private String SSN;
	private Date birthDate;
	private int age;
	
	public PersonImp1(String name, String ssn, String bDay){
		fName = name.split(" ")[0];
		lName = name.split(" ")[1];
		SSN = ssn;
		birthDate = Functions.stringToDate(bDay, "yyyy-MM-dd");
		age = Functions.calculateAge(birthDate);
	}
	

	public String toString() {
		return lName + ", " + fName;
	}
	
	public String getfName() {
		return fName;
	}
	public String getlName() {
		return lName;
	}
	public String getSSN() {
		return SSN;
	}
	public Date getDOB() {
		return birthDate;
	}
	public int getAge() {
		return age;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((SSN == null) ? 0 : SSN.hashCode());
		result = prime * result + age;
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((fName == null) ? 0 : fName.hashCode());
		result = prime * result + ((lName == null) ? 0 : lName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonImp1 other = (PersonImp1) obj;
		if (SSN == null) {
			if (other.SSN != null)
				return false;
		} else if (!SSN.equals(other.SSN))
			return false;
		if (age != other.age)
			return false;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (fName == null) {
			if (other.fName != null)
				return false;
		} else if (!fName.equals(other.fName))
			return false;
		if (lName == null) {
			if (other.lName != null)
				return false;
		} else if (!lName.equals(other.lName))
			return false;
		return true;
	}

	public int compareTo(Person other){
		int i = lName.compareTo(other.getlName());
		if (i != 0)
			return i;
		i = fName.compareTo(other.getfName());
		if (i != 0)
			return i;
		i = Integer.valueOf(age).compareTo(Integer.valueOf(other.getAge()));
		if(i != 0)
			return i;
		return Integer.valueOf(SSN).compareTo(Integer.valueOf(other.getSSN()));
	}
	
	
}
