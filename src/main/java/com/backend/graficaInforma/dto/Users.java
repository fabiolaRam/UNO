package com.backend.graficaInforma.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class Users implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4094220278947553138L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name ="ID_USUARIO")
	private Long idUsuario;
	@Column(name ="USERNAME")
	private String username;
	@Column(name ="PASSWORD")
	private String password;
	@Column(name ="PHONENUMBER")
	private String phoneNumber;
	private String usuarioReg; 
	private String clave;
	private String solicitud;
	private int aviso;
	
	public Users() {
		super();
	}
	
	public Users(String username, String password, String phoneNumber, String usuarioReg, String token, String solicitud, int aviso) {
		this.username = username;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.usuarioReg = usuarioReg;
		this.clave = token;
		this.solicitud = solicitud;
		this.aviso = aviso;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUsuarioReg() {
		return usuarioReg;
	}

	public void setUsuarioReg(String usuarioReg) {
		this.usuarioReg = usuarioReg;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String token) {
		this.clave = token;
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
		return "Users [idUsuario=" + idUsuario + ", username=" + username + ", password=" + password + ", phoneNumber="
				+ phoneNumber + ", usuarioReg=" + usuarioReg + ", clave=" + clave + ", solicitud=" + solicitud
				+ ", aviso=" + aviso + "]";
	}
}
