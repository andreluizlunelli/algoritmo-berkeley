package br.com.andreluizlunelli.algoritmo_berkeley;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

/**
 * Classe Servidora
 * 
 */
public class BerkeleyServer {

	// chaves utilizadas para padronizar o retorno
	public static String K_TIME = "tempo";
	public static String K_ADJUSTMENT = "ajuste";
	public static String K_DIRECTION = "direcao";
	public static String K_DIRECTION_MORE = ">";
	public static String K_DIRECTION_LESS = "<";

	private static BerkeleyServer server;
	private static final int PORT_SERVER = 9999;
	private ServerSocket socket;
	private List<Timestamp> listTimesClients = new Vector();	
	private HashMap<String, Client> mapClients = new HashMap<String, Client>();
	private Timestamp currentTimestamp = new Timestamp();
	private TimeAdjuster timeAdjuster = new TimeAdjuster();
	
	public static void main(String[] args) {
		BerkeleyServer.start();
	}
	
	private BerkeleyServer() {
	}
	
	public static BerkeleyServer getInstance() {
		return server;
	}
	
	public static BerkeleyServer start() {
		server = new BerkeleyServer();
		try {
			server.socket = new ServerSocket(PORT_SERVER);
			for (;;) {
				final Socket client = server.socket.accept(); // fica escutando algum cliente se conectar
				new Thread() {
					public void run() {
						try {
							server.requestAccepted(client);
						} catch (IOException e) {
							e.printStackTrace();
						}
					};
				}.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return server;
	}
	
	private void requestAccepted(Socket client) throws IOException {
		System.out.println("Passou pelo requestAccepted");
		BerkleyServerReturn serverReturn = new BerkleyServerReturn(client);
		Scanner reader = new Scanner(client.getInputStream());
		while (reader.hasNext()) {
			String requestMessage = reader.nextLine();
			ParseReturn parser = new ParseReturn(requestMessage);		
			String message = parser.getValue(BerkeleyClient.FUNCTION_RECEIVE);
			/**
			 * 
			 * CONTROLA A LEITURA E RESPOSTA DO SERVIDOR PARA OS CLIENTES
			 * 
			 */
			if (BerkeleyClient.FUNCTION_TYPE_START.equals(message)) { // inicia a logica
				serverReturn = requestFunctionStart(parser, client);
				serverReturn.socketReturn();
			} else if (BerkeleyClient.FUNCTION_TYPE_TIME_ACTUAL_RESPONSE.equals(message)) { // pegou a data atual do cliente
				requestFunctionTimeActialResponse(parser, client);
			} else if (BerkeleyClient.FUNCTION_TYPE_CHANGE_MY_DATE_RESPONSE.equals(message)) {
				System.out.println("ClientBerkeley.FUNCTION_TYPE_CHANGE_MY_DATE_RESPONSE");
			}
			
			// tem que adicionar um parametro para pegar a requisição para atualizar também a data do server
		}
	}

	private void requestFunctionTimeActialResponse(ParseReturn parser, Socket client) {
		String dateString = parser.getValue(K_TIME);
		if (dateString == null) {
			throw new IllegalArgumentException("Data de envio do cliente nao pode ser vazio");
		}
		Timestamp timestamp = Timestamp.newTimestamp(dateString);
		String host = client.getInetAddress().getHostName();
		getClients().get(host).setTimestamp(timestamp);
		addTimestamp(timestamp);
		if (getListTimesClients().size() == getClients().size()) {
			// dispara a thread para calcular a media do ajuste e enviar aos clientes
//			serverReturn.addParam("ajuste", "30");
//			serverReturn.addParam("sentido", ">");
			List<Timestamp> tmpListTimes = getListTimesClients();
			tmpListTimes.add(this.getCurrentTimestamp());
			new TaskAdjustTimeResponse(tmpListTimes, getClients(), this).start();
		}
	}

	private BerkleyServerReturn requestFunctionStart(ParseReturn parser, Socket client) {
		BerkleyServerReturn serverReturn = new BerkleyServerReturn(client);
		serverReturn.addParam(BerkeleyClient.FUNCTION_RECEIVE, BerkeleyClient.FUNCTION_TYPE_OK);
		try {
			addClient(client);
		} catch (IOException e) {
			e.printStackTrace();
		}
		initTaskGetAndChangeTime();
		return serverReturn;
	}

	private void initTaskGetAndChangeTime() {
		new TaskAskTime();
	}

	private synchronized void addClient(Socket socket) throws IOException {
		Client client = new Client(socket.getInetAddress().getHostName(), socket, null);
		mapClients.put(client.getHost(), client);
	}

	public synchronized HashMap<String, Client> getMapClients() {
		return mapClients;
	}

	public synchronized HashMap<String, Client> getClients() {
		return mapClients;
	}

	public void setMapClients(HashMap<String, Client> mapClients) {
		this.mapClients = mapClients;
	}	
	
	public synchronized List<Timestamp> getListTimesClients() {
		return listTimesClients;
	}

	public synchronized void addTimestamp(Timestamp timestamp) {
		this.listTimesClients.add(timestamp);
	}

	public void sayHello() {
		System.out.println("Hi");
	}

	public Timestamp getCurrentTimestamp() {
		return currentTimestamp;
	}

	public void setCurrentTimestamp(Timestamp currentTimestamp) {
		this.currentTimestamp = currentTimestamp;
	}
	
	public void adjustMyTime(MakeParams params) {
		timeAdjuster.adjustTimestamp(this, params);
	}
	
}
