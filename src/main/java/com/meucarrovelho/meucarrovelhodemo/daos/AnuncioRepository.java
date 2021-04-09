package com.meucarrovelho.meucarrovelhodemo.daos;

import com.meucarrovelho.meucarrovelhodemo.model.Anuncio;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface AnuncioRepository extends CrudRepository<Anuncio, Integer>{
    @Modifying
    @Transactional
    @Query(value = "update anuncio set visualizacoes = visualizacoes + 1 where id = :anuncioId", nativeQuery = true)
    public void updateAnuncioVisualizacao(@Param("anuncioId") int anuncioId);
}
