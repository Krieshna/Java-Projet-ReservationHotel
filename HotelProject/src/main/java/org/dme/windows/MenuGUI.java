package org.dme.windows;



import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuGUI extends JFrame {

    //BUTTONS
    private JButton reservationsBtn = new JButton("Gestion des réservations");
    private JButton clientsBtn = new JButton("Gestion des clients");
    private JButton roomsBtn = new JButton("Gestion des chambres");
    private JButton comptabilityBtn = new JButton("Comptabilité");
    private JButton quitBtn = new JButton("Quitter");

    public MenuGUI() {
        super();
        setTitle("Gestionnaire de réservation");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //LISTENER
        reservationsBtn.addActionListener(e -> reservationsBtnListener(e));

        clientsBtn.addActionListener(e -> clientsBtnListener(e));

        roomsBtn.addActionListener(e -> roomsBtnListener(e));

        comptabilityBtn.addActionListener(e -> comptabilityBtnListener(e));

        quitBtn.addActionListener(e -> quitBtnListener(e));


        //PANES
        JPanel rootPane = (JPanel) this.getContentPane();
        GridBagConstraints gbc = new GridBagConstraints();
        rootPane.setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        rootPane.add(reservationsBtn, gbc);
        rootPane.add(clientsBtn, gbc);
        rootPane.add(roomsBtn, gbc);
        rootPane.add(comptabilityBtn, gbc);
        rootPane.add(quitBtn, gbc);

        //rootPane.setBackground(new Color(250, 250, 250));
        rootPane.setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    public static void load() {
        try {
            UIManager.setLookAndFeel( new NimbusLookAndFeel() );
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println("Bug look and feel");
            e.getStackTrace();
        }

        MenuGUI menuGUI = new MenuGUI();
        menuGUI.pack();
        menuGUI.setVisible(true);
        menuGUI.setLocationRelativeTo(null);
    }


    private void reservationsBtnListener(ActionEvent e) {
        System.out.println("Réservations...");
        ReservationListGUI.load();
    }

    private void clientsBtnListener(ActionEvent e) {
        System.out.println("Clients...");
        ClientListGUI.load();
    }

    private void roomsBtnListener(ActionEvent e) {
    	RoomListGUI.load();
        System.out.println("Chambres...");
    }

    private void comptabilityBtnListener(ActionEvent e) {
        System.out.println("Comptabilité...");
    }

    private void quitBtnListener(ActionEvent e) {
        System.out.println("Fermeture de l'application...");
        this.dispose();
    }
}
