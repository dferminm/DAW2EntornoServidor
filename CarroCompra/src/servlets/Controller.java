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

import carrocompra.CarroCompra;
import dao.DaoProducto;
import entidades.Producto;

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
		ArrayList<Producto> listadoProductos = null;
		DaoProducto daoProducto = new DaoProducto();
		CarroCompra micarro = new CarroCompra();

		switch (operacion) {

		/*
		 * --LISTAR PRODUCTOS--
		 */

		case "listarProductos":
			try {
				listadoProductos = daoProducto.listadoProductos();
				session.setAttribute("listadoProductos", listadoProductos);
				request.getRequestDispatcher("./compra.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		/*
		 * --COMPRAR PRODUCTOS (AÑADIR AL CARRITO)--
		 */

		case "comprar":
			try {
				micarro = (CarroCompra) session.getAttribute("carro");
				if (micarro == null) {
					micarro = new CarroCompra();
					session.setAttribute("carro", micarro);
				}
				String idProducto = request.getParameter("idProducto");
				Producto p = daoProducto.buscarProducto(Integer.parseInt(idProducto));
				micarro.addElemento(p);
				session.setAttribute("carro", micarro);
				request.getRequestDispatcher("./compra.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
			/*
			 * --INCREMENTAR PRODUCTO DEL CARRITO--*/
			
		case "incrementar":
			try{
				micarro = (CarroCompra) session.getAttribute("carro");
				String idProducto = request.getParameter("idProducto");
				Producto p = daoProducto.buscarProducto(Integer.parseInt(idProducto));
				micarro.addElemento(p);
				session.setAttribute("carro", micarro);
				request.getRequestDispatcher("./compra.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
			/*
			 * --DISMINUIR PRODUCTO DEL CARRITO--*/
			
		case "disminuir":
			try{
				micarro = (CarroCompra) session.getAttribute("carro");
				String idProducto = request.getParameter("idProducto");
				Producto p = daoProducto.buscarProducto(Integer.parseInt(idProducto));
				micarro.disminuirElemento(p);
				session.setAttribute("carro", micarro);
				request.getRequestDispatcher("./compra.jsp").forward(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
			/*
			 * --ELIMINAR PRODUCTO DEL CARRITO--*/
			
		case "eliminar":
			try{
				micarro = (CarroCompra) session.getAttribute("carro");
				String idProducto = request.getParameter("idProducto");
				Producto p = daoProducto.buscarProducto(Integer.parseInt(idProducto));
				micarro.eliminarElemento(p);
				session.setAttribute("carro", micarro);
				request.getRequestDispatcher("./compra.jsp").forward(request, response);
			}catch (Exception e) {
				e.printStackTrace();
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

}
