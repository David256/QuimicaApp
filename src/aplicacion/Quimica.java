package aplicacion;

import interfaz.Ventana;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Quimica {

	public static void main(String[] args) {
		Ventana ventana = new Ventana();

		ventana.setTitle("Química: Electroquímica - MegaProyecto <<Mecatrónico>> 2do");

		ventana.setSize(1100, 600);

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

		ventana.setLocation((dimension.width / 2) - 550,
				(dimension.height / 2) - 300);

		ventana.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
				// cerramos
			}
		});

		ventana.setResizable(false);

		ventana.setVisible(true);

		new Thread(ventana).start();

		try {
			Thread.sleep(2000);
		} catch (Exception e) {
		}
		ventana.labs.setVisible(true);
		new Thread(ventana.labs).start();
	}

}
