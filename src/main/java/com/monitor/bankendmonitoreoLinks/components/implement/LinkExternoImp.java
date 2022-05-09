package com.monitor.bankendmonitoreoLinks.components.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.monitor.bankendmonitoreoLinks.components.conector.Conector;
import com.monitor.bankendmonitoreoLinks.dao.ILinkExternoDAO;
import com.monitor.bankendmonitoreoLinks.entity.monitor.Cuenta;
import com.monitor.bankendmonitoreoLinks.entity.monitor.LinkExterno;

public class LinkExternoImp implements ILinkExternoDAO {

	@Override
	public List<LinkExterno> listarLinksExternos() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		

		List<LinkExterno> linkExternos = new ArrayList<>();

		try {

			conn = Conector.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM link_externo ");
			rs = stmt.executeQuery();
			while (rs.next()) {
				Cuenta cuenta = new Cuenta();
				LinkExterno linkExterno =  new LinkExterno();
			
				String idLinksExterno = rs.getString("id_link");
				String descripcion = rs.getString("descripcion");
				String plataforma = rs.getString("plataforma");
				String url = rs.getString("url");
				String idCuentaFB = rs.getString("cuenta_id_cuenta");
				
				linkExterno.setIdLink(Long.parseLong(idLinksExterno));
				linkExterno.setUrl(url);
				linkExterno.setDescripcion(descripcion);
				linkExterno.setPlataforma(plataforma);
				cuenta.setIdCuenta(Long.parseLong(idCuentaFB));
				linkExterno.setCuenta(cuenta);

		
				linkExternos.add(linkExterno);

			}

		} catch (SQLException ex) {
			System.err.println("err" + ex);
		
		} finally {
			Conector.close(rs);
			Conector.close(stmt);
			Conector.close(conn);
		}
		return linkExternos;
	}

}
