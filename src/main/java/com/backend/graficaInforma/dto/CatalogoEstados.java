package com.backend.graficaInforma.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CatalogoEstados implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7606462844531395572L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Long idEstado;
	private int codigo;
	private String estado;
	
	public CatalogoEstados() {
		
	}
	
	public CatalogoEstados(int codigo, String estado) {
		this.codigo = codigo;
		this.estado = estado;
	}
	
	public Long getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return "CatalogoEstados [idEstado=" + idEstado + ", codigo=" + codigo + ", estado=" + estado + "]";
	}

}
