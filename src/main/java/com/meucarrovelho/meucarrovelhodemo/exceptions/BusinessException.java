package com.meucarrovelho.meucarrovelhodemo.exceptions;

import java.util.ArrayList;

public class BusinessException extends Exception{
    ArrayList<String> listOfErros;

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(ArrayList<String> listOfErros) {
        this.listOfErros = listOfErros;
    }

    public ArrayList<String> getListOfErros() {
        return this.listOfErros;
    }
}
