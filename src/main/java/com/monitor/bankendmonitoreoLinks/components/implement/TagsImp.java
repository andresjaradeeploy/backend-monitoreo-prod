package com.monitor.bankendmonitoreoLinks.components.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.monitor.bankendmonitoreoLinks.components.conector.Conector;
import com.monitor.bankendmonitoreoLinks.dao.ITagDao;
import com.monitor.bankendmonitoreoLinks.dao.Label;
import com.monitor.bankendmonitoreoLinks.dao.ReportLabels;
import com.monitor.bankendmonitoreoLinks.entity.monitor.CuentaFB;
import com.monitor.bankendmonitoreoLinks.entity.pages.Tags;

public class TagsImp  implements ITagDao{
	
	
	/*private static final String SQL_SELECT ="SELECT distinct name_tag, count(name_tag) as cantidad "
			+ "FROM tags "
			+ "group by name_tag ";*/
	
	private static final String SQL_SELECT ="SELECT distinct name_tag, count(name_tag) as cantidad "
			+ "	FROM tags as ta inner join post as po on "
			+ "	ta.post_id_post =po.id_post "
			+ "	inner join page pa on "
			+ "	po.page_id_page = pa.id_page "
			+ "	where pa.id_page  LIKE ? "
			+ "	group by name_tag ";
	
	
	private static final String SQL_SELECT_REPORT ="select "
			+ "distinct name_tag, "
			+ "count(name_tag) as cantidad, "
			+ "sum(p.love + p.likes + p.wow + p.sorry + p.wow + p.haha + p.anger) as reactions "
			+ ", "
			+ "sum(p.post_impressions_unique) as impresiones, "
			+ "TRUNCATE(((sum(p.love+p.likes+p.wow+p.sorry+p.wow+p.haha+p.anger))/(sum(p.post_impressions_unique))*100),2) as tasa "
			+ "from "
			+ "tags as ta "
			+ "inner join post as p on "
			+ "ta.post_id_post = p.id_post "
			+ "where "
			+ "p.page_id_page = ? "
			+ "group by "
			+ "name_tag "
			+ "order by "
			+ "cantidad desc ";
	
	
			
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

	@Override
	public List<Label> obtenerLabels(String page) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<Label> labels = new ArrayList<>();

		try {

			conn = Conector.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT);
			stmt.setString(1, page);
			rs = stmt.executeQuery();
			while (rs.next()) {
					Label label= new Label();
				String name_tag = rs.getString("name_tag");
				
				
				String cantidadString= rs.getString("cantidad");
				
					label.setText(name_tag);
					label.setCount(Integer.parseInt(cantidadString));
					
					labels.add(label);

			}
			return labels;

		} catch (SQLException ex) {
			
			
		} finally {

			Conector.close(stmt);
			Conector.close(conn);
			Conector.close(rs);

		}
		return labels;
	}
	
	@Override
	public List<ReportLabels> obtenerReportByPage(String page) {
		NumberFormat formatter = NumberFormat.getInstance(new Locale("en_US"));
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ReportLabels> reportLabels = new ArrayList<>();
		
		try {

			conn = Conector.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_REPORT);
			stmt.setString(1, page);
			rs = stmt.executeQuery();
			while (rs.next()) {
				ReportLabels report = new ReportLabels();

				String nameTag = rs.getString("name_tag");
				Integer cantidadTags=rs.getInt("cantidad");
				Integer reacciones=rs.getInt("reactions");
				Double impresiones= rs.getDouble("impresiones");
				Double tasa=rs.getDouble("tasa");
				
				
				String impresionesFormateados=formatter.format(impresiones);
System.out.println("impresiones"+impresionesFormateados);

//String str = String.format("%,d", impresiones);


			
			report.setNameTag(nameTag);
			report.setCantidadTags(cantidadTags);
			report.setReactions(reacciones);
			report.setImpressions(impresiones);
			report.setTasaInteracciones(tasa);
					
					reportLabels.add(report);

			}
			return reportLabels;

		} catch (SQLException ex) {
			
			
		} finally {

			Conector.close(stmt);
			Conector.close(conn);
			Conector.close(rs);

		}
		return reportLabels;
	}
}
