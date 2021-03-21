package com.meucarrovelho.meucarrovelhodemo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.meucarrovelho.meucarrovelhodemo.daos.AnuncioRepository;
import com.meucarrovelho.meucarrovelhodemo.model.Anuncio;
import com.meucarrovelho.meucarrovelhodemo.util.Mensagem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PostAnuncio {
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private AnuncioRepository anuncioRepository;


	@Test
	public void postNovoAnuncioEverificaSeAMensagemDeRetornoEDeSuceesso() throws JSONException  {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		//json body
		JSONObject anuncioJSON = new JSONObject();
		anuncioJSON.put("preco", "100");
		anuncioJSON.put("descricao", "teste do post anuncio");
		anuncioJSON.put("usuario", 2);
		//add imagens
		JSONObject img1 = new JSONObject();
		img1.put("src", "img1");
		JSONObject img2 = new JSONObject();
		img1.put("src", "img2");
		JSONObject img3 = new JSONObject();
		img1.put("src", "img3");
		JSONArray a = new JSONArray();
		a.put(img1);
		a.put(img2);
		a.put(img3);
		anuncioJSON.put("imagens", a);
		//add pecas
		JSONObject p1 = new JSONObject();
		p1.put("tipo", "volante");
		p1.put("estado", "novo");
		JSONObject p2 = new JSONObject();
		p2.put("tipo", "banco");
		p2.put("estado", "usado");
		JSONArray pecasLista = new JSONArray();
		pecasLista.put(p1);
		pecasLista.put(p2);
		JSONObject pecas = new JSONObject();
		pecas.put("carro", "1");
		pecas.put("pecas", pecasLista);
		anuncioJSON.put("pecas", pecas);

		//request
		HttpEntity<String> request = new HttpEntity<String>(anuncioJSON.toString(), headers);
		Mensagem msg = restTemplate.postForObject("http://localhost:" + port + "/api/anuncio", request, Mensagem.class); 

		assertNotNull(msg);
		assertEquals(msg.getStatus(), "Anuncio salvo com sucesso");
		assertTrue(msg.getAnuncioId() > 0);

		Optional<Anuncio> anuncio = anuncioRepository.findById(msg.getAnuncioId());

		assertTrue(anuncio.isPresent());
		//to do 
		//testar todos os campos da responta da consulta no db
	}

}
