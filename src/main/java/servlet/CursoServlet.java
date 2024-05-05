package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import entidades.Curso;
import interfaces.CursoDAO;



/**
 * Servlet implementation class CursoServlet
 */
@WebServlet("/CursoServlet")
public class CursoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CursoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo = request.getParameter("tipo");
		
		switch(tipo) {
		case "list" : listCurso(request, response); break;
		case "regist" : registCurso(request, response); break;
		case "info" : detalleCurso(request, response); break;
		case "modif" : modificarCurso(request, response); break;
		case "edit" : actualizarCurso(request, response); break;
		case "delete" : eliminarCurso(request, response); break;
		default:
			request.setAttribute("mensaje", "Ocurrio un problema");
			request.getRequestDispatcher("regCursos.jsp").forward(request, response);
		}	
	}
	
	protected void listCurso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		
		CursoDAO dao = daoFactory.getCurso();
		
		List<Curso> lista = dao.listarCursos();
		
		request.setAttribute("lista", lista);
		request.getRequestDispatcher("listCursos.jsp").forward(request, response);
	}

	protected void registCurso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codigo = request.getParameter("txtCodigo");
		String nombre = request.getParameter("txtNombre");
		String nivel = request.getParameter("txtNivel");
		String profesor = request.getParameter("txtProfesor");
		
		Curso curso = new Curso();
		curso.setCodigo(codigo);
		curso.setNombre(nombre);
		curso.setNivel(nivel);
		curso.setProfesor(profesor);
		
		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		
		CursoDAO dao = daoFactory.getCurso();
		
		int value = dao.registrarCurso(curso);
		
		if(value == 1){
			listCurso(request, response);
		} else {
			request.setAttribute("mensaje", "Ocurrio un problema");
			request.getRequestDispatcher("regCursos.jsp").forward(request, response);
		}
		
	}

	protected void detalleCurso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idCurso = Integer.parseInt(request.getParameter("id"));
		
		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		
		CursoDAO dao = daoFactory.getCurso();
		
		Curso curso = dao.obtenerCurso(idCurso);
		
		request.setAttribute("cursoData", curso);
		request.getRequestDispatcher("detCurso.jsp").forward(request, response);
	}

	protected void modificarCurso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idCurso = Integer.parseInt(request.getParameter("id"));
		
		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		
		CursoDAO dao = daoFactory.getCurso();
		
		Curso curso = dao.obtenerCurso(idCurso);
		
		request.setAttribute("cursoData", curso);
		request.getRequestDispatcher("editCurso.jsp").forward(request, response);		
	}

	protected void actualizarCurso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("idCurso"));
		String codigo = request.getParameter("txtCodigo");
		String nombre = request.getParameter("txtNombre");
		String nivel = request.getParameter("txtNivel");
		String profesor = request.getParameter("txtProfesor");
		
		Curso curso = new Curso();
		curso.setId(id);
		curso.setCodigo(codigo);
		curso.setNombre(nombre);
		curso.setNivel(nivel);
		curso.setProfesor(profesor);
		
		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		CursoDAO dao = daoFactory.getCurso();
		
		int value = dao.editarCurso(curso);
		
		if(value == 1) {
			listCurso(request, response);
		} else {
			request.setAttribute("mensaje", "Ocurrio un problema");
			request.getRequestDispatcher("listCursos.jsp").forward(request, response);
		}
		
	}
	
	protected void eliminarCurso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		CursoDAO dao = daoFactory.getCurso();
		
		int value = dao.eliminarCurso(id);
		
		if(value == 1) {
			listCurso(request, response);
		} else {
			request.setAttribute("mensaje", "Ocurrio un problema");
			request.getRequestDispatcher("listCursos.jsp").forward(request, response);
		}
			
	}
	

}
