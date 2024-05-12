package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.CallableStatement;

import entidades.Cita;
import interfaces.CitaDAO;
import util.MysqlConexion;

public class MySqlCitaDAO implements CitaDAO {
    @Override
    public List<Cita> listarCitas() {
        List<Cita> listaCitas = new ArrayList<Cita>();
        Connection cn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;

        try {
            cn = MysqlConexion.getConexion();
            String sql = "{call ObtenerDatosCitas()}";
            cstmt = (CallableStatement) cn.prepareCall(sql);
            rs = cstmt.executeQuery();

            while (rs.next()) {
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
                if (cstmt != null) cstmt.close();
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
    public boolean registrarCita(Cita cita) {
        Connection cn = null;
        PreparedStatement psm = null;
        boolean agregado = false;

        try {
            cn = MysqlConexion.getConexion();
            String sql = "INSERT INTO citas (nombre_paciente, nombre_personal, estado, tipo_atencion, fecha, hora) VALUES (?, ?, ?, ?, ?, ?)";
            psm = cn.prepareStatement(sql);
            psm.setString(1, cita.getNombre_paciente());
            psm.setString(2, cita.getNombre_personal());
            psm.setString(3, cita.getEstado());
            psm.setString(4, cita.getTipo_atencion());
            psm.setDate(5, new java.sql.Date(cita.getFecha().getTime()));
            psm.setTime(6, new java.sql.Time(cita.getHora().getTime()));

            int filasInsertadas = psm.executeUpdate();

            agregado = filasInsertadas > 0;

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

        return agregado;
    }

    
    @Override
    public boolean editarCita(Cita cita) {
        Connection cn = null;
        PreparedStatement psm = null;
        boolean actualizado = false;

        try {
            cn = MysqlConexion.getConexion();
            String sql = "UPDATE citas SET nombre_paciente = ?, nombre_personal = ?, estado = ?, tipo_atencion = ?, fecha = ?, hora = ? WHERE id_cita = ?";
            psm = cn.prepareStatement(sql);
            psm.setString(1, cita.getNombre_paciente());
            psm.setString(2, cita.getNombre_personal());
            psm.setString(3, cita.getEstado());
            psm.setString(4, cita.getTipo_atencion());
            psm.setDate(5, new java.sql.Date(cita.getFecha().getTime()));
            psm.setTime(6, new java.sql.Time(cita.getHora().getTime()));
            psm.setInt(7, cita.getId_cita());

            int filasActualizadas = psm.executeUpdate();

            actualizado = filasActualizadas > 0;

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

        return actualizado;
    }
    public Cita obtenerCita(int id) {
		Cita cita = null;
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		
		try {
			cn = MysqlConexion.getConexion();
			String sql = "call ObtenerDatosCitasPorNombreNE(?)";
			psm = cn.prepareStatement(sql);
			psm.setInt(1, id);
			rs = psm.executeQuery();
			if(rs.next()) {
				cita = new Cita();
                cita.setId_cita(rs.getInt("id_cita"));
                cita.setNombre_paciente(rs.getString("nombre_paciente"));
                cita.setNombre_personal(rs.getString("nombre_personal"));
                cita.setEstado(rs.getString("estado"));
                cita.setTipo_atencion(rs.getString("tipo_atencion"));
                cita.setFecha(rs.getDate("fecha"));
                cita.setHora(rs.getTime("hora"));
                //filtraCitas.add(cita);
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
