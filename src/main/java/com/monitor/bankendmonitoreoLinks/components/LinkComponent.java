package com.monitor.bankendmonitoreoLinks.components;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import com.monitor.bankendmonitoreoLinks.components.implement.AlertaImp;
import com.monitor.bankendmonitoreoLinks.components.implement.CorreoAlertaImp;
import com.monitor.bankendmonitoreoLinks.components.implement.EstadoAnuncioImp;
import com.monitor.bankendmonitoreoLinks.entity.monitor.CorreoAlerta;
import com.monitor.bankendmonitoreoLinks.entity.monitor.Estado;
import com.monitor.bankendmonitoreoLinks.entity.monitor.EstadoAnuncio;

@Component
public class LinkComponent {

	Log logObject = new Log("logs");
	Logger log = logObject.getLogger();

	public static String obtenerContenido(String sURL) throws IOException {

		URL url = new URL(sURL);
		URLConnection urlCon = url.openConnection();
		BufferedReader in = null;

		if (urlCon.getHeaderField("Content-Encoding") != null
				&& urlCon.getHeaderField("Content-Encoding").equals("gzip")) {
			in = new BufferedReader(new InputStreamReader(new GZIPInputStream(urlCon.getInputStream())));
		} else {
			in = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
		}

		String inputLine;
		StringBuilder sb = new StringBuilder();

		while ((inputLine = in.readLine()) != null)
			sb.append(inputLine);
		in.close();

		return sb.toString();
	}

	public int revisarLink(EstadoAnuncio estadoAnuncio) throws Exception {
		EstadoAnuncioImp estadosAnuncioImp = new EstadoAnuncioImp();
		AlertaImp alertaImp = new AlertaImp();
		Estado estado = new Estado();
		AlertaComponent alertaComponent = new AlertaComponent();
		Utilidades utilidades = new Utilidades();
		HttpURLConnection connection = null;
		try {
			/*URL u = new URL(estadoAnuncio.getAnuncio().getAdCreative().getLink());
			connection = (HttpURLConnection) u.openConnection();
			connection.setRequestMethod("HEAD");
			int code = connection.getResponseCode();*/
			Jsonp revisarUrl= new Jsonp();
			int code =revisarUrl.codeStatus(estadoAnuncio.getAnuncio().getAdCreative().getLink());
			if (code == 200) {

				if (estadoAnuncio.getCode() != 200 && code == 200) {

					String fechaSubida = utilidades.generarHoraActual();

					ArrayList<String> correos = new ArrayList<String>();
					CorreoAlertaImp correoAlertaImp = new CorreoAlertaImp();
					List<CorreoAlerta> dirCorreos = new ArrayList<>();
					dirCorreos = correoAlertaImp
							.correosByCuenta(estadoAnuncio.getAnuncio().getCuentaFB().getIdCuenta());
					for (CorreoAlerta correoAlerta : dirCorreos) {
						correos.add(correoAlerta.getCuentaCorreo());
					}
					alertaComponent.enviarAlertaUp(correos,
							"Link de Anuncio caido" + estadoAnuncio.getAnuncio().getIdAnuncio(),
							"Se envía correo para reportar subida de link", fechaSubida, code,estadoAnuncio);
				}

				Jsonp jsonp = new Jsonp();
				jsonp.getInfHtml(estadoAnuncio.getAnuncio().getAdCreative().getLink());
				estadoAnuncio.setMetaDescription(jsonp.getMetaDescription());
				estadoAnuncio.setTitle(jsonp.getTitle());
				estadoAnuncio.setCode(code);
				estadoAnuncio.setMensaje("OK");

				estado.setIdEstado(1);

				estadosAnuncioImp.actualizar(estadoAnuncio, estado);
				
			} else if (code == 429) {

				Jsonp jsonp = new Jsonp();
				jsonp.getInfHtml(estadoAnuncio.getAnuncio().getAdCreative().getLink());
				estadoAnuncio.setMetaDescription(jsonp.getMetaDescription());
				estadoAnuncio.setTitle(jsonp.getTitle());
				estadoAnuncio.setCode(code);
				estadoAnuncio.setMensaje("Muchas Peticiones al servidor");

				estado.setIdEstado(1);
				estadosAnuncioImp.actualizar(estadoAnuncio, estado);
				// colocar si existe alerta
			} else if (code == 400) {

				Jsonp jsonp = new Jsonp();
				jsonp.getInfHtml(estadoAnuncio.getAnuncio().getAdCreative().getLink());
				estadoAnuncio.setMetaDescription(jsonp.getMetaDescription());
				estadoAnuncio.setTitle(jsonp.getTitle());
				estadoAnuncio.setCode(code);
				estadoAnuncio.setMensaje("Bad Request");

				estado.setIdEstado(2);
				estadosAnuncioImp.actualizar(estadoAnuncio, estado);
				String fechaCaida = utilidades.generarHoraActual();

				alertaImp.generarAlerta(estadoAnuncio, fechaCaida);

				ArrayList<String> correos = new ArrayList<String>();
				CorreoAlertaImp correoAlertaImp = new CorreoAlertaImp();
				List<CorreoAlerta> dirCorreos = new ArrayList<>();
				dirCorreos = correoAlertaImp.correosByCuenta(estadoAnuncio.getAnuncio().getCuentaFB().getIdCuenta());
				for (CorreoAlerta correoAlerta : dirCorreos) {
					correos.add(correoAlerta.getCuentaCorreo());
				}
				alertaComponent.enviarAlertaDown(correos,
						"Link de Anuncio caido" + estadoAnuncio.getAnuncio().getIdAnuncio(),
						"Se envia correo para reportar caida de link", fechaCaida, estadoAnuncio);

			} else if (code == 404) {

				Jsonp jsonp = new Jsonp();
				jsonp.getInfHtml(estadoAnuncio.getAnuncio().getAdCreative().getLink());
				estadoAnuncio.setMetaDescription(jsonp.getMetaDescription());
				estadoAnuncio.setTitle(jsonp.getTitle());
				estadoAnuncio.setCode(code);
				estadoAnuncio.setMensaje("Not Found");

				estado.setIdEstado(2);
				estadosAnuncioImp.actualizar(estadoAnuncio, estado);
				String fechaCaida = utilidades.generarHoraActual();
				alertaImp.generarAlerta(estadoAnuncio, fechaCaida);

				ArrayList<String> correos = new ArrayList<String>();
				CorreoAlertaImp correoAlertaImp = new CorreoAlertaImp();
				List<CorreoAlerta> dirCorreos = new ArrayList<>();
				dirCorreos = correoAlertaImp.correosByCuenta(estadoAnuncio.getAnuncio().getCuentaFB().getIdCuenta());
				for (CorreoAlerta correoAlerta : dirCorreos) {
					correos.add(correoAlerta.getCuentaCorreo());
				}
				alertaComponent.enviarAlertaDown(correos,
						"Link de Anuncio caido" + estadoAnuncio.getAnuncio().getIdAnuncio(),
						"Se envia correo para reportar caida de link", fechaCaida, estadoAnuncio);
			} else if (code == 301) {

				Jsonp jsonp = new Jsonp();
				jsonp.getInfHtml(estadoAnuncio.getAnuncio().getAdCreative().getLink());
				estadoAnuncio.setMetaDescription(jsonp.getMetaDescription());
				estadoAnuncio.setTitle(jsonp.getTitle());
				estadoAnuncio.setCode(code);
				estadoAnuncio.setMensaje("Moved Permanently - Se redireccionó");

				estado.setIdEstado(2);
				estadosAnuncioImp.actualizar(estadoAnuncio, estado);
				String fechaCaida = utilidades.generarHoraActual();
				alertaImp.generarAlerta(estadoAnuncio, fechaCaida);

				ArrayList<String> correos = new ArrayList<String>();
				CorreoAlertaImp correoAlertaImp = new CorreoAlertaImp();
				List<CorreoAlerta> dirCorreos = new ArrayList<>();
				dirCorreos = correoAlertaImp.correosByCuenta(estadoAnuncio.getAnuncio().getCuentaFB().getIdCuenta());
				for (CorreoAlerta correoAlerta : dirCorreos) {
					correos.add(correoAlerta.getCuentaCorreo());
				}
				if(estadoAnuncio.getAnuncio().getAdCreative().getLink() !="http://fb.me/" && estadoAnuncio.getAnuncio().getAdCreative().getLink()!="https://api.whatsapp.com/send")
				alertaComponent.enviarAlertaDown(correos,
						"Moved Permanently - Se redireccionó a su nueva url (https u otra)"
								+ estadoAnuncio.getAnuncio().getIdAnuncio(),
						"Se envia correo para reportar caida de link", fechaCaida, estadoAnuncio);
			} else {
				Jsonp jsonp = new Jsonp();
				jsonp.getInfHtml(estadoAnuncio.getAnuncio().getAdCreative().getLink());
				estadoAnuncio.setMetaDescription(jsonp.getMetaDescription());
				estadoAnuncio.setTitle(jsonp.getTitle());
				estadoAnuncio.setCode(code);
				estadoAnuncio.setMensaje("Url no funciona");

				estado.setIdEstado(2);
				estadosAnuncioImp.actualizar(estadoAnuncio, estado);
				String fechaCaida = utilidades.generarHoraActual();

				alertaImp.generarAlerta(estadoAnuncio, fechaCaida);

				ArrayList<String> correos = new ArrayList<String>();
				CorreoAlertaImp correoAlertaImp = new CorreoAlertaImp();
				List<CorreoAlerta> dirCorreos = new ArrayList<>();
				dirCorreos = correoAlertaImp.correosByCuenta(estadoAnuncio.getAnuncio().getCuentaFB().getIdCuenta());
				for (CorreoAlerta correoAlerta : dirCorreos) {
					correos.add(correoAlerta.getCuentaCorreo());
				}
				alertaComponent.enviarAlertaDown(correos,
						"Link de Anuncio caido" + estadoAnuncio.getAnuncio().getIdAnuncio(),
						"Se envia correo para reportar caida de link", fechaCaida, estadoAnuncio);

			}
			return code;

		} catch (MalformedURLException e) {

			System.err.println("url dañado" + e);
			log.error("Error al leer url" + e);
		} catch (IOException e) {
			log.error("Error" + e);
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return 0;

	}

	public static String peticionHttpGet(String urlParaVisitar) throws Exception {

		StringBuilder resultado = new StringBuilder();

		URL url = new URL(urlParaVisitar);

		HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
		conexion.setRequestProperty("Content-Type", "application/json");
		conexion.setRequestProperty("Accept", "application/json");
		conexion.setRequestMethod("GET");

		BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
		String linea;

		while ((linea = rd.readLine()) != null) {

			resultado.append(linea);

		}

		rd.close();
		return resultado.toString();
	}

}
