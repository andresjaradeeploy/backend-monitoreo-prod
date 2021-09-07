package com.monitor.bankendmonitoreoLinks.components;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import com.monitor.bankendmonitoreoLinks.components.implement.AdCreativeImp;
import com.monitor.bankendmonitoreoLinks.components.implement.AnuncioImp;
import com.monitor.bankendmonitoreoLinks.components.implement.EstadoAnuncioImp;
import com.monitor.bankendmonitoreoLinks.entity.monitor.Anuncio;
import com.monitor.bankendmonitoreoLinks.entity.monitor.EstadoAnuncio;

@Component
public class ApiMarketing {
	
	
	Log logObject= new Log("logs");
	Logger log = logObject.getLogger();

	public List<Anuncio> ObteneryGuardarAnuncios() {

		// cuentaFBImp.guardarCuentas();

		List<Anuncio> anuncios = new AnuncioImp().obtenerAnunciosInf();

		return anuncios;
	}

	public List<EstadoAnuncio> obteneryGuardarEstadoAnuncios() {

		EstadoAnuncioImp estadoAnuncioImp = new EstadoAnuncioImp();

		List<EstadoAnuncio> estados = new ArrayList<>();
		List<Anuncio> anuncios = new AnuncioImp().listarAnuncios();
		for (Anuncio ads : anuncios) {
			EstadoAnuncio estadoAnuncio = new EstadoAnuncio();

			estados.add(estadoAnuncio);
			boolean ifExists = estadoAnuncioImp.verificarSiExisteEstadoAnuncio(ads.getIdAnuncio());
			if (ifExists == false) {
				estadoAnuncioImp.guardar(estadoAnuncio, ads);

			}

			else
				System.out.println("Ya existe se debe actualizar estado");
			
			log.warn("Ya existe estado de anuncio con id de anuncio: "+ads.getIdAnuncio());
		}
		return estados;

	}


	public void main() {
		AdCreativeImp adCreativeImp = new AdCreativeImp();
		adCreativeImp.obtenerAdCreativesInf();
		ObteneryGuardarAnuncios();

		obteneryGuardarEstadoAnuncios();

	}

}
