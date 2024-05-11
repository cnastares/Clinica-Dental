package dao;

import interfaces.*;

import modelo.*;

public class MySqlDAOFactory extends DAOFactory{

	@Override
	public CitaDAO getCita() {
		return new MySqlCitaDAO();
	}
}
