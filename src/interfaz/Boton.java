package interfaz;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class Boton extends JButton{
	
	public Boton(String valor){
		super(valor);
		hacerBonito();
	}
	public Boton(){
		super("");
		hacerBonito();
	}
	
	public void hacerBonito(){
		this.setBackground(Color.WHITE);
		this.setForeground(Color.blue);
		this.setFont(new Font("Arial", Font.BOLD, 14));
	}
}
