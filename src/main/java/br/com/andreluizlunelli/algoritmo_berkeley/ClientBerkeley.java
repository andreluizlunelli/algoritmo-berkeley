package br.com.andreluizlunelli.algoritmo_berkeley;

import java.sql.Timestamp;
import java.util.Calendar;

public class ClientBerkeley {

	public Timestamp whyYourTime() {
		Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
		currentTimestamp.setSeconds(33); // usar algo assim
		return currentTimestamp;
	}

}
