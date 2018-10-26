package interfaz.paneles.registrarTicket;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import interfaz.base.VentanaBase;

public class RegistrarTicket2Panel extends JPanel {
	
	JTextArea obserbacionesTxt;
	JButton aceptar, cancelar;
	JComboBox<String> accionList;
	VentanaBase ventana;

	public RegistrarTicket2Panel(VentanaBase ventana) {
		
		GridBagConstraints cons = new GridBagConstraints();
		Insets arIzq = new Insets(25, 25, 5, 5), izq = new Insets(5, 25, 5, 5),
				arDer = new Insets(25, 5, 5, 25), der = new Insets(5, 5, 5, 25),
				abIzq = new Insets(5, 25, 5, 25), abDer = new Insets(5, 5, 25, 25);
		JLabel labelAux;
		
		//pongo un layout GridBagaLayout
		setLayout(new GridBagLayout());
		
		//inicializa las variables globales
		this.ventana = ventana;
		
		obserbacionesTxt = new JTextArea();
		
		aceptar = new JButton("Aceptar");
		
		cancelar = new JButton("Cancelar");
		
		accionList = new JComboBox<>();		
		
		//Agrego todos los componentes al panel
		labelAux = new JLabel("Registrar ticket");
		labelAux.setFont(new Font(labelAux.getFont().getFontName(), labelAux.getFont().getStyle(), 20));
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = arIzq;
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		labelAux = new JLabel("Observaciones*");
		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = izq;
		cons.anchor = GridBagConstraints.WEST;
		
		
	}

}
