package grafica;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class VGraficaBeta extends JFrame {
	JPanel panel = new JPanel();
	private String[] dato;
	private int X = 20;
	private int sizeY = 500;

	public VGraficaBeta() {
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);

		setContentPane(panel);
	}

	public void mostrar(String[] dato) {
		this.dato = dato;
	}

	public void paint(Graphics g) {
		super.paint(g);
		this.sizeY = this.getSize().height;

		g.setColor(Color.black);
		// dibujamos linea para el eje
		int i = 0;
		while (i < (this.dato.length)) {
			puntoEn(g, getRealX(0), getRealY(i * 46), Color.green);
			g.drawLine(getRealX(0), getRealY(i * 46), getRealX(0),
					getRealY(i * 46) - 46);
			i++;
		}
		i = 0;
		while (i < (this.dato.length)) {
			puntoEn(g, getRealX(i * 46), getRealY(0), Color.green);
			g.drawLine(getRealX(i * 46), getRealY(0), getRealX(i * 46) + 46,
					getRealY(0));
			i++;
		}
		puntoEn(g, getRealX(0), getRealY(0));
		// intentamos graficar los valores que recibimos
		for (int a = 0; a < this.dato.length; a++) {
			puntoEn(g, getRealX(a * 46),
					getRealY(Integer.parseInt(this.dato[a]) * 46));
			g.drawString(this.dato[a], getRealX(a * 46) + 10,
					getRealY(Integer.parseInt(this.dato[a]) * 46) + 10);
			// intento de hacer una linea:
			g.setColor(new Color(200, 200, 200));
			if (a == 0) {
				g.drawLine(getRealX(0), getRealY(0), getRealX(a * 46),
						getRealY(Integer.parseInt(this.dato[a]) * 46));
			} else {
				g.drawLine(getRealX((a - 1) * 46),
						getRealY(Integer.parseInt(this.dato[(a - 1)]) * 46),
						getRealX(a * 46),
						getRealY(Integer.parseInt(this.dato[a]) * 46));
			}
			g.setColor(Color.BLACK);
		}
	}

	public int getX() {
		return this.X;
	}

	public void puntoEn(Graphics g, int x, int y) {
		g.setColor(Color.blue);
		g.fillOval(x, y, 5, 5);
		g.setColor(Color.black);
	}

	public void puntoEn(Graphics g, int x, int y, Color c) {
		g.setColor(c);
		g.fillOval(x, y, 5, 5);
		g.setColor(Color.black);
	}

	public int getMayor(boolean mayor) {
		if (mayor) {
			int interno = 0;
			for (int i = 0; i < this.dato.length; i++) {
				if (Integer.parseInt(this.dato[i]) > interno) {
					interno = Integer.parseInt(this.dato[i]);
				}// de lo contrario no hacemos nada porque nos pide el mayor :P
			}
			return interno;
		} else {
			int interno = 9999;
			for (int i = 0; i < this.dato.length; i++) {
				if (Integer.parseInt(this.dato[i]) < interno) {
					interno = Integer.parseInt(this.dato[i]);
				}
			}
			return interno;
		}
	}

	public int getRealY(int x) {
		// dejamos un marguen de 20px por cada eje
		return this.sizeY - x - 40;
	}

	public int getRealX(int x) {
		// dejamos un marguen de 20px por cada eje
		return x + 40;
	}
}
