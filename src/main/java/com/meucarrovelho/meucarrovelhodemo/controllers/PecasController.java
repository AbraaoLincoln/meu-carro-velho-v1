package com.meucarrovelho.meucarrovelhodemo.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.meucarrovelho.meucarrovelhodemo.daos.PecaRepository;
import com.meucarrovelho.meucarrovelhodemo.model.Peca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(path = "/api")
public class PecasController {
    @Autowired
    private PecaRepository pecaRepository;

    @GetMapping(path = "/{carro}")
    public Iterable<Peca> getAllPecasByCarro(@PathVariable("carro") int carro) {
        return pecaRepository.getPecaByCarro(carro);
    }
    
    @GetMapping(path = "/{carro}/{tipoPeca}")
    public Iterable<Peca> getAllPecasByCarroAndTipo(@PathVariable("carro") int carro, @PathVariable("tipoPeca") String tipoPeca) {
        return pecaRepository.getPecaByCarroAndType(carro, tipoPeca);
    }
}
