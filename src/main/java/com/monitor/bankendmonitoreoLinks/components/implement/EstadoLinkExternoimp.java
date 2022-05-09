package com.monitor.bankendmonitoreoLinks.components.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.monitor.bankendmonitoreoLinks.components.conector.Conector;
import com.monitor.bankendmonitoreoLinks.dao.IEstadoLinkExterno;
import com.monitor.bankendmonitoreoLinks.entity.monitor.AdCreative;
import com.monitor.bankendmonitoreoLinks.entity.monitor.Anuncio;
import com.monitor.bankendmonitoreoLinks.entity.monitor.Cuenta;
import com.monitor.bankendmonitoreoLinks.entity.monitor.CuentaFB;
import com.monitor.bankendmonitoreoLinks.entity.monitor.Estado;
import com.monitor.bankendmonitoreoLinks.entity.monitor.EstadoAnuncio;
import com.monitor.bankendmonitoreoLinks.entity.monitor.EstadoLinkExterno;
import com.monitor.bankendmonitoreoLinks.entity.monitor.LinkExterno;

public class EstadoLinkExternoimp implements IEstadoLinkExterno{

	private static final String SQL_INSERT = "INSERT INTO estado_link_externo(code_status,link_externo)" + " VALUES(?,?)";
	
	private static final String SQL_SELECT_BY_ID = "SELECT link_externo " + " FROM estado_link_externo WHERE link_externo = ?";

	private static final String SQL_UPDATE = "UPDATE estado_link_externo "
			+ " SET meta_description=?, title=?, estado=? , code_status=?, mensaje=?, resultado_busqueda=?  WHERE id_estado_link_externo=?";

	
	@Override
	public int guardar(EstadoLinkExterno estadoLinkExterno, LinkExterno linkExterno) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;

		try {

			conn = Conector.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT);
			stmt.setInt(1, 0);
			stmt.setLong(2, linkExterno.getIdLink());

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
	public List<EstadoLinkExterno> listarEstadosLinks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean verificarSiExisteEstadoLinkExterno(Long LinkExterno) {
		boolean res = false;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = Conector.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
			stmt.setLong(1, LinkExterno);
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
	public int actualizar(EstadoLinkExterno estadoLinkExterno, Estado estado) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;

		try {

			conn = Conector.getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE);

			stmt.setString(1, estadoLinkExterno.getMetaDescription());
			stmt.setString(2, estadoLinkExterno.getTitle());
			stmt.setInt(3, estado.getIdEstado());
			stmt.setInt(4, estadoLinkExterno.getCode());
			stmt.setString(5, estadoLinkExterno.getMensaje());
			stmt.setString(6,estadoLinkExterno.getResultadoBusquedaPalabras());
			stmt.setLong(7, estadoLinkExterno.getIdEstadoLinkExterno());
			

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
	public List<EstadoLinkExterno> obtenerEstadosExternos() {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<EstadoLinkExterno> estados = new ArrayList<>();
		try {

			conn = Conector.getConnection();
			stmt = conn.prepareStatement(
					"select ele.id_estado_link_externo, "
					+ "	ele.code_status , "
					+ "	ele.correo_alerta, "
					+ "	ele.mensaje, "
					+ "	ele.meta_description , "
					+ "	ele.resultado_busqueda, "
					+ "	ele.title, "
					+ " le.url, "
					+ " le.id_link , "
					+ "	le.plataforma, "
					+ "	c.nombre_cuenta, "
					+ "	c.id_cuenta, "
					
					+ "	ele.estado "
					+ " from estado_link_externo ele inner join "
					+ " link_externo le  on "
					+ " ele.link_externo = le.id_link "
					+ " inner join cuenta c on "
					+ " le.cuenta_id_cuenta = c.id_cuenta ");
			rs = stmt.executeQuery();

			while (rs.next()) {
				EstadoLinkExterno estadoLinkExterno = new EstadoLinkExterno();
				LinkExterno linkExterno = new LinkExterno();
				Estado estado = new Estado();
				Cuenta cuenta = new Cuenta();
				//id_estado_link_externo	code_status	correo_alerta	mensaje	meta_description	resultado_busqueda	title	estado	link_externo	id_link	descripcion	plataforma	url	cuenta_id_cuenta	id_estado	nombre_estado	id_cuenta	nombre_cuenta
				String id_estado_link_externo = rs.getString("id_estado_link_externo");
				String code_status = rs.getString("code_status");
				Long correo_alerta = rs.getLong("correo_alerta");
				String mensaje = rs.getString("mensaje");
				String meta_description = rs.getString("meta_description");
				String resultado_busqueda = rs.getString("resultado_busqueda");
				String title = rs.getString("title");
				String url = rs.getString("url");
				String idLinksExterno=rs.getString("id_link");
				String nombre_cuenta=rs.getString("nombre_cuenta");
				String id_cuenta=rs.getString("id_cuenta");
				
				Integer estadoid=rs.getInt("estado");
				

				
				long cuentanum=Long.parseLong(id_cuenta);
				
				estado.setIdEstado(estadoid);
				
				
				cuenta.setIdCuenta(cuentanum);
				cuenta.setNombreCuenta(nombre_cuenta);

				linkExterno.setIdLink(Long.parseLong(idLinksExterno));
				linkExterno.setUrl(url);
				linkExterno.setCuenta(cuenta);

				estadoLinkExterno.setIdEstadoLinkExterno(Long.parseLong(id_estado_link_externo));
				estadoLinkExterno.setCode(Integer.parseInt(code_status));
				estadoLinkExterno.setMensaje(mensaje);
				estadoLinkExterno.setMetaDescription(meta_description);
				estadoLinkExterno.setTitle(title);
				estadoLinkExterno.setResultadoBusquedaPalabras(resultado_busqueda);

				estadoLinkExterno.setLink_externo(linkExterno);
				estados.add(estadoLinkExterno);

			}
			for (EstadoLinkExterno estadoLinkExterno : estados) {
				System.out.println("estados"+estadoLinkExterno.getIdEstadoLinkExterno());
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
	
	public static void main(String[] args) {
		EstadoLinkExternoimp estadoLinkExternoimp = new EstadoLinkExternoimp();
		List<EstadoLinkExterno> lista;
		lista=estadoLinkExternoimp.obtenerEstadosExternos();
		System.out.println("lsita"+lista.size());
	}

}
