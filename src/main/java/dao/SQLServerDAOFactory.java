package dao;

import interfaces.CitaDAO;
import modelo.MySqlCitaDAO;

public class SQLServerDAOFactory extends DAOFactory {
	

	@Override
	public CitaDAO getCita() {
		return new MySqlCitaDAO();
	}
	

}
