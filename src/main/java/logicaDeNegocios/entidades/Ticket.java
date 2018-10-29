package logicaDeNegocios.entidades;

import java.time.LocalDateTime;
import java.util.*;

public class Ticket {
	
	private Integer numTIcket;
	private Empleado solicitante;
	private Clasificacion clasificacion;
	private LocalDateTime fechaHoraApertura;
	private String descripcion;
	private Stack<CambioEstadoTicket> historialCambioEstadoTicket = new Stack<CambioEstadoTicket>();
	private Stack<Reclasificacion> historialReclasificacion = new Stack<Reclasificacion>();
	private List<Intervencion> intervenciones = new ArrayList<Intervencion>();
	
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

	public Date getFechaHoraApertura() {
		return fechaHoraApertura;
	}

	public void setFechaHoraApertura(Date fechaHoraApertura) {
		this.fechaHoraApertura = fechaHoraApertura;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Ticket() {//Constructor
		// TODO Auto-generated constructor stub
	}

}
