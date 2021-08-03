package com.monitor.bankendmonitoreoLinks.components;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.json.JSONObject;

public class Utilidades {

	public JSONObject stringToJSON(String cadena) {

		cadena.replace("[", "").replace("]", "");
		cadena.trim();
		JSONObject jsonObject = new JSONObject(cadena);
		return jsonObject;
	}

	public String generarHoraActual2() {
		SimpleDateFormat formateador = new SimpleDateFormat("EE dd' de 'MMMM 'del' yyyy  hh:mm:ss:a ",
				new Locale("es", "ES"));
		Date fechaDate = new Date();
		String fecha = formateador.format(fechaDate);
		System.out.println(fecha);
		return fecha;
	}

	public String generarHoraActual() {
		String place = "America/Bogota";
		String hora = null;
		// String place = "GMT-8";
		java.util.TimeZone zone = java.util.TimeZone.getTimeZone(place);
		if (zone.getID() == "GMT") {
			for (String st : TimeZone.getAvailableIDs()) {
				System.out.println(st);
			}

		} else {
			java.util.Calendar calendar = java.util.Calendar.getInstance(zone);
			java.text.DateFormat format = java.text.DateFormat.getDateTimeInstance();
			format.setCalendar(calendar);
			hora = format.format(new java.util.Date());
		}

		return hora;

	}

}
