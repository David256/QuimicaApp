package quimica;

public class Electrodo {

	public String coeficiente1;
	public String elemento1;
	public String estado1;
	public String exponente1;

	public String coeficiente2;
	public String elemento2;
	public String estado2;
	public String exponente2;

	public String rCoeficiente;
	public String rElemento;
	public String rEstado;
	public String rExponente;

	public String resultado;
	public String cadena;
	public String eletron;

	public Electrodo(String coeficiente1, String elemento1, String estado1,
			String exponente1, String coeficiente2, String elemento2,
			String estado2, String exponente2, String rCoeficiente,
			String rElemento, String rEstado, String rExponente,
			String resultado, String cadena, String electron) {
		this.coeficiente1 = coeficiente1;
		this.elemento1 = elemento1;
		this.estado1 = estado1;
		this.exponente1 = exponente1;

		this.coeficiente2 = coeficiente2;
		this.elemento2 = elemento2;
		this.estado2 = estado2;
		this.exponente2 = exponente2;

		this.rCoeficiente = rCoeficiente;
		this.rElemento = rElemento;
		this.rEstado = rEstado;
		this.rExponente = rExponente;

		this.resultado = resultado;
		this.cadena = cadena;
		this.eletron = electron;

	}

	public String toString() {
		return this.cadena;
	}
}