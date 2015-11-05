package entidades;

public class Socio {
	private long idsocio;
	private String email;
	private String nombre;
	private String direccion;
	private String password;

	public Socio() {

	}

	public long getIdsocio() {
		return idsocio;
	}

	public void setIdsocio(long idsocio) {
		this.idsocio = idsocio;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
