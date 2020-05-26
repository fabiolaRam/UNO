package com.backend.graficaInforma;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GraficaInformaApplication {
	
	private static final Logger log = LoggerFactory.getLogger(GraficaInformaApplication.class);
		
	public static void main(String[] args) {
		SpringApplication.run(GraficaInformaApplication.class, args);
		log.info("iniciando...");
		
		
	}
}
