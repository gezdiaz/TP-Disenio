package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JPasswordField;

public class InicioSesion extends JFrame {
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicioSesion frame = new InicioSesion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InicioSesion() {
		setResizable(false);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblLaLlamitaInicio = new JLabel("La Llamita Inicio de sesi\u00F3n");
		lblLaLlamitaInicio.setFont(new Font("Tahoma", Font.BOLD, 17));
		GridBagConstraints gbc_lblLaLlamitaInicio = new GridBagConstraints();
		gbc_lblLaLlamitaInicio.gridheight = 2;
		gbc_lblLaLlamitaInicio.gridwidth = 4;
		gbc_lblLaLlamitaInicio.insets = new Insets(5, 5, 5, 5);
		gbc_lblLaLlamitaInicio.gridx = 0;
		gbc_lblLaLlamitaInicio.gridy = 0;
		getContentPane().add(lblLaLlamitaInicio, gbc_lblLaLlamitaInicio);
		
		JLabel lblNmeroDeLegajo = new JLabel("N\u00FAmero de Legajo");
		GridBagConstraints gbc_lblNmeroDeLegajo = new GridBagConstraints();
		gbc_lblNmeroDeLegajo.insets = new Insets(5, 5, 5, 5);
		gbc_lblNmeroDeLegajo.gridx = 1;
		gbc_lblNmeroDeLegajo.gridy = 3;
		getContentPane().add(lblNmeroDeLegajo, gbc_lblNmeroDeLegajo);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(5, 5, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 3;
		getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		GridBagConstraints gbc_lblContrasea = new GridBagConstraints();
		gbc_lblContrasea.insets = new Insets(5, 5, 5, 5);
		gbc_lblContrasea.gridx = 1;
		gbc_lblContrasea.gridy = 4;
		getContentPane().add(lblContrasea, gbc_lblContrasea);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(5, 5, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 3;
		gbc_passwordField.gridy = 4;
		getContentPane().add(passwordField, gbc_passwordField);
		
		JButton btnIngresar = new JButton("Ingresar");
		GridBagConstraints gbc_btnIngresar = new GridBagConstraints();
		gbc_btnIngresar.insets = new Insets(5, 5, 5, 5);
		gbc_btnIngresar.gridx = 1;
		gbc_btnIngresar.gridy = 6;
		getContentPane().add(btnIngresar, gbc_btnIngresar);
		
		JButton btnCancelar = new JButton("Cancelar");
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.gridx = 3;
		gbc_btnCancelar.gridy = 6;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
		
		pack();
		setLocationRelativeTo(null);
	}
}
