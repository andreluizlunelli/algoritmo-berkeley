package br.com.andreluizlunelli.algoritmo_berkeley;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class BerkeleyServer {

	private ServerSocket socket;
	private static final int PORT_SERVER = 9999;
	private HashMap<String, Client> mapClients = new HashMap<String, Client>();
	
	private BerkeleyServer() {
	}
	
	public static BerkeleyServer start() {
		BerkeleyServer server = new BerkeleyServer();
		try {
			server.socket = new ServerSocket(PORT_SERVER);
			for (;;) {
				Socket client = server.socket.accept();
				
				ReturnBerkleyServer returnBerkleyServer = server.requestAccepted(client);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return server;
	}
	
	private ReturnBerkleyServer requestAccepted(Socket client) throws IOException {
		addClient(client);
		
		Scanner leitura = new Scanner(client.getInputStream());
		int nextInt = leitura.nextInt();
		
		System.out.println("mandou um "+nextInt);
		
		return null;
	}

	private void addClient(Socket socket) throws IOException {
		Client client = new Client(socket.getInetAddress().getHostName(), new PrintStream(socket.getOutputStream()));
		mapClients.put(client.getHost(), client);
	}

	public HashMap<String, Client> getMapClients() {
		return mapClients;
	}

	public void setMapClients(HashMap<String, Client> mapClients) {
		this.mapClients = mapClients;
	}
	

}
