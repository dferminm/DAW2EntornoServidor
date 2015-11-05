package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexiones.Conexion;
import entidades.Prestamo;

public class DaoPrestamo {

	public DaoPrestamo() {

	}

	// LISTADO DE SOCIOS MOROSOS

	public ArrayList<Prestamo> listadoSociosMorosos() throws SQLException, Exception {
		ArrayList<Prestamo> listaSociosMorosos;
		listaSociosMorosos = new ArrayList<Prestamo>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			Conexion miconex = new Conexion();
			con = miconex.getConexion();
			String ordenSql = "SELECT PRESTAMO.IDSOCIO AS IDSOCIO, SOCIO.NOMBRE AS NOMBRE " + "FROM PRESTAMO "
					+ "INNER JOIN SOCIO ON SOCIO.IDSOCIO = PRESTAMO.IDSOCIO "
					+ "WHERE ROUND(SYSDATE-PRESTAMO.FECHALIMITEDEVOLUCION) > 0";
			st = con.prepareStatement(ordenSql);
			rs = st.executeQuery();
			while (rs.next()) {
				Prestamo miPrestamo = new Prestamo();
				miPrestamo.setIdsocio(rs.getLong("IDSOCIO"));
				miPrestamo.setNombre(rs.getString("NOMBRE"));
				listaSociosMorosos.add(miPrestamo);
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
		return listaSociosMorosos;
	}

	// LISTADO DE LIBROS DE LOS SOCIOS MOROSOS

	public ArrayList<Prestamo> listadoLibros(String idsocio) throws SQLException, Exception {
		ArrayList<Prestamo> listaprestamos;
		listaprestamos = new ArrayList<Prestamo>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			Conexion miconex = new Conexion();
			con = miconex.getConexion();
			String ordenSQL = "SELECT LIBRO.TITULO AS TITULO, ROUND(SYSDATE-PRESTAMO.FECHALIMITEDEVOLUCION) AS DIASDEMORA "
					+ "FROM LIBRO INNER JOIN EJEMPLAR ON LIBRO.ISBN=EJEMPLAR.ISBN "
					+ "INNER JOIN PRESTAMO ON EJEMPLAR.IDEJEMPLAR = PRESTAMO.IDEJEMPLAR "
					+ "WHERE PRESTAMO.IDSOCIO = ?";
			st = con.prepareStatement(ordenSQL);
			st.setString(1, idsocio);
			rs = st.executeQuery();
			while (rs.next()) {
				Prestamo miPrestamo = new Prestamo();
				miPrestamo.setTitulo(rs.getString("TITULO"));
				miPrestamo.setDiasdemora(rs.getInt("DIASDEMORA"));
				listaprestamos.add(miPrestamo);
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
		return listaprestamos;
	}

	// ELIMINAR PRESTAMO

	public void eliminarPrestamo(String idejemplar) throws SQLException, Exception {

		Connection con = null;
		PreparedStatement st = null;
		try {
			Conexion miconex = new Conexion();
			con = miconex.getConexion();
			String ordenSQL = "DELETE FROM PRESTAMO WHERE IDEJEMPLAR = ?";
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

	// AÑADIR PRESTAMO

	public void añadirPrestamo(String idsocio, String idejemplar) throws SQLException, Exception {

		Connection con = null;
		PreparedStatement st = null;
		try {
			Conexion miconex = new Conexion();
			con = miconex.getConexion();
			String ordenSQL = "INSERT INTO PRESTAMO (IDEJEMPLAR, IDSOCIO, FECHAPRESTAMO) " + "VALUES (?, ?, SYSDATE)";
			con.setAutoCommit(false);
			st = con.prepareStatement(ordenSQL);
			st.setString(1, idejemplar);
			st.setString(2, idsocio);
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
