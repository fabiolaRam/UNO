package com.backend.graficaInforma.dto;

public class SuscriptoresCounts {
	
	private String estado;
	private long suscriptores;
	
	public SuscriptoresCounts(String estado, long suscriptores) {
		this.estado = estado;
		this.suscriptores = suscriptores;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public long getSuscriptores() {
		return suscriptores;
	}

	public void setSuscriptores(long suscriptores) {
		this.suscriptores = suscriptores;
	}
	
}
