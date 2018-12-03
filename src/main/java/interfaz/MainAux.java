package interfaz;

import java.awt.Color;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import accesoADatos.GestorBD;
import interfaz.base.VentanaBase;
import interfaz.paneles.consultarIntervencion.ConsultarIntervencionPanel;
import interfaz.paneles.consultarTicket.ConsultarTicketPanel;
import logicaDeNegocios.entidades.Intervencion;
import logicaDeNegocios.enumeraciones.EstadoIntervencion;

public class MainAux {

	public static void main(String[] args) {
		
		List<Intervencion> intervencion = GestorBD.buscarintervenciones(EstadoIntervencion.Asignado, null, null, null, null);
		System.out.println(intervencion.get(0).getIdInt());
		//soy otro Easter Egg
		

	}

}
