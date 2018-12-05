package logicaDeNegocios.entidades;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import dto.IntervencionDTO;
import logicaDeNegocios.enumeraciones.EstadoIntervencion;

@Entity
@Table(name = "INTERVENCION")
public class Intervencion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_INT")
	private Long idInt;

	@Column(name="OBSERVACIONES", length=255, nullable=false)
	private String observaciones;

	@Column(name="FECHA_HORA_ASIGNACION", nullable = false)
	private LocalDateTime fechaHoraAsignacion;

	@ManyToOne
	@JoinColumn(name="NUM_TICKET", nullable = false, foreignKey = @ForeignKey(name = "FK_intervencion_ticket"))
	private Ticket ticket;

	@ManyToOne
	@JoinColumn(name="ID_GR", nullable = false, foreignKey = @ForeignKey(name = "FK_intervencion_grupo_de_resolucion"))
	private GrupoResolucion grupoResolucion;

	@OneToMany(mappedBy = "intervencion", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	private List<CambioEstadoIntervencion> historialCambioEstadoIntervencion;


	public Intervencion() {

	}

	public Intervencion(String observaciones, LocalDateTime fechaHoraAsignacion, Ticket ticket,
			GrupoResolucion grupoResolucion) {
		this.observaciones = observaciones;
		this.fechaHoraAsignacion = fechaHoraAsignacion;
		this.ticket = ticket;
		this.grupoResolucion = grupoResolucion;
		this.historialCambioEstadoIntervencion = new ArrayList<CambioEstadoIntervencion>();
	}

	public Long getIdInt() {
		return idInt;
	}

	public void setIdInt(Long idInt) {
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
		return fechaHoraAsignacion;
	}

	
	public void setFechaHoraASignacion(LocalDateTime fechaHoraAsignacion) {
		this.fechaHoraAsignacion = fechaHoraAsignacion;
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

	public void actualizarEstado(CambioEstadoIntervencion cambioEstadoIntervencion) {

		historialCambioEstadoIntervencion.add(cambioEstadoIntervencion);

	}

	public EstadoIntervencion estadoActual() {
		CambioEstadoIntervencion ultimoCambio = historialCambioEstadoIntervencion.get(historialCambioEstadoIntervencion.size()-1);
		EstadoIntervencion actual = ultimoCambio.getEstadoNuevo();
		//		
		//		for(CambioEstadoIntervencion c: historialCambioEstadoIntervencion) {
		//			if(c.getFechaHoraCambio().compareTo(ultimoCambio.getFechaHoraCambio()) > 0) {
		//				ultimoCambio = c;
		//				actual = c.getEstadoNuevo();
		//			}
		//		}

		return actual;
	}
	
	public IntervencionDTO getDTO() {
		IntervencionDTO intervencionDTO= new IntervencionDTO();
		
		intervencionDTO.setIdIntervencion(this.idInt);
		intervencionDTO.setNumTicket(this.ticket.getNumTIcket());
		intervencionDTO.setNumLegajo(this.ticket.getSolicitante().getNumLegajo());
		intervencionDTO.setClasificacion(this.ticket.ultimaCalsificacion().getNombre());
		intervencionDTO.setEstadoIntervencion(estadoActual());
		intervencionDTO.setEstadoTicket(this.ticket.estadoActual());
		intervencionDTO.setFechaApertura(this.ticket.getFechaHoraApertura());
		intervencionDTO.setFechaAsignacionIntervencion(this.fechaHoraAsignacion);
		intervencionDTO.setDescripcionTicket(this.ticket.getDescripcion());
		intervencionDTO.setGrupoResolucion(this.grupoResolucion.getNombre());
		
		return intervencionDTO;
	}

	@Override
	public String toString() {
		return "Intervencion [observaciones=" + observaciones + ", fechaHoraASignacion=" + fechaHoraAsignacion
				+ ", grupoResolucion=" + grupoResolucion + ", historialCambioEstadoIntervencion="
				+ historialCambioEstadoIntervencion + "]";
	}
	
}
