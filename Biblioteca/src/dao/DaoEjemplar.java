package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexiones.Conexion;
import entidades.Ejemplar;

public class DaoEjemplar {

	public DaoEjemplar() {
	}

	public ArrayList<Ejemplar> listadoEjemplaresEliminar(String isbn) throws SQLException, Exception {

		ArrayList<Ejemplar> listaEjemplares;
		listaEjemplares = new ArrayList<Ejemplar>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			Conexion miconex = new Conexion();
			con = miconex.getConexion();
			String ordenSQL = "SELECT IDEJEMPLAR, ISBN " + "FROM EJEMPLAR " + "WHERE ISBN = ?";
			st = con.prepareStatement(ordenSQL);
			st.setString(1, isbn);
			rs = st.executeQuery();
			while (rs.next()) {
				Ejemplar miEjemplar = new Ejemplar();
				miEjemplar.setIdejemplar(rs.getLong("IDEJEMPLAR"));
				miEjemplar.setIsbn(rs.getString("ISBN"));
				listaEjemplares.add(miEjemplar);
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
		return listaEjemplares;
	}

	public void eliminarEjemplar(String idejemplar) throws SQLException, Exception {

		Connection con = null;
		PreparedStatement st = null;
		try {
			Conexion miconex = new Conexion();
			con = miconex.getConexion();
			String ordenSQL = "UPDATE EJEMPLAR " + "SET BAJA = 'S' " + "WHERE IDEJEMPLAR = ?";
			con.setAutoCommit(false);
			st = con.prepareStatement(ordenSQL);
			st.setString(1, idejemplar);
			st.executeUpdate();
		} catch (SQLException se) {
			throw se;
		} catch (Exception e) {
			throw e;
		} finally {
			if (st != null)
				st.close();
			if (con != null)
				con.close();
		}
	}

}
