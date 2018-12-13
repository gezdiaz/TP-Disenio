package logicaDeNegocios.enumeraciones;

public enum EstadoIntervencion {
	ASIGNADO("Asignado"),
	EN_ESPERA("En espera"),
	INCORRECTO("Incorrecto"),
	PARCIALMENTE_TERMINADO("Parcialmente terminado"),
	TERMINADO("Terminado"),
	TRABAJANDO("Trabajando");
	
	
	private String name;
	
	EstadoIntervencion(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
