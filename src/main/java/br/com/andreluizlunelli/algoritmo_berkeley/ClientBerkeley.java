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
	
	private static final String IP_SERVER = "127.0.0.1";
	private static final int PORT_SERVER = 9999;
	private static final String FUNCTION_RECEIVE = "funcao";
	private static final String FUNCTION_TYPE_TIME_ACTUAL = "funcao";

	public static void main(String[] args) {
		ClientBerkeley client = new ClientBerkeley();
		client.connect();
	}

	public void connect() {
		try {			
			Socket client = new Socket(IP_SERVER, PORT_SERVER);
			PrintStream printer = new PrintStream(client.getOutputStream());
			printer.println(1); // manda um valor qualquer para se conectar
			Scanner serverReturn = new Scanner(client.getInputStream());
			String nextLine = null;
			while (serverReturn.hasNext()) { // fica escutando o server até ele morrer
				nextLine = serverReturn.nextLine();				
				MakeParams paramsToReturn = makeProcessRequest(nextLine);
				// tenho que retornar para o server				
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
		Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
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
		String value = parser.getValue(FUNCTION_RECEIVE);
		MakeParams params = new MakeParams();
		if (value == FUNCTION_TYPE_TIME_ACTUAL) { // está perguntando a data atual para o cliente
			Timestamp myTime = whyMyTime();
			params.addParam("tempo", myTime.toString());
		}
		return params;
	}


}
