package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexiones.Conexion;
import entidades.Producto;

public class DaoProducto {

	public DaoProducto() {
	}

	public ArrayList<Producto> listadoProductos() throws SQLException, Exception {
		ArrayList<Producto> listaProductos;
		listaProductos = new ArrayList<Producto>();
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		try {
			Conexion miconex = new Conexion();
			con = miconex.getConexion();
			st = con.createStatement();
			String ordenSQL = "SELECT * FROM PRODUCTO ORDER BY NOMBRE";
			rs = st.executeQuery(ordenSQL);
			while (rs.next()) {
				Producto miProducto = new Producto();
				miProducto.setId(rs.getInt("ID"));
				miProducto.setNombre(rs.getString("NOMBRE"));
				miProducto.setPrecioNormal(rs.getLong("PRECIO_NORMAL"));
				listaProductos.add(miProducto);
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
		return listaProductos;
	}
	
	public Producto buscarProducto(String idProducto) throws SQLException, Exception {
		Producto productoFinal = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			Conexion miconex = new Conexion();
			con = miconex.getConexion();
			con.setAutoCommit(false);
			String ordenSQL = "SELECT * FROM PRODUCTO WHERE ID = ?";
			st = con.prepareStatement(ordenSQL);
			st.setString(1, idProducto);
			rs = st.executeQuery();
			while (rs.next()) {
				Producto miProducto = new Producto();
				miProducto.setId(rs.getInt("ID"));
				miProducto.setNombre(rs.getString("NOMBRE"));
				miProducto.setPrecioNormal(rs.getLong("PRECIO_NORMAL"));
				miProducto = productoFinal;
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
		return productoFinal;
	}

}
