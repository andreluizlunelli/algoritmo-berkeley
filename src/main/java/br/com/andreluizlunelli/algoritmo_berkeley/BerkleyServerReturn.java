package br.com.andreluizlunelli.algoritmo_berkeley;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BerkleyServerReturn {
	private HashMap<String, String> params = new HashMap<String, String>();
	
	/**
	 * Escrever no console e cliente
	 */
	public void socketReturn() {
		Iterator it = params.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			String _return = String.format("%s:%s,", pair.getKey(), pair.getValue());
			System.out.println(_return);
		}
		
	}
	
	public void addParam(String key, String value) {
		params.put(key, value);
	}
}
