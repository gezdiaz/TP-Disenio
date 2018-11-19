package interfaz.principal;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interfaz.Main;
import interfaz.base.VentanaBase;
import interfaz.paneles.consultarIntervencion.ConsultarIntervencionPanel;
import interfaz.paneles.consultarTicket.ConsultarTicketPanel;
import interfaz.paneles.registrarTicket.RegistrarTicketPanel;
import logicaDeNegocios.gestores.GestorUsuarios;

public class MenuGrupoDeResolucionPanel extends JPanel{

	VentanaBase ventana;
	JButton btnConsultarIntervenciones, btnRegistrarClasificacion, btnBuscarClasificacion, btnCerrarSesion;

	public MenuGrupoDeResolucionPanel(VentanaBase ventana) {
		this();
		setVentana(ventana);
	}

	public void setVentana(VentanaBase ventana) {
		this.ventana = ventana;
	}

	public MenuGrupoDeResolucionPanel() {

		GridBagConstraints cons = new GridBagConstraints();
		setLayout(new GridBagLayout());
		JLabel labelAux;

		this.btnConsultarIntervenciones = new JButton("Consultar intervenciones asignadas");

		this.btnRegistrarClasificacion = new JButton("Registrar clasificación de tickets");

		this.btnBuscarClasificacion = new JButton("Buscar clasificación tickets");
		
		this.btnCerrarSesion = new JButton("Cerrar Sesión");

		labelAux = new JLabel("Grupo de resolucion");
		labelAux.setFont(new Font(labelAux.getFont().getFontName(), labelAux.getFont().getStyle(), 20));
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.anchor = GridBagConstraints.WEST;
		cons.insets = new Insets(15, 20, 15, 30);
		add(labelAux, cons);

		labelAux = new JLabel(new ImageIcon("icono.png"));
		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridheight = 3;
		cons.gridwidth = 1;
		cons.anchor = GridBagConstraints.CENTER;
		cons.insets = new Insets(5, 50, 5, 5);
		add(labelAux, cons);

		cons.gridx = 1;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.anchor = GridBagConstraints.SOUTH;
		cons.insets = new Insets(50, 5, 20, 30);
		cons.fill = GridBagConstraints.HORIZONTAL;
		btnConsultarIntervenciones.addActionListener(a->{
			ventana.cambiarPanel(new ConsultarIntervencionPanel(ventana));
		});
		add(btnConsultarIntervenciones, cons);

		cons.gridx = 1;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.anchor = GridBagConstraints.NORTH;
		cons.insets = new Insets(20, 5, 20, 30);
		cons.fill = GridBagConstraints.NONE;
		btnRegistrarClasificacion.addActionListener(a->{
			JOptionPane.showConfirmDialog(ventana, "Esta funcionalidad aun no esta disponible", "Proximamente", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
		});
		add(btnRegistrarClasificacion, cons);
		
		cons.gridx = 1;
		cons.gridy = 3;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.anchor = GridBagConstraints.NORTH;
		cons.insets = new Insets(20, 5, 10, 30);
		cons.fill = GridBagConstraints.NONE;
		btnBuscarClasificacion.addActionListener(a->{
			JOptionPane.showConfirmDialog(ventana, "Esta funcionalidad aun no esta disponible", "Proximamente", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
		});
		add(btnBuscarClasificacion, cons);

		cons.gridx = 0;
		cons.gridy = 4;
		cons.gridheight = 1;
		cons.gridwidth = 2;
		cons.anchor = GridBagConstraints.CENTER;
		cons.insets = new Insets(25, 5, 10, 30);
		btnCerrarSesion.addActionListener(a -> {
			GestorUsuarios.cerrarSesion();
			ventana.dispose();
			Main.iniciarSesion();
		});
		add(btnCerrarSesion, cons);


	}
	
}
