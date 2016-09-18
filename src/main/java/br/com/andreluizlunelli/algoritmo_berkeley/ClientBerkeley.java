package br.com.andreluizlunelli.algoritmo_berkeley;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

public class ClientBerkeley {
	
	public static final String IP_SERVER = "127.0.0.1";
	public static final int PORT_SERVER = 9999;
	public static final String FUNCTION_RECEIVE = "funcao";
	public static final String FUNCTION_TYPE_TIME_ACTUAL = "qual_seu_tempo";
	public static final String FUNCTION_TYPE_TIME_ACTUAL_RESPONSE = "qual_seu_tempo_resposta";
	public static final String FUNCTION_TYPE_CHANGE_MY_DATE = "mude_seu_timestamp";
	private Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

	public static void main(String[] args) {
		ClientBerkeley client = new ClientBerkeley();
		client.connect();
	}

	public void connect() {
		try {			
			Socket server = new Socket(IP_SERVER, PORT_SERVER);
			PrintStream printer = new PrintStream(server.getOutputStream());
			printer.println("c:1,"); // manda um valor para se conectar
			Scanner serverReturn = new Scanner(server.getInputStream());
			String nextLine = null;
			while (serverReturn.hasNext()) { // fica escutando o server até ele morrer
				nextLine = serverReturn.nextLine();				
				ClientBerkleyReturn _return = (ClientBerkleyReturn) makeProcessRequest(nextLine); // tenho que fazer um construtor que receba o makeParams 
				_return.socketReturn();
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	/**
	 * Retorna o timestamp atual alterado
	 * @return
	 */
	public Timestamp whyMyTime() {		
		int random = getRamdomIntPositive();
		currentTimestamp.setMinutes(random);
		return currentTimestamp;
	}

	private int getRamdomIntPositive() {		
		return getIntBetween(0, 60);
	}

	private int getIntBetween(int min, int max) {
		return new Random().nextInt((max + 1) - min) + min;
	}

	public MakeParams makeProcessRequest(String valueRequested) {
		ParseReturn parser = new ParseReturn(valueRequested);
		String value = parser.getValue(FUNCTION_RECEIVE); // pega um valor para saber o que se esta requisitando
		MakeParams params = new MakeParams();
		if (value == null) {
			System.out.println("valor da requisicao e nulo");
			return params;
		}
		if (FUNCTION_TYPE_TIME_ACTUAL.equals(value)) { // está perguntando a data atual do cliente
			Timestamp myTime = whyMyTime();
			params.addParam(BerkeleyServer.K_TIME, myTime.toString());
			params.addParam(FUNCTION_RECEIVE, FUNCTION_TYPE_TIME_ACTUAL_RESPONSE);
		}
		if (FUNCTION_TYPE_CHANGE_MY_DATE.equals(value)) {
			
		}
		return params;
	}


}
