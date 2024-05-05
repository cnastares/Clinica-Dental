package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidades.Curso;
import interfaces.CursoDAO;
import util.MysqlConexion;

public class MySqlCursoDAO implements CursoDAO {

	@Override
	public int registrarCurso(Curso curso) {
		int value = 0;
		Connection cn = null;
		PreparedStatement psm = null;
		
		try {
			cn = MysqlConexion.getConexion();
			String sql = "Insert into curso values (null, ?, ?, ?, ?)";
			psm = cn.prepareStatement(sql);
			psm.setString(1, curso.getCodigo());
			psm.setString(2, curso.getNombre());
			psm.setString(3, curso.getNivel());
			psm.setString(4, curso.getProfesor());
			
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
	public List<Curso> listarCursos() {
		List<Curso> listaCursos = new ArrayList<Curso>();
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		
		try {
			cn = MysqlConexion.getConexion();
			String sql = "Select * from curso";
			psm = cn.prepareStatement(sql);
			rs = psm.executeQuery();
			
			while(rs.next()) {
				Curso curso = new Curso();
				curso.setId(rs.getInt("idCurso"));
				curso.setCodigo(rs.getString("codCurso"));
				curso.setNombre(rs.getString("nomCurso"));
				curso.setNivel(rs.getString("nivCurso"));
				curso.setProfesor(rs.getString("profCurso"));
				listaCursos.add(curso);
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
		return listaCursos;
	}

	@Override
	public Curso obtenerCurso(int id) {
		Curso curso = null;
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		
		try {
			cn = MysqlConexion.getConexion();
			String sql = "Select * from curso where idCurso=?";
			psm = cn.prepareStatement(sql);
			psm.setInt(1, id);
			rs = psm.executeQuery();
			if(rs.next()) {
				curso = new Curso();
				curso.setId(rs.getInt("idCurso"));
				curso.setCodigo(rs.getString("codCurso"));
				curso.setNombre(rs.getString("nomCurso"));
				curso.setNivel(rs.getString("nivCurso"));
				curso.setProfesor(rs.getString("profCurso"));
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
		return curso;
	}

	@Override
	public int editarCurso(Curso curso) {
		int value = 0;
		Connection cn = null;
		PreparedStatement psm = null;
		
		try {
			cn = MysqlConexion.getConexion();
			String sql = "Update curso set codCurso=?, nomCurso=?, nivCurso=?, profCurso=? where idCurso=?";
			psm = cn.prepareStatement(sql);
			psm.setString(1, curso.getCodigo());
			psm.setString(2, curso.getNombre());
			psm.setString(3, curso.getNivel());
			psm.setString(4, curso.getProfesor());
			psm.setInt(5, curso.getId());
			
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
	public int eliminarCurso(int id) {
		int value = 0;
		Connection cn = null;
		PreparedStatement psm = null;
		
		try {
			cn = MysqlConexion.getConexion();
			String sql = "Delete from curso where idCurso=?";
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
