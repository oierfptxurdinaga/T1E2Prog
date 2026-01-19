package Main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import E2.ErabiltzaileMota;
import E2.Taldea;
import Metodoak.Metodoak;

public class ErronkaBisuala extends JFrame {

    // --- Atributuak ---
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private Font titleFont;

    // --- Panelen Osagaiak ---
    private JPanel LoginPanela, HasierakoPanela, KlasifikazioaPanela, EmaitzaPanela, TaldeakPanela, JokalariakPanela;
    
    // Login
    private JLabel logoaImg1, erabiltzaileak, pasahitza;
    private JTextField textErabiltzaile;
    private JPasswordField textPasahitza;
    private JButton sartu, atera1;

    // Hasiera
    private JLabel logoaImg2, img1, img2;
    private JButton atzerantz, atera2, klasifikazioaIkusi, sartuEmaitza, taldeakIkusi, jokalariakAldatu;

    // Klasifikazioa
    private JButton atzerantzKlasif, ateraKlasif;
    private JTable tablaKlasif;
    private DefaultTableModel modeloTabla;
    private JScrollPane scrollTabla;
    
    // Emaitza
    private JTable tablaEmaitzak;
    private DefaultTableModel modeloEmaitzak;
    private JButton atzerantzEmaitza; 
    private JButton ateraEmaitza; 
    private JButton gordeEmaitza;
    
    
    public ErronkaBisuala() {
        // --- JFrame Konfigurazioa ---
        setTitle("Bizkaiko Saskibaloi Federazioa");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
        setSize(1000, 700);
        setLocationRelativeTo(null);
        titleFont = new Font("Verdana", Font.BOLD, 24);

        // --- Datuak Kargatu ---
        Metodoak.kargatuDatuak();

        // --- Layout Konfigurazioa ---
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        setContentPane(contentPanel);

        // --- Panelak Inizializatu ---
        inizializatuPanelak();
        konfiguratuOsagaiBisualak();

        // --- Gehitu Panelak ---
        contentPanel.add(LoginPanela, "Login");
        contentPanel.add(HasierakoPanela, "Hasiera");
        contentPanel.add(KlasifikazioaPanela, "Klasifikazioa");
        contentPanel.add(EmaitzaPanela, "Emaitzak");
        contentPanel.add(TaldeakPanela, "Taldeak");
        contentPanel.add(JokalariakPanela, "Jokalariak");

        // ==========================================================
        // 3. Ekintzak (Listeners) - FUSIONADO
        // ==========================================================

        // --- LOGIN EKINTZAK ---
        sartu.addActionListener(e -> {
            String usernameInput = textErabiltzaile.getText();
            String passwordInput = new String(textPasahitza.getPassword());

            if (usernameInput.trim().isEmpty() || passwordInput.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Mesedez, bete eremu guztiak.", "Errorea", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String rola = Metodoak.login(usernameInput, passwordInput);
            if (rola != null) {
                textErabiltzaile.setText(null);
                textPasahitza.setText(null);
                erakutsiPanelak(rola);
                cardLayout.show(contentPanel, "Hasiera");
            } else {
                JOptionPane.showMessageDialog(null, "Erabiltzaile edo Pasahitz okerra", "Errorea", JOptionPane.ERROR_MESSAGE);
            }
        });

        atera1.addActionListener(e -> Metodoak.atera());

        // --- HASIERA EKINTZAK ---
        atzerantz.addActionListener(e -> cardLayout.show(contentPanel, "Login"));
        atera2.addActionListener(e -> Metodoak.atera());

        klasifikazioaIkusi.addActionListener(e -> {
            eguneratuKlasifikazioa();
            cardLayout.show(contentPanel, "Klasifikazioa");
        });

        sartuEmaitza.addActionListener(e -> cardLayout.show(contentPanel, "Emaitzak"));
        taldeakIkusi.addActionListener(e -> cardLayout.show(contentPanel, "Taldeak"));
        jokalariakAldatu.addActionListener(e -> cardLayout.show(contentPanel, "Jokalariak"));

        // --- KLASIFIKAZIOA EKINTZAK ---
        atzerantzKlasif.addActionListener(e -> cardLayout.show(contentPanel, "Hasiera"));
        ateraKlasif.addActionListener(e -> Metodoak.atera());
        
     // --- EMAITZA EKINTZAK ---
        sartuEmaitza.addActionListener(e -> {
            generatuJornadak(); // Sortu Jornadak behar bada
            cardLayout.show(contentPanel, "Emaitzak");
        });

        atzerantzEmaitza.addActionListener(e -> cardLayout.show(contentPanel, "Hasiera"));
        ateraEmaitza.addActionListener(e -> Metodoak.atera()); // Llama al método atera de Metodoak
        gordeEmaitza.addActionListener(e -> prozesatuEmaitzak());
        

        // Leihoaren "X" botoia
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Metodoak.atera();
            }
        });

        cardLayout.show(contentPanel, "Login");
        setVisible(true);
    }

    private void inizializatuPanelak() {
        // --- LOGIN PANELA ---
        LoginPanela = new JPanel(null);
        JLabel titleLogin = new JLabel(getTitle(), JLabel.CENTER);
        titleLogin.setFont(titleFont);
        titleLogin.setBounds(38, 20, 900, 30);
        
        logoaImg1 = new JLabel("LOGOA IMAGEN", JLabel.CENTER);
        logoaImg1.setBounds(420, 92, 200, 150);
        
        erabiltzaileak = new JLabel("Erabiltzailea:", JLabel.RIGHT);
        erabiltzaileak.setBounds(250, 300, 150, 30);
        textErabiltzaile = new JTextField();
        textErabiltzaile.setBounds(420, 300, 250, 30);
        
        pasahitza = new JLabel("Pasahitza:", JLabel.RIGHT);
        pasahitza.setBounds(250, 350, 150, 30);
        textPasahitza = new JPasswordField();
        textPasahitza.setBounds(420, 350, 250, 30);
        
        sartu = new JButton("Sartu");
        sartu.setBounds(483, 447, 100, 30);
        this.getRootPane().setDefaultButton(sartu);
        
        atera1 = new JButton("Atera");
        atera1.setBounds(858, 60, 80, 30);

        LoginPanela.add(titleLogin); LoginPanela.add(logoaImg1); LoginPanela.add(erabiltzaileak);
        LoginPanela.add(textErabiltzaile); LoginPanela.add(pasahitza); LoginPanela.add(textPasahitza);
        LoginPanela.add(sartu); LoginPanela.add(atera1);

        // --- HASIERA PANELA ---
        HasierakoPanela = new JPanel(null);
        JLabel titleHasiera = new JLabel("HASIERA PANELA", JLabel.CENTER);
        titleHasiera.setBounds(50, 20, 900, 30);
        titleHasiera.setFont(titleFont);
        
        logoaImg2 = new JLabel(); logoaImg2.setBounds(394, 60, 240, 211);
        img1 = new JLabel(); img1.setBounds(100, 250, 350, 200);
        img2 = new JLabel(); img2.setBounds(550, 250, 350, 200);
        
        atzerantz = new JButton("Atzerantz"); atzerantz.setBounds(800, 50, 100, 30);
        atera2 = new JButton("Atera"); atera2.setBounds(910, 50, 70, 30);
        
        klasifikazioaIkusi = new JButton("Klasifikazioa ikusi"); klasifikazioaIkusi.setBounds(150, 480, 250, 40);
        sartuEmaitza = new JButton("Sartu Emaitza"); sartuEmaitza.setBounds(600, 480, 250, 40);
        taldeakIkusi = new JButton("Taldeak ikusi"); taldeakIkusi.setBounds(150, 530, 250, 40);
        jokalariakAldatu = new JButton("Jokalariak Aldatu"); jokalariakAldatu.setBounds(600, 530, 250, 40);

        HasierakoPanela.add(titleHasiera); HasierakoPanela.add(logoaImg2); HasierakoPanela.add(img1);
        HasierakoPanela.add(img2); HasierakoPanela.add(atzerantz); HasierakoPanela.add(atera2);
        HasierakoPanela.add(klasifikazioaIkusi); HasierakoPanela.add(sartuEmaitza);
        HasierakoPanela.add(taldeakIkusi); HasierakoPanela.add(jokalariakAldatu);

        // --- KLASIFIKAZIOA PANELA  ---
        KlasifikazioaPanela = new JPanel(null);
        JLabel titleKlasif = new JLabel("LIGAKO KLASIFIKAZIOA", JLabel.CENTER);
        titleKlasif.setBounds(50, 20, 900, 30);
        titleKlasif.setFont(titleFont);

        String[] zutabeTituluak = {"Taldea", "P. Totalak", "Irabazi", "Galdu", "Aldeko", "Aurkako"};
        modeloTabla = new DefaultTableModel(zutabeTituluak, 0);
        tablaKlasif = new JTable(modeloTabla);
        scrollTabla = new JScrollPane(tablaKlasif);
        scrollTabla.setBounds(50, 80, 900, 400);

        atzerantzKlasif = new JButton("Atzerantz"); atzerantzKlasif.setBounds(50, 520, 100, 30);
        ateraKlasif = new JButton("Atera"); ateraKlasif.setBounds(850, 520, 100, 30);

        KlasifikazioaPanela.add(titleKlasif); KlasifikazioaPanela.add(scrollTabla);
        KlasifikazioaPanela.add(atzerantzKlasif); KlasifikazioaPanela.add(ateraKlasif);
        
        // --- EMAITZA PANELA ---
     
        EmaitzaPanela = new JPanel(null);
        JLabel titleEmaitza = new JLabel("LIGAKO EMAITZAK (10 JORNADA)", JLabel.CENTER);
        titleEmaitza.setBounds(50, 20, 900, 30);
        titleEmaitza.setFont(titleFont);

     
        modeloEmaitzak = new DefaultTableModel(new String[]{"Jornada / Partidua", "Puntuak", "vs", "Puntuak", "Kanpoko Taldea"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // 1. Bakarrik editatu 1 y 3 (puntuak)
                if (column != 1 && column != 3) return false;

                // 2. Ezin da editatu "JORNADA" edo "Jaurdunaldia" lerroak
                Object val = getValueAt(row, 0);
                if (val != null && (val.toString().startsWith("JORNADA") || val.toString().startsWith("Jaurdunaldia"))) {
                    return false;
                }
                
                return true;
            }
        };

        tablaEmaitzak = new JTable(modeloEmaitzak);
        JScrollPane scrollEmaitzak = new JScrollPane(tablaEmaitzak);
        scrollEmaitzak.setBounds(50, 80, 900, 400);

        atzerantzEmaitza = new JButton("Atzerantz"); 
        atzerantzEmaitza.setBounds(50, 520, 100, 30);

        gordeEmaitza = new JButton("Gorde Emaitzak");
        gordeEmaitza.setBounds(400, 520, 200, 30);

        ateraEmaitza = new JButton("Atera"); 
        ateraEmaitza.setBounds(850, 520, 100, 30);

        EmaitzaPanela.add(titleEmaitza);
        EmaitzaPanela.add(scrollEmaitzak);
        EmaitzaPanela.add(atzerantzEmaitza);
        EmaitzaPanela.add(gordeEmaitza);
        EmaitzaPanela.add(ateraEmaitza);
        
        // Beste panelak (Taldeak eta Jokalariak) - Panelak sortu besterik ez dago
        TaldeakPanela = panelBigarrenakSortu("TALDEAK IKUSI PANELA");
        JokalariakPanela = panelBigarrenakSortu("JOKALARIAK ALDATU PANELA");
    }

    // --- Klasifikazioa Eguneratu  ---
    
    private void eguneratuKlasifikazioa() {
        // 1. Datuak berrezarri gehitu aurretik, bikoiztuak saihesteko
        for (Taldea t : Metodoak.taldeakMasterList) {
            t.setPuntuTotalak(0);
            t.setIrabazitakoak(0);
            t.setGaldutakoak(0);
            t.setPuntuakF(0);
            t.setPuntuakC(0);
        }

        // 2. Irakurri emaitzak eta kalkulatu puntuazioa
        for (int i = 0; i < modeloEmaitzak.getRowCount(); i++) {
            Object locNameObj = modeloEmaitzak.getValueAt(i, 0);
            
            // Saltatu errenkada nulua bada edo egun-bereizlea bada
            if (locNameObj == null) continue;
            String locName = locNameObj.toString();
            if (locName.startsWith("JORNADA") || locName.startsWith("Jaurdunaldia")) continue;

            // Balioztatu puntudun gelaxkak hutsik badaude 
            Object valL = modeloEmaitzak.getValueAt(i, 1);
            Object valV = modeloEmaitzak.getValueAt(i, 3);

            // Puntu-gelaxkaren bat hutsik badago, partida hau saltatzen dugu (ez da jokatu).
            if (valL == null || valL.toString().trim().isEmpty() || 
                valV == null || valV.toString().trim().isEmpty()) {
                continue;
            }

            int pL = Integer.parseInt(valL.toString().trim());
            int pV = Integer.parseInt(valV.toString().trim());
            String visName = (String) modeloEmaitzak.getValueAt(i, 4);

            // Emaitza zehazki 0-0 bada, saltatu.
            if (pL == 0 && pV == 0) continue;

            Taldea tL = null, tV = null;
            for (Taldea t : Metodoak.taldeakMasterList) {
                if (t.getIzena().equals(locName)) tL = t;
                if (t.getIzena().equals(visName)) tV = t;
            }

            if (tL != null && tV != null) {
            	// Alde onak eta txarrak eguneratu
                tL.setPuntuakF(tL.getPuntuakF() + pL);
                tL.setPuntuakC(tL.getPuntuakC() + pV);
                
                tV.setPuntuakF(tV.getPuntuakF() + pV);
                tV.setPuntuakC(tV.getPuntuakC() + pL);

                // Irabazleak 2 puntu, galtzaileak 1 puntu
                if (pL > pV) {
                    tL.setPuntuTotalak(tL.getPuntuTotalak() + 2); 
                    tL.setIrabazitakoak(tL.getIrabazitakoak() + 1);
                    tV.setPuntuTotalak(tV.getPuntuTotalak() + 1); 
                    tV.setGaldutakoak(tV.getGaldutakoak() + 1);
                } else {
                    tV.setPuntuTotalak(tV.getPuntuTotalak() + 2); 
                    tV.setIrabazitakoak(tV.getIrabazitakoak() + 1);
                    tL.setPuntuTotalak(tL.getPuntuTotalak() + 1); 
                    tL.setGaldutakoak(tL.getGaldutakoak() + 1);
                }
            }
        }

        // 3. Ordenatu zerrenda (Beherapen puntuak, gero Batez besteko beherapena)
        Metodoak.taldeakMasterList.sort((t1, t2) -> {
            int diff = Integer.compare(t2.getPuntuTotalak(), t1.getPuntuTotalak());
            if (diff != 0) return diff;
            
            int avg1 = t1.getPuntuakF() - t1.getPuntuakC();
            int avg2 = t2.getPuntuakF() - t2.getPuntuakC();
            return Integer.compare(avg2, avg1);
        });

        // 4. Eguneratu taula bisuala
        modeloTabla.setRowCount(0);
        for (Taldea t : Metodoak.taldeakMasterList) {
            Object[] fila = {
                t.getIzena(), 
                t.getPuntuTotalak(), 
                t.getIrabazitakoak(), 
                t.getGaldutakoak(), 
                t.getPuntuakF(), 
                t.getPuntuakC()
            };
            modeloTabla.addRow(fila);
        }
    }
    
    // --- Emaitzak Prozesatu (Emaitza Paneala) ---
    
    private void generatuJornadak() {
        if (modeloEmaitzak.getRowCount() > 0) return; // Ez duplikatu baldin badaude 

        // 1. Sortu taldeen kopia ausazko ordenean
        ArrayList<Taldea> kopia = new ArrayList<>(Metodoak.taldeakMasterList); //
        java.util.Collections.shuffle(kopia);

        // 2. Sortu Jornadak 1-5 (joana)
        // 1vs6, 2vs5, 3vs4
        for (int j = 1; j <= 5; j++) {
            modeloEmaitzak.addRow(new Object[]{"Jaurdunaldia " + j, "", "", "", ""});
            modeloEmaitzak.addRow(new Object[]{kopia.get(0).getIzena(), 0, "vs", 0, kopia.get(5).getIzena()});
            modeloEmaitzak.addRow(new Object[]{kopia.get(1).getIzena(), 0, "vs", 0, kopia.get(4).getIzena()});
            modeloEmaitzak.addRow(new Object[]{kopia.get(2).getIzena(), 0, "vs", 0, kopia.get(3).getIzena()});
            
            // Ez jolasteko bi aldiz berdin
            // Mugitzen dugu azken taldea lehen postura, 0 beti izango da finko
            Taldea last = kopia.remove(5);
            kopia.add(1, last);
        }

        // 3. Sortu Jornadak 6-10 (etorria)
        // Sortutako partiduak joanekoen alderantziz
        int jornadaerrenkada = 4; // Titulua + 3 partiduak
        for (int j = 0; j < 5; j++) {
            modeloEmaitzak.addRow(new Object[]{"JORNADA " + (j + 6), "", "", "", ""});
            for (int p = 1; p <= 3; p++) {
                int filaOriginal = (j * jornadaerrenkada) + p;
                String local = (String) modeloEmaitzak.getValueAt(filaOriginal, 0);
                String visitante = (String) modeloEmaitzak.getValueAt(filaOriginal, 4);
                // Añadimos la vuelta invertida
                modeloEmaitzak.addRow(new Object[]{visitante, 0, "vs", 0, local});
            }
        }
    }
    
    // Partidu bat gehitu (laguntzailea)
    
    private void gehituPartidua(Taldea t1, Taldea t2) {
        modeloEmaitzak.addRow(new Object[]{t1.getIzena(), 0, "vs", 0, t2.getIzena()});
    }

    private void prozesatuEmaitzak() {
        // Erabiltzaileak une honetan idazten ari dena gordetzera behartzen dugu sistema.
        if (tablaEmaitzak.isEditing()) {
            tablaEmaitzak.getCellEditor().stopCellEditing();
        }

        try {
            for (int i = 0; i < modeloEmaitzak.getRowCount(); i++) {
                Object col0Obj = modeloEmaitzak.getValueAt(i, 0);
                if (col0Obj == null) continue;
                
                String col0 = col0Obj.toString();
                if (col0.startsWith("Jaurdunaldia") || col0.startsWith("JORNADA")) continue;

                // 1. Lortu balioak zeluletatik
                Object valL = modeloEmaitzak.getValueAt(i, 1);
                Object valV = modeloEmaitzak.getValueAt(i, 3);

                // Biak hutsik badaude, partida hau saltatzen dugu
                if ((valL == null || valL.toString().trim().isEmpty()) && 
                    (valV == null || valV.toString().trim().isEmpty())) {
                    continue; 
                }

                // 2. Bakarra hutsik badago, jakinaraziko dizugu.
                if (valL == null || valL.toString().trim().isEmpty() || 
                    valV == null || valV.toString().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Aviso: El partido '" + col0 + "' tiene un marcador incompleto.");
                    return;
                }

                // 3. Zenbakiak diren egiaztatu
                int pLocal, pVisit;
                try {
                    pLocal = Integer.parseInt(valL.toString().trim());
                    pVisit = Integer.parseInt(valV.toString().trim());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Error: Solo números en '" + col0 + "'");
                    return;
                }

                // 4. Kirol baliozkotzeak (negatiboak eta berdinketak)
                if (pLocal < 0 || pVisit < 0) {
                    JOptionPane.showMessageDialog(this, "Error: Puntos negativos en '" + col0 + "'");
                    return;
                }
                if (pLocal == pVisit && !(pLocal == 0 && pVisit == 0)) {
                    JOptionPane.showMessageDialog(this, "Aviso: En baloncesto no hay empates (" + col0 + ")");
                    return;
                }
            }

            // Ondo dagoen egiaztatuta dago, orain datuak gordetzen ditugu
            eguneratuKlasifikazioa(); 
            Metodoak.gordeDatuak();   
            JOptionPane.showMessageDialog(this, "Resultados procesados. Se han guardado los partidos completados.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    
    // --- Metodo Erabilgarriak ---
    private void erakutsiPanelak(String rola) {
        klasifikazioaIkusi.setVisible(true);
        taldeakIkusi.setVisible(true);
        sartuEmaitza.setVisible(rola.equals("Admin"));
        jokalariakAldatu.setVisible(rola.equals("Presidentea"));
    }

    private JPanel panelBigarrenakSortu(String titulo) {
        JPanel panel = new JPanel(null);
        JLabel lbl = new JLabel(titulo, JLabel.CENTER);
        lbl.setFont(titleFont); lbl.setBounds(50, 20, 900, 30);
        JButton btn = new JButton("Volver a Inicio");
        btn.setBounds(50, 50, 150, 30);
        btn.addActionListener(e -> cardLayout.show(contentPanel, "Hasiera"));
        panel.add(lbl); panel.add(btn);
        return panel;
    }

    private void konfiguratuOsagaiBisualak() {
        kargatuIrudia(logoaImg1, 200, 150, "/Multimedia/logoa.png");
        kargatuIrudia(logoaImg2, 200, 150, "/Multimedia/logoa.png");
        kargatuIrudia(img1, 150, 150, "/Multimedia/img1.png");
        kargatuIrudia(img2, 150, 150, "/Multimedia/img2.png");
    }

    private void kargatuIrudia(JLabel label, int w, int h, String path) {
        try {
            java.net.URL url = getClass().getResource(path);
            if (url != null) {
                Image img = new ImageIcon(url).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(img));
                label.setText("");
            }
        } catch (Exception e) { label.setText("Error Imagen"); }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new ErronkaBisuala());
    }
}