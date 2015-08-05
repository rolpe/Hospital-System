package rlipkin.hospital.classes;

import java.util.Date;

import rlipkin.hospital.misc.Functions;

public class VisitImp1<V, T> implements Visit<V,T>{
	private V visitor;
	private T host;
	private Date visitDate;
	
	public VisitImp1(V visitor, T host, String date){
		this.visitor = visitor;
		this.host = host;
		visitDate = Functions.stringToDate(date, "yyyy-MM-dd");
	}
	
	public String toString() {
		return "visitor=" + visitor + ", host=" + host
				+ ", visitDate=" + visitDate;
	}

	public V getVisitor(){
		return visitor;
	}
	
	public T getHost(){
		return host;
	}
	
	public Date getVisitDate(){
		return visitDate;
	}
	
	public boolean equals(Visit<V,T> v){
		if(this.getVisitor() == v.getVisitor() && this.getHost() == v.getHost() && this.getVisitDate() == v.getVisitDate()){
			return true;
		} else {
			return false;
		}
	}

	public int hashCode() {
		int result = 0;
		result = 31 * result + ((host == null) ? 0 : host.hashCode());
		result = 31 * result + ((visitDate == null) ? 0 : visitDate.hashCode());
		result = 31 * result + ((visitor == null) ? 0 : visitor.hashCode());
		return result;
	}		
	
}
