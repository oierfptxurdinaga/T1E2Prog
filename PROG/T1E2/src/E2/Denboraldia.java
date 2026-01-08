package E2;

import java.util.ArrayList;

public class Denboraldia {
	private String Data;
	private ArrayList<Jaurdunaldia> Denboraldia;
	private ArrayList<Puntuazioa> DenboraldiaP;
	public Denboraldia(String data, ArrayList<Jaurdunaldia> denboraldia, ArrayList<Puntuazioa> denboraldiaP) {
		super();
		Data = data;
		Denboraldia = denboraldia;
		DenboraldiaP = denboraldiaP;
	}
	public String getData() {
		return Data;
	}
	public void setData(String data) {
		Data = data;
	}
	public ArrayList<Jaurdunaldia> getDenboraldia() {
		return Denboraldia;
	}
	public void setDenboraldia(ArrayList<Jaurdunaldia> denboraldia) {
		Denboraldia = denboraldia;
	}
	public ArrayList<Puntuazioa> getDenboraldiaP() {
		return DenboraldiaP;
	}
	public void setDenboraldiaP(ArrayList<Puntuazioa> denboraldiaP) {
		DenboraldiaP = denboraldiaP;
	}
}