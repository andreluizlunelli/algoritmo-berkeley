package br.com.andreluizlunelli.algoritmo_berkeley;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MakeParams {
	
	protected HashMap<String, String> params = new HashMap<String, String>();
	
	public void addParam(String key, String value) {
		params.put(key, value);
	}
	
	public String makeParamsReturn() {
		String _return = "";
		Iterator it = params.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			_return += String.format("%s:%s,", pair.getKey(), pair.getValue());
		}
		return _return;
	}
}
