package br.com.andreluizlunelli.algoritmo_berkeley;

import java.io.PrintStream;
import java.net.Socket;

public class Client {
	private String host;
	private Socket socket;
	private Timestamp timestamp;
	public Client(String host, Socket socket, Timestamp timestamp) {
		super();
		this.host = host;
		this.socket = socket;
		this.setTimestamp(timestamp);
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
}
