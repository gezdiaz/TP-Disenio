package logicaDeNegocios.entidades;

import java.time.LocalDateTime;
import java.util.Stack;

import javax.persistence.*;

@Entity
@Table(name = "Intervencion")
public class Intervencion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdInt")
	private int idInt;

	@Column(name="Observaciones", length=255, nullable=false)
	private String observaciones;
	
	@Column(name="FechaHoraAsignacion")
	private LocalDateTime fechaHoraASignacion;

	@ManyToOne
	@JoinColumn(name="NumTicket")
	private Ticket ticket;
	
	@ManyToOne
	@JoinColumn(name="idGR")
	private GrupoResolucion grupoResolucion;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "intervencion")
	private Stack<CambioEstadoIntervencion> historialCambioEstadoIntervencion = new Stack<CambioEstadoIntervencion>();
	

	public Intervencion() {

	}
		
	public Intervencion(String observaciones, LocalDateTime fechaHoraASignacion, Ticket ticket,
			GrupoResolucion grupoResolucion, Stack<CambioEstadoIntervencion> historialCambioEstadoIntervencion) {
		this.observaciones = observaciones;
		this.fechaHoraASignacion = fechaHoraASignacion;
		this.ticket = ticket;
		this.grupoResolucion = grupoResolucion;
		this.historialCambioEstadoIntervencion = historialCambioEstadoIntervencion;
	}

	public int getIdInt() {
		return idInt;
	}

	public void setIdInt(int idInt) {
		this.idInt = idInt;
	}

	public Stack<CambioEstadoIntervencion> getHistorialCambioEstadoIntervencion() {
		return historialCambioEstadoIntervencion;
	}


	public void setHistorialCambioEstadoIntervencion(Stack<CambioEstadoIntervencion> historialCambioEstadoIntervencion) {
		this.historialCambioEstadoIntervencion = historialCambioEstadoIntervencion;
	}


	public String getObservaciones() {
		return observaciones;
	}


	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}


	public LocalDateTime getFechaHoraASignacion() {
		return fechaHoraASignacion;
	}


	public void setFechaHoraASignacion(LocalDateTime fechaHoraASignacion) {
		this.fechaHoraASignacion = fechaHoraASignacion;
	}


	public Ticket getTicket() {
		return ticket;
	}


	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}


	public GrupoResolucion getGrupoResolucion() {
		return grupoResolucion;
	}


	public void setGrupoResolucion(GrupoResolucion grupoResolucion) {
		this.grupoResolucion = grupoResolucion;
	}

}
