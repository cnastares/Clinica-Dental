package entidades;

import java.sql.Date;
import java.sql.Time;

public class Cita {
	public int id_cita;
	public String id_paciente, id_personal, estado, tipo_atencion;
	public Date fecha;
	public Time hora;
	
	public int getId_cita() {
		return id_cita;
	}
	public void setId_cita(int id_cita) {
		this.id_cita = id_cita;
	}
	public String getId_paciente() {
		return id_paciente;
	}
	public void setId_paciente(String id_paciente) {
		this.id_paciente = id_paciente;
	}
	public String getId_personal() {
		return id_personal;
	}
	public void setId_personal(String id_personal) {
		this.id_personal = id_personal;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getTipo_atencion() {
		return tipo_atencion;
	}
	public void setTipo_atencion(String tipo_atencion) {
		this.tipo_atencion = tipo_atencion;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Time getHora() {
		return hora;
	}
	public void setHora(Time hora) {
		this.hora = hora;
	}

}
