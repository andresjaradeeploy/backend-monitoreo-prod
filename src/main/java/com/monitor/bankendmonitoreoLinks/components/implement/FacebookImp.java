package com.monitor.bankendmonitoreoLinks.components.implement;

import org.springframework.stereotype.Component;

import com.monitor.bankendmonitoreoLinks.components.LinkComponent;
import com.monitor.bankendmonitoreoLinks.components.Utilidades;

import com.monitor.bankendmonitoreoLinks.entity.monitor.CuentaFbDeveloper;

@Component
public class FacebookImp {
	private Utilidades utilidades = new Utilidades();

	private static CuentaFbDeveloper cuentaFbDeveloper = new CuentaFbDeveloper(1000001);

	private static CuentaFbDeveloperImp cuentaFbDeveloperImp = new CuentaFbDeveloperImp();

	public String apiGraph(String consulta) {

		cuentaFbDeveloperImp.consultar(cuentaFbDeveloper);
		String accessToken = (cuentaFbDeveloper.getAccessToken());
		String secretKey = (cuentaFbDeveloper).getSecretKey();
		String idAplication = (cuentaFbDeveloper).getIdAplicacion();
		String direccion = "https://graph.facebook.com";
		String versionapi = "v10.0";

		String url = direccion + "/" + versionapi + "/" + consulta + "&access_token=" + accessToken;
		String respuesta;
		try {
			respuesta = LinkComponent.peticionHttpGet(url);

			utilidades.stringToJSON(respuesta);

			return respuesta;

		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {

	}

}
