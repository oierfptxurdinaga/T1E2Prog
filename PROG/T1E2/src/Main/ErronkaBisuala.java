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

    //Taldeak Ikusi

    private JComboBox<String> comboBox;
    private JTable tablaPequena;
    private JTable tablaGrande;
    
    public ErronkaBisuala() {
        // --- JFrame Konfigurazioa ---
        setTitle("Bizkaiko Saskibaloi Federazioa");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // X-ari jaramon egiteko
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

        // --- KLASIFIKAZIOA PANELA (Corregido) ---
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
      
        
        // ---  JOKALARIAK PANELA () ---

        // Panel para combo + tablas

     // --- JOKALARIAK ALDATU PANELA ---
        JokalariakPanela = new JPanel(null); // layout absoluto
        JokalariakPanela.setPreferredSize(new Dimension(1000, 600));

        // --- Título ---
        JLabel titleJokalariak = new JLabel("JOKALARIAK ALDATU", JLabel.CENTER);
        titleJokalariak.setFont(titleFont);
        titleJokalariak.setBounds(50, 20, 900, 30);
        JokalariakPanela.add(titleJokalariak);

        // --- ComboBox Izquierda ---
        JComboBox<String> comboIzquierda = new JComboBox<>();
        comboIzquierda.setBounds(50, 60, 200, 25);
        JokalariakPanela.add(comboIzquierda);
        for (Taldea t : Metodoak.taldeakMasterList) {
        	comboIzquierda.addItem(t.getIzena());
        }
       

        // --- ComboBox Derecha ---
        JComboBox<String> comboDerecha = new JComboBox<>();
        comboDerecha.setBounds(750, 60, 200, 25);
        JokalariakPanela.add(comboDerecha);
        for (Taldea t : Metodoak.taldeakMasterList) {
        	comboDerecha.addItem(t.getIzena());
        }

        // --- Panel Izquierda con Scroll (JTable ejemplo) ---
        String[] columnasIzquierda = {"Izena", "Abizena", "DNI", "Taldea"};
        DefaultTableModel modeloIzquierda = new DefaultTableModel(columnasIzquierda, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // ninguna celda es editable
            }
        };
        JTable tablaIzquierda = new JTable(modeloIzquierda);
        JScrollPane scrollIzquierda = new JScrollPane(tablaIzquierda);
        scrollIzquierda.setBounds(50, 100, 400, 400);
        JokalariakPanela.add(scrollIzquierda);

        // --- Panel Derecha con Scroll (JTable ejemplo) ---
        String[] columnasDerecha = {"Izena", "Abizena", "DNI", "Taldea"};
        DefaultTableModel modeloDerecha = new DefaultTableModel(columnasDerecha, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // ninguna celda es editable
            }
        };        JTable tablaDerecha = new JTable(modeloDerecha);
        JScrollPane scrollDerecha = new JScrollPane(tablaDerecha);
        scrollDerecha.setBounds(550, 100, 400, 400);
        JokalariakPanela.add(scrollDerecha);
        // Listener del comboBox izquierdo
        comboIzquierda.addActionListener(e -> {
            String seleccionado = (String) comboIzquierda.getSelectedItem();
            Metodoak.actualizarTablasJokalariak(seleccionado, tablaIzquierda);
        });
        // Listener del comboBox derecho
        comboDerecha.addActionListener(e -> {
            String seleccionado = (String) comboDerecha.getSelectedItem();
            Metodoak.actualizarTablasJokalariak(seleccionado, tablaDerecha);
        });

        // --- Botón Aldatu (medio) ---
        JButton btnAldatu = new JButton("Aldatu");
        btnAldatu.setBounds(460, 260, 80, 40);
        btnAldatu.addActionListener(e -> {
            // Aquí irá la lógica para intercambiar o modificar jugadores
        });
        JokalariakPanela.add(btnAldatu);

        // --- Botón Irten ---
        JButton btnIrten = new JButton("Irten");
        btnIrten.setBounds(50, 520, 100, 30);
        btnIrten.addActionListener(e -> cardLayout.show(contentPanel, "Hasiera"));
        JokalariakPanela.add(btnIrten);

        // --- Botón Atera ---
        JButton btnAtera = new JButton("Atera");
        btnAtera.setBounds(850, 520, 100, 30);
        btnAtera.addActionListener(e -> Metodoak.atera());
        JokalariakPanela.add(btnAtera);
        
        
        
        
        
        
        
        
        // --- TALDEAK PANELA () ---

        
     // Panel para combo + tablas
        TaldeakPanela = new JPanel(null); // layout absoluto
        TaldeakPanela.setPreferredSize(new Dimension(1000,600));

        // --- ComboBox arriba ---
        comboBox = new JComboBox<>();
        for (Taldea t : Metodoak.taldeakMasterList) {
            comboBox.addItem(t.getIzena());
        }
        comboBox.setBounds(400, 10, 200, 25); // posición y tamaño
        TaldeakPanela.add(comboBox);

        // --- Tabla pequeña ---
        String[] columnasPequena = {"SorreraUrtea", "Lehendakari", "N_Bazkideak"};
        DefaultTableModel modeloPequena = new DefaultTableModel(columnasPequena,0);
        tablaPequena = new JTable(modeloPequena);
        tablaPequena.setFillsViewportHeight(true);

        JScrollPane scrollPequena = new JScrollPane(tablaPequena);
        scrollPequena.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPequena.setBounds(50, 50, 900, tablaPequena.getRowHeight() + tablaPequena.getTableHeader().getPreferredSize().height);
        TaldeakPanela.add(scrollPequena);

        // --- Tabla grande ---
        String[] columnasGrande = {"Izena","Abizena","JaiotzeData","NAN","Taldea","Prezioa","JokalariarenPuntuak"};
        DefaultTableModel modeloGrande = new DefaultTableModel(columnasGrande,0);
        tablaGrande = new JTable(modeloGrande);
        tablaGrande.setFillsViewportHeight(true);

        JScrollPane scrollGrande = new JScrollPane(tablaGrande);
        scrollGrande.setBounds(50, 120, 900, 400); // ajustable a tu gusto
        scrollGrande.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        TaldeakPanela.add(scrollGrande);

        // Listener del comboBox
        comboBox.addActionListener(e -> {
            String seleccionado = (String) comboBox.getSelectedItem();
            Metodoak.actualizarTablasTaldeak(seleccionado, tablaPequena, tablaGrande);
        });
     // --- Botones Atzerantz y Atera para TaldeakPanela ---
        JButton atzerantzTaldeak = new JButton("Atzerantz");
        atzerantzTaldeak.setBounds(50, 540, 100, 30); // posición similar a otros paneles
        atzerantzTaldeak.addActionListener(e -> cardLayout.show(contentPanel, "Hasiera"));

        JButton ateraTaldeak = new JButton("Atera");
        ateraTaldeak.setBounds(850, 540, 100, 30); // posición similar a otros paneles
        ateraTaldeak.addActionListener(e -> Metodoak.atera());

        // Añadir al panel
        TaldeakPanela.add(atzerantzTaldeak);
        TaldeakPanela.add(ateraTaldeak);




    
    EmaitzaPanela = panelBigarrenakSortu("EMAITZAK SARTZEKO PANELA");
    }


    private void eguneratuKlasifikazioa() {
        modeloTabla.setRowCount(0);
        Metodoak.taldeakMasterList.sort((t1, t2) -> t2.getPuntuTotalak() - t1.getPuntuTotalak());

        for (int i = 0; i < Metodoak.taldeakMasterList.size(); i++) {
            Taldea t = (Taldea) Metodoak.taldeakMasterList.get(i);
            // ORDEN CORREGIDO SEGUN TU IMAGEN:
            Object[] TaldeInfo = {
                t.getIzena(),         // 1. Columna: Taldea
                t.getPuntuTotalak(),  // 2. Columna: P. Totalak
                t.getIrabazitakoak(), // 3. Columna: Irabazi
                t.getGaldutakoak(),   // 4. Columna: Galdu
                t.getPuntuakF(),      // 5. Columna: Aldeko
                t.getPuntuakC()       // 6. Columna: Aurkako
            };
            modeloTabla.addRow(TaldeInfo);
        }
    }

    // --- Metodos Auxiliares ---
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