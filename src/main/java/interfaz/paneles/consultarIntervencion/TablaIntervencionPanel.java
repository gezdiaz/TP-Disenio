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

public class TablaIntervencionPanel extends JPanel{

	private JButton btnModificarEstado, btnModificarComentario;
	private JTable tabla;
	private TablaIntervencionesModelo tableModel;
	
	public TablaIntervencionPanel(List<IntervencionDTO> intervenciones, VentanaBase ventana) {
		this.setLayout(new GridBagLayout());
		GridBagConstraints cons = new GridBagConstraints();
		JLabel labelAux;

		tableModel = new TablaIntervencionesModelo();
		tableModel.setIntervenciones(intervenciones);
		tabla = new JTable(tableModel);
		
		btnModificarEstado = new JButton("Modificar Estado");
		btnModificarComentario = new JButton("Modificar Comentario");
		
		labelAux = new JLabel("Intervenciones seleccionados");
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
		
		btnModificarEstado.addActionListener(a -> {
		});
		cons.gridx = 1;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(15, 5, 5, 5);
		cons.anchor = GridBagConstraints.CENTER;
		add(btnModificarEstado, cons);
		
		btnModificarComentario.addActionListener(a ->{
		});
		cons.gridx = 2;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(15, 5, 5, 5);
		cons.anchor = GridBagConstraints.CENTER;
		add(btnModificarComentario, cons);
		
		System.out.println("Termino de hacer la tabla gg");
		
	}
	
}
