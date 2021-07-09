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
import com.monitor.bankendmonitoreoLinks.entity.monitor.Anuncio;
import com.monitor.bankendmonitoreoLinks.entity.monitor.CuentaFB;


public class AnuncioImp implements IAnuncioDao {
	
	private static final FacebookImp FACEBOOK_IMP = new FacebookImp();
	
	private static final String SQL_INSERT = "INSERT INTO anuncio(id_anuncio,nombre,cuenta_fb)"
			+ " VALUES(?, ?, ?)";
	
	private static final String SQL_SELECT_BY_ID = "SELECT id_anuncio " + " FROM anuncio WHERE id_anuncio = ?";
	
	
	public  String obtenerAnunciosAllCuentasFB()
	{
		ArrayList<String> idCuentas= new ArrayList<String>();
		idCuentas= CuentaFBImp.obtenerCuentas();
		
		String anuncios="[";
		for(int i=0;i<idCuentas.size();i++) {
			if (anuncios == "[")
				anuncios = anuncios + FACEBOOK_IMP.apiGraph(idCuentas.get(i) + "/ads" + "?fields=name,id");
			else
				anuncios = anuncios + "," + FACEBOOK_IMP.apiGraph(idCuentas.get(i) + "/ads" + "?fields=name,id");
		}
		anuncios = anuncios + "]";
					
		return anuncios;
		
	}
	
	@Override
	public  List<Anuncio> obtenerAnunciosInf() {
		String res= obtenerAnunciosAllCuentasFB();
		JSONArray respuesta = new JSONArray(res);
		JSONObject resut;
		ArrayList<String> cuentas = new ArrayList<String>();
		List<Anuncio> anuncios = new ArrayList<>();
		
		ArrayList<String> idCuentas= new ArrayList<String>();
		idCuentas= CuentaFBImp.obtenerCuentas();
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
			
			
			/*System.out.println("nombre"+nombre);
			System.out.println("id"+id);
			System.out.println("cuenta"+idCuentas.get(i));*/
			Anuncio anuncio= new Anuncio();
			CuentaFB cuentaFB= new CuentaFB();
			
			anuncio.setIdAnuncio(id);
			anuncio.setNombre(nombre);
			cuentaFB.setIdCuenta(idCuentas.get(i));
			anuncio.setCuentaFB(cuentaFB);
			
			
			boolean ifExists= verificarSiExisteAnuncio(anuncio.getIdAnuncio());
			if(ifExists==false)
			guardar(anuncio, cuentaFB);
			else
				System.out.println("Ya existe el anuncio en BD");
					
			cuentas.add(id);
			cuentas.add(nombre);
				
			anuncios.add(anuncio);
			//guardar(cuentaFB);

		
		}
		
		}
		
		return  anuncios;
		
	
	}
	
	public static void main(String[] args) {
	/*CuentaFBImp cuentaFB = new CuentaFBImp();
		 cuentaFB.guardarCuentas();
		AnuncioImp anuncioImp = new AnuncioImp();
		//anuncioImp.obtenerAnunciosAllCuentasFB();
		//System.out.println("eror"+res);
		List<Anuncio> anuncios = new AnuncioImp().obtenerAnunciosInf();
		 for (Anuncio model : anuncios) {
	            System.out.println(model.getNombre());
	            System.out.println(model.getIdAnuncio());
	            System.out.println(model.getCuentaFB().getIdCuenta());
	        }
		//System.out.println(anuncios.toString());
		//anuncioImp.obtenerAnunciosInf();
		 */
		
		List<Anuncio> anuncios = new AnuncioImp().listarAnuncios();
		 for (Anuncio model : anuncios) {
	            System.out.println(" nombre: "+model.getNombre()+ " idAnuncio: "+model.getIdAnuncio()+ " CuentaFB: "+model.getCuentaFB().getIdCuenta());
	           
	        }
		 
	}

	@Override
	public int guardar(Anuncio anuncio,CuentaFB cuentaFB) {
		   
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;

		try {

			conn = Conector.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT);

			stmt.setString(1, anuncio.getIdAnuncio());
			stmt.setString(2, anuncio.getNombre());
			stmt.setString(3, cuentaFB.getIdCuenta());

			rows = rows + stmt.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
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
	                String linkAnuncio = rs.getString("link_anuncio");
	                String nombre = rs.getString("nombre");
	                CuentaFB cuentaFB = new CuentaFB(); 
	                String idCuentaFB= rs.getString("cuenta_fb");
	                
	                cuentaFB.setIdCuenta(idCuentaFB);
	               

	                anuncio = new Anuncio(idAnuncio, linkAnuncio, nombre, cuentaFB);
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
			// Conector.close(rs);
		}
		return res;
	}


}
