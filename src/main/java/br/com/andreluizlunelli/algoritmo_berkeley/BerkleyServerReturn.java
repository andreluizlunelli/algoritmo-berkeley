package br.com.andreluizlunelli.algoritmo_berkeley;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BerkleyServerReturn extends MakeParams {
	
	private Socket client;	
	
	public BerkleyServerReturn(Socket client) {
		this.client = client;
	}
	
	/**
	 * Escrever no console e cliente
	 */
	public void socketReturn() {
		try {
			PrintStream printer = new PrintStream(client.getOutputStream());
			String _return = makeParamsReturn();
			printer.println(_return);
			System.out.println("Enviou para o cliente: \""+_return+"\"");
		} catch (IOException e) {
			e.printStackTrace();
		}			
		
	}
	
}
