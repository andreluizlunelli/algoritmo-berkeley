package br.com.andreluizlunelli.algoritmo_berkeley;

import java.util.HashMap;

public class ParseReturn {
	private String return_;
	private HashMap<String, String> params = new HashMap<String, String>();

	public ParseReturn(String return_) {
		super();
		this.return_ = return_;
		parse(this.return_);
	}
	
	private void parse(String string) {
		String[] commaSplit = return_.split(",");
		for (String paramStr : commaSplit) {
			String[] split = paramStr.split(":");
			params.put(split[0], split[1]);
		}
	}
	
	public String getValue(String key) {
		return params.get(key);
	}
}
