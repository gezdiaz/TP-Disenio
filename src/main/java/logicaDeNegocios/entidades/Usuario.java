package logicaDeNegocios.entidades;

public class Usuario {
	
	private String nombreUsuario;
	private String clave;
	private GrupoResolucion grupoResolucion;

	
	public Usuario() {
		
	}

	public Usuario(String nombreUsuario, String clave, GrupoResolucion grupoResolucion) {
		this.nombreUsuario = nombreUsuario;
		this.clave = clave;
		this.grupoResolucion = grupoResolucion;
	}
	

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public GrupoResolucion getGrupoResolucion() {
		return grupoResolucion;
	}

	public void setGrupoResolucion(GrupoResolucion grupoResolucion) {
		this.grupoResolucion = grupoResolucion;
	}
	
	

}
