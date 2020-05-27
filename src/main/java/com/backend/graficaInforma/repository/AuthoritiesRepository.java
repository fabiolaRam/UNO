package com.backend.graficaInforma.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backend.graficaInforma.dto.Authorities;

@Repository
public interface AuthoritiesRepository extends CrudRepository<Authorities, Long> {

}
