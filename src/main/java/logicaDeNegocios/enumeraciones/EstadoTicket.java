package logicaDeNegocios.enumeraciones;

public enum EstadoTicket {
	EN_MESA_DE_AYUDA("En mesa de ayuda"),
	CERRADO("Cerrado"),
	ESPERA_OK("Espera ok"),
	DERIVADO("Derivado");
	
	private String name;
	
	EstadoTicket(String name){
		this.name=name;
	}
	
	
	public String getName() {
		return this.name;
	}
	
}
