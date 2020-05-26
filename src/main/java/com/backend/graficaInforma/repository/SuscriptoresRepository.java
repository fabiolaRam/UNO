package com.backend.graficaInforma.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backend.graficaInforma.dto.Suscriptores;
import com.backend.graficaInforma.dto.SuscriptoresCounts;

@Repository
public interface SuscriptoresRepository extends CrudRepository<Suscriptores, Long> {
	
	@Query("select new com.backend.graficaInforma.dto.SuscriptoresCounts(estado, count(telefono) as sus)"
			+ " from Suscriptores"
			+ " GROUP BY estado")
	List<SuscriptoresCounts> findSuscriptoresCount();
}
