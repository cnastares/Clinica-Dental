package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entidades.Usuario;
import interfaces.UsuarioDAO;
import util.MysqlConexion;

public class MySqlUsuarioDAO implements UsuarioDAO {

	public Usuario validarLogin(String usu, String pwd) {
		Usuario user = null;
		Connection cn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		
		try {
			cn = MysqlConexion.getConexion();
			String sql = "Select * from usuario where usuario=? and clave=?";
			psm = cn.prepareStatement(sql);
			psm.setString(1, usu);
			psm.setString(2, pwd);
			rs = psm.executeQuery();
			if(rs.next()) {
				user = new Usuario();
				user.setCodigo(rs.getInt("codUsuario"));
				user.setNombre(rs.getString("nomUsuario"));
				user.setApellido(rs.getString("apeUsuario"));
				user.setUsuario(rs.getString("usuario"));
				user.setClave(rs.getString("clave"));
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
		return user;	
	}
	
}
