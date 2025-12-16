package E2;

import java.util.ArrayList;

public class prruebakk {

	public static void main(String[] args) {
		ArrayList<Jokalariak> jokalariak = new ArrayList<>();
		Jokalariak J1 = new Jokalariak("oier", "garcia", "18-10-2006", "55555555S","mike", 0, 777, 88, true);
		Jokalariak J2 = new Jokalariak("uydkhd", "ffff", "18-9-2006", "66665555S","mike", 300, 737, 8, false);
		Taldeak T1 = new Taldeak("mike","iouqwedi","22-22-2222","ederbi", 1, 69, 100, 5, 2, 1, jokalariak);
		jokalariak.add(J1);
		jokalariak.add(J2);
		System.out.println(T1);
		ArrayList<Partidua> partidua = new ArrayList<>();
		Partidua P1 = new Partidua("mike","iturri",99,4,"22-22-2222","18:88");
		Jaurdunaldia JA1 = new Jaurdunaldia(partidua);
		System.out.println(P1);
		partidua.add(P1);
		System.out.println(JA1);
		ArrayList<Jaurdunaldia> Denboraldia = new ArrayList<>();
		ArrayList<Puntuazioa> DenboraldiaP = new ArrayList<>();
		ArrayList<Taldeak> Puntuazioa = new ArrayList<>();
		Puntuazioa PU1 = new Puntuazioa(Puntuazioa);
		Puntuazioa.add(T1);
		Denboraldia D1 = new Denboraldia("2025",Denboraldia, DenboraldiaP);
		Denboraldia.add(JA1);
		DenboraldiaP.add(PU1);
		System.out.println(D1);	
	}
}