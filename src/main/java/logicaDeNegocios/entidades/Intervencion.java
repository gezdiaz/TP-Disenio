package logicaDeNegocios.entidades;

import java.time.LocalDateTime;
import java.util.Stack;

public class Intervencion {
	
	private String observaciones;
	private LocalDateTime fechaHoraASignacion;
	private Ticket ticket;
	private GrupoResolucion grupoResolucion;
	private Stack<CambioEstadoIntervencion> historialCambioEstadoIntervencion = new Stack<CambioEstadoIntervencion>();
	

	public Intervencion() {

	}
		
	public Intervencion(String observaciones, LocalDateTime fechaHoraASignacion, Ticket ticket,
			GrupoResolucion grupoResolucion, Stack<CambioEstadoIntervencion> historialCambioEstadoIntervencion) {
		this.observaciones = observaciones;
		this.fechaHoraASignacion = fechaHoraASignacion;
		this.ticket = ticket;
		this.grupoResolucion = grupoResolucion;
		this.historialCambioEstadoIntervencion = historialCambioEstadoIntervencion;
	}



	public Stack<CambioEstadoIntervencion> getHistorialCambioEstadoIntervencion() {
		return historialCambioEstadoIntervencion;
	}


	public void setHistorialCambioEstadoIntervencion(Stack<CambioEstadoIntervencion> historialCambioEstadoIntervencion) {
		this.historialCambioEstadoIntervencion = historialCambioEstadoIntervencion;
	}


	public String getObservaciones() {
		return observaciones;
	}


	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}


	public LocalDateTime getFechaHoraASignacion() {
		return fechaHoraASignacion;
	}


	public void setFechaHoraASignacion(LocalDateTime fechaHoraASignacion) {
		this.fechaHoraASignacion = fechaHoraASignacion;
	}


	public Ticket getTicket() {
		return ticket;
	}


	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}


	public GrupoResolucion getGrupoResolucion() {
		return grupoResolucion;
	}


	public void setGrupoResolucion(GrupoResolucion grupoResolucion) {
		this.grupoResolucion = grupoResolucion;
	}

}
