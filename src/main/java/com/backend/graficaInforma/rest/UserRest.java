package com.backend.graficaInforma.rest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.backend.graficaInforma.dto.Authorities;
import com.backend.graficaInforma.dto.Users;
import com.backend.graficaInforma.repository.AuthoritiesRepository;
import com.backend.graficaInforma.repository.UsersRepository;
import com.backend.graficaInforma.utilerias.Utilerias;
import com.google.gson.Gson;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://10.191.190.15:7777", "http://10.191.190.9:7777",
		"http://www.informa.telcel.com:5000" })
public class UserRest {

	@Autowired
	UsersRepository uRepository;

	@Autowired
	AuthoritiesRepository aRepository;

	Gson gson = new Gson();
	Utilerias u = new Utilerias();

	@GetMapping("/backEGInforma/registraUsuario/{usuario}&{contrasena}&{telefono}&{permisos}")
	public ResponseEntity<String> registrarUsuario(@PathVariable("usuario") String usuario,
			@PathVariable("contrasena") String contrasena, @PathVariable("telefono") String telefono,
			@PathVariable("permisos") List<String> permisos) {
		System.out.println("servicio registraUsuario/" + usuario + "&" + contrasena + "&" + telefono + "&" + permisos);
		String estatus = "";

		Users usu = new Users();
		usu.setUsername(usuario);
		usu.setPassword(u.encripta(contrasena));
		usu.setPhoneNumber(telefono);
		usu.setUsuarioReg("");
		usu.setClave("");
		usu.setSolicitud(String.valueOf(new Date()));
		usu.setAviso(new BigDecimal(1));
		System.out.println("recorriendo lista de permisos");
		if (permisos != null) {
			for (String p : permisos) {
				guardaPermisos(usuario, p);
			}
		}
		try {
			System.out.println("datos de usuario: " + usu.toString());
			uRepository.save(usu);

			estatus = "Se registro correctamente el usuario";
			gson.toJson(estatus);
		} catch (Exception e) {
			estatus = "Ocurrio un error al registrar el usuario " + e;
			gson.toJson(estatus);
		}
		System.out.println("estatus: " + estatus);
		return new ResponseEntity<>(gson.toJson(estatus), HttpStatus.OK);
	}

	private void guardaPermisos(String usuario, String p) {
		Authorities aut = new Authorities();
		if (p != null) {
			aut.setUsername(usuario);
			aut.setAuthority(p);
			System.out.println("guardando en tabla authorities: " + aut.toString());
			aRepository.save(aut);
		}
	}

}
