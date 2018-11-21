package interfaz;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import accesoADatos.GestorBD;
import interfaz.principal.InicioSesionPanel;

public class Main {

	public static void main(String[] args) {

		UIManager.put("OptionPane.background", new Color(163,255,140));
		UIManager.put("Panel.background", new Color(163,255,140));

		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistencia");
			GestorBD.setEmf(emf);
			iniciarSesion();
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "No se pudo establecer coneccion con la base de datos\n"+e.getMessage(), "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}

	public static void iniciarSesion() {
		ImageIcon img = new ImageIcon("icono.png");
		JFrame ventana = new JFrame("Sistema de Mesa de ayuda");
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setContentPane(new InicioSesionPanel(ventana));
		ventana.setIconImage(img.getImage());		 		
		ventana.pack();
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}

}
