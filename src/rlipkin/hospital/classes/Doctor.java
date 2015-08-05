package rlipkin.hospital.classes;

import rlipkin.hospital.misc.MedicalSpecialty;

public class Doctor extends PersonImp1{
	private MedicalSpecialty specialty;
	private int doctorID;
	private static int doctorIDGenerator = 0;
	
	public Doctor(String name, String ssn, String bDay, String spec){
		super(name, ssn, bDay);
		specialty = MedicalSpecialty.getFromString(spec);
		++doctorIDGenerator;
		doctorID = doctorIDGenerator;
	}
	
	public String toString() {
		return super.toString() + ", Specialty: " + specialty + ", ID=" + doctorID;
	}
	
	public int getID() {
		return doctorID;
	}
	
	public MedicalSpecialty getSpecialty() {
		return specialty;
	}
}
