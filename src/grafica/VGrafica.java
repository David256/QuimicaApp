package grafica;

import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JFrame;

public class VGrafica extends JFrame {

	private Vector<Double> dominio = new Vector();

	public VGrafica(Vector<Double> dominio) {
		setBounds(100, 100, 500, 500);
		this.dominio = dominio;
		this.setResizable(false);

	}

	public void paint(Graphics g) {
		super.paint(g);
		dubujarEjes(g);
		graficar(g);
	}

	private void graficar(Graphics g) {
		for (int i = 0; i < this.dominio.size(); i++) {
			g.fillRect(getX(i * 25), this.dominio.get(i).intValue(), 4, 4);
			try {
				g.drawLine(getX((i - 1) * 25), this.dominio.get(i - 1)
						.intValue(), getX(i * 25), this.dominio.get(i)
						.intValue());
			} catch (Exception gg) {

			}
		}
	}

	private void dubujarEjes(Graphics g) {
		g.drawLine(getX(), getY(), getX(), getY(440));
		g.drawLine(getX(), getY(), getX(460), getY());
		for (int i = 0; i < 20; i++) {
			g.fillRect(getX(), getY(i * 25), 3, 3);
			g.fillRect(getX(i * 25), getY(), 3, 3);
		}
	}

	public int getY(int y) {
		return 500 - 20 - y;
	}

	public int getX(int x) {
		return 20 + x;
	}

	public int getX() {
		return 20;
	}

	public int getY() {
		return 500 - 20;
	}

}
