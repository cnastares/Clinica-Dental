package servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import entidades.Cita;
import entidades.Paciente;
import interfaces.CitaDAO;
import interfaces.PacienteDAO;
import servlet.CitaServlet;

/**
 * Servlet implementation class PacienteServlet
 */
@WebServlet("/PacienteServlet")
public class PacienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tipo = request.getParameter("tipo");
		System.out.println(tipo);
		listData(request, response);
		switch(tipo) {
		case "list" : listData(request, response); break;
		case "regist" : try {
				registPaciente(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		default:
			request.setAttribute("mensaje", "Ocurrio un problema");	
			request.getRequestDispatcher("regCitas.jsp").forward(request, response);
		}
	}

	protected void registPaciente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    // Analizar la cadena de tiempo
	    java.util.Date parsedDateUtil = dateFormat.parse(request.getParameter("txtFechaNacimiento"));
	    // Convertir el objeto Date a un objeto Time
	    java.sql.Date parsedDateSql = new java.sql.Date(parsedDateUtil.getTime());
		
	    String nombrePaciente = request.getParameter("txtNombresCompletos");	
		String documento = request.getParameter("txtDocIdentidad");	
		Date fecha =  parsedDateSql;
		String telefono = request.getParameter("txtTelefono");
		String correo = request.getParameter("txtCorreo");
		
		Paciente paciente = new Paciente();
		paciente.setDocumento_identidad(documento);
		paciente.setNombre(nombrePaciente);
		paciente.setFecha_nacimiento(fecha);
		paciente.setTelefono(telefono);
		paciente.setCorreo(correo);
		
		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		
		PacienteDAO dao = daoFactory.getPaciente();
		
		int value = dao.registrarPaciente(paciente);
		if(value == 1){
			request.setAttribute("registroExitoso", "Paciente registrado exitosamente");
		} else {
			request.setAttribute("mensaje", "Ocurrio un problema");
			request.getRequestDispatcher("regPacientes.jsp").forward(request, response);
		}
		
	}
	
	protected void listData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
	;
		PacienteDAO dao = daoFactory.getPaciente();
		
		List<Paciente> listaPacientes = dao.listarPacientes();

		System.out.println(listaPacientes);
		request.setAttribute("listaPacientes", listaPacientes);
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}


}
