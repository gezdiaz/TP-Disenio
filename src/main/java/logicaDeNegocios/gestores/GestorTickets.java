package logicaDeNegocios.gestores;

import java.time.LocalDateTime;

import accesoADatos.GestorBD;
import dto.TicketDTO;
import logicaDeNegocios.entidades.CambioEstadoTicket;
import logicaDeNegocios.entidades.Clasificacion;
import logicaDeNegocios.entidades.Empleado;
import logicaDeNegocios.entidades.Intervencion;
import logicaDeNegocios.entidades.Reclasificacion;
import logicaDeNegocios.entidades.Ticket;
import logicaDeNegocios.entidades.Usuario;
import logicaDeNegocios.enumeraciones.EstadoTicket;

public abstract class GestorTickets {

	public static TicketDTO getNuevoTicket() {
		// TODO Metodo que crea un nuevo ticket
		return new TicketDTO(115464L);
	}

	public static boolean registrarTicket(TicketDTO ticketDTO) {
		// TODO Auto-generated method stub
		Clasificacion clasificacion = GestorBD.buscarClasificacion(ticketDTO.getClasificacion());
		Empleado solicitante = SistemaPersonal.getEmpleado(ticketDTO.getNumLegajo());
		Ticket ticket = new Ticket(ticketDTO.getNumTicket(), solicitante, ticketDTO.getFechaHoraApertura(), ticketDTO.getDescripcion());
		Usuario usuario = GestorUsuarios.usuarioActual();
		
		//Cambia el estado a Abierto
		CambioEstadoTicket cambioEstado = new CambioEstadoTicket(LocalDateTime.now(), null, EstadoTicket.Abierto, ticket , usuario, ticketDTO.getDescripcion());
	
		ticket.acutalizarEstado(cambioEstado);
		
		//Setea la clasificacion
		Reclasificacion reclasificacion = new Reclasificacion(null, clasificacion, usuario);
		
		ticket.cambiarClasificacion(reclasificacion);
		
		//Crea la intervencion
		Intervencion intervencion = GestorIntervenciones.crearIntervencion(ticket, usuario, ""); //observaciones vacías, en la segunda pnatalla las cambia.
		
		if(intervencion == null) {
			return false;
		}
		
		ticket.agregarIntervencion(intervencion);
		
		//Guarda en la base de datos		
//		if(!GestorBD.guardarTicket(ticket)) {
//			return false;
//		}
		return true;
	}

}
