package Metodoak;

import java.io.*; 
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import E2.*;

public class Metodoak {
	
	private static List<ErabiltzaileMota> erabiltzaileaklist;
	
	// --- Array list Datuak.ser datu guztiak gordetzeko ---
	public static ArrayList<Taldea> taldeakMasterList = new ArrayList<>();

	/**
	 * Irakurri egiten du  datuak.ser y gordetzen ditu taldeak master list-ean.
	 * Se debe llamar al iniciar la aplicación.
	 */
	@SuppressWarnings("unchecked")
	public static void kargatuDatuak() {
		File f = new File("datuak.ser");
		if (f.exists()) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
				// Leemos el objeto del fichero y lo convertimos (cast) a ArrayList
				taldeakMasterList = (ArrayList<Taldea>) ois.readObject();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Ezin izan dira Datuak Kargatu", "Errorea", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Ez da Datuak.ser artxiboa aurkitu", "Errorea", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Gorde egiten du taldeakMasterList datuak.ser fitxategian.
	 * Deitzen dio aplikazioa itxi aurretik.
	 */
	public static void gordeDatuak() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("datuak.ser"))) {
			// Escribimos la lista entera en el fichero
			oos.writeObject(taldeakMasterList);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Ezin izan dira gorde aldaketak", "Errorea", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	// --- MÉTODOS EXISTENTES (LOGIN) ---
	
	public static void kargatuErabiltzaileak() {
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
