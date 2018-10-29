package logicaDeNegocios.entidades;

import java.time.LocalDateTime;

public class CambioEstadoTicket {
	
	private LocalDateTime fechaHoraCambio; //cambiar nombre en diagrama de clases
	private EstadoTicket estadoAnterior;
	private EstadoTicket estadoNuevo;
	private Ticket ticket;
	private Usuario responsableCambio;
	private String observaciones;
	

	public LocalDateTime getFechaHoraCambio() {
		return fechaHoraCambio;
	}


	public void setFechaHoraCambio(LocalDateTime fechaHoraCambio) {
		this.fechaHoraCambio = fechaHoraCambio;
	}


	public EstadoTicket getEstadoAnterior() {
		return estadoAnterior;
	}


	public void setEstadoAnterior(EstadoTicket estadoAnterior) {
		this.estadoAnterior = estadoAnterior;
	}


	public EstadoTicket getEstadoNuevo() {
		return estadoNuevo;
	}


	public void setEstadoNuevo(EstadoTicket estadoNuevo) {
		this.estadoNuevo = estadoNuevo;
	}


	public Ticket getTicket() {
		return ticket;
	}


	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}


	public Usuario getResponsableCambio() {
		return responsableCambio;
	}


	public void setResponsableCambio(Usuario responsableCambio) {
		this.responsableCambio = responsableCambio;
	}


	public String getObservaciones() {
		return observaciones;
	}


	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}


	public CambioEstadoTicket() {
		// TODO Auto-generated constructor stub
	}

}
