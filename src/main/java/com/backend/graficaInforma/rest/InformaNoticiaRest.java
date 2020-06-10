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
@CrossOrigin(origins = {"http://localhost:4200","http://10.191.190.15:7777", "http://10.191.190.9:7777", "http://www.informa.telcel.com:5000"})
public class InformaNoticiaRest {

	@Autowired
	InformaNoticiaRepository repository;

	InformaNoticia noticia = new InformaNoticia();
	Utilerias u = new Utilerias();

	@GetMapping("/backEGInforma/guardarNoticiaRegular/{mensaje}")
	public ResponseEntity<String> guardarNoticiaRegular(@PathVariable String mensaje) {
		System.out.println("servicio guardarNoticiaRegular/" + mensaje);
		String estatus = guardarNoticiaNoche(mensaje, "1");
		System.out.println("estatus: " + estatus);
		Gson gson = new Gson();
		gson.toJson(estatus);
		
		return new ResponseEntity<>(gson.toJson(estatus), HttpStatus.OK);
	}

	@SuppressWarnings("null")
	@GetMapping("/backEGInforma/guardarNoticiaRxEstado/{mensaje}/{estado}")
	public ResponseEntity<String> guardarNoticiaRxEstado(@PathVariable("mensaje") String mensaje, @PathVariable("estado") List<String> estado) {
		System.out.println("servicio guardarNoticiaRxEstado/" + mensaje + "/" + estado);
		String estatus = null;
		if (estado != null || !estado.isEmpty()) {
			System.out.println("lista de noticias...");
			for (String e : estado) {
				String n = "1" + e;
				estatus = guardarNoticiaTarde(mensaje, n);
			}
			System.out.println("estatus: " + estatus);
		}
		Gson gson = new Gson();
		gson.toJson(estatus);
		
		return new ResponseEntity<>(gson.toJson(estatus), HttpStatus.OK);
	}

	@GetMapping("/backEGInforma/guardarNoticiaUrgente/{mensaje}")
	public ResponseEntity<String> guardarNoticiaUrgente(@PathVariable String mensaje) {
		System.out.println("servicio guardarNoticiaUrgente/" +  mensaje);
		String estatus = guardarNoticiaNoche(mensaje, "2");
		System.out.println("estatus: " +  estatus);
		Gson gson = new Gson();
		gson.toJson(estatus);
		
		return new ResponseEntity<>(gson.toJson(estatus), HttpStatus.OK);
	}

	@SuppressWarnings("null")
	@GetMapping("/backEGInforma/guardarNoticiaUxEstado/{mensaje}/{estado}")
	public ResponseEntity<String> guardarNoticiaUxEstado(@PathVariable("mensaje") String mensaje, @PathVariable("estado") List<String> estado) {
		System.out.println("servicio guardarNoticiaUxEstado/" + mensaje + "/" + estado);
		String estatus = null;
		if (estado != null || !estado.isEmpty()) {
			System.out.println("recorriendo lista de estados");
			for (String e : estado) {
				String n = "2" + e;
				estatus = guardarNoticiaTarde(mensaje, n);
			}
			System.out.println("estatus: " +  estatus);
		}
		Gson gson = new Gson();
		gson.toJson(estatus);
		
		return new ResponseEntity<>(gson.toJson(estatus), HttpStatus.OK);
	}

	@SuppressWarnings("null")
	private String guardarNoticiaTarde(String mensaje, String estado) {
		System.out.println("metodo... guardarNoticiaTarde mensaje: " + mensaje + " estado: " + estado );
//		LocalTime limite = LocalTime.of(14, 00, 00);
		LocalTime limite = LocalTime.of(20, 00, 00);
		LocalTime actual = LocalTime.now();

		if (actual.isAfter(limite)) {
			System.out.println("validando hora limite : " + limite);
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
				System.out.println("guardando noticia: " + noticia.toString());
				repository.save(noticia);
				System.out.println("se guardo la noticia correctamente");
				return "Se registro correctamente la noticia";
			} else {
				System.out.println("hubo un error al guardar la noticia");
				return "Ocurrio un error al registrar la noticia";
			}
		}
	}
	
	@SuppressWarnings("null")
	private String guardarNoticiaNoche(String mensaje, String estado) {
		System.out.println("metodo guardarNoticiaNoche mensaje: " + mensaje + " estado: " + estado);
		LocalTime limite = LocalTime.of(19, 10, 00);
		LocalTime actual = LocalTime.now();
		
		if (actual.isAfter(limite)) {
			System.out.println("validando hora hasta " + limite);
			return "La hora limite de registro de noticias es a las 19:10 PM, por lo que esta noticia no puede ser registrada.";
		} else {
			System.out.println("guardando mensaje");
			if (mensaje != null || !mensaje.isEmpty()) {
				noticia.setMensaje(mensaje);
				noticia.setFecha(u.obtieneFecha());
				noticia.setEstatus("PE");
				noticia.setTipo(estado);
				noticia.setAcceso("usuario");
				noticia.setIp(u.obtieneIp());
				noticia.setHora(String.valueOf(u.obtieneHora()));
				System.out.println("guardando noticia. " + noticia.toString());
				repository.save(noticia);
				System.out.println("se guardo correctamente en bd");
				return "Se registro correctamente la noticia";
			} else {
				System.out.println("hubo un error al guardar la noticia");
				return "Ocurrio un error al registrar la noticia";
			}
		}
	}

	@GetMapping("/backEGInforma/historicoNoticia/{fechaInicio}/{fechaFin}")
	public List<InformaNoticia> historicoNoticia(@PathVariable("fechaInicio") String fechaInicio,
			@PathVariable("fechaFin") String fechaFin) {
		System.out.println("servicio historicoNoticia/" + fechaInicio + "/" + fechaFin);
		System.out.println("buscando noticias....");
		List<InformaNoticia> historico = repository.findByFechaBetween(fechaInicio, fechaFin);
		System.out.println("noticias...");
		System.out.println(historico);
		return historico;
	}

}
