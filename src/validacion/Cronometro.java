package validacion;

public class Cronometro {
	long tiempo = 0;
	
	public long diferencia(){
		long nuevo = System.currentTimeMillis();
		long diferencia = nuevo - this.tiempo;
		this.tiempo = nuevo;
		return diferencia;
	}
}
