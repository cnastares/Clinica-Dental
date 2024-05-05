package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import entidades.Usuario;
import interfaces.UsuarioDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String user = request.getParameter("txtUsuario");
		String password = request.getParameter("txtClave");
		
		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		
		UsuarioDAO dao = daoFactory.getUsuario();
		
		Usuario usu = dao.validarLogin(user, password);
		
		if(usu != null) {
			request.setAttribute("dataUsuario", usu);
			request.getRequestDispatcher("main.jsp").forward(request, response);
		} else {
			request.setAttribute("mensaje", "Usuario y/o Contraseña es incorrecta");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
	}

}
