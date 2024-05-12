package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;

import entidades.Paciente;
import util.MysqlConexion;
import interfaces.PacienteDAO;

public class MySqlPacienteDAO implements PacienteDAO {

	@Override
	public int registrarPaciente (Paciente paciente) {
		
		int value = 0;
		Connection cn = null;
		PreparedStatement psm = null;
		
		try {
			cn = MysqlConexion.getConexion();
			String sql = "Insert into pacientes values (null, ?, ?, ?, ?,?)";
			psm = cn.prepareStatement(sql);
	        psm.setString(1, paciente.getDocumento_identidad());
	        psm.setString(2, paciente.getNombre());
	        psm.setDate(3, paciente.getFecha_nacimiento());
	        psm.setString(4, paciente.getTelefono());
	        psm.setString(5, paciente.getCorreo());
			
			value = psm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (psm != null) psm.close();
				if (cn != null) cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		System.out.println(value);
		return value;
	}
}
