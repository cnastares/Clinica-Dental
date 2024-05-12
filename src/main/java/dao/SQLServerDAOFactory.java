package dao;

import interfaces.CitaDAO;
import interfaces.PacienteDAO;
import modelo.MySqlCitaDAO;
import modelo.MySqlPacienteDAO;

public class SQLServerDAOFactory extends DAOFactory {
	

	@Override
	public CitaDAO getCita() {
		return new MySqlCitaDAO();
	}
	
	@Override
	public PacienteDAO getPaciente() {
		return new MySqlPacienteDAO();
	}

}
