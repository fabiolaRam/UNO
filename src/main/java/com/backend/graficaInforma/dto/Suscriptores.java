package com.backend.graficaInforma.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="SUSCRIPTORES")
public class Suscriptores  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6725555030920230977L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Long id;
	private String telefono;
	private String fecha;
	private int region;
	private int tecnologia;
	private int plataforma;
	private String estado;
	
	public Suscriptores(String telefono, String fecha, int region, int tecnologia, int plataforma, String estado) {
		this.telefono = telefono;
		this.fecha = fecha;
		this.region = region;
		this.tecnologia = tecnologia;
		this.plataforma = plataforma;
		this.estado = estado;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getRegion() {
		return region;
	}

	public void setRegion(int region) {
		this.region = region;
	}

	public int getTecnologia() {
		return tecnologia;
	}

	public void setTecnologia(int tecnologia) {
		this.tecnologia = tecnologia;
	}

	public int getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(int plataforma) {
		this.plataforma = plataforma;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
