package logicaDeNegocios.entidades;

import java.time.LocalDateTime;
import java.util.*;

public class Ticket {
	
	private Integer numTIcket;
	private Empleado solicitante;
	private Clasificacion clasificacion;
	private LocalDateTime fechaHoraApertura;
	private String descripcion;
	private Stack<CambioEstadoTicket> historialCambioEstadoTicket;
	private Stack<Reclasificacion> historialReclasificacion;
	private List<Intervencion> intervenciones;
	
	
	public Ticket() {//Constructor
		this.historialCambioEstadoTicket = new Stack<CambioEstadoTicket>();
		this.historialReclasificacion = new Stack<Reclasificacion>();
		this.intervenciones = new Stack<Intervencion>();
	}
	
	public Ticket(Integer numTIcket, Empleado solicitante, Clasificacion clasificacion, LocalDateTime fechaHoraApertura,
			String descripcion) {
		this.numTIcket = numTIcket;
		this.solicitante = solicitante;
		this.clasificacion = clasificacion;
		this.fechaHoraApertura = fechaHoraApertura;
		this.descripcion = descripcion;
		this.historialCambioEstadoTicket = new Stack<CambioEstadoTicket>();
		this.historialReclasificacion = new Stack<Reclasificacion>();
		this.intervenciones = new Stack<Intervencion>();
	}



	public Stack<CambioEstadoTicket> getHistorialCambioEstadoTicket() {
		return historialCambioEstadoTicket;
	}

	public void setHistorialCambioEstadoTicket(Stack<CambioEstadoTicket> historialCambioEstadoTicket) {
		this.historialCambioEstadoTicket = historialCambioEstadoTicket;
	}

	public Stack<Reclasificacion> getHistorialReclasificacion() {
		return historialReclasificacion;
	}

	public void setHistorialReclasificacion(Stack<Reclasificacion> historialReclasificacion) {
		this.historialReclasificacion = historialReclasificacion;
	}

	public List<Intervencion> getIntervenciones() {
		return intervenciones;
	}

	public void setIntervenciones(List<Intervencion> intervenciones) {
		this.intervenciones = intervenciones;
	}

	public void setFechaHoraApertura(LocalDateTime fechaHoraApertura) {
		this.fechaHoraApertura = fechaHoraApertura;
	}

	public Integer getNumTIcket() {
		return numTIcket;
	}

	public void setNumTIcket(Integer numTIcket) {
		this.numTIcket = numTIcket;
	}

	public Empleado getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Empleado solicitante) {
		this.solicitante = solicitante;
	}

	public Clasificacion getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(Clasificacion clasificacion) {
		this.clasificacion = clasificacion;
	}

	public LocalDateTime getFechaHoraApertura() {
		return fechaHoraApertura;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
