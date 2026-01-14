package E2;

import java.io.Serializable;

public class Jokalaria implements Serializable {
    private static final long serialVersionUID = 1L;
	
	
	private String Izena;
	private String Abizena;
	private String JaiotzeData;
	private String NAN;
	private String Taldea;
	private int Prezioa;
	private int JokalarienPuntuak;

	
	public Jokalaria(String izena, String abizena, String jaiotzeData, String nAN, String taldea, int prezioa,
			int jokalarienPuntuak) {
		super();
		Izena = izena;
		Abizena = abizena;
		JaiotzeData = jaiotzeData;
		NAN = nAN;
		Taldea = taldea;
		Prezioa = prezioa;
		JokalarienPuntuak = jokalarienPuntuak;
	}

	public String getIzena() {
		return Izena;
	}

	public void setIzena(String izena) {
		Izena = izena;
	}

	public String getAbizena() {
		return Abizena;
	}

	public void setAbizena(String abizena) {
		Abizena = abizena;
	}

	public String getTaldea() {
		return Taldea;
	}

	public void setTaldea(String taldea) {
		Taldea = taldea;
	}

	public int getPrezioa() {
		return Prezioa;
	}

	public void setPrezioa(int prezioa) {
		Prezioa = prezioa;
	}

	public int getJokalarienPuntuak() {
		return JokalarienPuntuak;
	}

	public void setJokalarienPuntuak(int jokalarienPuntuak) {
		JokalarienPuntuak = jokalarienPuntuak;
	}

	public String getJaiotzeData() {
		return JaiotzeData;
	}

	public String getNAN() {
		return NAN;
	}
}