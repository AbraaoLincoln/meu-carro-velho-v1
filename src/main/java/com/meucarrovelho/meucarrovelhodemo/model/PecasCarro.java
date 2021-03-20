package com.meucarrovelho.meucarrovelhodemo.model;

import java.util.ArrayList;


public class PecasCarro {
    private int carro;
    private ArrayList<Peca> pecas;

    public int getCarro() {
        return carro;
    }

    public void setCarro(int carro) {
        this.carro = carro;
    }

    public ArrayList<Peca> getPecas() {
        return pecas;
    }

    public void setPecas(ArrayList<Peca> pecas) {
        this.pecas = pecas;
    }    
}
