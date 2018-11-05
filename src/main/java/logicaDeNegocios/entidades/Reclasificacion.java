package logicaDeNegocios.entidades;

import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Table(name = "Reclasificacion")

public class Reclasificacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idRecla")
	private Integer idRecla;	
	
	@Column(name = "fechaReclasificcacion")
	private LocalDateTime fechaReclasificacion;
	
	@ManyToOne
	@JoinColumn(name = "usuario")
	private Usuario usuario;
	
	@OneToMany(fetch = FetchType.LAZY)//no se mapear esto * Arreglar
	private Clasificacion clasificacionAnterior;
	
	@OneToMany(fetch = FetchType.LAZY)//no se mapear esto * Arreglar
	private Clasificacion clasificacionNueva;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "historialReclasificacion")
	private Ticket ticket;
	
	
}
