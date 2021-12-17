package com.monitor.bankendmonitoreoLinks.components.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.monitor.bankendmonitoreoLinks.components.conector.Conector;
import com.monitor.bankendmonitoreoLinks.dao.IOcrDao;
import com.monitor.bankendmonitoreoLinks.entity.pages.Ocr;

public class OcrImp implements IOcrDao {
	
	private static final String SQL_SELECT_BY = "SELECT id_ocr " + " FROM ocr WHERE post = ?";

	private static final String SQL_INSERT = "INSERT INTO ocr(description_ocr,post)"
			+ " VALUES(?, ?)";
	
	private static final String SQL_UPDATE = "UPDATE ocr "
			+ "SET description_ocr=?, post=?  WHERE post=?";


	@Override
	public int guardar(Ocr ocr) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;

		try {

			conn = Conector.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT);

			stmt.setString(1, ocr.getDescriptionOcr());
			stmt.setString(2, ocr.getPost().getIdPost());
			

			rows = rows + stmt.executeUpdate();

		} catch (SQLException ex) {
			System.err.println("error al guardar OCR" + ex);
		} finally {

			Conector.close(stmt);
			Conector.close(conn);
		}
		return rows;
	}
	
	@Override
	public int actualizar(Ocr ocr) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;

		try {

			conn = Conector.getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE);

			stmt.setString(1, ocr.getDescriptionOcr());
			stmt.setString(2, ocr.getPost().getIdPost());
			stmt.setString(3, ocr.getPost().getIdPost());
			

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
	public boolean verificarSiExisteOcr(String idPost) {
		boolean res = false;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = Conector.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_BY);
			stmt.setString(1, idPost);
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
	

}
