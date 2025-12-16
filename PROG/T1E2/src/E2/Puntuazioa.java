package E2;

import java.util.ArrayList;

public class Puntuazioa {
	private ArrayList<Taldeak> Puntuazioa;

	public Puntuazioa(ArrayList<Taldeak> puntuazioa) {
		super();
		Puntuazioa = puntuazioa;
	}

	public ArrayList<Taldeak> getPuntuazioa() {
		return Puntuazioa;
	}

	public void setPuntuazioa(ArrayList<Taldeak> puntuazioa) {
		Puntuazioa = puntuazioa;
	}

	@Override
	public String toString() {
		return "Puntuazioa [Puntuazioa=" + Puntuazioa + "]";
	}
}
