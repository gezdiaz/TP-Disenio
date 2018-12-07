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
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;

import accesoADatos.GestorBD;
import dto.IntervencionDTO;
import interfaz.auxiliar.LimiteTexto;
import interfaz.base.VentanaBase;
import interfaz.paneles.consultarIntervencion.ConsultarIntervencionPanel;
import logicaDeNegocios.enumeraciones.EstadoIntervencion;
import logicaDeNegocios.enumeraciones.Motivos;
import logicaDeNegocios.gestores.GestorIntervenciones;

public class ActualizarEstadoIntervencionPanel extends JPanel{

	private VentanaBase ventanaActual, ventanaAnterior;
	private JTextField txtEstadoActual;
	private JComboBox<String> listClasificacion, listEstadoIntervencion, listMotivo;
	private JTextArea txtObservaciones , txtDescripcion;
	private JButton btnAceptar, btnCancelar;
	private IntervencionDTO intervencionDTO;
	private ConsultarIntervencionPanel consultarIntervencionPanel;
	private JLabel caracteresRestantes;

	public ActualizarEstadoIntervencionPanel(VentanaBase ventanaActual, IntervencionDTO intervencionDTO, VentanaBase ventanaAnterior, ConsultarIntervencionPanel consultarIntervencionPanel) {
		this.setLayout(new GridBagLayout());
		JLabel labelAux, labelMotivo;
		JScrollPane scroll;
		GridBagConstraints cons = new GridBagConstraints();
		
		this.consultarIntervencionPanel = consultarIntervencionPanel;
		this.ventanaActual = ventanaActual;
		this.ventanaAnterior = ventanaAnterior;
		this.intervencionDTO = intervencionDTO;
		

		this.txtEstadoActual = new JTextField(10);
		this.txtEstadoActual.setText(intervencionDTO.getEstadoIntervencion().getName());
		this.txtEstadoActual.setEditable(false);
		this.txtEstadoActual.setFocusable(false);
		
		this.caracteresRestantes = new JLabel("Caracteres restantes: 250");
		
		this.txtDescripcion = new JTextArea(intervencionDTO.getDescripcionTicket());
		this.txtDescripcion.setEditable(false);
		this.txtDescripcion.setFocusable(false);
		this.txtDescripcion.setLineWrap(true);
		this.txtDescripcion.setWrapStyleWord(true);

		this.listClasificacion = new JComboBox<String>();
		this.listClasificacion.addItem("Seleccione una clasificacion");
		List<String> nombresClas = GestorBD.getListClasificaciones();
		for(String n: nombresClas) {
			listClasificacion.addItem(n);
		}
		this.listClasificacion.setSelectedItem(intervencionDTO.getClasificacion());
		
		this.listMotivo = new JComboBox<String>();
		this.listMotivo.addItem(Motivos.TRABAJO_TERMINADO.getName());
		this.listMotivo.addItem(Motivos.INTERVENCION_INCORRECTA.getName());
		this.listMotivo.addItem(Motivos.PARCIALMENTE_TERMINADA.getName());

		this.listEstadoIntervencion = new JComboBox<String>();
		this.listEstadoIntervencion.addItem("Seleccione un estado");
		switch(intervencionDTO.getEstadoIntervencion()) {
		case ASIGNADO:{
			listEstadoIntervencion.addItem(EstadoIntervencion.TRABAJANDO.name());
			break;
		}
		case TRABAJANDO:{
			listEstadoIntervencion.addItem(EstadoIntervencion.EN_ESPERA.name());
			listEstadoIntervencion.addItem(EstadoIntervencion.TERMINADO.name());
			break;
		}
		case EN_ESPERA:{
			listEstadoIntervencion.addItem(EstadoIntervencion.ASIGNADO.name());
			break;
		}
		default:
			break;
		}	
		listEstadoIntervencion.setSelectedItem("Seleccione un estado");

		this.txtObservaciones = new JTextArea(4,20);
		txtObservaciones.setLineWrap(true);
		txtObservaciones.setWrapStyleWord(true);

		AbstractDocument doc = (AbstractDocument) txtObservaciones.getDocument();
		doc.setDocumentFilter(new LimiteTexto(250));
		doc.addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				caracteresRestantes.setText("Caracteres restantes: "+(250-e.getDocument().getLength()));
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				caracteresRestantes.setText("Caracteres restantes: "+(250-e.getDocument().getLength()));
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				caracteresRestantes.setText("Caracteres restantes: "+(250-e.getDocument().getLength()));
			}
		});

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

		labelAux = new JLabel("Estado actual");
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
		
		caracteresRestantes.setFont(new Font(caracteresRestantes.getFont().getFontName(), caracteresRestantes.getFont().getStyle(), 10));
		cons.gridx = 0;
		cons.gridy = 5;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 1;
		cons.insets = new Insets(10, 0, 10, 5);
		cons.anchor = GridBagConstraints.SOUTH;
		add(caracteresRestantes, cons);

		scroll = new JScrollPane(txtDescripcion);
		scroll.setPreferredSize(new Dimension(200, 70));
		txtDescripcion.setBackground(new Color(0xF2F2F2));
		cons.gridx = 1;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 2;
		cons.insets = new Insets(10, 5, 5, 5);
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
			if(listEstadoIntervencion.getSelectedItem().equals(EstadoIntervencion.TERMINADO.name())) {
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
		int res = JOptionPane.showConfirmDialog(ventanaActual, "¿Está seguro que desea cancelar la operación?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		if(res == JOptionPane.YES_OPTION) {
			ventanaActual.dispose();
			ventanaAnterior.setVisible(true);
		}
	}

	private void apretoAceptar() {
		
		if(txtObservaciones.getText().trim().isEmpty()) {
			JOptionPane.showConfirmDialog(ventanaActual, "Debe ingresar observaciones.", "¡Error!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
		}
		else {
			if(listEstadoIntervencion.getSelectedItem().equals("Seleccione un estado")) {
				JOptionPane.showConfirmDialog(ventanaActual, "Debe seleccionar un estado", "¡Error!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
			}
			else {
				EstadoIntervencion estadoseleccionado = null;
				Motivos motivo = null;
				for(EstadoIntervencion e: EstadoIntervencion.values()) {
					if(listEstadoIntervencion.getSelectedItem().equals(e.getName())) {
						estadoseleccionado = e;
					}
				}
				if(estadoseleccionado.equals(EstadoIntervencion.TERMINADO)) {
					for(Motivos m: Motivos.values()) {
						if(listMotivo.getSelectedItem().equals(m.getName())) {
							motivo = m;
						}
					}
				}
				intervencionDTO.setMotivo(motivo);
				

				intervencionDTO.setClasificacion((String)listClasificacion.getSelectedItem());
				intervencionDTO.setObservaciones(txtObservaciones.getText().trim());
				switch(GestorIntervenciones.actualizarEstadoIntervencion(intervencionDTO)) {
				case -3:{
					JOptionPane.showConfirmDialog(ventanaActual, "Error actualizando el estado del ticket.", "¡Error!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					break;
				}
				case -2:{
					JOptionPane.showConfirmDialog(ventanaActual, "Error actualizando el estado de la intervencion.", "¡Error!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					break;
				}
				case -1:{
					JOptionPane.showConfirmDialog(ventanaActual, "Error reclasificando el ticket.", "¡Error!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					break;
				}
				case 1:{
					JOptionPane.showConfirmDialog(ventanaActual, "Se actualizo el estado de la intervencion exitosamente.", "¡Exito!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
					ventanaActual.dispose();
					ventanaAnterior.setVisible(true);
					consultarIntervencionPanel.apretoBuscar();
					break;
				}
				}
			}

		}
	}
}
