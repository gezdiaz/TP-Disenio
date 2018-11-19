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
import interfaz.paneles.consultarTicket.ConsultarTicketPanel;

public class MainAux {

	public static void main(String[] args) {
		
		UIManager.put("OptionPane.background", new Color(163,255,140));
		UIManager.put("Panel.background", new Color(163,255,140));
		
		VentanaBase ventana = new VentanaBase("titulo", "Usuario de prueba", new JPanel());
		JPanel panel = new ConsultarTicketPanel(new VentanaBase("", "", new JPanel()));
		
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistencia");
			GestorBD.setEmf(emf);
			
			ventana.cambiarPanel(panel);
			
			ventana.pack();			
			ventana.setLocationRelativeTo(null);
			ventana.setVisible(true);
			ventana.setFocusable(true);
			ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			System.out.println("Panel: "+ panel.getSize());
			System.out.println("Ventana: "+ ventana.getSize());
//			ventana.setSize(763, 760);
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "No se pudo establecer coneccion con la base de datos\n"+e.getMessage(), "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		

	}

}
