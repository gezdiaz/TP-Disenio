package interfaz.principal;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interfaz.base.VentanaBase;
import interfaz.paneles.registrarTicket.RegistrarTicketPanel;

public class MenuMesaAyudaPanel extends JPanel{
	
	VentanaBase ventana;
	JButton btnRegistrarTicket, btnConsultarTicket, btnCerrarSesion;
	
	public MenuMesaAyudaPanel(VentanaBase ventana) {
		this();
		setVentana(ventana);
	}
	
	public void setVentana(VentanaBase ventana) {
		this.ventana = ventana;
	}

	public MenuMesaAyudaPanel() {
		
		GridBagConstraints cons = new GridBagConstraints();
		setLayout(new GridBagLayout());
		JLabel labelAux;
		
		this.btnRegistrarTicket = new JButton("Registrar Ticket");
		
		this.btnConsultarTicket = new JButton("Consultar Tickets");
		
		this.btnCerrarSesion = new JButton("Cerrar Sesión");
		
		labelAux = new JLabel("Mesa de ayuda");
		labelAux.setFont(new Font(labelAux.getFont().getFontName(), labelAux.getFont().getStyle(), 20));
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.anchor = GridBagConstraints.WEST;
		cons.insets = new Insets(15, 20, 15, 30);
		add(labelAux, cons);
		
		labelAux = new JLabel(new ImageIcon("icono.png"));
		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridheight = 2;
		cons.gridwidth = 1;
		cons.anchor = GridBagConstraints.CENTER;
		cons.insets = new Insets(5, 50, 5, 5);
		add(labelAux, cons);
		
		cons.gridx = 1;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.anchor = GridBagConstraints.SOUTH;
		cons.insets = new Insets(50, 5, 20, 30);
		btnRegistrarTicket.addActionListener(a -> {
			ventana.cambiarPanel(new RegistrarTicketPanel(ventana));
		});
		add(btnRegistrarTicket, cons);
		
		cons.gridx = 1;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.anchor = GridBagConstraints.NORTH;
		cons.insets = new Insets(20, 5, 10, 30);
		btnConsultarTicket.addActionListener(a -> {
			ventana.cambiarPanel(new JPanel());//TODO crear el panel Consultar ticket
		});
		add(btnConsultarTicket, cons);
		
		cons.gridx = 0;
		cons.gridy = 3;
		cons.gridheight = 1;
		cons.gridwidth = 2;
		cons.anchor = GridBagConstraints.CENTER;
		cons.insets = new Insets(25, 5, 10, 30);
		btnCerrarSesion.addActionListener(a -> {
			System.out.println("Cierra sesión");
			ventana.dispose();
		});
		add(btnCerrarSesion, cons);
		
		
	}

}
