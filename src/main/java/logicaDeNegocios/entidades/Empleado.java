package logicaDeNegocios.entidades;

import javax.persistence.*;

@Entity
@Table(name="EMPLEADO")
public class Empleado {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="NUM_LEGAJO")
	private Long numLegajo;
	
	@Column(name="NOMBRE", length=50, nullable=false)
	private String nombre;
	
	@Column(name="APELLIDO", length=50, nullable=false)
	private String apellido;
	

	public Empleado() {
		
	}	
	
	public Empleado(String nombre, String apellido, Long numLegajo) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.numLegajo = numLegajo;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Long getNumLegajo() {
		return numLegajo;
	}

	public void setNumLegajo(Long numLegajo) {
		this.numLegajo = numLegajo;
	}


}
