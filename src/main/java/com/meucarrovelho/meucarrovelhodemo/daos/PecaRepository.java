package com.meucarrovelho.meucarrovelhodemo.daos;

import com.meucarrovelho.meucarrovelhodemo.model.Peca;

import org.springframework.data.repository.CrudRepository;

public interface PecaRepository extends CrudRepository<Peca, Integer>{
    
}
