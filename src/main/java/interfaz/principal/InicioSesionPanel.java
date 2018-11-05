package interfaz.principal;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import interfaz.base.VentanaBase;

public class InicioSesionPanel extends JPanel {

	JTextField txtUsuario;
	JPasswordField txtClave;
	JButton iniciarSesion, salir;
	JFrame ventana;
	
	public InicioSesionPanel(JFrame ventana) {
		
		this.ventana = ventana;
		GridBagConstraints cons = new GridBagConstraints();
		setLayout(new GridBagLayout());
		JLabel labelAux;
		
		txtUsuario = new JTextField();
		
		txtClave = new JPasswordField();
		
		iniciarSesion = new JButton("Inicicar sesión");
		
		salir = new JButton("Salir");
		
		labelAux = new JLabel("Iniciar sesión");
		labelAux.setFont(new Font(labelAux.getFont().getFontName(), labelAux.getFont().getStyle(), 20));
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridheight = 1;
		cons.gridwidth = 2;
		cons.insets = new Insets(20, 10, 20, 10);
		cons.anchor = GridBagConstraints.CENTER;
		add(labelAux, cons);
		
		labelAux = new JLabel("Nombre usuario");
		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(10, 20, 10, 10);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		labelAux = new JLabel("Clave");
		cons.gridx = 0;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(10, 20, 10, 10);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		cons.gridx = 1;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(10, 10, 10, 20);
		cons.anchor = GridBagConstraints.WEST;
		cons.fill = GridBagConstraints.HORIZONTAL;
		add(txtUsuario, cons);
		
		cons.gridx = 1;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(10, 10, 10, 20);
		cons.anchor = GridBagConstraints.WEST;
		cons.fill = GridBagConstraints.HORIZONTAL;
		add(txtClave, cons);
		
		cons.gridx = 1;
		cons.gridy = 3;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(10, 10, 10, 20);
		cons.anchor = GridBagConstraints.EAST;
		cons.fill = GridBagConstraints.NONE;
		iniciarSesion.addActionListener(a -> {
			iniciarSesion();
		});
		add(iniciarSesion, cons);
		
		cons.gridx = 0;
		cons.gridy = 3;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(10, 20, 10, 10);
		cons.anchor = GridBagConstraints.WEST;
		cons.fill = GridBagConstraints.NONE;
		salir.addActionListener(a -> {
			ventana.dispose();
		});
		add(salir, cons);
		
		
	}

	private void iniciarSesion() {
		
		if (!txtUsuario.getText().trim().isEmpty() || !txtClave.getPassword().toString().trim().isEmpty()) {
			//TODO buscar en el gestor de usuarios el usuario y verificar la clave.
			String titulo = "Título";
			/*if(usuario.getGrupo().getNombre()=="Mesa de ayuda"){
			 * 		titulo = "Mesa de ayuda";
			 * }else{
			 * 		titulo = "Grupo resoluci�n" //Puese ser gerencia tambi�n
			 * }
			 * */
			MenuMesaAyudaPanel mmap = new MenuMesaAyudaPanel();
			VentanaBase base = new VentanaBase(titulo, txtUsuario.getText().trim(), mmap);
			mmap.setVentana(base);
			base.pack();
			base.setLocationRelativeTo(null);
			base.setVisible(true);
			ventana.dispose();
		}else {
			JOptionPane.showConfirmDialog(ventana, "Debe ingresar un usuario y clave", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
}
