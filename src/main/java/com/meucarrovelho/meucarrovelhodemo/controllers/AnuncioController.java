package com.meucarrovelho.meucarrovelhodemo.controllers;

import java.time.LocalDate;
import java.util.ArrayList;

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
import com.meucarrovelho.meucarrovelhodemo.model.Usuario;
import com.meucarrovelho.meucarrovelhodemo.util.Mensagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/anuncio")
public class AnuncioController {
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

    @PostMapping()
    public Mensagem saveAnuncio(@RequestBody Anuncio novoAnuncio) {
        System.out.println("Salvando novo anuncio...");
        try {
            validateAnuncio(novoAnuncio);

            for(Peca p : novoAnuncio.getPecas()) {
                validatePeca(p);
            }

            novoAnuncio.setData(LocalDate.now().toString());
            novoAnuncio.setEstado("disponivel");
            novoAnuncio = anuncioRepository.save(novoAnuncio);

            for(Imagem img : novoAnuncio.getImagens()) {
                img.setSrc(novoAnuncio.getUsuario() + "/" + novoAnuncio.getId() + "/" + img.getSrc());
                img.setAnuncio(novoAnuncio.getId());
            }
            imagemRepository.saveAll(novoAnuncio.getImagens());

            for(Peca p : novoAnuncio.getPecas()) {
                p.setAnuncio(novoAnuncio.getId());
            }
            pecaRepository.saveAll(novoAnuncio.getPecas());

            return new Mensagem("Anuncio salvo com sucesso");
        } catch (BusinessException e) {
            Mensagem msg = new Mensagem("Error");
            msg.setListOfErros(e.getListOfErros());
            return msg;
        }
    }

    private void validateAnuncio(Anuncio anuncioToValidade) throws BusinessException{
        System.out.println("Validando o anuncio...");
        ArrayList<String> listOfErros = new ArrayList<>();
        Usuario user = usuarioRepository.findById(anuncioToValidade.getUsuario()).orElseGet(() -> null);

        if(user == null) {
            listOfErros.add("Usuario invalido");
        }

        if(anuncioToValidade.getPreco() < 0) {
            listOfErros.add("Preco do anuncio é invalido");
        }

        if(listOfErros.size() > 0) {
            throw new BusinessException(listOfErros);
        }
    }

    private void validatePeca(Peca pecaToValidade) throws BusinessException{
        System.out.println("Validando a peca...");
        ArrayList<String> listOfErros = new ArrayList<>();
        Carro carro = carroRepository.findById(pecaToValidade.getCarro()).orElseGet(() -> null);

        if(carro == null) {
            listOfErros.add("Modelo do carro invalido");
        }
        
        if(!(pecaToValidade.getEstado().equals("novo") || pecaToValidade.getEstado().equals("usado"))) {
            System.out.println("2");
            listOfErros.add("Estado da peca invalida, deve ser novo ou usado");
        }

        if(listOfErros.size() > 0) {
            throw new BusinessException(listOfErros);
        }
    }
}
