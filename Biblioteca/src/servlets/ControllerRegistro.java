package servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.DaoSocio;
import entidades.Socio;

/**
 * Servlet implementation class ControllerRegistro
 */
@WebServlet("/controllerRegistro")
public class ControllerRegistro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerRegistro() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operacion = request.getParameter("operacion");
		DaoSocio daosocio = null;

		switch (operacion) {
		/*
		 * --ALTA DE UN SOCIO--
		 */

		case "altasocio":
			daosocio = new DaoSocio();
			try {
				Socio nuevoSocio = new Socio();
				nuevoSocio.setNombre(request.getParameter("nombre"));
				nuevoSocio.setEmail(request.getParameter("email"));
				nuevoSocio.setDireccion(request.getParameter("direccion"));
				nuevoSocio.setPassword(request.getParameter("password"));
				daosocio.insertarSocio(nuevoSocio);
				request.getRequestDispatcher("seguridad/identificate.jsp").forward(request, response);

			} catch (SQLException e) {
				e.printStackTrace();
				procesarErrorSQL(request, response, e);
			} catch (Exception e) {
				e.printStackTrace();
				procesarError(request, response, e);
			}
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void procesarError(HttpServletRequest request, HttpServletResponse response, Exception e)
			throws ServletException, IOException {
		String mensajeError = e.getMessage();
		request.setAttribute("error", mensajeError);
		request.getRequestDispatcher("error.jsp").forward(request, response);
	}

	protected void procesarErrorSQL(HttpServletRequest request, HttpServletResponse response, SQLException e)
			throws ServletException, IOException {
		int codigoError = e.getErrorCode();
		String mensajeError;
		switch (codigoError) {
		case 30006:
			mensajeError = "Registro en proceso de modificación. Inténtelo más tarde";
			break;

		case 00001:
			mensajeError = "Ya hay un usuario con esa direccion";
			break;

		default:
			mensajeError = e.getMessage();
		}
		request.setAttribute("error", mensajeError);
		request.getRequestDispatcher("error.jsp").forward(request, response);
	}

}
