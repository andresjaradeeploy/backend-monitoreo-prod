package com.monitor.bankendmonitoreoLinks.components.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.monitor.bankendmonitoreoLinks.components.Log;
import com.monitor.bankendmonitoreoLinks.components.conector.Conector;
import com.monitor.bankendmonitoreoLinks.dao.IEstadoAnuncio;
import com.monitor.bankendmonitoreoLinks.entity.monitor.AdCreative;
import com.monitor.bankendmonitoreoLinks.entity.monitor.Anuncio;
import com.monitor.bankendmonitoreoLinks.entity.monitor.CuentaFB;
import com.monitor.bankendmonitoreoLinks.entity.monitor.Estado;
import com.monitor.bankendmonitoreoLinks.entity.monitor.EstadoAnuncio;

public class EstadoAnuncioImp implements IEstadoAnuncio {
	
	private Log logObject = new Log("logs");
	private Logger log = logObject.getLogger();

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
			log.error("Error al guardar estado de anuncio "+ ex);
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
			log.error("Error al verificari si existe Estado de anuncio"+ e);
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
				CuentaFB cuentaFB = new CuentaFB();
				String idCuentaFB = rs.getString("cuenta_fb");
				String estadoAN = rs.getString("estado");
				int estadoAnuncioId = Integer.parseInt(estadoAN);

				estado.setIdEstado(estadoAnuncioId);
				anuncio.setIdAnuncio(rs.getString("anuncio"));
				cuentaFB.setIdCuenta(idCuentaFB);
				anuncio.setCuentaFB(cuentaFB);
				estadoAnuncio = new EstadoAnuncio(id, title, metaDescription, estado, anuncio);

				estados.add(estadoAnuncio);

			}

		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
			log.error("Error al listar anuncios"+ex);
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
			log.error("Error al actualizar estado de anuncio "+ex);
		} finally {

			Conector.close(stmt);
			Conector.close(conn);
		}
		return rows;

	}

	
	public static void main(String[] args) {
		EstadoAnuncioImp anuncioImp= new EstadoAnuncioImp();
		List<EstadoAnuncio> estados= new ArrayList<>();
				estados=anuncioImp.obtener();
				System.out.println(estados);
				
	
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
					"select es.id_estado_anuncio,es.meta_description,es.title,es.code_status,es.mensaje,adc.link,es.estado,ad.id_anuncio,ad.preview_shareable_link,ad.ad_creative,cu.id_cuentafb,cu.nombre_cuenta "
							+ "from estado_anuncio es " + "inner join anuncio as ad on " + "ad.id_anuncio = es.anuncio "
							+ "inner join ad_creative as adc on " + "ad.ad_creative=adc.id_creative "
							+ "inner join cuentafb as cu on " + "cu.id_cuentafb= ad.cuenta_fb "
							+ "where adc.link is not null and ad.status='ACTIVE'");
			rs = stmt.executeQuery();

			while (rs.next()) {
				EstadoAnuncio estadoAnuncio = new EstadoAnuncio();
				AdCreative adCreative = new AdCreative();
				Anuncio anuncio = new Anuncio();
				CuentaFB cuentaFB = new CuentaFB();

				String linkAnuncio = rs.getString("link");
				String estadoid = rs.getString("id_estado_anuncio");
				Long idAdCreative = rs.getLong("ad_creative");
				String idCuentaFB = rs.getString("id_cuentafb");
				String nombreCuenta = rs.getString("nombre_cuenta");
				String idAnuncio = rs.getString("id_anuncio");
				String code = rs.getString("code_status");
				String mensaje = rs.getString("mensaje");
				String previewLink=rs.getString("preview_shareable_link");
				Integer codeStatus = Integer.parseInt(code);

				long idestado = Long.parseLong(estadoid);
				adCreative.setIdCreative(idAdCreative);
				adCreative.setLink(linkAnuncio);
				anuncio.setAdCreative(adCreative);
				cuentaFB.setIdCuenta(idCuentaFB);
				cuentaFB.setNombreCuenta(nombreCuenta);

				anuncio.setCuentaFB(cuentaFB);
				anuncio.setIdAnuncio(idAnuncio);
				anuncio.setPreview_shareable_link(previewLink);

				estadoAnuncio.setIdEstadoAnuncio(idestado);
				estadoAnuncio.setCode(codeStatus);
				estadoAnuncio.setMensaje(mensaje);

				estadoAnuncio.setAnuncio(anuncio);
				estados.add(estadoAnuncio);

			}

		}

		catch (SQLException e) {
			e.printStackTrace();
			log.error("Error al obtener estados de anuncios: "+e);
		} finally {
			Conector.close(rs);
			Conector.close(stmt);
			Conector.close(conn);
		}
		return estados;

	}

}
