 package rlipkin.hospital.classes;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class SchedulerData {
	
	private List<Patient> pList = new ArrayList<Patient>();
	private List<Doctor> dList = new ArrayList<Doctor>();
	private List<Visit<Integer, Integer>> vList = new ArrayList<Visit<Integer, Integer>>();

	
	public List<Patient> getPatientList() {
		return pList;
	}
	
	public List<Doctor> getDoctorList() {
		return dList;
	}
	
	public List<Visit<Integer, Integer>> getVisitList() {
		return vList;
	}
	
	public void addPatient(Patient p) {
		pList.add(p);
	}
	
	public void addDoctor(Doctor d) {
		dList.add(d);
	}
	
	public void addVisit (Visit<Integer, Integer> v) {
		vList.add(v);
	}
	
	public void removeDoctor(Doctor doc) {
		for(Doctor d: dList) {
			if (doc.equals(d)) {
				dList.remove(d);
			}
		}
	}
	
	public void removePatient(Patient pat) {
		for(Patient p: pList) {
			if (pat.equals(p)) {
				pList.remove(p);
			}
		}
	}
	
	public void removeVisit(Visit<Integer, Integer> visit) {
		for(Visit<Integer, Integer> v: vList) {
			if (v.equals(visit)) {
				vList.remove(v);
			}
		}
	}
}
