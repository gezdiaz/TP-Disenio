package logicaDeNegocios.enumeraciones;

public enum Motivos {
	
	
	
	PARCIALMENTE_TERMINADA("Parcialmente Terminada"),
	TRABAJO_TERMINADO("Trabajo Terminado"), 
	INTERVENCION_INCORRECTA("Intervencion Incorrecta");
	
	private String name;
	
	Motivos(String name){
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

}
