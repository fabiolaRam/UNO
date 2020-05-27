package com.backend.graficaInforma.utilerias;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import org.jsmpp.bean.BindType;
import org.jsmpp.bean.ESMClass;
import org.jsmpp.bean.GeneralDataCoding;
import org.jsmpp.bean.NumberingPlanIndicator;
import org.jsmpp.bean.RegisteredDelivery;
import org.jsmpp.bean.SMSCDeliveryReceipt;
import org.jsmpp.bean.TypeOfNumber;
import org.jsmpp.session.BindParameter;
import org.jsmpp.session.SMPPSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

	public Date obtieneHora() {
		Date hora = Calendar.getInstance().getTime();
		hora.getTime();
		return hora;
	}

	@SuppressWarnings("deprecation")
	public Date horaLimite() {
		Date limite = Calendar.getInstance().getTime();
		limite.setHours(19);
		limite.setMinutes(10);
		limite.setSeconds(00);
		return limite;
	}

	public Integer enviarSMS(Long telefono, String mensaje) {
		try {

			SMPPSession session = new SMPPSession();
			session.connectAndBind("10.201.18.134", 8305, new BindParameter(BindType.BIND_TX, "INFORSA", "inSva22", "",
					TypeOfNumber.ALPHANUMERIC, NumberingPlanIndicator.UNKNOWN, null));

			session.setEnquireLinkTimer(30 * 1000);
			session.setTransactionTimer(60 * 1000);
			String Id = session.submitShortMessage("", TypeOfNumber.ALPHANUMERIC, NumberingPlanIndicator.UNKNOWN,
					"UNONOTICIAS", TypeOfNumber.NATIONAL, NumberingPlanIndicator.ISDN, telefono.toString(),
					new ESMClass(), (byte) 0, (byte) 0, null, null, new RegisteredDelivery(SMSCDeliveryReceipt.DEFAULT),
					(byte) 0, new GeneralDataCoding(), (byte) 0, mensaje.getBytes());
			session.unbindAndClose();
			System.out.println("Se envio el sms al Telefono: " + telefono + " con Id: " + Id);
			return 0;
		} catch (Exception exc) {
			System.out.printf("Error al enviar el sms", exc);
			return -1;
		}
	}

	public static String generarToken() {
		String alfabeto = "ABCDEFGHIJKLMNPQRSTUVWXYZ123456789";
		StringBuilder token = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			int aleatorio = (int) (Math.random() * alfabeto.length());
			token.append(alfabeto.charAt(aleatorio));
		}
		System.out.println("token: " + token.toString());
		return token.toString();
	}

	public static String generarPassword() {
		String alfabeto = "ABCDEFGHIJKLMNPQRSTUVWXYZ123456789";
		StringBuilder token = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			int aleatorio = (int) (Math.random() * alfabeto.length());
			token.append(alfabeto.charAt(aleatorio));
		}
		return token.toString();
	}
	
	public String encripta(String texto) {
		BCryptPasswordEncoder s =new BCryptPasswordEncoder();
		return s.encode(texto);
	}
}
