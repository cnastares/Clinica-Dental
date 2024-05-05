package dao;

import interfaces.*;

import modelo.*;

public class MySqlDAOFactory extends DAOFactory{

	@Override
	public CursoDAO getCurso() {
		return new MySqlCursoDAO();
	}
	
	@Override
	public UsuarioDAO getUsuario() {
		return new MySqlUsuarioDAO();
	}
	
}
