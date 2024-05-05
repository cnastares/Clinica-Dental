package dao;

import interfaces.CursoDAO;
import interfaces.UsuarioDAO;
import modelo.MySqlCursoDAO;
import modelo.MySqlUsuarioDAO;

public class OracleDAOFactory extends DAOFactory {

	@Override
	public CursoDAO getCurso() {
		return new MySqlCursoDAO();
	}
	
	@Override
	public UsuarioDAO getUsuario() {
		return new MySqlUsuarioDAO();
	}
	
}
