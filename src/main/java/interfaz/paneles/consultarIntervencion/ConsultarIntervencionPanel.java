package interfaz.paneles.consultarIntervencion;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.color.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import accesoADatos.GestorBD;
import dto.IntervencionDTO;
import dto.TicketDTO;
import interfaz.base.VentanaBase;
import interfaz.paneles.consultarTicket.TablaTicketsPanel;
import interfaz.principal.MenuGrupoDeResolucionPanel;
import interfaz.principal.MenuMesaAyudaPanel;
import logicaDeNegocios.enumeraciones.EstadoIntervencion;
import logicaDeNegocios.enumeraciones.EstadoTicket;
import logicaDeNegocios.gestores.GestorIntervenciones;
import logicaDeNegocios.gestores.GestorTickets;

public class ConsultarIntervencionPanel extends JPanel {

	private VentanaBase ventana;
	private JTextField txtFechaDesde, txtFechaHasta, txtNumTicket, txtNumLegajo;
	private JComboBox<String> listEstado;
	private JButton btnBuscar, btnVolver;
	private TablaIntervencionPanel tablaResultados;
	
	public ConsultarIntervencionPanel(VentanaBase ventana) {
		this.ventana=ventana;
		this.setLayout(new GridBagLayout());
		JLabel labelAux;
		GridBagConstraints cons = new GridBagConstraints();
		
		tablaResultados = new TablaIntervencionPanel(new ArrayList<IntervencionDTO>(), this.ventana);
		
		listEstado = new JComboBox<String>();
		listEstado.addItem("Todos los estados");
		for(EstadoIntervencion e: EstadoIntervencion.values()) {
			listEstado.addItem(e.getName());
		}
		listEstado.setSelectedItem(EstadoIntervencion.ASIGNADO.getName());
		
		labelAux = new JLabel("Consultar intervenciones asignadas");
		labelAux.setFont(new Font(labelAux.getFont().getFontName(), labelAux.getFont().getStyle(), 20));
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridheight = 1;
		cons.gridwidth = 3;
		cons.insets = new Insets(20, 20, 15, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		labelAux = new JLabel("Estado");
		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(10, 10, 5, 0);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		labelAux = new JLabel("Fecha desde");
		cons.gridx = 2;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(5, 10, 5, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		labelAux = new JLabel("Fecha hasta");
		cons.gridx = 4;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(5, 10, 5, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		labelAux = new JLabel("Número de ticket");
		cons.gridx = 0;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(5, 10, 5, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		labelAux = new JLabel("Número de legajo");
		cons.gridx = 2;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(5, 10, 20, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		cons.gridx = 1;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(10, 5, 5, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(listEstado, cons);
		
		txtFechaDesde = new JTextField(10);
		cons.gridx = 3;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(5, 5, 5, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(txtFechaDesde, cons);
		
		txtFechaHasta = new JTextField(10);
		cons.gridx = 5;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(5, 5, 5, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(txtFechaHasta, cons);
		
		txtNumTicket = new JTextField(12);
		cons.gridx = 1;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(5, 5, 5, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(txtNumTicket, cons);
		
		txtNumLegajo = new JTextField(12);
		cons.gridx = 3;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(5, 5, 20, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(txtNumLegajo, cons);
		
		btnBuscar = new JButton("Buscar");
		cons.gridx = 4;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 3;
		cons.insets = new Insets(5, 5, 20, 5);
		cons.anchor = GridBagConstraints.CENTER;
		btnBuscar.addActionListener(a -> {
			apretoBuscar();
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
					apretoBuscar();
				}
			}
		});
		add(btnBuscar, cons);
		
		labelAux = new JLabel("dd/mm/aaaa");
		labelAux.setFont(new Font(labelAux.getFont().getFontName(), labelAux.getFont().getStyle(), 10));
		cons.gridx = 6;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(5, 0, 5, 10);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		/*labelAux = new JLabel("dd/mm/aaaa");
		labelAux.setFont(new Font(labelAux.getFont().getFontName(), labelAux.getFont().getStyle(), 10));
		cons.gridy = 3;
		add(labelAux, cons);*/
		
		cons.gridx = 0;
		cons.gridy = 3;
		cons.gridheight = 1;
		cons.gridwidth = 7;
		cons.insets = new Insets(5, 5, 5, 5);
		cons.anchor = GridBagConstraints.CENTER;
		cons.fill = GridBagConstraints.BOTH;
		cons.weightx = 2;
		//tablaResultados.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(tablaResultados, cons);
		
		btnVolver = new JButton("Volver");
		cons.gridx = 0;
		cons.gridy = 8;
		cons.gridheight = 1;
		cons.gridwidth = 7;
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
			
	}

	private void apretoBuscar() {
		// TODO Auto-generated method stub
		//JOptionPane.showConfirmDialog(ventana, "Esta funcionalidad aun no esta disponible", "Proximamente", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
		List<IntervencionDTO> intervencionesDTO = new ArrayList<IntervencionDTO>();
		Long numTicket=null, numLegajo=null;
		EstadoIntervencion estadoActual=null;
		LocalDate fechaDesde = null, fechaHasta = null;
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
		if(!txtFechaDesde.getText().trim().isEmpty()) {
			try {
				fechaDesde = LocalDate.parse(txtFechaDesde.getText().trim(), format);
			} catch (Exception e) {
				JOptionPane.showConfirmDialog(ventana, "La fecha de apertura ingresada no está en el formato correcto", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				return;
			}
			if(fechaDesde.compareTo(LocalDate.now()) > 0) {
				JOptionPane.showConfirmDialog(ventana, "La fecha de apertura no puede ser futura", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		if(!txtFechaHasta.getText().trim().isEmpty()) {
			try {
				fechaHasta = LocalDate.parse(txtFechaHasta.getText().trim(), format);
			} catch (Exception e) {
				JOptionPane.showConfirmDialog(ventana, "La fecha de último cambio de estado ingresada no está en el formato correcto", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(fechaHasta.compareTo(LocalDate.now()) > 0) {
				JOptionPane.showConfirmDialog(ventana, "La fecha de último cambio de estado no puede ser futura", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		if(!listEstado.getSelectedItem().equals("Todos los estados")) {
			for(EstadoIntervencion e: EstadoIntervencion.values()) {
				if(e.getName().equals(listEstado.getSelectedItem())) {
					estadoActual = e;
				}
			}
		}
		
		intervencionesDTO = GestorIntervenciones.consultarIntervencion(estadoActual,fechaDesde,fechaHasta,numTicket,numLegajo);
		
		if(intervencionesDTO.isEmpty()) {
			JOptionPane.showConfirmDialog(ventana, "No se encontraron tickets con los criterios ingresados", "No se encontraron tickets", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
			//return;
		}
		tablaResultados.setIntervenciones(intervencionesDTO);
	
	}

	private void apretoVolver() {
		ventana.cambiarPanel(new MenuGrupoDeResolucionPanel(ventana));
	}

}
