package com.ef.Util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class DateTimeUtil {

	 public static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss");
	 
	 
	 public static Optional<Date> parseRequestLogDate(String dateStr) {
	        try {
	        	
	            Date parsedDate = formatter.parse(dateStr);
	            
	            return Optional.of(parsedDate);
	        } catch (ParseException e) {
	            return Optional.empty();
	        }
	    }
}
