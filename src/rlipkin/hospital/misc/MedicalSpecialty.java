package rlipkin.hospital.misc;

public enum MedicalSpecialty {
	GENERAL_MEDICINE, PEDIATRICS, ONCOLOGY;
	
	public static MedicalSpecialty getFromString(String spec){
		return MedicalSpecialty.valueOf(spec.toUpperCase());
	}
}
