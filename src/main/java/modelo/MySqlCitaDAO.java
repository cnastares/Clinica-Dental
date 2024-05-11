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
	private int parameterIndex;

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
	public Cita obtenerCita(int id) {
	    Cita cita = null;
	    Connection cn = null;
	    PreparedStatement psm = null;
	    ResultSet rs = null;
	    
	    try {
	        cn = MysqlConexion.getConexion();
	        String sql = "Select * from cita where id_cia=?";
	        psm = cn.prepareStatement(sql);
	        psm.setInt(1, id);
	        rs = psm.executeQuery();
	        if(rs.next()) {
	            cita = new Cita(); // Cambiado aquí
	            cita.setId_cita(rs.getInt("id_cita"));
	            cita.setNombre_paciente(rs.getString("nombre_paciente"));
	            cita.setNombre_personal(rs.getString("nombre_personal"));
	            cita.setEstado(rs.getString("estado"));
	            cita.setTipo_atencion(rs.getString("tipo_atencion"));
	            cita.setFecha(rs.getDate("fecha"));
	            cita.setHora(rs.getTime("hora"));
	            
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
	    return cita;
	}

	@Override
	public int editarCita(Cita cita) {
	    int rowsUpdated = 0;
	    Connection cn = null;
	    PreparedStatement psm = null;

	    try {
	        cn = MysqlConexion.getConexion();
	        String sql = "UPDATE cita SET nombre_paciente=?, nombre_personal=?, estado=?, tipo_atencion=?, fecha=?, hora=? WHERE id_cita=?";
	        psm = cn.prepareStatement(sql);
	        psm.setString(1, cita.getNombre_paciente());
	        psm.setString(2, cita.getNombre_personal());
	        psm.setString(3, cita.getEstado());
	        psm.setString(4, cita.getTipo_atencion());
	        psm.setDate(5, cita.getFecha());
	        psm.setTime(6, cita.getHora());
	        psm.setInt(7, cita.getId_cita());

	        rowsUpdated = psm.executeUpdate();
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
	    return rowsUpdated;
	}
}
