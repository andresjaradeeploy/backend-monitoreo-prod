package com.monitor.bankendmonitoreoLinks.components;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.monitor.bankendmonitoreoLinks.components.conector.Conector;
import com.monitor.bankendmonitoreoLinks.components.implement.EstadoAnuncioImp;
import com.monitor.bankendmonitoreoLinks.entity.monitor.Anuncio;
import com.monitor.bankendmonitoreoLinks.entity.monitor.EstadoAnuncio;



@Component
public class MonitorComponent {


	
	public void main() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {

				while (true) {
					try {

						Thread.sleep(30000);

						// System.out.println("prueba de monitoreo");
						EstadoAnuncioImp estadosDeAnuncio = new EstadoAnuncioImp();
						LinkComponent linkComponent= new LinkComponent();

						List<EstadoAnuncio> urls = estadosDeAnuncio.obtener();

						for (EstadoAnuncio estadoAnuncios : urls) {
							linkComponent.revisarLink(estadoAnuncios);

						}

					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
		};

		Thread hilo = new Thread(runnable);
		hilo.start();
		System.out.println("Monitoreo ");

	}

	
	public static void main(String[] args) {
		MonitorComponent monitorComponent = new MonitorComponent();
		List<EstadoAnuncio> estados= new EstadoAnuncioImp().obtener();
		System.out.println(estados);
		for (EstadoAnuncio estadoAnuncio : estados) {
			System.out.println("estados"+estadoAnuncio.getAnuncio().getLinkAnuncio());
			
		}
		
		monitorComponent.main();
		
	}
	

}
