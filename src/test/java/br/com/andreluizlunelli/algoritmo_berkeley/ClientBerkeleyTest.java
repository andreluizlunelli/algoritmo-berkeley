package br.com.andreluizlunelli.algoritmo_berkeley;

import java.sql.Timestamp;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

public class ClientBerkeleyTest {

	
	private static final String FUNCTION_TIME_ACTUAL = "funcao:tempo_atual,";

	@Test
	/**
	 * Teste: valida o metodo de alterar senha
	 */
	public void test01() {				
		Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()); // pega o timestamp atual		
		ClientBerkeley client = new ClientBerkeley();
		Timestamp myDateChanged = client.whyMyTime();
		Assert.assertNotNull(myDateChanged);			
		Assert.assertNotEquals(currentTimestamp, myDateChanged); // se a data estiver diferente, o metodo esta funcionando
	}
	
	@Test
	/**
	 * Teste: simula o cliente recebendo uma requisição do server com a mensagem de retornar tempo atual
	 */
	public void test02() {
		ClientBerkeley client = new ClientBerkeley();
		MakeParams params = client.makeProcessRequest(FUNCTION_TIME_ACTUAL);
		// tenho que retornar esses parametros para o server
	}
}
