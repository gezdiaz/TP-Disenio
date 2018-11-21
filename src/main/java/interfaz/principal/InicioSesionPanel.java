package interfaz.principal;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import interfaz.base.VentanaBase;
import logicaDeNegocios.gestores.GestorUsuarios;

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
		cons.insets = new Insets(40, 20, 40, 20);
		cons.anchor = GridBagConstraints.CENTER;
		add(labelAux, cons);

		labelAux = new JLabel("Nombre usuario");
		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(10, 40, 10, 10);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);

		labelAux = new JLabel("Clave");
		cons.gridx = 0;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(10, 40, 20, 10);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);

		cons.gridx = 1;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(10, 10, 10, 40);
		cons.anchor = GridBagConstraints.WEST;
		cons.fill = GridBagConstraints.HORIZONTAL;
		add(txtUsuario, cons);

		cons.gridx = 1;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(10, 10, 20, 40);
		cons.anchor = GridBagConstraints.WEST;
		cons.fill = GridBagConstraints.HORIZONTAL;
		add(txtClave, cons);

		cons.gridx = 1;
		cons.gridy = 3;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(10, 10, 20, 40);
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
		cons.insets = new Insets(10, 40, 20, 10);
		cons.anchor = GridBagConstraints.WEST;
		cons.fill = GridBagConstraints.NONE;
		salir.addActionListener(a -> {
			ventana.dispose();
			System.exit(0);
		});
		add(salir, cons);

		this.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("ENTER"), "ApretoEnter");
		this.getActionMap().put("ApretoEnter", new Action() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(salir.isFocusOwner()) {
					ventana.dispose();
					System.exit(0);
				}else {
					iniciarSesion();
				}
			}

			@Override
			public void addPropertyChangeListener(PropertyChangeListener listener) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public Object getValue(String key) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean isEnabled() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public void putValue(String key, Object value) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void removePropertyChangeListener(PropertyChangeListener listener) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void setEnabled(boolean b) {
				// TODO Auto-generated method stub
				
			}
		}
		);
				

	}

	private void iniciarSesion() {
		String claveStr = new String(txtClave.getPassword());

		if (!txtUsuario.getText().trim().isEmpty() || !claveStr.trim().isEmpty()) {

			VentanaBase base;
			Integer res = GestorUsuarios.iniciarSesion(txtUsuario.getText(), claveStr);

			switch(res) {
			case -2: //Problema con la base de datos 
				JOptionPane.showConfirmDialog(ventana, "No se pudo establecer conexión con la base de datos", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				break;
			case -1: //no se encontró el usuario
				JOptionPane.showConfirmDialog(ventana, "Nombre de usuario inexistente", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				break;
			case 0: //clave incorrecta
				JOptionPane.showConfirmDialog(ventana, "La clave ingresada es incorrecta", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				break;
			case 1: //inicio de sesion correcto
				if(GestorUsuarios.usuarioActual().getGrupo().getNombre().equals("Mesa de Ayuda")) {
					MenuMesaAyudaPanel mmap = new MenuMesaAyudaPanel();
					base = new VentanaBase("Mesa de ayuda", txtUsuario.getText().trim(), mmap);
					mmap.setVentana(base);
				}else {
					MenuGrupoDeResolucionPanel mgrp = new MenuGrupoDeResolucionPanel();
					base = new VentanaBase("Grupo de resolución", txtUsuario.getText().trim(), mgrp);
					mgrp.setVentana(base);
				}
				base.pack();
				base.setLocationRelativeTo(null);
				base.setVisible(true);
				ventana.dispose();
			}

		}else {
			JOptionPane.showConfirmDialog(ventana, "Debe ingresar un usuario y clave", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
		}
	}


}
