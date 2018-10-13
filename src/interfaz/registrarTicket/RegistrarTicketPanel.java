package interfaz.registrarTicket;

import java.awt.Color;
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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RegistrarTicketPanel extends JPanel {

	Boolean legajoValido;
	JButton btnAceptar, btnCancelar;
	JTextField txtNumTicket, txtNumLegajo, txtFechaAp, txtHoraAp;
	JComboBox</*Clasificacion*/String> listClasificacion; //TODO Cambiar String por clasificacion cuando esa clase exista
	JTextArea txtDescripcion;
	JLabel infoEmpleado; /*Si no ingreso legajo, "Ingrese legajo" en negro
						   Si el legajo no es válido, "Legajo inválido" en rojo
						   Si el legajo es válido, "Nombre y apellido" en verde*/
	
	public RegistrarTicketPanel() {
		
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
		
		infoEmpleado = new JLabel("Ingrese Legajo");
//		infoEmpleado.setSize(120, infoEmpleado.getHeight());
		System.out.println("Cuando recien se crea el panel: "+infoEmpleado.getWidth());
		
		btnAceptar = new JButton("Aceptar");
		btnCancelar = new JButton("Cancelar");
		
		listClasificacion = new JComboBox<>();
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
		
		labelAux = new JLabel("Número de ticket");
		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = insetsDerecha;
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
		
	}
	private void apretoAceptar() {
		// TODO Accion del boton Aceptar de Registrar Ticket.
		
	}
	

	private void validarLegajo() {
		// TODO Validar legajo en Regitrar Ticket
		String legajoIngresado = txtNumLegajo.getText();
		Integer numLegajo;
		
		if(legajoIngresado.trim().isEmpty()) {
			legajoValido = false;
			infoEmpleado.setText("Ingrese un Legajo");
			infoEmpleado.setForeground(Color.black);
//			infoEmpleado.setSize(120, infoEmpleado.getHeight());
		}else {
			try {
				numLegajo = Integer.parseInt(legajoIngresado);
			}catch(NumberFormatException e) {
				//No ingresó un número
				System.out.println("Entro al catch");
				legajoValido = false;
				infoEmpleado.setText("Legajo inválido");
				infoEmpleado.setForeground(Color.red);
//				infoEmpleado.setSize(120, infoEmpleado.getHeight());
				System.out.println("Despues de validar: "+infoEmpleado.getWidth());
				return;
			}
			//TODO Buscar el empleado en el sistema de personal
			//if(SistemaPersonal.getEmpleado(numLegajo){
			System.out.println("Despues del catch");
			legajoValido = true;
			infoEmpleado.setText("Nombre y apellido");
			infoEmpleado.setForeground(Color.blue);
//			infoEmpleado.setSize(120, infoEmpleado.getHeight());
		}
		System.out.println("Despues de validar: "+infoEmpleado.getWidth());
		
	}
}
