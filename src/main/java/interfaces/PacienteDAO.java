package interfaces;

import java.util.List;

import entidades.Paciente;

public interface PacienteDAO {

	public int registrarPaciente(Paciente paciente);
	
	public List<Paciente> listarPacientes();
}
