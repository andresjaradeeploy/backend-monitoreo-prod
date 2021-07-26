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
import com.monitor.bankendmonitoreoLinks.dao.CuentaFBDao;
import com.monitor.bankendmonitoreoLinks.entity.monitor.CuentaFB;


public class CuentaFBImp implements CuentaFBDao {
	private static FacebookImp FACEBOOK_IMP = new FacebookImp();

	private static final String SQL_INSERT = "INSERT INTO cuentafb(id_cuentafb,nombre_cuenta, cuenta_fb_developer)"
			+ " VALUES(?, ?, 1000001)";

	private static final String SQL_UPDATE = "UPDATE cuentafb" + " SET nombre_cuenta=? WHERE id_cuenta=?";

	private static final String SQL_SELECT_BY_ID = "SELECT id_cuentafb " + " FROM cuentafb WHERE id_cuentafb = ?";

	private static final String SQL_SELECT = "SELECT * " + " FROM cuentafb ";

	// funcion para guardar una cuentafb
	/*@Override
	public int guardar(CuentaFB cuentaFB) {

		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;

		try {

			conn = Conector.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT);

			stmt.setString(1, cuentaFB.getIdCuenta());
			stmt.setString(2, cuentaFB.getNombreCuenta());

			rows = rows + stmt.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally {

			Conector.close(stmt);
			Conector.close(conn);
		}
		return rows;

	}*/

	// Obtiene las cuentasfb (id) asociadas a la cuenta developer
	/*
	public static ArrayList<String> obtenerCuentas() {
		String cuentas = "";
		cuentas = FACEBOOK_IMP.apiGraph("me?fields=adaccounts");

		JSONObject respuesta = new JSONObject(cuentas);

		JSONObject adaccounts = respuesta.getJSONObject("adaccounts");

		JSONArray adaccountsData = adaccounts.getJSONArray("data");
		ArrayList<String> id_cuentas = new ArrayList<String>();

		for (int i = 0; i < adaccountsData.length(); i++) {

			JSONObject objeto = adaccountsData.getJSONObject(i);

			String id = objeto.getString("id"); // obtiene valor 10
			// String valorNombre = objeto.getString("nombre"); //obtiene valor Ejemplo

			id_cuentas.add(id);
		}
		return id_cuentas;
	}*/

	public static ArrayList<String> obtenerCuentasBD() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		CuentaFB cuentaFB = null;
		ArrayList<String> id_cuentas = new ArrayList<String>();

		try {

			conn = Conector.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT);
			rs = stmt.executeQuery();
			while (rs.next()) {

				String id_cuentafb = rs.getString("id_cuentafb");

				cuentaFB = new CuentaFB(id_cuentafb);
				id_cuentas.add(cuentaFB.getIdCuenta());

			}
			return id_cuentas;

		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally {

			Conector.close(stmt);
			Conector.close(conn);
			Conector.close(rs);

		}
		return id_cuentas;
	}

	/*@Override
	public int actualizar() {
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;

		try {
			CuentaFB cuentasfb = new CuentaFB();
			ArrayList<String> id_cuenta = obtenerCuentas();

			for (int i = 0; i < id_cuenta.size(); i++)

			{
				conn = Conector.getConnection();
				stmt = conn.prepareStatement(SQL_UPDATE);
				cuentasfb.setIdCuenta(id_cuenta.get(i));

				stmt.setString(1, cuentasfb.getIdCuenta());

				rows = rows + stmt.executeUpdate();
			}

		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally {

			Conector.close(stmt);
			Conector.close(conn);
		}
		return rows;

	}*/

	@Override
	public List<CuentaFB> consultar() {
		return null;
	}

	// Obtener nombre y id de cuentas de facebook asociadas a la cuenta developer
	/*public String obtenerObjetosDeCuentas() {

		ArrayList<String> id_cuenta = obtenerCuentasBD();
		String res = "[";
		for (int i = 0; i < id_cuenta.size(); i++)

		{
			if (res == "[")
				res = res + FACEBOOK_IMP.apiGraph(id_cuenta.get(i) + "?" + "&fields=name,id");
			else
				res = res + "," + FACEBOOK_IMP.apiGraph(id_cuenta.get(i) + "?" + "&fields=name,id");

		}
		res = res + "]";

		return res;
	}*/

	// guardar cuentas asociadas en la bd
	/*public void guardarCuentas() {

		String res = obtenerObjetosDeCuentas();

		JSONArray respuesta = new JSONArray(res);

		

		for (int i = 0; i < respuesta.length(); i++) {

			JSONObject objeto = respuesta.getJSONObject(i);

			String nombre = objeto.getString("name");
			String id = objeto.getString("id");

			CuentaFB cuentaFB = new CuentaFB();
			cuentaFB.setIdCuenta(id);
			cuentaFB.setNombreCuenta(nombre);

			boolean ifExists = verificarSiExisteCuenta(id);
			if (ifExists == false)
				guardar(cuentaFB);
			else
				System.out.println("La cuenta ya existe");

			
		}

	}*/

	// Saber si una cuenta esta en la bd
	@Override
	public boolean verificarSiExisteCuenta(String idCuenta) {
		boolean res = false;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = Conector.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
			stmt.setString(1, idCuenta);
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

	public static void main(String[] args) {

	}
}
