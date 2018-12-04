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

	@Override
	public String toString() {
		return "Empleado [numLegajo=" + numLegajo + ", nombre=" + nombre + ", apellido=" + apellido + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellido == null) ? 0 : apellido.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((numLegajo == null) ? 0 : numLegajo.hashCode());
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
		Empleado other = (Empleado) obj;
		if (apellido == null) {
			if (other.apellido != null)
				return false;
		} else if (!apellido.equals(other.apellido))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (numLegajo == null) {
			if (other.numLegajo != null)
				return false;
		} else if (!numLegajo.equals(other.numLegajo))
			return false;
		return true;
	}

}
