package com.monitor.bankendmonitoreoLinks.components.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.monitor.bankendmonitoreoLinks.components.conector.Conector;
import com.monitor.bankendmonitoreoLinks.dao.IPalabraDao;


public class PalabraImp implements IPalabraDao {
	private static final String SQL_SELECTBYANUNCIO = "SELECT palabra "
			+ "FROM palabras "
			+ "where anuncio_id_anuncio = ? ";
			

	@Override
	public List<String> correosByAnuncio(String anuncio) {
		List<String> palabras = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		

		try {
			conn = Conector.getConnection();
			stmt = conn.prepareStatement(SQL_SELECTBYANUNCIO);
			stmt.setString(1, anuncio);
			rs = stmt.executeQuery();

			while (rs.next()) {
				palabras.add(rs.getString("palabra"));
			}

		} catch (Exception e) {
			System.err.print("Ha ocurrido un error: " + e.getMessage());
		} finally {
			Conector.close(conn);
			Conector.close(stmt);
			Conector.close(rs);
		}

		return palabras;
	}
	

}
