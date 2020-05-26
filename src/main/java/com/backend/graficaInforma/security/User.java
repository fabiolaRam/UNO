package com.backend.graficaInforma.security;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

	private String username;
	private String token;
	private List<String> permisos;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<String> getPermisos() {
		return permisos;
	}

	public void setPermisos(List<String> permisos) {
		this.permisos = permisos;
	}

}
