package interfaces;

import java.util.List;
import entidades.Curso;

public interface CursoDAO {

	public int registrarCurso(Curso curso);
	
	public List<Curso> listarCursos();
	
	public Curso obtenerCurso(int id);
	
	public int editarCurso(Curso curso);
	
	public int eliminarCurso(int id);
	
	
}
