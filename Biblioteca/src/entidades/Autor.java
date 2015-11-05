package entidades;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Autor {

	public Autor() {
	}

	private long idautor;
	private String nombre;
	private Date fecha;

	public long getIdautor() {
		return idautor;
	}

	public void setIdautor(long idautor) {
		this.idautor = idautor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha() {
		return fecha;
	}

	public String getFechaFormateado() {
		SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd-MM-yyyy");
		String fechaFormateada = formatoDeFecha.format((java.util.Date) fecha);
		return fechaFormateada;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
