package interfaces;

import entidades.Usuario;

public interface UsuarioDAO {

	public Usuario validarLogin(String usu, String pwd);
}
