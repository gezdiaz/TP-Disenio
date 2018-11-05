package logicaDeNegocios.entidades;

import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Table(name = "Reclasificacion")

public class Reclasificacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idRecla")
	private Integer idRecla;	
	
	@Column(name = "fechaReclasificcacion")
	private LocalDateTime fechaReclasificacion;
	
	@ManyToOne
	@JoinColumn(name = "usuario")
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Clasificacion clasificacionAnterior;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Clasificacion clasificacionNueva;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	private Ticket ticket;
	
	public Reclasificacion() {
		
	}
	
	public Reclasificacion(Clasificacion anterior, Clasificacion nueva, Usuario usuario) {
		
		this.clasificacionAnterior = anterior;
		this.clasificacionNueva = nueva;
		this.usuario = usuario;
		
	}

	public Integer getIdRecla() {
		return idRecla;
	}

	public void setIdRecla(Integer idRecla) {
		this.idRecla = idRecla;
	}

	public LocalDateTime getFechaReclasificacion() {
		return fechaReclasificacion;
	}

	public void setFechaReclasificacion(LocalDateTime fechaReclasificacion) {
		this.fechaReclasificacion = fechaReclasificacion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Clasificacion getClasificacionAnterior() {
		return clasificacionAnterior;
	}

	public void setClasificacionAnterior(Clasificacion clasificacionAnterior) {
		this.clasificacionAnterior = clasificacionAnterior;
	}

	public Clasificacion getClasificacionNueva() {
		return clasificacionNueva;
	}

	public void setClasificacionNueva(Clasificacion clasificacionNueva) {
		this.clasificacionNueva = clasificacionNueva;
	}

//	public Ticket getTicket() {
//		return ticket;
//	}
//
//	public void setTicket(Ticket ticket) {
//		this.ticket = ticket;
//	}
//	
	
	
	
}
