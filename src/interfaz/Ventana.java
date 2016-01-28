package interfaz;

import grafica.Laboratorio;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import quimica.Electrodo;
import validacion.Cronometro;
import base.BaseDato;

/**
 * La clase Ventana es la encargada de dibujar en pantalla una pantalla con
 * tamaño fijo. Esta clase hereda de la clase JFrame e implementa las interfaces
 * ActionListener, Runnable y ListSelectionListener: lo que le da a la clase la
 * posibilidad de persibir las pulsaciones en los botones, también la
 * posibilidad de correr un método en otro hilo y finalmente la interfaz que
 * permite detectar selección en objecto de la clase Lista.
 * 
 * @see Javax.swing.JFrame
 * @see java.awt.event.ActionListener
 * @see java.lang.Runnable
 * @see javax.swing.event.ListSelectionListener
 * 
 * @author David Elias
 * @version 4.4
 */
public class Ventana extends JFrame implements ActionListener, Runnable,
		ListSelectionListener {

	/**
	 * Hace referencia a un objeto de la clase Capa, el cual permite agregar
	 * objetos de tipo Component.
	 * 
	 * @see java.awt.Component
	 */
	private Capa capa = new Capa();

	private JLabel btnHelp = new JLabel("Help!");
	private Boton btnCalcular = new Boton("Balance Equation");
	private Boton btnGraficar = new Boton("Graph");

	/**
	 * Este es un objeto de la clase Laboratorio que permite visualizar un
	 * ejemplar de un laboratorio.
	 * 
	 * @see grafica.Laboratorio
	 */
	public Laboratorio labs = new Laboratorio();

	// ahora creamos las entradas de textos
	private JEditorPane balanceada = new JEditorPane(); // se muestra la
														// ecuacion balanceada
	private Entrada txtSemiPotencia1 = new Entrada(); // se muestra la semi
														// potencial
	private Entrada txtSemiPotencia2 = new Entrada(); // se muestra la semi
														// potencial
	private Entrada txtEcuacionGeneral = new Entrada(); // se muestra la
														// ecuacion completa

	// la seccion de calculos dependiendo de la T y la P
	private Entrada txtTemperatura = new Entrada("273"); // entrada de
															// temperatura
	private Entrada txtPresion = new Entrada("1"); // entrada de presion

	// ahora la concentración
	private Entrada enA = new Entrada(); // concentracion A
	private Entrada enB = new Entrada(); // B
	private Entrada enC = new Entrada(); // C
	private Entrada enD = new Entrada(); // D

	private Entrada exA = new Entrada(); // Exponente A
	private Entrada exB = new Entrada(); // B
	private Entrada exC = new Entrada(); // C
	private Entrada exD = new Entrada(); // D

	/*
	 * Ahora definimos los Label o etiquetas
	 */
	private Etiqueta titulo = new Etiqueta("Electrochemical");
	private Etiqueta lblElemento = new Etiqueta("Element:");
	private Etiqueta lblValorValorE = new Etiqueta("E° = 0V");
	// private Etiqueta lblValorE = new Etiqueta("Standard cell value:");
	private Etiqueta lblValorPila = new Etiqueta("E(cell) = 0V");
	private Etiqueta lblNombreFormula = new Etiqueta("Nernst equation");
	private Etiqueta lblLetraT = new Etiqueta("T(Kelvin): ");
	private Etiqueta lblLetraP = new Etiqueta("P(Atm): ");
	private Etiqueta lblLetraQ = new Etiqueta("Q =");
	private Etiqueta lblEcuacionGeneral = new Etiqueta("general equation:");
	private Etiqueta lblNoElectrones = new Etiqueta("Electrons Transferred: 0");

	// objetos necesarios
	private Electrodo eee1 = null;
	private Electrodo eee2 = null;
	private BaseDato db;

	private JEditorPane texto = new JEditorPane();
	private JScrollPane scroll1 = new JScrollPane();
	private JScrollPane scroll2 = new JScrollPane();
	private Lista lista1 = new Lista();
	private Lista lista2 = new Lista();
	private JLabel crom = new JLabel("0");
	private Cronometro chrome = new Cronometro();
	private int transferidos = 0;
	private String globalE = "";
	private String voltajePila = "0";

	/**
	 * Constructor de la clase Ventana
	 */
	public Ventana() {
		/*
		 * Referido a nada en especifico
		 */
		capa.setBackground(new Color(240, 240, 240));
		capa.setLayout(null);

		// BOTON AYUDA
		btnHelp.setBounds(1020, 0, 120, 50);
		btnHelp.setFont(new Font("calibri", Font.BOLD | Font.ITALIC, 18));
		btnHelp.setForeground(new Color(00, 00, 200));
		btnHelp.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				JOptionPane
						.showMessageDialog(
								null,
								"Hello\n"
										+ "\nIn this session, we're going to explain how works this App, so now we're going to say you something\n"
										+ "this App has a data base in format CSV, you can read from Excel as long as you don't add it more styles of Microsoft Excel\n"
										+ "\nThe user must choose between the two list the elemento and push [Calculate], later you will get E°\n"
										+ "Now, for get an exact value, you should give it the temperatura (T) and the pressure (P) and the value of\n"
										+ "electrons transferred (n). Also, you can write there the concentration if you want a measure more useful.\n"
										+ "\n\nThre is also, the function what can graph, this will be of very much help to the user.\n\n"
										+ "[Enjoy the application]");
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnHelp.setForeground(new Color(00, 00, 255));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				btnHelp.setForeground(new Color(00, 00, 200));

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				btnHelp.setForeground(new Color(255, 00, 00));
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				btnHelp.setForeground(new Color(00, 255, 00));

			}

		});
		capa.add(btnHelp);

		// TITULO
		titulo.setBounds(20, 20, 300, 50);
		titulo.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 30));
		titulo.setForeground(Color.red);
		capa.add(titulo);

		/*
		 * Referido a la entrada de datos para comenzar, esto es lo más
		 * importante en la vida
		 */

		// LABEL ELEMENTO
		lblElemento.setBounds(20, 80, 200, 25);
		lblElemento.setFont(new Font("consolas", Font.CENTER_BASELINE, 14));
		capa.add(lblElemento);

		scroll1.setBounds(20, 110, 400, 120);
		scroll1.setViewportView(lista1);
		lista1.addListSelectionListener(this);
		capa.add(scroll1);

		scroll2.setBounds(20, 240, 400, 120);
		scroll2.setViewportView(lista2);
		lista2.addListSelectionListener(this);
		capa.add(scroll2);

		texto.setBounds(20, 370, 400, 120);
		texto.setContentType("text/html");
		texto.setFont(new Font("verdana", Font.CENTER_BASELINE, 9));
		texto.setText("<html><font size='18' family='verdana'>Hello, I'm an application</font><br><p>Use me, and if you need help you can push <span color='green'>[Help]</span> or <span color='green'>[How to use it]</span></p></html>");
		capa.add(texto);

		/*
		 * Referido a la seccion de balanceo y de calculo de carga electrica
		 * estandar
		 */
		// LABEL "BALANCEAR"
		// 20, 500, 150, 25

		balanceada.setContentType("text/html");
		balanceada.setBounds(20, 535, 400, 35);
		balanceada.setFont(new Font("consolas", Font.ITALIC, 16));
		capa.add(balanceada);

		btnCalcular.setBounds(20, 500, 230, 30);
		capa.add(btnCalcular);

		// cronometro
		crom.setBounds(1020, 550, 200, 25);
		capa.add(crom);

		// SEGUNDA PARTE DEL LA APP
		/*
		 * lblValorE.setBounds(500, 20, 250, 40); //debe ser esto grande
		 * lblValorE.setFont(new Font("consolas", Font.CENTER_BASELINE, 18));
		 * capa.add(lblValorE);
		 */

		lblValorValorE.setBounds(300, 500, 398, 25);
		lblValorValorE.setFont(new Font("consolas", Font.CENTER_BASELINE, 16));
		capa.add(lblValorValorE);

		lblNoElectrones.setBounds(300, 512, 200, 25);
		capa.add(lblNoElectrones);

		// lblValorPila.setBounds(300, 300, 300, 300);

		lblNombreFormula.setBounds(500, 30, 300, 30); // vamos a subirlo 180px
		lblNombreFormula.setFont(new Font("consolas", Font.BOLD, 26));
		capa.add(lblNombreFormula);
		// mostrar la formula, que pereza
		JEditorPane nformula = new JEditorPane();
		nformula.setContentType("text/html");
		nformula.setFont(new Font("Arial", Font.ITALIC, 50));

		nformula.setText("<html><p><i><font size='60'>E</font><sub>cell</sub> <font size='20'>=</font> <font size='55'>E</font>"
				+ "<sup>°</sup><sub>cell</sub> <font size='30'>-</font> <font size='55'>(</font>"
				+ "<font size='40'>RT / nF</font>"
				+ "<font size='55'>)</font>"
				+ "<font size='40'>LnQ</font>" + "</i></p></html>");

		nformula.setBounds(500, 60, 400, 100);
		capa.add(nformula);

		lblLetraT.setBounds(500, 200, 200, 30);
		lblLetraT.setFont(new Font("consolas", Font.CENTER_BASELINE, 20));
		capa.add(lblLetraT);

		lblLetraP.setBounds(670, 200, 100, 30);
		lblLetraP.setFont(new Font("consolas", Font.CENTER_BASELINE, 20));
		capa.add(lblLetraP);

		txtTemperatura.setBounds(500, 230, 100, 30);
		txtTemperatura.setFont(new Font("verdana", Font.ITALIC, 14));
		capa.add(txtTemperatura);

		txtPresion.setBounds(670, 230, 100, 30);
		txtPresion.setFont(new Font("verdana", Font.ITALIC, 14));
		capa.add(txtPresion);

		lblValorPila.setBounds(450, 450, 800, 40); // debe ser esto grande
		lblValorPila.setFont(new Font("consolas", Font.ITALIC, 40));
		capa.add(lblValorPila);

		lblLetraQ.setBounds(510, 350, 100, 30);
		lblLetraQ.setFont(new Font("Centaur", Font.CENTER_BASELINE, 20));
		capa.add(lblLetraQ);

		enA.setBounds(560, 320, 80, 30);
		enA.setFont(new Font("verdana", Font.ITALIC, 14));
		capa.add(enA);
		enB.setBounds(660, 320, 80, 30);
		enB.setFont(new Font("verdana", Font.ITALIC, 14));
		capa.add(enB);
		enC.setBounds(560, 380, 80, 30);
		enC.setFont(new Font("verdana", Font.ITALIC, 14));
		capa.add(enC);
		enD.setBounds(660, 380, 80, 30);
		enD.setFont(new Font("verdana", Font.ITALIC, 14));
		capa.add(enD);

		exA.setBounds(620, 300, 20, 20);
		capa.add(exA);
		exB.setBounds(720, 300, 20, 20);
		capa.add(exB);
		exC.setBounds(620, 360, 20, 20);
		capa.add(exC);
		exD.setBounds(720, 360, 20, 20);
		capa.add(exD);

		// TODO corregir la temperatura

		lblEcuacionGeneral.setBounds(510, 470, 400, 30);
		lblEcuacionGeneral.setFont(new Font("consolas", Font.CENTER_BASELINE,
				16));

		btnGraficar.setBounds(920, 500, 150, 50);
		btnGraficar.setFont(new Font("verdana", Font.BOLD, 20));
		capa.add(btnGraficar);

		escucharTodo();
		add(capa);

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawLine(550, 383, 750, 383);
	}

	/**
	 * Método encargado de activar la escucha a evento en los dos botones
	 */
	private void escucharTodo() {
		btnCalcular.addActionListener(this);
		btnGraficar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object origen = e.getSource();

		if (origen == btnCalcular) {
			if (eee1 == null || eee1.equals("")) {
				JOptionPane.showMessageDialog(this,
						"there isn't a semi-reaction #1");
				return;
			}
			if (eee2 == null || eee2.equals("")) {
				JOptionPane.showMessageDialog(this,
						"there isn't a semi-reaction #2");
				return;
			}

			// bueno vamos a calcular esta mierda!!!!

			String coParaA = Integer.toString(Math.abs(Integer
					.parseInt(eee2.eletron)));
			String coParaB = Integer.toString(Math.abs(Integer
					.parseInt(eee1.eletron)));

			// JOptionPane.showMessageDialog(null, coParaA + ", " + coParaB);

			String balance = "";
			balance = procesarBalance(eee1, eee2, coParaA, coParaB);
			balanceada.setText(balance);

			// calculamos el potencial estandar
			String laPotencia = procesarDiferentialPotencia(eee1.resultado,
					eee2.resultado);
			try {
				lblValorValorE.setText("E° = " + laPotencia.substring(0, 6)
						+ "V");
			} catch (Exception h) {
				lblValorValorE.setText("E° = " + laPotencia + "V");
			}
		}

		if (origen == btnGraficar) {
			// TODO: esto no está bien definido, no da lo que debe hacer :/
			/*
			 * int ss=JOptionPane.showConfirmDialog(null,
			 * "Do you make sure?, the function of graph doesn't work well");
			 * if(ss==JOptionPane.CANCEL_OPTION ||
			 * ss==JOptionPane.NO_OPTION){return;} Vector<Double> dominio = new
			 * Vector(); for(int i=0; i<20; i++){
			 * dominio.add(Double.parseDouble(
			 * CeldaA(Double.parseDouble(this.enA.getText()) - i))); } VGrafica
			 * gg = new VGrafica(dominio); gg.setVisible(true);
			 */

			// Best goo = new Best();

			for (double i = 0.1; i <= 2.0; i = i + 0.1) {
				double vol = Double.parseDouble(CeldaA(i));
				String etiqueta = Double.toString(i);
				String linea = "volt";
				String[] todo = { Double.toString(vol), linea, etiqueta };
				// goo.setPartes(todo);
			}
			// goo.init();
		}

	}

	/**
	 * Este método compara el potencial energetico (de preferencia, general) y
	 * saca la diferencia. Unos de los problemas que puede pasar es que se haya
	 * escogido dos elementos con igual potencial, dando valores fuera de lo
	 * esperado. Al final retornamos la diferencia.
	 * 
	 * @param celdaA
	 *            String obtenido de la GUI, correspondiete el potencial de la
	 *            celda A
	 * @param celdaB
	 *            String obtenido de la GUI, correspondiete el potencial de la
	 *            celda B
	 * @return valor Double que indica la diferencia de potencial
	 */
	private String procesarDiferentialPotencia(String celdaA, String celdaB) {
		String valor = "";

		double cellA = Double.parseDouble(celdaA.replace(",", "."));
		double cellB = Double.parseDouble(celdaB.replace(",", "."));
		if (cellA > cellB) {
			valor = Double.toString(cellA - cellB);
		} else if (cellB > cellA) {
			valor = Double.toString(cellB - cellA);
		} else {
			JOptionPane
					.showMessageDialog(
							this,
							"hey guy, apparently you're using equal values of potential energy, you have to watch it. Anyway, I'll show you");
			valor = "0";
		}
		this.globalE = valor;
		return valor;
	}

	/**
	 * Este método "balancea". Su función es balancear mediante técnicas de
	 * manejo de String, con un simple algoritmo la función puede balancear con
	 * certeza.
	 * 
	 * @param AA
	 *            Objeto de tipo Electrodo, referido a la semicelda A
	 * @param BB
	 *            Objeto de tipo Electrodo, referido a la semicelda B
	 * @param A
	 *            String que indica el coeficiente de la molecula A
	 * @param B
	 *            String que indica el coeficiente de la molecula A
	 * @return String que representa una ecuación química balanceada y
	 *         marquetada con HTML
	 */
	private String procesarBalance(Electrodo AA, Electrodo BB, String A,
			String B) {
		String todo = "";
		// añadimos electrones a A
		todo = todo + "<span color='green'>" + A + "</span>(";

		if (!AA.coeficiente1.equals("1")) {
			todo = todo + "(<span color='blue'>" + AA.coeficiente1 + "</span>"
					+ AA.elemento1;
		}
		todo = todo + AA.elemento1;
		// agregamos exponentes
		if (!AA.exponente1.equals("0")) {
			todo = todo + "<sup>" + AA.exponente1 + "</sup>";
		}
		// agregamos estados
		todo = todo + "<sub><b>(" + AA.estado1 + ")</b></sub>";

		if (!AA.elemento2.equals("") && !AA.coeficiente2.equals("")
				&& !AA.estado2.equals("") && !AA.exponente2.equals("")) {

			todo = todo + " + ";
			if (!AA.coeficiente2.equals("1")) {
				todo = todo + "<span color='blue'>" + AA.coeficiente2
						+ "</span>";
			}
			todo = todo + AA.elemento2;
			if (!AA.exponente2.equals("0")) {
				todo = todo + "<sup>" + AA.exponente2 + "</sup>";
			}
			todo = todo + "<sub><b>(" + AA.estado2 + ")</b></sub>";
		}

		todo = todo + ") + ";
		// ahora tenermos que agregar el otro compuesto :/
		todo = todo + "<span color='green'>" + B + "</span>(";
		if (!BB.rCoeficiente.equals("1")) {
			todo = todo + "<span color='blue'>" + BB.rCoeficiente + "</span>";
		}
		todo = todo + BB.rElemento;
		// agregamos exponente de resultado
		if (!BB.rExponente.equals("0")) {
			todo = todo + "<sup>" + BB.rExponente + "</sup>";
		}
		todo = todo + "<sub><b>(" + BB.rEstado + ")</b></sub>)";
		// igual
		todo = todo + " => ";

		// Agregamos el segundo compuesto de la reaccion
		todo = todo + "<span color='green'>" + B + "</span>(";
		if (!BB.coeficiente1.equals("1")) {
			todo = todo + "<span color='blue'>" + BB.coeficiente1 + "</span>";
		}
		todo = todo + BB.elemento1;
		// comprobamos para meter el exponente
		if (!BB.exponente1.equals("0")) {
			todo = todo + "<sup>" + BB.exponente1 + "</sup>";
		}
		todo = todo + "<sub><b>(" + BB.estado1 + ")</b></sub>";
		// tenemos que mirar que tenga segundo elemento
		if (!BB.elemento2.equals("") && !BB.coeficiente2.equals("")
				&& !BB.estado2.equals("") && !BB.exponente2.equals("")) {

			todo = todo + " + ";
			if (!BB.coeficiente2.equals("1")) {
				todo = todo + "<span color='blue'>" + BB.coeficiente2
						+ "</span>";
			}
			todo = todo + BB.elemento2;
			if (!BB.exponente2.equals("0")) {
				todo = todo + "<sup>" + BB.exponente2 + "</sup>";
			}
			todo = todo + "<sub><b>(" + BB.estado2 + ")</b></sub>";
		}
		todo = todo + ")";
		todo = todo + " + ";

		todo = todo + "<span color='green'>" + A + "</span>(";
		if (!AA.rCoeficiente.equals("1")) {
			todo = todo + "<span color='blue'>" + AA.rCoeficiente + "</span>";
		}
		todo = todo + AA.rElemento;
		if (!AA.rExponente.equals("0")) {
			todo = todo + "<sup>" + AA.rExponente + "</sup>";
		}
		todo = todo + "<sub><b>(" + AA.rEstado + ")</b></sub>";

		todo = todo + ")";

		if (Integer.parseInt(A) != Integer.parseInt(B)) {
			this.transferidos = Integer.parseInt(A) * Integer.parseInt(B);
		}
		this.transferidos = Integer.parseInt(A);

		return todo;
	}

	@Override
	public void run() {
		// vamos a cargar la base de datos
		db = new BaseDato();
		String listaCosas[] = db.getTodoEnArray();
		System.out.println("Cargando base de datos a Lista...");
		for (int i = 0; i < listaCosas.length; i++) {
			lista1.agregar(listaCosas[i]);
			lista2.agregar(listaCosas[i]);
		}
		System.out.println("Base de datos cargada completamente");

		while (true) {
			// actualizamos el texto
			lblNoElectrones.setText("Electrons Transferred: "
					+ this.transferidos);
			// actualizamos energia condicinada
			try {
				lblValorPila.setText("E(cell) = "
						+ calcularCelda().substring(0, 9) + "V");
			} catch (Exception t) {
				lblValorPila.setText("E(cell) = " + calcularCelda() + "V");
			}
			// validamos las celdas de concentracción para que no nos metan gato
			// por liebre
			validaCelda();
			// actualizamos lab

			// actualizar exponentes
			actualizarExponentes();
			try {
				actualLab();
			} catch (Exception g) {
			}
			try {
				crom.setText("\u0394" + "t = "
						+ Long.toString(chrome.diferencia()) + "ms");
				texto.setText(procesarTexto(eee1, eee2));
			} catch (Exception f) {
				// System.err.println("Te dije que deberías mejor inicializar eee1 y eee2 ¬_¬");
			}
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				System.err
						.println("Por alguna extraña razón nefasta, fallo el SLEEP(n) de Ventana");
			}
		}
	}

	/**
	 * Este método se encarga de actualizar el valor String de otros objetos de
	 * tipo Entrada (que hereda de la clase JTextField). Todo esto para mostrar
	 * en tiempo real el exponente tomado de la información que el usuario da
	 * cuando selecciona los elementos con quién quiere trabajar.
	 */
	private void actualizarExponentes() {
		try {
			int primero = Integer.parseInt(eee2.eletron);
			int segundo = Integer.parseInt(eee1.eletron)
					* Integer.parseInt(eee2.rCoeficiente);
			int tercero = Integer.parseInt(eee1.eletron);
			int cuarto = Integer.parseInt(eee1.rCoeficiente)
					* Integer.parseInt(eee2.eletron);
			exA.setText(Integer.toString(primero));
			exB.setText(Integer.toString(segundo));
			exC.setText(Integer.toString(tercero));
			exD.setText(Integer.toString(cuarto));
		} catch (Exception g) {

		}

	}

	/**
	 * Este método actualiza la información que se verá en la simulación de
	 * laboratorio, el cuál se lleva a cabo mediante el objeto labs de tipo
	 * Laboratorio.
	 * 
	 * @see grafica.Laboratorio
	 */
	private void actualLab() {
		if (!eee1.cadena.equals("") && !eee1.cadena.equals("")) {
			labs.setL1(eee1.cadena);
		}
		if (!eee2.cadena.equals("") && !eee2.cadena.equals("")) {
			labs.setL2(eee2.cadena);
		}
		// electrones
		if (!eee1.eletron.equals("") && !eee1.eletron.equals("")) {
			labs.setE1(eee1.eletron);
		}
		if (!eee2.eletron.equals("") && !eee2.eletron.equals("")) {
			labs.setE2(eee2.eletron);
		}
		//
		labs.setV(this.voltajePila);

	}

	/**
	 * Este método se usa para evitar errores en fúturas conversiones de tipo de
	 * datos: de String a Int. Todo esto, porque cuando el exponente es 1 se
	 * suele no escribir. Pero, en matemática es muy importante el valor 1.
	 */
	private void validaCelda() {
		if (this.enA.getText().equals("")) {
			this.enA.setText("1");
		}

		if (this.enB.getText().equals("")) {
			this.enB.setText("1");
		}

		if (this.enC.getText().equals("")) {
			this.enC.setText("1");
		}

		if (this.enD.getText().equals("")) {
			this.enD.setText("1");
		}
		//
		if (this.exA.getText().equals("")) {
			this.exA.setText("1");
		}

		if (this.exB.getText().equals("")) {
			this.exB.setText("1");
		}

		if (this.exC.getText().equals("")) {
			this.exC.setText("1");
		}

		if (this.exD.getText().equals("")) {
			this.exD.setText("1");
		}

	}

	/**
	 * Este método calcula el voltaje relativo de la celda. Toma valores como la
	 * temperatura, concentración, etc.
	 * 
	 * @return voltajePila es el voltaje real de la pila.
	 */
	private String calcularCelda() {
		String valor = "0";
		double logaritmo = 0.0;
		try {
			logaritmo = Math.log((Math.pow(Double.parseDouble(enA.getText()),
					Double.parseDouble(exA.getText())) * Math.pow(
					Double.parseDouble(enB.getText()),
					Double.parseDouble(exB.getText())))
					/ (Math.pow(Double.parseDouble(enC.getText()),
							Double.parseDouble(exC.getText())) * Math.pow(
							Double.parseDouble(enD.getText()),
							Double.parseDouble(exD.getText()))));
			double segundo = ((Double
					.parseDouble(this.txtTemperatura.getText()) * 8.3144621) / (this.transferidos * 96500.0))
					* ((logaritmo == 0) ? 1 : logaritmo);
			valor = Double.toString(Double.parseDouble(this.globalE) - segundo);
		} catch (Exception f) {
			// error porque las lineas son vacias
			valor = "0";
		}
		this.voltajePila = valor;
		return valor;
	}

	// TODO: mirar que se hace con este método de la sección de gráfico
	private String CeldaA(double SS) {
		String valor = "0";
		double logaritmo = 0.0;
		try {
			logaritmo = Math.log((Math.pow(Double.parseDouble(enA.getText()),
					Double.parseDouble(exA.getText())) * Math.pow(
					Double.parseDouble(enB.getText()),
					Double.parseDouble(exB.getText())))
					/ (Math.pow(SS /* Double.parseDouble(enC.getText()) */,
							Double.parseDouble(exC.getText())) * Math.pow(
							Double.parseDouble(enD.getText()),
							Double.parseDouble(exD.getText()))));
			double segundo = ((Double
					.parseDouble(this.txtTemperatura.getText()) * 8.3144621) / (this.transferidos * 96500.0))
					* logaritmo;
			valor = Double.toString(Double.parseDouble(this.globalE) - segundo);
		} catch (Exception f) {
			// error porque las lineas son vacias
			valor = "0";
		}
		this.voltajePila = valor;
		return valor;
	}

	/**
	 * Procesa las dos semicelda y obtiene un formato legible marquetado en
	 * HTML.
	 * 
	 * @param eee1
	 *            Representa la celda 1
	 * @param eee2
	 *            Representa la celda 2
	 * 
	 * @return String de la formula formateada y marquetada con HTML
	 */
	private String procesarTexto(Electrodo eee1, Electrodo eee2) {
		String cadena = "<html><p size='9' face='verdana'>";
		cadena = cadena + prepocesarSub(eee1);
		cadena = cadena + "<br><br>";
		cadena = cadena + prepocesarSub(eee2);
		cadena = cadena + "</p></html>";
		return cadena;
	}

	/**
	 * Este método convierte un objeto con la información de la molecula, en una
	 * semi-formula. EL objeto Electrodo tiene información sobre el elemento,
	 * coheficiente, exponente, etc. Luego esto es convertido en un formato
	 * legible.
	 * 
	 * @param eee1
	 *            Objeto Electrodo a transformar a formato legible.
	 * @return String cadena con formato legible para el usuario marquetado en
	 *         HTML
	 */
	private String prepocesarSub(Electrodo eee1) {
		String cadena = "";
		// agregamos los coeficiente 1
		if (!eee1.coeficiente1.equals("1")) {
			cadena = cadena + "<span color='blue'>" + eee1.coeficiente1
					+ "</span>";
		}
		// agregamos el elemento 1
		cadena = cadena + eee1.elemento1;
		// agregamos el exponente
		if (!eee1.exponente1.equals("0")) {
			cadena = cadena + "<sup><i>" + eee1.exponente1 + "</i></sup>";
		}
		// agregamos estado
		cadena = cadena + "<sub><b>(" + eee1.estado1 + ")</b></sub>";
		cadena = cadena + " ";

		// comprobamos que existe un segundo elemento
		if (!eee1.coeficiente2.equals("") && !eee1.estado2.equals("")
				&& !eee1.exponente2.equals("") && !eee1.elemento2.equals("")) {
			cadena = cadena + "+ ";
			if (!eee1.coeficiente2.equals("1")) {
				cadena = cadena + "<span color='blue'>" + eee1.coeficiente2
						+ "</span>";
			}
			cadena = cadena + eee1.elemento2;
			if (!eee1.exponente2.equals("0")) {
				cadena = cadena + "<sup><i>" + eee1.exponente2 + "</i></sup>";
			}
			cadena = cadena + "<sub><b>(" + eee1.estado2 + ")</b></sub>";
		}
		// agregamos electrones
		cadena = cadena + " + <span color='green'>" + eee1.eletron
				+ "</span> e<sup>-</sup>";
		cadena = cadena + " = ";

		// agregamos resultado 1
		if (!eee1.rCoeficiente.equals("1")) {
			cadena = cadena + eee1.rCoeficiente;
		}
		// agregamos elemento
		cadena = cadena + eee1.rElemento;
		// agregamos exponente
		if (!eee1.rExponente.equals("0")) {
			cadena = cadena + "<sup>" + eee1.rExponente + "</sup>";
		}
		cadena = cadena + "<sub><b>(" + eee1.rEstado + ")</b></sub>";
		// agregamos por fin electricidad
		cadena = cadena + " : <span color='red'>" + eee1.resultado + "</span>";

		return cadena;
	}

	@Override
	public void valueChanged(ListSelectionEvent cosa) {
		if (cosa.getSource().equals(lista1) && lista1.getValueIsAdjusting()) {
			eee1 = db.getElectrodoPorCadena(lista1.getTextoDe(lista1
					.getSelectedIndex()));
			System.out.println("Seleccionado la Lista 1");
		} else if (cosa.getSource().equals(lista2)
				&& lista2.getValueIsAdjusting()) {
			eee2 = db.getElectrodoPorCadena(lista2.getTextoDe(lista2
					.getSelectedIndex()));
			System.out.println("Seleccionado la Lista 2");
		} else {
			// JOptionPane.showMessageDialog(this,
			// "Error, este mensaje no debería salir al menos que exista otra Lista registrada que no es lista1 o lista2");
			System.err
					.println("Error, este mensaje no debería salir en las Lista :"
							+ cosa.toString());
		}
	}

}
