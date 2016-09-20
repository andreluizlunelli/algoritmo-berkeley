package br.com.andreluizlunelli.algoritmo_berkeley;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CalcAdjustTimeTest {
	
	@Test
	public void test01() {
		Timestamp timestamp1 = Timestamp.newTimestamp("2016-09-20 18:34:54");
		Timestamp timestamp2 = Timestamp.newTimestamp("2016-09-20 18:35:34");
		Timestamp timestamp3 = Timestamp.newTimestamp("2016-09-20 18:34:34");
		timestamp2.addSeconds(40);
		timestamp3.removeSeconds(20);
		List<Timestamp> timestamps = new ArrayList<Timestamp>();
		timestamps.add(timestamp1);
		timestamps.add(timestamp2);
		timestamps.add(timestamp3);
		CalcAdjustTime adjust = new CalcAdjustTime(timestamps);
		adjust.averageSeconds();
		
	}
}
