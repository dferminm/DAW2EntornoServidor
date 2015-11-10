package entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the EJEMPLAR database table.
 * 
 */
@Entity
@NamedQuery(name="Ejemplar.findAll", query="SELECT e FROM Ejemplar e")
public class Ejemplar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long idejemplar;

	private String baja;

	//uni-directional many-to-one association to Libro
	@ManyToOne(cascade={CascadeType.REMOVE})
	@JoinColumn(name="ISBN")
	private Libro libro;

	public Ejemplar() {
	}

	public long getIdejemplar() {
		return this.idejemplar;
	}

	public void setIdejemplar(long idejemplar) {
		this.idejemplar = idejemplar;
	}

	public String getBaja() {
		return this.baja;
	}

	public void setBaja(String baja) {
		this.baja = baja;
	}

	public Libro getLibro() {
		return this.libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

}