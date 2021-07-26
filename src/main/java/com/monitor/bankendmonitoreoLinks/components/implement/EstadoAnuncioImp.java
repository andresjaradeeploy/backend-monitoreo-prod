package com.monitor.bankendmonitoreoLinks.components.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.monitor.bankendmonitoreoLinks.components.conector.Conector;
import com.monitor.bankendmonitoreoLinks.dao.IEstadoAnuncio;
import com.monitor.bankendmonitoreoLinks.entity.monitor.AdCreative;
import com.monitor.bankendmonitoreoLinks.entity.monitor.Anuncio;
import com.monitor.bankendmonitoreoLinks.entity.monitor.Estado;
import com.monitor.bankendmonitoreoLinks.entity.monitor.EstadoAnuncio;

public class EstadoAnuncioImp implements IEstadoAnuncio {

	private static final String SQL_INSERT = "INSERT INTO estado_anuncio(anuncio,code_status)" + " VALUES(?,?)";

	private static final String SQL_SELECT_BY_ID = "SELECT anuncio " + " FROM estado_anuncio WHERE anuncio = ?";

	private static final String SQL_UPDATE = "UPDATE estado_anuncio"
			+ " SET meta_description=?, title=?, estado=? , code_status=?, mensaje=?  WHERE id_estado_anuncio=?";

	@Override
	public int guardar(EstadoAnuncio estadoAnuncio, Anuncio anuncio) {

		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;

		try {

			conn = Conector.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT);
			stmt.setString(1, anuncio.getIdAnuncio());
			stmt.setInt(2, 0);

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
	public boolean verificarSiExisteEstadoAnuncio(String anuncio) {
		boolean res = false;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = Conector.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
			stmt.setString(1, anuncio);
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

	@Override
	public List<EstadoAnuncio> listarAnuncios() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		EstadoAnuncio estadoAnuncio = null;

		List<EstadoAnuncio> estados = new ArrayList<EstadoAnuncio>();

		try {

			conn = Conector.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM estado_anuncio");
			rs = stmt.executeQuery();
			while (rs.next()) {
				String idEstadoAnuncio = rs.getString("id_estado_anuncio");
				Long id = Long.parseLong(idEstadoAnuncio);
				String metaDescription = rs.getString("meta_description");
				String title = rs.getString("title");
				Anuncio anuncio = new Anuncio();
				Estado estado = new Estado();
				String idCuentaFB = rs.getString("cuenta_fb");
				String estadoAN = rs.getString("estado");
				int estadoAnuncioId = Integer.parseInt(estadoAN);

				estado.setIdEstado(estadoAnuncioId);
				anuncio.setIdAnuncio(rs.getString("anuncio"));

				estadoAnuncio = new EstadoAnuncio(id, title, metaDescription, estado, anuncio);

				estados.add(estadoAnuncio);

			}

		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally {
			Conector.close(rs);
			Conector.close(stmt);
			Conector.close(conn);
		}
		return estados;
	}

	@Override
	public int actualizar(EstadoAnuncio estadoAnuncio, Estado estado) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;

		try {

			conn = Conector.getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE);

			stmt.setString(1, estadoAnuncio.getMetaDescription());
			stmt.setString(2, estadoAnuncio.getTitle());
			stmt.setInt(3, estado.getIdEstado());
			stmt.setInt(4, estadoAnuncio.getCode());
			stmt.setString(5, estadoAnuncio.getMensaje());
			stmt.setLong(6, estadoAnuncio.getIdEstadoAnuncio());

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
	public List<EstadoAnuncio> obtener() {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<EstadoAnuncio> estados = new ArrayList<>();
		try {

			conn = Conector.getConnection();
			stmt = conn.prepareStatement(
					"select es.id_estado_anuncio,es.meta_description,es.title,adc.link,es.estado,ad.ad_creative "
							+ "from estado_anuncio es " + "inner join anuncio as ad on " + "ad.id_anuncio = es.anuncio "
							+ "inner join ad_creative as adc on " + "ad.ad_creative=adc.id_creative "
							+ "where adc.link is not null");
			rs = stmt.executeQuery();

			while (rs.next()) {
				EstadoAnuncio estadoAnuncio = new EstadoAnuncio();
				AdCreative adCreative = new AdCreative();
				Anuncio anuncio = new Anuncio();

				String linkAnuncio = rs.getString("adc.link");
				String estadoid = rs.getString("es.id_estado_anuncio");
				Long idAdCreative = rs.getLong("ad.ad_creative");

				long idestado = Long.parseLong(estadoid);
				adCreative.setIdCreative(idAdCreative);
				adCreative.setLink(linkAnuncio);
				anuncio.setAdCreative(adCreative);

				estadoAnuncio.setIdEstadoAnuncio(idestado);
				// estadoAnuncio.setAdCreative(adCreative);
				estadoAnuncio.setAnuncio(anuncio);
				estados.add(estadoAnuncio);

			}

		}

		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conector.close(rs);
			Conector.close(stmt);
			Conector.close(conn);
		}
		return estados;

	}

}
