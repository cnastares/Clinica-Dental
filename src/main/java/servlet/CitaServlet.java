package servlet;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
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
		case "regist" : registCita(request, response); break;
		case "modif" : modificarCita(request, response); break;
		case "edit" : actualizarCita(request, response); break;
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

	protected void registCita(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String nombrePaciente = request.getParameter("txtNombrePaciente");
	    String nombrePersonal = request.getParameter("txtNombrePersonal");
	    String fechaStr = request.getParameter("txtFecha");
	    String horaStr = request.getParameter("txtHora");
	    String estado = request.getParameter("txtEstado");
	    String tipoAtencion = request.getParameter("txtTipoAtencion");

	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date fecha = null;
	    try {
	        fecha = new Date(dateFormat.parse(fechaStr).getTime());
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }

	    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
	    Time hora = null;
	    try {
	        hora = new Time(timeFormat.parse(horaStr).getTime());
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }

	    Cita cita = new Cita();  // Aquí se crea la instancia de Cita
	    cita.setNombre_paciente(nombrePaciente);
	    cita.setNombre_personal(nombrePersonal);
	    cita.setFecha(fecha);
	    cita.setHora(hora);
	    cita.setEstado(estado);
	    cita.setTipo_atencion(tipoAtencion);

	    DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
	    CitaDAO dao = daoFactory.getCita();

	    boolean agregado = dao.registrarCita(cita);

	    if (agregado) {
	        listCita(request, response);
	    } else {
	        request.setAttribute("mensaje", "Ocurrio un problema");
	        request.getRequestDispatcher("regCitas.jsp").forward(request, response);
	    }
	}
	
	protected void modificarCita(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id_cita = Integer.parseInt(request.getParameter("id"));
		
		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		
		CitaDAO dao = daoFactory.getCita();
		
		Cita cita = dao.obtenerCita(id_cita);
		
		request.setAttribute("citaData", cita);
		request.getRequestDispatcher("editCita.jsp").forward(request, response);		
	}

	protected void actualizarCita(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int id = Integer.parseInt(request.getParameter("id_cita"));
	    String nombrePaciente = request.getParameter("txtNombrePaciente");
	    String nombrePersonal = request.getParameter("txtNombrePersonal");
	    String fechaStr = request.getParameter("txtFecha");
	    String horaStr = request.getParameter("txtHora");
	    String estado = request.getParameter("txtEstado");
	    String tipoAtencion = request.getParameter("txtTipoAtencion");

	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date fecha = null;
	    try {
	        fecha = new Date(dateFormat.parse(fechaStr).getTime());
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }

	    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
	    Time hora = null;
	    try {
	        hora = new Time(timeFormat.parse(horaStr).getTime());
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }

	    Cita cita = new Cita();
	    cita.setId_cita(id);
	    cita.setNombre_paciente(nombrePaciente);
	    cita.setNombre_personal(nombrePersonal);
	    cita.setFecha(fecha);
	    cita.setHora(hora);
	    cita.setEstado(estado);
	    cita.setTipo_atencion(tipoAtencion);

	    DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
	    CitaDAO dao = daoFactory.getCita();

	    boolean actualizado = dao.editarCita(cita);

	    if (actualizado) {
	        listCita(request, response);
	    } else {
	        request.setAttribute("mensaje", "Ocurrió un problema");
	        request.getRequestDispatcher("home.jsp").forward(request, response);
	    }
	}

	
	protected void filtrarCita(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filter = request.getParameter("filterWord");
		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
	
		CitaDAO dao = daoFactory.getCita();
		
		List<Cita> lista = dao.filtrarCita(filter);
		
		request.setAttribute("lista", lista);
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

		
	
	
	protected void listPacientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
	;
		CitaDAO dao = daoFactory.getCita();
		
		List<Cita> lista = dao.listarCitas();
		
		request.setAttribute("lista", lista);
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}
	
	protected void eliminarCita(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int id = Integer.parseInt(request.getParameter("id"));

	    DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
	    CitaDAO dao = daoFactory.getCita();

	    int eliminado = dao.eliminarCita(id);

	    if (eliminado == 1) {
	        listCita(request, response);
	    } else {
	        request.setAttribute("mensaje", "Ocurrió un problema al eliminar la cita");
	        request.getRequestDispatcher("home.jsp").forward(request, response);
	    }
	}


}
