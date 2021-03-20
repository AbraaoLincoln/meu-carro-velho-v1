package com.meucarrovelho.meucarrovelhodemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "peca")
public class Peca {
    private int id;
    private String tipo;
    private String estado;
    private int anuncio;
    private int carro;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(int anuncio) {
        this.anuncio = anuncio;
    }

    public int getCarro() {
        return carro;
    }

    public void setCarro(int carro) {
        this.carro = carro;
    }
}
