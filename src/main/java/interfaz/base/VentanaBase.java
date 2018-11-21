package interfaz.base;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class VentanaBase extends JFrame {


	PanelBarraUsuario barraUsuario;
	JPanel subPanel;
	JPanel panel;
	CardLayout cartas;
	//	GridBagConstraints restriccionesPanel;


	public VentanaBase(String title, String nombreUsuario, JPanel subPanel){
		super(title);
		//		this.restriccionesPanel = new GridBagConstraints();
		this.subPanel = subPanel;
		this.cartas = new CardLayout();
		JPanel contentPane = new JPanel(new BorderLayout());
		panel = new JPanel(cartas);
		barraUsuario = new PanelBarraUsuario(nombreUsuario);


		panel.add(this.subPanel, "Inicio");
		cartas.show(panel, "Inicio");
		
		

		contentPane.add(barraUsuario, BorderLayout.NORTH);
		contentPane.add(panel, BorderLayout.CENTER);

		ImageIcon img = new ImageIcon("icono.png");
		setIconImage(img.getImage());

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setContentPane(contentPane);
		this.setLocationRelativeTo(null);

	}

	public void cambiarPanel(JPanel nuevoPanel) {
		this.subPanel = nuevoPanel;
		
		panel.add(nuevoPanel, "nuevoPanel");

		cartas.show(panel, "nuevoPanel");

		this.revalidate();
		this.repaint();
		this.pack();
		this.setLocationRelativeTo(null);
	}
	

}
