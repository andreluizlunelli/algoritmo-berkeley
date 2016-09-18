package br.com.andreluizlunelli.algoritmo_berkeley;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

public class Timestamp {

	public final static String PATTERN = "yyyy-MM-dd HH:mm:ss";
	private LocalDateTime dateTime = LocalDateTime.now();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);

	public String formatToString() {
		return dateTime.format(formatter);
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public static Timestamp newTimestamp(String dateString) {
		Timestamp timestamp = new Timestamp();
		DateTimeFormatter formatter = timestamp.getFormater();
		LocalDateTime ldt = LocalDateTime.parse(dateString, formatter);
		timestamp.setDateTime(ldt);
		return timestamp;
	}

	public DateTimeFormatter getFormater() {
		return formatter;
	}

	public void setFormater(DateTimeFormatter formater) {
		this.formatter = formater;
	}

	public void addSeconds(int i) {
		dateTime = dateTime.plusSeconds(i);		
	}

	public void removeSeconds(int i) {
		dateTime = dateTime.minusSeconds(i);		
	}
        
        public static long getDiffInSeconds(String date1, String date2)
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Timestamp.PATTERN);

            LocalDateTime dateTime1= LocalDateTime.parse(date1, formatter);
            LocalDateTime dateTime2= LocalDateTime.parse(date2, formatter);
            
            long diff = java.time.Duration.between(dateTime1, dateTime2).getSeconds();

            return diff;
        }
	
}
