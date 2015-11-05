package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexiones.Conexion;
import entidades.Alumno;

public class DaoAlumno {
	
	public DaoAlumno() {
		
	}

	public ArrayList<Alumno> listadoAlumnos() throws Exception {
		ArrayList<Alumno> listadoAlumnos;
		listadoAlumnos = new ArrayList<Alumno>();//nuevo arrayList de alumnos
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		try {
			Conexion miconex = new Conexion();
			con = miconex.getConexion();
			st = con.createStatement();
			String ordenSQL = "SELECT * FROM ALUMNOS ORDER BY NOMBRE";//consulta a la base de datos
			rs = st.executeQuery(ordenSQL);
			while (rs.next()) {//mientras que haya valores, pasa al siguiente
				Alumno miAlumno = new Alumno();//creamos un nuevo objeto alumno
				miAlumno.setDni(rs.getString("DNI"));
				miAlumno.setNombre(rs.getString("NOMBRE"));
				miAlumno.setCurso(rs.getString("CURSO"));//vamos asignamos al objeto lo que devuelva la consulta
				listadoAlumnos.add(miAlumno);//añadimos el objeto alumno al arrayList de alumnos
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
		return listadoAlumnos;//devuelve el arraylist
	}

}

	
