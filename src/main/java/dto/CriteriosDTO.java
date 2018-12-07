package dto;

import java.time.LocalDate;

import logicaDeNegocios.enumeraciones.EstadoIntervencion;

public class CriteriosDTO {

	private EstadoIntervencion estadoActual;
	private LocalDate fechaDesde, fechaHasta;
	private Long numTicket, numLegajo;
	
	public CriteriosDTO(EstadoIntervencion estadoActual, LocalDate fechaDesde, LocalDate fechaHasta, Long numTicket,
			Long numLegajo) {
		super();
		this.estadoActual = estadoActual;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.numTicket = numTicket;
		this.numLegajo = numLegajo;
	}

	public EstadoIntervencion getEstadoActual() {
		return estadoActual;
	}

	public void setEstadoActual(EstadoIntervencion estadoActual) {
		this.estadoActual = estadoActual;
	}

	public LocalDate getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(LocalDate fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public LocalDate getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(LocalDate fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public Long getNumTicket() {
		return numTicket;
	}

	public void setNumTicket(Long numTicket) {
		this.numTicket = numTicket;
	}

	public Long getNumLegajo() {
		return numLegajo;
	}

	public void setNumLegajo(Long numLegajo) {
		this.numLegajo = numLegajo;
	}
		
}
