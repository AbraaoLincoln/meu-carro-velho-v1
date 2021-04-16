package com.meucarrovelho.meucarrovelhodemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.meucarrovelho.meucarrovelhodemo.model.Anuncio;
import com.meucarrovelho.meucarrovelhodemo.model.Peca;
import com.meucarrovelho.meucarrovelhodemo.daos.AnuncioRepository;
import com.meucarrovelho.meucarrovelhodemo.util.Mensagem;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TesteAnunciosEndPoints {
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private AnuncioRepository anuncioRepository;

	@Test
	public void testeSeGetAnunciosEstaFuncionando()  {
		int carro = 1;
		ResponseEntity<Anuncio[]> res = restTemplate.getForEntity("http://localhost:" + port + "/api/anuncio/" + carro + "/pecas", Anuncio[].class);
		Anuncio[] anuncios = res.getBody();
		for(int i = 0; i < anuncios.length; i++) {
			assertEquals(carro, anuncios[i].getPecas().getCarro());
		} 
	}

	@Test
	public void testeSeGetAnunciosByPecaTypeEstaFuncionando()  {
		int carro = 1;
		String pecaTipo = "volante";
		ResponseEntity<Anuncio[]> res = restTemplate.getForEntity("http://localhost:" + port + "/api/anuncio/" + carro + "/" + pecaTipo, Anuncio[].class);
		Anuncio[] anuncios = res.getBody();
		for(int i = 0; i < anuncios.length; i++) {
			assertEquals(carro, anuncios[i].getPecas().getCarro());
			assertTrue(checkIfListOfPecasHasOneOfPecaTipo(pecaTipo, anuncios[i].getPecas().getPecas()));
		} 
	}

	private boolean checkIfListOfPecasHasOneOfPecaTipo(String pecaTipo, ArrayList<Peca> pecas) {
		for(Peca p : pecas) {
			if(p.getTipo().equals(pecaTipo)) return true;
		}

		return false;
	}

	@Test
	public void postNovoAnuncioEverificaSeAMensagemDeRetornoEDeSuceesso() throws JSONException  {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		//request
		HttpEntity<String> request = new HttpEntity<String>(getJsonAnuncio().toString(), headers);
		Mensagem msg = restTemplate.postForObject("http://localhost:" + port + "/api/anuncio", request, Mensagem.class); 

		assertNotNull(msg);
		assertEquals("Anuncio salvo com sucesso", msg.getStatus());
		assertTrue(msg.getAnuncioId() > 0);

		Optional<Anuncio> anuncio = anuncioRepository.findById(msg.getAnuncioId());

		assertTrue(anuncio.isPresent());
		//to do 
		//testar todos os campos da resposta da consulta no db
	}

	private JSONObject getJsonAnuncio() throws JSONException {
		//json body
		JSONObject anuncioJSON = new JSONObject();
		anuncioJSON.put("preco", "100");
		anuncioJSON.put("descricao", "teste do put anuncio");
		anuncioJSON.put("usuario", 1);
		//add imagens
		JSONObject img1 = new JSONObject();
		img1.put("src", "img1");
		JSONObject img2 = new JSONObject();
		img1.put("src", "img2");
		JSONObject img3 = new JSONObject();
		img1.put("src", "img3");
		JSONArray imgs = new JSONArray();
		imgs.put(img1);
		imgs.put(img2);
		imgs.put(img3);
		anuncioJSON.put("imagens", imgs);
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

		return anuncioJSON;
	}

	@Test
	public void testaSeUpdateNumOfVisualizacoesEstaFuncionando() throws URISyntaxException {
		int anuncioId = 1;
		int anuncioOldVisualizacao = anuncioRepository.findById(anuncioId).orElseGet(() -> null).getVisualizacoes();
		URI uri = new URI("http://localhost:" + port + "/api/anuncio/visto/" + anuncioId);
		restTemplate.put(uri, null);
		int anuncioNewVisualizacao = anuncioRepository.findById(anuncioId).orElseGet(() -> null).getVisualizacoes();

		assertTrue(anuncioNewVisualizacao > anuncioOldVisualizacao);
	}

	@Test
	public void testaSeUpdateDisponivelEstaFuncionando() throws URISyntaxException {
		int anuncioId = 2;
		URI uri = new URI("http://localhost:" + port + "/api/anuncio/disponivel/" + anuncioId);
		restTemplate.put(uri, null);
		LocalDate dataVerificacaoDisponibilidade = LocalDate.parse(anuncioRepository.findById(anuncioId).orElseGet(() -> null).getDisponivel());
		LocalDate today = LocalDate.now();

		assertTrue(dataVerificacaoDisponibilidade.equals(today));
	}

	@Test
	public void testaSeRemoverEstaFuncionando() throws URISyntaxException {
		int anuncioId = 6;
		URI url = new URI("http://localhost:" + port + "/api/anuncio/" + anuncioId);
		restTemplate.delete(url);

		Optional<Anuncio> anuncio = anuncioRepository.findById(anuncioId);
		assertTrue(anuncio.isEmpty());
	}

	@Test
	public void testaSeAtualizarEstaFuncionando() throws JSONException, URISyntaxException  {
		int anuncioId = 1;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject anuncioJSON = getJsonAnuncio();
		anuncioJSON.put("data", LocalDate.now().toString());
		anuncioJSON.put("estado", "vendido");
		anuncioJSON.put("disponivel", LocalDate.now().toString());

		//request
		HttpEntity<String> request = new HttpEntity<String>(anuncioJSON.toString(), headers);
		URI url = new URI("http://localhost:" + port + "/api/anuncio/" + anuncioId);
		restTemplate.put(url, request);

		//test
		Anuncio anuncio = anuncioRepository.findById(anuncioId).orElseGet(() -> null);
		assertEquals("vendido", anuncio.getEstado());
		assertEquals(LocalDate.now().toString(), anuncio.getData());
		assertEquals(LocalDate.now().toString(), anuncio.getDisponivel());
	}
}
