package com.monitor.bankendmonitoreoLinks.components;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import com.monitor.bankendmonitoreoLinks.components.implement.EstadoAnuncioImp;
import com.monitor.bankendmonitoreoLinks.components.implement.EstadoLinkExternoimp;
import com.monitor.bankendmonitoreoLinks.entity.monitor.EstadoAnuncio;
import com.monitor.bankendmonitoreoLinks.entity.monitor.EstadoLinkExterno;

@Component
public class MonitorComponent {
	Log logObject= new Log("logs");
	Logger log = logObject.getLogger();
	
	
	public void mainLinks() {
		
		Runnable runnable = new Runnable() {

			@Override
			public void run() {

				while (true) {
					try {

						Thread.sleep(300000);
						ApiMarketing apiMarketing = new ApiMarketing();
						apiMarketing.main();

						EstadoAnuncioImp estadosDeAnuncio = new EstadoAnuncioImp();
						LinkComponent linkComponent = new LinkComponent();

						List<EstadoAnuncio> urls = estadosDeAnuncio.obtener();

						for (EstadoAnuncio estadoAnuncios : urls) {
							linkComponent.revisarLink(estadoAnuncios);

						}

					} catch (Exception e) {
						System.err.println("error al revisar link" + e);
						log.error("error al revisar link"+e);
					}

				}
			}
		};

		Thread hilo = new Thread(runnable);
		hilo.start();
		System.out.println("Monitoreo ");

	}
	
public void mainLinksExternos() {
	Runnable runnable = new Runnable() {

		@Override
		public void run() {

			while (true) {
				try {

					Thread.sleep(300000);
				
					EstadoLinkExternoimp estadoLinkExternoimp = new EstadoLinkExternoimp();
					LinkComponent linkComponent = new LinkComponent();
					
					ApiMarketing apiMarketing = new ApiMarketing();
					apiMarketing.mainExternos();


					List<EstadoLinkExterno> urls = estadoLinkExternoimp.obtenerEstadosExternos();
					System.out.println("tamaño "+urls.size());
					for (EstadoLinkExterno estadoLinkExterno : urls) {
						linkComponent.revisarLinkExterno(estadoLinkExterno);
						System.out.println("url"+estadoLinkExterno.getLink_externo().getUrl());

					}

				} catch (Exception e) {
					System.err.println("error al revisar link" + e);
					log.error("error al revisar link"+e);
				}

			}
		}
	};

	Thread hilo = new Thread(runnable);
	hilo.start();
	System.out.println("Monitoreo ");
}


}
