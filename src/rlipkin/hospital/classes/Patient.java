package rlipkin.hospital.classes;

public class Patient extends PersonImp1{
	
	private static int patientIDGenerator = 0;
	private int patientID;
	
	public Patient(String name, String ssn, String bDay){
		super(name, ssn, bDay);
		++patientIDGenerator;
		patientID = patientIDGenerator;
		
	}
	
	public int getID() {
		return patientID;
	}
	
	public String toString() {
		return super.toString() + ", ID=" + patientID;
	}
		
}
