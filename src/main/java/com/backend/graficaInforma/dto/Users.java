package com.backend.graficaInforma.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	@Column(name = "USERNAME")
	private String username;
	@Column(name = "PASSWORD")
	private String password;
	public String getPohenNumber() {
		return pohenNumber;
	}

	public void setPohenNumber(String pohenNumber) {
		this.pohenNumber = pohenNumber;
	}

	@Column(name = "PHONENUMBER")

	private String pohenNumber;

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

}
