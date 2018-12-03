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
	joinColumns = { @JoinColumn(name = "ID_GR", nullable = false, foreignKey = @ForeignKey(name = "FK_capacitado_para_grupo_de_resolucion")) }, 
	inverseJoinColumns = { @JoinColumn(name = "CLAVE", nullable = false, foreignKey = @ForeignKey(name = "FK_capacitado_para_clasificacion"))}
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GrupoResolucion other = (GrupoResolucion) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
}
