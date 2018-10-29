package logicaDeNegocios.entidades;

import java.time.LocalDateTime;
import java.util.Stack;

public class Intervencion {
	
	private String observaciones;
	private LocalDateTime fechaHoraASignacion;
	private Ticket ticket;
	private GrupoResolucion grupoResolucion;
	private Stack<CambioEstadoIntervencion> historialCambioEstadoIntervencion = new Stack<CambioEstadoIntervencion>();
	
	
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


	public Intervencion() {
		// TODO Auto-generated constructor stub
	}

}
