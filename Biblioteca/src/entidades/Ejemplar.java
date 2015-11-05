package entidades;

public class Ejemplar {

	public Ejemplar() {
	}

	private long idejemplar;
	private String isbn;
	private char baja;

	public long getIdejemplar() {
		return idejemplar;
	}

	public void setIdejemplar(long idejemplar) {
		this.idejemplar = idejemplar;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public char getBaja() {
		return baja;
	}

	public void setBaja(char baja) {
		this.baja = baja;
	}

}
