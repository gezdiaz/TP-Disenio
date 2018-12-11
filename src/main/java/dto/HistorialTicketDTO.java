package dto;

import java.time.LocalDateTime;

import logicaDeNegocios.enumeraciones.EstadoTicket;

public class HistorialTicketDTO {

	private LocalDateTime fechaHoraCambioEstado;
	private String Operador;
	private EstadoTicket nuevoEstado;
	private String grupoResolucion;
	private String clasificacion;
	private String Observaciones;
	
	
	public LocalDateTime getFechaHoraCambioEstado() {
		return fechaHoraCambioEstado;
	}
	public void setFechaHoraCambioEstado(LocalDateTime fechaHoraCambioEstado) {
		this.fechaHoraCambioEstado = fechaHoraCambioEstado;
	}
	public String getOperador() {
		return Operador;
	}
	public void setOperador(String operador) {
		Operador = operador;
	}
	public EstadoTicket getNuevoEstado() {
		return nuevoEstado;
	}
	public void setNuevoEstado(EstadoTicket nuevoEstado) {
		this.nuevoEstado = nuevoEstado;
	}
	public String getGrupoResolucion() {
		return grupoResolucion;
	}
	public void setGrupoResolucion(String grupoResolucion) {
		this.grupoResolucion = grupoResolucion;
	}
	public String getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}
	public String getObservaciones() {
		return Observaciones;
	}
	public void setObservaciones(String observaciones) {
		Observaciones = observaciones;
	}
	
}
