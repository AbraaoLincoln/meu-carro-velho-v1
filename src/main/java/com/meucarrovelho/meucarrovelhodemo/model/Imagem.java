package com.meucarrovelho.meucarrovelhodemo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "imagens")
public class Imagem {
    private String src;
    private int anuncio;

    @Id
    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public int getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(int anuncio) {
        this.anuncio = anuncio;
    } 
}
