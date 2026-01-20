package Metodoak;

import java.awt.BorderLayout;
import java.io.*; 
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
	
	
	
	// Kargatu erabiltzaileak listara
	public static void kargatuErabiltzaileak() {
		erabiltzaileaklist = new ArrayList<>();
		erabiltzaileaklist.add(new Administradorea("Eder", "Bilbao", "12345678A", "ebilbao", "12345"));
		erabiltzaileaklist.add(new Presidentea("Aratz", "Elexpe", "12345678B", "aelexpe", "12345"));
		erabiltzaileaklist.add(new ErabiltzaileNormala("Kirian", "Munoz", "12345678C", "kmunoz", "12345"));
		erabiltzaileaklist.add(new ErabiltzaileNormala("Aratz", "Barcena", "12345678D", "abarcena", "12345"));
	}
	
	// --- Atera Metodoa ---
	public static void atera() {
		int respuesta = JOptionPane.CLOSED_OPTION; 

    	// Bucle: se repite si el usuario cierra la ventana o presiona Cancel
    	while (respuesta == JOptionPane.CLOSED_OPTION || respuesta == JOptionPane.CANCEL_OPTION) {
    	    respuesta = JOptionPane.showConfirmDialog(
    	            null,
    	            "¿Atera baino lehen,gorde nahi duzu?",
    	            "Berrespena",
    	            JOptionPane.YES_NO_CANCEL_OPTION
    	    );

    	    if (respuesta == JOptionPane.CLOSED_OPTION) {
    	        JOptionPane.showMessageDialog(
    	                null,
    	                "Aukeratu behar duzu bat",
    	                "Atención",
    	                JOptionPane.WARNING_MESSAGE
    	        );
    	    } else if (respuesta == JOptionPane.CANCEL_OPTION) {
    	        // Solo cerramos el diálogo, el programa sigue en el bucle
    	        System.out.println("Cancel aukeratua, programa jarraitzen du");
    	        break; // si quieres que salga del bucle y siga el programa
    	    }
    	

    	// Manejo de las opciones válidas
    	if (respuesta == JOptionPane.YES_OPTION) {
    	    Metodoak.gordeDatuak(); // Gorde aldaketak irten baino lehen
    	    System.exit(0);          // Cierra todo
    	} else if (respuesta == JOptionPane.NO_OPTION) {
    	    System.exit(0);          // Cierra todo
    	}

    	// Si era Cancel, el programa sigue normalmente aquí
    	System.out.println("Programa jarraitzen du Cancel aukeratu ondoren...");

    }
	};
	 public static void actualizarTablasJokalariak(String seleccionado, JTable tablaizquierdaoderecha) {
		 if (seleccionado == null) {
		        return;
		    }

		    Taldea t = null;
		  // Buscar el Taldea seleccionado con un for normal
		    for (Taldea taldea : taldeakMasterList) {
		        if (taldea.getIzena().equals(seleccionado)) {
		            t = taldea;
		            break;
		        }
		       
            		}
		    if (t != null) {
			    DefaultTableModel modeloizquierdaoderecha = (DefaultTableModel) tablaizquierdaoderecha.getModel();
			    modeloizquierdaoderecha.setRowCount(0); // Limpiar tabla
	            		for (Jokalaria j : t.getJokalariak()) {
	            			modeloizquierdaoderecha.addRow(new Object[] {
	            		        j.getIzena(),
	            		        j.getAbizena(),
	            		        j.getJaiotzeData(),
	            		        j.getNAN(),
	            		        t.getIzena(),
	            		        j.getPrezioa(),
	            		        j.getJokalarienPuntuak()
	            		    });
		    }
		    }
            }
        
	 

	 public static void actualizarTablasTaldeak(String seleccionado, JTable tablaPequena, JTable tablaGrande) {
		 if (seleccionado == null) {
		        return;
		    }

		    Taldea t = null;

		    // Buscar el Taldea seleccionado con un for normal
		    for (Taldea taldea : taldeakMasterList) {
		        if (taldea.getIzena().equals(seleccionado)) {
		            t = taldea;
		            break;
		        }
		    }

	            if (t != null) {
	                DefaultTableModel modeloPequena = (DefaultTableModel) tablaPequena.getModel();
	                modeloPequena.setRowCount(0); // Limpiar tabla

	                modeloPequena.addRow(new Object[]{
	                        t.getIzena(),
	                        t.getSorreraUrtea(),
	                        t.getLehendakari(),
	                        t.getN_Bazkideak(),
	                        t.getPuntuakF(),
	                        t.getPuntuakC(),
	                        t.getPuntuTotalak(),
	                        t.getIrabazitakoak(),
	                        t.getGaldutakoak(),
	                        t.getJokalariak().size() // número de jugadores
	                });
	                DefaultTableModel modeloGrande = (DefaultTableModel) tablaGrande.getModel();
	                modeloGrande.setRowCount(0); // Limpiar tabla
	                		for (Jokalaria j : t.getJokalariak()) {
	                		    modeloGrande.addRow(new Object[] {
	                		        j.getIzena(),
	                		        j.getAbizena(),
	                		        j.getJaiotzeData(),
	                		        j.getNAN(),
	                		        t.getIzena(),
	                		        j.getPrezioa(),
	                		        j.getJokalarienPuntuak()
	                		    });
	                		}
	                }
	            }



	
	// --- Login Metodoa ---
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
