package interfaz.paneles;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelPrueba extends JPanel {

	public PanelPrueba() {
		super();
		
		JLabel label = new JLabel("Texto");
		this.setLayout(new GridBagLayout());
		GridBagConstraints cons = new GridBagConstraints();
		
		cons.gridx=0;
		cons.gridy = 0;
		
		this.add(label, cons);
		
		
		
	}
	
	
}
