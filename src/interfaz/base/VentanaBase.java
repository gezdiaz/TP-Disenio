package interfaz.base;

import java.awt.Color;
import java.awt.Container;
import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import javafx.scene.layout.Border;

public class VentanaBase extends JFrame {

	
	PanelBarraUsuario barraUsuario;
	JPanel subPanel;
	JPanel panel;
	GridBagConstraints restriccionesPanel;
	

	public VentanaBase(String title, String nombreUsuario, JPanel subPanel){
		super(title);
		GridBagConstraints cons = new GridBagConstraints();
		this.restriccionesPanel = new GridBagConstraints();
		this.subPanel = subPanel;
		panel = new JPanel();
		barraUsuario = new PanelBarraUsuario(nombreUsuario);
		panel.setLayout(new GridBagLayout());
		
		
		cons.gridx=0;
		cons.gridy=0;	
		cons.fill=GridBagConstraints.HORIZONTAL;
		barraUsuario.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(barraUsuario, cons);
		
		this.restriccionesPanel.gridx=0;
		this.restriccionesPanel.gridy=1;
		this.restriccionesPanel.fill=GridBagConstraints.BOTH;
		this.restriccionesPanel.weighty = 2;
		this.restriccionesPanel.weightx = 2;
//		otroPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		this.subPanel.setBackground(new Color(163,255,140));
		panel.add(this.subPanel, restriccionesPanel);
		
		this.setContentPane(panel);
		
	}

	public void cambiarPanel(JPanel nuevoPanel) {
		this.subPanel = nuevoPanel;
		panel.add(this.subPanel, restriccionesPanel);
		panel.repaint();
		this.repaint();
	}

	
	
	
	
	
	
}
