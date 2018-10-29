package logicaDeNegocios.entidades;

public class Empleado {
	
	private String nombre;
	private String apellido;
	private Integer numLegajo;

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

	public Integer getNumLegajo() {
		return numLegajo;
	}

	public void setNumLegajo(Integer numLegajo) {
		this.numLegajo = numLegajo;
	}

	public Empleado() {
		// TODO Auto-generated constructor stub
	}

}
