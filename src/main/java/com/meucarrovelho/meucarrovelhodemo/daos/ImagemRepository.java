package com.meucarrovelho.meucarrovelhodemo.daos;

import java.util.ArrayList;

import com.meucarrovelho.meucarrovelhodemo.model.Imagem;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ImagemRepository extends CrudRepository<Imagem, Integer>{
    @Query(value = "select * from imagens where anuncio = :anuncioId", nativeQuery = true)
    public ArrayList<Imagem> getImagensByAnuncio(@Param("anuncioId") int anuncioId); 
}
