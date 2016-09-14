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

	public static String K_TIME = "tempo";
	public static String K_DIRECTION = "direcao";	
	
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
				BerkleyServerReturn returnBerkleyServer = server.requestAccepted(client);
				returnBerkleyServer.socketReturn();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return server;
	}
	
	private BerkleyServerReturn requestAccepted(Socket client) throws IOException {
		addClient(client);
		BerkleyServerReturn serverReturn = new BerkleyServerReturn(client);
		serverReturn.addParam("tempo", "3");
		serverReturn.addParam("sentido", ">");
		// tem que fazer os ifs de resposta do cliente 
		
		
		return serverReturn;
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
