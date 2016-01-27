package validacion;

/**
 * Esta clase tiene como función servir de cronometro cada vez que es llamado el
 * metodo diferencia()
 * 
 * @author David Elias
 * @version 1.1
 */

public class Cronometro {

	private long tiempo = 0;

	/**
	 * Método que retorna el tiempo trascurrido entre la anterior llamada, y la
	 * actual. El valor que retorna está en milisegundos
	 * 
	 * @return El valor diferencia es un dato de tipo long, y es la diferencia
	 *         entre la anterior llamada y esta
	 */
	public long diferencia() {
		// obtenemos el tiempo actual
		long nuevo = System.currentTimeMillis();
		// obtenemos la diferencia entre el nuevo valor y el anterior (el cual
		// es menor)
		long diferencia = nuevo - this.tiempo;
		// actualizamos y retornamos
		this.tiempo = nuevo;
		return diferencia;
	}
}
