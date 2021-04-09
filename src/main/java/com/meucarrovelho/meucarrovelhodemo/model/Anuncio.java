package com.meucarrovelho.meucarrovelhodemo.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "anuncio")
public class Anuncio {
    private int id;
    private String data;
    private float preco;
    private String estado;
    private String descricao;
    private int usuario;
    private int visualizacoes;
    private String disponivel;
    private PecasCarro pecas;
    private ArrayList<Imagem> imagens;

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getUsuario() {
        return usuario;
    }
    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getVisualizacoes() {
        return visualizacoes;
    }

    public void setVisualizacoes(int visualizacoes) {
        this.visualizacoes = visualizacoes;
    }

    public String getDisponivel() {
        return this.disponivel;
    }

    public void setDisponivel(String data) {
        this.disponivel = data;
    }

    @Transient
    public PecasCarro getPecas() {
        return pecas;
    }
    @Transient
    public void setPecas(PecasCarro pecas) {
        this.pecas = pecas;
    }

    @Transient
    public ArrayList<Imagem> getImagens() {
        return imagens;
    }
    @Transient
    public void setImagens(ArrayList<Imagem> imagens) {
        this.imagens = imagens;
    }

    
}
