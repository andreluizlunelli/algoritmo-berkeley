package br.com.andreluizlunelli.algoritmo_berkeley;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 * FUNCTION_TYPE_TIME_ACTUAL
 *
 */
public class TaskAskTime extends TimerTask {
	
	private Timer timer = new Timer();
	private final int time = 1000 * 20;
	
	public TaskAskTime() {
		super();
		timer.scheduleAtFixedRate(this, time, time);
	}

	@Override
	public void run() {
		BerkeleyServer instanceServer = BerkeleyServer.getInstance();
		instanceServer.sayHello();
		Iterator<Entry<String, Client>> it = instanceServer.getClients().entrySet().iterator();
		ClientBerkleyReturn clientReturn;
		while (it.hasNext()) {
			Entry<String, Client> entry = it.next();
			Client client = entry.getValue();
			System.out.println("Perguntando a data atual para o cliente: "+client.getHost());
			clientReturn = new ClientBerkleyReturn(client.getSocket());
			clientReturn.addParam(BerkeleyClient.FUNCTION_RECEIVE, BerkeleyClient.FUNCTION_TYPE_TIME_ACTUAL);
			clientReturn.socketReturn();
		}
	}

}
