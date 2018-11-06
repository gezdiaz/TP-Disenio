package logicaDeNegocios.gestores;

import logicaDeNegocios.entidades.GrupoResolucion;
import logicaDeNegocios.entidades.Usuario;

public abstract class GestorUsuarios {
	
	private static Usuario usuarioActual;
	
	public static Usuario usuarioActual() {
		return usuarioActual;
	}
	
	public static void iniciarSesion(String nombreUsuario, String clave) {
		
		//TODO Buscar usuario enla base de datos
		GrupoResolucion grupo = new GrupoResolucion("MSA", "Mesa de Ayuda");
		Usuario usuario = new Usuario(nombreUsuario, clave, grupo);
		grupo.agregarUsuario(usuario);
		usuarioActual = usuario;
	}
}
		