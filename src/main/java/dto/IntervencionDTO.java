package dto;

import java.time.LocalDateTime;

import logicaDeNegocios.enumeraciones.EstadoIntervencion;
import logicaDeNegocios.enumeraciones.EstadoTicket;
import logicaDeNegocios.enumeraciones.Motivos;

public class IntervencionDTO {
	
	private Long idIntervencion;
	private Long numTicket;
	private Long numLegajo;
	private EstadoTicket estadoTicket;
	private String clasificacion;
	private LocalDateTime fechaApertura;
	private EstadoIntervencion estadoIntervencion;
	private LocalDateTime fechaAsignacionIntervencion;
	private String grupoResolucion;
	private String descripcionTicket;
	private Motivos motivo;
	
	public IntervencionDTO() {
		
	}
	
	public IntervencionDTO(Long id) {
		this.idIntervencion=id;
	
	}
	public Long getIdIntervencion() {
		return idIntervencion;
	}

	public void setIdIntervencion(Long idIntervencion) {
		this.idIntervencion = idIntervencion;
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

	public EstadoTicket getEstadoTicket() {
		return estadoTicket;
	}

	public void setEstadoTicket(EstadoTicket estadoTicket) {
		this.estadoTicket = estadoTicket;
	}

	public String getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	public LocalDateTime getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(LocalDateTime fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	public EstadoIntervencion getEstadoIntervencion() {
		return estadoIntervencion;
	}

	public void setEstadoIntervencion(EstadoIntervencion estadoIntervencion) {
		this.estadoIntervencion = estadoIntervencion;
	}

	public LocalDateTime getFechaAsignacionIntervencion() {
		return fechaAsignacionIntervencion;
	}

	public void setFechaAsignacionIntervencion(LocalDateTime fechaAsignacionIntervencion) {
		this.fechaAsignacionIntervencion = fechaAsignacionIntervencion;
	}

	public String getGrupoResolucion() {
		return grupoResolucion;
	}

	public void setGrupoResolucion(String grupoResolucion) {
		this.grupoResolucion = grupoResolucion;
	}

	public String getDescripcionTicket() {
		return descripcionTicket;
	}

	public void setDescripcionTicket(String descripcionTicket) {
		this.descripcionTicket = descripcionTicket;
	}

	public Motivos getMotivo() {
		return motivo;
	}

	public void setMotivo(Motivos motivo) {
		this.motivo = motivo;
	}

}
