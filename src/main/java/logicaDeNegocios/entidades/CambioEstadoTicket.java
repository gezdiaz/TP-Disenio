package logicaDeNegocios.entidades;

public class CambioEstadoTicket {
	
	private LocalDateTime fechaHoraCambio; //cambiar nombre en diagrama de clases
	private EstadoTicket estadoAnterior;
	private EstadoTicket estadoNuevo;
	private Ticket ticket;
	private Usuario responsableCambio;
	private String observaciones;
	

	public CambioEstadoTicket() {
		// TODO Auto-generated constructor stub
	}

}
