package com.monitor.bankendmonitoreoLinks.components.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.monitor.bankendmonitoreoLinks.components.conector.Conector;
import com.monitor.bankendmonitoreoLinks.dao.IAdCreative;
import com.monitor.bankendmonitoreoLinks.entity.monitor.AdCreative;
import com.monitor.bankendmonitoreoLinks.entity.monitor.Anuncio;
import com.monitor.bankendmonitoreoLinks.entity.monitor.CuentaFB;

public class AdCreativeImp implements IAdCreative {
	private static final FacebookImp FACEBOOK_IMP = new FacebookImp();

	private static final String SQL_INSERT = "INSERT INTO ad_creative(id_creative,link,url_img,anuncio,cuenta_fb)"
			+ " VALUES(?, ?, ?, ?, ?)";

	private static final String SQL_SELECT_BY_ID = "SELECT id_creative " + " FROM ad_creative WHERE id_creative = ?";

	@Override
	public List<AdCreative> obtenerAdCreative() {

		return null;
	}

	public String obtenerAdCreativesAllCuentasFB() {
		ArrayList<String> idCuentas = new ArrayList<String>();
		idCuentas = CuentaFBImp.obtenerCuentasBD();

		String anuncios = "[";
		for (int i = 0; i < idCuentas.size(); i++) {
			if (anuncios == "[")
				anuncios = anuncios + FACEBOOK_IMP.apiGraph(idCuentas.get(i) + "/adcreatives"
						+ "?fields=name,id,object_story_spec,thumbnail_url,image_url");
			else
				anuncios = anuncios + "," + FACEBOOK_IMP.apiGraph(idCuentas.get(i) + "/adcreatives"
						+ "?fields=name,id,object_story_spec,thumbnail_url,image_url");
		}
		anuncios = anuncios + "]";

		return anuncios;

	}

	public List<Anuncio> obtenerAdCreativesInf() {
		String res = obtenerAdCreativesAllCuentasFB();
		JSONArray respuesta = new JSONArray(res);
		JSONObject resut;
		boolean ifExists = false;

		List<Anuncio> anuncios = new ArrayList<>();

		ArrayList<String> idCuentas = new ArrayList<String>();
		idCuentas = CuentaFBImp.obtenerCuentasBD();
		for (int i = 0; i < respuesta.length(); i++) {
			idCuentas.get(i);
			resut = respuesta.getJSONObject(i);
			// System.out.println("res"+resut);

			JSONArray adaccountsData = resut.getJSONArray("data");

			for (int j = 0; j < adaccountsData.length(); j++) {

				JSONObject objeto = adaccountsData.getJSONObject(j);

				String nombre = objeto.getString("name");
				String id = objeto.getString("id");

				Long id_creative = Long.parseLong(id);
				String link = null;
				String thumbnail_url = null;
				String img_url = null;
				JSONObject link_data = null;
				JSONObject object_story_spec = null;
				String data = objeto.toString();
				String ifspec = null;
				String iflink = null;
				// System.out.println("objeto" + objeto);
				if (data.contains("image_url") == true) {
					try {
						img_url = objeto.getString("image_url");
					} catch (Exception e) {
						System.err.println("error"+e);
						img_url = null;
					}
				}
				// img_url = "iamgen si tiene";
				else

					img_url = null;
				System.out.println(img_url);
				if (data.contains("thumbnail_url") == true)

					thumbnail_url = objeto.getString("thumbnail_url");
				else
					thumbnail_url = null;

				if (data.contains("object_story_spec") == true) {
					object_story_spec = objeto.getJSONObject("object_story_spec");

					ifspec = object_story_spec.toString();

					if (ifspec.contains("link_data") == true) {
						link_data = object_story_spec.getJSONObject("link_data");
						iflink = link_data.toString();

						if (iflink.contains("link") == true)
							link = link_data.getString("link");

						else
							link = null;
					} else
						link_data = null;

				} else
					object_story_spec = null;

				AdCreative adCreative = new AdCreative();
				Anuncio anuncio = new Anuncio();
				CuentaFB cuentaFB = new CuentaFB();
				adCreative.setAnuncio(anuncio);
				adCreative.setIdCreative(id_creative);
				adCreative.setLink(link);
				adCreative.setUrlImg(img_url);
				cuentaFB.setIdCuenta(idCuentas.get(i));

				adCreative.setCuentaFB(cuentaFB);

				if (link != null) {

					ifExists = verificarSiExisteAdCreative(adCreative.getIdCreative());
					if (ifExists == false)
						guardar(adCreative, cuentaFB);
					else
						System.out.println("Ya existe el ad creative en BD");
				} else
					System.out.println("Ad creatriv eno tiene link no se guardara");

			}

		}

		return anuncios;

	}

	@Override
	public int guardar(AdCreative adCreative, CuentaFB cuentaFB) {

		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;

		try {

			conn = Conector.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT);

			stmt.setLong(1, adCreative.getIdCreative());
			stmt.setString(2, adCreative.getLink());
			stmt.setString(3, adCreative.getUrlImg());
			stmt.setString(4, adCreative.getAnuncio().getIdAnuncio());
			stmt.setString(5, cuentaFB.getIdCuenta());

			rows = rows + stmt.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally {

			Conector.close(stmt);
			Conector.close(conn);
		}
		return rows;

	}

	public static void main(String[] args) {

		AdCreativeImp adCreativeImp = new AdCreativeImp();
		adCreativeImp.obtenerAdCreativesInf();
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
				String url_img = rs.getString("url_img");
				Anuncio anuncio = new Anuncio();

				adCreative = new AdCreative(id_creative, link, url_img, anuncio);
				adCreatives.add(adCreative);

			}

		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
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
		} finally {
			Conector.close(conn);
			Conector.close(stmt);
			Conector.close(rs);
		}
		return res;
	}
}
