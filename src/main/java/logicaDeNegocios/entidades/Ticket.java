package logicaDeNegocios.entidades;

import java.time.LocalDateTime;
import java.util.*;
import javax.persistence.*;

import dto.TicketDTO;
import logicaDeNegocios.enumeraciones.EstadoIntervencion;
import logicaDeNegocios.enumeraciones.EstadoTicket;

@Entity
@Table(name = "TICKET")

public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NUM_TICKET")
	private Long numTIcket;
	
	@ManyToOne
	@JoinColumn(name = "NUM_LEGAJO")
	private Empleado solicitante;
	
	@Column(name = "FECHA_HORA_APERTURA")
	private LocalDateTime fechaHoraApertura;
	
	@Column(name = "DESCRIPCION",nullable = false, length = 255)
	private String descripcion;
	
	@OneToMany(mappedBy = "ticket")
	private List<CambioEstadoTicket> historialCambioEstadoTicket;

	@OneToMany(mappedBy = "ticket")
	private List<Reclasificacion> historialReclasificacion;
	
	@OneToMany(mappedBy = "ticket")
	private List<Intervencion> intervenciones;
	
	
	public Ticket() {
		
	}
	
	public Ticket(Long numTIcket, Empleado solicitante, LocalDateTime fechaHoraApertura,
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

	public Long getNumTIcket() {
		return numTIcket;
	}

	public void setNumTIcket(Long numTIcket) {
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

	public void acutalizarEstado(CambioEstadoTicket cambioEstado) {
		
		historialCambioEstadoTicket.add(cambioEstado);
		
	}

	public void cambiarClasificacion(Reclasificacion reclasificacion) {
		
		historialReclasificacion.add(reclasificacion);
		
	}

	public void agregarIntervencion(Intervencion intervencion) {

		intervenciones.add(intervencion);
		
	}

	public EstadoTicket estadoActual() {
		CambioEstadoTicket ultimoCambio = historialCambioEstadoTicket.get(0);
		EstadoTicket actual = ultimoCambio.getEstadoNuevo();
		
		for(CambioEstadoTicket c: historialCambioEstadoTicket) {
			if(c.getFechaHoraCambio().compareTo(ultimoCambio.getFechaHoraCambio()) > 0) {
				ultimoCambio = c;
				actual = c.getEstadoNuevo();
			}
		}
		
		return actual;
	}

	public Intervencion ultimaIntervencion() {
		
		Intervencion ultima = this.intervenciones.get(0);
		
		for(Intervencion i : intervenciones) {
			if(i.getFechaHoraASignacion().compareTo(ultima.getFechaHoraASignacion())>0) {
				ultima = i;
			}
		}
		
		return ultima;
	}

	public Clasificacion ultimaCalsificacion() {
		Reclasificacion ultima = historialReclasificacion.get(0);
		
		for(Reclasificacion rc : historialReclasificacion) {
			if(rc.getFechaReclasificacion().compareTo(ultima.getFechaReclasificacion())>0) {
				ultima = rc;
			}
		}
		return ultima.getClasificacionNueva();
	}
	
	
	public TicketDTO getDTO() {
		TicketDTO dto = new TicketDTO(numTIcket);
		
		dto.setClasificacion(ultimaCalsificacion().getNombre());
		dto.setDescripcion(descripcion);
		dto.setFechaHoraApertura(fechaHoraApertura);
		dto.setNumLegajo(solicitante.getNumLegajo());
		
		return dto;
	}

}
