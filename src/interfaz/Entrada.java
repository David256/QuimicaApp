package interfaz;

import javax.swing.JTextField;

/**
 * Clase Entrada hereda de la clase JTextField y permite la creaci�n de un campo
 * de entrada de texto.
 * 
 * @author David Elias
 * @version 1.0
 * @see javax.swing.JTextField
 * @see "Un buen libro de programaci�n en Java"
 */
public class Entrada extends JTextField {

	/**
	 * Contructor que recibe un valor que mostrar� en el campo de texto
	 * 
	 * @param valor
	 *            Variable String que define el texto que se mostrar� en el
	 *            campo de texto
	 */
	public Entrada(String valor) {
		super(valor);
	}

	/**
	 * Constructor que no necesita recibir par�metro
	 */
	public Entrada() {
		super("");
	}

}
