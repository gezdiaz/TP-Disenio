package logicaDeNegocios.entidades;

import java.time.LocalDateTime;

public class CambioEstadoIntervencion {
	
	private LocalDateTime fechaHoraCambio; //cambiar nombre en diagrama de clases
	private EstadoIntervencion estadoAnterior;
	private EstadoIntervencion estadoNuevo;
	private Intervencion intervencion;
	private Usuario responsableCambio;
	private String observaciones;
	
	public LocalDateTime getFechaHoraCambio() {
		return fechaHoraCambio;
	}

	public void setFechaHoraCambio(LocalDateTime fechaHoraCambio) {
		this.fechaHoraCambio = fechaHoraCambio;
	}

	public EstadoIntervencion getEstadoAnterior() {
		return estadoAnterior;
	}

	public void setEstadoAnterior(EstadoIntervencion estadoAnterior) {
		this.estadoAnterior = estadoAnterior;
	}

	public EstadoIntervencion getEstadoNuevo() {
		return estadoNuevo;
	}

	public void setEstadoNuevo(EstadoIntervencion estadoNuevo) {
		this.estadoNuevo = estadoNuevo;
	}

	public Intervencion getIntervencion() {
		return intervencion;
	}

	public void setIntervencion(Intervencion intervencion) {
		this.intervencion = intervencion;
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

	public CambioEstadoIntervencion() {
		// TODO Auto-generated constructor stub
	}

}
