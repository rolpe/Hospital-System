package rlipkin.hospital.classes;

import java.util.Date;

public interface Visit<V,T> {
	public V getVisitor();
	public T getHost();
	public Date getVisitDate();
}
