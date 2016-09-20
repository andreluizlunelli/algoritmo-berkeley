package br.com.andreluizlunelli.algoritmo_berkeley;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Iterator;

public class ClientBerkleyReturn extends MakeParams {

	private Socket server;

	public ClientBerkleyReturn(Socket socket) {
		this.server = socket;
	}
	
	/**
	 * Escrever no console e cliente
	 */
	public void socketReturn() {
		try {
			PrintStream printer = new PrintStream(server.getOutputStream());
			String _return = makeParamsReturn();
			printer.println(_return);
			System.out.println("Enviou para o server: \""+_return+"\"");
		} catch (IOException e) {
			e.printStackTrace();
		}			
		
	}

}
