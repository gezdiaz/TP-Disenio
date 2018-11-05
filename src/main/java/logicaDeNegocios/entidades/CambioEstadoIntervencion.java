package logicaDeNegocios.entidades;

import java.time.LocalDateTime;

import logicaDeNegocios.enumeraciones.EstadoIntervencion;

import javax.persistence.*;

@Entity
@Table(name="CambioEstadoIntervencion")
public class CambioEstadoIntervencion {
	
	@Id
	@Column(name="IdCambioInt")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCambioInt;

	@Column(name="FechaHoraCambio")
	private LocalDateTime fechaHoraCambio; //cambiar nombre en diagrama de clases
	
	@Enumerated(EnumType.STRING)
	@Column(name="EstadoAnterior")
	private EstadoIntervencion estadoAnterior;
	
	@Enumerated(EnumType.STRING)
	@Column(name="EstadoNuevo")
	private EstadoIntervencion estadoNuevo;
	
	@ManyToOne
	@JoinColumn(name="Intervencion")
	private Intervencion intervencion;
	
	@ManyToOne
	@JoinColumn(name="ResponsableCambio")
	private Usuario responsableCambio;
	
	@Column(name="Observaciones", length=255, nullable = false)
	private String observaciones;
	
	

	public CambioEstadoIntervencion() {

	}
	
	public CambioEstadoIntervencion(LocalDateTime fechaHoraCambio, EstadoIntervencion estadoAnterior,
			EstadoIntervencion estadoNuevo, Intervencion intervencion, Usuario responsableCambio,
			String observaciones) {
		super();
		this.fechaHoraCambio = fechaHoraCambio;
		this.estadoAnterior = estadoAnterior;
		this.estadoNuevo = estadoNuevo;
		this.intervencion = intervencion;
		this.responsableCambio = responsableCambio;
		this.observaciones = observaciones;
	}


	public int getIdCambioInt() {
		return idCambioInt;
	}

	public void setIdCambioInt(int idCambioInt) {
		this.idCambioInt = idCambioInt;
	}

	public LocalDateTime getFechaHoraCambio() {
		return fechaHoraCambio;
	}

	public void setFechaHoraCambio(LocalDateTime fechaHoraCambio) {
		this.fechaHoraCambio = fechaHoraCambio;
	}

	public EstadoIntervencion getEstadoAnterior() {
		return estadoAnterior;
	}

	public void setEstadoAnterior(EstadoIntervencion estadoAnterior) {
		this.estadoAnterior = estadoAnterior;
	}

	public EstadoIntervencion getEstadoNuevo() {
		return estadoNuevo;
	}

	public void setEstadoNuevo(EstadoIntervencion estadoNuevo) {
		this.estadoNuevo = estadoNuevo;
	}

	public Intervencion getIntervencion() {
		return intervencion;
	}

	public void setIntervencion(Intervencion intervencion) {
		this.intervencion = intervencion;
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
