package interfaz.paneles.consultarIntervencion;

import java.awt.Dimension;
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

import dto.IntervencionDTO;
import dto.TicketDTO;
import interfaz.base.VentanaBase;
import interfaz.paneles.cerrarTicket.CerrarTicketPanel;
import interfaz.paneles.consultarTicket.TablaTicketsModelo;
import interfaz.paneles.derivarTicket.DerivarTicketPanel;

public class TablaIntervencionPanel {


	private JButton btnVer, btnCerrar, btnDerivar, btnConfigurarReporte;
	private JTable tabla;
	private TablaIntervencionesModelo tableModel;
	
	public TablaIntervencionPanel(List<IntervencionDTO> intervenciones, VentanaBase ventana) {
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints cons = new GridBagConstraints();
		JLabel labelAux;
		tableModel = new TablaTicketsModelo();
		tableModel.setIntervenciones(intervenciones);
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
		scroll.setPreferredSize(new Dimension(593, 117));
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
		add(btnVer, cons);
		
		btnCerrar.addActionListener(a -> {

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
