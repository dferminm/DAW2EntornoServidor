package entidades;

public class Libro {

	public Libro() {
	}

	private long idautor;
	private String titulo;
	private String isbn;
	private String nombreAutor;
	private int ejemplaresTotales;
	private int ejemplaresEnPrestamo;
	private int ejemplaresDisponibles;

	public long getIdautor() {
		return idautor;
	}

	public void setIdautor(long idautor) {
		this.idautor = idautor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getNombreAutor() {
		return nombreAutor;
	}

	public void setNombreAutor(String nombreAutor) {
		this.nombreAutor = nombreAutor;
	}

	public int getEjemplaresTotales() {
		return ejemplaresTotales;
	}

	public void setEjemplaresTotales(int ejemplaresTotales) {
		this.ejemplaresTotales = ejemplaresTotales;
	}

	public int getEjemplaresEnPrestamo() {
		return ejemplaresEnPrestamo;
	}

	public void setEjemplaresEnPrestamo(int ejemplaresEnPrestamo) {
		this.ejemplaresEnPrestamo = ejemplaresEnPrestamo;
	}

	public int getEjemplaresDisponibles() {
		return ejemplaresDisponibles;
	}

	public void setEjemplaresDisponibles(int ejemplaresDisponibles) {
		this.ejemplaresDisponibles = ejemplaresDisponibles;
	}

}
