package com.backend.graficaInforma.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.graficaInforma.dto.Users;

@Repository
public interface UsersRepository extends CrudRepository<Users, Long> {

	@Transactional
	@Modifying
	@Query("Update Users u SET u.password=:password WHERE u.phoneNumber=:phoneNumber")
	public void updatePassword(@Param("password") String password, @Param("phoneNumber") String phoneNumber);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("Update Users u SET u.clave=:clave WHERE u.phoneNumber=:phoneNumber")
	public void updateClave(@Param("clave") String clave, @Param("phoneNumber") String phoneNumber);

	List<Users> findByUsernameAndClave(String username, String clave);
	
	List<Users> findByUsername(String username);
}
