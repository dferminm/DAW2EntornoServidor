package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the PRESTAMO database table.
 * 
 */
@Entity
@NamedQuery(name="Prestamo.findAll", query="SELECT p FROM Prestamo p")
public class Prestamo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long idejemplar;
	private long idsocio;

	@Temporal(TemporalType.DATE)
	private Date fechalimitedevolucion;

	@Temporal(TemporalType.DATE)
	private Date fechaprestamo;

	//uni-directional one-to-one association to Ejemplar
	@OneToOne
	@JoinColumn(name="IDEJEMPLAR", insertable=false, updatable=false)
	private Ejemplar ejemplar;

	//bi-directional many-to-one association to Socio
	@ManyToOne
	@JoinColumn(name="IDSOCIO", insertable=false, updatable=false)
	private Socio socio;

	public Prestamo() {
	}

	public long getIdejemplar() {
		return this.idejemplar;
	}

	public void setIdejemplar(long idejemplar) {
		this.idejemplar = idejemplar;
	}

	public Date getFechalimitedevolucion() {
		return this.fechalimitedevolucion;
	}

	public void setFechalimitedevolucion(Date fechalimitedevolucion) {
		this.fechalimitedevolucion = fechalimitedevolucion;
	}

	public Date getFechaprestamo() {
		return this.fechaprestamo;
	}

	public void setFechaprestamo(Date fechaprestamo) {
		this.fechaprestamo = fechaprestamo;
	}

	public Ejemplar getEjemplar() {
		return this.ejemplar;
	}

	public void setEjemplar(Ejemplar ejemplar) {
		this.ejemplar = ejemplar;
	}

	public Socio getSocio() {
		return this.socio;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
	}

	public long getIdsocio() {
		return idsocio;
	}

	public void setIdsocio(long idsocio) {
		this.idsocio = idsocio;
	}

}