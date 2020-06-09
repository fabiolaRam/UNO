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
		System.out.println("servicio olvido de password.......");
		String estatus = "";
		System.out.println("TELEFONO:  "+telefono);
		if (telefono != null) {
			String password = Utilerias.generarPassword();
			System.out.println("actualiza contrasena con el telefono: " + telefono + " password: " + password);
			repository.updatePassword(u.encripta(password), telefono);
			System.out.println("se actualizaron los datos de contrasena correctamente...");
			(new Utilerias()).enviarSMS(Long.parseLong(telefono), "Tu nuevo password es: " + password);
			estatus = "La Contraseña fue enviada a tu telefono por medio de un mensaje de texto (SMS) ";
			
		} else {
			estatus = "El telefono proporcionado no es valido.";
			System.out.println("error al actualizar el password");
		}

		gson.toJson(estatus);
		return new ResponseEntity<>(gson.toJson(estatus), HttpStatus.OK);
	}

	@GetMapping("/backEGInforma/enviaClaveAcceso/{username}")
	private Boolean enviaClaveAcceso(@PathVariable String username) {
		System.out.println("servicio envia clave acceso.......");
		if (username != null) {
			System.out.println("buscando usuario: " + username);
			List<Users> usuario = repository.findByUsername(username);
			System.out.println("se encontro datos: " + usuario.toString());
			Long telefono = Long.valueOf(usuario.get(0).getPhoneNumber());
			if (telefono != null) {
				System.out.println("generando token para el telefono: " + telefono);
				String clave = Utilerias.generarToken();
				System.out.println("la nueva la clave a actualizar es: " + clave);
				repository.updateClave(clave, String.valueOf(telefono));
				System.out.println("se actualizo la clave");
				String mensaje = "El token de acceso es: " + clave;
				System.out.println(".......... " + mensaje);
				(new Utilerias()).enviarSMS(telefono, mensaje);
				return true;
			}
		}
		return false;
	}


	
}
