package aplicacion;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

// TODO: corregir esto tan pronto como sea posible
public class Best extends JFrame {
	JPanel panel;
	Vector<String[]> vector;

	// DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();

	public Best() {
		setTitle("Graph of Volt and Concentration");
		setSize(800, 600);
		setLocationRelativeTo(null);
		// setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void setValores(Vector<String[]> valor) {
		for (int i = 0; i < valor.size(); i++) {
			// line_chart_dataset.addValue(Integer.parseInt(valor.get(i)[0]),
			// valor.get(i)[1], valor.get(i)[2]);
		}
	}

	public void setPartes(String[] hola) {
		// this.line_chart_dataset.addValue(Double.parseDouble(hola[0]),
		// hola[1],
		// hola[2]);
	}

	public void init() {
		panel = new JPanel();
		getContentPane().add(panel);
		// Creando el Grafico
		// JFreeChart chart = ChartFactory
		// .createLineChart("Graph of Volt and Concentration",
		// "Concentration", "Volt", line_chart_dataset,
		// PlotOrientation.VERTICAL, true, true, false);
		// Mostrar Grafico
		// ChartPanel chartPanel = new ChartPanel(chart);
		// panel.add(chartPanel);
	}
}
