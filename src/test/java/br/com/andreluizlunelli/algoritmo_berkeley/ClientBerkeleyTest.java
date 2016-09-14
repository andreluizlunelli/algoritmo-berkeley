package br.com.andreluizlunelli.algoritmo_berkeley;

import java.sql.Timestamp;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

public class ClientBerkeleyTest {
	
	@Test
	public void test01() {		
		// pega o timestamp atual
		Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());		
		ClientBerkeley client = new ClientBerkeley();
		Timestamp myDateChanged = client.whyYourTime();
//		if (myDateChanged == null) {
////			deu erro 
//			return;
//		}
	
		Assert.assertNotNull(myDateChanged);			
		Assert.assertNotEquals(currentTimestamp, myDateChanged);
	}
}
