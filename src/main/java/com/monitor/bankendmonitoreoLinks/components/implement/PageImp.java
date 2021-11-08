package com.monitor.bankendmonitoreoLinks.components.implement;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.monitor.bankendmonitoreoLinks.components.conector.Conector;
import com.monitor.bankendmonitoreoLinks.dao.PagesDao;
import com.monitor.bankendmonitoreoLinks.entity.monitor.AdCreative;
import com.monitor.bankendmonitoreoLinks.entity.monitor.Anuncio;
import com.monitor.bankendmonitoreoLinks.entity.monitor.CuentaFB;
import com.monitor.bankendmonitoreoLinks.entity.pages.Page;

public class PageImp implements PagesDao {
	
	private static final String SQL_INSERT = "INSERT INTO page(id_page,access_token,category,name)"
			+ " VALUES(?, ?, ?, ?)";
	
	private static final String SQL_SELECT = "SELECT * " + " FROM page";
	
	private static final String SQL_UPDATE = "UPDATE page"
			+ " SET picture=?, link=?, fan_count=?, access_token=?, cover=?, social_sentence=? WHERE id_page=?";
	
	private static final FacebookImp FACEBOOK_IMP = new FacebookImp();

	public String obtenerPages() {

		String pages = "[";

		if (pages == "[") {
			try {
				pages = pages + FACEBOOK_IMP.apiGraphPage("/me" + "?fields=accounts");
			} catch (Exception e) {
				System.err.println("no se pudo obtener paginas");
			}

		} else {
			try {
				pages = pages + "," + FACEBOOK_IMP.apiGraphPage("/me" + "?fields=accounts");
			} catch (Exception e) {
				System.err.println("no se pudo obtener pages");

			}
		}

		pages = pages + "]";

		return pages;

	}

	public List<Page> obtenerPageInf() {
		String res = obtenerPages();
		JSONArray respuesta = new JSONArray(res);
		System.out.println("tamaño"+respuesta.length());
		JSONObject resut;
		List<Page> pages = new ArrayList<>();
		PageImp pageImp= new PageImp(); 

		for (int i = 0; i < respuesta.length(); i++) {

			resut = respuesta.getJSONObject(i);

			JSONObject accounts = resut.getJSONObject("accounts");
			
			JSONArray data = accounts.getJSONArray("data");

			System.out.println("tamaño array data"+data.length());
			
			for (int j=0;j<data.length();j++) {
				
				
			
			JSONObject objeto = data.getJSONObject(j);

			String access_token = objeto.getString("access_token");
			String id = objeto.getString("id");
			String category = objeto.getString("category");
			String name = objeto.getString("name");

			Page page = new Page();

			page.setAccess_token(access_token);
			page.setCategory(category);
			page.setName(name);
			page.setIdPage(id);
			
			pageImp.guardar(page);
			

			pages.add(page);
			}
			
		}

		return pages;
	}
	
	public List<Page> obtenerAllPageInf() {
		String res = obtenerAllPages();
		JSONArray respuesta = new JSONArray(res);
		System.out.println("tamaño"+respuesta.length());
		JSONObject resut;
		List<Page> pages = new ArrayList<>();
		PageImp pageImp= new PageImp(); 

		for (int i = 0; i < respuesta.length(); i++) {

			resut = respuesta.getJSONObject(i);

			JSONObject dataPage = resut.getJSONObject("picture");
				
			JSONObject datapicture = dataPage.getJSONObject("data");
			String coverSource= null;
			JSONObject dataCover=null;
			
			String social_sentence= null;
			JSONObject engagement=null;
			
			try {
				dataCover= resut.getJSONObject("cover");
			} catch (Exception e) {
				System.err.println("no exite cover");
			}

			try {
				engagement= resut.getJSONObject("engagement");
			} catch (Exception e) {
				System.err.println("no exite engagement");
			}
			
		

			String access_token = resut.getString("access_token");
			String picture = datapicture.getString("url");
			String link = resut.getString("link");
			try {
				coverSource= dataCover.getString("source");
			} catch (Exception e) {
				System.err.println("no exite source");
			}
			try {
				social_sentence= engagement.getString("social_sentence");
			} catch (Exception e) {
				System.err.println("no exite social sentence");
			}
			
			
			Integer fan_count = resut.getInt("fan_count");
			String id_page = resut.getString("id");
			//Integer likes= Integer.parseInt(fan_count);

			Page page = new Page();

			page.setAccess_token(access_token);
			page.setFan_count(fan_count);
			page.setLink(link);
			page.setPicture(picture);
			page.setIdPage(id_page);
			page.setCover(coverSource);
			page.setSocial_sentence(social_sentence);
			
		
			actualizar(page);
			pages.add(page);
			
			}
			
		

		return pages;
	}
	
	
	public String obtenerAllPages() {
		
		
	
		ArrayList<String> idPages = new ArrayList<String>();
		PageImp pageImp = new PageImp();
		idPages = pageImp.obtenerPagesBD();

		String pages = "[";
		for (int i = 0; i < idPages.size(); i++) {
			if (pages == "[")
				pages = pages + FACEBOOK_IMP.apiGraphPage(idPages.get(i) + "?fields=picture,fan_count,link,access_token,cover,social_sentence");
			else
				pages = pages + "," + FACEBOOK_IMP.apiGraphPage(idPages.get(i) + "?fields=picture,fan_count,link,access_token,cover,social_sentence");
		}
		pages = pages + "]";

		return pages;

	}
	
	@Override
	public ArrayList<String> obtenerPagesBD() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
	
		ArrayList<String> id_pages = new ArrayList<String>();

		try {

			conn = Conector.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Page page= new Page();
				String id_page = rs.getString("id_page");

				page.setIdPage(id_page);
				id_pages.add(page.getIdPage());
			

			}
			return id_pages;

		} catch (SQLException ex) {
			System.err.println("Error al obtener cuentas fb "+ex);
			
		} finally {

			Conector.close(stmt);
			Conector.close(conn);
			Conector.close(rs);

		}
		return id_pages;
	}
	
	@Override
	public int guardar(Page page) {

		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;

		try {

			conn = Conector.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT);

			stmt.setString(1, page.getIdPage());
			stmt.setString(2, page.getAccess_token());
			stmt.setString(3, page.getCategory());
			stmt.setString(4, page.getName());
		

			rows = rows + stmt.executeUpdate();

		} catch (SQLException ex) {
			System.err.println("error al guardar page" + ex);
		} finally {

			Conector.close(stmt);
			Conector.close(conn);
		}
		return rows;

	}
	
	@Override
	public int actualizar(Page page) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;

		try {

			conn = Conector.getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE);

			stmt.setString(1, page.getPicture());
			stmt.setString(2, page.getLink());
			stmt.setInt(3, page.getFan_count());
			stmt.setString(4, page.getAccess_token());
			stmt.setString(5, page.getCover());
			stmt.setString(6, page.getSocial_sentence());
			stmt.setString(7, page.getIdPage());

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
		PageImp imp = new PageImp();
		System.out.println(imp.obtenerPages());
		
		List<Page> pages = new ArrayList<>();
		imp.obtenerAllPageInf();
	
}
}
