package logicaDeNegocios.entidades;

import java.time.LocalDateTime;
import java.util.*;
import javax.persistence.*;

import dto.TicketDTO;
import logicaDeNegocios.enumeraciones.EstadoTicket;

@Entity
@Table(name = "TICKET")

public class Ticket {

	@Id
	@Column(name = "NUM_TICKET")
	private Long numTIcket;

	@ManyToOne
	@JoinColumn(name = "NUM_LEGAJO", nullable = false, foreignKey = @ForeignKey(name = "FK_ticket_empleado"))
	private Empleado solicitante;

	@Column(name = "FECHA_HORA_APERTURA", nullable = false)
	private LocalDateTime fechaHoraApertura;

	@Column(name = "DESCRIPCION",nullable = false, length = 255)
	private String descripcion;

	@OneToMany(mappedBy = "ticket", cascade = {CascadeType.ALL})
	private List<CambioEstadoTicket> historialCambioEstadoTicket;

	@OneToMany(mappedBy = "ticket", cascade = {CascadeType.ALL})
	private List<Reclasificacion> historialReclasificacion;

	@OneToMany(mappedBy = "ticket", cascade = {CascadeType.ALL})
	private List<Intervencion> intervenciones;


	public Ticket() {
		this.historialCambioEstadoTicket = new ArrayList<CambioEstadoTicket>();
		this.historialReclasificacion = new ArrayList<Reclasificacion>();
		this.intervenciones = new ArrayList<Intervencion>();
	}

	public Ticket(Long numTIcket, Empleado solicitante, LocalDateTime fechaHoraApertura,
			String descripcion) {
		this.numTIcket = numTIcket;
		this.solicitante = solicitante;
		this.fechaHoraApertura = fechaHoraApertura;
		this.descripcion = descripcion;
		this.historialCambioEstadoTicket = new ArrayList<CambioEstadoTicket>();
		this.historialReclasificacion = new ArrayList<Reclasificacion>();
		this.intervenciones = new ArrayList<Intervencion>();
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
		cambioEstado.setTicket(this);

	}

	public void cambiarClasificacion(Reclasificacion reclasificacion) {

		historialReclasificacion.add(reclasificacion);
		reclasificacion.setTicket(this);

	}

	public void agregarIntervencion(Intervencion intervencion) {

		intervenciones.add(intervencion);
		intervencion.setTicket(this);
	}

	public EstadoTicket estadoActual() {
		CambioEstadoTicket ultimoCambio = historialCambioEstadoTicket.get(historialCambioEstadoTicket.size()-1);
		EstadoTicket actual = ultimoCambio.getEstadoNuevo();

		//		for(CambioEstadoTicket c: historialCambioEstadoTicket) {
		//			if(c.getFechaHoraCambio().compareTo(ultimoCambio.getFechaHoraCambio()) > 0) {
		//				ultimoCambio = c;
		//				actual = c.getEstadoNuevo();
		//			}
		//		}

		return actual;
	}

	public Intervencion ultimaIntervencion() {

		Intervencion ultima = this.intervenciones.get(intervenciones.size()-1);

		//		for(Intervencion i : intervenciones) {
		//			if(i.getFechaHoraASignacion().compareTo(ultima.getFechaHoraASignacion())>0) {
		//				ultima = i;
		//			}
		//		}

		return ultima;
	}

	public Clasificacion ultimaCalsificacion() {
		System.out.println("Historial recla: "+historialReclasificacion);
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

		dto.setDescripcion(descripcion);
		dto.setFechaHoraApertura(fechaHoraApertura);
		if (!historialReclasificacion.isEmpty()) {
			dto.setClasificacion(ultimaCalsificacion().getNombre());
		}
		if(solicitante != null) {
			dto.setNumLegajo(solicitante.getNumLegajo());
		}
		return dto;
	}

	public List<Intervencion> getIntervenciones(GrupoResolucion grupoResolucion) {
		List<Intervencion> intervencionesRes = new ArrayList<Intervencion>();
		for(Intervencion i : intervenciones) {
			if(i.getGrupoResolucion().equals(grupoResolucion)) {
				intervencionesRes.add(i);
			}
		}
		return intervencionesRes;
	}

}
