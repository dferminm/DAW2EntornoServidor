package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexiones.Conexion;
import entidades.Libro;

public class DaoLibro {

	public DaoLibro() {
	}

	public ArrayList<Libro> listadoLibros(String titulo, String autor, String isbn) throws SQLException, Exception {

		ArrayList<Libro> listaLibros;
		listaLibros = new ArrayList<Libro>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			Conexion miconex = new Conexion();
			con = miconex.getConexion();
			String ordenSQL = "SELECT T.ISBN,TITULO,AUTOR.NOMBRE AUTOR, EJEMPLARESTOTALES, EJEMPLARESENPRESTAMO,(EJEMPLARESTOTALES-EJEMPLARESENPRESTAMO)EJEMPLARESDISPONIBLES"
					+ " FROM LIBRO T,AUTOR," + "("
					+ " SELECT A.ISBN,EJEMPLARES EJEMPLARESTOTALES,NVL(EJEMPLARESPRESTADOS,0)EJEMPLARESENPRESTAMO"
					+ " FROM" + "(SELECT L.ISBN,COUNT(*)EJEMPLARES" + " FROM LIBRO L,EJEMPLAR E"
					+ " WHERE L.ISBN=E.ISBN" + " GROUP BY L.ISBN)A," + " (SELECT ISBN,COUNT(*) EJEMPLARESPRESTADOS"
					+ " FROM PRESTAMO P,EJEMPLAR E" + " WHERE P.IDEJEMPLAR=E.IDEJEMPLAR" + " GROUP BY ISBN)B"
					+ " WHERE A.ISBN=B.ISBN(+))A" + " WHERE T.ISBN=A.ISBN" + " AND T.IDAUTOR=AUTOR.IDAUTOR"
					+ " AND TRANSLATE(UPPER(AUTOR.NOMBRE),'Á,É,Í,Ó,Ú','A,E,I,O,U') LIKE TRANSLATE(UPPER(?),'Á,É,Í,Ó,Ú','A,E,I,O,U')"
					+ " AND TRANSLATE(UPPER(T.TITULO),'Á,É,Í,Ó,Ú','A,E,I,O,U') LIKE TRANSLATE(UPPER(?),'Á,É,Í,Ó,Ú','A,E,I,O,U')"
					+ " AND T.ISBN LIKE ?" + " ORDER BY AUTOR,TITULO";
			st = con.prepareStatement(ordenSQL);
			st.setString(1, autor);
			st.setString(2, titulo);
			st.setString(3, isbn);
			rs = st.executeQuery();
			while (rs.next()) {
				Libro miLibro = new Libro();
				miLibro.setNombreAutor(rs.getString("AUTOR"));
				miLibro.setTitulo(rs.getString("TITULO"));
				miLibro.setEjemplaresTotales(rs.getInt("EJEMPLARESTOTALES"));
				miLibro.setEjemplaresEnPrestamo(rs.getInt("EJEMPLARESENPRESTAMO"));
				miLibro.setEjemplaresDisponibles(rs.getInt("EJEMPLARESDISPONIBLES"));
				listaLibros.add(miLibro);
			}
			for (Libro s : listaLibros) {
				System.out.println(s.getTitulo());
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
		return listaLibros;
	}

	public ArrayList<Libro> listadoLibrosEliminar(String titulo, String autor, String isbn)
			throws SQLException, Exception {

		ArrayList<Libro> listaLibros;
		listaLibros = new ArrayList<Libro>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			Conexion miconex = new Conexion();
			con = miconex.getConexion();
			String ordenSQL = "SELECT T.ISBN,TITULO,AUTOR.NOMBRE AUTOR" + " FROM LIBRO T,AUTOR," + "("
					+ " SELECT A.ISBN" + " FROM" + "(SELECT L.ISBN" + " FROM LIBRO L,EJEMPLAR E"
					+ " WHERE L.ISBN=E.ISBN" + " GROUP BY L.ISBN)A," + " (SELECT ISBN" + " FROM PRESTAMO P,EJEMPLAR E"
					+ " WHERE P.IDEJEMPLAR=E.IDEJEMPLAR" + " GROUP BY ISBN)B" + " WHERE A.ISBN=B.ISBN(+))A"
					+ " WHERE T.ISBN=A.ISBN" + " AND T.IDAUTOR=AUTOR.IDAUTOR"
					+ " AND TRANSLATE(UPPER(AUTOR.NOMBRE),'Á,É,Í,Ó,Ú','A,E,I,O,U') LIKE TRANSLATE(UPPER(?),'Á,É,Í,Ó,Ú','A,E,I,O,U')"
					+ " AND TRANSLATE(UPPER(T.TITULO),'Á,É,Í,Ó,Ú','A,E,I,O,U') LIKE TRANSLATE(UPPER(?),'Á,É,Í,Ó,Ú','A,E,I,O,U')"
					+ " AND T.ISBN LIKE ?" + " ORDER BY AUTOR,TITULO";
			st = con.prepareStatement(ordenSQL);
			st.setString(1, autor);
			st.setString(2, titulo);
			st.setString(3, isbn);
			rs = st.executeQuery();
			while (rs.next()) {
				Libro miLibro = new Libro();
				miLibro.setTitulo(rs.getString("TITULO"));
				miLibro.setNombreAutor(rs.getString("AUTOR"));
				miLibro.setIsbn(rs.getString("ISBN"));
				listaLibros.add(miLibro);
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
		return listaLibros;
	}

}
