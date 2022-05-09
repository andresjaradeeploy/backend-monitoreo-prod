package com.monitor.bankendmonitoreoLinks.components.implement;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.monitor.bankendmonitoreoLinks.components.Log;
import com.monitor.bankendmonitoreoLinks.components.conector.Conector;
import com.monitor.bankendmonitoreoLinks.dao.IAlertaDao;
import com.monitor.bankendmonitoreoLinks.entity.monitor.EstadoAnuncio;
import com.monitor.bankendmonitoreoLinks.entity.monitor.EstadoLinkExterno;

public class AlertaImp implements IAlertaDao {

	private Log logObject = new Log("logs");
	private Logger log = logObject.getLogger();
	private static final String SQL_INSERT = "INSERT INTO alerta(estado_anuncio_fk,fecha_hora)" + " VALUES(?,?)";
	private static final String SQL_INSERT_EXTERNO = "INSERT INTO alerta(estado_link_externo,fecha_hora)" + " VALUES(?,?)";

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
			log.error("Error al guardar alerta"+ex);
		} finally {

			Conector.close(stmt);
			Conector.close(conn);
		}
		return rows;
	}
	
	@Override
	public int generarAlertaExterno(EstadoLinkExterno estadoLinkExterno, String fecha) {

		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;

		try {

			conn = Conector.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT_EXTERNO);

			stmt.setLong(1, estadoLinkExterno.getLink_externo().getIdLink());
			stmt.setString(2, fecha);

			rows = rows + stmt.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
			log.error("Error al guardar alerta"+ex);
		} finally {

			Conector.close(stmt);
			Conector.close(conn);
		}
		return rows;
	}

}
