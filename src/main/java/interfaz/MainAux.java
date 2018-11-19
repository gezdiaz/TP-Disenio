package interfaz;

import java.awt.Color;

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

public class MainAux {

	public static void main(String[] args) {
		
		UIManager.put("OptionPane.background", new Color(163,255,140));
		UIManager.put("Panel.background", new Color(163,255,140));
		
		VentanaBase ventana = new VentanaBase("Auxiliar", "Usuario de prueba", new JPanel());
		JPanel panel = new ConsultarIntervencionPanel(ventana);
		
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistencia");
			GestorBD.setEmf(emf);
			
			ventana.cambiarPanel(panel);
			
			ventana.setLocationRelativeTo(null);
			ventana.pack();
			ventana.setVisible(true);
			ventana.setFocusable(true);
			ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "No se pudo establecer coneccion con la base de datos\n"+e.getMessage(), "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		

	}

}
