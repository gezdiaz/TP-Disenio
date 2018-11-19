package interfaz.paneles.consultarTicket;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import accesoADatos.GestorBD;
import dto.TicketDTO;
import interfaz.base.VentanaBase;
import logicaDeNegocios.entidades.Ticket;
import logicaDeNegocios.enumeraciones.EstadoTicket;

public class ConsultarTicketPanel extends JPanel {

	private VentanaBase ventana;
	private JTextField txtNumTicket, txtFechaApertura, txtNumLegajo, txtFechaUltimoCambio;
	private JComboBox<String> listClasificacion, listUltimoGrupo, listEstadoActual;
	private JButton btnBuscar, btnVolver;
	private TablaTicketsPanel tablaResultados;
	
	public ConsultarTicketPanel(VentanaBase ventana) {
		this.ventana=ventana;
		this.setLayout(new GridBagLayout());
		JLabel labelAux;
		GridBagConstraints cons = new GridBagConstraints();
		
		
		txtNumTicket = new JTextField("        ");
//		txtNumTicket.setSize(50, (int) txtNumTicket.getSize().getHeight());
		
		listClasificacion = new JComboBox<String>();
		listClasificacion.addItem("Seleccione una clasificación");
		List<String> nombresClas = GestorBD.getListClasificaciones();
		for(String n: nombresClas) {
			listClasificacion.addItem(n);
		}
		
		txtFechaApertura = new JTextField();
		
		listUltimoGrupo = new JComboBox<String>();
		listUltimoGrupo.addItem("Seleccione un grupo de resolución");
		List<String> nombresGrupo = GestorBD.getListGrupos();
		for(String n: nombresGrupo) {
			listUltimoGrupo.addItem(n);
		}
		
		txtNumLegajo = new JTextField(6);
		
		listEstadoActual = new JComboBox<String>();
		for(EstadoTicket e: EstadoTicket.values()) {
			listEstadoActual.addItem(e.name());
		}
		
		txtFechaUltimoCambio = new JTextField(10);
		
		btnBuscar = new JButton("Buscar");
		btnVolver = new JButton("Vover");
		
		tablaResultados = new TablaTicketsPanel(new ArrayList<TicketDTO>(), ventana);
		
		labelAux = new JLabel("Consultar Ticket");
		labelAux.setFont(new Font(labelAux.getFont().getFontName(), labelAux.getFont().getStyle(), 20));
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(20, 20, 15, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		labelAux = new JLabel("Número de ticket");
		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(10, 25, 5, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		labelAux = new JLabel("Clasificaón actual");
		cons.gridx = 0;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(5, 25, 5, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		labelAux = new JLabel("Fecha de Apertura");
		cons.gridx = 0;
		cons.gridy = 3;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(5, 25, 5, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		labelAux = new JLabel("Último grupo de resolución");
		cons.gridx = 0;
		cons.gridy = 4;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(5, 25, 5, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		labelAux = new JLabel("Número de legajo");
		cons.gridx = 3;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(10, 15, 5, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		labelAux = new JLabel("Número de legajo");
		cons.gridx = 3;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(10, 15, 5, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		labelAux = new JLabel("Estado actual");
		cons.gridx = 3;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(5, 15, 5, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		labelAux = new JLabel("Fecha último cambio");
		cons.gridx = 3;
		cons.gridy = 3;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(5, 15, 5, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		labelAux = new JLabel("dd/mm/aaaa");
		labelAux.setFont(new Font(labelAux.getFont().getFontName(), labelAux.getFont().getStyle(), 10));
		cons.gridx = 2;
		cons.gridy = 3;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(5, 5, 5, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		labelAux = new JLabel("dd/mm/aaaa");
		labelAux.setFont(new Font(labelAux.getFont().getFontName(), labelAux.getFont().getStyle(), 10));
		cons.gridx=5;
		add(labelAux, cons);
		
		cons.gridx = 1;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(10, 5, 5, 5);
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.anchor = GridBagConstraints.WEST;
		//cons.fill = GridBagConstraints.BOTH;
		add(txtNumTicket, cons);
		
		cons.gridx = 1;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 2;
		cons.insets = new Insets(5, 5, 5, 5);
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.WEST;
		//cons.fill = GridBagConstraints.NONE;
		add(listClasificacion, cons);
		
		cons.gridx = 1;
		cons.gridy = 3;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(5, 5, 5, 5);
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.anchor = GridBagConstraints.WEST;
		//cons.fill = GridBagConstraints.BOTH;
		add(txtFechaApertura, cons);
		
		cons.gridx = 1;
		cons.gridy = 4;
		cons.gridheight = 1;
		cons.gridwidth = 2;
		cons.insets = new Insets(5, 5, 5, 5);
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.WEST;
		//cons.fill = GridBagConstraints.NONE;
		add(listUltimoGrupo, cons);
		
		cons.gridx = 4;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(10, 5, 5, 5);
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.anchor = GridBagConstraints.WEST;
		//cons.fill = GridBagConstraints.BOTH;
		add(txtNumLegajo, cons);
		
		cons.gridx = 4;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(5, 5, 5, 5);
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.WEST;
		//cons.fill = GridBagConstraints.NONE;
		add(listEstadoActual, cons);
		
		cons.gridx = 4;
		cons.gridy = 3;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(5, 5, 5, 5);
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.anchor = GridBagConstraints.WEST;
		//cons.fill = GridBagConstraints.BOTH;
		add(txtFechaUltimoCambio, cons);
		
		cons.gridx = 3;
		cons.gridy = 4;
		cons.gridheight = 1;
		cons.gridwidth = 2;
		cons.insets = new Insets(5, 5, 5, 5);
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.CENTER;
		//cons.fill = GridBagConstraints.NONE;
		add(btnBuscar, cons);
		
		cons.gridx = 0;
		cons.gridy = 5;
		cons.gridheight = 1;
		cons.gridwidth = 6;
		cons.insets = new Insets(5, 5, 5, 5);
		cons.anchor = GridBagConstraints.CENTER;
		cons.fill = GridBagConstraints.BOTH;
		cons.weightx = 2;
		tablaResultados.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(tablaResultados, cons);
		
		cons.gridx = 0;
		cons.gridy = 6;
		cons.gridheight = 1;
		cons.gridwidth = 6;
		cons.insets = new Insets(5, 5, 5, 5);
		cons.anchor = GridBagConstraints.CENTER;
		cons.fill = GridBagConstraints.NONE;
		cons.weightx = 1;
		add(btnVolver, cons);
		
		System.out.println("Dentro del panel:" + this.getSize());
		
	}
	
}
