package logicaDeNegocios.entidades;

import java.time.LocalDate;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "Clasificacion")

public class Clasificacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	private Integer codigo;
	
	@Column(name = "nombre",nullable = false,length = 30)
	private String nombre;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "activa")
	private Boolean activa;
	
	@Column(name = "fechaCreacion")
	private LocalDate fechaCreacion;
	
	@ManyToMany(mappedBy = "clasificaciones")
	private List<GrupoResolucion> gruposDeResolucion;
	
	
	
	
	public Clasificacion() {
		
	}
	
	public Clasificacion(String nombre, String descripcion,ArrayList<GrupoResolucion> gruposDeResolucion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.gruposDeResolucion = gruposDeResolucion;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getActiva() {
		return activa;
	}

	public void setActiva(Boolean activa) {
		this.activa = activa;
	}

	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public List<GrupoResolucion> getGruposDeResolucion() {
		return gruposDeResolucion;
	}

	public void setGruposDeResolucion(List<GrupoResolucion> gruposDeResolucion) {
		this.gruposDeResolucion = gruposDeResolucion;
	}

	
}
