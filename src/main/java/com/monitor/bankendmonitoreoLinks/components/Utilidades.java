package com.monitor.bankendmonitoreoLinks.components;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONObject;

public class Utilidades {
	
	public JSONObject stringToJSON(String cadena)
	{
		
		cadena.replace("[", "").replace("]", "");
		cadena.trim();
		JSONObject jsonObject= new JSONObject(cadena);
		return jsonObject;
	}
	
	public String generarHoraActual()
	{
		SimpleDateFormat formateador = new SimpleDateFormat("EE dd' de 'MMMM 'del' yyyy  hh:mm:ss:a "  , new Locale("ES"));
		   Date fechaDate = new Date();
		   String fecha = formateador.format(fechaDate);
		   //System.out.println(fecha);
		   return fecha;
	}

}
