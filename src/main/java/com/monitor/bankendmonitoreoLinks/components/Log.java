package com.monitor.bankendmonitoreoLinks.components;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.*;

public class Log {
	
	Utilidades utilidades= new Utilidades();
	
	//String fecha= utilidades.generarHoraActual();
	
	Date fecha = new Date();
	
	private Logger logger;
	public Log(String workspace)  {
		logger = Logger.getLogger(Log.class);
		
		
		SimpleDateFormat formato = new SimpleDateFormat("dd.MM.yyyy");
		String fechaAc= formato.format(fecha);
		
		
		PatternLayout defaultLayout = new PatternLayout("%p:%d{HH:mm:ss}->%m%n");
		RollingFileAppender rollingFileAppender = new RollingFileAppender();
		
		try {
			rollingFileAppender.setFile(workspace+"//archivoLogger-"+fechaAc+".txt",true,false,0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rollingFileAppender.setLayout(defaultLayout);
		
		logger.removeAllAppenders();
		logger.addAppender(rollingFileAppender);
		logger.setAdditivity(false);
		
		
	}
	public Logger getLogger() {
		return logger;
	}
	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public static void main(String[] args) throws IOException {
		Log logObject= new Log("logs");
		Logger log = logObject.getLogger();
		log.info("texto");
		log.error("texto");
		log.warn("texto");
	}
	
}
