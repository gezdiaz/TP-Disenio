package logicaDeNegocios.enumeraciones;

public enum Motivos{	
	INTERVENCION_INCORRECTA("Intervencion Incorrecta"),
	PARCIALMENTE_TERMINADA("Parcialmente Terminada"),
	TRABAJO_TERMINADO("Trabajo Terminado");
	
	private String name;
	
	Motivos(String name){
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

}
