package com.meucarrovelho.meucarrovelhodemo.daos;

import java.util.ArrayList;

import com.meucarrovelho.meucarrovelhodemo.model.Peca;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PecaRepository extends CrudRepository<Peca, Integer>{
    @Query(value = "select * from peca where carro = :carroId", nativeQuery = true)
    public ArrayList<Peca> getPecaByCarro(@Param("carroId") int carroId);  
}
