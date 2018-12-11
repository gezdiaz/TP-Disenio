package interfaz.paneles.consultarTicket;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import dto.TicketDTO;
import interfaz.auxiliar.TablaRender;
import interfaz.base.VentanaBase;
import interfaz.paneles.cerrarTicket.CerrarTicketPanel;
import interfaz.paneles.derivarTicket.DerivarTicketPanel;
import logicaDeNegocios.entidades.Ticket;
import logicaDeNegocios.enumeraciones.EstadoTicket;
import logicaDeNegocios.gestores.GestorUsuarios;

public class TablaTicketsPanel extends JPanel {

	private JButton btnVer, btnCerrar, btnDerivar, btnConfigurarReporte;
	private JTable tabla;
	private TablaTicketsModelo tableModel;
	private VentanaBase ventana;
	private ConsultarTicketPanel consultarTicketPanel;

	public TablaTicketsPanel(List<TicketDTO> tickets, VentanaBase ventana, ConsultarTicketPanel consultarTicketPanel) {
		this.ventana = ventana;
		this.consultarTicketPanel = consultarTicketPanel;
		this.setLayout(new GridBagLayout());
		GridBagConstraints cons = new GridBagConstraints();
		JLabel labelAux;
		tableModel = new TablaTicketsModelo();
		tableModel.setTickets(tickets);
		tabla = new JTable(tableModel);

		btnVer = new JButton("Ver");
		btnCerrar = new JButton("Cerrar");
		btnDerivar = new JButton("Derivar");
		btnConfigurarReporte = new JButton("Configurar reporte");

		labelAux = new JLabel("Tickets seleccionados");
		labelAux.setFont(new Font(labelAux.getFont().getFontName(), labelAux.getFont().getStyle(), 18));
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(15, 15, 7, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);

		JScrollPane scroll = new JScrollPane(tabla);
		tabla.setDefaultRenderer(Object.class, new TablaRender());
		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 2;
		cons.insets = new Insets(5, 0, 5, 0);
		cons.anchor = GridBagConstraints.CENTER;
		cons.fill = GridBagConstraints.BOTH;
		scroll.setPreferredSize(new Dimension(900, 117));
		cons.weighty = 2;
		add(scroll, cons);

		btnVer.addActionListener(a -> {
			apretoVer();
		});
		btnVer.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {				
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {

				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					apretoVer();
				}
			}
		});

		btnCerrar.addActionListener(a -> {
			apretoCerrar();
		});
		btnCerrar.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {				
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {

				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					apretoCerrar();
				}
			}
		});

		btnDerivar.addActionListener(a ->{
			apretoDerivar();
		});
		btnDerivar.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {				
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {

				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					apretoDerivar();
				}
			}

		});

		btnConfigurarReporte.addActionListener(a -> {
			apretoConfigurarReporte();
		});
		btnConfigurarReporte.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {				
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {

				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					apretoConfigurarReporte();
				}
			}
		});

		JPanel panelBotones = new JPanel(new GridLayout(1, 4, 7, 7));
		panelBotones.add(btnVer);
		panelBotones.add(btnCerrar);
		panelBotones.add(btnDerivar);
		panelBotones.add(btnConfigurarReporte);
		
		cons.gridx = 0;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 2;
		cons.insets = new Insets(5, 0, 5, 0);
		cons.anchor = GridBagConstraints.CENTER;
		cons.fill = GridBagConstraints.NONE;
		cons.weighty = 2;
		add(panelBotones, cons);
		
	}

	private void apretoConfigurarReporte() {
		JOptionPane.showConfirmDialog(ventana, "Esta funcionalidad aun no esta disponible.", "Proximamente", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
	}

	private void apretoDerivar() {
		if(tabla.getSelectedRow()!=-1) {
			if(tableModel.getTickets().get(tabla.getSelectedRow()).getEstado().equals(EstadoTicket.ESPERA_OK) || tableModel.getTickets().get(tabla.getSelectedRow()).getEstado().equals(EstadoTicket.EN_MESA_DE_AYUDA)) {
				VentanaBase ventanaCerrar = new VentanaBase(ventana.getTitle(), GestorUsuarios.usuarioActual().getNombreUsuario(), new JPanel());
				ventana.setVisible(false);
				ventanaCerrar.cambiarPanel(new DerivarTicketPanel(ventanaCerrar,tableModel.getTickets().get(tabla.getSelectedRow()), ventana, consultarTicketPanel));
			}
			else {
				JOptionPane.showConfirmDialog(ventana, "Solo se pueden derivar tickets en estado Abierto o en Espera Ok.", "¡Error!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			JOptionPane.showConfirmDialog(ventana, "Debe seleccionar un ticket.", "¡Error!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
		}
	}

	private void apretoVer() {
		JOptionPane.showConfirmDialog(ventana, "Esta funcionalidad aun no esta disponible.", "Proximamente", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
	}

	private void apretoCerrar() {
		if(tabla.getSelectedRow()!=-1) {
			if(tableModel.getTickets().get(tabla.getSelectedRow()).getEstado().equals(EstadoTicket.ESPERA_OK)) {
				VentanaBase ventanaCerrar = new VentanaBase(ventana.getTitle(), GestorUsuarios.usuarioActual().getNombreUsuario(), new JPanel());
				ventana.setVisible(false);
				ventanaCerrar.cambiarPanel(new CerrarTicketPanel(ventanaCerrar,tableModel.getTickets().get(tabla.getSelectedRow()), ventana, consultarTicketPanel));
			}
			else {
				JOptionPane.showConfirmDialog(ventana, "Solo se pueden cerrar tickets en estado Espera Ok.", "¡Error!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			JOptionPane.showConfirmDialog(ventana, "Debe seleccionar un ticket.", "¡Error!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
		}

	}
	public void setTickets(List<TicketDTO> tickets) {
		tableModel.setTickets(tickets);
		tableModel.fireTableDataChanged();
	}
}
