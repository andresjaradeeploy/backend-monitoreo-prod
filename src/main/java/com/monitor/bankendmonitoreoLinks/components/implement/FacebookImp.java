package com.monitor.bankendmonitoreoLinks.components.implement;



import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.monitor.bankendmonitoreoLinks.components.LinkComponent;
import com.monitor.bankendmonitoreoLinks.components.Utilidades;


import com.monitor.bankendmonitoreoLinks.entity.monitor.CuentaFbDeveloper;

@Component
public class FacebookImp {
	private Utilidades utilidades = new Utilidades();

	private static CuentaFbDeveloper cuentaFbDeveloper= new CuentaFbDeveloper(1000001);
	
	private static CuentaFbDeveloperImp cuentaFbDeveloperImp= new CuentaFbDeveloperImp();

	public String apiGraph(String consulta) {
		
		cuentaFbDeveloperImp.consultar(cuentaFbDeveloper);
		String accessToken = (cuentaFbDeveloper.getAccessToken());
		String secretKey = (cuentaFbDeveloper).getSecretKey();
		String idAplication = (cuentaFbDeveloper).getIdAplicacion();
		String direccion = "https://graph.facebook.com";
		String versionapi = "v10.0";

		String url = direccion + "/" + versionapi + "/"
				+consulta +"&access_token=" + accessToken;
		String respuesta;
		try {
			respuesta = LinkComponent.peticionHttpGet(url);
			//System.out.println((int) respuesta.trim().charAt(0));
			utilidades.stringToJSON(respuesta);
			//System.out.println(utilidades.stringToJSON(respuesta));
			return respuesta;

		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		FacebookImp facebook = new FacebookImp();
		facebook.apiGraph("me?fields=birthday%2Cfirst_name%2Cname%2Clast_name%2Clink%2Cpermissions");
		facebook.apiGraph("me?fields=adaccounts");
		facebook.apiMarketing("23843376072790480", "name%2Cadset%2Cconversion_domain%2Ccreated_time%2Clast_updated_by_app_id%2Cpreview_shareable_link%2Ceffective_status");

	}
	
	//en el parametro separar field con coma
	public JSONObject apiMarketing(String campana, String fields) {
		apiGraph(campana+"?fields="+fields);
		return null;
		
	}

}
