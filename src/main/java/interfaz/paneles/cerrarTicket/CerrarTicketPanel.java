package interfaz.paneles.cerrarTicket;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dto.TicketDTO;
import interfaz.base.VentanaBase;

public class CerrarTicketPanel extends JPanel{

	private VentanaBase ventana;
	private JTextArea txtObservaciones;
	private JButton btnAceptar, btnCancelar;
	private TicketDTO ticketDTO;
	
	public CerrarTicketPanel(VentanaBase ventana, TicketDTO ticketDTO, VentanaBase ventanaAnterior) {
		this.setLayout(new GridBagLayout());
		JLabel labelAux;
		JScrollPane scroll;
		
		GridBagConstraints cons = new GridBagConstraints();
		this.ventana = ventana;

		this.ticketDTO = ticketDTO;

		txtObservaciones = new JTextArea();

		btnAceptar = new JButton("Aceptar");

		btnCancelar = new JButton("Cancelar");
		
		this.ventana.setVisible(true);
		
		//Nombre de la pantalla: Cerrar Ticket
		labelAux = new JLabel("Cerrar Ticket");
		labelAux.setFont(new Font(labelAux.getFont().getFontName(), labelAux.getFont().getStyle(), 20));
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(30, 60, 15, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		//Muestra el Numero de ticket
		labelAux = new JLabel("N° Ticket: "+/*"123456"*/ticketDTO.getNumTicket());
		cons.gridx = 1;
		cons.gridy = 0;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 1;
		cons.insets = new Insets(30, 30, 15, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		//Muestra el Nuevo estado del ticket
		labelAux = new JLabel("Nuevo estado de ticket: CERRADO"/*ticketDTO.getNumTicket()*/);
		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 2;
		cons.weightx = 1;
		cons.insets = new Insets(10, 50, 10, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		//Label Observaciones
		labelAux = new JLabel("Observaciones*");
		cons.gridx = 0;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(25, 50, 10, 5);
		cons.anchor = GridBagConstraints.NORTHWEST;
		add(labelAux, cons);
		
		//Campo Observaciones
		scroll = new JScrollPane(txtObservaciones);
		txtObservaciones.setPreferredSize(new Dimension(200, 70));
		cons.gridx = 1;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 2;
		cons.insets = new Insets(25, 5, 5, 25);
		cons.fill = GridBagConstraints.BOTH;
		cons.anchor = GridBagConstraints.CENTER;
		add(scroll, cons);
		
		//*campo obligatorio
		labelAux = new JLabel("*Campo obligatorio");
		labelAux.setFont(new Font(labelAux.getFont().getFontName(), labelAux.getFont().getStyle(), 8));
		cons.gridx = 0;
		cons.gridy = 3;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 1;
		cons.insets = new Insets(5, 60, 5, 5);
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		//Botones
		btnAceptar.addActionListener(a -> {
			//apretoAceptar();//TODO apretoAceptar()
			if(txtObservaciones.getText().trim().isEmpty()) {
				JOptionPane.showConfirmDialog(ventana, "Debe ingresar observaciones", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
			}
			else {
				ventana.dispose();
				ventanaAnterior.setVisible(true);
			}		
		});
		cons.gridx = 0;
		cons.gridy = 4;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 1;
		cons.insets = new Insets(20, 40, 40, 10);
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.EAST;
		add(btnAceptar, cons);

		btnCancelar.addActionListener(a -> {
			//apretoCancelar();//TODO apretoCancelar()
			ventana.dispose();
			ventanaAnterior.setVisible(true);
		});
		cons.gridx = 1;
		cons.gridy = 4;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 1;
		cons.insets = new Insets(20, 10, 40, 25);
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.WEST;
		add(btnCancelar, cons);
	}
	
}
