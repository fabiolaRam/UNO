package com.backend.graficaInforma.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backend.graficaInforma.dto.CatalogoEstados;

@Repository
public interface CatalogoEstadoRepository  extends CrudRepository<CatalogoEstados, Long> {

}
