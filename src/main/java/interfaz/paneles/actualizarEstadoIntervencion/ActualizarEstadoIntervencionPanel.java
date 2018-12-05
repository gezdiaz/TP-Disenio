package interfaz.paneles.actualizarEstadoIntervencion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import accesoADatos.GestorBD;
import dto.IntervencionDTO;
import dto.TicketDTO;
import interfaz.base.VentanaBase;
import logicaDeNegocios.enumeraciones.EstadoIntervencion;
import logicaDeNegocios.enumeraciones.EstadoTicket;
import logicaDeNegocios.enumeraciones.Motivos;
import logicaDeNegocios.gestores.GestorIntervenciones;

public class ActualizarEstadoIntervencionPanel extends JPanel{

	private VentanaBase ventanaActual, ventanaAnterior;
	private JTextField txtEstadoActual;
	private JComboBox<String> listClasificacion, listEstadoIntervencion, listMotivo;
	private JTextArea txtObservaciones , txtDescripcion;
	private JButton btnAceptar, btnCancelar;
	private IntervencionDTO intervencionDTO;

	public ActualizarEstadoIntervencionPanel(VentanaBase ventanaActual, IntervencionDTO intervencionDTO, VentanaBase ventanaAnterior) {
		this.setLayout(new GridBagLayout());
		JLabel labelAux, labelMotivo;
		JScrollPane scroll;
		GridBagConstraints cons = new GridBagConstraints();

		this.ventanaActual = ventanaActual;
		this.ventanaAnterior = ventanaAnterior;
		this.intervencionDTO = intervencionDTO;
		
		this.txtEstadoActual = new JTextField(10/*this.intervencionDTO.getEstado().name()*/);
		this.txtEstadoActual.setText(intervencionDTO.getEstadoIntervencion().name());
		this.txtEstadoActual.setEditable(false);
		this.txtEstadoActual.setFocusable(false);
		
		this.txtDescripcion = new JTextArea(intervencionDTO.getDescripcionTicket());
		this.txtDescripcion.setEditable(false);
		this.txtDescripcion.setFocusable(false);

		this.listClasificacion = new JComboBox<String>();
		this.listClasificacion.addItem("Seleccione una clasificacion");
		List<String> nombresClas = GestorBD.getListClasificaciones();
		for(String n: nombresClas) {
			listClasificacion.addItem(n);
		}
		this.listClasificacion.setSelectedItem(intervencionDTO.getClasificacion());
		
		this.listMotivo = new JComboBox<String>();
		this.listMotivo.addItem(Motivos.Trabajo_Terminado.getName());
		this.listMotivo.addItem(Motivos.Intervencion_Incorrecta.getName());
		this.listMotivo.addItem(Motivos.Parcialmente_Terminada.getName());

		this.listEstadoIntervencion = new JComboBox<String>();
		this.listEstadoIntervencion.addItem("Seleccione un estado");
		switch(intervencionDTO.getEstadoIntervencion()) {
		case Asignado:{
			listEstadoIntervencion.addItem(EstadoIntervencion.Trabajando.name());
			break;
		}
		case Trabajando:{
			listEstadoIntervencion.addItem(EstadoIntervencion.EnEspera.name());
			listEstadoIntervencion.addItem(EstadoIntervencion.Terminado.name());
			break;
		}
		case EnEspera:{
			listEstadoIntervencion.addItem(EstadoIntervencion.Asignado.name());
			break;
		}
		default:
			break;
		}		

		this.txtObservaciones = new JTextArea(4,20);
		txtObservaciones.setLineWrap(true);
		txtObservaciones.setWrapStyleWord(true);
		//		txtObservaciones.setPreferredSize(new Dimension(200, 70));

		btnAceptar = new JButton("Aceptar");
		btnCancelar = new JButton("Cancelar");

		this.ventanaActual.setVisible(true);

		labelAux = new JLabel("Actualizar estado intervencion");
		labelAux.setFont(new Font(labelAux.getFont().getFontName(), labelAux.getFont().getStyle(), 20));
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridheight = 1;
		cons.gridwidth = 2;
		cons.insets = new Insets(30, 60, 15, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);

		labelAux = new JLabel("Estado actual"/*"123456"*//*ticketDTO.getNumTicket()*/);
		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 1;
		cons.insets = new Insets(10, 50, 10, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);

		labelAux = new JLabel("Descripcion");
		cons.gridx = 0;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 1;
		cons.insets = new Insets(10, 50, 10, 5);
		cons.anchor = GridBagConstraints.NORTHWEST;
		add(labelAux, cons);

		labelAux = new JLabel("Nuevo estado*");
		cons.gridx = 0;
		cons.gridy = 3;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 1;
		cons.insets = new Insets(10, 50, 10, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		labelMotivo = new JLabel("Motivo");
		cons.gridx = 2;
		cons.gridy = 3;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 1;
		cons.insets = new Insets(10, 0, 10, 5);
		cons.anchor = GridBagConstraints.WEST;
		/*if(!listEstadoIntervencion.getSelectedItem().equals(EstadoIntervencion.Terminado)) {
			labelMotivo.setVisible(false);
		}*/
		add(labelMotivo, cons);

		labelAux = new JLabel("Clasificacion*");
		cons.gridx = 0;
		cons.gridy = 4;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 1;
		cons.insets = new Insets(10, 50, 10, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);

		labelAux = new JLabel("Observaciones*");
		cons.gridx = 0;
		cons.gridy = 5;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 1;
		cons.insets = new Insets(10, 50, 10, 5);
		cons.anchor = GridBagConstraints.NORTHWEST;
		add(labelAux, cons);

		labelAux = new JLabel("*Campo Obligatorio");
		labelAux.setFont(new Font(labelAux.getFont().getFontName(), labelAux.getFont().getStyle(), 8));
		cons.gridx = 0;
		cons.gridy = 6;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 1;
		cons.insets = new Insets(5, 60, 5, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);

		cons.gridx = 1;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(10, 5, 5, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(txtEstadoActual, cons);

		scroll = new JScrollPane(txtDescripcion);
		txtDescripcion.setPreferredSize(new Dimension(200, 70));
		txtDescripcion.setBackground(new Color(0xF2F2F2));
		cons.gridx = 1;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 2;
		cons.insets = new Insets(10, 5, 5, 5);
		//cons.fill = GridBagConstraints.BOTH;
		cons.anchor = GridBagConstraints.WEST;
		add(scroll, cons);

		cons.gridx = 1;
		cons.gridy = 3;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(10, 5, 5, 5);
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.WEST;
		this.listEstadoIntervencion.addActionListener(e->{
			if(listEstadoIntervencion.getSelectedItem().equals(EstadoIntervencion.Terminado.name())) {
				this.listMotivo.setEnabled(true);
			}
			else {
				this.listMotivo.setEnabled(false);
			}
		});
		add(listEstadoIntervencion, cons);
		
		cons.gridx = 2;
		cons.gridy = 3;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(10, 5, 5, 5);
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.EAST;
		this.listMotivo.setEnabled(false);
		add(listMotivo, cons);

		cons.gridx = 1;
		cons.gridy = 4;
		cons.gridheight = 1;
		cons.gridwidth = 3;
		cons.insets = new Insets(10, 5, 5, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(listClasificacion, cons);

		scroll = new JScrollPane(txtObservaciones);
		cons.gridx = 1;
		cons.gridy = 5;
		cons.gridheight = 1;
		cons.gridwidth = 2;
		cons.insets = new Insets(10, 5, 5, 5);
		//cons.fill = GridBagConstraints.BOTH;
		cons.anchor = GridBagConstraints.WEST;
		txtObservaciones.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
		txtObservaciones.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
		scroll.setPreferredSize(new Dimension(200, 70));
		add(scroll, cons);

		//Botones
		cons.gridx = 0;
		cons.gridy = 7;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 1;
		cons.insets = new Insets(20, 40, 40, 10);
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.EAST;
		btnAceptar.addActionListener(a -> {
			apretoAceptar();		
		});
		btnAceptar.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {				
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {

				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					apretoAceptar();
				}
			}
		});
		add(btnAceptar, cons);

		btnCancelar.addActionListener(a -> {
			apretoCancelar();
		});
		btnCancelar.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {				
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {

				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					apretoCancelar();
				}
			}
		});
		cons.gridx = 2;
		cons.gridy = 7;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 1;
		cons.insets = new Insets(20, 10, 40, 25);
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.WEST;
		add(btnCancelar, cons);
	}

	private void apretoCancelar() {
		int res = JOptionPane.showConfirmDialog(ventanaActual, "Está seguro que desea cancelar la operación", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		if(res == JOptionPane.YES_OPTION) {
			ventanaActual.dispose();
			ventanaAnterior.setVisible(true);
		}
	}

	private void apretoAceptar() {
		
		if(txtObservaciones.getText().trim().isEmpty()) {
			JOptionPane.showConfirmDialog(ventanaActual, "Debe ingresar observaciones", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
		}
		else {
			intervencionDTO.setObservaciones(txtObservaciones.getText());
			for(EstadoIntervencion e: EstadoIntervencion.values()) {
				if(listEstadoIntervencion.getSelectedItem().equals(e.name())) {
					intervencionDTO.setEstadoIntervencion(e);
				}
			}
			if(intervencionDTO.getEstadoIntervencion().equals(EstadoIntervencion.Terminado)) {
				for(Motivos m : Motivos.values()) {
					if(m.name().equals(listMotivo.getSelectedItem())) {
						intervencionDTO.setMotivo(m);
					}
				}
			}
			else {
				intervencionDTO.setMotivo(null);
			}
			intervencionDTO.setClasificacion((String)listClasificacion.getSelectedItem());
			switch(GestorIntervenciones.actualizarEstadoIntervencion(intervencionDTO)) {
			case -3:{
				JOptionPane.showConfirmDialog(ventanaActual, "Error actualizando el estado del ticket", "¡Error!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				break;
			}
			case -2:{
				JOptionPane.showConfirmDialog(ventanaActual, "Error actualizando el estado de la intervencion", "¡Error!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				break;
			}
			case -1:{
				JOptionPane.showConfirmDialog(ventanaActual, "Error reclasificando el ticket", "¡Error!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				break;
			}
			case 1:{
				JOptionPane.showConfirmDialog(ventanaActual, "Se actualizo el estado de la intervencion exitosamente", "¡Exito!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
				break;
			}
			}
			ventanaActual.dispose();
			ventanaAnterior.setVisible(true);
		}
	}
}
