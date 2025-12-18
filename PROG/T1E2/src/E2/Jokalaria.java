package E2;

public class Jokalaria {
	private String Izena;
	private String Abizena;
	private String JaiotzeData;
	private String NAN;
	private String Taldea;
	private int Prezioa;
	private int JokalarienPuntuak;
	private int Dortsala;
	private boolean Kapitaina;
	
	public Jokalaria(String izena, String abizena, String jaiotzeData, String nAN, String taldea, int prezioa,
			int jokalarienPuntuak, int dortsala, boolean kapitaina) {
		super();
		Izena = izena;
		Abizena = abizena;
		JaiotzeData = jaiotzeData;
		NAN = nAN;
		Taldea = taldea;
		Prezioa = prezioa;
		JokalarienPuntuak = jokalarienPuntuak;
		Dortsala = dortsala;
		Kapitaina = kapitaina;
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

	public int getDortsala() {
		return Dortsala;
	}

	public void setDortsala(int dortsala) {
		Dortsala = dortsala;
	}

	public boolean isKapitaina() {
		return Kapitaina;
	}

	public void setKapitaina(boolean kapitaina) {
		Kapitaina = kapitaina;
	}

	public String getJaiotzeData() {
		return JaiotzeData;
	}

	public String getNAN() {
		return NAN;
	}
}