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
		System.out.println(tipo);
		switch(tipo) {
		case "list" : listCita(request, response); break;
		case "editar":editarCita(request, response);
		case "actualizar" : actualizarCita(request, response);
		case "delete" : eliminarCita(request, response); break;
		case "filter" : filtrarCita(request, response); break;
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
        System.out.println("Entrando al método editarCita");
        int idcita = Integer.parseInt(request.getParameter("id"));
        System.out.println("ID de la cita: " + idcita);
        DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
        //CitaDAO dao = daoFactory.getCita();
        //Cita cita = dao.obtenerCita(id_cita);
        CitaDAO citaDAO = daoFactory.getCita();
        Cita cita = citaDAO.obtenerCita(idcita);
        // Enviar los datos de la cita como un atributo en la solicitud
        request.setAttribute("citaData", cita);

        // Redirigir a la página home.jsp
        request.getRequestDispatcher("edit_citas.jsp").forward(request, response);
    }


    private void actualizarCita(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_Cita = Integer.parseInt(request.getParameter("id_cita"));
        String nombrePaciente = request.getParameter("txtNombre_paciente");
        String nombrePersonal = request.getParameter("txtNombre_personal");
        String estado = request.getParameter("txtEstado");
        String tipoAtencion = request.getParameter("txtTipo_atencion");
        Date fecha = Date.valueOf(request.getParameter("txtFecha"));
        Time hora = Time.valueOf(request.getParameter("txtHora") + ":00");

        Cita cita = new Cita();
        cita.setId_cita(id_Cita);
        cita.setNombre_paciente(nombrePaciente);
        cita.setNombre_personal(nombrePersonal);
        cita.setEstado(estado);
        cita.setTipo_atencion(tipoAtencion);
        cita.setFecha(fecha);
        cita.setHora(hora);

        DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
        CitaDAO citaDAO = daoFactory.getCita();
        int resultado = citaDAO.editarCita(cita);

        if (resultado == 1) {
            listarCitas(request, response);
        } else {
            request.setAttribute("mensaje", "Ocurrió un problema");
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }
    }

	private void listarCitas(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
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
	
	protected void filtrarCita(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filter = request.getParameter("filterWord");
		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
	
		CitaDAO dao = daoFactory.getCita();
		
		List<Cita> lista = dao.filtrarCita(filter);
		
		request.setAttribute("lista", lista);
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

}
	