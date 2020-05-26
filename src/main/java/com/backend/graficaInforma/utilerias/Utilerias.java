package com.backend.graficaInforma.utilerias;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Utilerias {
	
	public String obtieneIp() {
		InetAddress address = null;
		try {
			address = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return address.getHostAddress();
	}
	
	public String obtieneFecha() {
		LocalDate fecha = LocalDate.now();
		return String.valueOf(fecha);
	}
	
	@SuppressWarnings("deprecation")
	public Date obtieneHora() {
		Date hora = Calendar.getInstance().getTime();
		hora.getTime();
		return hora;
	}

	public Date horaLimite() {
		Date limite = Calendar.getInstance().getTime();
		limite.setHours(19);
		limite.setMinutes(10);
		limite.setSeconds(00);
		return limite;
	}
}
