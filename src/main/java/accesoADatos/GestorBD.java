package accesoADatos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import logicaDeNegocios.entidades.CambioEstadoIntervencion;
import logicaDeNegocios.entidades.CambioEstadoTicket;
import logicaDeNegocios.entidades.Clasificacion;
import logicaDeNegocios.entidades.Empleado;
import logicaDeNegocios.entidades.GrupoResolucion;
import logicaDeNegocios.entidades.Intervencion;
import logicaDeNegocios.entidades.Reclasificacion;
import logicaDeNegocios.entidades.Ticket;
import logicaDeNegocios.entidades.Usuario;
import logicaDeNegocios.enumeraciones.EstadoTicket;



public abstract class GestorBD {

	private static EntityManagerFactory emf;

	private static Query consulta;

	public static void setEmf(EntityManagerFactory factory) {
		emf = factory;
	}

	public static Boolean guardarTicket(Ticket ticket) {

		System.out.println("Entro a guardar TIcket");

		try {
			EntityManager manager = emf.createEntityManager();
			manager.getTransaction().begin();
			manager.persist(ticket);
			manager.merge(ticket);
			System.out.println("Después del merge, numTicket: "+ticket.getNumTIcket());
			//			manager.persist(ticket);
			manager.getTransaction().commit();
			manager.close();
			System.out.println("Salgo de guardar TIcket");
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}

	}

	public static Boolean actualizarTicket(Ticket ticket) {

		System.out.println("Entro a guardar cambio de ticket");

		try {
			EntityManager manager = emf.createEntityManager();
			manager.getTransaction().begin();
			ticket = manager.merge(ticket);
			manager.persist(ticket);
			manager.getTransaction().commit();
			manager.close();
			System.out.println("Salgo de guardar ticket");
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}

	}


	public static Integer guardarIntervencion(Intervencion intervencion) {

		try {
			EntityManager manager = emf.createEntityManager();
			manager.getTransaction().begin();
			manager.persist(intervencion);
			manager.merge(intervencion);
			manager.getTransaction().commit();
			manager.close();

			return 1;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;

		}

	}

	public static Integer guardarClasificacion(Clasificacion clasificacion) {

		try {
			EntityManager manager = emf.createEntityManager();
			manager.getTransaction().begin();
			manager.persist(clasificacion);
			manager.getTransaction().commit();
			manager.close();

			return 1;

		} catch (Exception e) {

			return 0;

		}

	}

	public static Clasificacion buscarClasificacion(String clasificacion) {
		// TODO Auto-generated method stub
		return null;
	}

	public static List<Ticket> buscarTickets(Integer numTicket,Integer numLeg,String nombreClasificacion,EstadoTicket estadoActual, LocalDateTime fechaApertura, LocalDateTime fechaUltimoCambio,GrupoResolucion ultGrupo){
		/*EntityManager manager = emf.createEntityManager();
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
		consulta.setParameter(6, fechaUltimoCambio);*/

		return null;


	}

	public static Ticket buscarTicketPorId(Long numTicket) {

		try {
			EntityManager manager = emf.createEntityManager();
			Ticket ticket;

			manager.getTransaction().begin();
			ticket = manager.find(Ticket.class, numTicket);
			manager.getTransaction().commit();
			ticket.getHistorialCambioEstadoTicket().size();
			ticket.getHistorialReclasificacion().size();
			ticket.getIntervenciones().size();
			manager.close();

			/*if(ticket==null) {
				System.out.println("Ticket es null");
			}*/

			return ticket;
		} catch (Exception e) {

			e.printStackTrace();

			return null;
		}
	}

	public static Empleado buscarEmpleado(Long numLegajo) {

		try {
			EntityManager manager = emf.createEntityManager();
			Empleado empleado = null;

			manager.getTransaction().begin();
			empleado = manager.find(Empleado.class, numLegajo);
			manager.getTransaction().commit();
			manager.close();


			/*if(empleado == null) {
				//no encontró el empleado
				empleado = new Empleado();
				empleado.setNumLegajo(-1L);//num de legajo -1 para indicar un error
			}*/

			return empleado;
		} catch (Exception e) {
			System.out.println("Exepcion en buscar usuario: ");
			System.out.println();
			e.printStackTrace();

			return null;			
		}

	}

	public static Usuario buscarUsuario(String nombreUsuario) {

		if(emf == null) {
			System.out.println("EL EMF ES NULL!!!");
		}

		try {
			EntityManager manager = emf.createEntityManager();
			Usuario usuario = null;

			manager.getTransaction().begin();
			usuario = manager.find(Usuario.class, nombreUsuario);
			manager.getTransaction().commit();
			manager.close();


			if(usuario == null) {
				//no encontró el usuario
				usuario = new Usuario();
				usuario.setNombreUsuario("");
			}

			return usuario;
		} catch (Exception e) {
			System.out.println("Exepcion en buscar usuario: ");
			System.out.println();
			e.printStackTrace();

			return null;			
		}


	}

//	public static Boolean guardarCambioEstadoTIcket(CambioEstadoTicket cambioEstado) {
//		//System.out.println("Entro a guardar TIcket");
//
//		try {
//			EntityManager manager = emf.createEntityManager();
//			manager.getTransaction().begin();
//			manager.persist(cambioEstado);
//			manager.merge(cambioEstado);
//			//System.out.println("Después del merge, numTicket: "+ticket.getNumTIcket());
//			//			manager.persist(ticket);
//			manager.getTransaction().commit();
//			manager.close();
//			//System.out.println("Salgo de guardar TIcket");
//			return true;
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//
//		}
//	}
//
//
//
//	public static Boolean guardarCambioEstadoIntervencion(CambioEstadoIntervencion cambioEstado) {
//		//System.out.println("Entro a guardar TIcket");
//
//		try {
//			EntityManager manager = emf.createEntityManager();
//			manager.getTransaction().begin();
//			manager.persist(cambioEstado);
//			manager.merge(cambioEstado);
//			//System.out.println("Después del merge, numTicket: "+ticket.getNumTIcket());
//			//		manager.persist(ticket);
//			manager.getTransaction().commit();
//			manager.close();
//			//System.out.println("Salgo de guardar TIcket");
//			return true;
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//
//		}
//	}
//
//	public static Boolean guardarReclasificacion(Reclasificacion reclasificacion) {
//		//System.out.println("Entro a guardar TIcket");
//
//		try {
//			EntityManager manager = emf.createEntityManager();
//			manager.getTransaction().begin();
//			manager.persist(reclasificacion);
//			manager.merge(reclasificacion);
//			//System.out.println("Después del merge, numTicket: "+ticket.getNumTIcket());
//			//		manager.persist(ticket);
//			manager.getTransaction().commit();
//			manager.close();
//			//System.out.println("Salgo de guardar TIcket");
//			return true;
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//
//		}
//	}

	public static boolean actualizarIntervencion(Intervencion ultima) {
		try {
			EntityManager manager = emf.createEntityManager();
			manager.getTransaction().begin();
			ultima = manager.merge(ultima);
			manager.persist(ultima);
			manager.getTransaction().commit();
			manager.close();

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}
	}

	public static Boolean eliminarTicket(Long numTicket) {
		Ticket ticket;
		
		try {
			EntityManager manager = emf.createEntityManager();
			manager.getTransaction().begin();
			ticket = manager.find(Ticket.class, numTicket);
			manager.remove(ticket);
			manager.getTransaction().commit();
			manager.close();

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}
		
		
	}
	
	public static List<String> getListClasificaciones(){
		List<Clasificacion> clasificaciones;
		List<String> nombres = new ArrayList<String>();
		
		try {
			EntityManager manager = emf.createEntityManager();
			manager.getTransaction().begin();
			Query cons = manager.createQuery("from Clasificacion");
			clasificaciones = cons.getResultList();
			manager.getTransaction().commit();
			manager.close();

			for(Clasificacion c: clasificaciones) {
				nombres.add(c.getNombre());
			}
			
			return nombres;

		} catch (Exception e) {
			
			return new ArrayList<String>();

		}
		
	}

}
