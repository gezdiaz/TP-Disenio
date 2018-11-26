package logicaDeNegocios.entidades;

import javax.persistence.*;

@Entity
@Table(name="USUARIO")
public class Usuario {
	
	@Id
	@Column(name="NOMBRE_USUARIO", length=30)
	private String nombreUsuario;
	
	@Column(name="CLAVE", nullable = false, length=20)
	private int claveHash;
	
	@OneToOne
	@JoinColumn(name = "NUM_LEGAJO", nullable = false, foreignKey = @ForeignKey(name = "FK_usuario_empleado"))
	Empleado empleado;
	
	@ManyToOne
	@JoinColumn(name="ID_GR", nullable = false, foreignKey = @ForeignKey(name = "FK_usuario_grupo_der_resolucion"))
	private GrupoResolucion grupoResolucion;

	
	public Usuario() {
		
	}

	public Usuario(String nombreUsuario, int clave, GrupoResolucion grupoResolucion) {
		this.nombreUsuario = nombreUsuario;
		this.claveHash = clave;
		this.grupoResolucion = grupoResolucion;
	}
	

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public int getClaveHash() {
		return claveHash;
	}

	public void setClaveHash(int clave) {
		this.claveHash = clave;
	}

	public GrupoResolucion getGrupoResolucion() {
		return grupoResolucion;
	}

	public void setGrupoResolucion(GrupoResolucion grupoResolucion) {
		this.grupoResolucion = grupoResolucion;
	}

	@Override
	public String toString() {
		return "Usuario [nombreUsuario=" + nombreUsuario + ", clave=" + claveHash + ", grupoResolucion=" + grupoResolucion
				+ "]";
	}

	public GrupoResolucion getGrupo() {
		
		return grupoResolucion;
		
	}

}
