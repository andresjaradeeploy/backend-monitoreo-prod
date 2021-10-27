package com.monitor.bankendmonitoreoLinks.components.implement;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.monitor.bankendmonitoreoLinks.components.LinkComponent;
import com.monitor.bankendmonitoreoLinks.components.Log;
import com.monitor.bankendmonitoreoLinks.components.Utilidades;
import com.monitor.bankendmonitoreoLinks.entity.monitor.CuentaFbDeveloper;

@Component
public class FacebookImp {
	private Utilidades utilidades = new Utilidades();
	private Log logObject = new Log("logs");
	private Logger log = logObject.getLogger();
	private static CuentaFbDeveloper cuentaFbDeveloperAds = new CuentaFbDeveloper(1000001);
	private static CuentaFbDeveloper cuentaFbDeveloperPages = new CuentaFbDeveloper(1000002);
	
	private static CuentaFbDeveloperImp cuentaFbDeveloperImp = new CuentaFbDeveloperImp();

	public String apiGraph(String consulta) {

		cuentaFbDeveloperImp.consultar(cuentaFbDeveloperAds);
		String accessToken = (cuentaFbDeveloperAds.getAccessToken());
		String direccion = "https://graph.facebook.com";
		String versionapi = "v12.0";

		String url = direccion + "/" + versionapi + "/" + consulta + "&access_token=" + accessToken;
		String respuesta;
		try {
			respuesta = LinkComponent.peticionHttpGet(url);

			utilidades.stringToJSON(respuesta);

			return respuesta;

		} catch (Exception e) {

			System.err.println("Se generó un error de respuesta por parte de Facebook"+e);
			log.error("Error al obtener propiedades del link: "+ url+"  "+e);
		}
		return null;
	}
	public String apiGraphPage(String consulta) {

		cuentaFbDeveloperImp.consultar(cuentaFbDeveloperPages);
		String accessToken = (cuentaFbDeveloperPages.getAccessToken());
		String direccion = "https://graph.facebook.com";
		String versionapi = "v12.0";

		String url = direccion + "/" + versionapi + "/" + consulta + "&access_token=" + accessToken;
		String respuesta;
		try {
			respuesta = LinkComponent.peticionHttpGet(url);

			utilidades.stringToJSON(respuesta);

			return respuesta;

		} catch (Exception e) {

			System.err.println("Se generó un error de respuesta por parte de Facebook"+e);
			log.error("Error al obtener propiedades del link: "+ url+"  "+e);
		}
		return null;
	}
	
	public static void main(String[] args) {
		FacebookImp imp = new FacebookImp();
		System.out.println("cuentas"+imp.apiGraphPage("16126780553?fields=picture,fan_count,link")); 
	}

}
