package Metodoak;

import java.util.ArrayList;
import java.util.List;

import E2.*;

public class Metodoak {
	
	private static List<ErabiltzaileMota> erabiltzaileaklist;
	
	private Metodoak() {
		
	}
	
	private static void kargatuErabiltzaileak() {
		erabiltzaileaklist = new ArrayList<>();
		
		
		erabiltzaileaklist.add(new Administradorea("Eder", "Bilbao", "12345678A", "ebilbao", "12345"));
		
		erabiltzaileaklist.add(new Presidentea("Aratz", "Elexpe", "12345678B", "aelexpe", "12345"));
		
		erabiltzaileaklist.add(new ErabiltzaileNormala("Kirian", "Munoz", "12345678C", "kmunoz", "12345"));
		
		erabiltzaileaklist.add(new ErabiltzaileNormala("Aratz", "Barcena", "12345678D", "abarcena", "12345"));
	}
	
	public static String login(String erabiltzailea, String pasahitza) {
		
		if (erabiltzaileaklist == null) {
			kargatuErabiltzaileak();
		}
		
		for (int i = 0; i < erabiltzaileaklist.size(); i++) {
			ErabiltzaileMota erabiltzaileaB = erabiltzaileaklist.get(i);
			
			if (erabiltzaileaB.getErabiltzailea().equals(erabiltzailea) && erabiltzaileaB.getPasahitza().equals(pasahitza)) {
				return erabiltzaileaB.baimenak();
			}
		}
		
		return null;
	}
}
