package dto;

import java.time.LocalDateTime;

public class TicketDTO {

	Long numTicket;
	String clasificacion;
	Long numLegajo;
	String descripcion;
	LocalDateTime fechaHoraApertura;


	public TicketDTO(Long num) {
		numTicket = num;
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
