package interfaz.paneles.consultarTicket;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dto.HistorialTicketDTO;
import dto.TicketDTO;
import interfaz.auxiliar.TablaRender;
import logicaDeNegocios.entidades.Empleado;
import logicaDeNegocios.gestores.GestorTickets;
import logicaDeNegocios.gestores.SistemaPersonal;

public class VerTicketPanel extends JPanel {

	private TicketDTO ticketDTO;
	private JButton btnVolver, btnVerObservaciones;
	private JTable tablaHistorial;
	private TablaHistorialModelo tableModel;
	
	public VerTicketPanel(TicketDTO ticketDTO) {
		
		this.ticketDTO = ticketDTO;
		
		List<HistorialTicketDTO> historialDTO = GestorTickets.getHistorialTicket(ticketDTO);
		
		tableModel = new TablaHistorialModelo(historialDTO);
		tableModel.setHistorial(new ArrayList<HistorialTicketDTO>());
		tablaHistorial = new JTable(tableModel);
		btnVerObservaciones = new JButton("Ver Observaciones");
		btnVolver = new JButton("Volver");
		
		setLayout(new GridBagLayout());
		GridBagConstraints cons = new GridBagConstraints();
		Empleado empleado = SistemaPersonal.getEmpleado(ticketDTO.getNumLegajo());
		
		JLabel labelAux = new JLabel("Información del ticket");
		labelAux.setFont(new Font(labelAux.getFont().getFontName(), labelAux.getFont().getStyle(), 20));
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(20, 20, 10, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		labelAux = new JLabel("\uD83D\uDF84   Empleado: ");
		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(10, 40, 10, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		labelAux = new JLabel("\uD83D\uDF84   Legajo: "+empleado.getNumLegajo());
		cons.gridx = 0;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(2, 65, 2, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		labelAux = new JLabel("\uD83D\uDF84   Apellido: "+empleado.getApellido());
		cons.gridx = 0;
		cons.gridy = 3;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(2, 65, 2, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		labelAux = new JLabel("\uD83D\uDF84   Nombre: "+empleado.getNombre());
		cons.gridx = 0;
		cons.gridy = 4;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(2, 65, 2, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		labelAux = new JLabel("\uD83D\uDF84   Tel. Interno: "+empleado.getTelefonoInterno());
		cons.gridx = 0;
		cons.gridy = 5;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(2, 65, 2, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		labelAux = new JLabel("\uD83D\uDF84   Tel. Directo: "+empleado.getTelefonoDirecto());
		cons.gridx = 0;
		cons.gridy = 6;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(2, 65, 2, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		labelAux = new JLabel("\uD83D\uDF84   Cargo: "+empleado.getCargo());
		cons.gridx = 0;
		cons.gridy = 7;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(2, 65, 2, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		labelAux = new JLabel("\uD83D\uDF84   Ubicación: "+empleado.getDireccion());
		cons.gridx = 0;
		cons.gridy = 8;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(2, 65, 2, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		labelAux = new JLabel("\uD83D\uDF84   Detalle histórico de los cambios de estado:");
		cons.gridx = 0;
		cons.gridy = 9;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(10, 40, 10, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		JScrollPane scroll = new JScrollPane(tablaHistorial);
		tablaHistorial.setDefaultRenderer(Object.class, new TablaRender());
		scroll.setPreferredSize(new Dimension(600, 150));
		cons.gridx = 0;
		cons.gridy = 10;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(10, 40, 10, 5);
		cons.anchor = GridBagConstraints.CENTER;
		add(scroll, cons);
		
		btnVerObservaciones.addActionListener(a -> {
			apretoVerObservaciones();
		});
		btnVerObservaciones.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			@Override
			public void keyPressed(KeyEvent e) {

				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					apretoVerObservaciones();
				}
			}
		});
		cons.gridx = 0;
		cons.gridy = 11;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(10, 5, 10, 5);
		cons.anchor = GridBagConstraints.CENTER;
		add(btnVerObservaciones, cons);
		
		
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
		cons.gridx = 0;
		cons.gridy = 12;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(10, 5, 10, 5);
		cons.anchor = GridBagConstraints.CENTER;
		add(btnVolver, cons);
		
		
		
	}
	
	private void apretoVerObservaciones() {
		
	}
	
	private void apretoVolver() {
		
	}
	
}
