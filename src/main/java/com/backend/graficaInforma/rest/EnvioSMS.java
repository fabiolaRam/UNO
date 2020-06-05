package com.backend.graficaInforma.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.backend.graficaInforma.dto.Users;
import com.backend.graficaInforma.repository.UsersRepository;
import com.backend.graficaInforma.utilerias.Utilerias;
import com.google.gson.Gson;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://10.191.190.15:7777", "http://10.191.190.9:7777",
		"http://www.informa.telcel.com:5000" })
public class EnvioSMS {

	@Autowired
	private UsersRepository repository;

	Utilerias u = new Utilerias();
	Gson gson = new Gson();

	@GetMapping("/backEGInforma/olvidoPassword/{telefono}")
	public ResponseEntity<String> olvidoPassword(@PathVariable String telefono) {
		String estatus = "";
		System.out.println("TELEFONO:  "+telefono);
		if (telefono != null) {
			String password = Utilerias.generarPassword();
			repository.updatePassword(u.encripta(password), telefono);
			(new Utilerias()).enviarSMS(Long.parseLong(telefono), "Tu nuevo password es: " + password);
			estatus = "La Contraseña fue enviada a tu telefono por medio de un mensaje de texto (SMS) ";
		} else {
			estatus = "El telefono proporcionado no es valido.";
		}

		gson.toJson(estatus);
		return new ResponseEntity<>(gson.toJson(estatus), HttpStatus.OK);
	}

	@GetMapping("/backEGInforma/enviaClaveAcceso/{username}")
	private Boolean enviaClaveAcceso(@PathVariable String username) {
		if (username != null) {
			List<Users> usuario = repository.findByUsername(username);
			Long telefono = Long.valueOf(usuario.get(0).getPhoneNumber());
			if (telefono != null) {
				String clave = Utilerias.generarToken();
				repository.updateClave(clave, String.valueOf(telefono));
				String mensaje = "El token de acceso es: " + clave;
				System.out.println(".......... " + mensaje);
				(new Utilerias()).enviarSMS(telefono, mensaje);
				return true;
			}
		}
		return false;
	}


	
}
