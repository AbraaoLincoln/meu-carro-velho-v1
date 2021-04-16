package com.meucarrovelho.meucarrovelhodemo;

import com.meucarrovelho.meucarrovelhodemo.model.Peca;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestePecasEndPoints {
	@LocalServerPort
	private int port;

	@Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testeSeTodasAsPecasSaoDoCarroDesejado() {
        int carro = 1;
        ResponseEntity<Peca[]> response = restTemplate.getForEntity("http://localhost:" + port + "/api/" + carro, Peca[].class);
        Peca[] pecas = response.getBody();

        for(int i = 0; i < pecas.length; i++) {
            assertTrue(pecas[i].getCarro() == carro);
        } 
    }

    @Test
    public void testeSeTodasAsPecasSaoDoCarroETipoDesejado() {
        int carro = 1;
        String tipoPeca = "volante";
        ResponseEntity<Peca[]> response = restTemplate.getForEntity("http://localhost:" + port + "/api/" + carro + "/" + tipoPeca, Peca[].class);
        Peca[] pecas = response.getBody();

        for(int i = 0; i < pecas.length; i++) {
            assertTrue(pecas[i].getCarro() == carro);
            assertTrue(pecas[i].getTipo().equals(tipoPeca));
        }
    }

}
