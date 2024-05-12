package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidades.Cita;
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
	
	@Override
	public List<Paciente> listarPacientes() {
		List<Paciente> listaPacientes = new ArrayList<Paciente>();
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		System.out.println("lista pacientes modelo");
		try {
			cn = MysqlConexion.getConexion();
			String sql = "select * from pacientes";
			psm = cn.prepareStatement(sql);
			rs = psm.executeQuery();
			
			while(rs.next()) {
				Paciente paciente = new Paciente();
				paciente.setId_paciente(rs.getInt("id_paciente"));
				paciente.setDocumento_identidad(rs.getString("documento_identidad"));
				paciente.setNombre(rs.getString("nombre"));
				paciente.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
				paciente.setTelefono(rs.getString("telefono")); 
				paciente.setCorreo(rs.getString("correo")); 
				listaPacientes.add(paciente);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (psm != null) psm.close();
				if (cn != null) cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		return listaPacientes;
	}
}
