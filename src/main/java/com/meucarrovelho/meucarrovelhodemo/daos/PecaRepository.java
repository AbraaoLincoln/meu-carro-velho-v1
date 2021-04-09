package com.meucarrovelho.meucarrovelhodemo.daos;

import java.util.ArrayList;

import com.meucarrovelho.meucarrovelhodemo.model.Peca;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PecaRepository extends CrudRepository<Peca, Integer>{
    @Query(value = "select * from peca where carro = :carroId", nativeQuery = true)
    public ArrayList<Peca> getPecaByCarro(@Param("carroId") int carroId); 
    
    @Query(value = "select * from peca where carro = :carroId and tipo = :tipoPeca", nativeQuery = true)
    public ArrayList<Peca> getPecaByCarroAndType(@Param("carroId") int carroId, @Param("tipoPeca") String tipoPeca); 

    @Query(value = "select * from peca where anuncio = :anuncioId", nativeQuery = true)
    public ArrayList<Peca> getPecaByAnuncio(@Param("anuncioId") int anuncioId);

}
