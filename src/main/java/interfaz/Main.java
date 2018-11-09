package interfaz;

import java.awt.Color;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import accesoADatos.GestorBD;
import interfaz.base.VentanaBase;
import interfaz.paneles.registrarTicket.RegistrarTicket2Panel;
import interfaz.paneles.registrarTicket.RegistrarTicketPanel;
import interfaz.principal.InicioSesionPanel;
import interfaz.principal.MenuMesaAyudaPanel;
import logicaDeNegocios.entidades.GrupoResolucion;
import logicaDeNegocios.entidades.Usuario;

public class Main {

	public static void main(String[] args) {

		//		VentanaBase ventana = new VentanaBase("titulo", "Gaston", new JPanel());

		//		Esto es para que muestre los mensajes de error con el color correcto
		UIManager.put("OptionPane.background", new Color(163,255,140));
		UIManager.put("Panel.background", new Color(163,255,140));
		//		JPanel p = new PanelPrueba();
		//		p.setBackground(new Color(255, 0, 0));
		//		UIManager.put("ToolTip.background", Color.WHITE);
		//		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//		ventana.cambiarPanel(p);
		//		ventana.cambiarPanel(new MenuMesaAyuda(ventana));
		//		ventana.pack();
		//
		//		ventana.setLocationRelativeTo(null);
		//		ventana.setVisible(true);

		//		 try {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistencia");
		GestorBD.setEmf(emf);
		EntityManager manager = emf.createEntityManager();
		GrupoResolucion grupo = new GrupoResolucion("A113", "un grupo");
		Usuario usr = new Usuario("usr", "1234", grupo), u2;
		manager.getTransaction().begin();
		manager.persist(grupo);
		manager.persist(usr);
		manager.getTransaction().commit();
		manager.getTransaction().begin();
		u2 = manager.find(Usuario.class, "usr");
		manager.getTransaction().commit();
		System.out.println(u2.toString());
		manager.close();
		//			} catch (Exception e) {
		//				JOptionPane.showConfirmDialog(null, "No se pudo establecer coneccion con la base de datos\n"+e.getMessage(), "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
		//			}

		iniciarSesion();

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
