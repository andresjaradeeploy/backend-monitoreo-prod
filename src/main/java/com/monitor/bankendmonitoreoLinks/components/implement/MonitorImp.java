package com.monitor.bankendmonitoreoLinks.components.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.monitor.bankendmonitoreoLinks.components.conector.Conector;
import com.monitor.bankendmonitoreoLinks.dao.IMonitorDao;
import com.monitor.bankendmonitoreoLinks.entity.monitor.Anuncio;
import com.monitor.bankendmonitoreoLinks.entity.monitor.Estado;
import com.monitor.bankendmonitoreoLinks.entity.monitor.EstadoAnuncio;
import com.monitor.bankendmonitoreoLinks.entity.monitor.Monitor;

public class MonitorImp implements IMonitorDao{

	
	private static final String SQL_INSERT = "INSERT INTO monitor()"
			+ " VALUES()";
	
	private static final String SQL_ULTIMO_INSERT= "SELECT *"
			+ "FROM monitor"
			+ " ORDER BY id_monitor DESC"
			+ " LIMIT 1";
	
	private static final String SQL_SELECT_BY_ID = "SELECT id_monitor " + " FROM monitor WHERE id_monitor = ?";
	
	@Override
	public long guardar() {

		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;
		
		ArrayList<String> cuentas=CuentaFBImp.obtenerCuentas();
		for(int i=0;i<cuentas.size();i++) {
		try {

			conn = Conector.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT);

			
			rows = rows + stmt.executeUpdate();
			System.out.println(rows);
		}
		
		catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally {

			Conector.close(stmt);
			Conector.close(conn);
		}
		
		}
		return rows;
	}
	
	@Override
	public long ultimoInsertado() {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Monitor monitor= new Monitor();
		
		
		try {
			conn = Conector.getConnection();
			stmt = conn.prepareStatement(SQL_ULTIMO_INSERT);
			
			
			rs = stmt.executeQuery();

			while (rs.next())
			{
				monitor.setIdMonitor(rs.getLong("id_monitor"));
			}
			System.out.println("monitor"+monitor.getIdMonitor());
		} catch (Exception e) {
			System.err.print("Ha ocurrido un error: " + e.getMessage());
		} finally {
			Conector.close(conn);
			Conector.close(stmt);
			// Conector.close(rs);
		}
		return 0;
		
	}
	
	
	@Override
	public boolean verificarSiExisteMonitor(long idAnuncio) {
		boolean res = false;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = Conector.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
			stmt.setLong(1, idAnuncio);
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
			// Conector.close(rs);
		}
		return res;
	}
	
	public static void main(String[] args) {
		MonitorImp monitorImp= new MonitorImp();
		monitorImp.guardar();
		monitorImp.ultimoInsertado();
	}

	@Override
	public List<Monitor> listarMonitores() {
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Monitor monitor = null;

        List<Monitor> monitores = new ArrayList<>();

        try {

            conn = Conector.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM monitor");
            rs = stmt.executeQuery();
            while (rs.next()) {
                String idMonitor = rs.getString("id_monitor");
                Long id=Long.parseLong(idMonitor);
                
                
               
               monitor = new Monitor(id);
               
                monitores.add(monitor);

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conector.close(rs);
            Conector.close(stmt);
            Conector.close(conn);
        }
        return monitores;
	}
}
