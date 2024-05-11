package interfaces;

import java.util.List;

import entidades.Cita;

public interface CitaDAO {
	
	public int registrarCita(Cita cita);
	
	public List<Cita> listarCitas();
	
	//public Cita obtenerCita(int id);
	
	//public int editarCita(Cita cita);
	
	public int eliminarCita(int id);
	
	public List<Cita> filtrarCita(String nombre);

}
