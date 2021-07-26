package com.monitor.bankendmonitoreoLinks.components.implement;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.monitor.bankendmonitoreoLinks.components.conector.Conector;
import com.monitor.bankendmonitoreoLinks.dao.IAlertaDao;
import com.monitor.bankendmonitoreoLinks.entity.monitor.EstadoAnuncio;

public class AlertaImp implements IAlertaDao {

	private static final String SQL_INSERT = "INSERT INTO alerta(estado_anuncio,fecha_hora)" + " VALUES(?,?)";

	@Override
	public int generarAlerta(EstadoAnuncio estadoAnuncio, String fecha) {

		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;

		try {

			conn = Conector.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT);

			stmt.setLong(1, estadoAnuncio.getIdEstadoAnuncio());
			stmt.setString(2, fecha);

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

	}

}
