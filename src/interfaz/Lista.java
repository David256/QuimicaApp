package interfaz;

import java.util.Vector;

import javax.swing.JList;

/**
 * Esta clase hereda de la clase JList, y define un objeto que se mostrará.
 * 
 * @author David Elias
 * @version 1.5
 */
public class Lista extends JList {

	// esto recibirá el string , luego si se selecciona entonces lo buscará en
	// Electrodo, la clase, y obtendrá los elementos
	private Vector<String> elementos = new Vector();

	/**
	 * Constructor de la clase Lista.
	 */
	public Lista() {
		elementos.add("");
		this.setListData(elementos);
	}

	/**
	 * Agrega valores a la lista.
	 * 
	 * @param ee
	 *            String que se quiere agregar.
	 */
	public void agregar(String ee) {
		this.elementos.add(ee);
		this.setListData(elementos);
	}

	/**
	 * Este método retorna un valor del Vector interno, solo es necesario
	 * obtener el índice.
	 * 
	 * @param i
	 *            índice a buscar
	 * @return Valor miembro del vector
	 */
	public String getTextoDe(int i) {
		try {
			System.out
					.println("Aquí lista reportando el elemento seleccionado: "
							+ i);
			return elementos.get(i);
		} catch (Exception f) {
			return null;
		}
	}

}
