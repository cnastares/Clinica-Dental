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
			String sql = "Select * from citas";
			psm = cn.prepareStatement(sql);
			rs = psm.executeQuery();
			
			while(rs.next()) {
				Cita cita = new Cita();
				cita.setId_cita(rs.getInt("id_cita"));
				cita.setId_paciente(rs.getInt("id_paciente"));
				cita.setId_personal(rs.getInt("id_personal"));
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
}
