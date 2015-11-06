package carrocompra;

import java.util.ArrayList;

import entidades.Producto;

public class CarroCompra {
	private ArrayList<Producto> elementos;

	public CarroCompra() {
		elementos = new ArrayList<Producto>();
	}
	
	public void setElementos(ArrayList<Producto> elementos) {
		this.elementos = elementos;
		}
	
	public ArrayList<Producto> getElementos() {
		return elementos;
	}
	
	public void addElemento(Producto p){
		int posicion=elementos.indexOf(p);
		if(posicion==-1){
		elementos.add(p);
		}
		else{
		Producto actual = elementos.get(posicion);
		actual.setCantidad(actual.getCantidad()+1);
		}
		}
	
	public void disminuirElemento(Producto p){
		int posicion = elementos.indexOf(p);
		Producto actual = elementos.get(posicion);
		actual.setCantidad(actual.getCantidad()-1);
		if(actual.getCantidad() == 0){
			elementos.remove(posicion);
		}
	}
	
	public void eliminarElemento(Producto p){
		elementos.remove(p);
		}
	
	public long precioTotal(){
		long total = 0;
		for(Producto p : elementos){
			total = total + (p.getPrecioNormal()*p.getCantidad());
		}
		return total;
	}
	}
