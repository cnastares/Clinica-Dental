package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidades.Cita;

import interfaces.CitaDAO;
import util.MysqlConexion;

public class MySqlCitaDAO implements CitaDAO {
	@Override
	public List<Cita> listarCitas() {
		List<Cita> listaCitas = new ArrayList<Cita>();
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		
		try {
			cn = MysqlConexion.getConexion();
			String sql = "call ObtenerDatosCitas();";
			psm = cn.prepareStatement(sql);
			rs = psm.executeQuery();
			
			while(rs.next()) {
				Cita cita = new Cita();
				cita.setId_cita(rs.getInt("id_cita"));
				cita.setNombre_paciente(rs.getString("nombre_paciente"));
				cita.setNombre_personal(rs.getString("nombre_personal"));
				cita.setEstado(rs.getString("estado"));
				cita.setTipo_atencion(rs.getString("tipo_atencion"));
				cita.setFecha(rs.getDate("fecha"));
				cita.setHora(rs.getTime("hora"));
				listaCitas.add(cita);
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
		return listaCitas;
	}

	@Override
	public int eliminarCita(int id) {
		int value = 0;
		Connection cn = null;
		PreparedStatement psm = null;
		
		try {
			cn = MysqlConexion.getConexion();
			String sql = "Delete from citas where id_cita=?";
			psm = cn.prepareStatement(sql);
			psm.setInt(1, id);
			
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
		
		return value;
	}
}
