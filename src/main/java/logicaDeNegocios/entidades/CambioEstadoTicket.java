package logicaDeNegocios.entidades;

import java.time.LocalDateTime;

import logicaDeNegocios.enumeraciones.EstadoTicket;

import javax.persistence.*;

@Entity
@Table(name="CambioEstadoTicket")
public class CambioEstadoTicket {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IdCambioTick")
	private int idCambioTick;

	@Column(name="FechaHoraCambio")
	private LocalDateTime fechaHoraCambio; //cambiar nombre en diagrama de clases
	
	@Enumerated(EnumType.STRING)
	@Column(name="EstadoAnterior")
	private EstadoTicket estadoAnterior;
	
	@Enumerated(EnumType.STRING)
	@Column(name="EstadoNuevo")
	private EstadoTicket estadoNuevo;
	
	@ManyToOne
	@JoinColumn(name="NumTicket")
	private Ticket ticket;
	
	@ManyToOne
	@JoinColumn(name="ResponsableCambio")
	private Usuario responsableCambio;
	
	@Column(name="Observaciones", length=255, nullable=false)
	private String observaciones;
	


	public CambioEstadoTicket() {

	}
		
	public CambioEstadoTicket(LocalDateTime fechaHoraCambio, EstadoTicket estadoAnterior, EstadoTicket estadoNuevo,
			Ticket ticket, Usuario responsableCambio, String observaciones) {
		super();
		this.fechaHoraCambio = fechaHoraCambio;
		this.estadoAnterior = estadoAnterior;
		this.estadoNuevo = estadoNuevo;
		this.ticket = ticket;
		this.responsableCambio = responsableCambio;
		this.observaciones = observaciones;
	}

	public int getIdCambioTick() {
		return idCambioTick;
	}

	public void setIdCambioTick(int idCambioTick) {
		this.idCambioTick = idCambioTick;
	}
	
	public LocalDateTime getFechaHoraCambio() {
		return fechaHoraCambio;
	}


	public void setFechaHoraCambio(LocalDateTime fechaHoraCambio) {
		this.fechaHoraCambio = fechaHoraCambio;
	}


	public EstadoTicket getEstadoAnterior() {
		return estadoAnterior;
	}


	public void setEstadoAnterior(EstadoTicket estadoAnterior) {
		this.estadoAnterior = estadoAnterior;
	}


	public EstadoTicket getEstadoNuevo() {
		return estadoNuevo;
	}


	public void setEstadoNuevo(EstadoTicket estadoNuevo) {
		this.estadoNuevo = estadoNuevo;
	}


	public Ticket getTicket() {
		return ticket;
	}


	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}


	public Usuario getResponsableCambio() {
		return responsableCambio;
	}


	public void setResponsableCambio(Usuario responsableCambio) {
		this.responsableCambio = responsableCambio;
	}


	public String getObservaciones() {
		return observaciones;
	}


	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}


}
