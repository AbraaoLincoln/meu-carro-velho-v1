package com.meucarrovelho.meucarrovelhodemo.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.meucarrovelho.meucarrovelhodemo.daos.AnuncioRepository;
import com.meucarrovelho.meucarrovelhodemo.daos.CarroRepository;
import com.meucarrovelho.meucarrovelhodemo.daos.ImagemRepository;
import com.meucarrovelho.meucarrovelhodemo.daos.PecaRepository;
import com.meucarrovelho.meucarrovelhodemo.daos.UsuarioRepository;
import com.meucarrovelho.meucarrovelhodemo.exceptions.BusinessException;
import com.meucarrovelho.meucarrovelhodemo.model.Anuncio;
import com.meucarrovelho.meucarrovelhodemo.model.Carro;
import com.meucarrovelho.meucarrovelhodemo.model.Imagem;
import com.meucarrovelho.meucarrovelhodemo.model.Peca;
import com.meucarrovelho.meucarrovelhodemo.model.PecasCarro;
import com.meucarrovelho.meucarrovelhodemo.model.Usuario;
import com.meucarrovelho.meucarrovelhodemo.util.Mensagem;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.MDC;

@RestController
@RequestMapping(path = "/api/anuncio")
public class AnuncioController {
    private static final Logger logger = LoggerFactory.getLogger(PecasController.class);
    @Autowired
    private AnuncioRepository anuncioRepository;
    @Autowired
    private PecaRepository pecaRepository;
    @Autowired
    private ImagemRepository imagemRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CarroRepository carroRepository;

    @GetMapping(path = "/{carro}/pecas")
    public Iterable<Anuncio> getAnuncios(@PathVariable("carro") int carro) {
        logger.trace("Executing getAnuncios");
        ArrayList<Anuncio> listOfAnuncios = new ArrayList<>();
        // System.out.println("pegando todos os anuncios de pecas para o carro: " + carro + "...");
        ArrayList<Peca> pecas = pecaRepository.getPecaByCarro(carro);

        HashMap<Integer, ArrayList<Peca>> anuncioPecas = new HashMap<>();
        for(Peca p : pecas) {
            if(anuncioPecas.containsKey(p.getAnuncio())) {
                anuncioPecas.get(p.getAnuncio()).add(p);
            }else {
                anuncioPecas.put(p.getAnuncio(), new ArrayList<Peca>());
                anuncioPecas.get(p.getAnuncio()).add(p);
            } 
        }

        for(int anuncioId : anuncioPecas.keySet()) {
            Anuncio anuncio = anuncioRepository.findById(anuncioId).orElseGet(() -> null);
            PecasCarro pc = new PecasCarro();
            pc.setCarro(carro);
            pc.setPecas(anuncioPecas.get(anuncioId));
            anuncio.setPecas(pc);
            anuncio.setImagens(imagemRepository.getImagensByAnuncio(anuncioId));
            listOfAnuncios.add(anuncio);
        }

        return listOfAnuncios;
    }

    @GetMapping(path = "/{carro}/{tipoPeca}")
    public Iterable<Anuncio> getAnunciosByPecaType(@PathVariable("carro") int carro, @PathVariable("tipoPeca") String tipoPeca) {
        logger.trace("Executing getAnunciosByPecaType");
        ArrayList<Anuncio> listOfAnuncios = new ArrayList<>();
        // System.out.println("pegando todos os anuncios de pecas do tipo " + tipoPeca + " para o carro: " + carro + "...");
        ArrayList<Peca> pecas = pecaRepository.getPecaByCarroAndType(carro, tipoPeca);

        Set<Integer> anunciosId = new HashSet<>();
        for(Peca p : pecas) {
            anunciosId.add(p.getAnuncio());
        }

        for(int anuncioId : anunciosId) {
            Anuncio anuncio = anuncioRepository.findById(anuncioId).orElseGet(() -> null);
            PecasCarro pc = new PecasCarro();
            pc.setCarro(carro);
            pc.setPecas(pecaRepository.getPecaByAnuncio(anuncioId));
            anuncio.setPecas(pc);
            anuncio.setImagens(imagemRepository.getImagensByAnuncio(anuncioId));
            listOfAnuncios.add(anuncio);
        }

        return listOfAnuncios;
    }

    @PostMapping()
    public Mensagem saveAnuncio(@RequestBody Anuncio novoAnuncio) {
        logger.trace("Executing saveAnuncio");
        try {
            validateAnuncio(novoAnuncio);

            for(Peca p : novoAnuncio.getPecas().getPecas()) {
                validatePeca(p);
                p.setCarro(novoAnuncio.getPecas().getCarro());
            }

            novoAnuncio.setData(LocalDate.now().toString());
            novoAnuncio.setEstado("disponivel");
            novoAnuncio.setDisponivel(LocalDate.now().toString());
            novoAnuncio = anuncioRepository.save(novoAnuncio);

            for(Imagem img : novoAnuncio.getImagens()) {
                img.setSrc(novoAnuncio.getUsuario() + "/" + novoAnuncio.getId() + "/" + img.getSrc());
                img.setAnuncio(novoAnuncio.getId());
            }
            imagemRepository.saveAll(novoAnuncio.getImagens());

            for(Peca p : novoAnuncio.getPecas().getPecas()) {
                p.setAnuncio(novoAnuncio.getId());
            }
            pecaRepository.saveAll(novoAnuncio.getPecas().getPecas());

            Mensagem msg = new Mensagem("Anuncio salvo com sucesso");
            msg.setAnuncioId(novoAnuncio.getId()); 
            return msg;
        } catch (BusinessException e) {
            Mensagem msg = new Mensagem("Error");
            msg.setListOfErros(e.getListOfErros());
            msg.setAnuncioId(0);
            return msg;
        }
    }

    private void validateAnuncio(Anuncio anuncioToValidade) throws BusinessException{
        logger.trace("Executing validateAnuncio");
        ArrayList<String> listOfErros = new ArrayList<>();
        Usuario user = usuarioRepository.findById(anuncioToValidade.getUsuario()).orElseGet(() -> null);

        if(user == null) {
            listOfErros.add("Usuario invalido");
        }

        if(anuncioToValidade.getPreco() < 0) {
            listOfErros.add("Preco do anuncio ?? invalido");
        }

        Carro carro = carroRepository.findById(anuncioToValidade.getPecas().getCarro()).orElseGet(() -> null);
        if(carro == null) {
            listOfErros.add("Modelo do carro invalido");
        }

        if(listOfErros.size() > 0) {
            throw new BusinessException(listOfErros);
        }
    }

    private void validatePeca(Peca pecaToValidade) throws BusinessException{
        logger.trace("Executing validatePeca");
        ArrayList<String> listOfErros = new ArrayList<>();
        
        if(!(pecaToValidade.getEstado().equals("novo") || pecaToValidade.getEstado().equals("usado"))) {
            listOfErros.add("Estado da peca invalida, deve ser novo ou usado");
        }

        if(listOfErros.size() > 0) {
            throw new BusinessException(listOfErros);
        }
    }

    @PutMapping(path = "/visto/{anuncioId}")
    public Mensagem updateNumOfVisualizacoes(@PathVariable("anuncioId") int anuncioId) {
        logger.trace("Executing updateNumOfVisualizacoes");
        if(anuncioRepository.existsById(anuncioId)) {
            anuncioRepository.updateAnuncioVisualizacao(anuncioId);
            return new Mensagem("visualizao do anuncio atualizada com sucesso");
        }else {
            return new Mensagem("anuncio n??o existe");
        }
    }

    @PutMapping(path = "/disponivel/{anuncioId}")
    public Mensagem updateDisponivel(@PathVariable("anuncioId") int anuncioId) {
        logger.trace("Executing updateDisponivel");
        if(anuncioRepository.existsById(anuncioId)) {
            anuncioRepository.updateAnuncioDisponivel(anuncioId, LocalDate.now().toString());
            return new Mensagem("Anuncio Atualizado");
        }else {
            return new Mensagem("Anuncio n??o existe");
        }
    }

    @PutMapping("/{pCodigo}")
	@Transactional
	public ResponseEntity<Anuncio> atualizar(@PathVariable Integer pCodigo, @RequestBody Anuncio anuncioAtualizado) {
        logger.trace("Executing atualizar");

		if (!anuncioRepository.existsById(pCodigo)) {
			return ResponseEntity.notFound().build();
		}

		anuncioAtualizado.setId(pCodigo);

		Anuncio anuncioSalvo = anuncioRepository.save(anuncioAtualizado);

		return ResponseEntity.ok(anuncioSalvo);
	}
	
	
	@DeleteMapping("/{pCodigo}")
	public ResponseEntity<Void> remover(@PathVariable Integer pCodigo) {
        logger.trace("Executing remover");
		if (!anuncioRepository.existsById(pCodigo)) {
			return ResponseEntity.notFound().build();
		}

		anuncioRepository.deleteById(pCodigo);

		return ResponseEntity.noContent().build();
	}
}
