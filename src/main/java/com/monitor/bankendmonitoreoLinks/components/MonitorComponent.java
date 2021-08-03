package com.monitor.bankendmonitoreoLinks.components;

import java.util.List;
import org.springframework.stereotype.Component;
import com.monitor.bankendmonitoreoLinks.components.implement.EstadoAnuncioImp;
import com.monitor.bankendmonitoreoLinks.entity.monitor.EstadoAnuncio;

@Component
public class MonitorComponent {

	public void main() {
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
					}

				}
			}
		};

		Thread hilo = new Thread(runnable);
		hilo.start();
		System.out.println("Monitoreo ");

	}

}
