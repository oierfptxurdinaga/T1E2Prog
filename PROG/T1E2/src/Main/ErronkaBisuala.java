package Main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import E2.ErabiltzaileMota;
import Metodoak.Metodoak;

public class ErronkaBisuala extends JFrame {

    // ==========================================================
    // 1. Atrbutuak Ordenez Jarrita
    // ==========================================================

	// --- Atributuak POJO-entzako ---

	
    // Nabegazio Layouta eta Content Panel-a
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private Font titleFont; // Barruko titulurako letra-tipoa

    // --- Bigarreneko Panelak ---
    // (Ordenatuta nola agertuko diren)
    private JPanel LoginPanela;
    private JPanel HasierakoPanela;
    private JPanel KlasifikazioaPanela;
    private JPanel EmaitzaPanela;
    private JPanel TaldeakPanela;
    private JPanel JokalariakPanela;

    // --- Osagaiak JPanel 1: LoginPanela ---
    // JLabels (4)
    private JLabel logoaImg1;
    private JLabel erabiltzaileak;
    private JLabel pasahitza;
    // JTextFields (2)
    private JTextField textErabiltzaile;
    private JPasswordField textPasahitza; // JPasswordField pasahitzak
    // JButton (1)
    private JButton sartu;

    // --- Osagaiak JPanel 2: HasierakoPanela ---
    // JLabels (4)
    private JLabel logoaImg2;
    private JLabel img1;
    private JLabel img2;
    // JButtons (6)
    private JButton atzerantz;
    private JButton atera;
    private JButton klasifikazioaIkusi;
    private JButton sartuEmaitza;
    private JButton taldeakIkusi;
    private JButton jokalariakAldatu;


    // ==========================================================
    // 2. Eraikitzailea
    // ==========================================================

    public ErronkaBisuala() {
        // --- JFrame Konfigurazioa ---
        setTitle("Bizkaiko Saskibaloi Federazioa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700); // Tamaina handia 
        setLocationRelativeTo(null);
        
        // Tituluan letra-tipoa definitzea
        titleFont = new Font("Verdana", Font.BOLD, 24);

        // --- Konfigurazioa Content Panel (CardLayout) ---
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        setContentPane(contentPanel);

        // ==========================================================
        // 3. Panelen Inizializazioa eta Nabegazioa
        // ==========================================================

        // 1. 6 Panelen Inizializazioa
        inizializatuPanelak();
        konfiguratuOsagaiBisualak();
        // 2. Gehitu Panelak Content Panel-ean
        contentPanel.add(LoginPanela, "Login");
        contentPanel.add(HasierakoPanela, "Hasiera");
        contentPanel.add(KlasifikazioaPanela, "Klasifikazioa");
        contentPanel.add(EmaitzaPanela, "Emaitzak");
        contentPanel.add(TaldeakPanela, "Taldeak");
        contentPanel.add(JokalariakPanela, "Jokalariak");

        // 3. Nabegazioa panelen artean eta botoien ekintzak
        sartu.addActionListener(e -> {
        	
            // 1. Erabiltzailearen sarrerak lortzen ditu
            String usernameInput = textErabiltzaile.getText();
            String passwordInput = new String(textPasahitza.getPassword());
            
            // 2. Sarrerak balidatu hutsik dauden
            if (usernameInput.trim().isEmpty() || passwordInput.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Mesedez, bete eremu guztiak.", "Errorea", JOptionPane.ERROR_MESSAGE);
				return; // Ez jarraitu hurrengo pausoekin
			}
            
            // 3. Balidazioari deitu
            String rola = Metodoak.login(usernameInput, passwordInput);

            // 4. Zer aldatu behar den erabaki erabiltzailearen rolaren arabera
            if (rola != null) { 
                // Éxito:
                erakutsiPanelak(rola); // Habilitar/Deshabilitar botones
                cardLayout.show(contentPanel, "Hasiera");
            } else {
                // Erabiltzailea edo pasahitza okerra
            	JOptionPane.showMessageDialog(null, "Erabiltzaile edo Pasahitz okerra", "Errorea", JOptionPane.ERROR_MESSAGE);
                
            }
        });
        atzerantz.addActionListener(e -> cardLayout.show(contentPanel, "Login"));
        atera.addActionListener(e -> System.exit(0));
        
        klasifikazioaIkusi.addActionListener(e -> cardLayout.show(contentPanel, "Klasifikazioa"));
        sartuEmaitza.addActionListener(e -> cardLayout.show(contentPanel, "Emaitzak"));
        taldeakIkusi.addActionListener(e -> cardLayout.show(contentPanel, "Taldeak"));
        jokalariakAldatu.addActionListener(e -> cardLayout.show(contentPanel, "Jokalariak"));
        
        // Lehnen panel gisa LoginPanela erakutsi
        cardLayout.show(contentPanel, "Login");
        setVisible(true);
    }

    // ==========================================================
    // 5. Metodoen Inizializazioa
    // ==========================================================

    private void inizializatuPanelak() {
        // 1. LOGIN PANELA
        LoginPanela = new JPanel();
        LoginPanela.setLayout(null); // *** ABSOLUTE LAYOUT ***

        // Títulua (Estiloa iturria Verdana 24)
        JLabel titleLogin = new JLabel(getTitle(), JLabel.CENTER);
        titleLogin.setFont(titleFont);
        titleLogin.setBounds(50, 20, 900, 30);
        LoginPanela.add(titleLogin);

        // Login Panelaren osagaiak inizializatzea
        logoaImg1 = new JLabel("LOGOA IMAGEN", JLabel.CENTER);
        erabiltzaileak = new JLabel("Erabiltzailea:", JLabel.RIGHT);
        pasahitza = new JLabel("Pasahitza:", JLabel.RIGHT);
        textErabiltzaile = new JTextField(15);
        textPasahitza = new JPasswordField(15);
        sartu = new JButton("Sartu");

        // Kokapena eta tamaina setBounds erabiliz
        logoaImg1.setBounds(420, 92, 200, 150);
        erabiltzaileak.setBounds(250, 300, 150, 30);
        textErabiltzaile.setBounds(420, 300, 250, 30);
        pasahitza.setBounds(250, 350, 150, 30);
        textPasahitza.setBounds(420, 350, 250, 30);
        sartu.setBounds(483, 447, 100, 30);

        // Gehitu osagaiak panelera
        LoginPanela.add(logoaImg1);
        LoginPanela.add(erabiltzaileak);
        LoginPanela.add(pasahitza);
        LoginPanela.add(textErabiltzaile);
        LoginPanela.add(textPasahitza);
        LoginPanela.add(sartu);

        // 2. HASIERAKO PANELA
        HasierakoPanela = new JPanel();
        HasierakoPanela.setLayout(null);

        // Barruko titulua
        JLabel titleHasiera = new JLabel("HASIERA PANELA", JLabel.CENTER);
        titleHasiera.setBounds(50, 20, 900, 30);
        titleHasiera.setFont(titleFont);
        HasierakoPanela.add(titleHasiera);

        // Hasierako Panelaren osagaiak inizializatzea
        logoaImg2 = new JLabel("Logoa", JLabel.CENTER);
        logoaImg2.setBounds(394, 60, 240, 211);
        img1 = new JLabel("Img1", JLabel.CENTER);
        img1.setBounds(100, 250, 350, 200);
        img2 = new JLabel("Img2", JLabel.CENTER);
        img2.setBounds(550, 250, 350, 200);
        atzerantz = new JButton("Atzerantz");
        atzerantz.setBounds(800, 50, 100, 30);
        atera = new JButton("Atera");
        atera.setBounds(910, 50, 70, 30);
        klasifikazioaIkusi = new JButton("Klasifikazioa ikusi");
        klasifikazioaIkusi.setBounds(150, 480, 250, 40);
        sartuEmaitza = new JButton("Sartu Emaitza");
        sartuEmaitza.setBounds(600, 480, 250, 40);
        taldeakIkusi = new JButton("Taldeak ikusi");
        taldeakIkusi.setBounds(150, 530, 250, 40);
        jokalariakAldatu = new JButton("Jokalariak Aldatu");
        jokalariakAldatu.setBounds(600, 530, 250, 40);

        // Gehitu osagaiak panelera
        HasierakoPanela.add(logoaImg2);
        HasierakoPanela.add(img1);
        HasierakoPanela.add(img2);
        HasierakoPanela.add(atzerantz);
        HasierakoPanela.add(atera);
        HasierakoPanela.add(klasifikazioaIkusi);
        HasierakoPanela.add(sartuEmaitza);
        HasierakoPanela.add(taldeakIkusi);
        HasierakoPanela.add(jokalariakAldatu);
        
        // 3-6. Bigarren Panelak (Botoien Helmuga)
        
        KlasifikazioaPanela = panelBigarrenakSortu("KLASIFIKAZIOA PANELA");
        EmaitzaPanela = panelBigarrenakSortu("EMAITZAK SARTZEKO PANELA");
        TaldeakPanela = panelBigarrenakSortu("TALDEAK IKUSI PANELA");
        JokalariakPanela = panelBigarrenakSortu("JOKALARIAK ALDATU PANELA");
    }
    /**
     * Konfiguratu irudiak eta beste elementu bisualak
     */
    	private void konfiguratuOsagaiBisualak() {
    	    
    	    // 1. Logoa Nagusiaren Karga (Logoa.png)
    	    // Tamaina: 200x150
    	    kargatuIrudia(logoaImg1, 200, 150, "/Multimedia/logoa.png"); 
    	 
    	    // 1.2 Logoa Nagusiaren Karga (Logoa.png)
    	    // Tamaina: 200x150
    	    kargatuIrudia(logoaImg2, 200, 150, "/Multimedia/logoa.png"); 
    	    
    	    // 2. Albistearen Irudia 1 (img1.png)
    	    // Tamaina: 350x200
    	    kargatuIrudia(img1, 150, 150, "/Multimedia/img1.png"); 
    	    
    	    // 3. Albistearen Irudia 2 (img2.png)
    	    // Tamaina: 350x200
    	    kargatuIrudia(img2, 150, 150, "/Multimedia/img2.png"); 
    	    

    	}
    	private void kargatuIrudia(JLabel label, int width, int height, String path) {
    	    try {
    	        // Erabilita ErronkaBisuala.class Class Loader-a lortzeko
    	        java.net.URL imgURL = ErronkaBisuala.class.getResource(path);

    	        if (imgURL != null) {
    	            ImageIcon originalIcon = new ImageIcon(imgURL);

    	            // Redimensionatu (Redimensionar)
    	            Image originalImage = originalIcon.getImage();
    	            Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH); 
    	            ImageIcon resizedIcon = new ImageIcon(resizedImage);
    	            
    	            // Asignatu irudia
    	            label.setIcon(resizedIcon);
    	            label.setText(""); // Testua ezabatu irudia daukanean
    	        } else {
    	            // Akatsen kudeaketa
    	            label.setIcon(null); // Quitar cualquier icono anterior
    	            label.setText("ERROR: Irudia ez da aurkitu: " + path); 
    	            System.err.println("Ezin izan da irudia kargatu: " + path);
    	        }
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	        label.setIcon(null);
    	        label.setText("Irudi Karga Akatsa");
    	    }
    	}
    /**
     * Metodo bat panel sekundarioak sortzeko
     */
    private JPanel panelBigarrenakSortu(String titulo) {
        JPanel panel = new JPanel();
        panel.setLayout(null); // *** ABSOLUTE LAYOUT ***

        JLabel titleLabel = new JLabel(titulo, JLabel.CENTER);
        titleLabel.setFont(titleFont);
        
        // Kokapena eta tamaina
        titleLabel.setBounds(50, 20, 900, 30); 
        panel.add(titleLabel);
        
        // Botoia Hasierako Panela-ra itzultzeko
        JButton backButton = new JButton("Volver a Inicio");
        backButton.setBounds(50, 50, 150, 30);
        backButton.addActionListener(e -> cardLayout.show(contentPanel, "Hasiera"));
        panel.add(backButton);
        
        return panel;
    }
    
    /*==============
     Login Metodoak
	 ===============*/
     
    private void erakutsiPanelak(String rola) {
        
        // 1. Botoiak desgaitu lehenik
        klasifikazioaIkusi.setEnabled(false);
        sartuEmaitza.setEnabled(false);
        taldeakIkusi.setEnabled(false);
        jokalariakAldatu.setEnabled(false);
        
        // 2. Rolaren Arabera Botoiak Aktibatu
        switch (rola) {
            case "Admin":
                // Administradoreak ser ikus dezake eta aldatu.
                klasifikazioaIkusi.setEnabled(true);
                sartuEmaitza.setEnabled(true);
                taldeakIkusi.setEnabled(true);
                break;
            case "Presidente":
                // Presidentea ser ikus dezake eta aldatu.
                klasifikazioaIkusi.setEnabled(true);
                taldeakIkusi.setEnabled(true);
                jokalariakAldatu.setEnabled(true);
                break;
            case "Arrunta":
                // Arrunta ser ikus dezake.
                klasifikazioaIkusi.setEnabled(true);
                taldeakIkusi.setEnabled(true);
                break;
        }
    }
    

    // ==========================================================
    // 5. MAIN
    // ==========================================================

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new ErronkaBisuala());
    }
}