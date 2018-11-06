package interfaz.paneles.registrarTicket;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dto.TicketDTO;
import interfaz.base.VentanaBase;
import interfaz.principal.MenuMesaAyudaPanel;
import dto.*;

public class RegistrarTicket2Panel extends JPanel {
	
	JTextArea obserbacionesTxt;
	JButton aceptar, cancelar;
	JComboBox<String> accionList;
	VentanaBase ventana;
	TicketDTO ticketDTO;

	public RegistrarTicket2Panel(VentanaBase ventana, TicketDTO ticketDTO) {
		//TODO Debería recibir también un ticketDTO
		GridBagConstraints cons = new GridBagConstraints();
		Insets arIzq = new Insets(25, 25, 5, 5), izq = new Insets(10, 50, 10, 5),
				arDer = new Insets(25, 5, 5, 25), der = new Insets(10, 5, 10, 25);
		JLabel labelAux;
		JScrollPane scroll;
		
		//pongo un layout GridBagaLayout
		setLayout(new GridBagLayout());
		
		//inicializa las variables globales
		this.ventana = ventana;
		
		this.ticketDTO = ticketDTO;
		
		obserbacionesTxt = new JTextArea();
		
		aceptar = new JButton("Aceptar");
		
		cancelar = new JButton("Cancelar");
		
		accionList = new JComboBox<>();		
		
		//Agrego todos los componentes al panel
		labelAux = new JLabel("Registrar ticket");
		labelAux.setFont(new Font(labelAux.getFont().getFontName(), labelAux.getFont().getStyle(), 20));
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = new Insets(15, 25, 25, 5);
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		labelAux = new JLabel("Observaciones*");
		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = izq;
		cons.anchor = GridBagConstraints.NORTHWEST;
		add(labelAux, cons);
		
		labelAux = new JLabel("Acción");
		cons.gridx = 0;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.insets = izq;
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
		//Muestra el numero de ticket
		labelAux = new JLabel("N° Ticket: "+/*"123456"*/ticketDTO.getNumTicket());
		cons.gridx = 1;
		cons.gridy = 0;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 1;
		cons.insets = new Insets(15, 25, 25, 5);
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.CENTER;
		add(labelAux, cons);
		
		//Campo observaciones con su barra de scroll
		scroll = new JScrollPane(obserbacionesTxt);
		obserbacionesTxt.setPreferredSize(new Dimension(200, 70));
		cons.gridx = 1;
		cons.gridy = 1;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 2;
		cons.insets = arDer;
		cons.fill = GridBagConstraints.BOTH;
		cons.anchor = GridBagConstraints.CENTER;
		add(scroll, cons);
		
		//Lista desplegabel con los grupos:
		//TODO Debería pedri al gestor los grupos que pueden resolver la clasificación del ticket.
		//List<GrupoResolucion>listaGR = GestorBD.getGrupos(ticketDTO.getClasificacion());
		accionList.addItem("Cerrar ticet");
		/*accionList.addItem("Grupo 1");
		accionList.addItem("Grupo 2");
		accionList.addItem("Grupo 3");
		accionList.addItem("Grupo 4");*/
		/*for(GrupoResolucion gr : listaGR){
		  	accionList.addItem(gr.getNombre());
		  }*/
		accionList.setSelectedItem("Cerrar ticket");
		cons.gridx = 1;
		cons.gridy = 2;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 1;
		cons.insets = der;
		cons.fill = GridBagConstraints.BOTH;
		cons.anchor = GridBagConstraints.CENTER;
		add(accionList, cons);
		
		//botones
		aceptar.addActionListener(a -> {
			apretoAceptar();
		});
		cons.gridx = 0;
		cons.gridy = 4;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 1;
		cons.insets = new Insets(20, 40, 40, 10);
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.WEST;
		add(aceptar, cons);
		
		cancelar.addActionListener(a -> {
			apretoCancelar();
		});
		cons.gridx = 1;
		cons.gridy = 4;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 1;
		cons.insets = new Insets(20, 10, 40, 25);
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.EAST;
		add(cancelar, cons);
		
		//*campo obligatorio
		labelAux = new JLabel("*Campo obligatorio");
		labelAux.setFont(new Font(labelAux.getFont().getFontName(), labelAux.getFont().getStyle(), 8));
		cons.gridx = 0;
		cons.gridy = 3;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 1;
		cons.insets = new Insets(5, 40, 5, 5);
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.WEST;
		add(labelAux, cons);
		
	}
	private void apretoCancelar() {
		//TODO Boton cancelar en la segunda pantalla de registrar ticket
		//GestorTickets.borrarTicket(ticketDTO);
		ventana.cambiarPanel(new MenuMesaAyudaPanel(ventana));
	}
	private void apretoAceptar() {
		if(obserbacionesTxt.getText().trim().isEmpty()) {
			JOptionPane.showConfirmDialog(ventana, "Debe ingresar observaciones", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
		}else {
			//TODO Hacer la acción seleccionada, cerrar o derivar ticket del CU1
			if(accionList.getSelectedItem()=="Cerrar ticket") {
				//GestorTickets.cerrarTicket(ticketDTO);
			}
			else {
				//GestorTickets.derivarTicket(ticketDTO,accionList.getSelectedItem());
			}
			ventana.cambiarPanel(new MenuMesaAyudaPanel(ventana));
		}
	}

}
