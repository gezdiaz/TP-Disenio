package interfaz.paneles.cerrarTicket;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;

import dto.TicketDTO;
import interfaz.auxiliar.LimiteTexto;
import interfaz.auxiliar.PanelCancelable;
import interfaz.base.VentanaBase;
import interfaz.paneles.consultarTicket.ConsultarTicketPanel;
import interfaz.principal.MenuMesaAyudaPanel;
import logicaDeNegocios.enumeraciones.EstadoTicket;
import logicaDeNegocios.gestores.GestorTickets;

public class CerrarTicketPanel extends PanelCancelable{

	private VentanaBase ventanaActual, ventanaAnterior;
	private JTextArea txtObservaciones;
	private JButton btnAceptar, btnCancelar;
	private TicketDTO ticketDTO;
	private ConsultarTicketPanel consultarTicketPanel;
	private JLabel caracteresRestantes;
	
	public CerrarTicketPanel(VentanaBase ventanaActual, TicketDTO ticketDTO, VentanaBase ventanaAnterior, ConsultarTicketPanel consultarTicketPanel) {
		this.setLayout(new GridBagLayout());
		JLabel labelAux;
		JScrollPane scroll;

		GridBagConstraints cons = new GridBagConstraints();
		this.ventanaActual = ventanaActual;
		this.ventanaAnterior = ventanaAnterior;
		this.consultarTicketPanel = consultarTicketPanel;
		this.ticketDTO = ticketDTO;
		
		caracteresRestantes = new JLabel("Caracteres restantes: 250");

		txtObservaciones = new JTextArea();
		txtObservaciones.setLineWrap(true);
		txtObservaciones.setWrapStyleWord(true);
		AbstractDocument doc = (AbstractDocument) txtObservaciones.getDocument();
		doc.setDocumentFilter(new LimiteTexto(250));
		doc.addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				caracteresRestantes.setText("Caracteres restantes: "+(250-e.getDocument().getLength()));
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				caracteresRestantes.setText("Caracteres restantes: "+(250-e.getDocument().getLength()));
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				caracteresRestantes.setText("Caracteres restantes: "+(250-e.getDocument().getLength()));
			}
		});


		btnAceptar = new JButton("Aceptar");

		btnCancelar = new JButton("Cancelar");

		this.ventanaActual.setVisible(true);

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
		labelAux = new JLabel("N° Ticket: "+ticketDTO.getNumTicket());
		cons.gridx = 1;
		cons.gridy = 0;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 1;
		cons.insets = new Insets(30, 30, 15, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);

		//Muestra el Nuevo estado del ticket
		labelAux = new JLabel("Nuevo estado de ticket: "+EstadoTicket.CERRADO.getName());
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

		caracteresRestantes.setFont(new Font(caracteresRestantes.getFont().getFontName(), caracteresRestantes.getFont().getStyle(), 10));
		cons.gridx = 0;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(10, 5, 5, 5);
		cons.anchor = GridBagConstraints.SOUTH;
		add(caracteresRestantes, cons);
		
		//Campo Observaciones
		scroll = new JScrollPane(txtObservaciones);
		scroll.setPreferredSize(new Dimension(200, 70));
		txtObservaciones.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
		txtObservaciones.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
		cons.gridx = 1;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 2;
		cons.insets = new Insets(25, 5, 5, 25);
		cons.fill = GridBagConstraints.NONE;
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
		cons.gridx = 0;
		cons.gridy = 4;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 1;
		cons.insets = new Insets(20, 40, 40, 10);
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.WEST;
		btnAceptar.addActionListener(a -> {
			apretoAceptar(txtObservaciones.getText());		
		});
		btnAceptar.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {				
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {

				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					apretoAceptar(txtObservaciones.getText());
				}
			}
		});
		add(btnAceptar, cons);


		cons.gridx = 1;
		cons.gridy = 4;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 1;
		cons.insets = new Insets(20, 10, 40, 25);
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.EAST;
		btnCancelar.addActionListener(a -> {
			apretoCancelar(false);
		});
		btnCancelar.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {				
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {

				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					apretoCancelar(false);
				}
			}
		});
		add(btnCancelar, cons);
	}

	@Override
	public void apretoCancelar(Boolean desdeVentana) {
		int res = JOptionPane.YES_NO_OPTION;
		
		if(!desdeVentana) {
			res = JOptionPane.showConfirmDialog(ventanaActual, "¿Está seguro que desea cancelar la operación?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		}

		if(res == JOptionPane.YES_OPTION) {
			ventanaActual.dispose();
			ventanaAnterior.setVisible(true);
		}
	}

	private void apretoAceptar(String observaciones) {
		
		if(txtObservaciones.getText().trim().isEmpty()) {
			JOptionPane.showConfirmDialog(ventanaActual, "Debe ingresar observaciones.", "¡Error!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
		}
		else {
			int res = JOptionPane.showConfirmDialog(ventanaActual, "¿Está seguro que desea cerrar el ticket?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if(res == JOptionPane.YES_OPTION) {
				switch(GestorTickets.cerrarTicket(ticketDTO, observaciones)) {
				case -2:{
					JOptionPane.showConfirmDialog(ventanaActual, "No se ha podido actualizar el ticket en la base de datos.", "¡Error!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					break;
				}
				case -1:{
					JOptionPane.showConfirmDialog(ventanaActual, "Error conectándose a la base de datos.", "¡Error!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					break;
				}
				case 0:{
					JOptionPane.showConfirmDialog(ventanaActual, "Ticket no encontrado en la base de datos.", "¡Error!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					break;
				}
				case 1:{JOptionPane.showConfirmDialog(ventanaActual, "El ticket ha sido cerrado exitosamente.", "¡Exito!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
				ventanaActual.dispose();
				ventanaAnterior.setVisible(true);
				consultarTicketPanel.buscar();
				break;
				}
				default:{}
				}	
			}
		}
	}

}
