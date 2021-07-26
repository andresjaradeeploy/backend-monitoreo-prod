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
import com.monitor.bankendmonitoreoLinks.dao.IAnuncioDao;
import com.monitor.bankendmonitoreoLinks.entity.monitor.AdCreative;
import com.monitor.bankendmonitoreoLinks.entity.monitor.Anuncio;
import com.monitor.bankendmonitoreoLinks.entity.monitor.CuentaFB;


public class AnuncioImp implements IAnuncioDao {
	
	private static final FacebookImp FACEBOOK_IMP = new FacebookImp();
	
	private static final String SQL_INSERT = "INSERT INTO anuncio(id_anuncio,impresiones,nombre,preview_shareable_link,ad_creative,cuenta_fb)"
			+ " VALUES(?, ?, ?, ?, ?, ?)";
	
	private static final String SQL_SELECT_BY_ID = "SELECT id_anuncio " + " FROM anuncio WHERE id_anuncio = ?";
	
	
	public  String obtenerAnunciosAllCuentasFB()
	{
		ArrayList<String> idCuentas= new ArrayList<String>();
		idCuentas= CuentaFBImp.obtenerCuentasBD();
		
		String anuncios="[";
		for(int i=0;i<idCuentas.size();i++) {
			if (anuncios == "[")
				anuncios = anuncios + FACEBOOK_IMP.apiGraph(idCuentas.get(i) + "/ads" + "?fields=name,id,preview_shareable_link,adcreatives,insights");
			else
				anuncios = anuncios + "," + FACEBOOK_IMP.apiGraph(idCuentas.get(i) + "/ads" + "?fields=name,id,preview_shareable_link,adcreatives,insights");
		}
		anuncios = anuncios + "]"; 
					
		return anuncios;
		
	}
	
	@Override
	public  List<Anuncio> obtenerAnunciosInf() {
		String res= obtenerAnunciosAllCuentasFB();
		JSONArray respuesta = new JSONArray(res);
		String impresiones=null;
		Integer numeroImpresiones=0;
		
		JSONObject resut;
		ArrayList<String> cuentas = new ArrayList<String>();
		List<Anuncio> anuncios = new ArrayList<>();
		
		ArrayList<String> idCuentas= new ArrayList<String>();
		idCuentas= CuentaFBImp.obtenerCuentasBD();
		
		for(int i=0;i<respuesta.length();i++)
		{
			idCuentas.get(i);
		resut= respuesta.getJSONObject(i);
		//System.out.println("res"+resut);
		
		JSONArray adaccountsData = resut.getJSONArray("data");
		
			
		for (int j = 0; j < adaccountsData.length(); j++) {

			JSONObject objeto = adaccountsData.getJSONObject(j);

			String nombre = objeto.getString("name");
			String id = objeto.getString("id");
			String preview_shareable_link = objeto.getString("preview_shareable_link");
			
			
			JSONObject objectAdcreatives = objeto.getJSONObject("adcreatives");
			JSONArray dataAdCreatives= objectAdcreatives.getJSONArray("data");
			JSONObject dataIdAdCreative = dataAdCreatives.getJSONObject(0);
			String idAdCreative= dataIdAdCreative.getString("id");
			
			
			try {
				JSONObject objectInsights = objeto.getJSONObject("insights");
				JSONArray dataInsights= objectInsights.getJSONArray("data");
				JSONObject dataImpresionInsights = dataInsights.getJSONObject(i);
				impresiones= dataImpresionInsights.getString("impressions");
				numeroImpresiones= Integer.parseInt(impresiones);
				
			} catch (Exception e) {
				System.out.println("err"+e);
				impresiones="Ad sin estadisticas";
			}
			
			
			
			
			
			Anuncio anuncio= new Anuncio();
			CuentaFB cuentaFB= new CuentaFB();
			AdCreative adCreative= new AdCreative();
			
			Long idAdcreative=Long.parseLong(idAdCreative);
			
			anuncio.setIdAnuncio(id);
			anuncio.setNombre(nombre);
			anuncio.setPreview_shareable_link(preview_shareable_link);
			anuncio.setImpresiones(numeroImpresiones);
			cuentaFB.setIdCuenta(idCuentas.get(i));
			anuncio.setCuentaFB(cuentaFB);
			adCreative.setIdCreative(idAdcreative);
			
			
			boolean ifExists= verificarSiExisteAnuncio(anuncio.getIdAnuncio());
			if(ifExists==false)
				
			if (numeroImpresiones>0) {
				guardar(anuncio, cuentaFB,adCreative);
			}
			else
				System.err.println("Impresiones menores que 0");
			
			
			else
				System.out.println("Ya existe el anuncio en BD");
					
			cuentas.add(id);
			cuentas.add(nombre);
			
			
				
			anuncios.add(anuncio);
			

		
		}
		
		}
		
		return  anuncios;
		
	
	}
	
	public static void main(String[] args) {
	 
	}

	@Override
	public int guardar(Anuncio anuncio,CuentaFB cuentaFB,AdCreative adCreative) {
		   
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;

		try {

			conn = Conector.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT);
			
			stmt.setString(1, anuncio.getIdAnuncio());
			stmt.setInt(2, anuncio.getImpresiones());
			stmt.setString(3, anuncio.getNombre());
			stmt.setString(4, anuncio.getPreview_shareable_link());
			stmt.setLong(5, adCreative.getIdCreative());
			stmt.setString(6, cuentaFB.getIdCuenta());

			rows = rows + stmt.executeUpdate();

		} catch (SQLException ex) {
			System.out.println("error el adcreative no tenía link por ello no se agrega anuncio");
			System.out.println(ex);
		} finally {

			Conector.close(stmt);
			Conector.close(conn);
		}
		return rows;

	}

	@Override
	public List<Anuncio> listarAnuncios() {
		 Connection conn = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;
	        Anuncio anuncio = null;

	        List<Anuncio> anuncios = new ArrayList<>();

	        try {

	            conn = Conector.getConnection();
	            stmt = conn.prepareStatement("SELECT * FROM anuncio");
	            rs = stmt.executeQuery();
	            while (rs.next()) {
	                String idAnuncio = rs.getString("id_anuncio");
	                String nombre = rs.getString("nombre");
	                CuentaFB cuentaFB = new CuentaFB(); 
	                String idCuentaFB= rs.getString("cuenta_fb");
	                
	                cuentaFB.setIdCuenta(idCuentaFB);
	               

	                anuncio = new Anuncio(idAnuncio, nombre, cuentaFB);
	                anuncios.add(anuncio);

	            }

	        } catch (SQLException ex) {
	            ex.printStackTrace(System.out);
	        } finally {
				Conector.close(rs);
	            Conector.close(stmt);
	            Conector.close(conn);
	        }
	        return anuncios;
	}
	
	@Override
	public boolean verificarSiExisteAnuncio(String idAnuncio) {
		boolean res = false;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = Conector.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
			stmt.setString(1, idAnuncio);
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
