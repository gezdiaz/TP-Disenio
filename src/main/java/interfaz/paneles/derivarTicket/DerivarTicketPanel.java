package interfaz.paneles.derivarTicket;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import accesoADatos.GestorBD;
import dto.TicketDTO;
import interfaz.base.VentanaBase;
import logicaDeNegocios.enumeraciones.EstadoTicket;

public class DerivarTicketPanel extends JPanel{

	private VentanaBase ventana;
	private JTextField txtEstadoActual, txtNuevoEstado;
	private JComboBox<String> listClasificacion, listGrupoResolucion;
	private JTextArea txtObservaciones , txtDescripcion;
	private JButton btnAceptar, btnCancelar;
	private TicketDTO ticketDTO;

	public DerivarTicketPanel(VentanaBase ventana, TicketDTO ticketDTO, VentanaBase ventanaAnterior) {

		this.setLayout(new GridBagLayout());
		JLabel labelAux;
		JScrollPane scroll;
		GridBagConstraints cons = new GridBagConstraints();

		this.ventana = ventana;
		this.ticketDTO = ticketDTO;
		//TODO que muestre el estado del ticket
		this.txtEstadoActual = new JTextField(10/*this.ticketDTO.getEstado().name()*/);
		this.txtEstadoActual.setText(EstadoTicket.Abierto.name());
		this.txtEstadoActual.setEditable(false);
		//TODO que muestre la descripcion del ticket
		this.txtDescripcion = new JTextArea("Una descripcion"/*this.ticketDTO.getDescripcion()*/);
		this.txtDescripcion.setEditable(false);

		this.txtNuevoEstado = new JTextField(10);
		this.txtNuevoEstado.setText("Derivado");
		this.txtNuevoEstado.setEditable(false);

		this.listClasificacion = new JComboBox<String>();
		this.listClasificacion.addItem("Seleccione una clasificacion");
		List<String> nombresClas = GestorBD.getListClasificaciones();
		for(String n: nombresClas) {
			listClasificacion.addItem(n);
		}

		this.listGrupoResolucion = new JComboBox<String>();
		this.listGrupoResolucion.addItem("Seleccione un grupo de resolución");
		List<String> nombresGrupo = GestorBD.getListGrupos();
		for(String n: nombresGrupo) {
			listGrupoResolucion.addItem(n);
		}

		this.txtObservaciones = new JTextArea();

		btnAceptar = new JButton("Aceptar");
		btnCancelar = new JButton("Cancelar");

		this.ventana.setVisible(true);

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
		labelAux = new JLabel("Estado actual del ticket"/*ticketDTO.getNumTicket()*/);
		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 2;
		cons.weightx = 1;
		cons.insets = new Insets(10, 50, 10, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);

		//Muestra la descripcion del ticket
		labelAux = new JLabel("Descripcion"/*ticketDTO.getNumTicket()*/);
		cons.gridx = 0;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 2;
		cons.weightx = 1;
		cons.insets = new Insets(10, 50, 10, 5);
		cons.anchor = GridBagConstraints.NORTHWEST;
		add(labelAux, cons);

		//Muestra el Nuevo estado del ticket
		labelAux = new JLabel("Nuevo estado"/*ticketDTO.getNumTicket()*/);
		cons.gridx = 0;
		cons.gridy = 3;
		cons.gridheight = 1;
		cons.gridwidth = 2;
		cons.weightx = 1;
		cons.insets = new Insets(10, 50, 10, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);

		//Muestra la clasificacion del ticket
		labelAux = new JLabel("Clasificacion"/*ticketDTO.getNumTicket()*/);
		cons.gridx = 0;
		cons.gridy = 4;
		cons.gridheight = 1;
		cons.gridwidth = 2;
		cons.weightx = 1;
		cons.insets = new Insets(10, 50, 10, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);

		//Muestra el grupo de resolucion
		labelAux = new JLabel("Grupo de resolucion*"/*ticketDTO.getNumTicket()*/);
		cons.gridx = 0;
		cons.gridy = 5;
		cons.gridheight = 1;
		cons.gridwidth = 2;
		cons.weightx = 1;
		cons.insets = new Insets(10, 50, 10, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);

		//Muestra las observaciones
		labelAux = new JLabel("Observaciones*"/*ticketDTO.getNumTicket()*/);
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

		//Muestra las observaciones
		scroll = new JScrollPane(txtObservaciones);
		txtObservaciones.setPreferredSize(new Dimension(200, 70));
		cons.gridx = 1;
		cons.gridy = 6;
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
		cons.gridy = 7;
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
		cons.gridy = 8;
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
		cons.gridy = 8;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 1;
		cons.insets = new Insets(20, 10, 40, 25);
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.WEST;
		add(btnCancelar, cons);
	}

}
