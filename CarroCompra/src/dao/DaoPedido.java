package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import carrocompra.CarroCompra;
import conexiones.Conexion;
import entidades.Producto;

public class DaoPedido {

	public DaoPedido() {
	}

	@SuppressWarnings("resource")
	public void aniadirPedido(int idcliente, String direccion, CarroCompra micarro) throws SQLException, Exception {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			Conexion miconex = new Conexion();
			con = miconex.getConexion();
			con.setAutoCommit(false);

			String ordenSQLSecuencia = "SELECT S_PEDIDO.NEXTVAL FROM DUAL";
			st = con.prepareStatement(ordenSQLSecuencia);
			rs = st.executeQuery();
			rs.next();
			int valorSecuencia = rs.getInt(1);

			String ordenSQL = "INSERT INTO PEDIDO VALUES(?, ?,'PENDIENTE', SYSDATE, ?, NULL)";
			st = con.prepareStatement(ordenSQL);
			st.setInt(1, valorSecuencia);
			st.setInt(2, idcliente);
			st.setString(3, direccion);
			st.executeUpdate();

			int lineaDetalle = 1;
			for (Producto p : micarro.getElementos()) {
				st = con.prepareStatement("INSERT INTO DETALLEPEDIDO VALUES(?,?,?,?,?,?)");
				st.setInt(1, valorSecuencia);
				st.setInt(2, lineaDetalle);
				st.setInt(3, p.getId());
				st.setInt(4, p.getCantidad());
				st.setDouble(5, p.getPrecioNormal());
				st.setDouble(6, (p.getPrecioNormal() * p.getCantidad()));
				lineaDetalle++;
				st.executeUpdate();
			}
			con.commit();
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

	public boolean esNumero(String cadena) {
		try {
			@SuppressWarnings("unused")
			long l = Long.parseLong(cadena);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

}
