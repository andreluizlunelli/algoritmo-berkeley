package br.com.andreluizlunelli.algoritmo_berkeley;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class ServerBerkeleyTest {
	
	@Before
	public void initServer() {
		new Thread() {
			@Override
			public void run() {				
				BerkeleyServer server = BerkeleyServer.start();				
			}
		}.start();
	}

	@Test
	public void connectInServerTest() {
		try {			
			Socket client = new Socket("127.0.0.1", 9999);
			PrintStream printer = new PrintStream(client.getOutputStream());
			printer.println(1);
			
			Scanner serverReturn = new Scanner(client.getInputStream());
			String nextLine = null;
			while (serverReturn.hasNext()) {
				nextLine = serverReturn.nextLine();				
			}
			System.out.println(nextLine);
			ParseReturn parser = new ParseReturn(nextLine);
			String value = parser.getValue("retorno");
			System.out.println("Valor: "+value);

			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
