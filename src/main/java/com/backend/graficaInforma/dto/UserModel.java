package com.backend.graficaInforma.dto;

import java.io.Serializable;
import java.util.List;

public class UserModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2668152001817637951L;
	String username;
	String token;
	List<String> autorities;
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
	public List<String> getAutorities() {
		return autorities;
	}
	public void setAutorities(List<String> autorities) {
		this.autorities = autorities;
	}
	
	
}
