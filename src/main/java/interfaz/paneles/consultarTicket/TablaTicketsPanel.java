package interfaz.paneles.consultarTicket;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
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
	
	public TablaTicketsPanel(List<TicketDTO> tickets, VentanaBase ventana) {
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
		add(scroll, cons);
		
		cons.gridx = 1;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(15, 5, 5, 5);
		cons.anchor = GridBagConstraints.CENTER;
		cons.fill = GridBagConstraints.NONE;
		add(btnVer, cons);
		
		btnCerrar.addActionListener(a -> {
			//TODO que envie el ticket seleccionado
			VentanaBase ventanaCerrar = new VentanaBase(ventana.getTitle(), "Usuario de Prueba", new JPanel());
			ventana.setVisible(false);
			ventanaCerrar.cambiarPanel(new CerrarTicketPanel(ventanaCerrar, new TicketDTO(123456L), ventana));
		});
		cons.gridx = 2;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(15, 5, 5, 5);
		cons.anchor = GridBagConstraints.CENTER;
		add(btnCerrar, cons);
		
		btnDerivar.addActionListener(a ->{
			//TODO que envie el ticket seleccionado
			VentanaBase ventanaCerrar = new VentanaBase(ventana.getTitle(), "Usuario de Prueba", new JPanel());
			ventana.setVisible(false);
			ventanaCerrar.cambiarPanel(new DerivarTicketPanel(ventanaCerrar, new TicketDTO(123456L), ventana));
		});
		cons.gridx = 3;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(15, 5, 5, 5);
		cons.anchor = GridBagConstraints.CENTER;
		add(btnDerivar, cons);
		
		cons.gridx = 4;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(15, 5, 5, 5);
		cons.anchor = GridBagConstraints.CENTER;
		add(btnConfigurarReporte, cons);
		
	}
	
}
