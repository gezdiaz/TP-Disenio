package logicaDeNegocios.gestores;

import accesoADatos.GestorBD;
import dto.TicketDTO;
import logicaDeNegocios.entidades.Clasificacion;
import logicaDeNegocios.entidades.Ticket;

public class GestorTickets {

	public static TicketDTO getNuevoTicket() {
		// TODO Metodo que crea un nuevo ticket
		return null;
	}

	public static boolean registrarTicket(TicketDTO ticketDTO) {
		// TODO Auto-generated method stub
		Clasificacion clasificacion = GestorBD.buscarClasificacion(ticketDTO.getClasificacion());
		
//		Ticket ticket = new Ticket(ticketDTO.getNumTicket(), ticketDTO.getNumLegajo(), ticketDTO.getClasificacion(), fechaHoraApertura, descripcion)
		
		
		return true;
	}

}
