package logicaDeNegocios.entidades;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import logicaDeNegocios.enumeraciones.EstadoIntervencion;

@Entity
@Table(name = "INTERVENCION")
public class Intervencion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_INT")
	private int idInt;

	@Column(name="OBSERVACIONES", length=255, nullable=false)
	private String observaciones;

	@Column(name="FECHA_HORA_ASIGNACION", nullable = false)
	private LocalDateTime fechaHoraASignacion;

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

	public Intervencion(String observaciones, LocalDateTime fechaHoraASignacion, Ticket ticket,
			GrupoResolucion grupoResolucion) {
		this.observaciones = observaciones;
		this.fechaHoraASignacion = fechaHoraASignacion;
		this.ticket = ticket;
		this.grupoResolucion = grupoResolucion;
		this.historialCambioEstadoIntervencion = new ArrayList<CambioEstadoIntervencion>();
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

}
