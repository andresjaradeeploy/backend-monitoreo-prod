package com.monitor.bankendmonitoreoLinks.components.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.monitor.bankendmonitoreoLinks.components.Log;
import com.monitor.bankendmonitoreoLinks.components.conector.Conector;
import com.monitor.bankendmonitoreoLinks.dao.IAdCreative;
import com.monitor.bankendmonitoreoLinks.entity.monitor.AdCreative;

public class AdCreativeImp implements IAdCreative {

	private Log logObject = new Log("logs");
	private Logger log = logObject.getLogger();

	private static final FacebookImp FACEBOOK_IMP = new FacebookImp();

	private static final String SQL_INSERT = "INSERT INTO ad_creative(id_creative,link,nombre_anuncio,url_img)"
			+ " VALUES(?, ?, ?, ?)";

	private static final String SQL_SELECT_BY_ID = "SELECT id_creative " + " FROM ad_creative WHERE id_creative = ?";

	private static final String SQL_UPDATE = "UPDATE ad_creative" + " SET link=?, url_img=?  WHERE id_creative=?";

	public String obtenerAdCreativesAllCuentasFB(List<String> creatives) {

		String adcreative = "[";

		for (String creative : creatives) {
			if (adcreative == "[") {

				adcreative = adcreative
						+ FACEBOOK_IMP.apiGraph(creative + "?fields=name,id,object_story_spec,thumbnail_url,image_url");

			} else {

				adcreative = adcreative + ","
						+ FACEBOOK_IMP.apiGraph(creative + "?fields=name,id,object_story_spec,thumbnail_url,image_url");
			}

		}
		adcreative = adcreative + "]";

		return adcreative;

	}

	public String obtenerCreativesAds() {

		ArrayList<String> idCuentas = new ArrayList<String>();
		CuentaFBImp cuentaFBImp = new CuentaFBImp();
		idCuentas = cuentaFBImp.obtenerCuentasBD();

		String anuncios = "[";
		for (int i = 0; i < idCuentas.size(); i++) {
			if (anuncios == "[")
				anuncios = anuncios + FACEBOOK_IMP.apiGraph(idCuentas.get(i) + "/ads?fields=creative");
			else
				anuncios = anuncios + "," + FACEBOOK_IMP.apiGraph(idCuentas.get(i) + "/ads?fields=creative");
		}
		anuncios = anuncios + "]";

		return anuncios;

	}

	public List<String> listadoCreativesAds() {
		String res = obtenerCreativesAds();
		JSONArray respuesta = new JSONArray(res);
		JSONObject resut;

		List<String> creatives = new ArrayList<>();

		ArrayList<String> idCuentas = new ArrayList<String>();
		CuentaFBImp cuentaFBImp = new CuentaFBImp();
		idCuentas = cuentaFBImp.obtenerCuentasBD();
		for (int i = 0; i < respuesta.length(); i++) {
			idCuentas.get(i);
			resut = respuesta.getJSONObject(i);
			System.out.println("res" + respuesta.length());

			JSONArray adaccountsData = resut.getJSONArray("data");
			for (int j = 0; j < adaccountsData.length(); j++) {
				JSONObject objeto = adaccountsData.getJSONObject(j);
				JSONObject datacreative = objeto.getJSONObject("creative");
				String idCreative = datacreative.getString("id");
				creatives.add(idCreative);
			}
		}

		return creatives;
	}

	public List<AdCreative> obtenerAdCreativesInf() {
		AdCreativeImp adCreativeImp = new AdCreativeImp();
		String res = obtenerAdCreativesAllCuentasFB(adCreativeImp.listadoCreativesAds());

		JSONArray respuesta = new JSONArray(res);

		JSONObject resut;
		boolean ifExists = false;

		List<AdCreative> anuncios = new ArrayList<>();

		for (int i = 0; i < respuesta.length(); i++) {

			try {
				resut = respuesta.getJSONObject(i);

				String nombre = resut.getString("name");

				String id = resut.getString("id");

				Long id_creative = Long.parseLong(id);
				String link = null;

				String img_url = null;
				String imgUrl_videoData = null;
				JSONObject link_data = null;
				JSONObject videoData = null;
				JSONArray dataChild = null;
				JSONObject linkChild = null;
				JSONObject object_story_spec = null;
				String data = resut.toString();
				String ifspec = null;
				String linkchild = null;
				String iflink = null;

				if (data.contains("object_story_spec") == true) {
					object_story_spec = resut.getJSONObject("object_story_spec");

					ifspec = object_story_spec.toString();

					if (ifspec.contains("link_data") == true) {
						link_data = object_story_spec.getJSONObject("link_data");
						iflink = link_data.toString();

						if (iflink.contains("link") == true)
							try {
								link = link_data.getString("link");
							} catch (Exception e) {
								System.err.println("err" + e);
								dataChild = link_data.getJSONArray("child_attachments");
								linkChild = dataChild.getJSONObject(0);
								linkchild = linkChild.getString("link");
								link = linkchild;

							}

						else
							link = null;
					} else
						link_data = null;

				} else
					object_story_spec = null;

				if (data.contains("image_url") == true) {
					try {
						img_url = resut.getString("image_url");
					} catch (Exception e) {
						log.warn("objeto image_url se encuentra en otro array"+e);
						videoData = object_story_spec.getJSONObject("video_data");
						imgUrl_videoData = videoData.getString("image_url");
						img_url = imgUrl_videoData;
					}
				}

				else

					img_url = null;

				AdCreative adCreative = new AdCreative();

				adCreative.setIdCreative(id_creative);
				adCreative.setLink(link);
				adCreative.setUrlImg(img_url);
				adCreative.setNombre(nombre);

				if (link != null &&  link !="http://fb.me/" && link!="https://api.whatsapp.com/send") {

					ifExists = verificarSiExisteAdCreative(adCreative.getIdCreative());
					if (ifExists == false)
						guardar(adCreative);
					else
						System.out.println("Ya existe el Ad Creative en BD");
					actualizar(adCreative);
					System.out.println("Se actualizó Ad Creative");
				} else
					System.out.println("Ad creatriv eno tiene link no se guardara");
					log.warn("Ad creatriv eno tiene link no se guardara"+adCreative.getIdCreative());

			} catch (Exception e) {
				System.err.println("error Object" + e);
				log.error("Error object"+e);
			}
		}

		return anuncios;

	}

	@Override
	public int guardar(AdCreative adCreative) {

		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;

		try {

			conn = Conector.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT);

			stmt.setLong(1, adCreative.getIdCreative());
			stmt.setString(2, adCreative.getLink());
			stmt.setString(3, adCreative.getNombre());
			stmt.setString(4, adCreative.getUrlImg());

			rows = rows + stmt.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
			log.error("Error al guardar AdCreative"+ex);
		} finally {

			Conector.close(stmt);
			Conector.close(conn);
		}
		return rows;

	}

	@Override
	public List<AdCreative> listarAdCreatives() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		AdCreative adCreative = null;

		List<AdCreative> adCreatives = new ArrayList<>();

		try {

			conn = Conector.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM ad_creative");
			rs = stmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id_creative");
				Long id_creative = Long.parseLong(id);
				String link = rs.getString("link");
				String nombre = rs.getString("nombre_anuncio");
				String url_img = rs.getString("url_img");

				adCreative = new AdCreative(id_creative, nombre, link, url_img);
				adCreatives.add(adCreative);

			}

		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
			log.error("Error al listar AdCreative"+ex);
			
		} finally {
			Conector.close(rs);
			Conector.close(stmt);
			Conector.close(conn);
		}
		return adCreatives;
	}

	@Override
	public boolean verificarSiExisteAdCreative(Long idAdCreative) {
		boolean res = false;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = Conector.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
			stmt.setLong(1, idAdCreative);
			rs = stmt.executeQuery();

			if (rs.next())
				res = true;
			else
				res = false;

		} catch (Exception e) {
			System.err.print("Ha ocurrido un error: " + e.getMessage());
			log.error("No se pudo verificar si existe AdCreative"+e);
		} finally {
			Conector.close(conn);
			Conector.close(stmt);
			Conector.close(rs);
		}
		return res;
	}

	@Override
	public int actualizar(AdCreative adCreative) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;

		try {

			conn = Conector.getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE);

			stmt.setString(1, adCreative.getLink());
			stmt.setString(2, adCreative.getUrlImg());
			stmt.setLong(3, adCreative.getIdCreative());

			rows = rows + stmt.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
			log.error("Error al actualizar AdCreative"+ex);
		} finally {

			Conector.close(stmt);
			Conector.close(conn);
		}
		return rows;
	}
}
