package interfaz.paneles.registrarTicket;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;

import javax.swing.Action;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import accesoADatos.GestorBD;
import dto.TicketDTO;
import interfaz.base.VentanaBase;
import interfaz.principal.MenuMesaAyudaPanel;
import logicaDeNegocios.entidades.Empleado;
import logicaDeNegocios.gestores.GestorTickets;
import logicaDeNegocios.gestores.SistemaPersonal;

public class RegistrarTicketPanel extends JPanel {

	VentanaBase ventana;
	Boolean legajoValido;
	JButton btnAceptar, btnCancelar;
	JTextField txtNumTicket, txtNumLegajo, txtFechaAp, txtHoraAp;
	JComboBox</*Clasificacion*/String> listClasificacion; //TODO Cambiar String por clasificacion cuando esa clase exista
	JTextArea txtDescripcion;
	JLabel infoEmpleado; /*Si no ingreso legajo, "Ingrese legajo" en negro
						   Si el legajo no es válido, "Legajo inválido" en rojo
						   Si el legajo es válido, "Nombre y apellido" en verde*/
	TicketDTO ticketDTO;
	LocalDateTime fecha;

	public RegistrarTicketPanel(VentanaBase ventana) {
		this();
		setVentana(ventana);
	}

	public RegistrarTicketPanel() {
		GridBagConstraints cons = new GridBagConstraints();	
		DateTimeFormatter formatoDia = DateTimeFormatter.ofPattern("dd/MM/uuuu", Locale.getDefault());
		DateTimeFormatter formatoHora = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);	
		fecha = LocalDateTime.now();
		Insets insetsDerecha = new Insets(5,20,5,5), insetsMedio = new Insets(5,5,5,5),
				insetsIzquierda = new Insets(5, 5, 5, 30);


		legajoValido = false;

		txtNumTicket = new JTextField(15);
		ticketDTO = GestorTickets.getNuevoTicket();
		//GestorTickets.guardarTicket(ticketDTO);
		System.out.println("ticket dto: "+ticketDTO);
		System.out.println("numTicektDto: "+ticketDTO.getNumTicket());
		System.out.println("txtnumticket: "+txtNumTicket);
		txtNumTicket.setText(ticketDTO.getNumTicket().toString());
		txtNumTicket.setEditable(false);
		txtNumTicket.setFocusable(false);

		txtFechaAp = new JTextField(15);
		txtFechaAp.setText(fecha.format(formatoDia));
		txtFechaAp.setEditable(false);
		txtFechaAp.setFocusable(false);

		txtHoraAp = new JTextField(15);
		txtHoraAp.setText(fecha.format(formatoHora));
		txtHoraAp.setEditable(false);
		txtHoraAp.setFocusable(false);

		txtNumLegajo = new JTextField(15);

		txtDescripcion = new JTextArea(4, 20);
		txtDescripcion.setLineWrap(true);
		txtDescripcion.setWrapStyleWord(true);
		JScrollPane descripcionScroll = new JScrollPane(txtDescripcion);

		infoEmpleado = new JLabel("Ingrese un legajo");
		infoEmpleado.setPreferredSize(new Dimension(180, infoEmpleado.getFont().getSize()+4));

		btnAceptar = new JButton("Aceptar");
		btnCancelar = new JButton("Cancelar");

		listClasificacion = new JComboBox<>();
		listClasificacion.addItem("Seleccione una clasificación");
		//TODO meter las clasificaciones en la lista
		List<String> nombresClas = GestorBD.getListClasificaciones();
		for(String n: nombresClas) {
			listClasificacion.addItem(n);
		}
		
		JLabel labelAux;

		setLayout(new GridBagLayout());
		setBackground(new Color(163,255,140));


		labelAux = new JLabel("Registrar ticket");
		labelAux.setFont(new Font(labelAux.getFont().getFontName(), labelAux.getFont().getStyle(), 20));
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(5, 25, 5, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);

				
		labelAux = new JLabel("Número de ticket");
		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = insetsDerecha;
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);

		labelAux = new JLabel("Número Legajo*");
		cons.gridx = 0;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = insetsDerecha;
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);

		labelAux = new JLabel("Clasificación de ticket*");
		cons.gridx = 0;
		cons.gridy = 3;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = insetsDerecha;
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);

		labelAux = new JLabel("Descripción*");
		cons.gridx = 0;
		cons.gridy = 4;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = insetsDerecha;
		cons.anchor = GridBagConstraints.NORTHWEST;
		add(labelAux, cons);

		labelAux = new JLabel("Fecha apertura");
		cons.gridx = 0;
		cons.gridy = 6;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = insetsDerecha;
		cons.anchor = GridBagConstraints.WEST;

		labelAux = new JLabel("Hora apertura");
		cons.gridx = 0;
		cons.gridy = 7;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = insetsDerecha;
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);


		labelAux = new JLabel("*Campo obligatorio");
		labelAux.setFont(new Font(labelAux.getFont().getFontName(), labelAux.getFont().getStyle(), 8));
		cons.gridx = 0;
		cons.gridy = 8;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = insetsDerecha;
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);

		cons.gridx = 0;
		cons.gridy = 9;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = insetsDerecha;
		cons.anchor = GridBagConstraints.CENTER;
		btnAceptar.addActionListener(e -> {
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
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = insetsMedio;
		cons.anchor = GridBagConstraints.CENTER;
		cons.fill = GridBagConstraints.BOTH;
		add(txtNumTicket, cons);

		cons.gridx = 1;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = insetsMedio;
		cons.anchor = GridBagConstraints.CENTER;
		cons.fill = GridBagConstraints.NONE;
		txtNumLegajo.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				validarLegajo();			
			}

			@Override
			public void focusGained(FocusEvent arg0) {

			}
		});
		add(txtNumLegajo, cons);

//		txtNumLegajo.requestFocusInWindow();

		cons.gridx = 1;
		cons.gridy = 3;
		cons.gridheight = 1;
		cons.gridwidth = 2;
		cons.insets = insetsMedio;
		cons.anchor = GridBagConstraints.WEST;
		cons.fill = GridBagConstraints.NONE;
		add(listClasificacion, cons);

		cons.gridx = 1;
		cons.gridy = 4;
		cons.gridheight = 2;
		cons.gridwidth = 2;
		cons.insets = insetsMedio;
		cons.anchor = GridBagConstraints.WEST;
		cons.fill = GridBagConstraints.VERTICAL;
		txtDescripcion.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
		txtDescripcion.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
		add(descripcionScroll, cons);

		cons.gridx = 1;
		cons.gridy = 6;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = insetsMedio;
		cons.anchor = GridBagConstraints.CENTER;
		cons.fill = GridBagConstraints.BOTH;
		add(txtFechaAp, cons);

		cons.gridx = 1;
		cons.gridy = 7;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = insetsMedio;
		cons.anchor = GridBagConstraints.CENTER;
		cons.fill = GridBagConstraints.BOTH;
		add(txtHoraAp, cons);

		cons.gridx = 1;
		cons.gridy = 9;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = insetsMedio;
		cons.anchor = GridBagConstraints.CENTER;
		cons.fill = GridBagConstraints.NONE;
		btnCancelar.addActionListener(e -> {
			apretoCancelar();
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
					apretoCancelar();
				}
			}
		});
		add(btnCancelar, cons);

		cons.gridx = 2;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = insetsIzquierda;
		cons.anchor = GridBagConstraints.WEST;
		cons.fill = GridBagConstraints.NONE;
		cons.weightx = 2;
		add(infoEmpleado, cons);

		labelAux = new JLabel("dd/mm/aaaa");
		labelAux.setFont(new Font(labelAux.getFont().getFontName(), labelAux.getFont().getStyle(), 10));
		cons.gridx = 2;
		cons.gridy = 6;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = insetsIzquierda;
		cons.anchor = GridBagConstraints.WEST;
		cons.fill = GridBagConstraints.NONE;
		add(labelAux, cons);

		txtNumLegajo.requestFocusInWindow();
		
	}

	public void setVentana(VentanaBase ventana) {
		this.ventana = ventana;
	}

	private void apretoCancelar() {
		// TODO Accion del boton cancelar de Registrar Ticket
//		GestorTickets.eliminarTicket(ticketDTO);
		int res = JOptionPane.showConfirmDialog(ventana, "Está seguro que desea cancelar la operación", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		if(res == JOptionPane.YES_OPTION) {
			ventana.cambiarPanel(new MenuMesaAyudaPanel(ventana)); //deberia volver a la pantalla de mesa de ayuda
		}

	}
	private void apretoAceptar() {
		// TODO Accion del boton Aceptar de Registrar Ticket.

		if(legajoValido) {
			if(txtDescripcion.getText().trim().isEmpty()) {
				//mostrar Error
				JOptionPane.showConfirmDialog(ventana, "Debe ingresar una descripción", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				//				System.out.println("Debe ingresar una descripción");
			}else {
				if(listClasificacion.getSelectedItem().toString().equals("Seleccione una clasificación")) {
					//mostrar error
					JOptionPane.showConfirmDialog(ventana, "Debe seleccionar una clasificación", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					//					System.out.println("Debe seleccionar una clasificación");
				}else {
					//					System.out.println("Todo correcto");
					//TODO Pasar el ticketDTO a RegistrarTicket2Panel

					ticketDTO.setNumLegajo(Long.parseLong((txtNumLegajo.getText())));
					ticketDTO.setClasificacion(listClasificacion.getSelectedItem().toString());
					ticketDTO.setDescripcion(txtDescripcion.getText().trim());
					ticketDTO.setFechaHoraApertura(fecha);

					if(GestorTickets.registrarTicket(ticketDTO)) {
						JOptionPane.showConfirmDialog(ventana, "El ticket se ha registrado correctamente", "Registro exitoso", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showConfirmDialog(ventana, "Se produjo un error al registrar el ticket", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}

					JPanel p = new RegistrarTicket2Panel(ventana, ticketDTO);
					ventana.cambiarPanel(p);

				}
			}
		}else {
			//mostrar Error
			JOptionPane.showConfirmDialog(ventana, "Debe ingresar un legajo válido", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
			//			System.out.println("Debe ingresar un legajo válido");
		}


	}


	private void validarLegajo() {
		// TODO Validar legajo en Regitrar Ticket
		String legajoIngresado = txtNumLegajo.getText();
		Long numLegajo;

		if(legajoIngresado.trim().isEmpty()) {
			legajoValido = false;
			infoEmpleado.setText("Ingrese un Legajo");
			infoEmpleado.setForeground(Color.black);
		}else {
			try {
				numLegajo = Long.parseLong(legajoIngresado);
			}catch(NumberFormatException e) {
				//No ingresó un número
				legajoValido = false;
				infoEmpleado.setText("Legajo inválido");
				infoEmpleado.setForeground(Color.red);
				return;
			}
			//TODO Buscar el empleado en el sistema de personal
			Empleado empleado = SistemaPersonal.getEmpleado(numLegajo);
			if(empleado!=null){
				legajoValido = true;
				infoEmpleado.setText(empleado.getNombre()+" "+empleado.getApellido());
				infoEmpleado.setForeground(Color.blue);
			}
			else {
				legajoValido = false;
				infoEmpleado.setText("Legajo inválido");
				infoEmpleado.setForeground(Color.red);
			}
			ventana.pack();
		}

	}
}
