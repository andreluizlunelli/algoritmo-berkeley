package br.com.andreluizlunelli.algoritmo_berkeley;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

import junit.framework.Assert;

public class ServerBerkeleyTest {

	@Test
	public void connectInServerTest() {
		BerkeleyServer server = BerkeleyServer.start(); // precisa executar o server em outra thread se n não vai funcionar
		try {
			Runtime.getRuntime().wait(2000);
			Socket client = new Socket("127.0.0.1", 9999);
			PrintStream printer = new PrintStream(client.getOutputStream());
			printer.println(1);
			int size = server.getMapClients().size();
			Assert.assertEquals("Não há clientes conectados", 1, size);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
