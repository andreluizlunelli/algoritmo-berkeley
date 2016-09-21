package br.com.andreluizlunelli.algoritmo_berkeley;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;

public class CalcAdjustTimeTest {
	
	private List<Timestamp> timestamps;

	@Before
	public void initTests() {
		Timestamp timestamp1 = Timestamp.newTimestamp("2016-09-20 18:34:54");
		Timestamp timestamp2 = Timestamp.newTimestamp("2016-09-20 18:35:34");
		Timestamp timestamp3 = Timestamp.newTimestamp("2016-09-20 18:34:34");
		timestamp2.addSeconds(40);
		timestamp3.removeSeconds(20);
		timestamps = new ArrayList<Timestamp>();
		timestamps.add(timestamp1);
		timestamps.add(timestamp2);
		timestamps.add(timestamp3);
	}
	
	@Test
	public void testAverageSeconds() {
		CalcAdjustTime adjust = new CalcAdjustTime(timestamps);
		long averageSeconds = adjust.averageSeconds();
		Assert.assertEquals((long)1474396507, averageSeconds);
	}
	
	@Test
	public void testGetDiffSecondsToClient() {
		CalcAdjustTime adjust = new CalcAdjustTime(timestamps);
		Client client = null;
		client = new Client("127.0.0.1", null, Timestamp.newTimestamp("2016-09-20 18:34:54"));
		MakeParams params = adjust.calc(client);
		Assert.assertEquals(params.getValue(BerkeleyServer.K_ADJUSTMENT), "13");
		Assert.assertEquals(params.getValue(BerkeleyServer.K_DIRECTION), ">");
	}
}
