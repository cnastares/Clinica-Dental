package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import entidades.Cita;
import interfaces.CitaDAO;

/**
 * Servlet implementation class CitaServlet
 */
@WebServlet("/CitaServlet")
public class CitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CitaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tipo = request.getParameter("tipo");
		System.out.println(tipo);
		switch(tipo) {
		case "list" : listCita(request, response); break;
		case "delete" : eliminarCita(request, response); break;
		default:
			request.setAttribute("mensaje", "Ocurrio un problema");
			request.getRequestDispatcher("home.jsp").forward(request, response);
			
		}
	}
	
	protected void listCita(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
	;
		CitaDAO dao = daoFactory.getCita();
		
		List<Cita> lista = dao.listarCitas();
		
		request.setAttribute("lista", lista);
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

	protected void eliminarCita(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("linea 61");
		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		CitaDAO dao = daoFactory.getCita();
		
		int value = dao.eliminarCita(id);
		
		if(value == 1) {
			listCita(request, response);
		} else {
			request.setAttribute("mensaje", "Ocurrio un problema");
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}
			
	}

}
