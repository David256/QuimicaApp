package base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Vector;

import javax.swing.JOptionPane;

import quimica.Electrodo;

public class BaseDato {
	File file = null;
	FileReader fr = null;
	BufferedReader br = null;

	Vector<String> lineas = new Vector();
	Vector<Electrodo> ele = new Vector();;

	public BaseDato() {
		System.out.println("Se lee la base de datos");
		try {
			file = new File("./res/database.csv");
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String linea = "";
			while (true) {
				linea = br.readLine();
				if (linea != null) {
					lineas.add(linea);
				} else {
					System.err.println("[Se acabó el archivo]");
					break;
				}
			}
			procesarDato(lineas);
		} catch (Exception gg) {
			JOptionPane.showMessageDialog(null,
					"No puedo abrir el archivo de base de datos por X o Y motivos:\n"
							+ gg.getMessage());
		} finally {
			try {
				if (fr != null) {
					fr.close();
				}
			} catch (Exception f) {
				f.printStackTrace();
			}
		}
	}

	private void procesarDato(Vector<String> lin) {
		for (int i = 1; i < lin.size(); i++) {
			String linealidad = lin.get(i);
			String[] partes = linealidad.split(";");

			String coeficiente1 = partes[0];
			String elemento1 = partes[1];
			String estado1 = partes[2];
			String exponente1 = partes[3];
			// String signoPlus = partes[4];
			String coeficiente2 = partes[5];
			String elemento2 = partes[6];
			String estado2 = partes[7];
			String exponente2 = partes[8];
			String electron = partes[9];
			// String signoIgual = partes[10];
			String rCoeficiente = partes[11];
			String rElemento = partes[12];
			String rExponente = partes[13];
			String rEstado = partes[14];
			String resultado = partes[15];

			// vamos a guardarlo para enscribirlo en el listado
			// Si existe un segundo elemento hay que incluirlo en la sopa
			String segundo = "";
			if (!coeficiente2.equals("") && !elemento2.equals("")
					&& !estado2.equals("") && !exponente2.equals("")) {
				// OSEA, esta mierda esta llena
				if (exponente2.equals("0")) {
					segundo = coeficiente2 + "(" + elemento2 + ")" + "¬("
							+ estado2 + ")";
				} else {
					segundo = coeficiente2 + "(" + elemento2 + ")" + "^("
							+ exponente2 + ")" + "¬(" + estado2 + ")";
				}
			}
			String cadena = "";
			String primero = "";
			if (exponente1.equals("0")) {
				primero = coeficiente1 + "(" + elemento1 + ")" + "¬(" + estado1
						+ ")";
			} else {
				primero = coeficiente1 + "(" + elemento1 + ")" + "^("
						+ exponente1 + ")" + "¬(" + estado1 + ")";
			}
			primero = primero + " + " + electron + "e-";
			cadena = primero + "" + segundo + " => ";
			String rr = "";
			if (rExponente.equals("0")) {
				rr = rCoeficiente + "(" + rElemento + ")" + "¬(" + rEstado
						+ ")";
			} else {
				rr = rCoeficiente + "(" + rElemento + ")" + "^(" + rExponente
						+ ")" + "¬(" + rEstado + ")";
			}
			cadena = cadena + rr + " | " + resultado;

			ele.add(new Electrodo(coeficiente1, elemento1, estado1, exponente1,
					coeficiente2, elemento2, estado2, exponente2, rCoeficiente,
					rElemento, rEstado, rExponente, resultado, cadena, electron));
		}
	}

	public Electrodo getElectrodoPorCadena(String textoDe) {
		if (ele.size() == 0 || ele == null) {
			System.err
					.println("Intento de Obtener Electrodo Por Cadena en null");
			return null;
		}
		System.out.println("Buscando " + textoDe + " ...");

		for (int i = 0; i < ele.size(); i++) {
			if (ele.get(i).toString().equals(textoDe)) {
				System.out.println("Encontrado (" + i + ") -> " + textoDe);
				return ele.get(i);
			}
		}
		System.err.println("Obtener Por Cadena No Encontrada NULL");
		return null;
	}

	public String[] getTodoEnArray() {
		String[] paco = new String[ele.size()];
		System.out.println("Todo se va al Array : " + ele.size());
		for (int i = 0; i < ele.size(); i++) {
			paco[i] = ele.get(i).toString();
		}
		return paco;
	}
}
