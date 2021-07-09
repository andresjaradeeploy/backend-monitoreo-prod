package com.monitor.bankendmonitoreoLinks.components.conector;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import net.htmlparser.jericho.*;
import java.util.*;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jboss.jandex.Main;
import org.jsoup.*;

import com.monitor.bankendmonitoreoLinks.components.LinkComponent;
import com.monitor.bankendmonitoreoLinks.components.implement.CuentaFbDeveloperImp;
import com.monitor.bankendmonitoreoLinks.entity.monitor.CuentaFbDeveloper;

import java.io.*;
import java.net.*;

public class Prueba {

	public static void main(String[] args) {
		
	
		SimpleDateFormat formateador = new SimpleDateFormat("EE dd' de 'MMMM 'del' yyyy  hh:mm:ss:a "  , new Locale("ES"));
				   Date fechaDate = new Date();
				   String fecha = formateador.format(fechaDate);
				   System.out.println(fecha);
	}
}
