package br.com.andreluizlunelli.algoritmo_berkeley;

import java.io.PrintStream;

public class Client {
	private String host;
	private PrintStream stream;
	public Client(String host, PrintStream stream) {
		super();
		this.host = host;
		this.stream = stream;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public PrintStream getStream() {
		return stream;
	}
	public void setStream(PrintStream stream) {
		this.stream = stream;
	}	
}
