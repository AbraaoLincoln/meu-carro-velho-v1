package com.meucarrovelho.meucarrovelhodemo.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.meucarrovelho.meucarrovelhodemo.daos.PecaRepository;
import com.meucarrovelho.meucarrovelhodemo.model.Peca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.MDC;

@RestController
@RequestMapping(path = "/api")
public class PecasController {
    private static final Logger logger = LoggerFactory.getLogger(PecasController.class);
    @Autowired
    private PecaRepository pecaRepository;

    @GetMapping(path = "/{carro}")
    public Iterable<Peca> getAllPecasByCarro(@PathVariable("carro") int carro) {
        logger.trace("Executing getAllPecasByCarro");
        return pecaRepository.getPecaByCarro(carro);
    }
    
    @GetMapping(path = "/{carro}/{tipoPeca}")
    public Iterable<Peca> getAllPecasByCarroAndTipo(@PathVariable("carro") int carro, @PathVariable("tipoPeca") String tipoPeca) {
        logger.trace("Executing getAllPecasByCarroAndTipo");
        return pecaRepository.getPecaByCarroAndType(carro, tipoPeca);
    }
}
