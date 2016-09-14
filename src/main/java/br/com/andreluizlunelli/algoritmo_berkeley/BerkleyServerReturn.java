package br.com.andreluizlunelli.algoritmo_berkeley;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BerkleyServerReturn {
	private HashMap<String, String> params = new HashMap<String, String>();
	private Socket client;
	
	public BerkleyServerReturn(Socket client) {
		this.client = client;
	}
	
	/**
	 * Escrever no console e cliente
	 */
	public void socketReturn() {
		Iterator it = params.entrySet().iterator();
		try {
			PrintStream printer = new PrintStream(client.getOutputStream());
			String _return = "";
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry)it.next();
				_return += String.format("%s:%s,", pair.getKey(), pair.getValue());
			}
			printer.println(_return);
		} catch (IOException e) {
			e.printStackTrace();
		}			
		
	}
	
	public void addParam(String key, String value) {
		params.put(key, value);
	}
}
