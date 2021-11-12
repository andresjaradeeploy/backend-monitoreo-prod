package com.monitor.bankendmonitoreoLinks.components.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.monitor.bankendmonitoreoLinks.components.conector.Conector;
import com.monitor.bankendmonitoreoLinks.dao.ITagDao;
import com.monitor.bankendmonitoreoLinks.entity.pages.Tags;

public class TagsImp  implements ITagDao{
	
	private static final String SQL_SELECT_BY_NAME = "SELECT name_tag " + " FROM tags WHERE name_tag = ? and post_id_post = ?";

	private static final String SQL_INSERT = "INSERT INTO tags(name_tag,post_id_post)"
			+ " VALUES(?, ?)";

	@Override
	public int guardar(Tags tag) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;

		try {

			conn = Conector.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT);

			stmt.setString(1, tag.getNameTag());
			stmt.setString(2, tag.getPost().getIdPost());
			

			rows = rows + stmt.executeUpdate();

		} catch (SQLException ex) {
			System.err.println("error al guardar post" + ex);
		} finally {

			Conector.close(stmt);
			Conector.close(conn);
		}
		return rows;
	}

	@Override
	public boolean verificarSiExisteTag(String tag, String idPost) {
		boolean res = false;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = Conector.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_BY_NAME);
			stmt.setString(1, tag);
			stmt.setString(2, idPost);
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
