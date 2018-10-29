package logicaDeNegocios.entidades;

public class Empleado {
	
	private String nombre;
	private String apellido;
	private Integer numLegajo;


	public Empleado() {
		
	}	
	
	public Empleado(String nombre, String apellido, Integer numLegajo) {
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

	public Integer getNumLegajo() {
		return numLegajo;
	}

	public void setNumLegajo(Integer numLegajo) {
		this.numLegajo = numLegajo;
	}


}
