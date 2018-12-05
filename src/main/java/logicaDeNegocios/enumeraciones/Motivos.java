package logicaDeNegocios.enumeraciones;

public enum Motivos {
	
	
	
	Parcialmente_Terminada("Parcialmente Terminada"),
	Trabajo_Terminado("Trabajo Terminado"), 
	Intervencion_Incorrecta("Intervencion Incorrecta");
	
	private String name;
	
	Motivos(String name){
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

}
