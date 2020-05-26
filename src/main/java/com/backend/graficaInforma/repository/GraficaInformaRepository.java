package com.backend.graficaInforma.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backend.graficaInforma.dto.GraficaInforma;


@Repository
public interface GraficaInformaRepository extends CrudRepository<GraficaInforma, Long> {
	
	List<GraficaInforma> findByMarcacionAndAnioAndMesAndDia(String marcacion, String anio, String mes, String dia);
}
