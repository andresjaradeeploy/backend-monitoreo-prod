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
import com.monitor.bankendmonitoreoLinks.components.implement.EstadoLinkExternoimp;
import com.monitor.bankendmonitoreoLinks.components.implement.PalabraImp;
import com.monitor.bankendmonitoreoLinks.dao.EstadoLinkFile;
import com.monitor.bankendmonitoreoLinks.entity.monitor.CorreoAlerta;
import com.monitor.bankendmonitoreoLinks.entity.monitor.Estado;
import com.monitor.bankendmonitoreoLinks.entity.monitor.EstadoAnuncio;
import com.monitor.bankendmonitoreoLinks.entity.monitor.EstadoLinkExterno;

@Component
public class LinkComponent {

	Log logObject = new Log("logs");
	Logger log = logObject.getLogger();
	private PalabraImp palabraImp = new PalabraImp();
	private Utilidades utilidades = new Utilidades();

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
			/*
			 * URL u = new URL(estadoAnuncio.getAnuncio().getAdCreative().getLink());
			 * connection = (HttpURLConnection) u.openConnection();
			 * connection.setRequestMethod("HEAD"); int code = connection.getResponseCode();
			 */
			Jsonp revisarUrl = new Jsonp();
			int code = revisarUrl.codeStatus(estadoAnuncio.getAnuncio().getAdCreative().getLink());
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
							"Link de Anuncio nuevamente arriba" + estadoAnuncio.getAnuncio().getIdAnuncio(),
							"Se envía correo para reportar subida de link", fechaSubida, code, estadoAnuncio);
				}
				Jsonp jsonp = new Jsonp();
				List<String> palabrasBusqueda = new ArrayList<String>();
				palabrasBusqueda = palabraImp.correosByAnuncio(estadoAnuncio.getAnuncio().getIdAnuncio());

				if (palabrasBusqueda != null) {

					for (String palabra : palabrasBusqueda) {
						String resultadoBusqueda = null;
						resultadoBusqueda = jsonp.search(estadoAnuncio.getAnuncio().getAdCreative().getLink(), palabra);

						if (estadoAnuncio.getResultadoBusquedaPalabras() == null) {
							estadoAnuncio.setResultadoBusquedaPalabras(resultadoBusqueda);

						} else {
							estadoAnuncio.setResultadoBusquedaPalabras(
									estadoAnuncio.getResultadoBusquedaPalabras() + " - " + resultadoBusqueda);
						}

					}
				}

				jsonp.getInfHtml(estadoAnuncio.getAnuncio().getAdCreative().getLink());
				estadoAnuncio.setMetaDescription(jsonp.getMetaDescription());
				estadoAnuncio.setTitle(jsonp.getTitle());
				estadoAnuncio.setCode(code);

				estadoAnuncio.setMensaje("OK");

				estado.setIdEstado(1);
				// actualizar imagen pendiente
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
				if (estadoAnuncio.getAnuncio().getAdCreative().getLink() != "http://fb.me/"
						&& estadoAnuncio.getAnuncio().getAdCreative().getLink() != "https://api.whatsapp.com/send")
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

	// externo

	public EstadoLinkFile obtenerEstadoLinkFile(String link, String palabra1, String palabra2) {

		EstadoLinkFile estadoLinkFile = new EstadoLinkFile();

		Estado estado = new Estado();
		HttpURLConnection connection = null;
		
		if (link!=null) {
			
		
		try {

			Jsonp revisarUrl = new Jsonp();
			int code = revisarUrl.codeStatus(link);
			System.out.println("code" + code);
			if (code == 200) {

				Jsonp jsonp = new Jsonp();

				List<String> palabrasBusqueda = new ArrayList<String>();

				palabrasBusqueda.add(palabra1);
				palabrasBusqueda.add(palabra2);

				if (palabrasBusqueda != null) {

					for (String palabra : palabrasBusqueda) {
						String resultadoBusqueda = null;
						resultadoBusqueda = jsonp.search(link, palabra);

						if (estadoLinkFile.getResultadoBusquedaPalabras() == null) {
							estadoLinkFile.setResultadoBusquedaPalabras(resultadoBusqueda);

						} else {
							estadoLinkFile.setResultadoBusquedaPalabras(
									estadoLinkFile.getResultadoBusquedaPalabras() + " - " + resultadoBusqueda);
						}

					}
				}

				jsonp.getInfHtml(link);
				estadoLinkFile.setMetaDescription(jsonp.getMetaDescription());
				estadoLinkFile.setTitle(jsonp.getTitle());
				estadoLinkFile.setCode(code);
				estadoLinkFile.setLink(link);
				estadoLinkFile.setMensaje("OK");
				estadoLinkFile.setEstado("Arriba");
				estado.setIdEstado(1);
				// actualizar imagen pendiente

			} else if (code == 429) {

				Jsonp jsonp = new Jsonp();
				jsonp.getInfHtml(link);
				estadoLinkFile.setMetaDescription(jsonp.getMetaDescription());
				estadoLinkFile.setTitle(jsonp.getTitle());
				estadoLinkFile.setCode(code);
				estadoLinkFile.setMensaje("Muchas Peticiones al servidor");
				estadoLinkFile.setLink(link);
				estadoLinkFile.setEstado("Arriba");

				// colocar si existe alerta
			} else if (code == 400) {

				Jsonp jsonp = new Jsonp();
				jsonp.getInfHtml(link);
				estadoLinkFile.setMetaDescription(jsonp.getMetaDescription());
				estadoLinkFile.setTitle(jsonp.getTitle());
				estadoLinkFile.setCode(code);
				estadoLinkFile.setMensaje("Bad Request");
				estadoLinkFile.setLink(link);
				estadoLinkFile.setEstado("Caido");

				estado.setIdEstado(2);

			} else if (code == 404) {

				Jsonp jsonp = new Jsonp();
				jsonp.getInfHtml(link);
				estadoLinkFile.setMetaDescription(jsonp.getMetaDescription());
				estadoLinkFile.setTitle(jsonp.getTitle());
				estadoLinkFile.setCode(code);
				estadoLinkFile.setMensaje("Not Found");
				estadoLinkFile.setLink(link);
				estadoLinkFile.setEstado("Caido");

				estado.setIdEstado(2);

			} else if (code == 301) {

				Jsonp jsonp = new Jsonp();
				jsonp.getInfHtml(link);
				estadoLinkFile.setMetaDescription(jsonp.getMetaDescription());
				estadoLinkFile.setTitle(jsonp.getTitle());
				estadoLinkFile.setCode(code);
				estadoLinkFile.setMensaje("Moved Permanently - Se redireccionó");
				estadoLinkFile.setLink(link);
				estadoLinkFile.setEstado("Caido");

				estado.setIdEstado(2);

			} else {
				Jsonp jsonp = new Jsonp();
				jsonp.getInfHtml(link);
				estadoLinkFile.setMetaDescription(jsonp.getMetaDescription());
				estadoLinkFile.setTitle(jsonp.getTitle());
				estadoLinkFile.setCode(code);
				estadoLinkFile.setMensaje("Url no funciona");
				estadoLinkFile.setLink(link);
				estadoLinkFile.setEstado("Caido");

				estado.setIdEstado(2);

			}

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
		}else {
			System.err.println("link invalido");
		}
		return estadoLinkFile;
	}

	public int revisarLinkExterno(EstadoLinkExterno estadoLinkExterno) throws Exception {
		System.out.println("sss");

		EstadoLinkExternoimp estadosAnuncioImp = new EstadoLinkExternoimp();
		AlertaImp alertaImp = new AlertaImp();
		Estado estado = new Estado();
		AlertaComponent alertaComponent = new AlertaComponent();
		Utilidades utilidades = new Utilidades();
		HttpURLConnection connection = null;
		try {

			Jsonp revisarUrl = new Jsonp();
			int code = revisarUrl.codeStatus(estadoLinkExterno.getLink_externo().getUrl());
			System.out.println("code" + code);
			if (code == 200) {

				if (estadoLinkExterno.getCode() != 200 && code == 200) {

					String fechaSubida = utilidades.generarHoraActual();

					ArrayList<String> correos = new ArrayList<String>();
					CorreoAlertaImp correoAlertaImp = new CorreoAlertaImp();
					List<CorreoAlerta> dirCorreos = new ArrayList<>();
					dirCorreos = correoAlertaImp.correosByCuenta(estadoLinkExterno.getLink_externo().getDescripcion());
					for (CorreoAlerta correoAlerta : dirCorreos) {
						correos.add(correoAlerta.getCuentaCorreo());
					}
					alertaComponent.enviarAlertaUpExterno(correos,
							"Link de Anuncio nuevamente arriba" + estadoLinkExterno.getLink_externo().getDescripcion(),
							"Se envía correo para reportar subida de link", fechaSubida, code, estadoLinkExterno);
				}
				Jsonp jsonp = new Jsonp();
				List<String> palabrasBusqueda = new ArrayList<String>();
				palabrasBusqueda = palabraImp.correosByLinkExterno(estadoLinkExterno.getLink_externo().getIdLink());

				if (palabrasBusqueda != null) {

					for (String palabra : palabrasBusqueda) {
						String resultadoBusqueda = null;
						resultadoBusqueda = jsonp.search(estadoLinkExterno.getLink_externo().getUrl(), palabra);

						if (estadoLinkExterno.getResultadoBusquedaPalabras() == null) {
							estadoLinkExterno.setResultadoBusquedaPalabras(resultadoBusqueda);

						} else {
							estadoLinkExterno.setResultadoBusquedaPalabras(
									estadoLinkExterno.getResultadoBusquedaPalabras() + " - " + resultadoBusqueda);
						}

					}
				}

				jsonp.getInfHtml(estadoLinkExterno.getLink_externo().getUrl());
				estadoLinkExterno.setMetaDescription(jsonp.getMetaDescription());
				estadoLinkExterno.setTitle(jsonp.getTitle());
				estadoLinkExterno.setCode(code);

				estadoLinkExterno.setMensaje("OK");

				estado.setIdEstado(1);
				// actualizar imagen pendiente
				estadosAnuncioImp.actualizar(estadoLinkExterno, estado);

			} else if (code == 429) {

				Jsonp jsonp = new Jsonp();
				jsonp.getInfHtml(estadoLinkExterno.getLink_externo().getUrl());
				estadoLinkExterno.setMetaDescription(jsonp.getMetaDescription());
				estadoLinkExterno.setTitle(jsonp.getTitle());
				estadoLinkExterno.setCode(code);
				estadoLinkExterno.setMensaje("Muchas Peticiones al servidor");

				estado.setIdEstado(1);
				estadosAnuncioImp.actualizar(estadoLinkExterno, estado);
				// colocar si existe alerta
			} else if (code == 400) {

				Jsonp jsonp = new Jsonp();
				jsonp.getInfHtml(estadoLinkExterno.getLink_externo().getUrl());
				estadoLinkExterno.setMetaDescription(jsonp.getMetaDescription());
				estadoLinkExterno.setTitle(jsonp.getTitle());
				estadoLinkExterno.setCode(code);
				estadoLinkExterno.setMensaje("Bad Request");

				estado.setIdEstado(2);
				estadosAnuncioImp.actualizar(estadoLinkExterno, estado);
				String fechaCaida = utilidades.generarHoraActual();

				alertaImp.generarAlertaExterno(estadoLinkExterno, fechaCaida);

				ArrayList<String> correos = new ArrayList<String>();
				CorreoAlertaImp correoAlertaImp = new CorreoAlertaImp();
				List<CorreoAlerta> dirCorreos = new ArrayList<>();
				dirCorreos = correoAlertaImp.correosByCuenta(estadoLinkExterno.getLink_externo().getDescripcion());
				for (CorreoAlerta correoAlerta : dirCorreos) {
					correos.add(correoAlerta.getCuentaCorreo());
				}
				alertaComponent.enviarAlertaDownExterno(correos,
						"Link de Anuncio caido" + estadoLinkExterno.getLink_externo().getDescripcion(),
						"Se envia correo para reportar caida de link", fechaCaida, estadoLinkExterno);

			} else if (code == 404) {

				Jsonp jsonp = new Jsonp();
				jsonp.getInfHtml(estadoLinkExterno.getLink_externo().getUrl());
				estadoLinkExterno.setMetaDescription(jsonp.getMetaDescription());
				estadoLinkExterno.setTitle(jsonp.getTitle());
				estadoLinkExterno.setCode(code);
				estadoLinkExterno.setMensaje("Not Found");

				estado.setIdEstado(2);
				estadosAnuncioImp.actualizar(estadoLinkExterno, estado);
				String fechaCaida = utilidades.generarHoraActual();
				alertaImp.generarAlertaExterno(estadoLinkExterno, fechaCaida);

				ArrayList<String> correos = new ArrayList<String>();
				CorreoAlertaImp correoAlertaImp = new CorreoAlertaImp();
				List<CorreoAlerta> dirCorreos = new ArrayList<>();
				dirCorreos = correoAlertaImp.correosByCuenta(estadoLinkExterno.getLink_externo().getDescripcion());
				for (CorreoAlerta correoAlerta : dirCorreos) {
					correos.add(correoAlerta.getCuentaCorreo());
				}
				alertaComponent.enviarAlertaDownExterno(correos,
						"Link de Anuncio caido" + estadoLinkExterno.getLink_externo().getDescripcion(),
						"Se envia correo para reportar caida de link", fechaCaida, estadoLinkExterno);
			} else if (code == 301) {

				Jsonp jsonp = new Jsonp();
				jsonp.getInfHtml(estadoLinkExterno.getLink_externo().getUrl());
				estadoLinkExterno.setMetaDescription(jsonp.getMetaDescription());
				estadoLinkExterno.setTitle(jsonp.getTitle());
				estadoLinkExterno.setCode(code);
				estadoLinkExterno.setMensaje("Moved Permanently - Se redireccionó");

				estado.setIdEstado(2);
				estadosAnuncioImp.actualizar(estadoLinkExterno, estado);
				String fechaCaida = utilidades.generarHoraActual();
				alertaImp.generarAlertaExterno(estadoLinkExterno, fechaCaida);

				ArrayList<String> correos = new ArrayList<String>();
				CorreoAlertaImp correoAlertaImp = new CorreoAlertaImp();
				List<CorreoAlerta> dirCorreos = new ArrayList<>();
				dirCorreos = correoAlertaImp.correosByCuenta(estadoLinkExterno.getLink_externo().getDescripcion());
				for (CorreoAlerta correoAlerta : dirCorreos) {
					correos.add(correoAlerta.getCuentaCorreo());
				}
				if (estadoLinkExterno.getLink_externo().getUrl() != "http://fb.me/"
						&& estadoLinkExterno.getLink_externo().getUrl() != "https://api.whatsapp.com/send")
					alertaComponent.enviarAlertaDownExterno(correos,
							"Moved Permanently - Se redireccionó a su nueva url (https u otra)"
									+ estadoLinkExterno.getLink_externo().getIdLink(),
							"Se envia correo para reportar caida de link", fechaCaida, estadoLinkExterno);
			} else {
				Jsonp jsonp = new Jsonp();
				jsonp.getInfHtml(estadoLinkExterno.getLink_externo().getUrl());
				estadoLinkExterno.setMetaDescription(jsonp.getMetaDescription());
				estadoLinkExterno.setTitle(jsonp.getTitle());
				estadoLinkExterno.setCode(code);
				estadoLinkExterno.setMensaje("Url no funciona");

				estado.setIdEstado(2);
				estadosAnuncioImp.actualizar(estadoLinkExterno, estado);
				String fechaCaida = utilidades.generarHoraActual();

				alertaImp.generarAlertaExterno(estadoLinkExterno, fechaCaida);

				ArrayList<String> correos = new ArrayList<String>();
				CorreoAlertaImp correoAlertaImp = new CorreoAlertaImp();
				List<CorreoAlerta> dirCorreos = new ArrayList<>();
				dirCorreos = correoAlertaImp.correosByCuenta(estadoLinkExterno.getLink_externo().getDescripcion());
				for (CorreoAlerta correoAlerta : dirCorreos) {
					correos.add(correoAlerta.getCuentaCorreo());
				}
				alertaComponent.enviarAlertaDownExterno(correos,
						"Link de Anuncio caido" + estadoLinkExterno.getLink_externo().getDescripcion(),
						"Se envia correo para reportar caida de link", fechaCaida, estadoLinkExterno);

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

	public static String peticionHttpGet(String urlParaVisitar) {

		StringBuilder resultado = new StringBuilder();

		URL url;
		try {
			url = new URL(urlParaVisitar);
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

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return resultado.toString();

	}

}
