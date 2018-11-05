package interfaz;

import java.awt.Color;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

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
		 ImageIcon img = new ImageIcon("icono.png");
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
		
		 JFrame ventana = new JFrame("Sistema de Mesa de ayuda");
		 ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		 ventana.setContentPane(new InicioSesionPanel(ventana));
		 ventana.setIconImage(img.getImage());		 		 
		 ventana.pack();
		 ventana.setLocationRelativeTo(null);
		 ventana.setVisible(true);
		 
		 
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistencia");
		 EntityManager manager = emf.createEntityManager();
		 GrupoResolucion grupo = new GrupoResolucion("A113", "un grupo", new ArrayList<Usuario>());
		 Usuario usr = new Usuario("usr", "1234", grupo);
		 manager.getTransaction().begin();
		 manager.persist(grupo);
		 manager.persist(usr);
		 manager.getTransaction().commit();
		 
	}

}
