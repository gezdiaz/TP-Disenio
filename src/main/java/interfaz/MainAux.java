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
import dto.TicketDTO;
import interfaz.base.VentanaBase;
import interfaz.paneles.consultarIntervencion.ConsultarIntervencionPanel;
import interfaz.paneles.consultarTicket.ConsultarTicketPanel;
import logicaDeNegocios.entidades.Ticket;

public class MainAux {

	public static void main(String[] args) {
		
		UIManager.put("OptionPane.background", new Color(163,255,140));
		UIManager.put("Panel.background", new Color(163,255,140));
		

		VentanaBase ventana = new VentanaBase("Auxiliar", "Usuario de prueba", new JPanel());
		JPanel panel = new ConsultarTicketPanel(ventana);
		
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistencia");
			GestorBD.setEmf(emf);
			
			List<Ticket> tickets = GestorBD.buscarTickets(1L, null, null, null, null, null,null);
			System.out.println("Tickets: "+tickets);
//			ventana.cambiarPanel(panel);
//			
//			ventana.pack();			
//			ventana.setLocationRelativeTo(null);
//			ventana.setVisible(true);
//			ventana.setFocusable(true);
//			ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//			System.out.println("Panel: "+ panel.getSize());
//			System.out.println("Ventana: "+ ventana.getSize());
//			ventana.setSize(763, 760);
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "No se pudo establecer coneccion con la base de datos\n"+e.getMessage(), "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
				
//		System.out.println("1234".hashCode());
		

	}

}
