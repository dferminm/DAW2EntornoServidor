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
	
	public Producto buscarProducto(int idProducto) throws SQLException, Exception {
		Connection con = null;
		PreparedStatement st = null;
		String ordenSQL = "SELECT * FROM PRODUCTO WHERE ID = ?";
		try {
			Conexion miconex = new Conexion();
			con = miconex.getConexion();
			st = con.prepareStatement(ordenSQL);
			st.setInt(1, idProducto);
			ResultSet rs = st.executeQuery();
			rs.next();
				Producto miProducto = new Producto();
				miProducto.setId(rs.getInt("ID"));
				miProducto.setNombre(rs.getString("NOMBRE"));
				miProducto.setPrecioNormal(rs.getLong("PRECIO_NORMAL"));
				miProducto.setCantidad(1);
				return miProducto;
				
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
