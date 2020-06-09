package com.backend.graficaInforma.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="AUTHORITIES")
//@Table (name="AUTHORITIES", schema="VI5ADMW")
public class Authorities implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8767518584152674937L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name="ID")
	private BigDecimal id;
	
	@Column(name="USERNAME")
	private String username;
	@Column(name="AUTHORITY")
	private String authority;
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
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
	@Override
	public String toString() {
		return "Authorities [id=" + id + ", username=" + username + ", authority=" + authority + "]";
	}
}
