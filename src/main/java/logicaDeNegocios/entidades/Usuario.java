package logicaDeNegocios.entidades;

import javax.persistence.*;

@Entity
@Table(name="Usuario")
public class Usuario {
	
	@Id
	@Column(name="NombreUsusario", length=30)
	private String nombreUsuario;
	
	@Column(name="Clave", length=20, nullable=false)
	private String clave;
	
	@ManyToOne
	@JoinColumn(name="GrupoResolucion")
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
