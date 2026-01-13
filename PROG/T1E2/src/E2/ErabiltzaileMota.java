package E2;

public abstract class ErabiltzaileMota {
	protected String Izena;
	protected String Abizena;
	protected String NAN;
	protected String Erabiltzailea;
	protected String Pasahitza;
	
	public ErabiltzaileMota(String izena, String abizena, String NAN, String erabiltzailea, String pasahitza) {
		super();
		this.Izena = izena;
		this.Abizena = abizena;
		this.NAN = NAN;
		this.Erabiltzailea = erabiltzailea;
		this.Pasahitza = pasahitza;
	}
	
	public String getErabiltzailea() {
		return Erabiltzailea;
	}
	
	public String getPasahitza() {
		return Pasahitza;
	}
	
	public abstract String baimenak();
}
