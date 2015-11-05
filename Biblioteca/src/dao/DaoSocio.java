package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexiones.Conexion;
import entidades.Socio;

public class DaoSocio {

	public DaoSocio() {
	}

	/*******************************************************************************************************************/

	// LISTADO DE SOCIOS

	public ArrayList<Socio> listadoSocios(int pagina, int numRegPag) throws SQLException, Exception {
		ArrayList<Socio> listaSocios;
		listaSocios = new ArrayList<Socio>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			Conexion miconex = new Conexion();
			con = miconex.getConexion();
			String ordenSql = "SELECT IDSOCIO, EMAIL, NOMBRE, DIRECCION "
					+ "FROM (SELECT ROWNUM FILA, IDSOCIO, EMAIL, NOMBRE, DIRECCION "
					+ "FROM (SELECT IDSOCIO, EMAIL, NOMBRE, DIRECCION " + "FROM SOCIO ORDER BY NOMBRE))"
					+ "WHERE FILA >=? AND FILA <=?";
			st = con.prepareStatement(ordenSql);
			st.setInt(1, (pagina * numRegPag) + 1);
			st.setInt(2, (pagina * numRegPag) + numRegPag);
			rs = st.executeQuery();
			while (rs.next()) {
				Socio miSocio = new Socio();
				miSocio.setIdsocio(rs.getLong("IDSOCIO"));
				miSocio.setEmail(rs.getString("EMAIL"));
				miSocio.setNombre(rs.getString("NOMBRE"));
				miSocio.setDireccion(rs.getString("DIRECCION"));
				listaSocios.add(miSocio);
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
		return listaSocios;
	}

	/*******************************************************************************************************************/

	// CONTAR SOCIOS

	public int contarSocios() throws SQLException, Exception {
		int totalSocios = 0;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			Conexion miconex = new Conexion();
			con = miconex.getConexion();
			con.setAutoCommit(false);
			String ordenSQL = "SELECT * FROM SOCIO";
			st = con.prepareStatement(ordenSQL);
			rs = st.executeQuery();
			while (rs.next()) {
				totalSocios++;
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
		return totalSocios;
	}

	/*************************************************************************************************************************/

	// BUSCAR SOCIO

	public ArrayList<Socio> buscarSocio(String cadena) throws SQLException, Exception {
		ArrayList<Socio> listasocios;
		listasocios = new ArrayList<Socio>();
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			Conexion miconex = new Conexion();
			con = miconex.getConexion();
			con.setAutoCommit(false);
			String ordenSQL = "SELECT * FROM SOCIO WHERE UPPER(NOMBRE) LIKE UPPER(?)";
			st = con.prepareStatement(ordenSQL);
			st.setString(1, "%" + cadena + "%");
			rs = st.executeQuery();
			while (rs.next()) {
				Socio miSocio = new Socio();
				miSocio.setIdsocio(rs.getLong("IDSOCIO"));
				miSocio.setEmail(rs.getString("EMAIL"));
				miSocio.setNombre(rs.getString("NOMBRE"));
				miSocio.setDireccion(rs.getString("DIRECCION"));
				listasocios.add(miSocio);
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
		return listasocios;
	}

	/***************************************************************************************************************/

	// MODIFICAR SOCIO

	public void modificarSocio(String idsocio, String nombre, String direccion, String email)
			throws SQLException, Exception {
		Connection con = null;
		PreparedStatement st = null;
		String OrdenSQL = "UPDATE SOCIO SET NOMBRE = ?,EMAIL = ?,DIRECCION = ? WHERE IDSOCIO = ?";
		Conexion miconex = new Conexion();
		try {
			con = miconex.getConexion();
			con.setAutoCommit(false);
			st = con.prepareStatement(OrdenSQL);
			st.setString(1, nombre);
			st.setString(2, email);
			st.setString(3, direccion);
			st.setString(4, idsocio);
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

	public void modificarDatosPersonales(String nombre, String direccion, String email) throws SQLException, Exception {
		Connection con = null;
		PreparedStatement st = null;
		String OrdenSQL = "UPDATE SOCIO SET NOMBRE = ?,DIRECCION = ? WHERE EMAIL = ?";
		Conexion miconex = new Conexion();
		try {
			con = miconex.getConexion();
			con.setAutoCommit(false);
			st = con.prepareStatement(OrdenSQL);
			st.setString(1, nombre);
			st.setString(2, direccion);
			st.setString(3, email);
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

	public void insertarSocio(Socio s) throws SQLException, Exception {
		Connection con = null;
		PreparedStatement st = null;
		String OrdenSQLSocios = "INSERT INTO SOCIO VALUES(S_SOCIO.NEXTVAL,?,?,?)";
		String OrdenSQLUsuarios = "INSERT INTO USUARIOS VALUES (?,MD5HASH(?))";
		String OrdenSQLGrupos = "INSERT INTO GRUPOS VALUES (?,?)";
		Conexion miconex = new Conexion();
		try {
			con = miconex.getConexion();
			con.setAutoCommit(false);

			st = con.prepareStatement(OrdenSQLSocios);
			st.setString(1, s.getEmail());
			st.setString(2, s.getNombre());
			st.setString(3, s.getDireccion());
			st.executeUpdate();
			st.close();

			st = con.prepareStatement(OrdenSQLUsuarios);
			st.setString(1, s.getEmail());
			st.setString(2, s.getPassword());
			st.executeUpdate();
			st.close();

			st = con.prepareStatement(OrdenSQLGrupos);
			st.setString(1, "sociosbiblioteca");
			st.setString(2, s.getEmail());
			st.executeUpdate();
			st.close();

		} catch (SQLException se) {
			con.rollback();
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
