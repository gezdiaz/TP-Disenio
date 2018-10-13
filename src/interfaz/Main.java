package interfaz;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import interfaz.base.VentanaBase;
import interfaz.paneles.PanelPrueba;
import interfaz.registrarTicket.RegistrarTicketPanel;

public class Main {

	public static void main(String[] args) {
		
		VentanaBase ventana = new VentanaBase("titulo", "Gaston", new JPanel());
//		JPanel p = new PanelPrueba();
//		p.setBackground(new Color(255, 0, 0));
//		UIManager.put("ToolTip.background", Color.WHITE);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		ventana.cambiarPanel(p);
		ventana.cambiarPanel(new RegistrarTicketPanel(ventana));
		ventana.pack();

		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
		
	}

}
