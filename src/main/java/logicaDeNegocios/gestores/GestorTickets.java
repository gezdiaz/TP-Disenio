package logicaDeNegocios.gestores;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import accesoADatos.GestorBD;
import dto.HistorialTicketDTO;
import dto.TicketDTO;
import logicaDeNegocios.entidades.CambioEstadoTicket;
import logicaDeNegocios.entidades.Clasificacion;
import logicaDeNegocios.entidades.Empleado;
import logicaDeNegocios.entidades.GrupoResolucion;
import logicaDeNegocios.entidades.Intervencion;
import logicaDeNegocios.entidades.Reclasificacion;
import logicaDeNegocios.entidades.Ticket;
import logicaDeNegocios.entidades.Usuario;
import logicaDeNegocios.enumeraciones.EstadoIntervencion;
import logicaDeNegocios.enumeraciones.EstadoTicket;
import logicaDeNegocios.enumeraciones.Motivos;

public abstract class GestorTickets {

	public static TicketDTO getNuevoTicket() {
		TicketDTO ticketDTO = new TicketDTO(-1L);

		ticketDTO.setNumTicket(GestorBD.getNumTicket());


		return ticketDTO;

	}

	public static Boolean registrarTicket(TicketDTO ticketDTO) {
		Clasificacion clasificacion = GestorBD.buscarClasificacion(ticketDTO.getClasificacion());
		Usuario usuario = GestorUsuarios.usuarioActual();
		Empleado solicitante = SistemaPersonal.getEmpleado(ticketDTO.getNumLegajo());
		Ticket ticket = new Ticket(ticketDTO,usuario,solicitante);


		//Setea la clasificacion
		Reclasificacion reclasificacion = new Reclasificacion(null, clasificacion, usuario, LocalDateTime.now());

		ticket.cambiarClasificacion(reclasificacion);

		//Crea la intervencion

		Intervencion intervencion = GestorIntervenciones.crearIntervencionRT(ticket, usuario.getGrupo().getNombre()); //observaciones vacías, en la segunda pnatalla las cambia.

		if(intervencion == null) {

			return false;
		}

		ticket.agregarIntervencion(intervencion);
		
		//Cambia el estado a Abierto
		CambioEstadoTicket cambioEstado = new CambioEstadoTicket(LocalDateTime.now(), null, EstadoTicket.EN_MESA_DE_AYUDA, ticket , usuario, ticketDTO.getDescripcion());

		ticket.acutalizarEstado(cambioEstado);

		if(!GestorBD.guardarTicket(ticket)) {
			return false;
		}
		return true;
	}

	public static Integer cerrarTicketRT(TicketDTO ticketDTO, String observaciones) {

		Ticket ticket = new Ticket();

		ticket.setNumTIcket(-1L);

		ticket = GestorBD.buscarTicketPorId(ticketDTO.getNumTicket());
		//no encontro el ticket
		if(ticket == null ) {
			return 0;
		}
		//no se concecto a la base de datos
		if(ticket.getNumTIcket()==-1) {
			return -1;
		}

		CambioEstadoTicket nuevoEstado = new CambioEstadoTicket(LocalDateTime.now(), ticket.estadoActual(), EstadoTicket.CERRADO, ticket, GestorUsuarios.usuarioActual(), observaciones);

		ticket.acutalizarEstado(nuevoEstado);
		//no se pudo guardar en base de datos
		if(!GestorBD.guardarTicket(ticket)) {
			return -2;
		}

		return 1;
	}

	public static void eliminarTicket(TicketDTO ticketDTO) {

		GestorBD.eliminarTicket(ticketDTO.getNumTicket());

	}

	public static List<TicketDTO> consultarTicket(TicketDTO tDTO){
		Long numTicket = tDTO.getNumTicket();
		Long numLeg = tDTO.getNumLegajo();
		EstadoTicket estadoActual = tDTO.getEstado();
		String nombreClasificacion = tDTO.getClasificacion();
		LocalDate fechaApertura = tDTO.getFechaHoraApertura() != null? tDTO.getFechaHoraApertura().toLocalDate(): null;
		LocalDate fechaUltimoCambio = tDTO.getFechaUltimoCambioEstado() != null? tDTO.getFechaUltimoCambioEstado().toLocalDate(): null;
		String ultGrupo = tDTO.getGrupoActual();

		List<Ticket> tickets = GestorBD.buscarTickets(numTicket, numLeg, estadoActual, nombreClasificacion, fechaApertura, fechaUltimoCambio, ultGrupo);

		List<TicketDTO> ticketsDTO = new ArrayList<TicketDTO>();
		for(Ticket i : tickets) {
			ticketsDTO.add(i.getDTO());
		}

		return ticketsDTO;

	}

	public static Integer derivarTicketRT(TicketDTO ticketDTO, String nombreGrupo, String observaciones) {
		
		GrupoResolucion grupoResolucion = GestorBD.buscarGrupoPorNombre(nombreGrupo);
		Ticket ticket = GestorBD.buscarTicketPorId(ticketDTO.getNumTicket());
		
		if(ticket == null || grupoResolucion == null) {
			return 0;
		}

		Usuario usuario = GestorUsuarios.usuarioActual();

		Intervencion intervencion = GestorIntervenciones.crearIntervencion(ticket, nombreGrupo, observaciones);

		ticket.agregarIntervencion(intervencion);

		CambioEstadoTicket cambioEstado = new CambioEstadoTicket(LocalDateTime.now(), ticket.estadoActual(), EstadoTicket.DERIVADO,
				ticket, usuario, observaciones);
		ticket.acutalizarEstado(cambioEstado);

		if(!GestorBD.guardarTicket(ticket)) {
			return -2;
		}

		return 1;
	}

	public static Integer cerrarTicket(TicketDTO ticketDTO, String observaciones) {

		Ticket ticket = new Ticket();

		ticket.setNumTIcket(-1L);

		ticket = GestorBD.buscarTicketPorId(ticketDTO.getNumTicket());
		//no encontro el ticket
		if(ticket == null ) {
			return 0;
		}
		//no se concecto a la base de datos
		if(ticket.getNumTIcket()==-1) {
			return -1;
		}

		CambioEstadoTicket nuevoEstado = new CambioEstadoTicket(LocalDateTime.now(),ticket.estadoActual(),EstadoTicket.CERRADO,ticket,GestorUsuarios.usuarioActual(),observaciones);

		ticket.acutalizarEstado(nuevoEstado);

		//no se pudo guardar en base de datos
		if(!GestorBD.guardarTicket(ticket)) {
			return -2;
		}

		return 1;
	}

	public static Integer derivarTicket(TicketDTO ticketDTO, String nombreGrupo, String observaciones, String clasificacion) {
		
		Usuario usuario = GestorUsuarios.usuarioActual();
		Ticket ticket = new Ticket();
		GrupoResolucion grupoResolucion = new GrupoResolucion();

		ticket.setNumTIcket(-1L);
		grupoResolucion.setCodigo("vacio");

		ticket = GestorBD.buscarTicketPorId(ticketDTO.getNumTicket());
		grupoResolucion = GestorBD.buscarGrupoPorNombre(nombreGrupo);
		//no encontro el ticket
		if(ticket == null ) {
			return 0;
		}
		//no se encontro el grupo de resolucion
		if(grupoResolucion == null ) {
			return -1;
		}
		//no se concecto a la base de datos
		if(ticket.getNumTIcket()==-1 || grupoResolucion.getCodigo().equals("vacio")) {
			return -2;
		}

		List<Intervencion> intervenciones = ticket.getIntervenciones(grupoResolucion);
		Intervencion intervencion=null;
		if(!intervenciones.isEmpty()) {
			for(Intervencion i : intervenciones) {
				if(i.estadoActual().equals(EstadoIntervencion.EN_ESPERA)) {
					intervencion = i;
				}
			}
		}
		if(intervencion==null) {
			intervencion = GestorIntervenciones.crearIntervencion(ticket, nombreGrupo, observaciones);
		}
		else {
			GestorIntervenciones.actualizarEstado(intervencion,EstadoIntervencion.ASIGNADO, usuario, observaciones);
		}
		ticket.agregarIntervencion(intervencion);

		if(clasificacion != null) {
			
			Clasificacion nuevaClasificacion = GestorBD.buscarClasificacion(clasificacion);
			if(nuevaClasificacion == null) {
				return -1;
			}
			Reclasificacion reclasificacion = new Reclasificacion(ticket.ultimaClasificacion(), nuevaClasificacion, usuario, LocalDateTime.now());
			
			ticket.cambiarClasificacion(reclasificacion);
			
			
		}
		
		CambioEstadoTicket nuevoEstado = new CambioEstadoTicket(LocalDateTime.now(), ticket.estadoActual(), EstadoTicket.DERIVADO, ticket, usuario, observaciones);
		ticket.acutalizarEstado(nuevoEstado);
				
		if(!GestorBD.guardarTicket(ticket)) {
			return -3;
		}

		return 1;
	}

	public static Integer cambiarEstado(Motivos motivo, Long numTicket, String observaciones, Long idIntervencion) {
		
		Ticket ticket = GestorBD.buscarTicketPorId(numTicket); Boolean bandera=false; CambioEstadoTicket nuevoEstado=null;
		
		if(ticket==null) {
			return 0;
		}
		
		if(motivo==null || motivo.equals(Motivos.INTERVENCION_INCORRECTA) || motivo.equals(Motivos.PARCIALMENTE_TERMINADA)) {
			nuevoEstado = new CambioEstadoTicket(LocalDateTime.now(), ticket.estadoActual(), EstadoTicket.EN_MESA_DE_AYUDA, ticket, GestorUsuarios.usuarioActual(), observaciones);
		}else {
			if(motivo.equals(Motivos.TRABAJO_TERMINADO)) {
				for(Intervencion i : ticket.getIntervenciones()) {
					if(i.estadoActual().equals(EstadoIntervencion.EN_ESPERA)) {
						bandera = true;
					}
				}
				if(bandera) {
					nuevoEstado = new CambioEstadoTicket(LocalDateTime.now(), ticket.estadoActual(), EstadoTicket.EN_MESA_DE_AYUDA, ticket, GestorUsuarios.usuarioActual(), observaciones);
				}
				else {
					nuevoEstado = new CambioEstadoTicket(LocalDateTime.now(), ticket.estadoActual(), EstadoTicket.ESPERA_OK, ticket, GestorUsuarios.usuarioActual(), observaciones);
				}
			}
		}
		
		ticket.acutalizarEstado(nuevoEstado);
		
		if(!GestorBD.guardarTicket(ticket)) {
			return 0;
		}
		
		return 1;
	}

	public static Integer cambiarClasificacion(TicketDTO ticketDTO) {
		
		Ticket ticket = GestorBD.buscarTicketPorId(ticketDTO.getNumTicket());
		
		if(ticket==null) {
			return 0;
		}
		
		if(!ticket.ultimaClasificacion().getNombre().equals(ticketDTO.getClasificacion())) {
			Reclasificacion reclasificacion = new Reclasificacion(ticket.ultimaClasificacion(), GestorBD.buscarClasificacion(ticketDTO.getClasificacion()), GestorUsuarios.usuarioActual(), LocalDateTime.now());
			ticket.cambiarClasificacion(reclasificacion);
			if(!GestorBD.guardarTicket(ticket)) {
				return 0;
			}
		}
		
		return 1;
	}

	public static List<HistorialTicketDTO> getHistorialTicket(TicketDTO ticketDTO) {
		// TODO Auto-generated method stub
		ArrayList<HistorialTicketDTO> historialDTO = new ArrayList<HistorialTicketDTO>();
		Ticket ticket = GestorBD.buscarTicketPorId(ticketDTO.getNumTicket());
		HistorialTicketDTO entradaHistorial;
		
		for(CambioEstadoTicket c: ticket.getHistorialCambioEstadoTicket()) {
			entradaHistorial = new HistorialTicketDTO();
			entradaHistorial.setFechaHoraCambioEstado(c.getFechaHoraCambio());
			entradaHistorial.setNuevoEstado(c.getEstadoNuevo());
			entradaHistorial.setObservaciones(c.getObservaciones());
			entradaHistorial.setOperador(c.getResponsableCambio().getNombreUsuario());
			entradaHistorial.setClasificacion(ticket.getClasificacionEnFecha(c.getFechaHoraCambio()).getNombre());
			entradaHistorial.setGrupoResolucion(ticket.getGrupoEnFecha(c.getFechaHoraCambio()).getNombre());
			historialDTO.add(entradaHistorial);
		}
		
		Collections.reverse(historialDTO);
		
		return historialDTO;
	}

}
