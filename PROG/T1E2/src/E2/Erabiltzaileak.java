package E2;

public abstract class Erabiltzaileak {
	protected String Izena;
	protected String Abizena;
	protected String NAN;
	protected String Erabiltzailea;
	protected String Pasahitza;
	
	public Erabiltzaileak(String izena, String abizena, String NAN, String erabiltzailea, String pasahitza) {
		super();
		this.Izena = izena;
		this.Abizena = abizena;
		this.NAN = NAN;
		this.Erabiltzailea = erabiltzailea;
		this.Pasahitza = pasahitza;
	}
	public abstract boolean baimenak();
	
	@Override
	public String toString() {
		return "Erabiltzaileak [izena=" + Izena + ", abizena=" + Abizena + ", NAN=" + NAN + ", erabiltzailea="
				+ Erabiltzailea + ", pasahitza=" + Pasahitza + "]";
	}
}
