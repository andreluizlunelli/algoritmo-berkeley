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

/**
 * Classe Servidora
 * 
 */
public class BerkeleyServer {

	// chaves utilizadas para padronizar o retorno
	public static String K_TIME = "tempo";
	public static String K_DIRECTION = "direcao";
	public static String K_ADJUSTMENT = "ajuste";
	public static String K_DIRECTION_MORE = ">";
	public static String K_DIRECTION_LESS = "<";
	
	private ServerSocket socket;
	private static final int PORT_SERVER = 9999;
	private HashMap<String, Client> mapClients = new HashMap<String, Client>();
	
	public static void main(String[] args) {
		BerkeleyServer server = new BerkeleyServer();
		server.start();
	}
	
	private BerkeleyServer() {
	}
	
	public static BerkeleyServer start() {
		BerkeleyServer server = new BerkeleyServer();
		try {
			server.socket = new ServerSocket(PORT_SERVER);
			for (;;) {
				Socket client = server.socket.accept(); // fica escutando algum cliente se conectar
				BerkleyServerReturn returnBerkleyServer = server.requestAccepted(client); // trata a requisição
				returnBerkleyServer.socketReturn(); // responde o cliente escrevendo na inputstream
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return server;
	}
	
	private BerkleyServerReturn requestAccepted(Socket client) throws IOException {
		addClient(client);
		System.out.println("Passou pelo requestAccepted");
		
		// TODO in progress
		BerkleyServerReturn serverReturn = new BerkleyServerReturn(client);
		Scanner reader = new Scanner(client.getInputStream());
		String requestMessage = reader.nextLine();
		ParseReturn parser = new ParseReturn(requestMessage);		
		String message = parser.getValue(ClientBerkeley.FUNCTION_RECEIVE);
		if (ClientBerkeley.FUNCTION_TYPE_CHANGE_MY_DATE_RESPONSE.equals(message)) {
			System.out.println("ClientBerkeley.FUNCTION_TYPE_TIME_ACTUAL_RESPONSE");
			System.out.println("Tempo respondido: "+parser.getValue("tempo"));
		}
		
		serverReturn.addParam(ClientBerkeley.FUNCTION_RECEIVE, ClientBerkeley.FUNCTION_TYPE_TIME_ACTUAL);

		// mais ou menos isso aqui tem que enviar de tantos tem tantos tempos p todos os clientes		
//		serverReturn.addParam("ajuste", "30");
//		serverReturn.addParam("sentido", ">");
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
