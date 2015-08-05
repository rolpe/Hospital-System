package rlipkin.hospital.misc;

import java.util.Comparator;

import rlipkin.hospital.classes.Visit;

public class vDateComparator<V,T> implements Comparator<Visit<V, T>>{
	public int compare(Visit<V, T> v1, Visit<V, T> v2){
		return v1.getVisitDate().compareTo(v2.getVisitDate());
	}
}
