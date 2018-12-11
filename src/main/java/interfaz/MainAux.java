package interfaz;

import java.awt.Color;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import accesoADatos.GestorBD;
import dto.TicketDTO;
import interfaz.base.VentanaBase;
import interfaz.paneles.consultarTicket.VerTicketPanel;

public class MainAux {

	public static void main(String[] args) {
		UIManager.put("OptionPane.background", new Color(163,255,140));
		UIManager.put("Panel.background", new Color(163,255,140));
		UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);

		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistencia");
			GestorBD.setEmf(emf);

			TicketDTO ticketDTO = new TicketDTO(1234L);
			ticketDTO.setNumLegajo(23809L);
			VentanaBase ventana = new VentanaBase("Título", "Usuario", new VerTicketPanel(ticketDTO));
			
			ventana.pack();
			ventana.setLocationRelativeTo(null);
			ventana.setVisible(true);
			
			
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "No se pudo establecer conección con la base de datos.\n"+e.getMessage(), "¡Error!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(0);
		}
	}

}
