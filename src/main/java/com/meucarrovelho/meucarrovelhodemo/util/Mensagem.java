package com.meucarrovelho.meucarrovelhodemo.util;

import java.util.ArrayList;

public class Mensagem {
    private String status;
    private ArrayList<String> listOfErros;
    private int anuncioId;

    public Mensagem() {}

    public Mensagem(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<String> getListOfErros() {
        return listOfErros;
    }

    public void setListOfErros(ArrayList<String> listOfErros) {
        this.listOfErros = listOfErros;
    }

    public int getAnuncioId() {
        return anuncioId;
    }

    public void setAnuncioId(int anuncioId) {
        this.anuncioId = anuncioId;
    }
}
