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
	private Long numTicket;

	@ManyToOne
	@JoinColumn(name = "NUM_LEGAJO", nullable = false, foreignKey = @ForeignKey(name = "FK_ticket_empleado"))
	private Empleado solicitante;

	@Column(name = "FECHA_HORA_APERTURA", nullable = false)
	private LocalDateTime fechaHoraApertura;

	@Column(name = "DESCRIPCION",nullable = false, length = 255)
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name = "OPERADOR", nullable = false, foreignKey = @ForeignKey(name = "FK_ticket_usuario"))
	private Usuario operador; 

	@OneToMany(mappedBy = "ticket", cascade = {CascadeType.ALL})
	@OrderBy("idCambioTick ASC")
	private List<CambioEstadoTicket> historialCambioEstadoTicket;

	@OneToMany(mappedBy = "ticket", cascade = {CascadeType.ALL})
	@OrderBy("idRecla ASC")
	private List<Reclasificacion> historialReclasificacion;

	@OneToMany(mappedBy = "ticket", cascade = {CascadeType.ALL})
	@OrderBy("idInt ASC")
	private List<Intervencion> intervenciones;
	


	public Ticket() {
		this.historialCambioEstadoTicket = new ArrayList<CambioEstadoTicket>();
		this.historialReclasificacion = new ArrayList<Reclasificacion>();
		this.intervenciones = new ArrayList<Intervencion>();
	}

	public Ticket(Long numTIcket, Empleado solicitante, LocalDateTime fechaHoraApertura,
			String descripcion, Usuario usuario) {
		this.numTicket = numTIcket;
		this.solicitante = solicitante;
		this.fechaHoraApertura = fechaHoraApertura;
		this.descripcion = descripcion;
		this.historialCambioEstadoTicket = new ArrayList<CambioEstadoTicket>();
		this.historialReclasificacion = new ArrayList<Reclasificacion>();
		this.intervenciones = new ArrayList<Intervencion>();
		this.operador=usuario;
	}

	public Ticket(TicketDTO ticketDTO, Usuario usuario, Empleado solicitante) {
		this(ticketDTO.getNumTicket(),solicitante,ticketDTO.getFechaHoraApertura(),ticketDTO.getDescripcion(),usuario);
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
		return numTicket;
	}

	public void setNumTIcket(Long numTIcket) {
		this.numTicket = numTIcket;
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

	public Usuario getOperador() {
		return operador;
	}

	public void setOperador(Usuario operador) {
		this.operador = operador;
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

		for(Intervencion i : intervenciones) {
			if(i.ultimoCambioEstado().getFechaHoraCambio().compareTo(ultima.ultimoCambioEstado().getFechaHoraCambio()) > 0) {
				ultima = i;
			}
		}

		return ultima;
	}

	public Clasificacion ultimaClasificacion() {
//		System.out.println("Historial recla: "+historialReclasificacion);
		Reclasificacion ultima = historialReclasificacion.get(historialReclasificacion.size()-1);

//		for(Reclasificacion rc : historialReclasificacion) {
//			if(rc.getFechaReclasificacion().compareTo(ultima.getFechaReclasificacion())>0) {
//				ultima = rc;
//			}
//		}
		return ultima.getClasificacionNueva();
	}


	public TicketDTO getDTO() {
		TicketDTO dto = new TicketDTO(numTicket);

		dto.setDescripcion(descripcion);
		dto.setFechaHoraApertura(fechaHoraApertura);
		dto.setNombreOperador(operador.getNombreUsuario());
		if (!historialReclasificacion.isEmpty()) {
			dto.setClasificacion(ultimaClasificacion().getNombre());
		}
		if(solicitante != null) {
			dto.setNumLegajo(solicitante.getNumLegajo());
		}
		if(!historialCambioEstadoTicket.isEmpty()) {
			dto.setEstado(estadoActual());
			dto.setFechaUltimoCambioEstado(historialCambioEstadoTicket.get(historialCambioEstadoTicket.size()-1).getFechaHoraCambio());
		}
		if(!intervenciones.isEmpty()) {
			dto.setGrupoActual(ultimoGrupo().getNombre());
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


	@Override
	public String toString() {
		return "Ticket [numTIcket=" + numTicket + ", solicitante=" + solicitante + ", fechaHoraApertura="
				+ fechaHoraApertura + ", descripcion=" + descripcion + ", historialCambioEstadoTicket="
				+ historialCambioEstadoTicket + ", historialReclasificacion=" + historialReclasificacion
				+ ", intervenciones=" + intervenciones +"]";
	}

	public GrupoResolucion ultimoGrupo() {
		EstadoTicket estActual = estadoActual();
		
		if(estActual.equals(EstadoTicket.CERRADO) || estActual.equals(EstadoTicket.EN_MESA_DE_AYUDA) || estActual.equals(EstadoTicket.ESPERA_OK)) {
			return new GrupoResolucion("A1", "Mesa de Ayuda");
		}else {
			return ultimaIntervencion().getGrupoResolucion();
		}
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fechaHoraApertura == null) ? 0 : fechaHoraApertura.hashCode());
		result = prime * result + ((numTicket == null) ? 0 : numTicket.hashCode());
		result = prime * result + ((solicitante == null) ? 0 : solicitante.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		if (fechaHoraApertura == null) {
			if (other.fechaHoraApertura != null)
				return false;
		} else if (!fechaHoraApertura.equals(other.fechaHoraApertura))
			return false;
		if (numTicket == null) {
			if (other.numTicket != null)
				return false;
		} else if (!numTicket.equals(other.numTicket))
			return false;
		if (solicitante == null) {
			if (other.solicitante != null)
				return false;
		} else if (!solicitante.equals(other.solicitante))
			return false;
		return true;
	}

	public CambioEstadoTicket ultimoCambioEstado() {
		return historialCambioEstadoTicket.get(historialCambioEstadoTicket.size()-1);
	}

	public Clasificacion getClasificacionEnFecha(LocalDateTime fechaHora) {
		int i = 0;
		while(i < (historialReclasificacion.size()-1) && historialReclasificacion.get(i+1).getFechaReclasificacion().compareTo(fechaHora) <= 0) {
			i++;
		}
		
		return historialReclasificacion.get(i).getClasificacionNueva();
	}

	public GrupoResolucion getGrupoEnFecha(LocalDateTime fechaHora) {

		Intervencion ultimaModificada = intervenciones.get(0);

		for(Intervencion i: intervenciones) {
			if(i.getFechaHoraUltimoCambioAntesDe(fechaHora).compareTo(ultimaModificada.getFechaHoraUltimoCambioAntesDe(fechaHora)) >0) {
				ultimaModificada = i;
			}
		}
		
		
		return ultimaModificada.getGrupoResolucion();
	}

	
	

}
