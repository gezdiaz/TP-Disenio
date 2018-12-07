package interfaz.paneles.consultarIntervencion;

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

import dto.IntervencionDTO;
import interfaz.auxiliar.TablaRender;
import interfaz.base.VentanaBase;
import interfaz.paneles.actualizarEstadoIntervencion.ActualizarEstadoIntervencionPanel;
import logicaDeNegocios.enumeraciones.EstadoIntervencion;
import logicaDeNegocios.gestores.GestorUsuarios;

public class TablaIntervencionPanel extends JPanel{

	private JButton btnModificarEstado, btnModificarComentario;
	private JTable tabla;
	private TablaIntervencionesModelo tableModel;
	VentanaBase ventana;
	ConsultarIntervencionPanel consultarIntervencionPanel;
	
	public TablaIntervencionPanel(List<IntervencionDTO> intervenciones, VentanaBase ventana, ConsultarIntervencionPanel consultarIntervencionPanel) {
		this.consultarIntervencionPanel = consultarIntervencionPanel;
		this.ventana = ventana;
		this.setLayout(new GridBagLayout());
		GridBagConstraints cons = new GridBagConstraints();
		JLabel labelAux;

		tableModel = new TablaIntervencionesModelo();
		tableModel.setIntervenciones(intervenciones);
		tabla = new JTable(tableModel);
		
		btnModificarEstado = new JButton("Modificar Estado");
		btnModificarComentario = new JButton("Modificar Comentario");
		
		labelAux = new JLabel("Intervenciones encontradas:");
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
		cons.gridwidth = 6;
		cons.insets = new Insets(5, 0, 5, 0);
		cons.anchor = GridBagConstraints.CENTER;
		cons.fill = GridBagConstraints.BOTH;
		scroll.setPreferredSize(new Dimension(800, 120));
		cons.weighty = 2;
		add(scroll, cons);
		
		cons.gridx = 1;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(15, 5, 5, 5);
		cons.anchor = GridBagConstraints.EAST;
		cons.fill = GridBagConstraints.NONE;
		btnModificarEstado.addActionListener(a -> {
			apretoModificarEstado();
		});
		btnModificarEstado.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			@Override
			public void keyPressed(KeyEvent e) {

				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					apretoModificarEstado();
				}
			}
		});
		add(btnModificarEstado, cons);
		
		cons.gridx = 3;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(15, 5, 5, 5);
		cons.anchor = GridBagConstraints.WEST;
		cons.fill = GridBagConstraints.NONE;
		btnModificarComentario.addActionListener(a ->{
			apretoModificarComentario();
		});
		btnModificarComentario.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			@Override
			public void keyPressed(KeyEvent e) {

				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					apretoModificarComentario();
				}
			}
		});
		add(btnModificarComentario, cons);
		
	}

	private void apretoModificarComentario() {
		if(tabla.getSelectedRow()!=-1) {
			JOptionPane.showConfirmDialog(ventana, "Esta funcionalidad no esta disponible.", "Proximamente", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);;
		}
		else {
			JOptionPane.showConfirmDialog(ventana, "Debe seleccionar una intervencion.", "Error!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
		}
	}

	private void apretoModificarEstado() {
		if(tabla.getSelectedRow()!=-1) {
			if(!tableModel.getIntervenciones().get(tabla.getSelectedRow()).getEstadoIntervencion().equals(EstadoIntervencion.TERMINADO)) {
				VentanaBase ventanaModificar = new VentanaBase(ventana.getTitle(), GestorUsuarios.usuarioActual().getNombreUsuario(), new JPanel());
				ventana.setVisible(false);
				ventanaModificar.cambiarPanel(new ActualizarEstadoIntervencionPanel(ventanaModificar, tableModel.getIntervenciones().get(tabla.getSelectedRow()), ventana, consultarIntervencionPanel));
			}
			else {
				JOptionPane.showConfirmDialog(ventana, "No se puede modificar el estado de una intervencion terminada.", "¡Error!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			JOptionPane.showConfirmDialog(ventana, "Debe seleccionar una intervencion.", "¡Error!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void setIntervenciones(List<IntervencionDTO> intervenciones) {
		tableModel.setIntervenciones(intervenciones);
		tableModel.fireTableDataChanged();
	}
	
}
