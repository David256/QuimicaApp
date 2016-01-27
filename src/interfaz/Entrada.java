package interfaz;

import javax.swing.JTextField;

/**
 * Clase Entrada hereda de la clase JTextField y permite la creación de un campo
 * de entrada de texto.
 * 
 * @author David Elias
 * @version 1.0
 * @see javax.swing.JTextField
 * @see "Un buen libro de programación en Java"
 */
public class Entrada extends JTextField {

	/**
	 * Contructor que recibe un valor que mostrará en el campo de texto
	 * 
	 * @param valor
	 *            Variable String que define el texto que se mostrará en el
	 *            campo de texto
	 */
	public Entrada(String valor) {
		super(valor);
	}

	/**
	 * Constructor que no necesita recibir parámetro
	 */
	public Entrada() {
		super("");
	}

}
