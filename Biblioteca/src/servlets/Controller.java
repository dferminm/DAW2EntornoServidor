package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoAutor;
import dao.DaoEjemplar;
import dao.DaoLibro;
import dao.DaoMensajeError;
import dao.DaoPrestamo;
import dao.DaoSocio;
import entidades.Autor;
import entidades.Ejemplar;
import entidades.Libro;
import entidades.Prestamo;
import entidades.Socio;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
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
		ArrayList<Socio> listadoSocios = null;
		ArrayList<Socio> buscaSocio = null;
		ArrayList<Prestamo> listadoSociosMorosos = null;
		DaoEjemplar daoEjemplar = null;
		ArrayList<Ejemplar> listaEjemplaresEliminar = null;
		DaoSocio daosocio = null;
		DaoAutor daoautor = null;
		// Paginado->
		int totalregistros = 0;
		int paginaactual = 0;
		int numregpag = 5;
		int paginamasalta = 0;
		// <--Paginado

		// OPERACION

		switch (operacion) {

		/*************************************************************************************************************/

		/*
		 * --LISTAR SOCIOS (PAGINADO)-- 1. Añadir el total de registros. 2.
		 * Añadir la página y el número de registros por página: -Si request de
		 * "pag" no es nulo, la pagina actual será igual al request de "pag".
		 * -Si request de "nrp" (número de registros por página) no es nulo,
		 * numregpag será igual al request de "nrp". 3. El array listadoSocios
		 * es igual al listadoSocios del daoSocio pasando la paginaactual y el
		 * numregpag. 4. Si el resto entre totalregistros y numregpag es igual a
		 * 0, paginamasalta-1 y se insertan los atributos: -Añadir al atributo
		 * pagiaactual la página actual. -Añadir al atributo paginamasalta la
		 * página más alta. -Añadir al atributo totalregistros el total de
		 * registros. -Añadir al atributo nrp el número de registros por página.
		 * -Añadir al atributo listadosocios el listado de socios. -Redirigir la
		 * página a listadoSocios.jsp.
		 */

		case "listarSocios":
			daosocio = new DaoSocio();

			try {
				totalregistros = daosocio.contarSocios();
				if (request.getParameter("pag") != null)
					paginaactual = Integer.parseInt(request.getParameter("pag"));
				if (request.getParameter("nrp") != null)
					numregpag = Integer.parseInt(request.getParameter("nrp"));

				listadoSocios = daosocio.listadoSocios(paginaactual, numregpag);

				paginamasalta = totalregistros / numregpag;

				if (totalregistros % numregpag == 0)
					paginamasalta--;
				session.setAttribute("paginaactual", paginaactual);
				session.setAttribute("paginamasalta", paginamasalta);
				session.setAttribute("totalregistros", totalregistros);
				session.setAttribute("nrp", numregpag);
				session.setAttribute("listadoSocios", listadoSocios);// recupera
																		// el
																		// array
																		// listadoSocios
				response.sendRedirect("admin/listadoSocios.jsp");

			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		/*
		 * --LISTAR SOCIOS MOROSOS--
		 */
		case "socioslibrosfueraplazo":
			DaoPrestamo daoPrestamo = new DaoPrestamo();
			try {
				listadoSociosMorosos = daoPrestamo.listadoSociosMorosos();
				session.setAttribute("listadoSociosMorosos", listadoSociosMorosos);
				response.sendRedirect("admin/listadoSociosMorosos.jsp");
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		/*
		 * --VER LIBROS DE LOS SOCIOS MOROSOS--
		 */

		case "verlibros":
			ArrayList<Prestamo> listadoLibros = null;
			DaoPrestamo daoprestamo1 = null;
			daoprestamo1 = new DaoPrestamo();
			try {
				String nombre = request.getParameter("nombreSocio");
				listadoLibros = daoprestamo1.listadoLibros(request.getParameter("idsocio"));
				session.setAttribute("listadoLibros", listadoLibros);
				session.setAttribute("nombre", nombre);
				response.sendRedirect("admin/listadoSociosMorosos.jsp");
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		/*
		 * --ALTA AUTOR-- 1. Crear un nuevo autor. 2. establecer el formato de
		 * fecha y parsear le fecha pasada por el atributo "fecha". 3. añadir al
		 * nuevo autor el parámetro pasado por el campo "nombre" y la fecha. 4.
		 * insertar el autor con el método del daoAutor. 5. redirigir a la
		 * página altaAutorCompleta.jsp.
		 */

		case "altaautor":
			daoautor = new DaoAutor();
			try {
				Autor nuevoAutor = new Autor();
				SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date fechanueva = formatoDeFecha.parse(request.getParameter("fecha"));
				nuevoAutor.setNombre(request.getParameter("nombre"));
				nuevoAutor.setFecha(new java.sql.Date(fechanueva.getTime()));
				daoautor.insertarAutor(nuevoAutor);
				request.getRequestDispatcher("admin/altaAutorCompleta.jsp").forward(request, response);

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		/*
		 * --BUSCAR SOCIO (1ª PARTE DE MODIFICAR SOCIO)-- 1. el array buscaSocio
		 * es igual al método de buscarSocio del daoSocio pasándole el atributo
		 * "cadena" del jsp. 2. añadir al atributo "buscaSocio" el array
		 * buscaSocio 3. redirigir a la página getsocio.jsp.
		 */

		case "getsocio":
			daosocio = new DaoSocio();
			try {
				buscaSocio = daosocio.buscarSocio(request.getParameter("cadena"));
				request.setAttribute("buscaSocio", buscaSocio);
				request.getRequestDispatcher("admin/getsocio.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		/*
		 * --MODIFICAR SOCIO (2ª PARTE DE MODIFICAR SOCIO)-- 1. añadir a la
		 * variable idsocio lo recogido en el atributo "idsocio". 2. añadir al
		 * atributo "idsocio" la variable idsocio. 3. redirigir a la página
		 * modificarSocio.jsp.
		 */

		case "modificarsocio":
			try {
				String idsocio = request.getParameter("idsocio");
				request.setAttribute("idsocio", idsocio);
				request.getRequestDispatcher("admin/modificarSocio.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		/*
		 * --EDITAR SOCIO (3ª PARTE DE MODIFICAR SOCIO)-- 1. utilizamod el
		 * método modificarSocio del daoSocio y le pasamos el contenido del
		 * atributo "idsocio", del atributo "nombre", de "direccion" y de
		 * "email". 2. redirigimos a la página modificarSocioCompleta.jsp.
		 */

		case "editarSocio":
			daosocio = new DaoSocio();
			try {
				daosocio.modificarSocio(request.getParameter("idsocio"), request.getParameter("nombre"),
						request.getParameter("direccion"), request.getParameter("email"));
				request.getRequestDispatcher("/admin/modificarSocioCompleta.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		/*
		 * --BUSQUEDA LIBRO A ELIMINAR--
		 */
		case "buscaLibroEliminar":
			ArrayList<Libro> listadoLibrosEliminar = null;
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
			DaoLibro daoLibro = new DaoLibro();
			try {
				listadoLibrosEliminar = daoLibro.listadoLibrosEliminar(titulo, nombreAutor, isbn);
				session.setAttribute("valorcampobusqueda", valorbusqueda);
				session.setAttribute("listadoLibrosBusqueda", listadoLibrosEliminar);
				response.sendRedirect("admin/eliminarEjemplar.jsp");
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		/*
		 * --LISTADO EJEMPLARES A ELIMINAR--
		 */
		case "eliminarejemplar":
			try {
				String tituloEjemplarEliminar = request.getParameter("titulo");
				daoEjemplar = new DaoEjemplar();
				listaEjemplaresEliminar = daoEjemplar.listadoEjemplaresEliminar(request.getParameter("isbn"));
				request.setAttribute("listadoEjemplaresEliminar", listaEjemplaresEliminar);
				request.setAttribute("isbn", request.getParameter("isbn"));
				request.setAttribute("titulo", tituloEjemplarEliminar);
				request.getRequestDispatcher("admin/eliminarEjemplar.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		/*
		 * --ELIMINAR EJEMPLARES SELECCIONADOS--
		 */

		case "eliminarejemplaresseleccionados":
			try {
				DaoEjemplar daoEjemplarEliminar = new DaoEjemplar();
				String[] idejemplares = request.getParameterValues("ejemplaraeliminar");
				if (idejemplares != null && idejemplares.length > 0) {
					for (int i = 0; i < idejemplares.length; i++) {
						daoEjemplarEliminar.eliminarEjemplar(idejemplares[i]);
					}
				}
				response.sendRedirect("/Biblioteca/operacioncorrecta.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		/*
		 * --DEVOLUCIÓN. ELIMINAR EJEMPLAR DE LA TABLA PRESTAMO--
		 */

		case "devolucion":
			try {
				DaoPrestamo daoPrestamoEliminar = new DaoPrestamo();
				String idejemplar = request.getParameter("numeroejemplar");
				daoPrestamoEliminar.eliminarPrestamo(idejemplar);
				response.sendRedirect("/Biblioteca/operacioncorrecta.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
			/*
			 * --NUEVO PRESTAMO--*/

		case "nuevoprestamo":
			try {
				DaoPrestamo daoPrestamoAñadir = new DaoPrestamo();
				String idsocio = request.getParameter("idsocio");
				String idejemplar = request.getParameter("idejemplar");
				daoPrestamoAñadir.añadirPrestamo(idsocio, idejemplar);
				response.sendRedirect("/Biblioteca/operacioncorrecta.jsp");
			} catch (SQLException e) {
				e.printStackTrace();
				procesarErrorSQL(request, response, e);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
	}

	protected void procesarErrorSQL(HttpServletRequest request, HttpServletResponse response, SQLException e)
			throws ServletException, IOException {
		String mensajeError = e.getMessage();
		DaoMensajeError error = new DaoMensajeError();
		try {
			mensajeError = error.error(mensajeError);
			System.out.println(mensajeError);
			int codigoError = e.getErrorCode();
			switch (codigoError) {
			case 20001:
				mensajeError = "El socio está penalizado";
			case 20000:
				mensajeError = "El socio tiene un ejemplar en prestamo";
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		request.setAttribute("error", mensajeError);
		request.getRequestDispatcher("error.jsp").forward(request, response);

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