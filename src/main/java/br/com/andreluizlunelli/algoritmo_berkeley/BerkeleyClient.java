package br.com.andreluizlunelli.algoritmo_berkeley;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

public class BerkeleyClient {
	
	public static final String IP_SERVER = "127.0.0.1";
	public static final int PORT_SERVER = 9999;
	public static final String FUNCTION_TYPE_START = "1";
	public static final String FUNCTION_RECEIVE = "funcao";
	public static final String FUNCTION_TYPE_OK = "OK";
	public static final String FUNCTION_TYPE_TIME_ACTUAL = "qual_seu_tempo";
	public static final String FUNCTION_TYPE_TIME_ACTUAL_RESPONSE = "qual_seu_tempo_resposta";
	public static final String FUNCTION_TYPE_CHANGE_MY_DATE = "mude_seu_timestamp";
	public static final String FUNCTION_TYPE_CHANGE_MY_DATE_RESPONSE = "OK MUDEI";
	private Timestamp currentTimestamp = new Timestamp();
	private TimeAdjuster timeAdjuster = new TimeAdjuster();

	public static void main(String[] args) {
		BerkeleyClient client = new BerkeleyClient();
		client.connect();
	}

	public void connect() {
		try {		
			Socket server = new Socket(IP_SERVER, PORT_SERVER);
			PrintStream printer = new PrintStream(server.getOutputStream());
			printer.println(createInitValueToConnect()); // manda um valor para se conectar
			Scanner serverReturn = new Scanner(server.getInputStream());
			String nextLine = null;
			while (serverReturn.hasNext()) { // fica escutando o server até ele morrer
				nextLine = serverReturn.nextLine();				
				ClientBerkleyReturn _return = makeProcessRequest(server, nextLine); // tenho que fazer um construtor que receba o makeParams
				if (_return != null) {
					_return.socketReturn();
				}
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
		LocalDateTime dateTime = getCurrentTimestamp().getDateTime();
		dateTime.withMinute(random);
		getCurrentTimestamp().setDateTime(dateTime);
		return getCurrentTimestamp();
	}

	private int getRamdomIntPositive() {		
		return getIntBetween(0, 60);
	}

	private int getIntBetween(int min, int max) {
		return new Random().nextInt((max + 1) - min) + min;
	}

	public ClientBerkleyReturn makeProcessRequest(Socket server, String valueRequested) {
		ParseReturn parser = new ParseReturn(valueRequested);
		String value = parser.getValue(FUNCTION_RECEIVE); // pega um valor para saber o que se esta requisitando
		MakeParams params = new MakeParams();
		if (value == null) {
			System.out.println("valor da requisicao e nulo");
			return null;
		}
		/**
		 * 
		 * CONTROLA A LEITURA E RESPOSTA PARA O SERVIDOR
		 * 
		 */
		if (FUNCTION_TYPE_TIME_ACTUAL.equals(value)) { // está perguntando a data atual do cliente
			params.addParam(FUNCTION_RECEIVE, FUNCTION_TYPE_TIME_ACTUAL_RESPONSE);
			params.addParam(BerkeleyServer.K_TIME, whyMyTime().formatToString());
		} else if (FUNCTION_TYPE_CHANGE_MY_DATE.equals(value)) {
			params.addParam(FUNCTION_RECEIVE, FUNCTION_TYPE_CHANGE_MY_DATE_RESPONSE); // responde ok p o server
			String direction = parser.getValue(BerkeleyServer.K_DIRECTION);
			int second = Integer.valueOf(parser.getValue(BerkeleyServer.K_ADJUSTMENT));
			adjustMyTime(direction, second);
		}
		ClientBerkleyReturn clientReturn = new ClientBerkleyReturn(server);
		clientReturn.setParams(params.getParams());
		return clientReturn;
	}

	private String createInitValueToConnect() {
		MakeParams makeParams = new MakeParams();
		makeParams.addParam(FUNCTION_RECEIVE, "1");
		String _return = makeParams.makeParamsReturn();
		return _return;
	}

	public Timestamp getCurrentTimestamp() {
		return currentTimestamp;
	}

	public void setCurrentTimestamp(Timestamp currentTimestamp) {
		this.currentTimestamp = currentTimestamp;
	}

	public void adjustMyTime(String direction, int second) {
		timeAdjuster.adjustTimestamp(this, direction, second);
	}

}
