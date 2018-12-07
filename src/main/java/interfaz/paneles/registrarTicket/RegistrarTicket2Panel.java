package interfaz.paneles.registrarTicket;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import accesoADatos.GestorBD;
import dto.TicketDTO;
import interfaz.base.VentanaBase;
import interfaz.principal.MenuMesaAyudaPanel;
import logicaDeNegocios.gestores.GestorTickets;

public class RegistrarTicket2Panel extends JPanel {

	JTextArea obserbacionesTxt;
	JButton aceptar, cancelar;
	JComboBox<String> accionList;
	VentanaBase ventana;
	TicketDTO ticketDTO;
	private List<String> listaGR;

	public RegistrarTicket2Panel(VentanaBase ventana, TicketDTO ticketDTO) {
		
		GridBagConstraints cons = new GridBagConstraints();
		Insets arIzq = new Insets(25, 25, 5, 5), izq = new Insets(10, 50, 10, 5),
				arDer = new Insets(25, 5, 5, 25), der = new Insets(10, 5, 10, 25);
		JLabel labelAux;
		JScrollPane scroll;

		//pongo un layout GridBagaLayout
		setLayout(new GridBagLayout());

		//inicializa las variables globales
		this.ventana = ventana;

		this.ticketDTO = ticketDTO;

		obserbacionesTxt = new JTextArea();
		obserbacionesTxt.setLineWrap(true);
		obserbacionesTxt.setWrapStyleWord(true);

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
		cons.insets = new Insets(15, 25, 25, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);

		labelAux = new JLabel("Observaciones*");
		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = izq;
		cons.anchor = GridBagConstraints.NORTHWEST;
		add(labelAux, cons);

		labelAux = new JLabel("Acción");
		cons.gridx = 0;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = izq;
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);

		//Muestra el numero de ticket
		labelAux = new JLabel("N° Ticket: "+ticketDTO.getNumTicket());
		cons.gridx = 1;
		cons.gridy = 0;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 1;
		cons.insets = new Insets(15, 25, 25, 5);
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.CENTER;
		add(labelAux, cons);

		//Campo observaciones con su barra de scroll
		scroll = new JScrollPane(obserbacionesTxt);
		obserbacionesTxt.setPreferredSize(new Dimension(250, 70));
		cons.gridx = 1;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 2;
		cons.insets = arDer;
		cons.fill = GridBagConstraints.BOTH;
		cons.anchor = GridBagConstraints.CENTER;
		obserbacionesTxt.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
		obserbacionesTxt.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
		add(scroll, cons);

		listaGR = GestorBD.getListGruposConClasificacion(ticketDTO.getClasificacion());
		accionList.addItem("Cerrar ticket");
		for(String g: listaGR) {
			accionList.addItem("Derivar a: "+g);
		}
		accionList.setSelectedItem("Cerrar ticket");
		cons.gridx = 1;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 1;
		cons.insets = der;
		cons.fill = GridBagConstraints.BOTH;
		cons.anchor = GridBagConstraints.CENTER;
		add(accionList, cons);

		//botones
		aceptar.addActionListener(a -> {
			apretoAceptar();
		});
		cons.gridx = 0;
		cons.gridy = 4;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 1;
		cons.insets = new Insets(20, 40, 40, 10);
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.WEST;
		aceptar.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			@Override
			public void keyPressed(KeyEvent e) {

				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					apretoAceptar();
				}
			}
		});
		add(aceptar, cons);

		cancelar.addActionListener(a -> {
			apretoCancelar();
		});
		cons.gridx = 1;
		cons.gridy = 4;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 1;
		cons.insets = new Insets(20, 10, 40, 25);
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.EAST;
		cancelar.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			@Override
			public void keyPressed(KeyEvent e) {

				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					apretoCancelar();
				}
			}
		});
		add(cancelar, cons);

		//*campo obligatorio
		labelAux = new JLabel("*Campo obligatorio");
		labelAux.setFont(new Font(labelAux.getFont().getFontName(), labelAux.getFont().getStyle(), 8));
		cons.gridx = 0;
		cons.gridy = 3;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 1;
		cons.insets = new Insets(5, 40, 5, 5);
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);

	}
	private void apretoCancelar() {
		
		int res = JOptionPane.showConfirmDialog(ventana, "¿Está seguro que desea cancelar la operación? Se eliminará el ticket creado.", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if(res == JOptionPane.YES_OPTION) {
			GestorTickets.eliminarTicket(ticketDTO);
			ventana.cambiarPanel(new MenuMesaAyudaPanel(ventana));
		}
		
	}
	private void apretoAceptar() {
		if(obserbacionesTxt.getText().trim().isEmpty()) {
			JOptionPane.showConfirmDialog(ventana, "Debe ingresar observaciones.", "¡Error!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
		}else {
			
			if(accionList.getSelectedItem()=="Cerrar ticket") {
				switch(GestorTickets.cerrarTicketRT(this.ticketDTO, obserbacionesTxt.getText())) {
				case -2:{
					JOptionPane.showConfirmDialog(ventana, "No se ha podido registrar el ticket en la base de datos.", "¡Error!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					break;
				}
				case -1:{
					JOptionPane.showConfirmDialog(ventana, "Error conectándose a la base de datos.", "¡Error!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					break;
				}
				case 0:{
					JOptionPane.showConfirmDialog(ventana, "Ticket no encontrado en la base de datos.", "¡Error!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					break;
				}
				case 1:{JOptionPane.showConfirmDialog(ventana, "El ticket ha sido cerrado exitosamente.", "¡Exito!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
				ventana.cambiarPanel(new MenuMesaAyudaPanel(ventana));
				break;
				}
				default:{}
				}
				
			}else {
				String nombreGrupo = listaGR.get(accionList.getSelectedIndex()-1);
				switch(GestorTickets.derivarTicketRT(ticketDTO, nombreGrupo, obserbacionesTxt.getText().trim())) {
				case -2:{
					JOptionPane.showConfirmDialog(ventana, "No se ha podido registrar el ticket en la base de datos.", "¡Error!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					break;
				}
				case 0:{
					JOptionPane.showConfirmDialog(ventana, "Ticket o grupo de resolución no encontrado en la base de datos.", "¡Error!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					break;
				}
				case 1:{JOptionPane.showConfirmDialog(ventana, "El ticket ha sido derivado exitosamente \n al grupo de resolucion: "+nombreGrupo+".", "¡Exito!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
				ventana.cambiarPanel(new MenuMesaAyudaPanel(ventana));
				break;
				}
				default:{}
				}
			}
			
		}
	}

}
