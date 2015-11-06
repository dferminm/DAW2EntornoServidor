package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexiones.Conexion;

public class DaoMensajeError {

	public DaoMensajeError() {
	}
	
	public String error(String mensajeError) throws SQLException, Exception {
		String error = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			Conexion miconex = new Conexion();
			con = miconex.getConexion();
			String ordenSQL = "SELECT RESTRICCION, MENSAJE " +
							 "FROM MENSAJE_ERROR " + 
							 "WHERE RESTRICCION LIKE ?";
			st = con.prepareStatement(ordenSQL);
			st.setString(1, mensajeError);
			rs = st.executeQuery();
			while(rs.next()){
			error = rs.getString("MENSAJE");
			System.out.println(rs.getString("RESTRICCION"));
			}
		} catch (SQLException se) {
			throw se;
		} catch (Exception e) {
			throw e;
		} finally {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (con != null)
				con.close();
		}
		return error;
	}

}
