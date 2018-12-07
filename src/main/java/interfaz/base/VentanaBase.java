package interfaz.base;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class VentanaBase extends JFrame {


	PanelBarraUsuario barraUsuario;
	JPanel subPanel;
	JPanel panel;
	CardLayout cartas;


	public VentanaBase(String title, String nombreUsuario, JPanel subPanel){
		super(title);
		this.subPanel = subPanel;
		this.cartas = new CardLayout();
		JPanel contentPane = new JPanel(new BorderLayout());
		panel = new JPanel(cartas);
		barraUsuario = new PanelBarraUsuario(nombreUsuario);
		this.setResizable(false);


		panel.add(this.subPanel, "Inicio");
		cartas.show(panel, "Inicio");
		

		contentPane.add(barraUsuario, BorderLayout.NORTH);
		contentPane.add(panel, BorderLayout.CENTER);

		ImageIcon img = new ImageIcon("icono.png");
		setIconImage(img.getImage());

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setContentPane(contentPane);
		this.setLocationRelativeTo(null);

	}

	public void cambiarPanel(JPanel nuevoPanel) {
		int diffH = (int)this.getSize().getHeight()-(int)subPanel.getSize().getHeight();
		int diffW = (int)this.getSize().getWidth()-(int)subPanel.getSize().getWidth();

		this.subPanel = nuevoPanel;

		panel.add(nuevoPanel, "nuevoPanel");

		cartas.show(panel, "nuevoPanel");

		this.setMaximumSize(new Dimension((int)subPanel.getPreferredSize().getWidth()+diffW, (int)subPanel.getPreferredSize().getHeight()+diffH));
		
		this.revalidate();
		this.repaint();
		this.pack();
		this.setLocationRelativeTo(null);
		this.requestFocus();
	}
	

	@Override
    public void paint(Graphics g) {
        Dimension d = getSize();
        Dimension m = getMaximumSize();
        boolean resize = d.width > m.width || d.height > m.height;
        d.width = Math.min(m.width, d.width);
        d.height = Math.min(m.height, d.height);

        if (resize) {
            setVisible(false);
            setSize(d);
            setVisible(true);
            setLocationRelativeTo(null);
        }
        super.paint(g);
    }
	
}
