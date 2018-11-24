package interfaz.paneles.consultarTicket;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import interfaz.base.VentanaBase;
import interfaz.paneles.cerrarTicket.CerrarTicketPanel;
import interfaz.paneles.derivarTicket.DerivarTicketPanel;
import logicaDeNegocios.entidades.Ticket;

public class TablaTicketsPanel extends JPanel {

	private JButton btnVer, btnCerrar, btnDerivar, btnConfigurarReporte;
	private JTable tabla;
	private TablaTicketsModelo tableModel;
	VentanaBase ventana;
	
	public TablaTicketsPanel(List<TicketDTO> tickets, VentanaBase ventana) {
		this.ventana = ventana;
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
		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 6;
		cons.insets = new Insets(5, 0, 5, 0);
		cons.anchor = GridBagConstraints.CENTER;
		cons.fill = GridBagConstraints.BOTH;
		scroll.setPreferredSize(new Dimension(850, 117));
		cons.weighty = 2;
		add(scroll, cons);
		
		cons.gridx = 1;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(15, 5, 5, 5);
		cons.anchor = GridBagConstraints.CENTER;
		cons.fill = GridBagConstraints.NONE;
		cons.weighty = 1;
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
		add(btnVer, cons);
		
		
		cons.gridx = 2;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(15, 5, 5, 5);
		cons.anchor = GridBagConstraints.CENTER;
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
		add(btnCerrar, cons);
		
		cons.gridx = 3;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(15, 5, 5, 5);
		cons.anchor = GridBagConstraints.CENTER;
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
		add(btnDerivar, cons);
		
		cons.gridx = 4;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(15, 5, 5, 5);
		cons.anchor = GridBagConstraints.CENTER;
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
		add(btnConfigurarReporte, cons);
		
	}

	private void apretoConfigurarReporte() {
		// TODO Configurar Reporte
		JOptionPane.showConfirmDialog(ventana, "Esta funcionalidad aun no esta disponible", "Proximamente", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
	}

	private void apretoDerivar() {
		//TODO que envie el ticket seleccionado
		VentanaBase ventanaCerrar = new VentanaBase(ventana.getTitle(), "Usuario de Prueba", new JPanel());
		ventana.setVisible(false);
		ventanaCerrar.cambiarPanel(new DerivarTicketPanel(ventanaCerrar, new TicketDTO(123456L), ventana));
	}

	private void apretoVer() {
		// TODO Auto-generated method stub
		JOptionPane.showConfirmDialog(ventana, "Esta funcionalidad aun no esta disponible", "Proximamente", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
	}

	private void apretoCerrar() {
		//TODO que envie el ticket seleccionado
		VentanaBase ventanaCerrar = new VentanaBase(ventana.getTitle(), "Usuario de Prueba", new JPanel());
		ventana.setVisible(false);
		ventanaCerrar.cambiarPanel(new CerrarTicketPanel(ventanaCerrar, new TicketDTO(123456L), ventana));
	}
	
	
}
