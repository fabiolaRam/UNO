package com.backend.graficaInforma.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="INFORMA_ACCESO")
public class UsuarioInforma implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1646113020826944093L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Long idUsuario;
	private String usuario;
	private String password;
	private String permiso;
	private String usuarioReg;
	private String token;
	private String telefono;
	private String solicitud;
	private int aviso;
	
	public UsuarioInforma() {
		super();
	}
	
	public UsuarioInforma(String usuario, String contrasena, String permisos, String usuarioReg, String token, String telefono, String solicitud, int aviso) {
		this.usuario = usuario;
		this.password = contrasena;
		this.permiso = permisos;
		this.usuarioReg = usuarioReg;
		this.token = token;
		this.telefono = telefono;
		this.solicitud = solicitud;
		this.aviso = aviso;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}



	public String getUsuarioReg() {
		return usuarioReg;
	}

	public void setUsuarioReg(String usuarioReg) {
		this.usuarioReg = usuarioReg;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(String solicitud) {
		this.solicitud = solicitud;
	}

	public int getAviso() {
		return aviso;
	}

	public void setAviso(int aviso) {
		this.aviso = aviso;
	}

	@Override
	public String toString() {
		return "UsuarioInforma [idUsuario=" + idUsuario + ", usuario=" + usuario + ", contrasena=" + password
				+ ", permisos=" + permiso + ", usuarioReg=" + usuarioReg + ", token=" + token + ", telefono="
				+ telefono + ", solicitud=" + solicitud + ", aviso=" + aviso + "]";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPermiso() {
		return permiso;
	}

	public void setPermiso(String permiso) {
		this.permiso = permiso;
	}
	
}
