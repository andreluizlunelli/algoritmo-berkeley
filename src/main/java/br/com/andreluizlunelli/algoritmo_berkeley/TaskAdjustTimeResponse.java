package br.com.andreluizlunelli.algoritmo_berkeley;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;
import java.util.Map.Entry;

public class TaskAdjustTimeResponse extends Thread {

	private List<Timestamp> timestamps;
	private HashMap<String, Client> clients;
	private BerkeleyServer berkeleyServer;
	private CalcAdjustTime calcAdjustTime;

	public TaskAdjustTimeResponse(List<Timestamp> timestamps, HashMap<String, Client> clients, BerkeleyServer berkeleyServer) {
		this.timestamps = timestamps;
		this.clients = clients;
		this.berkeleyServer = berkeleyServer;
	}
	
	@Override
	public void run() {
		super.run();
		Iterator<Entry<String, Client>> it = clients.entrySet().iterator();
		BerkleyServerReturn serverReturn;
		calcAdjustTime = new CalcAdjustTime(timestamps);
		MakeParams params;
		while (it.hasNext()) {
			Entry<String, Client> entry = it.next();
			Client client = entry.getValue();
			params = calcAdjustTime.calc(client);
			System.out.println("Respondendo o fator de ajuste para o cliente: "+client.getHost());
			// fazer uma função que retorna o makeparams e uso ele p responder os clientes e o server
			serverReturn = new BerkleyServerReturn(client.getSocket());
			serverReturn.setParams(params.getParams());
			serverReturn.addParam(BerkeleyClient.FUNCTION_RECEIVE, BerkeleyClient.FUNCTION_TYPE_CHANGE_MY_DATE);
			serverReturn.socketReturn();
		}
		
		// TODO faz o ajuste do server tambem
//		berkeleyServer.adjustTime(seconds, direction);
	}
	
}
