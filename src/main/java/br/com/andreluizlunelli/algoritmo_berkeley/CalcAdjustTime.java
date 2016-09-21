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

	public List<Timestamp> getTimestamps() {
		return timestamps;
	}

	public long averageSeconds() {
		long sum = 0;
		int i = 0;
		for (Timestamp timestamp : timestamps) {
			long seconds = timestamp.getSecondsTotal();
			sum += seconds;
			i++;
		}
		return sum / i;
	}

	private MakeParams calc(long secondsCli) {
		MakeParams params = new MakeParams();
		long average = averageSeconds();
		long sub = average - secondsCli; 		
		if (sub > 0) { // é positivo, o cliente tem que ajustar a hora dele pra cima
			params.addParam(BerkeleyServer.K_ADJUSTMENT, String.valueOf(sub));
			params.addParam(BerkeleyServer.K_DIRECTION, BerkeleyServer.K_DIRECTION_MORE);
		} else { // é negativo, tem que ajustar o valor da hora do cliente pra baixo ex: 100,150 = -50      
			params.addParam(BerkeleyServer.K_ADJUSTMENT, String.valueOf(sub * -1));
			params.addParam(BerkeleyServer.K_DIRECTION, BerkeleyServer.K_DIRECTION_LESS);
		}
		return params;
	}
	
	public MakeParams calc(Client client) {
		return calc(client.getTimestamp().getSecondsTotal());
	}
	
	public MakeParams calc(BerkeleyServer server) {
		return calc(server.getCurrentTimestamp().getSecondsTotal());
	}

}
