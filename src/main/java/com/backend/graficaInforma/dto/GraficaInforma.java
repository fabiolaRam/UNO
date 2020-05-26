package com.backend.graficaInforma.dto;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class GraficaInforma implements Serializable {

	private static final long serialVersionUID = 2510128880720820517L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Long idinforma;
	private String codigo;
	private String marcacion;
	private int cantidad;
	private String anio;
	private String mes;
	private String dia;
	private String hora;

	public GraficaInforma() {
	}

	public GraficaInforma(String codigo, String marcacion, int cantidad, String anio, String mes, String dia,
			String hora) {
		// TODO Auto-generated constructor stub
		this.codigo = codigo;
		this.marcacion = marcacion;
		this.cantidad = cantidad;
		this.anio = anio;
		this.mes = mes;
		this.dia = dia;
		this.hora = hora;
	}

	public Long getId() {
		return idinforma;
	}

	public void setId(Long id) {
		this.idinforma = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMarcacion() {
		return marcacion;
	}

	public void setMarcacion(String marcacion) {
		this.marcacion = marcacion;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	@Override
	public String toString() {
		return "GraficaInforma [id=" + idinforma + ", codigo=" + codigo + ", marcacion=" + marcacion + ", cantidad=" + cantidad
				+ ", anio=" + anio + ", mes=" + mes + ", dia=" + dia + ", hora=" + hora + "]";
	}

}
