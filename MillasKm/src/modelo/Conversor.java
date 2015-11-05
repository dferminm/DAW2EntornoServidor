package modelo;

public class Conversor {
	
	public Conversor(){
	}
	
	public double millasKm(double millas){
		return millas*1.609;
	}
	
	public double kmMillas(double km){
		return km*0.625;
	}

}
