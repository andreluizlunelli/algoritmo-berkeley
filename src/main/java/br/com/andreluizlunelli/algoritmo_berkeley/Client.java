package br.com.andreluizlunelli.algoritmo_berkeley;

import java.io.PrintStream;
import java.net.Socket;

public class Client {
	private String host;
	private Socket socket;
	public Client(String host, Socket socket) {
		super();
		this.host = host;
		this.socket = socket;
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
	
}
