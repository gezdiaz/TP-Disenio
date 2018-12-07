package interfaz.paneles.derivarTicket;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;

import org.w3c.dom.ls.LSInput;

import accesoADatos.GestorBD;
import dto.TicketDTO;
import interfaz.auxiliar.LimiteTexto;
import interfaz.auxiliar.PanelCancelable;
import interfaz.base.VentanaBase;
import interfaz.paneles.consultarTicket.ConsultarTicketPanel;
import logicaDeNegocios.enumeraciones.EstadoTicket;
import logicaDeNegocios.gestores.GestorTickets;

public class DerivarTicketPanel extends PanelCancelable{

	private VentanaBase ventanaActual, ventanaAnterior;
	private JTextField txtEstadoActual, txtNuevoEstado;
	private JComboBox<String> listClasificacion, listGrupoResolucion;
	private JTextArea txtObservaciones , txtDescripcion;
	private JButton btnAceptar, btnCancelar;
	private TicketDTO ticketDTO;
	private ConsultarTicketPanel consultarTicketPanel;
	private JLabel caracteresRestantes;

	public DerivarTicketPanel(VentanaBase ventanaActual, TicketDTO ticketDTO, VentanaBase ventanaAnterior, ConsultarTicketPanel consultarTicketPanel) {

		this.setLayout(new GridBagLayout());
		JLabel labelAux;
		JScrollPane scroll;
		GridBagConstraints cons = new GridBagConstraints();

		this.ventanaActual = ventanaActual;
		this.ventanaAnterior = ventanaAnterior;
		this.ticketDTO = ticketDTO;
		this.consultarTicketPanel = consultarTicketPanel;
		

		this.txtEstadoActual = new JTextField(10);
		this.txtEstadoActual.setText(ticketDTO.getEstado().getName());
		this.txtEstadoActual.setEditable(false);
		this.txtEstadoActual.setFocusable(false);
		
		this.txtDescripcion = new JTextArea(this.ticketDTO.getDescripcion());
		this.txtDescripcion.setEditable(false);
		this.txtDescripcion.setFocusable(false);

		this.txtNuevoEstado = new JTextField(10);
		this.txtNuevoEstado.setText(EstadoTicket.DERIVADO.getName());
		this.txtNuevoEstado.setEditable(false);
		this.txtNuevoEstado.setFocusable(false);

		this.listClasificacion = new JComboBox<String>();
		List<String> nombresClas = GestorBD.getListClasificaciones();
		for(String n: nombresClas) {
			listClasificacion.addItem(n);
		}
		listClasificacion.setSelectedItem(ticketDTO.getClasificacion());
		
		listClasificacion.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				if(e.getStateChange() == ItemEvent.SELECTED) {
					String clasifSel = (String) e.getItem();
					List<String> grupos = GestorBD.getListGruposConClasificacion(clasifSel);
					listGrupoResolucion.removeAllItems();
					listGrupoResolucion.addItem("Seleccione un grupo de resolución");
					for(String n: grupos) {
						listGrupoResolucion.addItem(n);
					}
				}
				
			}
		});
		

		this.listGrupoResolucion = new JComboBox<String>();
		this.listGrupoResolucion.addItem("Seleccione un grupo de resolución");
		List<String> nombresGrupo = GestorBD.getListGruposConClasificacion(ticketDTO.getClasificacion());
		for(String n: nombresGrupo) {
			listGrupoResolucion.addItem(n);
		}
		
		this.caracteresRestantes = new JLabel("Caracteres restantes: 250");

		this.txtObservaciones = new JTextArea();
		this.txtObservaciones.setLineWrap(true);
		this.txtObservaciones.setWrapStyleWord(true);
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

		//Nombre de la pantalla: Derivar Ticket
		labelAux = new JLabel("Derivar Ticket");
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
		labelAux = new JLabel("Estado actual del ticket");
		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 2;
		cons.weightx = 1;
		cons.insets = new Insets(10, 50, 10, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);

		//Muestra la descripcion del ticket
		labelAux = new JLabel("Descripcion");
		cons.gridx = 0;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 2;
		cons.weightx = 1;
		cons.insets = new Insets(10, 50, 10, 5);
		cons.anchor = GridBagConstraints.NORTHWEST;
		add(labelAux, cons);

		//Muestra el Nuevo estado del ticket
		labelAux = new JLabel("Nuevo estado");
		cons.gridx = 0;
		cons.gridy = 3;
		cons.gridheight = 1;
		cons.gridwidth = 2;
		cons.weightx = 1;
		cons.insets = new Insets(10, 50, 10, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);

		//Muestra la clasificacion del ticket
		labelAux = new JLabel("Clasificacion");
		cons.gridx = 0;
		cons.gridy = 4;
		cons.gridheight = 1;
		cons.gridwidth = 2;
		cons.weightx = 1;
		cons.insets = new Insets(10, 50, 10, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);

		//Muestra el grupo de resolucion
		labelAux = new JLabel("Grupo de resolucion*");
		cons.gridx = 0;
		cons.gridy = 5;
		cons.gridheight = 1;
		cons.gridwidth = 2;
		cons.weightx = 1;
		cons.insets = new Insets(10, 50, 10, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);

		//Muestra las observaciones
		labelAux = new JLabel("Observaciones*");
		cons.gridx = 0;
		cons.gridy = 6;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 1;
		cons.insets = new Insets(25, 50, 10, 5);
		cons.anchor = GridBagConstraints.NORTHWEST;
		add(labelAux, cons);

		//Muestra el estado actual del ticket
		cons.gridx = 1;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(10, 5, 5, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(txtEstadoActual, cons);

		//Muestra la descripcion
		scroll = new JScrollPane(txtDescripcion);
		txtDescripcion.setPreferredSize(new Dimension(200, 70));
		txtDescripcion.setBackground(new Color(0xF2F2F2));
		txtDescripcion.setLineWrap(true);
		txtDescripcion.setWrapStyleWord(true);
		cons.gridx = 1;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(10, 5, 5, 5);
		cons.fill = GridBagConstraints.BOTH;
		cons.anchor = GridBagConstraints.CENTER;
		add(scroll, cons);

		//Muestra el nuevo estado del ticket
		cons.gridx = 1;
		cons.gridy = 3;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(10, 5, 5, 5);
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.WEST;
		add(txtNuevoEstado, cons);

		//Muestra las clasificaciones de ticket
		cons.gridx = 1;
		cons.gridy = 4;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(10, 5, 5, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(listClasificacion, cons);

		//Muestra los grupos de resolucion
		cons.gridx = 1;
		cons.gridy = 5;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(10, 5, 5, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(listGrupoResolucion, cons);

		caracteresRestantes.setFont(new Font(caracteresRestantes.getFont().getFontName(), caracteresRestantes.getFont().getStyle(), 10));
		cons.gridx = 0;
		cons.gridy = 7;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(10, 5, 5, 5);
		cons.anchor = GridBagConstraints.EAST;
		add(caracteresRestantes, cons);
		
		//Muestra las observaciones
		scroll = new JScrollPane(txtObservaciones);
		scroll.setPreferredSize(new Dimension(200, 70));
		txtObservaciones.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
		txtObservaciones.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
		cons.gridx = 1;
		cons.gridy = 6;
		cons.gridheight = 2;
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
		cons.gridy = 8;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 1;
		cons.insets = new Insets(5, 60, 5, 5);
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);

		//Botones
		cons.gridx = 0;
		cons.gridy = 9;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 1;
		cons.insets = new Insets(20, 40, 40, 10);
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.WEST;
		btnAceptar.addActionListener(a -> {
			apretoAceptar();		
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
					apretoAceptar();
				}
			}
		});
		add(btnAceptar, cons);

		cons.gridx = 1;
		cons.gridy = 9;
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
	public void apretoCancelar(Boolean desdeVantana) {
		int res = JOptionPane.YES_OPTION;
		
		if(!desdeVantana) {
			res = JOptionPane.showConfirmDialog(ventanaActual, "¿Está seguro que desea cancelar la operación?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		}
		
		if(res == JOptionPane.YES_OPTION) {
			ventanaActual.dispose();
			ventanaAnterior.setVisible(true);
		}
	}

	private void apretoAceptar() {
		
		String grupoResolucion = (String) listGrupoResolucion.getSelectedItem(), clasificacion = null;
		
		if(txtObservaciones.getText().trim().isEmpty()) {
			JOptionPane.showConfirmDialog(ventanaActual, "Debe ingresar observaciones.", "¡Error!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
		}else {
			if(grupoResolucion.equals("Seleccione un grupo de resolución")) {
				JOptionPane.showConfirmDialog(ventanaActual, "Debe seleccionar un grupo de resolucion.", "¡Error!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
			}
			else {
				
				if(!listClasificacion.getSelectedItem().equals(ticketDTO.getClasificacion())) {
					clasificacion = (String) listClasificacion.getSelectedItem();
				}
				
				
				switch(GestorTickets.derivarTicket(ticketDTO, grupoResolucion, txtObservaciones.getText().trim(), clasificacion)) {
				case -3:{
					JOptionPane.showConfirmDialog(ventanaActual, "No se ha podido actualizar el ticket en la base de datos.", "¡Error!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					break;
				}
				case -2:{
					JOptionPane.showConfirmDialog(ventanaActual, "Error conectándose a la base de datos.", "¡Error!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					break;
				}
				case -1:{
					JOptionPane.showConfirmDialog(ventanaActual, "Grupo de resolucion o clasificación no encontrados en la base de datos.", "¡Error!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					break;
				}
				case 0:{
					JOptionPane.showConfirmDialog(ventanaActual, "Ticket no encontrado en la base de datos.", "¡Error!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					break;
				}
				case 1:{JOptionPane.showConfirmDialog(ventanaActual, "El ticket ha sido derivado exitosamente\n al grupo: "+grupoResolucion+".", "¡Exito!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
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
