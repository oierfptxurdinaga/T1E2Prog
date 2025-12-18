package E2;

import java.util.ArrayList;
import java.util.Arrays;

public class Taldea {
	private String Izena;
	private String Logoa;
	private String SorreraUrtea;
	private String Lehendakari;
	private int N_Bazkideak;
	private int PuntuakF;
	private int PuntuakC;
	private int PuntuTotalak;
	private int Irabazitakoak;
	private int Galdutakoak;
	private ArrayList<Jokalaria> jokalariak;
	
	public Taldea(String izena, String logoa, String sorreraUrtea, String lehendakari, int n_Bazkideak, int puntuakF,
			int puntuakC, int puntuTotalak, int irabazitakoak, int galdutakoak, ArrayList<Jokalaria> jokalariak2) {
		super();
		Izena = izena;
		Logoa = logoa;
		SorreraUrtea = sorreraUrtea;
		Lehendakari = lehendakari;
		N_Bazkideak = n_Bazkideak;
		PuntuakF = puntuakF;
		PuntuakC = puntuakC;
		PuntuTotalak = puntuTotalak;
		Irabazitakoak = irabazitakoak;
		Galdutakoak = galdutakoak;
		this.jokalariak = jokalariak2;
	}

	public String getIzena() {
		return Izena;
	}

	public void setIzena(String izena) {
		Izena = izena;
	}

	public String getLogoa() {
		return Logoa;
	}

	public void setLogoa(String logoa) {
		Logoa = logoa;
	}

	public String getLehendakari() {
		return Lehendakari;
	}

	public void setLehendakari(String lehendakari) {
		Lehendakari = lehendakari;
	}

	public int getN_Bazkideak() {
		return N_Bazkideak;
	}

	public void setN_Bazkideak(int n_Bazkideak) {
		N_Bazkideak = n_Bazkideak;
	}

	public int getPuntuakF() {
		return PuntuakF;
	}

	public void setPuntuakF(int puntuakF) {
		PuntuakF = puntuakF;
	}

	public int getPuntuakC() {
		return PuntuakC;
	}

	public void setPuntuakC(int puntuakC) {
		PuntuakC = puntuakC;
	}

	public int getPuntuTotalak() {
		return PuntuTotalak;
	}

	public void setPuntuTotalak(int puntuTotalak) {
		PuntuTotalak = puntuTotalak;
	}

	public int getIrabazitakoak() {
		return Irabazitakoak;
	}

	public void setIrabazitakoak(int irabazitakoak) {
		Irabazitakoak = irabazitakoak;
	}

	public int getGaldutakoak() {
		return Galdutakoak;
	}

	public void setGaldutakoak(int galdutakoak) {
		Galdutakoak = galdutakoak;
	}

	public ArrayList<Jokalaria> getJokalariak() {
		return jokalariak;
	}

	public void setJokalariak(ArrayList<Jokalaria> jokalariak) {
		this.jokalariak = jokalariak;
	}

	public String getSorreraUrtea() {
		return SorreraUrtea;
	}
}