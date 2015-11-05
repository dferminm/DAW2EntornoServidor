package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoAlumno;
import entidades.Alumno;

/**
 * Servlet implementation class Controller
 */
@WebServlet(name = "controller", urlPatterns = { "/controller" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*variable operacion que almacena la informacion volcada por el metodo request.getParameter
		 * de la variable operacion definida en el index.jsp*/
		String operacion = request.getParameter("operacion");
		
		ArrayList<Alumno> listadoAlumnos = null;
		DaoAlumno daoAlumno = null;
		
		/*creamos un switch donde introduzcamos la variable operacion y el case listarAlumnos, que será
		 * lo que introduzcamos en el enlace del index.jsp*/
		switch(operacion){
		case "listarAlumnos":
			/*creamos un nuevo daoAlumno*/
			daoAlumno = new DaoAlumno();
			try {
				listadoAlumnos = daoAlumno.listadoAlumnos();
				
				/*recupera el arrayList de listadoAlumnos*/
				request.setAttribute("listadoAlumnos", listadoAlumnos);
				
				/*indicamos la salida de listadoAlumnos*/
				request.getRequestDispatcher("/listadoalumnos.jsp").forward(request, response);
				
				/*por cada alumno del listadoAlumnos, recogemos el nombre*/
				for(Alumno a:listadoAlumnos){
					System.out.println(a.getNombre());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
