package com.meucarrovelho.meucarrovelhodemo.daos;

import com.meucarrovelho.meucarrovelhodemo.model.Anuncio;
import org.springframework.data.repository.CrudRepository;


public interface AnuncioRepository extends CrudRepository<Anuncio, Integer>{
    
}
