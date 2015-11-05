package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoAutor;
import dao.DaoLibro;
import dao.DaoSocio;
import entidades.Autor;
import entidades.Libro;

/**
 * Servlet implementation class ControllerSocio
 */
@WebServlet("/controllerSocio")
public class ControllerSocio extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerSocio() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String operacion = request.getParameter("operacion");
		ArrayList<Autor> listadoAutores = null;
		ArrayList<Libro> listadoLibros = null;
		DaoAutor daoautor = null;

		switch (operacion) {

		/*
		 * --LISTAR AUTORES-- 1. listadoAutores es igual a el listadoAutores del
		 * daoAutor. 2. añadir al atributo listadoAutores el listado de autores.
		 * 3. redirigir a listadoAutores.jsp
		 */

		case "listarAutores":
			daoautor = new DaoAutor();
			try {
				listadoAutores = daoautor.listadoAutores();
				request.setAttribute("listadoAutores", listadoAutores);
				request.getRequestDispatcher("./admin/listadoAutores.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		/*
		 * --MODIFICAR DATOS PERSONALES--
		 */

		case "modificardatospersonales":
			try {
				DaoSocio daoSocioPersonal = new DaoSocio();
				String nombre = request.getParameter("nombre");
				String direccion = request.getParameter("direccion");
				String email = request.getUserPrincipal().toString();
				daoSocioPersonal.modificarDatosPersonales(nombre, direccion, email);
				response.sendRedirect("/Biblioteca/operacioncorrecta.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		/*
		 * --LISTAR LIBROS--
		 */

		case "buscaLibro":
			String titulo;
			String isbn;
			String nombreAutor;
			String campobusqueda = request.getParameter("campobusqueda");
			String valorbusqueda = request.getParameter("valorcampobusqueda");
			request.setAttribute("campobusqueda", campobusqueda);
			if (campobusqueda.equals("autor")) {
				titulo = "%";
				isbn = "%";
				nombreAutor = "%" + valorbusqueda + "%";
			} else if (campobusqueda.equals("titulo")) {
				titulo = "%" + valorbusqueda + "%";
				isbn = "%";
				nombreAutor = "%";
			} else {
				titulo = "%";
				isbn = valorbusqueda;
				nombreAutor = "%";
			}
			DaoLibro dao = new DaoLibro();
			try {
				listadoLibros = dao.listadoLibros(titulo, nombreAutor, isbn);
				request.setAttribute("valorcampobusqueda", valorbusqueda);
				request.setAttribute("listadoLibrosBusqueda", listadoLibros);
				request.getRequestDispatcher("socios/consultaLibro.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		/*
		 * --LOGOUT-- 1. Cerrar la sesión actual y redirigir al index.jsp.
		 */

		case "logout":
			session.invalidate();
			response.sendRedirect("index.jsp");
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

}
