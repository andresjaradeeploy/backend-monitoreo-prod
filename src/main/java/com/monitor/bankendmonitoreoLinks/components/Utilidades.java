package com.monitor.bankendmonitoreoLinks.components;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
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

	public int extraerMesFecha(Date fecha) {

		LocalDate localDate = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int year = localDate.getYear();
		int mes = localDate.getMonthValue();

		System.out.println(year);
		System.out.println(mes);
		return mes;
	}

	public static void main(String[] args) {
		Utilidades utilidades = new Utilidades();
//	utilidades.guardarImageneUrl("https://external.fbog11-1.fna.fbcdn.net/safe_image.php?d=AQGHMQWQHA7ekSdl&url=https%3A%2F%2Fstatic.iris.net.co%2Ffinanzas%2Fupload%2Fimages%2F2017%2F9%2F15%2F73790_1.jpg&ext=emg0&_nc_oe=6efa3&_nc_sid=64c8fc&ccb=3-5&_nc_hash=AQGBC8_611rjZX2w","imagentest2");
	try {
		Process process = Runtime.getRuntime().exec("ping www.Pharos.sh.com");
		printResults(process);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public static void printResults(Process process) throws IOException {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	    String line = "";
	    while ((line = reader.readLine()) != null) {
	        System.out.println(line);
	    }
	    }

	public void guardarImageneUrl(String url,String nameImage) {
		try (InputStream in = new URL(url).openStream()) {
			Files.copy(in, Paths.get("C:/Users/1012421355/Desktop/images/"+nameImage+".jpg"));
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	

}
