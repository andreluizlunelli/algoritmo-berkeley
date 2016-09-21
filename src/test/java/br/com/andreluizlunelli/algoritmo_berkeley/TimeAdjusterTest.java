package br.com.andreluizlunelli.algoritmo_berkeley;

import org.junit.Test;

import junit.framework.Assert;

public class TimeAdjusterTest {
	
	@Test
	public void test01ParamsCli() {
		Timestamp newTimestamp = Timestamp.newTimestamp("2016-09-17 19:44:33"); // 22
		BerkeleyClient cli = new BerkeleyClient();
		cli.setCurrentTimestamp(newTimestamp);
		TimeAdjuster adjuster = new TimeAdjuster();
		Timestamp adjusted = adjuster.adjustTimestamp(cli, ">", 22);
		Assert.assertEquals("2016-09-17 19:44:55", adjusted.formatToString());
		Assert.assertEquals("2016-09-17 19:44:55", cli.getCurrentTimestamp().formatToString());
	}	
	
	@Test
	public void test02ParamsCliMaked() {
		Timestamp newTimestamp = Timestamp.newTimestamp("2016-09-17 19:44:33"); // 22
		BerkeleyClient cli = new BerkeleyClient();
		cli.setCurrentTimestamp(newTimestamp);
		MakeParams params = new MakeParams();
		params.addParam(BerkeleyServer.K_ADJUSTMENT, "22");
		params.addParam(BerkeleyServer.K_DIRECTION, ">");
		TimeAdjuster adjuster = new TimeAdjuster();
		Timestamp adjusted = adjuster.adjustTimestamp(cli, params);
		Assert.assertEquals("2016-09-17 19:44:55", adjusted.formatToString());
	}
}
