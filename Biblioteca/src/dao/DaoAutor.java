package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexiones.Conexion;
import entidades.Autor;

public class DaoAutor {

	public DaoAutor() {
	}

	public ArrayList<Autor> listadoAutores() throws SQLException, Exception {
		ArrayList<Autor> listaautores;
		listaautores = new ArrayList<Autor>();
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		try {
			Conexion miconex = new Conexion();
			con = miconex.getConexion();
			st = con.createStatement();
			String ordenSQL = "SELECT * FROM AUTOR ORDER BY NOMBRE";
			rs = st.executeQuery(ordenSQL);
			while (rs.next()) {
				Autor miAutor = new Autor();
				miAutor.setIdautor(rs.getLong("IDAUTOR"));
				miAutor.setNombre(rs.getString("NOMBRE"));
				miAutor.setFecha(rs.getDate("FECHANACIMIENTO"));
				listaautores.add(miAutor);
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
		System.out.println("El listado de socios se devuelve");
		return listaautores;
	}

	// -------------------------------------------------------------------------------------------//
	// insertar autores
	public void insertarAutor(Autor a) throws SQLException, Exception {
		Connection con = null;
		PreparedStatement st = null;
		String OrdenSQL = "INSERT INTO AUTOR VALUES(S_AUTOR.NEXTVAL,?,?)";
		Conexion miconex = new Conexion();
		try {
			con = miconex.getConexion();
			con.setAutoCommit(false);
			st = con.prepareStatement(OrdenSQL);
			st.setString(1, a.getNombre());
			st.setDate(2, a.getFecha());
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
