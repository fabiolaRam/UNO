package com.backend.graficaInforma.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="INFORMA_NOTICIA")
public class InformaNoticia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5782282525517689962L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Long id;
	private String mensaje;
	private String fecha;
	private String estatus;
	private String tipo;
	private String acceso;
	private String ip;
	private String hora;
	
	public InformaNoticia() {

	}
	
	public InformaNoticia(String mensaje, String fecha, String estatus, String tipo, String acceso, String ip, String hora) {
		this.mensaje = mensaje;
		this.fecha = fecha;
		this.estatus = estatus;
		this.tipo = tipo;
		this.acceso = acceso;
		this.ip = ip;
		this.hora = hora;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getAcceso() {
		return acceso;
	}

	public void setAcceso(String acceso) {
		this.acceso = acceso;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	@Override
	public String toString() {
		return "InformaNoticia [id=" + id + ", mensaje=" + mensaje + ", fecha=" + fecha + ", estatus=" + estatus
				+ ", tipo=" + tipo + ", acceso=" + acceso + ", ip=" + ip + ", hora=" + hora + "]";
	}
}
