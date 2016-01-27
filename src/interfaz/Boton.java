package interfaz;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

/**
 * Esta clase define a un botón de forma particular
 * 
 * @author David Elias
 * @version 1.5
 */
public class Boton extends JButton {

	/**
	 * Contructor de la clase.
	 * 
	 * @param valor
	 *            Variable de tipo String que define el texto del botón.
	 */
	public Boton(String valor) {
		super(valor);
		// ahora llamamos al método que nos ayudará un montón
		hacerBonito();
	}

	/**
	 * Contructor de la clase, pero esta no tiene parametro. En este caso es
	 * vacio
	 */
	public Boton() {
		super("");
		// ahora llamamos al método que nos ayudará un montón
		hacerBonito();
	}

	/**
	 * Método que formatea el objeto: le coloca color de fondo y a la letra,
	 * también define una tipográfica y su respectivo estilo.
	 */
	public void hacerBonito() {
		this.setBackground(Color.WHITE);
		this.setForeground(Color.blue);
		this.setFont(new Font("Arial", Font.BOLD, 14));
	}
}
