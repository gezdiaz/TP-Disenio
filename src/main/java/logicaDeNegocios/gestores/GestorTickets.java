package logicaDeNegocios.gestores;

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
import logicaDeNegocios.enumeraciones.EstadoTicket;

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

		//Cambia el estado a Abierto
		CambioEstadoTicket cambioEstado = new CambioEstadoTicket(LocalDateTime.now(), null, EstadoTicket.Abierto, ticket , usuario, ticketDTO.getDescripcion());

		ticket.acutalizarEstado(cambioEstado);

		//Setea la clasificacion
		Reclasificacion reclasificacion = new Reclasificacion(null, clasificacion, usuario, LocalDateTime.now());

		ticket.cambiarClasificacion(reclasificacion);
		System.out.println("Clasificacion: "+clasificacion);
		//Crea la intervencion
		System.out.println("Empiezo a crear la intervencion");
		Intervencion intervencion = GestorIntervenciones.crearIntervencionRT(ticket, usuario.getGrupo().getNombre(), ""); //observaciones vacías, en la segunda pnatalla las cambia.
		System.out.println("Termino de crear la intervencion");
		if(intervencion == null) {
			System.out.println("Intervencion nula");
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

		if(!GestorBD.actualizarTicket(ticket)) {
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

		CambioEstadoTicket nuevoEstado = new CambioEstadoTicket(LocalDateTime.now(), ticket.estadoActual(), EstadoTicket.Cerrado, ticket, GestorUsuarios.usuarioActual(), observaciones);

		//		if(!GestorBD.guardarCambioEstadoTIcket(nuevoEstado)) {
		//			return 0;
		//		}

		ticket.acutalizarEstado(nuevoEstado);
		//no se pudo guardar en base de datos
		if(!GestorBD.actualizarTicket(ticket)) {
			return -2;
		}

		return 1;
	}

//	public static void guardarTicket(TicketDTO ticketDTO) {
//
//		Ticket ticket = new Ticket();
//		ticket.setNumTIcket(ticketDTO.getNumTicket());
//		ticket.setDescripcion("");
//
//		if(!GestorBD.actualizarTicket(ticket)) {
//			System.out.println("No se guarda el ticket de mierda");
//		}
//
//	}

	public static void eliminarTicket(TicketDTO ticketDTO) {

		GestorBD.eliminarTicket(ticketDTO.getNumTicket());

	}
	
	public static List<TicketDTO> consultarTicket(Long numTicket,Long numLeg,String nombreCLasificacion,LocalDateTime fechaApertura, LocalDateTime fechaUltimoGrupo, GrupoResolucion ultGrupo){
		
		
		
		return null;
		
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
		
		CambioEstadoTicket cambioEstado = new CambioEstadoTicket(LocalDateTime.now(), ticket.estadoActual(), EstadoTicket.Derivado,
																 ticket, usuario, observaciones);
		ticket.acutalizarEstado(cambioEstado);
		
		if(!GestorBD.actualizarTicket(ticket)) {
			return -2;
		}
		
		return 1;
	}

}
