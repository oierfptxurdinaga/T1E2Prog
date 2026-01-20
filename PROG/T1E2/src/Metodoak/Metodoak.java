package Metodoak;

import java.io.*; 
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import E2.*;

public class Metodoak {
	
	private static List<ErabiltzaileMota> erabiltzaileaklist;
	public static ArrayList<Taldea> taldeakMasterList = new ArrayList<>();

	// --- Fitxategien kudeaketa ---

	@SuppressWarnings("unchecked")
	public static void kargatuDatuak() {
		File f = new File("datuak.ser");
		if (f.exists()) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
				taldeakMasterList = (ArrayList<Taldea>) ois.readObject();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Ezin izan dira Datuak Kargatu", "Errorea", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Ez da Datuak.ser artxiboa aurkitu", "Errorea", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void gordeDatuak() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("datuak.ser"))) {
			oos.writeObject(taldeakMasterList);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Ezin izan dira gorde aldaketak", "Errorea", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void kargatuErabiltzaileak() {
		erabiltzaileaklist = new ArrayList<>();
		erabiltzaileaklist.add(new Administradorea("Eder", "Bilbao", "12345678A", "ebilbao", "12345"));
		erabiltzaileaklist.add(new Presidentea("Aratz", "Elexpe", "12345678B", "aelexpe", "12345"));
		erabiltzaileaklist.add(new ErabiltzaileNormala("Kirian", "Munoz", "12345678C", "kmunoz", "12345"));
		erabiltzaileaklist.add(new ErabiltzaileNormala("Aratz", "Barcena", "12345678D", "abarcena", "12345"));
	}
	
	// --- Logika Metodoak ---

	public static void actualizarTablasJokalariak(String seleccionadoderecha, String seleccionadoziquerda, JTable tablaDerecha, JTable tablaIzquierda) {
	    if (seleccionadoziquerda == null || seleccionadoderecha == null) return;
	    
	    if (seleccionadoderecha.equals(seleccionadoziquerda)) {
	        JOptionPane.showMessageDialog(null, "Bi talde desberdin aukeratu behar dituzu");
	        return;
	    }

	    Taldea tderecha = null, tizquierda = null;
	    for (Taldea taldea : taldeakMasterList) {
	        if (taldea.getIzena().equals(seleccionadoderecha)) tderecha = taldea;
	        if (taldea.getIzena().equals(seleccionadoziquerda)) tizquierda = taldea;
	    }

	    if (tderecha == null || tizquierda == null) return;

	    int jokalarider = tablaDerecha.getSelectedRow();
	    int jokalariizq = tablaIzquierda.getSelectedRow();

	    if (jokalarider == -1 || jokalariizq == -1) {
	        JOptionPane.showMessageDialog(null, "Aukeratu behar dituzu bi jokalari (bana taula bakoitzean)");
	        return;
	    }

	    // Intercambio con constructor copia
	    Jokalaria copiaDer = new Jokalaria(tderecha.getJokalariak().get(jokalarider));
	    Jokalaria copiaIzq = new Jokalaria(tizquierda.getJokalariak().get(jokalariizq));

	    copiaDer.setTaldea(tizquierda.getIzena());
	    copiaIzq.setTaldea(tderecha.getIzena());

	    tderecha.getJokalariak().set(jokalarider, copiaIzq);
	    tizquierda.getJokalariak().set(jokalariizq, copiaDer);

	    // Guardar y refrescar (meterlosJokalaris ya incluye el Collections.sort)
	    gordeDatuak();
	    meterlosJokalaris(seleccionadoziquerda, tablaIzquierda);	 
	    meterlosJokalaris(seleccionadoderecha, tablaDerecha);	    

	    JOptionPane.showMessageDialog(null, "Jokalariak ondo aldatu dira!");
	}
        
	public static void meterlosJokalaris(String seleccionado, JTable tabla) {
		if (seleccionado == null) return;

		Taldea t = null;
		for (Taldea taldea : taldeakMasterList) {
			if (taldea.getIzena().equals(seleccionado)) {
				t = taldea;
				break;
			}
		}
		
		if (t != null) {
			// Ordenar antes de mostrar
			Collections.sort(t.getJokalariak()); 
			
			DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
			modelo.setRowCount(0);
			for (Jokalaria j : t.getJokalariak()) {
				modelo.addRow(new Object[] {
					j.getIzena(), j.getAbizena(), j.getJaiotzeData(),
					j.getNAN(), t.getIzena(), j.getPrezioa(), j.getJokalarienPuntuak()
				});
			}
		}
	}

	public static void actualizarTablasTaldeak(String seleccionado, JTable tablaPequena, JTable tablaGrande) {
		if (seleccionado == null) return;

		Taldea t = null;
		for (Taldea taldea : taldeakMasterList) {
			if (taldea.getIzena().equals(seleccionado)) {
				t = taldea;
				break;
			}
		}

		if (t != null) {
			Collections.sort(t.getJokalariak()); // Ordenar tabla de jugadores

			// Tabla resumen equipo
			DefaultTableModel modeloPequena = (DefaultTableModel) tablaPequena.getModel();
			modeloPequena.setRowCount(0);
			modeloPequena.addRow(new Object[]{
				t.getIzena(), t.getSorreraUrtea(), t.getLehendakari(),
				t.getN_Bazkideak(), t.getPuntuakF(), t.getPuntuakC(),
				t.getPuntuTotalak(), t.getIrabazitakoak(), t.getGaldutakoak(),
				t.getJokalariak().size()
			});

			// Tabla lista jugadores
			DefaultTableModel modeloGrande = (DefaultTableModel) tablaGrande.getModel();
			modeloGrande.setRowCount(0);
			for (Jokalaria j : t.getJokalariak()) {
				modeloGrande.addRow(new Object[] {
					j.getIzena(), j.getAbizena(), j.getJaiotzeData(),
					j.getNAN(), t.getIzena(), j.getPrezioa(), j.getJokalarienPuntuak()
				});
			}
		}
	}

	public static String login(String erabiltzailea, String pasahitza) {
		if (erabiltzaileaklist == null) kargatuErabiltzaileak();
		
		for (ErabiltzaileMota e : erabiltzaileaklist) {
			if (e.getErabiltzailea().equals(erabiltzailea) && e.getPasahitza().equals(pasahitza)) {
				return e.baimenak();
			}
		}
		return null;
	}

	public static void atera() {
		int respuesta = JOptionPane.showConfirmDialog(null, "¿Atera baino lehen, gorde nahi duzu?", "Berrespena", JOptionPane.YES_NO_CANCEL_OPTION);

		if (respuesta == JOptionPane.YES_OPTION) {
			gordeDatuak();
			System.exit(0);
		} else if (respuesta == JOptionPane.NO_OPTION) {
			System.exit(0);
		}
	}
}