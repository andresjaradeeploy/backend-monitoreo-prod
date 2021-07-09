package com.monitor.bankendmonitoreoLinks.components.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.monitor.bankendmonitoreoLinks.components.conector.Conector;
import com.monitor.bankendmonitoreoLinks.dao.ICuentaFbDeveloper;
import com.monitor.bankendmonitoreoLinks.entity.monitor.CuentaFbDeveloper;



public class CuentaFbDeveloperImp implements ICuentaFbDeveloper {

	
	private static final String SQL_SELECT_BY_ID = "SELECT id_cuenta,access_token,id_aplicacion,secret_key"
            + " FROM cuenta_fb_developer WHERE id_cuenta = ?";
	@Override
	public CuentaFbDeveloper consultar(CuentaFbDeveloper cuentaFbDeveloper) {
		
		        Connection conn = null;
		        PreparedStatement stmt = null;
		        ResultSet rs = null;
		        try {

		            conn = Conector.getConnection();

		            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
		            stmt.setInt(1, cuentaFbDeveloper.getIdCuenta());
		            rs = stmt.executeQuery();
		           

		                rs.next();
		                int id_cuenta = rs.getInt("id_cuenta");
		                String access_token = rs.getString("access_token");
		                String id_aplicacion = rs.getString("id_aplicacion");
		                String secret_key = rs.getString("secret_key");
		                

		                cuentaFbDeveloper.setIdCuenta(id_cuenta);
		                cuentaFbDeveloper.setAccessToken(access_token);
		                cuentaFbDeveloper.setIdAplicacion(id_aplicacion);
		                cuentaFbDeveloper.setSecretKey(secret_key);
		                
		        } catch (SQLException ex) {
		            ex.printStackTrace(System.out);
		        } finally {
		            Conector.close(rs);
		            Conector.close(stmt);
		            Conector.close(conn);
		        }
		        return cuentaFbDeveloper;
	}

}
