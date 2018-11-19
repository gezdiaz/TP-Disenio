package dto;

import java.time.LocalDateTime;

import logicaDeNegocios.enumeraciones.EstadoTicket;

public class TicketDTO {

	Long numTicket;
	String clasificacion, grupoActual;
	Long numLegajo;
	String descripcion;
	LocalDateTime fechaHoraApertura, fechaUltimoCambioEstado;
	EstadoTicket estado;


	public TicketDTO(Long num) {
		numTicket = num;
	}
	
	
	
	
	public String getGrupoActual() {
		return grupoActual;
	}




	public void setGrupoActual(String grupoActual) {
		this.grupoActual = grupoActual;
	}




	public LocalDateTime getFechaUltimoCambioEstado() {
		return fechaUltimoCambioEstado;
	}




	public void setFechaUltimoCambioEstado(LocalDateTime fechaUltimoCambioEstado) {
		this.fechaUltimoCambioEstado = fechaUltimoCambioEstado;
	}




	public EstadoTicket getEstado() {
		return estado;
	}


	public void setEstado(EstadoTicket estado) {
		this.estado = estado;
	}


	public Long getNumTicket() {
		return numTicket;
	}
	public void setNumTicket(Long numTicket) {
		this.numTicket = numTicket;
	}
	public String getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}
	public Long getNumLegajo() {
		return numLegajo;
	}
	public void setNumLegajo(Long numLegajo) {
		this.numLegajo = numLegajo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public LocalDateTime getFechaHoraApertura() {
		return fechaHoraApertura;
	}
	public void setFechaHoraApertura(LocalDateTime fechaHoraApertura) {
		this.fechaHoraApertura = fechaHoraApertura;
	}


}
