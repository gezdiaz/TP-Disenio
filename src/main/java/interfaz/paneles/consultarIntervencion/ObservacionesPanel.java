package interfaz.paneles.consultarIntervencion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ObservacionesPanel extends JPanel {
	
	private JTextArea txtObservaciones;
	private JButton btnAceptar;
	private JDialog dialogo;

	public ObservacionesPanel(JDialog dialogo, String observaciones) {
		
		setLayout(new GridBagLayout());
		
		this.dialogo = dialogo;
		
		txtObservaciones = new JTextArea(observaciones);
		txtObservaciones.setEditable(false);
		txtObservaciones.setLineWrap(true);
		txtObservaciones.setWrapStyleWord(true);
		txtObservaciones.setFocusable(false);
		
		btnAceptar = new JButton("Aceptar");
		
		GridBagConstraints cons = new GridBagConstraints();
		
		JScrollPane scroll = new JScrollPane(txtObservaciones);
		scroll.setPreferredSize(new Dimension(300, 140));
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 2;
		cons.insets = new Insets(20, 20, 20, 20);
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.CENTER;
		add(scroll, cons);
		
		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 2;
		cons.insets = new Insets(10, 10, 10, 10);
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.CENTER;
		btnAceptar.addActionListener(a -> {
			this.dialogo.dispose();
		});
		add(btnAceptar, cons);
		
		
	}
	
	

}
