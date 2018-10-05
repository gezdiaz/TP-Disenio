package interfaz.base;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Insets;

import com.sun.scenario.effect.impl.state.GaussianRenderState;


public class PanelBarraUsuario extends JPanel{
	
	
	JLabel nombreUsuario;
	JLabel laLlamita;
	
	
	public PanelBarraUsuario(String nombreUsuario) {
		super();
		this.nombreUsuario = new JLabel(nombreUsuario);
		this.laLlamita = new JLabel("La Llamita");
		this.construir();
	}


	private void construir() {
		GridBagConstraints cons = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		laLlamita.setHorizontalAlignment(JLabel.CENTER);
		nombreUsuario.setHorizontalAlignment(JLabel.CENTER);
		
		cons.insets = new Insets(15, 25, 15, 25);
		cons.gridx=0;
		cons.gridy=0;
		cons.fill=GridBagConstraints.HORIZONTAL;
		cons.anchor=GridBagConstraints.CENTER;
		this.add(laLlamita, cons);
		
		cons.gridx = 1;
		cons.gridy=0;
		cons.fill=GridBagConstraints.HORIZONTAL;
		cons.weightx=1;
		JLabel lab = new JLabel("");
		this.add(lab, cons);
		
		
		cons.insets = new Insets(15, 15, 15, 25);
		cons.gridx=2;
		cons.gridy=0;
		cons.fill=GridBagConstraints.HORIZONTAL;
		cons.weightx=0;
		cons.weighty=0;
		this.add(nombreUsuario, cons);
		
		this.setBackground(new Color(147,245,49));
		
	}
	

}
