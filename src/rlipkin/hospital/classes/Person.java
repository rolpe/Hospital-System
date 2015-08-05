package rlipkin.hospital.classes;

import java.util.Date;

public interface Person extends Comparable<Person>{
	String getlName();
	String getfName();
	String getSSN();
	Date getDOB();
	int getID();
	int getAge();
	int compareTo(Person other);
	String toString();
}
