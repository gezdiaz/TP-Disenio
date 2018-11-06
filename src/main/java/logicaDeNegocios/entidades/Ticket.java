package logicaDeNegocios.entidades;

import java.time.LocalDateTime;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "Ticket")

public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "numTicket")
	private Integer numTIcket;
	
	@ManyToOne
	@JoinColumn(name = "numLegajo")
	private Empleado solicitante;
	
	@Column(name = "fechaHoraApertura")
	private LocalDateTime fechaHoraApertura;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@OneToMany(mappedBy = "ticket")
	private List<CambioEstadoTicket> historialCambioEstadoTicket;

	@OneToMany(mappedBy = "ticket")
	private List<Reclasificacion> historialReclasificacion;
	
	@OneToMany(mappedBy = "ticket")
	private List<Intervencion> intervenciones;
	
	
	public Ticket() {
		
	}
	
	public Ticket(Integer numTIcket, Empleado solicitante, Clasificacion clasificacion, LocalDateTime fechaHoraApertura,
			String descripcion) {
		this.numTIcket = numTIcket;
		this.solicitante = solicitante;
		this.fechaHoraApertura = fechaHoraApertura;
		this.descripcion = descripcion;
		this.historialCambioEstadoTicket = new Stack<CambioEstadoTicket>();
		this.historialReclasificacion = new Stack<Reclasificacion>();
		this.intervenciones = new Stack<Intervencion>();
	}



	public List<CambioEstadoTicket> getHistorialCambioEstadoTicket() {
		return historialCambioEstadoTicket;
	}

	public void setHistorialCambioEstadoTicket(Stack<CambioEstadoTicket> historialCambioEstadoTicket) {
		this.historialCambioEstadoTicket = historialCambioEstadoTicket;
	}

	public List<Reclasificacion> getHistorialReclasificacion() {
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
