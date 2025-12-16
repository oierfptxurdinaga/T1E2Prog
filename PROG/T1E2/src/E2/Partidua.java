package E2;

public class Partidua {
	private String TaldeLokala;
	private String TaldeBisitari;
	private int ResultLokala;
	private int ResulBisitari;
	private String Data;
	private String Ordua;
	public Partidua(String taldeLokala, String taldeBisitari, int resultLokala, int resulBisitari, String data,
			String ordua) {
		super();
		TaldeLokala = taldeLokala;
		TaldeBisitari = taldeBisitari;
		ResultLokala = resultLokala;
		ResulBisitari = resulBisitari;
		Data = data;
		Ordua = ordua;
	}
	public String getTaldeLokala() {
		return TaldeLokala;
	}
	public void setTaldeLokala(String taldeLokala) {
		TaldeLokala = taldeLokala;
	}
	public String getTaldeBisitari() {
		return TaldeBisitari;
	}
	public void setTaldeBisitari(String taldeBisitari) {
		TaldeBisitari = taldeBisitari;
	}
	public int getResultLokala() {
		return ResultLokala;
	}
	public void setResultLokala(int resultLokala) {
		ResultLokala = resultLokala;
	}
	public int getResulBisitari() {
		return ResulBisitari;
	}
	public void setResulBisitari(int resulBisitari) {
		ResulBisitari = resulBisitari;
	}
	public String getData() {
		return Data;
	}
	public void setData(String data) {
		Data = data;
	}
	public String getOrdua() {
		return Ordua;
	}
	public void setOrdua(String ordua) {
		Ordua = ordua;
	}
	@Override
	public String toString() {
		return "Partidua [TaldeLokala=" + TaldeLokala + ", TaldeBisitari=" + TaldeBisitari + ", ResultLokala="
				+ ResultLokala + ", ResulBisitari=" + ResulBisitari + ", Data=" + Data + ", Ordua=" + Ordua + "]";
	}
}
