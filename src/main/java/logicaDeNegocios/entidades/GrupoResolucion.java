package logicaDeNegocios.entidades;

import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "Grupo_de_Resolucion")

public class GrupoResolucion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idGR")
	private String codigo;
	
	@Column(name = "nombre",nullable = false,length = 50)
	private String nombre;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "grupoResolucion")
	private List<Usuario> usuarios;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "grupoResolucion")
	private List<Intervencion> intervenciones;
	
	@ManyToMany()
	private List<Clasificacion> clasificaciones;

	public GrupoResolucion() {

	}
	
	public GrupoResolucion(String codigo,String nombre,ArrayList<Usuario> usuarios) {
		
		this.codigo = codigo;
		this.nombre = nombre;
		this.usuarios = usuarios;
		
	}
	
	
	
	
	
	
}
