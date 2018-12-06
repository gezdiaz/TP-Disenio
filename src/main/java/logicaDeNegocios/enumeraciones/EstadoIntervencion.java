package logicaDeNegocios.enumeraciones;

public enum EstadoIntervencion {
	TERMINADO("Terminado"),
	INCORRECTO("Incorrecto"),
	PARCIALMENTE_TERMINADO("Parcialmente terminado"),
	EN_ESPERA("En espera"),
	TRABAJANDO("Trabajando"), 
	ASIGNADO("Asignado");
	
	private String name;
	
	EstadoIntervencion(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
