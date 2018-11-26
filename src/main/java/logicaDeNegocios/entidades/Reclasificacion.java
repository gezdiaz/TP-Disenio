package logicaDeNegocios.entidades;

import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Table(name = "RECLASIFICACION")

public class Reclasificacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_RECLA")
	private Integer idRecla;	

	@Column(name = "FECHA_RECLASIFICACION", nullable = false)
	private LocalDateTime fechaReclasificacion;

	@ManyToOne
	@JoinColumn(name = "USUARIO", nullable = false)
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "CLAVE_ANTERIOR", foreignKey = @ForeignKey(name = "FK_reclasificacion_clasificacion_anterior"))
	private Clasificacion clasificacionAnterior;


	@ManyToOne
	@JoinColumn(name = "CLAVE_NUEVA", nullable = false, foreignKey = @ForeignKey(name = "FK_reclasificacion_clasificacion_nueva"))
	private Clasificacion clasificacionNueva;

	@ManyToOne
	@JoinColumn(name = "NUM_TICKET", nullable = false, foreignKey = @ForeignKey(name = "FK_reclasificacion_ticket"))
	private Ticket ticket;

	public Reclasificacion() {

	}

	public Reclasificacion(Clasificacion anterior, Clasificacion nueva, Usuario usuario, LocalDateTime fechaReclasificacion) {

		this.clasificacionAnterior = anterior;
		this.clasificacionNueva = nueva;
		this.usuario = usuario;
		this.fechaReclasificacion = fechaReclasificacion;

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

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}




}
