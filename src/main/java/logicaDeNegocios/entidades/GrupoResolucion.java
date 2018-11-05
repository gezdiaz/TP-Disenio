package logicaDeNegocios.entidades;

import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "Grupo_de_Resolucion")

public class GrupoResolucion {

	@Id
	@Column(name = "idGR", length = 50)
	private String codigo;
	
	@Column(name = "nombre",nullable = false,length = 50)
	private String nombre;
	
	@OneToMany(mappedBy = "grupoResolucion")
	private List<Usuario> usuarios;
	
	@OneToMany(mappedBy = "grupoResolucion")
	private List<Intervencion> intervenciones;
	
	@ManyToMany()
	@JoinTable(name = "Capacitado_Para", 
	        joinColumns = { @JoinColumn(name = "idFR") }, 
	        inverseJoinColumns = { @JoinColumn(name = "clave")}
	)
	private List<Clasificacion> clasificaciones;

	public GrupoResolucion() {

	}
	
	public GrupoResolucion(String codigo,String nombre,ArrayList<Usuario> usuarios) {
		
		this.codigo = codigo;
		this.nombre = nombre;
		this.usuarios = usuarios;
		
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Intervencion> getIntervenciones() {
		return intervenciones;
	}

	public void setIntervenciones(List<Intervencion> intervenciones) {
		this.intervenciones = intervenciones;
	}

	public List<Clasificacion> getClasificaciones() {
		return clasificaciones;
	}

	public void setClasificaciones(List<Clasificacion> clasificaciones) {
		this.clasificaciones = clasificaciones;
	}

	@Override
	public String toString() {
		return "GrupoResolucion [codigo=" + codigo + ", nombre=" + nombre + ", intervenciones=" + intervenciones
				+ ", clasificaciones=" + clasificaciones + "]";
	}	
	
	
}
