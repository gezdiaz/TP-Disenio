package accesoADatos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import logicaDeNegocios.entidades.Clasificacion;
import logicaDeNegocios.entidades.GrupoResolucion;
import logicaDeNegocios.entidades.Intervencion;
import logicaDeNegocios.entidades.Ticket;
import logicaDeNegocios.enumeraciones.EstadoTicket;



public abstract class GestorBD {
	
	@PersistenceContext(unitName = "persistencia")
	private static EntityManager manager;
	
	@PersistenceUnit(name = "persistencia")
	private static EntityManagerFactory emf;
	
	
	public static Boolean guardarTicket(Ticket ticket) {
		
		try {
			manager.getTransaction().begin();
			manager.persist(ticket);
			manager.getTransaction().commit();
			manager.close();
			
			return true;
			
		} catch (Exception e) {

			return false;
			
		}
		
	}
	
	public static Boolean guardarIntervencion(Intervencion intervencion) {
		
		try {
			manager.getTransaction().begin();
			manager.persist(intervencion);
			manager.getTransaction().commit();
			manager.close();
			
			return true;
			
		} catch (Exception e) {

			return false;
			
		}
		
	}
	
	public static Boolean guardarClasificacion(Clasificacion clasificacion) {
		
		try {
			manager.getTransaction().begin();
			manager.persist(clasificacion);
			manager.getTransaction().commit();
			manager.close();
			
			return true;
			
		} catch (Exception e) {

			return false;
			
		}
		
	}

	public static Clasificacion buscarClasificacion(String clasificacion) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*public static List<Ticket> buscarTickets(Integer numTicket,Integer numLeg,String nombreClasificacion,EstadoTicket estadoActual, LocalDate fechaApertura, LocalDate fechaUltimoCambio,GrupoResolucion ultGrupo){
	
		
	}*/
	
	
	
}
