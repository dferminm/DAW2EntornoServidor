package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Conversor;

/**
 * Servlet implementation class Controlador
 */
@WebServlet(name = "controlador", urlPatterns = { "/controlador" })
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controlador() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operacion = request.getParameter("operacion");
		double millas;
		double km;
		if(operacion.equals("millasKm")){
			millas = new Double(request.getParameter("cantidad")).doubleValue();
			Conversor c = new Conversor();
			request.setAttribute("resultado", c.millasKm(millas));
			System.out.println(c.millasKm(millas));
			request.getRequestDispatcher("/resultado.jsp").forward(request, response);
		}
		if(operacion.equals("kmMillas")){
			km = new Double(request.getParameter("cantidad")).doubleValue();
			Conversor c = new Conversor();
			request.setAttribute("resultado", c.kmMillas(km));
			System.out.println(c.kmMillas(km));
			request.getRequestDispatcher("/resultado.jsp").forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
