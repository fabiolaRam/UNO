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
@CrossOrigin(origins = { "http://localhost:4200", "http://10.191.190.15:7777", "http://10.191.190.9:7777", "http://intranet.telcel.com:9045" })
public class EnvioSMS {

	@Autowired
	private UsersRepository repository;

	Utilerias u = new Utilerias();
	Gson gson = new Gson();

	@GetMapping("/olvidoPassword/{telefono}")
	public ResponseEntity<String> olvidoPassword(@PathVariable String telefono) {
		String estatus = "";

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

	@GetMapping("/enviaClaveAcceso/{telefono}")
	private Boolean enviaClaveAcceso(@PathVariable Long telefono) {
		if (telefono != null) {
			String clave = Utilerias.generarToken();
			repository.updateClave(clave, String.valueOf(telefono));
			String mensaje = "El token de acceso es: " + clave;
			(new Utilerias()).enviarSMS(telefono, mensaje);
			return true;
		}
		return false;
	}

	@GetMapping("/validaClaveAcceso/{usuario}&{clave}")
	private Boolean validaClave(@PathVariable String usuario, @PathVariable String clave) {
		List<Users> l = new ArrayList<Users>();
		l = repository.findByUsernameAndClave(usuario, clave);
		System.out.println(l.toString());
		if (!l.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
}
