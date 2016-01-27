package interfaz;

import javax.swing.JLabel;

/**
 * Clase que hereda de JLabel, y permite crear etiquetas
 * 
 * @author David Elias
 * @version 1.1
 * @see javax.swing.JLabel
 */
public class Etiqueta extends JLabel {

	/**
	 * Contructor de la clase, la cual necesita un valor para mostrar en la
	 * etiqueta.
	 * 
	 * @param valor
	 *            Este será el valor que es mostrará
	 */
	public Etiqueta(String valor) {
		super(valor);
	}

	/**
	 * Contructor de la clase, el cual crea una etiqueta vacia, o sea, sin
	 * texto.
	 */
	public Etiqueta() {
		super("");
	}

}
