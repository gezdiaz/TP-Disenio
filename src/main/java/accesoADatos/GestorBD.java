package accesoADatos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import dto.NumTicket;
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

	public static Long getNumTicket() {
		
		Long numTicket = -1L;
		NumTicket nt;
		System.out.println("GetNumTicket() 1: "+numTicket);
		try {
			EntityManager manager = emf.createEntityManager();
			manager.getTransaction().begin();
			Query consulta = manager.createQuery("from NumTicket");
			nt = (NumTicket) consulta.getResultList().get(0);
			numTicket = nt.getNum();
			nt.setNum(numTicket+1);
			manager.persist(nt);
			manager.getTransaction().commit();
			manager.close();
			System.out.println("GetNumTicket() 1: "+numTicket);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return numTicket;
		
	}
	
	public static Boolean guardarTicket(Ticket ticket) {

//		System.out.println("Entro a guardar Ticket");

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
		
		try {
			EntityManager manager = emf.createEntityManager();
			Clasificacion rta;
			manager.getTransaction().begin();
			Query cons = manager.createQuery("from Clasificacion c where c.nombre = ?1");
			cons.setParameter(1, clasificacion);
			rta = (Clasificacion) cons.getSingleResult();
			manager.getTransaction().commit();
			manager.close();
			return rta;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
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

	public static List<String> getListGruposConClasificacion(String clasificacion) {
		
		List<GrupoResolucion> grupos;
		List<String> nombres = new ArrayList<String>();
		
		try {
			EntityManager manager = emf.createEntityManager();
			manager.getTransaction().begin();
			String consulta = "SELECT g.ID_GR, g.NOMBRE FROM GRUPO_DE_RESOLUCION g, CLASIFICACION c, CAPACITADO_PARA cp WHERE g.ID_GR=cp.ID_GR AND c.CODIGO=cp.CLAVE AND c.NOMBRE=?1";
			/*Query cons = manager.createNativeQuery("SELECT g.ID_GR, g.NOMBRE "
													+ "FROM GRUPO_DE_RESOLUCION g, CLASIFICACION c, CAPACITADO_PARA cp "
													+ "WHERE g.ID_GR=cp.ID_GR "
													+ "AND c.CODIGO=cp.CLAVE "
													+ "AND c.NOMBRE=?1", GrupoResolucion.class);*/
			Query cons = manager.createNativeQuery(consulta, GrupoResolucion.class);
			cons.setParameter(1, clasificacion);
			grupos = (List<GrupoResolucion>)cons.getResultList();
			System.out.println("Resultado consulta: "+grupos);
			manager.getTransaction().commit();
			manager.close();
			System.out.println("Lista grupos en el gestorBD: " + grupos);
			for(GrupoResolucion c: grupos) {
				nombres.add(c.getNombre());
			}
			System.out.println("Lista Nombres grupos en el gestorBD: " + grupos);
			return nombres;

		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<String>();

		}
	}

	public static List<String> getListGrupos() {
		List<GrupoResolucion> grupos;
		List<String> nombres = new ArrayList<String>();
		
		try {
			EntityManager manager = emf.createEntityManager();
			manager.getTransaction().begin();
			Query cons = manager.createQuery("from GrupoResolucion");
			grupos = cons.getResultList();
			manager.getTransaction().commit();
			manager.close();

			for(GrupoResolucion g: grupos) {
				nombres.add(g.getNombre());
			}
			
			return nombres;

		} catch (Exception e) {
			
			return new ArrayList<String>();

		}
	}
	
	public static List<Ticket> buscarTickets(Long numTicket,Long numLeg,String estadoActual, String nombreClasificacion,LocalDateTime fechaApertura, LocalDateTime fechaUltimoGrupo, GrupoResolucion ultGrupo){
		
		EntityManager manager = emf.createEntityManager();
		List<Ticket> resultado;
		
		try {
			 
            CriteriaBuilder cb = manager.getCriteriaBuilder();
            List<Predicate> lstPredicates = new ArrayList<Predicate>();
            CriteriaQuery<Ticket> consulta = cb.createQuery(Ticket.class);
             
            Root<Ticket> tickets = consulta.from(Ticket.class);
            
            
            if(numTicket != null) {
                //Si no funciona podemos utilizar consulta.where antes de cb.equal
                Predicate p1 = (Predicate) cb.equal(tickets.get("NUM_TICKET"),numTicket.toString());
                lstPredicates.add(p1);
            }
             
            if(numLeg != null) {
                 
            	Join<Ticket, Empleado> datos1 = tickets.join("NUM_LEG");
	            Predicate p2 = (Predicate) cb.equal(datos1.get("NUM_LEG"),"NUM_LEG");
	            lstPredicates.add(p2);
	            Predicate p3 = (Predicate) cb.equal(datos1.get("NUM_LEG"), numLeg.toString());
	            lstPredicates.add(p3);
                 
            }
            
            if(nombreClasificacion != null) {
            	
            	Join<Reclasificacion,Ticket> datos2 = tickets.join("NUM_TICKET");
            	Join<Reclasificacion,Clasificacion> datos3 = datos2.join("CLAVE_NUEVA");
            	Predicate p3 = (Predicate)cb.equal(datos3.get("NOMBRE"), nombreClasificacion);
            	lstPredicates.add(p3);
            	
            }
            
            if(estadoActual != null) {
            	
            	Join<CambioEstadoTicket, Ticket> datos4 = tickets.join("NUM_TICKET");
            	Predicate p4 = (Predicate)cb.equal(datos4.get("ESTADO_NUEVO"), estadoActual);
            	lstPredicates.add(p4);
            }
            
            if(fechaApertura != null) {
            	
            	Predicate p5 = (Predicate) cb.equal(tickets.get("FECHA_HORA_APERTURA"),fechaApertura.toString());
            	lstPredicates.add(p5);
            	
            }
            
            if(fechaUltimoGrupo != null) {
            	
            	Join<Intervencion,Ticket> datos5 = tickets.join("NUM_TICKET");
            	Predicate p6 = (Predicate) cb.equal(datos5.get("FECHA_HORA_ASIGNACION"),fechaUltimoGrupo.toString());
            	lstPredicates.add(p6);
            	
            }
            
            if(ultGrupo != null) {
            	
            	Join<Intervencion,Ticket> datos6 = tickets.join("NUM_TICKET");
            	Join<Intervencion,GrupoResolucion> datos7 = datos6.join("ID_GR");
            	Predicate p7 = (Predicate) cb.equal(datos7.get("NOMBRE"),ultGrupo);
            	lstPredicates.add(p7);
            	
            }
             
            consulta.where(cb.and((javax.persistence.criteria.Predicate[]) lstPredicates.toArray(new Predicate[lstPredicates.size()])));
            
         
             
            resultado = manager.createQuery(consulta).getResultList();
            manager.close();
            return resultado;
            
        } catch (Exception e) {
 
            e.printStackTrace();
		
		return null;
		
        }

	}

	public static GrupoResolucion buscarGrupoPorNombre(String nombreGrupo) {
	
		EntityManager manager = emf.createEntityManager();
		GrupoResolucion grupo;
		System.out.println("Nombre a buscar:" + nombreGrupo);
		try {
			
			manager.getTransaction().begin();
			Query consulta = manager.createQuery("from GrupoResolucion g where g.nombre = ?1", GrupoResolucion.class);
			consulta.setParameter(1, nombreGrupo);
			grupo = (GrupoResolucion) consulta.getResultList().get(0);
			manager.getTransaction().commit();
			manager.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
			return null;
		}
		
		return grupo;
	}
}
