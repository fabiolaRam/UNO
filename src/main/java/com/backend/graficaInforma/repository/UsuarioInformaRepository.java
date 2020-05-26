package com.backend.graficaInforma.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backend.graficaInforma.dto.UsuarioInforma;

@Repository
public interface UsuarioInformaRepository extends CrudRepository<UsuarioInforma, Long> {

}
