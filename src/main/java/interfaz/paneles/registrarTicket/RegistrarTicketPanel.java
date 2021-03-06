package interfaz.paneles.registrarTicket;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;

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

import accesoADatos.GestorBD;
import dto.TicketDTO;
import interfaz.auxiliar.LimiteTexto;
import interfaz.auxiliar.PanelCancelable;
import interfaz.base.VentanaBase;
import interfaz.principal.MenuMesaAyudaPanel;
import logicaDeNegocios.entidades.Empleado;
import logicaDeNegocios.gestores.GestorTickets;
import logicaDeNegocios.gestores.SistemaPersonal;

public class RegistrarTicketPanel extends PanelCancelable {

	private VentanaBase ventana;
	private Boolean legajoValido;
	private JButton btnAceptar, btnCancelar;
	private JTextField txtNumTicket, txtNumLegajo, txtFechaAp, txtHoraAp;
	private JComboBox<String> listClasificacion;
	private JTextArea txtDescripcion;
	private JLabel infoEmpleado; /*Si no ingreso legajo, "Ingrese legajo" en negro
						   		   Si el legajo no es válido, "Legajo inválido" en rojo
						   		   Si el legajo es válido, "Nombre y apellido" en verde*/
	private TicketDTO ticketDTO;
	private LocalDateTime fecha;
	private JLabel caracteresRestantes;
	

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
		AbstractDocument doc = (AbstractDocument) txtNumLegajo.getDocument();
		doc.setDocumentFilter(new LimiteTexto(6));
		
		caracteresRestantes = new JLabel("Caracteres restantes: 250");

		txtDescripcion = new JTextArea(4, 20);
		txtDescripcion.setLineWrap(true);
		txtDescripcion.setWrapStyleWord(true);
		JScrollPane descripcionScroll = new JScrollPane(txtDescripcion);
		doc = (AbstractDocument) txtDescripcion.getDocument();
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

		infoEmpleado = new JLabel("Ingrese un legajo");
		infoEmpleado.setPreferredSize(new Dimension(180, infoEmpleado.getFont().getSize()+4));

		btnAceptar = new JButton("Aceptar");
		btnCancelar = new JButton("Cancelar");

		listClasificacion = new JComboBox<>();
		listClasificacion.addItem("Seleccione una clasificación");
		
		List<String> nombresClas = GestorBD.getListClasificaciones();
		for(String n: nombresClas) {
			listClasificacion.addItem(n);
		}
		
		JLabel labelAux;

		setLayout(new GridBagLayout());


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
		
		labelAux = new JLabel("Descripción*");
		cons.gridx = 0;
		cons.gridy = 3;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = insetsDerecha;
		cons.anchor = GridBagConstraints.NORTHWEST;
		add(labelAux, cons);

		labelAux = new JLabel("Clasificación de ticket*");
		cons.gridx = 0;
		cons.gridy = 5;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = insetsDerecha;
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);

		labelAux = new JLabel("Fecha apertura");
		cons.gridx = 0;
		cons.gridy = 6;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = insetsDerecha;
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);

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
		cons.insets = new Insets(20, 40, 30, 10);
		cons.anchor = GridBagConstraints.WEST;
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

		cons.gridx = 1;
		cons.gridy = 3;
		cons.gridheight = 2;
		cons.gridwidth = 2;
		cons.insets = insetsMedio;
		cons.anchor = GridBagConstraints.WEST;
		cons.fill = GridBagConstraints.VERTICAL;
		txtDescripcion.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
		txtDescripcion.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
		add(descripcionScroll, cons);
		
		cons.gridx = 1;
		cons.gridy = 5;
		cons.gridheight = 1;
		cons.gridwidth = 2;
		cons.insets = insetsMedio;
		cons.anchor = GridBagConstraints.WEST;
		cons.fill = GridBagConstraints.NONE;
		add(listClasificacion, cons);

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

		cons.gridx = 2;
		cons.gridy = 9;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(20, 10, 30, 40);
		cons.anchor = GridBagConstraints.EAST;
		cons.fill = GridBagConstraints.NONE;
		btnCancelar.addActionListener(e -> {
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

		cons.gridx = 2;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = insetsIzquierda;
		cons.anchor = GridBagConstraints.WEST;
		cons.fill = GridBagConstraints.NONE;
		cons.weightx = 2;
		add(infoEmpleado, cons);
		
		caracteresRestantes.setFont(new Font(caracteresRestantes.getFont().getFontName(), caracteresRestantes.getFont().getStyle(), 10));
		cons.gridx = 0;
		cons.gridy = 4;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = insetsIzquierda;
		cons.anchor = GridBagConstraints.EAST;
		cons.fill = GridBagConstraints.NONE;
		cons.weightx = 2;
		add(caracteresRestantes, cons);

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
		
	}

	public void setVentana(VentanaBase ventana) {
		this.ventana = ventana;
	}

	@Override
	public void apretoCancelar(Boolean desdeVentana) {
		
		int res = JOptionPane.YES_OPTION;
		
		if(!desdeVentana) {
			res = JOptionPane.showConfirmDialog(ventana, "¿Está seguro que desea cancelar la operación?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		}
		if(res == JOptionPane.YES_OPTION) {
			ventana.cambiarPanel(new MenuMesaAyudaPanel(ventana)); //deberia volver a la pantalla de mesa de ayuda
		}

	}
	private void apretoAceptar() {
		
		if(legajoValido) {
			if(txtDescripcion.getText().trim().isEmpty()) {
				//mostrar Error
				JOptionPane.showConfirmDialog(ventana, "Debe ingresar una descripción.", "¡Error!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
			}else {
				if(listClasificacion.getSelectedItem().toString().equals("Seleccione una clasificación")) {
					//mostrar error
					JOptionPane.showConfirmDialog(ventana, "Debe seleccionar una clasificación.", "¡Error!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				}else {					
					ticketDTO.setNumLegajo(Long.parseLong((txtNumLegajo.getText())));
					ticketDTO.setClasificacion(listClasificacion.getSelectedItem().toString());
					ticketDTO.setDescripcion(txtDescripcion.getText().trim());
					ticketDTO.setFechaHoraApertura(fecha);

					if(GestorTickets.registrarTicket(ticketDTO)) {
						JOptionPane.showConfirmDialog(ventana, "El ticket se ha registrado correctamente.", "Registro exitoso", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showConfirmDialog(ventana, "Se produjo un error al registrar el ticket.", "¡Error!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					JPanel p = new RegistrarTicket2Panel(ventana, ticketDTO);
					ventana.cambiarPanel(p);
				}
			}
		}else {
			//mostrar Error
			JOptionPane.showConfirmDialog(ventana, "Debe ingresar un legajo válido.", "¡Error!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
		}
	}


	private void validarLegajo() {
		
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
