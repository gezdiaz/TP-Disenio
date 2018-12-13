package logicaDeNegocios.enumeraciones;

public enum EstadoTicket {
	CERRADO("Cerrado"),
	DERIVADO("Derivado"),
	EN_MESA_DE_AYUDA("En mesa de ayuda"),
	ESPERA_OK("Espera ok");
	
	
	private String name;
	
	EstadoTicket(String name){
		this.name=name;
	}
	
	
	public String getName() {
		return this.name;
	}
	
}
