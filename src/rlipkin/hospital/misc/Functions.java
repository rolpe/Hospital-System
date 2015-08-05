package rlipkin.hospital.misc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;


public abstract class Functions {
	
	
	public static String dateToString(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String stringDate = formatter.format(date);
		return stringDate;
	}
	
	public static Date stringToDate(String s, String format){
		Date d = null;
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		try {
			d = formatter.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
	
	public static int daysUntil(Date d){
		long milInDay = 1000 * 60 * 60 * 24;
		Date now = new Date();
		int days = (int) ((d.getTime() - now.getTime())/ milInDay);
		return days + 1;
	}
	

	public static int calculateAge(Date dateOfBirth){
		Calendar dob = Calendar.getInstance();  
		dob.setTime(dateOfBirth);  
		Calendar today = Calendar.getInstance();  
		int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);  
		if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR))  
		age--;  
		return age;  
	}
}