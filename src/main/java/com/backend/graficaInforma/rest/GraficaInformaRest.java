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
import com.backend.graficaInforma.repository.GraficaInformaRepository;

@RestController
@CrossOrigin(origins = {"http://localhost:4200","http://10.191.190.15:7777", "http://10.191.190.9:7777", "http://www.informa.telcel.com:5000"})
public class GraficaInformaRest {
	
	@Autowired
	GraficaInformaRepository repository;

	@GetMapping("/backEGInforma/GraficaInformaRest/{fecha}")
	public List<Object> getFecha(@PathVariable String fecha) {
		
		String dia = fecha.substring(0, 2);
		String mes = fecha.substring(3, 5);
		String anio = fecha.substring(6,10);
		
		List<GraficaInforma> lista26 = buscarPorMarcacion("2226", anio, mes, dia);
		List<GraficaInforma> lista27 = buscarPorMarcacion("2227", anio, mes, dia);
		
		Map<Object, Object> mapa26 = lista26.stream().sorted((a,b)->a.getHora().compareTo(b.getHora())).collect(Collectors.toMap(x->x.getHora(), x->x.getCantidad()));
		Map<Object, Object> mapa27 = lista27.stream().sorted((a,b)->a.getHora().compareTo(b.getHora())).collect(Collectors.toMap(x->x.getHora(), x->x.getCantidad()));
		
		addPuts(mapa26);
		addPuts(mapa27);
		
		List<Object> mapas = new ArrayList<Object>();
		mapas.add(mapa26);
		mapas.add(mapa27);

		return mapas;
	}
	
	public List<GraficaInforma> buscarPorMarcacion(String marcacion, String anio, String mes, String dia){
		List<GraficaInforma> gi = new ArrayList<GraficaInforma>();
		for (GraficaInforma grafica : repository.findByMarcacionAndAnioAndMesAndDia(marcacion, anio, mes, dia)) { 
			gi.add(grafica);
		}
		if(gi.size()>0 ) {
			System.out.println("Se encontraron registros");	
		}else {
			System.out.println("no hay registros!");
		}
		return gi;
	}
	
	public Map<Object, Object> addPuts(Map<Object, Object> mapa){
		for (int i = 1; i < 25; i++) {
			if ((mapa.containsKey((i<10)?"0"+String.valueOf(i):String.valueOf(i))) == false) {
				mapa.put((i<10)?"0"+String.valueOf(i):String.valueOf(i), 0);
			} 
		}
		return mapa;
	}

}
