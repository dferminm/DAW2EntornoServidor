package conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	public Conexion() {
	}

	public Connection getConexion() throws SQLException, Exception {

		Connection con;
        String url="jdbc:mysql://localhost:3306/alumnos";
        String usuario="root";
        String password="";
		try {
			Class.forName("com.mysql.jdbc.Driver");//registrar el driver.
			con = DriverManager.getConnection(url,usuario,password);
			System.out.println("Conexion establecida");
		} catch (SQLException sqle) {
			System.out.println(sqle.toString());
			throw sqle;
		} catch (Exception e) {
			System.out.println(e.toString());
			throw e;
		}
		return con;
	}
}
