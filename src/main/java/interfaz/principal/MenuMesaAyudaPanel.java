package interfaz.principal;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interfaz.Main;
import interfaz.base.VentanaBase;
import interfaz.paneles.consultarTicket.ConsultarTicketPanel;
import interfaz.paneles.registrarTicket.RegistrarTicketPanel;
import logicaDeNegocios.gestores.GestorUsuarios;

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
		cons.fill = GridBagConstraints.HORIZONTAL;
		btnRegistrarTicket.addActionListener(a -> {
			registrarTicket();
		});
		btnRegistrarTicket.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			@Override
			public void keyPressed(KeyEvent e) {

				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					registrarTicket();
				}
			}
		});
		add(btnRegistrarTicket, cons);

		cons.gridx = 1;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.anchor = GridBagConstraints.NORTH;
		cons.insets = new Insets(20, 5, 10, 30);
		cons.fill = GridBagConstraints.NONE;
		btnConsultarTicket.addActionListener(a -> {
			consultarTicket();
		});
		btnConsultarTicket.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			@Override
			public void keyPressed(KeyEvent e) {

				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					consultarTicket();
				}
			}
		});
		add(btnConsultarTicket, cons);

		cons.gridx = 0;
		cons.gridy = 3;
		cons.gridheight = 1;
		cons.gridwidth = 2;
		cons.anchor = GridBagConstraints.CENTER;
		cons.insets = new Insets(25, 5, 10, 30);
		btnCerrarSesion.addActionListener(a -> {
			cerrarSesion();
		});
		btnCerrarSesion.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			@Override
			public void keyPressed(KeyEvent e) {

				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					cerrarSesion();
				}
			}
		});
		add(btnCerrarSesion, cons);


	}

	private void cerrarSesion() {
		int res = JOptionPane.showConfirmDialog(ventana, "¿Está seguro que desea cerrar sesión?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		if(res == JOptionPane.YES_OPTION) {
			GestorUsuarios.cerrarSesion();
			ventana.dispose();
			Main.iniciarSesion();
		}
	}

	private void consultarTicket() {
		ventana.cambiarPanel(new ConsultarTicketPanel(ventana));// crear el panel Consultar ticket
	}

	
	private void registrarTicket() {
		ventana.cambiarPanel(new RegistrarTicketPanel(ventana));
	}

}
