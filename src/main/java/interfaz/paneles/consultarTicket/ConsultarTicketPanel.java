package interfaz.paneles.consultarTicket;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import accesoADatos.GestorBD;
import dto.TicketDTO;
import interfaz.base.VentanaBase;
import interfaz.principal.MenuMesaAyudaPanel;
import logicaDeNegocios.entidades.Ticket;
import logicaDeNegocios.enumeraciones.EstadoTicket;
import logicaDeNegocios.gestores.GestorTickets;

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
		
		
		txtNumTicket = new JTextField(20);
		
		listClasificacion = new JComboBox<String>();
		listClasificacion.addItem("Todas las clasificaciones");
		List<String> nombresClas = GestorBD.getListClasificaciones();
		for(String n: nombresClas) {
			listClasificacion.addItem(n);
		}
		listClasificacion.setSelectedItem("Todas las clasificaciones");
		
		txtFechaApertura = new JTextField();
		
		listUltimoGrupo = new JComboBox<String>();
		listUltimoGrupo.addItem("Todos los grupos de resolución");
		List<String> nombresGrupo = GestorBD.getListGrupos();
		for(String n: nombresGrupo) {
			listUltimoGrupo.addItem(n);
		}
		listUltimoGrupo.setSelectedItem("Todos los grupos de resolución");
		
		txtNumLegajo = new JTextField(6);
		
		listEstadoActual = new JComboBox<String>();
		listEstadoActual.addItem("Todos los estados");
		for(EstadoTicket e: EstadoTicket.values()) {
			listEstadoActual.addItem(e.getName());
		}
		listEstadoActual.setSelectedItem(EstadoTicket.EN_MESA_DE_AYUDA.getName());
		
		txtFechaUltimoCambio = new JTextField(10);
		
		btnBuscar = new JButton("Buscar");
		btnVolver = new JButton("Vover");
		
		tablaResultados = new TablaTicketsPanel(new ArrayList<TicketDTO>(), ventana, this);
		
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
		add(txtNumTicket, cons);
		
		cons.gridx = 1;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 2;
		cons.insets = new Insets(5, 5, 5, 5);
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.WEST;
		add(listClasificacion, cons);
		
		cons.gridx = 1;
		cons.gridy = 3;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(5, 5, 5, 5);
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.anchor = GridBagConstraints.WEST;
		add(txtFechaApertura, cons);
		
		cons.gridx = 1;
		cons.gridy = 4;
		cons.gridheight = 1;
		cons.gridwidth = 2;
		cons.insets = new Insets(5, 5, 5, 5);
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.WEST;
		add(listUltimoGrupo, cons);
		
		cons.gridx = 4;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(10, 5, 5, 5);
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.anchor = GridBagConstraints.WEST;
		add(txtNumLegajo, cons);
		
		cons.gridx = 4;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(5, 5, 5, 5);
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.WEST;
		add(listEstadoActual, cons);
		
		cons.gridx = 4;
		cons.gridy = 3;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(5, 5, 5, 5);
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.anchor = GridBagConstraints.WEST;
		add(txtFechaUltimoCambio, cons);
		
		cons.gridx = 3;
		cons.gridy = 4;
		cons.gridheight = 1;
		cons.gridwidth = 2;
		cons.insets = new Insets(5, 5, 5, 5);
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.CENTER;
		btnBuscar.addActionListener(a -> {
			buscar();
		});
		btnBuscar.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			@Override
			public void keyPressed(KeyEvent e) {

				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					buscar();
				}
			}
		});
		add(btnBuscar, cons);
		
		cons.gridx = 0;
		cons.gridy = 5;
		cons.gridheight = 1;
		cons.gridwidth = 6;
		cons.insets = new Insets(5, 5, 5, 5);
		cons.anchor = GridBagConstraints.CENTER;
		cons.fill = GridBagConstraints.BOTH;
		cons.weightx = 2;
//		tablaResultados.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(tablaResultados, cons);
		
		cons.gridx = 0;
		cons.gridy = 6;
		cons.gridheight = 1;
		cons.gridwidth = 6;
		cons.insets = new Insets(5, 5, 5, 5);
		cons.anchor = GridBagConstraints.CENTER;
		cons.fill = GridBagConstraints.NONE;
		cons.weightx = 1;
		btnVolver.addActionListener(a -> {
			apretoVolver();
		});
		btnVolver.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			@Override
			public void keyPressed(KeyEvent e) {

				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					apretoVolver();
				}
			}
		});
		add(btnVolver, cons);
		
		//System.out.println("Dentro del panel:" + this.getSize());
				
	}

	private void apretoVolver() {
		ventana.cambiarPanel(new MenuMesaAyudaPanel(ventana));
	}

	public void buscar() {
		
		List<TicketDTO> ticketsDTO = new ArrayList<TicketDTO>();
		Long numTicket = null, numLegajo = null;
		String nombreClasificacion = null, ultGrupo = null;
		EstadoTicket estadoActual = null;
		LocalDate fechaApertura = null, fechaUltimoGrupo = null;
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		if(!txtNumTicket.getText().trim().isEmpty()) {
			try {
				numTicket = Long.parseLong(txtNumTicket.getText().trim());
			} catch (Exception e) {
				JOptionPane.showConfirmDialog(ventana, "Solo se permiten números en el campo \"Número ticket\"", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		if(!txtNumLegajo.getText().trim().isEmpty()) {
			try {
				numLegajo = Long.parseLong(txtNumLegajo.getText().trim());
			} catch (Exception e) {
				JOptionPane.showConfirmDialog(ventana, "Solo se permiten números en el campo \"Número Legajo\"", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		if(!txtFechaApertura.getText().trim().isEmpty()) {
			try {
				fechaApertura = LocalDate.parse(txtFechaApertura.getText().trim(), format);
			} catch (Exception e) {
				JOptionPane.showConfirmDialog(ventana, "La fecha de apertura ingresada no está en el formato correcto", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				return;
			}
			if(fechaApertura.compareTo(LocalDate.now()) > 0) {
				JOptionPane.showConfirmDialog(ventana, "La fecha de apertura no puede ser futura", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		if(!txtFechaUltimoCambio.getText().trim().isEmpty()) {
			try {
				fechaUltimoGrupo = LocalDate.parse(txtFechaUltimoCambio.getText().trim(), format);
			} catch (Exception e) {
				JOptionPane.showConfirmDialog(ventana, "La fecha de último cambio de estado ingresada no está en el formato correcto", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(fechaUltimoGrupo.compareTo(LocalDate.now()) > 0) {
				JOptionPane.showConfirmDialog(ventana, "La fecha de último cambio de estado no puede ser futura", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		if(!listEstadoActual.getSelectedItem().equals("Todos los estados")) {
			for(EstadoTicket e: EstadoTicket.values()) {
				if(e.getName().equals(listEstadoActual.getSelectedItem())) {
					estadoActual = e;
				}
			}
		}
		if(!listClasificacion.getSelectedItem().equals("Todas las clasificaciones")) {
			nombreClasificacion = (String) listClasificacion.getSelectedItem();
		}
		if(!listUltimoGrupo.getSelectedItem().equals("Todos los grupos de resolución")) {
			ultGrupo = (String) listUltimoGrupo.getSelectedItem();
		}
		
		ticketsDTO = GestorTickets.consultarTicket(numTicket, numLegajo, estadoActual, nombreClasificacion, fechaApertura, fechaUltimoGrupo, ultGrupo);
		
		if(ticketsDTO.isEmpty()) {
			JOptionPane.showConfirmDialog(ventana, "No se encontraron tickets con los criterios ingresados", "No se encontraron tickets", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
			//return;
		}
		tablaResultados.setTickets(ticketsDTO);

		
	}
	
}
