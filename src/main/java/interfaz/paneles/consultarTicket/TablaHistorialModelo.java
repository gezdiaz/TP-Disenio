package interfaz.paneles.consultarTicket;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;

import javax.swing.table.AbstractTableModel;

import dto.HistorialTicketDTO;

public class TablaHistorialModelo extends AbstractTableModel {

	private List<HistorialTicketDTO> historial;
	private String[] columnas = {"Fecha", "Hora", "Operador", "Estado", "Grupo de resolución", "Clasificación"};
	
	public TablaHistorialModelo(List<HistorialTicketDTO> historialDTO) {
		super();
		this.setHistorial(historialDTO);
	}

	public List<HistorialTicketDTO> getHistorial() {
		return historial;
	}

	public void setHistorial(List<HistorialTicketDTO> historial) {
		this.historial = historial;
	}
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columnas[column];
	}

	@Override
	public int getColumnCount() {
		return columnas.length;
	}

	@Override
	public int getRowCount() {
		return historial.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object valor = null;
		switch (columnIndex) {
		case 0:
			valor = historial.get(rowIndex).getFechaHoraCambioEstado().format(DateTimeFormatter.ofPattern("dd/MM/uuuu", Locale.getDefault()));
			break;
		case 1:
			valor = historial.get(rowIndex).getFechaHoraCambioEstado().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
			break;
		case 2:
			valor = historial.get(rowIndex).getOperador();
			break;
		case 3:
			valor = historial.get(rowIndex).getNuevoEstado().getName();
			break;
		case 4:
			valor = historial.get(rowIndex).getGrupoResolucion();
			break;
		case 5:
			valor = historial.get(rowIndex).getClasificacion();
			break;
		default:
			System.out.println("Indice fuera de rango");
			valor = "S/D";
			break;
		}
		
		return valor;
	}

}
