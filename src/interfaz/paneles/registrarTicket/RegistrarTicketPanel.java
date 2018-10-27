package interfaz.paneles.registrarTicket;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.management.remote.SubjectDelegationPermission;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.OptionPaneUI;

import interfaz.base.VentanaBase;

public class RegistrarTicketPanel extends JPanel {

	VentanaBase ventana;
	Boolean legajoValido;
	JButton btnAceptar, btnCancelar;
	JTextField txtNumTicket, txtNumLegajo, txtFechaAp, txtHoraAp;
	JComboBox</*Clasificacion*/String> listClasificacion; //TODO Cambiar String por clasificacion cuando esa clase exista
	JTextArea txtDescripcion;
	JLabel infoEmpleado; /*Si no ingreso legajo, "Ingrese legajo" en negro
						   Si el legajo no es v�lido, "Legajo inv�lido" en rojo
						   Si el legajo es v�lido, "Nombre y apellido" en verde*/
	
	public RegistrarTicketPanel(VentanaBase ventana) {
		this.ventana = ventana;
		GridBagConstraints cons = new GridBagConstraints();	
		SimpleDateFormat formatoDia = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatoHora = new SimpleDateFormat("kk:mm");	
		Date hoy = (new GregorianCalendar()).getTime();
		Insets insetsDerecha = new Insets(5,20,5,5), insetsMedio = new Insets(5,5,5,5),
				insetsIzquierda = new Insets(5, 5, 5, 30);
		
		
		
		
		legajoValido = false;
		
		txtNumTicket = new JTextField(15);
		txtNumTicket.setText("000000001"); //TODO Buscar el siguiente numero de ticket con el gestor
		txtNumTicket.setEditable(false);
		
		txtFechaAp = new JTextField(15);
		txtFechaAp.setText(formatoDia.format(hoy));
		txtFechaAp.setEditable(false);
		
		txtHoraAp = new JTextField(15);
		txtHoraAp.setText(formatoHora.format(hoy));
		txtHoraAp.setEditable(false);
		
		txtNumLegajo = new JTextField(15);
		
		txtDescripcion = new JTextArea(4, 20);
		txtDescripcion.setLineWrap(true);
		txtDescripcion.setWrapStyleWord(true);
		JScrollPane descripcionScroll = new JScrollPane(txtDescripcion);
		
		infoEmpleado = new JLabel("Ingrese un legajo");
		infoEmpleado.setPreferredSize(new Dimension(180, infoEmpleado.getFont().getSize()+4));
//		infoEmpleado.setToolTipText("Nombre del empleado");
//		infoEmpleado.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		btnAceptar = new JButton("Aceptar");
		btnCancelar = new JButton("Cancelar");
		
		listClasificacion = new JComboBox<>();
		listClasificacion.addItem("Seleccione una clasificaci�n");
		//TODO meter las clasificaciones en la lista
		for(int i=1; i<5; i++) {
			listClasificacion.addItem("Clasificacion "+i);
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
		
		labelAux = new JLabel("N�mero de ticket");
		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = insetsDerecha;
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		labelAux = new JLabel("N�mero Legajo*");
		cons.gridx = 0;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = insetsDerecha;
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		labelAux = new JLabel("Clasificaci�n de ticket*");
		cons.gridx = 0;
		cons.gridy = 3;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = insetsDerecha;
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		labelAux = new JLabel("Descripci�n*");
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
		cons.fill = GridBagConstraints.BOTH;
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
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = insetsMedio;
		cons.anchor = GridBagConstraints.CENTER;
		cons.fill = GridBagConstraints.BOTH;
		add(listClasificacion, cons);
		
		cons.gridx = 1;
		cons.gridy = 4;
		cons.gridheight = 2;
		cons.gridwidth = 2;
		cons.insets = insetsMedio;
		cons.anchor = GridBagConstraints.WEST;
		cons.fill = GridBagConstraints.VERTICAL;
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
		cons.gridx = 2;
		cons.gridy = 6;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = insetsIzquierda;
		cons.anchor = GridBagConstraints.WEST;
		cons.fill = GridBagConstraints.NONE;
		add(labelAux, cons);
		
		System.out.println("Creo el panel");
		
	}
	private void apretoCancelar() {
		// TODO Accion del boton cancelar de Registrar Ticket"
		ventana.cambiarPanel(new JPanel()); //deberia volver a la pantalla de mesa de ayuda
		
	}
	private void apretoAceptar() {
		// TODO Accion del boton Aceptar de Registrar Ticket.
		
		if(legajoValido) {
			if(txtDescripcion.getText().trim().isEmpty()) {
				//mostrar Error
				JOptionPane.showConfirmDialog(ventana, "Debe ingresar una descripci�n", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
//				System.out.println("Debe ingresar una descripci�n");
			}else {
				if(listClasificacion.getSelectedItem().toString().equals("Seleccione una clasificaci�n")) {
					//mostrar error
					JOptionPane.showConfirmDialog(ventana, "Debe seleccionar una clasificaci�n", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
//					System.out.println("Debe seleccionar una clasificaci�n");
				}else {
//					System.out.println("Todo correcto");
					//TODO Pasar el ticketDTO a RegistrarTicket2Panel
					JPanel p = new RegistrarTicket2Panel(ventana);
//					p.add(new JLabel("siguiente pantalla"), BorderLayout.CENTER);
					ventana.cambiarPanel(p);
				}
			}
		}else {
			//mostrar Error
			JOptionPane.showConfirmDialog(ventana, "Debe ingresar un legajo v�lido", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
//			System.out.println("Debe ingresar un legajo v�lido");
		}
		
		
	}
	

	private void validarLegajo() {
		// TODO Validar legajo en Regitrar Ticket
		String legajoIngresado = txtNumLegajo.getText();
		Integer numLegajo;
		
		if(legajoIngresado.trim().isEmpty()) {
			legajoValido = false;
			infoEmpleado.setText("Ingrese un Legajo");
			infoEmpleado.setForeground(Color.black);
		}else {
			try {
				numLegajo = Integer.parseInt(legajoIngresado);
			}catch(NumberFormatException e) {
				//No ingres� un n�mero
				legajoValido = false;
				infoEmpleado.setText("Legajo inv�lido");
				infoEmpleado.setForeground(Color.red);
				return;
			}
			//TODO Buscar el empleado en el sistema de personal
			//if(SistemaPersonal.getEmpleado(numLegajo){
			legajoValido = true;
			infoEmpleado.setText("Jos� Mar�a D�az de la Pe�a");
			infoEmpleado.setForeground(Color.blue);
			ventana.pack();
		}
		
	}
}
