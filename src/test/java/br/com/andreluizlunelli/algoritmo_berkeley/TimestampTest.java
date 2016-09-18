package br.com.andreluizlunelli.algoritmo_berkeley;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import org.junit.Test;

import junit.framework.Assert;

public class TimestampTest {
		
	@Test
	public void testFormatLocalDateTimeToString() {
		String dateString = "2016-09-17 19:44:33";
		Timestamp timestamp2 = Timestamp.newTimestamp(dateString);
		LocalDateTime dateTime = LocalDateTime.now()
				.withYear(2016)
				.withMonth(9)
				.withDayOfMonth(17)
				.withHour(19)
				.withMinute(44)
				.withSecond(33);
		Timestamp timestamp = new Timestamp();
		timestamp.setDateTime(dateTime);		
		Assert.assertEquals(timestamp.formatToString(), timestamp2.formatToString());
	}
	
	@Test
	public void testAdjustmentTime() {
		Timestamp timestamp = Timestamp.newTimestamp("2016-09-17 19:44:33");
		timestamp.addSeconds(30);
		Assert.assertEquals("2016-09-17 19:45:03", timestamp.formatToString());
		timestamp.addSeconds(-4);
		Assert.assertEquals("2016-09-17 19:44:59", timestamp.formatToString());
	}
}
