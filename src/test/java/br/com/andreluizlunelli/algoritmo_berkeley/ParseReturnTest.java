package br.com.andreluizlunelli.algoritmo_berkeley;

import java.util.HashMap;

import org.junit.Test;

import junit.framework.Assert;

public class ParseReturnTest {

	@Test
	public void test01() {
		ParseReturn parseReturn = new ParseReturn("chave%%valor&&chave2%%valor2");
		HashMap<String,String> parsed = parseReturn.parse();
		Assert.assertEquals("valor", parsed.get("chave"));
		Assert.assertEquals("valor2", parsed.get("chave2"));
	}
}
