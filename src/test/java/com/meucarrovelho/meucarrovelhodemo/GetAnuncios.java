package com.meucarrovelho.meucarrovelhodemo;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.meucarrovelho.meucarrovelhodemo.model.Anuncio;

// import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GetAnuncios {
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testeSeAnuncioPecasEstaFuncionando()  {
		ResponseEntity<Anuncio[]> res = restTemplate.getForEntity("http://localhost:" + port + "/api/anuncio/1/pecas", Anuncio[].class);
		Anuncio[] anuncios = res.getBody();
		for(int i = 0; i < anuncios.length; i++) {
			// assert anuncios[i].getPecas().getCarro() == 2;
			assertEquals(1, anuncios[i].getPecas().getCarro());
		} 
	}

}
