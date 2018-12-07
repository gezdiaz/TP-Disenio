package interfaz.base;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interfaz.auxiliar.PanelCancelable;


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

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				salir();
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
			}
		});
		
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
	
	private void salir() {

		int res = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea salir del sistema? \nEs posible que se eliminen datos.", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if(res == JOptionPane.YES_OPTION) {
			if(subPanel instanceof PanelCancelable) {
				((PanelCancelable) subPanel).apretoCancelar(true);
			}
		}else {
			return;
		}

		System.exit(0);
	}
	
}
