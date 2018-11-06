package logicaDeNegocios.gestores;

import java.time.LocalDateTime;

import accesoADatos.GestorBD;
import logicaDeNegocios.entidades.CambioEstadoIntervencion;
import logicaDeNegocios.entidades.GrupoResolucion;
import logicaDeNegocios.entidades.Intervencion;
import logicaDeNegocios.entidades.Ticket;
import logicaDeNegocios.entidades.Usuario;
import logicaDeNegocios.enumeraciones.EstadoIntervencion;

public abstract class GestorIntervenciones {

	public static Intervencion crearIntervencion(Ticket ticket, Usuario usuario, String observaciones) {
		
		Intervencion intervencion = new Intervencion("", LocalDateTime.now(), ticket, usuario.getGrupo());
		CambioEstadoIntervencion cambioEstado1 = new CambioEstadoIntervencion(LocalDateTime.now(), null, EstadoIntervencion.Asignado, intervencion, usuario, observaciones);
		
		intervencion.actualizarEstado(cambioEstado1);
		
		CambioEstadoIntervencion cambioEstado2 = new CambioEstadoIntervencion(LocalDateTime.now(), intervencion.estadoActual(), EstadoIntervencion.Trabajando, intervencion, usuario, observaciones);

		intervencion.actualizarEstado(cambioEstado2);
		
//		if (!GestorBD.guardarIntervencion(intervencion)) {
//			return null;
//		}
		
		return intervencion;
	}

}
