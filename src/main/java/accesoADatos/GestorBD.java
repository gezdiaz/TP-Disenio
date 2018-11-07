package accesoADatos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import logicaDeNegocios.entidades.Clasificacion;
import logicaDeNegocios.entidades.GrupoResolucion;
import logicaDeNegocios.entidades.Intervencion;
import logicaDeNegocios.entidades.Ticket;
import logicaDeNegocios.entidades.Usuario;
import logicaDeNegocios.enumeraciones.EstadoTicket;



public abstract class GestorBD {
	
	@PersistenceUnit(name = "persistencia")
	private static EntityManagerFactory emf;
	
	private static Query consulta;
	
	
	public static Boolean guardarTicket(Ticket ticket) {
		
		try {
			EntityManager manager = emf.createEntityManager();
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
			EntityManager manager = emf.createEntityManager();
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
			EntityManager manager = emf.createEntityManager();
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
	
	public static List<Ticket> buscarTickets(Integer numTicket,Integer numLeg,String nombreClasificacion,EstadoTicket estadoActual, LocalDateTime fechaApertura, LocalDateTime fechaUltimoCambio,GrupoResolucion ultGrupo){
		EntityManager manager = emf.createEntityManager();
		consulta = manager.createQuery("SELECT *"
									 + "FROM TICKET t, RECLASIFICACION r, CAMBIO_ESTADO_TICKET cet,CLASIFICACION c"
									 + "WHERE t.NUM_TICKET = ?1"
									 + "AND   t.NUM_LEGAJO = ?2"
									 + "AND   t.FECHA_HORA_APERTURA = ?3"
									 + "AND   t.NUM_TICKET = r.NUM_TICKET"
									 + "AND   r.CLAVE_NUEVA = c.CLAVE"
									 + "AND   c.NOMBRE = ?4"
									 + "AND   t.NUM_TICKET = cet.NUM_TICKET"
									 + "AND   cet.ESTADO_NUEVO = ?5"
									 + "AND   cet.FECHA_HORA_CAMBIO = ?6");
		consulta.setParameter(1, numTicket);
		consulta.setParameter(2, numLeg);
		consulta.setParameter(3, fechaApertura);
		consulta.setParameter(4, nombreClasificacion);
		consulta.setParameter(5, estadoActual);
		consulta.setParameter(6, fechaUltimoCambio);
		
		return null;
	
		
	}
	
	public Usuario buscarUsuario(String nombreUsuario) {
		EntityManager manager = emf.createEntityManager();
		Usuario usuario;
		
		manager.getTransaction().begin();
		usuario = manager.find(Usuario.class, nombreUsuario);
		manager.getTransaction().commit();
		manager.close();
		
		
		return usuario;
	}
	
}
