package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
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
		
		switch(tipo) {
		case "list" : listCita(request, response); break;
		default:
			request.setAttribute("mensaje", "Ocurrio un problema");
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}
	}
	
	protected void listCita(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		
		CitaDAO dao = daoFactory.getCita();
		
		List<Cita> lista = dao.listarCitas();
		
		request.setAttribute("lista", lista);
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}
	
	protected void editarCita(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id_cita = Integer.parseInt(request.getParameter("id"));
		
		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		
		CitaDAO dao = daoFactory.getCita();
		
		Cita cita = dao.obtenerCita(id_cita);
		
		request.setAttribute("citaData", cita);
		request.getRequestDispatcher("editcita.jsp").forward(request, response);		
	}

	protected void actualizarCita(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int id_cita = Integer.parseInt(request.getParameter("id_cita"));
	    String nombre_paciente = request.getParameter("txtNombre_paciente");
	    String nombre_personal = request.getParameter("txtNombre_personal");
	    String estado = request.getParameter("txtEstado");
	    String tipo_atencion = request.getParameter("txtTipo_atencion");
	    Date fecha = Date.valueOf(request.getParameter("txtFecha"));
	    Time hora = Time.valueOf(request.getParameter("txtHora"));
	    
	    Cita cita = new Cita();
	    cita.setId_cita(id_cita);
	    cita.setNombre_paciente(nombre_paciente);
	    cita.setNombre_personal(nombre_personal);
	    cita.setEstado(estado);
	    cita.setTipo_atencion(tipo_atencion);
	    cita.setFecha(fecha);
	    cita.setHora(hora);
	    
	    DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
	    CitaDAO dao = daoFactory.getCita();
	    
	    int value = dao.editarCita(cita);
	    
	    if(value == 1) {
	        listCita(request, response);
	    } else {
	        request.setAttribute("mensaje", "Ocurrio un problema");
	        request.getRequestDispatcher("home.jsp").forward(request, response);
	    }
	}


}