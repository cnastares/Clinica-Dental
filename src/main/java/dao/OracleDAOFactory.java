package dao;

import interfaces.CitaDAO;
import modelo.MySqlCitaDAO;


public class OracleDAOFactory extends DAOFactory {

	@Override
	public CitaDAO getCita() {
		return new MySqlCitaDAO();
	}
	

}
