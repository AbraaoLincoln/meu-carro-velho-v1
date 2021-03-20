package com.meucarrovelho.meucarrovelhodemo.util;

import java.util.ArrayList;

public class Mensagem {
    private String status;
    private ArrayList<String> listOfErros;

    public Mensagem(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<String> getListOfErros() {
        return listOfErros;
    }

    public void setListOfErros(ArrayList<String> listOfErros) {
        this.listOfErros = listOfErros;
    }
}
