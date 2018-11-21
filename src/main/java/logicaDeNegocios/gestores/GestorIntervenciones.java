package logicaDeNegocios.gestores;

import java.time.LocalDateTime;

import accesoADatos.GestorBD;
import dto.TicketDTO;
import logicaDeNegocios.entidades.CambioEstadoIntervencion;
import logicaDeNegocios.entidades.GrupoResolucion;
import logicaDeNegocios.entidades.Intervencion;
import logicaDeNegocios.entidades.Ticket;
import logicaDeNegocios.entidades.Usuario;
import logicaDeNegocios.enumeraciones.EstadoIntervencion;

public abstract class GestorIntervenciones {

	public static Intervencion crearIntervencionRT(Ticket ticket, String nombreGrupo, String observaciones) {

		Usuario usuario = GestorUsuarios.usuarioActual();
		GrupoResolucion grupo;
		try {
			grupo = GestorBD.buscarGrupoPorNombre(nombreGrupo);
		} catch (Exception e) {
			return null;
		}
		if(grupo == null) {
			return null;
		}
		
		Intervencion intervencion = new Intervencion(observaciones, LocalDateTime.now(), ticket, grupo);

//		if (!(GestorBD.guardarIntervencion(intervencion)>0)) {
//			return null;
//		}

		CambioEstadoIntervencion cambioEstado1 = new CambioEstadoIntervencion(LocalDateTime.now(), null, EstadoIntervencion.Asignado, intervencion, usuario, observaciones);

		intervencion.actualizarEstado(cambioEstado1);

		CambioEstadoIntervencion cambioEstado2 = new CambioEstadoIntervencion(LocalDateTime.now(), intervencion.estadoActual(), EstadoIntervencion.Trabajando, intervencion, usuario, observaciones);

		intervencion.actualizarEstado(cambioEstado2);

		//		if(!GestorBD.guardarCambioEstadoIntervencion(cambioEstado1)) {
		//			return null;
		//		}
		//		if(!GestorBD.guardarCambioEstadoIntervencion(cambioEstado2)) {
		//			return null;
		//		}
		/*if (!(GestorBD.guardarIntervencion(intervencion)>0)) {
			return null;
		}*/

		return intervencion;
	}

	public static boolean terminarIntervencion(Ticket ticket, String observaciones) {

		Intervencion ultima = ticket.ultimaIntervencion();

		ultima.setObservaciones(observaciones);

		CambioEstadoIntervencion cambioEstado = new CambioEstadoIntervencion(LocalDateTime.now(), ultima.estadoActual(), EstadoIntervencion.Terminado, ultima, GestorUsuarios.usuarioActual(), observaciones);

		ultima.actualizarEstado(cambioEstado);

		//		if(!GestorBD.guardarCambioEstadoIntervencion(cambioEstado)) {
		//			return false;
		//		}
		//		if(!GestorBD.actualizarIntervencion(ultima)) {
		//			return false;
		//		}

		return true;
	}

	public static Intervencion crearIntervencion(Ticket ticket, String nombreGrupo, String observaciones) {

		Usuario usuario = GestorUsuarios.usuarioActual();
		GrupoResolucion grupo;
		try {
			grupo = GestorBD.buscarGrupoPorNombre(nombreGrupo);
		} catch (Exception e) {
			return null;
		}
		if(grupo == null) {
			return null;
		}
		Intervencion intervencion = new Intervencion(observaciones, LocalDateTime.now(), ticket, grupo);
		
		CambioEstadoIntervencion cambioEstado = new CambioEstadoIntervencion(LocalDateTime.now(), null,	EstadoIntervencion.Asignado,
																			 intervencion, usuario, observaciones);
		
		intervencion.actualizarEstado(cambioEstado);
		
		return intervencion;
		
	}

}
