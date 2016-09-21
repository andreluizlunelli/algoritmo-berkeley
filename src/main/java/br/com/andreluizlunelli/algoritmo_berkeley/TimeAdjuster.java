package br.com.andreluizlunelli.algoritmo_berkeley;

public class TimeAdjuster {

	public Timestamp adjustTimestamp(BerkeleyClient cli, MakeParams params) {
		String seconds = params.getValue(BerkeleyServer.K_ADJUSTMENT);
		int iSeconds = Integer.valueOf(seconds);
		String direction = params.getValue(BerkeleyServer.K_DIRECTION);
		return adjustTimestamp(cli, direction, iSeconds);
	}

	public Timestamp adjustTimestamp(BerkeleyClient cli, String direction, int seconds) {
		return adjustTimestamp(cli.getCurrentTimestamp(), direction, seconds);
	}
	
	public Timestamp adjustTimestamp(BerkeleyServer server, MakeParams params) {
		String seconds = params.getValue(BerkeleyServer.K_ADJUSTMENT);
		int iSeconds = Integer.valueOf(seconds);
		String direction = params.getValue(BerkeleyServer.K_DIRECTION);
		return adjustTimestamp(server, direction, iSeconds);
	}

	public Timestamp adjustTimestamp(BerkeleyServer server, String direction, int seconds) {
		return adjustTimestamp(server.getCurrentTimestamp(), direction, seconds);
	}

	private Timestamp adjustTimestamp(Timestamp timestamp, String direction, int seconds) {		
		if (BerkeleyServer.K_DIRECTION_MORE.equals(direction)) {
			timestamp.addSeconds(seconds);
		} else if (BerkeleyServer.K_DIRECTION_LESS.equals(direction)) {
			timestamp.removeSeconds(seconds);
		} else {
			throw new IllegalArgumentException("Deu algum pau na direcao do ajuste do tempo");
		}
		return timestamp;
	}


}
