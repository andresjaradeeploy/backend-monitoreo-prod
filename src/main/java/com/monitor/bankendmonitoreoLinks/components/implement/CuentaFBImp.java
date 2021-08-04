package com.monitor.bankendmonitoreoLinks.components.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.monitor.bankendmonitoreoLinks.components.Log;
import com.monitor.bankendmonitoreoLinks.components.conector.Conector;
import com.monitor.bankendmonitoreoLinks.dao.CuentaFBDao;
import com.monitor.bankendmonitoreoLinks.entity.monitor.CuentaFB;

public class CuentaFBImp implements CuentaFBDao {
	private Log logObject = new Log("logs");
	private Logger log = logObject.getLogger();
	private static final String SQL_SELECT_BY_ID = "SELECT id_cuentafb " + " FROM cuentafb WHERE id_cuentafb = ?";
	private static final String SQL_SELECT = "SELECT * " + " FROM cuentafb ";

	@Override
	public ArrayList<String> obtenerCuentasBD() {
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
			System.err.println("Error al obtener cuentas fb "+ex);
			log.error("Error al obtener cuentas fb "+ex);
		} finally {

			Conector.close(stmt);
			Conector.close(conn);
			Conector.close(rs);

		}
		return id_cuentas;
	}

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
			System.err.print("Ha ocurrido un error a verificar si existe cuenta: " + e.getMessage());
			log.error("Ha ocurrido un error a verificar si existe cuenta: " + e);
			
		} finally {
			Conector.close(conn);
			Conector.close(stmt);
			Conector.close(rs);
		}
		return res;
	}

}
