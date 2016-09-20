package br.com.andreluizlunelli.algoritmo_berkeley;

import java.time.Duration;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;

public class CalcAdjustTime {
	
	public CalcAdjustTime(List<Timestamp> timestamps) {
		super();
		this.timestamps = timestamps;
	}

	private List<Timestamp> timestamps = new ArrayList<Timestamp>();

	public MakeParams calc(List<Timestamp> timestamps) {
		
		return null;
	}

	public List<Timestamp> getTimestamps() {
		return timestamps;
	}

	public void averageSeconds() {
		long sum = 0;
		for (Timestamp timestamp : timestamps) {
//			java.time.chrono.
//			Duration.between((Temporal) Duration.ZERO, (Temporal) timestamp.getDateTime());
		}
		
	}

}
