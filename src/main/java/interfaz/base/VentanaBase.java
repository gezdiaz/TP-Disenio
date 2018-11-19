package interfaz.base;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class VentanaBase extends JFrame {


	PanelBarraUsuario barraUsuario;
	JPanel subPanel;
	JPanel panel;
	CardLayout cartas;
	//	GridBagConstraints restriccionesPanel;


	public VentanaBase(String title, String nombreUsuario, JPanel subPanel){
		super(title);
		//		this.restriccionesPanel = new GridBagConstraints();
		this.subPanel = subPanel;
		this.cartas = new CardLayout();
		JPanel contentPane = new JPanel(new BorderLayout());
		panel = new JPanel(cartas);
		barraUsuario = new PanelBarraUsuario(nombreUsuario);


		panel.add(this.subPanel, "Inicio");
		cartas.show(panel, "Inicio");

		contentPane.add(barraUsuario, BorderLayout.NORTH);
		contentPane.add(panel, BorderLayout.CENTER);
		//		this.restriccionesPanel.gridx=0;
		//		this.restriccionesPanel.gridy=1;
		//		this.restriccionesPanel.fill=GridBagConstraints.BOTH;
		//		this.restriccionesPanel.weighty = 2;
		//		this.restriccionesPanel.weightx = 2;
		////		otroPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		//		this.subPanel.setBackground(new Color(163,255,140));

		ImageIcon img = new ImageIcon("icono.png");
		setIconImage(img.getImage());

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setContentPane(contentPane);

	}

	public void cambiarPanel(JPanel nuevoPanel) {
		this.subPanel = nuevoPanel;
		//		panel.add(this.subPanel, restriccionesPanel);
		//		panel.repaint();
		//		this.repaint();
		//		System.out.println(nuevoPanel.toString());
		panel.add(nuevoPanel, "nuevoPanel");
//		nuevoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		cartas.show(panel, "nuevoPanel");
		this.pack();		
		this.setLocationRelativeTo(null);
//		System.out.println("En la ventana: " + nuevoPanel.getSize());
//		System.out.println("Panel completo: "+ panel.getSize());
//		System.out.println("Tamaño ventana: "+ this.getSize());
//		System.out.println("Tamaño ventana: "+ this.getSize());
	}

	//	@Override
	//	public Image getIconImage() {
	//	   Image retValue = Toolkit.getDefaultToolkit().
	//	         getImage(ClassLoader.getSystemResource("icono.png"));
	//	   System.out.println("Lo hace");
	//	   return retValue;
	//	}





}
