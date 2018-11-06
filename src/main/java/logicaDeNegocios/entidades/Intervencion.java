package logicaDeNegocios.entidades;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Stack;

import javax.persistence.*;

@Entity
@Table(name = "INTERVENCION")
public class Intervencion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_INT")
	private int idInt;

	@Column(name="OBSERVACIONES", length=255, nullable=false)
	private String observaciones;
	
	@Column(name="FECHA_HORA_ASIGNACION")
	private LocalDateTime fechaHoraASignacion;

	@ManyToOne
	@JoinColumn(name="NUM_TICKET")
	private Ticket ticket;
	
	@ManyToOne
	@JoinColumn(name="ID_GR")
	private GrupoResolucion grupoResolucion;
	
	@OneToMany(mappedBy = "intervencion")
	private List<CambioEstadoIntervencion> historialCambioEstadoIntervencion;
	

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

	public List<CambioEstadoIntervencion> getHistorialCambioEstadoIntervencion() {
		return historialCambioEstadoIntervencion;
	}


	public void setHistorialCambioEstadoIntervencion(List<CambioEstadoIntervencion> historialCambioEstadoIntervencion) {
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
