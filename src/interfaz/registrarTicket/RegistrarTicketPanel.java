package interfaz.registrarTicket;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RegistrarTicketPanel extends JPanel {

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
		
		txtNumTicket = new JTextField(15);
		txtNumTicket.setText("000000001"); //TODO Buscar el siguiente numero de ticket con el gestor
		txtNumTicket.setEditable(false);
		
		txtFechaAp = new JTextField(15);
		txtFechaAp.setText(formatoDia.format(hoy));
		
		txtHoraAp = new JTextField(15);
		txtHoraAp.setText(formatoHora.format(hoy));
		
		txtNumLegajo = new JTextField(15);
		
		txtDescripcion = new JTextArea(4, 20);
		txtDescripcion.setLineWrap(true);
		txtDescripcion.setWrapStyleWord(true);
		JScrollPane scroll = new JScrollPane(txtDescripcion);
		
		
		infoEmpleado = new JLabel("Ingrese un Legajo");
		
		btnAceptar = new JButton("Aceptar");
		btnCancelar = new JButton("Cancelar");
		
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
		cons.insets = new Insets(5, 20, 5, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		labelAux = new JLabel("Número Legajo");
		cons.gridx = 0;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(5, 20, 5, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		labelAux = new JLabel("Calsificación de ticket");
		cons.gridx = 0;
		cons.gridy = 3;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(5, 20, 5, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		labelAux = new JLabel("Descripción");
		cons.gridx = 0;
		cons.gridy = 4;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(5, 20, 5, 5);
		cons.anchor = GridBagConstraints.NORTHWEST;
		add(labelAux, cons);
		
		labelAux = new JLabel("Fecha apertura");
		cons.gridx = 0;
		cons.gridy = 6;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(5, 20, 5, 5);
		cons.anchor = GridBagConstraints.WEST;
		
		labelAux = new JLabel("Hora apertura");
		cons.gridx = 0;
		cons.gridy = 7;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(5, 20, 5, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);

		
		labelAux = new JLabel("*Campo obligatorio");
		labelAux.setFont(new Font(labelAux.getFont().getFontName(), labelAux.getFont().getStyle(), 5));
		cons.gridx = 0;
		cons.gridy = 8;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(5, 20, 5, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		cons.gridx = 0;
		cons.gridy = 9;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(5, 20, 5, 5);
		cons.anchor = GridBagConstraints.CENTER;
		add(btnAceptar, cons);
		
	}
	
	
}
