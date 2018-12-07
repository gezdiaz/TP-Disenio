package logicaDeNegocios.gestores;

import accesoADatos.GestorBD;
import logicaDeNegocios.entidades.Usuario;

public abstract class GestorUsuarios {

	private static Usuario usuarioActual;

	public static Usuario usuarioActual() {
		return usuarioActual;
	}

	public static Integer iniciarSesion(String nombreUsuario, String clave) {

		Usuario usuario = GestorBD.buscarUsuario(nombreUsuario);

		if (usuario == null) {
			return -2;

		}else {
			if (usuario.getNombreUsuario().isEmpty()) {
				return -1;
			} else {
				if (usuario.getClaveHash() == (clave.hashCode())) {
					usuarioActual = usuario;
					return 1;
				} else {
					return 0;
				}
			} 
		}

	}

	public static void cerrarSesion() {
		
		usuarioActual = null;
		
	}
}
