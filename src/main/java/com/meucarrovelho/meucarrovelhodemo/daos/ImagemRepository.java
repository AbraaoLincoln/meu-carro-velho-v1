package com.meucarrovelho.meucarrovelhodemo.daos;

import com.meucarrovelho.meucarrovelhodemo.model.Imagem;

import org.springframework.data.repository.CrudRepository;

public interface ImagemRepository extends CrudRepository<Imagem, Integer>{
    
}
