package com.monitor.bankendmonitoreoLinks.components.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.monitor.bankendmonitoreoLinks.components.Log;
import com.monitor.bankendmonitoreoLinks.components.conector.Conector;
import com.monitor.bankendmonitoreoLinks.dao.ICorreoAlertaDao;

import com.monitor.bankendmonitoreoLinks.entity.monitor.CorreoAlerta;

public class CorreoAlertaImp implements ICorreoAlertaDao {

	private Log logObject = new Log("logs");
	private Logger log = logObject.getLogger();
	private static final String SQL_SELECTBYCUENTA = "select id_correo,cuenta_correo " + "from correo_alerta "
			+ "where cuenta_fb = ? ";

	@Override
	public List<CorreoAlerta> correosByCuenta(String cuenta) {

		List<CorreoAlerta> dirCorreos = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		CorreoAlerta correoAlerta = null;

		try {
			conn = Conector.getConnection();
			stmt = conn.prepareStatement(SQL_SELECTBYCUENTA);
			stmt.setString(1, cuenta);
			rs = stmt.executeQuery();

			while (rs.next()) {
				correoAlerta = new CorreoAlerta(rs.getLong("id_correo"), rs.getString("cuenta_correo"));
				dirCorreos.add(correoAlerta);
			}

		} catch (Exception e) {
			System.err.print("Ha ocurrido un error: " + e.getMessage());
			log.error("No se pudo obtener correos de cuenta " + cuenta + " :" + e);
		} finally {
			Conector.close(conn);
			Conector.close(stmt);
			Conector.close(rs);
		}

		return dirCorreos;
	}

}
