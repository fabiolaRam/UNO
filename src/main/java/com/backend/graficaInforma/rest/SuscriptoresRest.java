package com.backend.graficaInforma.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.graficaInforma.dto.SuscriptoresCounts;
import com.backend.graficaInforma.repository.CatalogoEstadoRepository;
import com.backend.graficaInforma.repository.SuscriptoresRepository;
import com.backend.graficaInforma.utilerias.Utilerias;


@RestController
@CrossOrigin(origins = {"http://localhost:4200","http://10.191.190.15:7777", "http://10.191.190.9:7777", "http://www.informa.telcel.com:5000"})

public class SuscriptoresRest {

	@Autowired
	public SuscriptoresRepository repository;
	
	@Autowired
	public CatalogoEstadoRepository catalogoRepository;
	
	Utilerias u = new Utilerias();

	@GetMapping("/backEGInforma/consultaSuscriptores")
	public List<SuscriptoresCounts> consultaSuscriptores(){
		System.out.println("servicio consultaSuscriptores");
		System.out.println("buscando suscriptores");
		List<SuscriptoresCounts> suscrip = repository.findSuscriptoresCount();
		System.out.println("datos...");
		System.out.println(suscrip.toString());
		return suscrip;
	}
}
