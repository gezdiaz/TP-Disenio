package interfaz;

import java.awt.Color;

import javax.swing.JPanel;

import interfaz.base.VentanaBase;
import interfaz.paneles.PanelPrueba;

public class Main {

	public static void main(String[] args) {
		
		VentanaBase ventana = new VentanaBase("titulo", "Nombre Usuario", new JPanel());
		JPanel p = new PanelPrueba();
		p.setBackground(new Color(255, 0, 0));
		ventana.cambiarPanel(p);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
		ventana.pack();
	}

}
