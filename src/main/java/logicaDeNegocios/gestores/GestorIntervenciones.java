package logicaDeNegocios.gestores;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import accesoADatos.GestorBD;
import dto.IntervencionDTO;
import dto.TicketDTO;
import logicaDeNegocios.entidades.CambioEstadoIntervencion;
import logicaDeNegocios.entidades.GrupoResolucion;
import logicaDeNegocios.entidades.Intervencion;
import logicaDeNegocios.entidades.Ticket;
import logicaDeNegocios.entidades.Usuario;
import logicaDeNegocios.enumeraciones.EstadoIntervencion;
import logicaDeNegocios.enumeraciones.EstadoTicket;

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

	public static void actualizarEstado(Intervencion intervencion, EstadoIntervencion asignado, String observaciones) {
		
		CambioEstadoIntervencion nuevoEstado = new CambioEstadoIntervencion(LocalDateTime.now(),intervencion.estadoActual(),asignado,intervencion,GestorUsuarios.usuarioActual(),observaciones);
		intervencion.actualizarEstado(nuevoEstado);
	}
	
	public static List<IntervencionDTO> consultarIntervencion(EstadoIntervencion estadoActual, LocalDate fechaDesde, LocalDate fechaHasta, Long numTicket, Long numLegajo){
		
		LocalDateTime fechaHoraDesde=null, fechaHoraHasta=null;
		String codGrupoActual = GestorUsuarios.usuarioActual().getGrupo().getCodigo();
		
		if(fechaDesde!=null) {
			fechaHoraDesde=LocalDateTime.of(fechaDesde, LocalTime.of(0, 0));
		}
		
		if(fechaHasta!=null) {
			fechaHoraHasta=LocalDateTime.of(fechaHasta, LocalTime.of(0, 0));
		}
			
		List<Intervencion> intervenciones = GestorBD.buscarintervenciones(estadoActual, fechaHoraDesde,fechaHoraHasta, numTicket, numLegajo, codGrupoActual);
		List<IntervencionDTO> intervencionesDTO = new ArrayList<IntervencionDTO>();
		for(Intervencion i : intervenciones) {
			intervencionesDTO.add(i.getDTO());
		}

		return intervencionesDTO;

	}

}
