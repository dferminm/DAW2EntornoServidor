package entidades;

import java.sql.Date;

public class Pedido {

	public Pedido() {
	}

	private int idpedido;
	private int idcliente;
	private String estado;
	private Date fecha;
	private String direcciondeenvio;
	private int factura;

	public int getIdpedido() {
		return idpedido;
	}

	public void setIdpedido(int idpedido) {
		this.idpedido = idpedido;
	}

	public int getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDirecciondeenvio() {
		return direcciondeenvio;
	}

	public void setDirecciondeenvio(String direcciondeenvio) {
		this.direcciondeenvio = direcciondeenvio;
	}

	public int getFactura() {
		return factura;
	}

	public void setFactura(int factura) {
		this.factura = factura;
	}

}
