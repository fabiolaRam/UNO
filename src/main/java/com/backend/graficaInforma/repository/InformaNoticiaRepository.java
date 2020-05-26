package com.backend.graficaInforma.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backend.graficaInforma.dto.InformaNoticia;

@Repository
public interface InformaNoticiaRepository extends CrudRepository<InformaNoticia, Long> {

	List<InformaNoticia> findByFechaBetween(String fechaInicio, String fechaFin);
	
}
