package controllers.Date;

import java.util.Date;

import com.google.appengine.repackaged.org.joda.time.DateTimeZone;
import com.google.appengine.repackaged.org.joda.time.LocalDateTime;

public class DateNow {
	
	public static Date now;
	
	public static Date getDateNow(){
		
		return now = LocalDateTime.now(DateTimeZone.forID("America/Lima")).toDate();
		
	}
	
}
