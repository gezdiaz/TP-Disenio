package interfaz.paneles.consultarIntervencion;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;

import javax.swing.table.AbstractTableModel;

import dto.IntervencionDTO;
import dto.TicketDTO;

public class TablaIntervencionesModelo extends AbstractTableModel{
	
	private List<IntervencionDTO> intervenciones;
	private String[] columnas = {"Numero de ticket", "Numero de legajo", "Clasificacion actual", "Estado actual","Fecha apertura",
								 "Estado intervencion", "Fecha de asignacion de intervencion", "Grupo resolucion"};
	
	
	
	public List<IntervencionDTO> getIntervenciones() {
		return intervenciones;
	}

	public void setIntervenciones(List<IntervencionDTO> intervenciones) {
		this.intervenciones = intervenciones;
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
//		return 6;
		return intervenciones.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object valor = null;
		
		switch (columnIndex) {
		case 0:
			valor = intervenciones.get(rowIndex).getNumTicket();
			break;
		case 1:
			valor = intervenciones.get(rowIndex).getNumLegajo();
			break;
		case 2:
			valor = intervenciones.get(rowIndex).getClasificacion();
			break;
		case 3:
			valor = intervenciones.get(rowIndex).getEstadoTicket().getName();
			break;
		case 4:
			valor = intervenciones.get(rowIndex).getFechaApertura().format(DateTimeFormatter.ofPattern("dd/MM/uuuu", Locale.getDefault()));
			break;
		case 5:
			valor = intervenciones.get(rowIndex).getEstadoIntervencion().getName();
			break;
		case 6:
			valor = intervenciones.get(rowIndex).getFechaAsignacionIntervencion().format(DateTimeFormatter.ofPattern("dd/MM/uuuu", Locale.getDefault()));
			break;
		case 7:
			valor = intervenciones.get(rowIndex).getGrupoResolucion();
			break;
		default:
			System.out.println("Indice fuera de rango");
			valor = "S/D";
			break;
		}
		
		return valor;
	}

}
