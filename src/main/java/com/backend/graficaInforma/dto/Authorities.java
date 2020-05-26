package com.backend.graficaInforma.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="AUTHORITIES")
public class Authorities implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8767518584152674937L;

	@Id
	@Column(name="ID")
	private Long id;
	
	@Column(name="USERNAME")
	private String username;
	@Column(name="AUTHORITY")
	private String authority;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	
}
