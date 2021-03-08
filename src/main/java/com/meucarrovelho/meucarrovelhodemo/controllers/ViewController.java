package com.meucarrovelho.meucarrovelhodemo.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(path = "/anuncio")
public class ViewController {
    @GetMapping(path = "/{modelo}")
    public String getHome(@PathVariable String modelo) {
        System.out.println("Teste");
        return "Sprint1 de WEB2\n peças do carro " + modelo;
    }
}
