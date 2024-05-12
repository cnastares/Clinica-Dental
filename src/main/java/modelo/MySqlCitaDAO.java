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
	
	@Override
	public List<Cita> filtrarCita(String nombre) {
		List<Cita> filtraCitas = new ArrayList<Cita>();
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		
		try {
			cn = MysqlConexion.getConexion();
			String sql = "call ObtenerDatosCitasPorNombreNE(?)";
			psm = cn.prepareStatement(sql);
			psm.setString(1, nombre);
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
				filtraCitas.add(cita);
			}
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
		
		return filtraCitas;
	}
	
	@Override
	public int registrarCita(Cita cita) {
		int value = 0;
		Connection cn = null;
		PreparedStatement psm = null;
		
		try {
			cn = MysqlConexion.getConexion();
			String sql = "Insert into citas values (null, ?, ?, ?, ?,?,?)";
			psm = cn.prepareStatement(sql);
	        psm.setInt(1, cita.getId_paciente());
	        psm.setInt(2, cita.getId_personal());
	        psm.setDate(3, cita.getFecha());
	        psm.setTime(4, cita.getHora());
	        psm.setString(5, cita.getEstado());
	        psm.setString(6, cita.getTipo_atencion());
			
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
    public int editarCita(Cita cita) {
		int value = 0;
        Connection cn = null;
        PreparedStatement psm = null;

        try {
            cn = MysqlConexion.getConexion();
            String sql = "CALL ActualizarCita(cita_id=? , nombre_paciente = ?, nombre_personal = ?,fecha = ?  , hora = ? , estado = ?, tipo_atencion = ? )";
            psm = cn.prepareStatement(sql);
            psm.setInt(1, cita.getId_cita());
            psm.setString(1, cita.getNombre_paciente());
            psm.setString(2, cita.getNombre_personal());
            psm.setDate(5, new java.sql.Date(cita.getFecha().getTime()));
            psm.setTime(6, new java.sql.Time(cita.getHora().getTime()));
            psm.setString(3, cita.getEstado());
            psm.setString(4, cita.getTipo_atencion());

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
	
    public Cita obtenerCita(int id) {
		Cita cita = null;
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		
		try {
			cn = MysqlConexion.getConexion();
			String sql = "CALL ObtenerDatosCita(?)";
			psm = cn.prepareStatement(sql);
			psm.setInt(1, id);
			rs = psm.executeQuery();
			if(rs.next()) {
				cita = new Cita();
                cita.setId_cita(rs.getInt("id_cita"));
                cita.setId_paciente(rs.getInt("id_paciente"));
                cita.setId_personal(rs.getInt("id_personal"));
                cita.setFecha(rs.getDate("fecha"));
                cita.setHora(rs.getTime("hora"));
                cita.setEstado(rs.getString("estado"));
                cita.setTipo_atencion(rs.getString("tipo_atencion"));
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
    
}
	
