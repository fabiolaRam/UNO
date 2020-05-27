package com.backend.graficaInforma.rest;


import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.backend.graficaInforma.dto.InformaNoticia;
import com.backend.graficaInforma.repository.InformaNoticiaRepository;
import com.backend.graficaInforma.utilerias.Utilerias;
import com.google.gson.Gson;

@RestController
@CrossOrigin(origins = {"http://localhost:4200","http://10.191.190.15:7777", "http://10.191.190.9:7777", "http://intranet.telcel.com:9045"})
public class InformaNoticiaRest {

	@Autowired
	InformaNoticiaRepository repository;

	InformaNoticia noticia = new InformaNoticia();
	Utilerias u = new Utilerias();

	@GetMapping("/guardarNoticiaRegular/{mensaje}")
	public ResponseEntity<String> guardarNoticiaRegular(@PathVariable String mensaje) {
		String estatus = guardarNoticiaNoche(mensaje, "1");
		Gson gson = new Gson();
		gson.toJson(estatus);
		
		return new ResponseEntity<>(gson.toJson(estatus), HttpStatus.OK);
	}

	@SuppressWarnings("null")
	@GetMapping("/guardarNoticiaRxEstado/{mensaje}/{estado}")
	public ResponseEntity<String> guardarNoticiaRxEstado(@PathVariable("mensaje") String mensaje, @PathVariable("estado") List<String> estado) {
		String estatus = null;
		if (estado != null || !estado.isEmpty()) {
			for (String e : estado) {
				String n = "1" + e;
				estatus = guardarNoticiaTarde(mensaje, n);
			}
		}
		Gson gson = new Gson();
		gson.toJson(estatus);
		
		return new ResponseEntity<>(gson.toJson(estatus), HttpStatus.OK);
	}

	@GetMapping("/guardarNoticiaUrgente/{mensaje}")
	public ResponseEntity<String> guardarNoticiaUrgente(@PathVariable String mensaje) {
		String estatus = guardarNoticiaNoche(mensaje, "2");
		Gson gson = new Gson();
		gson.toJson(estatus);
		
		return new ResponseEntity<>(gson.toJson(estatus), HttpStatus.OK);
	}

	@SuppressWarnings("null")
	@GetMapping("/guardarNoticiaUxEstado/{mensaje}/{estado}")
	public ResponseEntity<String> guardarNoticiaUxEstado(@PathVariable("mensaje") String mensaje, @PathVariable("estado") List<String> estado) {
		String estatus = null;
		if (estado != null || !estado.isEmpty()) {
			for (String e : estado) {
				String n = "2" + e;
				estatus = guardarNoticiaTarde(mensaje, n);
			}
		}
		Gson gson = new Gson();
		gson.toJson(estatus);
		
		return new ResponseEntity<>(gson.toJson(estatus), HttpStatus.OK);
	}

	@SuppressWarnings("null")
	private String guardarNoticiaTarde(String mensaje, String estado) {
		LocalTime limite = LocalTime.of(18, 00, 00);
		LocalTime actual = LocalTime.now();

		if (actual.isAfter(limite)) {
			return "La hora limite de registro de noticias por estado son las 2:00 PM, por lo que esta noticia no puede ser registrada.";
		} else {
			if (mensaje != null || !mensaje.isEmpty()) {
				noticia.setMensaje(mensaje);
				noticia.setFecha(u.obtieneFecha());
				noticia.setEstatus("PE");
				noticia.setTipo(estado);
				noticia.setAcceso("usuario");
				noticia.setIp(u.obtieneIp());
				noticia.setHora(String.valueOf(u.obtieneHora()));
				repository.save(noticia);
				return "Se registro correctamente la noticia";
			} else {
				return "Ocurrio un error al registrar la noticia";
			}
		}
	}
	
	@SuppressWarnings("null")
	private String guardarNoticiaNoche(String mensaje, String estado) {
		LocalTime limite = LocalTime.of(14, 10, 00);
		LocalTime actual = LocalTime.now();

		if (actual.isAfter(limite)) {
			return "La hora limite de registro de noticias es a las 19:10 PM, por lo que esta noticia no puede ser registrada.";
		} else {
			if (mensaje != null || !mensaje.isEmpty()) {
				noticia.setMensaje(mensaje);
				noticia.setFecha(u.obtieneFecha());
				noticia.setEstatus("PE");
				noticia.setTipo(estado);
				noticia.setAcceso("usuario");
				noticia.setIp(u.obtieneIp());
				noticia.setHora(String.valueOf(u.obtieneHora()));
				repository.save(noticia);

				return "Se registro correctamente la noticia";
			} else {
				return "Ocurrio un error al registrar la noticia";
			}
		}
	}

	@GetMapping("/historicoNoticia/{fechaInicio}/{fechaFin}")
	public List<InformaNoticia> historicoNoticia(@PathVariable("fechaInicio") String fechaInicio,
			@PathVariable("fechaFin") String fechaFin) {
		List<InformaNoticia> historico = repository.findByFechaBetween(fechaInicio, fechaFin);
		return historico;
	}

}
