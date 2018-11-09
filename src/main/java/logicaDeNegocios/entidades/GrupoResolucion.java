package logicaDeNegocios.entidades;

import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "GRUPO_DE_RESOLUCION")

public class GrupoResolucion {

	@Id
	@Column(name = "ID_GR", length = 50)
	private String codigo;

	@Column(name = "NOMBRE",nullable = false,length = 50)
	private String nombre;

	@OneToMany(mappedBy = "grupoResolucion")
	private List<Usuario> usuarios;

	@OneToMany(mappedBy = "grupoResolucion")
	private List<Intervencion> intervenciones;

	@ManyToMany()
	@JoinTable(name = "CAPACITADO_PARA", 
	joinColumns = { @JoinColumn(name = "ID_GR") }, 
	inverseJoinColumns = { @JoinColumn(name = "CLAVE")}
			)
	private List<Clasificacion> clasificaciones;

	public GrupoResolucion() {

	}

	public GrupoResolucion(String codigo,String nombre) {

		this.codigo = codigo;
		this.nombre = nombre;
		this.usuarios = new ArrayList<Usuario>();
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

	public void agregarUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
	}

	@Override
	public String toString() {
		return "GrupoResolucion [codigo=" + codigo + ", nombre=" + nombre +"]";
	}	


}
