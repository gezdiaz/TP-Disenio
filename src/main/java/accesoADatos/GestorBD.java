package accesoADatos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
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
import logicaDeNegocios.enumeraciones.EstadoIntervencion;
import logicaDeNegocios.enumeraciones.EstadoTicket;



public abstract class GestorBD {

	private static EntityManagerFactory emf;

	public static void setEmf(EntityManagerFactory factory) {
		emf = factory;
	}

	public static Long getNumTicket() {	
		Long numTicket = -1L;
		NumTicket nt;
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
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return numTicket;		
	}
	
	public static Boolean guardarTicket(Ticket ticket) {
		try {
			EntityManager manager = emf.createEntityManager();
			manager.getTransaction().begin();		
			ticket = manager.merge(ticket);
			manager.persist(ticket);
			manager.getTransaction().commit();
			manager.close();
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
			intervencion=manager.merge(intervencion);
			manager.persist(intervencion);
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
			Query cons = manager.createQuery("from Clasificacion c where c.nombre = ?1 and activa = true");
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
	
	public static List<Intervencion> buscarintervenciones(EstadoIntervencion estado, LocalDateTime fechaDesde, LocalDateTime fechaHasta, Long numTicket, Long numLeg, String codGrupoActual) {	
		EntityManager manager = emf.createEntityManager();
		List<Intervencion> resultado;	
		try {			
            CriteriaBuilder cb = manager.getCriteriaBuilder();
            List<Predicate> lstPredicates = new ArrayList<Predicate>();
            CriteriaQuery<Intervencion> consulta = cb.createQuery(Intervencion.class);          
            Root<Intervencion> intervenciones = consulta.from(Intervencion.class);
            Join<Intervencion,Ticket> datos2 = null;    
            if(estado != null) {
                Join<CambioEstadoIntervencion,Intervencion> datos1 = intervenciones.join("historialCambioEstadoIntervencion");
                Predicate p1 = cb.equal(datos1.get("estadoNuevo"),estado);
                lstPredicates.add(p1);
            }            

            if(fechaDesde != null && fechaHasta != null) {
            	Predicate p2 = cb.between(intervenciones.get("fechaHoraAsignacion"), fechaDesde, fechaHasta);
            	lstPredicates.add(p2);
            }
            
            if(numTicket != null) {            	
            	datos2 = intervenciones.join("ticket");
            	Predicate p4 = cb.equal(datos2.get("numTicket"),numTicket);
            	lstPredicates.add(p4);            	
            }            
            if(numLeg != null) {            	
            	if(numTicket == null) {
            	datos2 = intervenciones.join("ticket");
            	}
            	Join<Ticket,Empleado> datos3 = datos2.join("solicitante");
            	Predicate p4 = cb.equal(datos3.get("numLegajo"),numLeg);
            	lstPredicates.add(p4);
            }            
            Join<GrupoResolucion, Intervencion> datos4 = intervenciones.join("grupoResolucion");
            Predicate p5 = cb.equal(datos4.get("codigo"), codGrupoActual);
            lstPredicates.add(p5);        
            
            consulta.where(cb.and((javax.persistence.criteria.Predicate[]) lstPredicates.toArray(new Predicate[lstPredicates.size()])));     
            
            consulta.orderBy(cb.desc(intervenciones.get("idInt")));
            
            resultado = manager.createQuery(consulta).getResultList();      
            
            for(Intervencion t: resultado) {
            	t.getHistorialCambioEstadoIntervencion().size();
            	t.getTicket().getHistorialCambioEstadoTicket().size();
            	t.getTicket().getHistorialReclasificacion().size();
            }            
            manager.close();           
            int j=1;
            for(int i=0; i<resultado.size(); i++) {
            	j=i+1;
            	while(j<resultado.size() && resultado.get(i).equals(resultado.get(j))) {
            		resultado.remove(j);
            	}           	
            	if((estado != null && !resultado.get(i).estadoActual().equals(estado))){
            		resultado.remove(i);
            		i--;
            	}           	
            }            
            return resultado;             
        } catch (Exception e) {
            e.printStackTrace();
            return null;		
        }
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
			return ticket;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Intervencion buscarIntervencionPorId(Long idIntervencion) {
		try {
			EntityManager manager = emf.createEntityManager();
			Intervencion intervencion;
			manager.getTransaction().begin();
			intervencion = manager.find(Intervencion.class, idIntervencion);
			manager.getTransaction().commit();
			intervencion.getHistorialCambioEstadoIntervencion().size();
			intervencion.getTicket().getHistorialCambioEstadoTicket().size();
			intervencion.getTicket().getHistorialReclasificacion().size();
			manager.close();
			return intervencion;
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
			return empleado;
		} catch (Exception e) {
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
			e.printStackTrace();
			return null;			
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
			Query cons = manager.createQuery("from Clasificacion c where activa = true order by c.nombre");
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
			String consulta = "SELECT g.ID_GR, g.NOMBRE FROM GRUPO_DE_RESOLUCION g, CLASIFICACION c, CAPACITADO_PARA cp WHERE g.ID_GR=cp.ID_GR AND c.CODIGO=cp.CLAVE AND c.NOMBRE=?1 ORDER BY g.NOMBRE";
			Query cons = manager.createNativeQuery(consulta, GrupoResolucion.class);
			cons.setParameter(1, clasificacion);
			grupos = (List<GrupoResolucion>)cons.getResultList();
			manager.getTransaction().commit();
			manager.close();
			for(GrupoResolucion c: grupos) {
				nombres.add(c.getNombre());
			}
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
			Query cons = manager.createQuery("from GrupoResolucion g order by g.nombre");
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

	public static List<Ticket> buscarTickets(Long numTicket,Long numLeg,EstadoTicket estadoActual, String nombreClasificacion,LocalDate fechaApertura, LocalDate fechaUltimoCambio, String ultGrupo){		
		EntityManager manager = emf.createEntityManager();
		List<Ticket> resultado;		
		try {			 
            CriteriaBuilder cb = manager.getCriteriaBuilder();
            List<Predicate> lstPredicates = new ArrayList<Predicate>();
            CriteriaQuery<Ticket> consulta = cb.createQuery(Ticket.class);             
            Root<Ticket> tickets = consulta.from(Ticket.class);           
            if(numTicket != null) {    	
                //Si no funciona podemos utilizar consulta.where antes de cb.equal
                Predicate p1 = cb.equal(tickets.get("numTicket"),numTicket.toString()); 
                lstPredicates.add(p1);
            } 
            if(numLeg != null) {   
            	Join<Ticket, Empleado> datos1 = tickets.join("solicitante");
	            Predicate p3 = cb.equal(datos1.get("numLegajo"), numLeg);
	            lstPredicates.add(p3);       
            }  
            if(nombreClasificacion != null) {	
            	Join<Reclasificacion,Ticket> datos2 = tickets.join("historialReclasificacion");
            	Join<Reclasificacion,Clasificacion> datos3 = datos2.join("clasificacionNueva");
            	Predicate p3 = (Predicate)cb.equal(datos3.get("nombre"), nombreClasificacion);
            	lstPredicates.add(p3); 	
            }  
            if(estadoActual != null) { 	
            	Join<CambioEstadoTicket, Ticket> datos4 = tickets.join("historialCambioEstadoTicket");
            	Predicate p4 = (Predicate)cb.equal(datos4.get("estadoNuevo"), estadoActual);
            	lstPredicates.add(p4);
            }    
            if(fechaApertura != null) {
            	Predicate p5 = cb.between(tickets.get("fechaHoraApertura"),
            					LocalDateTime.of(fechaApertura.getYear(), fechaApertura.getMonth(), fechaApertura.getDayOfMonth(), 0, 0),
            					LocalDateTime.of(fechaApertura.getYear(), fechaApertura.getMonth(), fechaApertura.getDayOfMonth(), 23, 59));
            	lstPredicates.add(p5);  	
            }   
            if(fechaUltimoCambio != null) {
            	Join<CambioEstadoTicket,Ticket> datos5 = tickets.join("historialCambioEstadoTicket");
            	Predicate p6 = cb.between(datos5.get("fechaHoraCambio"), 
		            			LocalDateTime.of(fechaUltimoCambio.getYear(), fechaUltimoCambio.getMonth(), fechaUltimoCambio.getDayOfMonth(), 0, 0),
		    					LocalDateTime.of(fechaUltimoCambio.getYear(), fechaUltimoCambio.getMonth(), fechaUltimoCambio.getDayOfMonth(), 23, 59));
            	lstPredicates.add(p6);     	
            } 
            if(ultGrupo != null) {
            	Join<Intervencion,Ticket> datos6 = tickets.join("intervenciones");
            	Join<Intervencion,GrupoResolucion> datos7 = datos6.join("grupoResolucion");
            	Predicate p7 = (Predicate) cb.equal(datos7.get("nombre"),ultGrupo);
            	lstPredicates.add(p7);	
            }
            consulta.where(cb.and((javax.persistence.criteria.Predicate[]) lstPredicates.toArray(new Predicate[lstPredicates.size()])));
            consulta.orderBy(cb.desc(tickets.get("numTicket")));  
            resultado = manager.createQuery(consulta).getResultList();
            for(Ticket t: resultado) {
            	t.getHistorialCambioEstadoTicket().size();
            	t.getIntervenciones().size();
            	t.getHistorialReclasificacion().size();
            }
            manager.close();
            int j=1;
            for(int i=0; i<resultado.size(); i++) {
            	j=i+1;
            	while(j<resultado.size() && resultado.get(i).equals(resultado.get(j))) {
            		resultado.remove(j);
            	}
            	if((estadoActual != null && !resultado.get(i).estadoActual().equals(estadoActual))
            		|| (ultGrupo != null && !resultado.get(i).ultimoGrupo().getNombre().equals(ultGrupo))
            		|| (nombreClasificacion != null && !resultado.get(i).ultimaClasificacion().getNombre().equals(nombreClasificacion)
            		|| (fechaUltimoCambio !=null) && !resultado.get(i).ultimoCambioEstado().getFechaHoraCambio().toLocalDate().equals(fechaUltimoCambio))){	
            		resultado.remove(i);
            		i--;
            	}	
            }
            return resultado;   
        } catch (Exception e) {
            e.printStackTrace();
		return null;	
        }
	}

	public static GrupoResolucion buscarGrupoPorNombre(String nombreGrupo) {	
		EntityManager manager = emf.createEntityManager();
		GrupoResolucion grupo;
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
