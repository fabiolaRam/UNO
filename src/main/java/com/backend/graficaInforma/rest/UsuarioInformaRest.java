package com.backend.graficaInforma.rest;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.backend.graficaInforma.dto.UsuarioInforma;
import com.backend.graficaInforma.repository.UsuarioInformaRepository;
import com.google.gson.Gson;

@RestController
@CrossOrigin(origins = {"http://localhost:4200","http://10.191.190.15:7777", "http://10.191.190.9:7777", "http://intranet.telcel.com:9045"})
public class UsuarioInformaRest {
	
	@Autowired
	UsuarioInformaRepository repository;
	
	Gson gson = new Gson();
	
	@GetMapping("/registraUsuario/{usuario}&{contrasena}&{telefono}&{permisos}")
	public ResponseEntity<String> registrarUsuario(@PathVariable("usuario") String usuario, @PathVariable("contrasena") String contrasena,
			@PathVariable("telefono") String telefono, @PathVariable("permisos") String permisos){
		String estatus = "";
		UsuarioInforma usu =  new UsuarioInforma();
		usu.setUsuario(usuario);
		usu.setPassword(contrasena);
		usu.setPermiso(permisos);
		usu.setUsuarioReg("");
		usu.setToken("");
		usu.setTelefono(telefono);
		usu.setSolicitud(String.valueOf(new Date()));
		usu.setAviso(1);
		try {
			repository.save(usu);
			estatus = "Se registro correctamente el usuario";
			System.out.println("usuario: " + repository.findAll() );
			gson.toJson(estatus);
		}catch(Exception e) {
			estatus = "Ocurrio un error al registrar el usuario " + e;
			gson.toJson(estatus);
		}
		return new ResponseEntity<>(gson.toJson(estatus), HttpStatus.OK);
	}

}
