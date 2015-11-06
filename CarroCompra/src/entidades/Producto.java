package entidades;

public class Producto {

	public Producto() {
	}

	private int id;
	private String nombre;
	private long precioNormal;
	private int cantidad;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getPrecioNormal() {
		return precioNormal;
	}

	public void setPrecioNormal(long precioNormal) {
		this.precioNormal = precioNormal;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public boolean equals(Object q) {
		if (q == null)
			return false;
		if (q == this)
			return true;
		if (!(q instanceof Producto))
			return false;
		Producto p = (Producto) q;
		if (id == (p.getId()))
			return true;
		else
			return false;
	}
	
}
