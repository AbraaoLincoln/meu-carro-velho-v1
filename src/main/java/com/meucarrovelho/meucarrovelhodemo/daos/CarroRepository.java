package com.meucarrovelho.meucarrovelhodemo.daos;

import com.meucarrovelho.meucarrovelhodemo.model.Carro;

import org.springframework.data.repository.CrudRepository;

public interface CarroRepository extends CrudRepository<Carro, Integer>{
    
}
