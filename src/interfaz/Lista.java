package interfaz;

import java.util.Vector;

import javax.swing.JList;

import quimica.Electrodo;

public class Lista extends JList{
	
	//esto recibir� el string , luego si se selecciona entonces lo buscar� en Electrodo, la clase, y obtendr� los elementos
	private Vector<String> elementos = new Vector();
	
	public Lista(){
		elementos.add("");
		this.setListData(elementos);
	}
	
	public void agregar(String ee){
		this.elementos.add(ee);
		this.setListData(elementos);
	}
	public String getTextoDe(int i){
		try{
			System.out.println("Aqu� lista reportando el elemento seleccionado: "+i);
			return elementos.get(i);
		}catch(Exception f){
			return null;
		}
	}

}
