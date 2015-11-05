package entidades;

import java.sql.Date;

public class Prestamo {

	public Prestamo() {
	}

	private int idejemplar;
	private long idsocio;
	private Date fechaprestamo;
	private Date fechalimitedevolucion;
	private String titulo;
	private String nombre;
	private int diasdemora;

	public int getIdejemplar() {
		return idejemplar;
	}

	public void setIdejemplar(int idejemplar) {
		this.idejemplar = idejemplar;
	}

	public long getIdsocio() {
		return idsocio;
	}

	public void setIdsocio(long idsocio) {
		this.idsocio = idsocio;
	}

	public Date getFechaprestamo() {
		return fechaprestamo;
	}

	public void setFechaprestamo(Date fechaprestamo) {
		this.fechaprestamo = fechaprestamo;
	}

	public Date getFechalimitedevolucion() {
		return fechalimitedevolucion;
	}

	public void setFechalimitedevolucion(Date fechalimitedevolucion) {
		this.fechalimitedevolucion = fechalimitedevolucion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDiasdemora() {
		return diasdemora;
	}

	public void setDiasdemora(int diasdemora) {
		this.diasdemora = diasdemora;
	}

}
