package com.backend.graficaInforma.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.backend.graficaInforma.dto.GraficaInforma;
import com.backend.graficaInforma.dto.SuscriptoresCounts;
import com.backend.graficaInforma.repository.CatalogoEstadoRepository;
import com.backend.graficaInforma.repository.GraficaInformaRepository;
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
	
	@Autowired
	GraficaInformaRepository gRepository;

	@GetMapping("/backEGInforma/grafica/{fecha}")
	public List<Object> getFecha(@PathVariable String fecha) {
		String anio = fecha.substring(0, 4);
		String mes = fecha.substring(5, 7);
		String dia = fecha.substring(8, 10);
		System.out.println("dia: " + dia + " mes: " + mes + " anio: " + anio);
		System.out.println("servicio GraficaInformaRest/" + fecha);
		System.out.println("busca por marcacion 26 y 27");
		List<GraficaInforma> lista26 = buscarPorMarcacion("2226", anio, mes, dia);
		System.out.println(lista26);
		List<GraficaInforma> lista27 = buscarPorMarcacion("2227", anio, mes, dia);
		System.out.println(lista27);
		System.out.println("creando mapas");
		Map<Object, Object> mapa26 = lista26.stream().sorted((a, b) -> a.getHora().compareTo(b.getHora()))
				.collect(Collectors.toMap(x -> x.getHora(), x -> x.getCantidad()));
		Map<Object, Object> mapa27 = lista27.stream().sorted((a, b) -> a.getHora().compareTo(b.getHora()))
				.collect(Collectors.toMap(x -> x.getHora(), x -> x.getCantidad()));

		addPuts(mapa26);
		addPuts(mapa27);
		System.out.println("termiando mapas");
		List<Object> mapas = new ArrayList<Object>();
		mapas.add(mapa26);
		mapas.add(mapa27);
		System.out.println("enviando informacion: " + mapas.toString());
		return mapas;
	}

	public List<GraficaInforma> buscarPorMarcacion(String marcacion, String anio, String mes, String dia) {
		System.out.println(marcacion + " " + anio + " " + mes + " " + dia);
		List<GraficaInforma> gi = new ArrayList<GraficaInforma>();
		for (GraficaInforma grafica : gRepository.findByMarcacionAndAnioAndMesAndDia(marcacion, anio, mes, dia)) {
			gi.add(grafica);
		}
		if (gi.size() > 0) {
			System.out.println("Se encontraron registros");
		} else {
			System.out.println("no hay registros!");
		}
		return gi;
	}

	public Map<Object, Object> addPuts(Map<Object, Object> mapa) {
		for (int i = 1; i < 25; i++) {
			if ((mapa.containsKey((i < 10) ? "0" + String.valueOf(i) : String.valueOf(i))) == false) {
				mapa.put((i < 10) ? "0" + String.valueOf(i) : String.valueOf(i), 0);
			}
		}
		return mapa;
	}
}
