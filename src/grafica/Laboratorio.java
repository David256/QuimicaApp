package grafica;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Laboratorio extends JFrame implements Runnable {
	private String lbl1 = "element 1";
	private String lbl2 = "element 2";
	private String volts = "0";
	private String e1 = "semi volts 1";
	private String e2 = "semi volts 2";

	private final Color MARRON = new Color(78, 71, 47);
	private final Color NEGRO = new Color(0, 0, 0);
	private final Color AMARILLO = new Color(255, 255, 0);
	private final Color METAL = new Color(110, 110, 88);
	private final Color ROJO = new Color(255, 0, 0);
	private final Color CABLE = new Color(78, 71, 18);
	private final Color BLANCO = new Color(255, 255, 255);
	private final Color AGUJA = new Color(195, 135, 0);
	// colores de compuestos
	private final Color CO1 = new Color(130, 150, 10);
	private final Color CO2 = new Color(125, 60, 80);
	private final Color CO3 = new Color(70, 115, 75);
	private final Color SAL = new Color(205, 205, 205);

	public Laboratorio() {
		setTitle("Eletro-Labs");
		setSize(500, 400);
		setLocationRelativeTo(null);
	}

	public void paint(Graphics g) {
		super.paint(g);
		// vaso uno
		g.setColor(new Color(33, 33, 33));
		g.fillRect(50, 220, 2, 100);
		g.fillRect(150, 220, 2, 100);
		g.fillRect(50, 318, 100, 2);

		// TODO tienes las variables al reves en la clase Ventana, aca no, pero
		// bueno, toco cambiarlas :S
		try {
			if (this.e1.equals("1")) {
				this.dibujarLiquido(Color.green, g, 50, 220, 30);
			} else if (this.e1.equals("2")) {
				this.dibujarLiquido(Color.green, g, 50, 220, 60);
			} else if (this.e1.equals("3")) {
				this.dibujarLiquido(Color.green, g, 50, 220, 90);
			}
		} catch (Exception rr) {
		}

		// vaso dos
		g.setColor(new Color(33, 33, 33));
		g.fillRect(350, 220, 2, 100);
		g.fillRect(450, 220, 2, 100);
		g.fillRect(350, 318, 100, 2);

		try {
			if (this.e2.equals("1")) {
				this.dibujarLiquido(Color.PINK, g, 350, 220, 30);
			} else if (this.e2.equals("2")) {
				this.dibujarLiquido(Color.PINK, g, 350, 220, 60);
			} else if (this.e2.equals("3")) {
				this.dibujarLiquido(Color.PINK, g, 350, 220, 90);
			}
		} catch (Exception rr) {
		}

		// puente salino
		g.setColor(SAL);
		g.fillRect(120, 200, 6, 115);
		//
		g.fillRect(370, 200, 6, 115);
		//
		g.fillRect(120, 200, 250, 6);
		g.setColor(NEGRO);
		g.drawString("salt bridge", 220, 220);

		// multimetro
		g.setColor(METAL);
		g.fillOval(230, 80, 50, 50);
		g.setColor(BLANCO);
		g.fillOval(233, 83, 44, 44);
		g.setColor(NEGRO);
		g.fillOval(250, 110, 10, 10);

		// barra de electrodos
		g.setColor(MARRON);
		g.fillRect(80, 200, 5, 100);
		g.fillRect(420, 200, 5, 100);

		// cable A1 & B1
		g.setColor(CABLE);
		g.drawLine(80, 200, 230, 105);
		g.drawLine(280, 105, 420, 200);

		// nombre
		g.setColor(NEGRO);
		// this.lbl1 = "lorem ipsum";this.lbl2 = "lorem ipsum";this.volts =
		// "150";
		// g.setFont(new Font("consolas", Font.CENTER_BASELINE, 16));
		g.drawString(this.lbl1, 50, 350);
		g.drawString(this.lbl2, 350, 350);
		g.drawString(this.volts + "V", 200, 80);

		dibujarAguja(g, 255, 115, this.volts);
	}

	private void dibujarAguja(Graphics g, int x, int y, String voltaje) {
		g.setColor(AGUJA);
		double grados = 90.0 / (6 - Double.parseDouble(voltaje)); // daria 15°
		grados = grados + 45;
		/*
		 * al estar en 0 esta a -45° (-20,20)
		 */
		int conX = (int) ((int) 28 * Math.cos(Math.toRadians(grados)));
		int conY = (int) ((int) 28 * Math.sin(Math.toRadians(grados)));

		g.drawLine(x, y, x - conX, y - 20);

		g.setColor(NEGRO);
	}

	@Override
	public void run() {
		while (true) {
			try {
				repaint();
				Thread.sleep(5000);
			} catch (Exception d) {

			}
		}
	}

	public void dibujarLiquido(Color color, Graphics g, int x, int y, int valor) {
		g.setColor(color);
		int newX = x + 3;
		int newY = (y + 100 - 2) - valor;
		g.fillRect(newX, newY, 96, valor);
		g.setColor(Color.black);
	}

	public void setL1(String l) {
		this.lbl1 = l;
	}

	public void setL2(String l) {
		this.lbl2 = l;
	}

	public void setV(String v) {
		this.volts = v;
	}

	public void setE1(String e) {
		this.e1 = e;
	}

	public void setE2(String e) {
		this.e2 = e;
	}

}
