package interfaz.paneles.consultarTicket;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;

import javax.swing.table.AbstractTableModel;

import dto.TicketDTO;

public class TablaTicketsModelo extends AbstractTableModel {

	private List<TicketDTO> tickets;
	private String[] columnas = {"Numero de ticket", "Numero de legajo", "Fecha de apertura", "Hora de apertura","Operador",
								 "Clasificacion actual", "Estado actual", "Fecha último estado", "Grupo actual"};
	
	
	
	
	public List<TicketDTO> getTickets() {
		return tickets;
	}

	public void setTickets(List<TicketDTO> tickets) {
		this.tickets = tickets;
	}
	
	@Override
	public String getColumnName(int indice) {
		return this.columnas[indice];
	}

	@Override
	public int getColumnCount() {
		return columnas.length;
	}

	@Override
	public int getRowCount() {
		return tickets.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object valor = null;
		
		switch (columnIndex) {
		case 0:
			valor = tickets.get(rowIndex).getNumTicket();
			break;
		case 1:
			valor = tickets.get(rowIndex).getNumLegajo();
			break;
		case 2:
			valor = tickets.get(rowIndex).getFechaHoraApertura().format(DateTimeFormatter.ofPattern("dd/MM/uuuu", Locale.getDefault()));
			break;
		case 3:
			valor = tickets.get(rowIndex).getFechaHoraApertura().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
			break;
		case 4:
//			valor = tickets.get(rowIndex).getNumTicket();
			valor = "Operador??";
			break;
		case 5:
			valor = tickets.get(rowIndex).getClasificacion();
			break;
		case 6:
			valor = tickets.get(rowIndex).getEstado().name();
			break;
		case 7:
			valor = tickets.get(rowIndex).getFechaUltimoCambioEstado().format(DateTimeFormatter.ofPattern("dd/MM/uuuu", Locale.getDefault()));
			break;
		case 8:
			valor = tickets.get(rowIndex).getGrupoActual();
			break;
		default:
			System.out.println("Indice fuera de rango");
			valor = "S/D";
			break;
		}
		
		return valor;
	}

}
