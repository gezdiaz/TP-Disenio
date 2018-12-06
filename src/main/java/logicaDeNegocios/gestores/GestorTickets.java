package logicaDeNegocios.gestores;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import accesoADatos.GestorBD;
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
		// TODO Metodo que crea un nuevo ticket
		TicketDTO ticketDTO = new TicketDTO(-1L);

		ticketDTO.setNumTicket(GestorBD.getNumTicket());


		return ticketDTO;

	}

	public static Boolean registrarTicket(TicketDTO ticketDTO) {
		// TODO Auto-generated method stub
		Clasificacion clasificacion = GestorBD.buscarClasificacion(ticketDTO.getClasificacion());
		Empleado solicitante = SistemaPersonal.getEmpleado(ticketDTO.getNumLegajo());
		Ticket ticket = new Ticket(ticketDTO.getNumTicket(), solicitante, ticketDTO.getFechaHoraApertura(), ticketDTO.getDescripcion());
		Usuario usuario = GestorUsuarios.usuarioActual();

		ticket.setOperador(usuario);

		//Cambia el estado a Abierto
		CambioEstadoTicket cambioEstado = new CambioEstadoTicket(LocalDateTime.now(), null, EstadoTicket.EN_MESA_DE_AYUDA, ticket , usuario, ticketDTO.getDescripcion());

		ticket.acutalizarEstado(cambioEstado);

		//Setea la clasificacion
		Reclasificacion reclasificacion = new Reclasificacion(null, clasificacion, usuario, LocalDateTime.now());

		ticket.cambiarClasificacion(reclasificacion);
//		System.out.println("Clasificacion: "+clasificacion);
		//Crea la intervencion
//		System.out.println("Empiezo a crear la intervencion");
		Intervencion intervencion = GestorIntervenciones.crearIntervencionRT(ticket, usuario.getGrupo().getNombre(), ""); //observaciones vacías, en la segunda pnatalla las cambia.
//		System.out.println("Termino de crear la intervencion");
		if(intervencion == null) {
//			System.out.println("Intervencion nula");
			return false;
		}

		ticket.agregarIntervencion(intervencion);

		//Guarda en la base de datos
		//		if(!GestorBD.guardarCambioEstadoTIcket(cambioEstado)) {
		//			return false;
		//		}
		//		if(!GestorBD.guardarReclasificacion(reclasificacion)) {
		//			return false;
		//		}

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

		if(!GestorIntervenciones.terminarIntervencion(ticket, observaciones)) {
			return 0;
		}

		CambioEstadoTicket nuevoEstado = new CambioEstadoTicket(LocalDateTime.now(), ticket.estadoActual(), EstadoTicket.CERRADO, ticket, GestorUsuarios.usuarioActual(), observaciones);

		//		if(!GestorBD.guardarCambioEstadoTIcket(nuevoEstado)) {
		//			return 0;
		//		}

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

	public static List<TicketDTO> consultarTicket(Long numTicket,Long numLeg,EstadoTicket estadoActual,String nombreClasificacion,LocalDate fechaApertura, LocalDate fechaUltimoGrupo, String ultGrupo){

		List<Ticket> tickets = GestorBD.buscarTickets(numTicket, numLeg, estadoActual, nombreClasificacion, fechaApertura, fechaUltimoGrupo, ultGrupo);

		List<TicketDTO> ticketsDTO = new ArrayList<TicketDTO>();
		for(Ticket i : tickets) {
			ticketsDTO.add(i.getDTO());
		}

		return ticketsDTO;

	}

	public static Integer derivarTicketRT(TicketDTO ticketDTO, String nombreGrupo, String observaciones) {

		Ticket ticket = GestorBD.buscarTicketPorId(ticketDTO.getNumTicket());
		GrupoResolucion grupoResolucion = GestorBD.buscarGrupoPorNombre(nombreGrupo);

		if(ticket == null || grupoResolucion == null) {
			return 0;
		}

		Usuario usuario = GestorUsuarios.usuarioActual();

		Intervencion intervencion = GestorIntervenciones.crearIntervencion(ticket, nombreGrupo, observaciones);

		if(!GestorIntervenciones.terminarIntervencion(ticket, observaciones)) {
			return -1;
		}

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
			GestorIntervenciones.actualizarEstado(intervencion,EstadoIntervencion.ASIGNADO, observaciones);
		}
		ticket.agregarIntervencion(intervencion);

		Usuario usuario = GestorUsuarios.usuarioActual();
		CambioEstadoTicket nuevoEstado = new CambioEstadoTicket(LocalDateTime.now(), ticket.estadoActual(), EstadoTicket.DERIVADO, ticket, usuario, observaciones);
		ticket.acutalizarEstado(nuevoEstado);

		if(clasificacion != null) {
			
			Clasificacion nuevaClasificacion = GestorBD.buscarClasificacion(clasificacion);
			if(nuevaClasificacion == null) {
				return -1;
			}
			Reclasificacion reclasificacion = new Reclasificacion(ticket.ultimaCalsificacion(), nuevaClasificacion, usuario, LocalDateTime.now());
			
			ticket.cambiarClasificacion(reclasificacion);
			
			
		}
		
		
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
		
		if(!ticket.ultimaCalsificacion().getNombre().equals(ticketDTO.getClasificacion())) {
			Reclasificacion reclasificacion = new Reclasificacion(ticket.ultimaCalsificacion(), GestorBD.buscarClasificacion(ticketDTO.getClasificacion()), GestorUsuarios.usuarioActual(), LocalDateTime.now());
			ticket.cambiarClasificacion(reclasificacion);
			if(!GestorBD.guardarTicket(ticket)) {
				return 0;
			}
		}
		
		return 1;
	}

}
