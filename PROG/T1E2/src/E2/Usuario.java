package E2;

public class Usuario extends Erabiltzaileak {

	public Usuario(String izena, String abizena, String NAN, String erabiltzailea, String pasahitza) {
		super(izena, abizena, NAN, erabiltzailea, pasahitza);
	}

	public String getizena() {
		return Izena;
	}
	public String getabizena() {
		return Abizena;
	}
	public String getNAN() {
		return NAN;
	}
	public String geterabiltzailea() {
		return Erabiltzailea;
	}
	public String getpasahitza() {
		return Pasahitza;
	}
	public void setizena(String izena) {
		Izena = izena;
	}
	public void setabizena(String abizena) {
		Abizena = abizena;
	}
	public void seterabiltzailea(String erabiltzailea) {
		Erabiltzailea = erabiltzailea;
	}
	public void setpasahitza(String pasahitza) {
		Pasahitza = pasahitza;
	}
	
	@Override
	public String toString() {
		return "Usuario [izena()=" + getizena() + ", abizena()=" + getabizena() + ", NAN()=" + getNAN()
				+ ", erabiltzailea()=" + geterabiltzailea() + ", pasahitza()=" + getpasahitza() + ", baimenak()="
				+ baimenak() + "]";
	}

	@Override
	public boolean baimenak() {
		return false;
	}

}
