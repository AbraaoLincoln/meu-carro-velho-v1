package com.meucarrovelho.meucarrovelhodemo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/home")
public class ViewController {
    @GetMapping()
    public String getHome() {
        System.out.println("Teste");
        return "Sprint1 de WEB2";
    }
}
