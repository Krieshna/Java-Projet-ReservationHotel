package org.dme.windows;



import org.dme.entities.Client;
import org.dme.entities.Room;
import org.dme.interfaces.PaginationDataProvider;
import org.dme.pagination.PaginatedTableDecorator;
import org.dme.repositories.ClientRepository;
import org.dme.repositories.RoomRepository;
import org.dme.utils.ObjectTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RoomListGUI extends JFrame {

    private static JTable table;
    private static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    List<Room> room = RoomRepository.readRoom();
    private JPanel tablePane;
    private PaginationDataProvider<Room> dataProvider;
    private PaginatedTableDecorator<Room> paginatedDecorator;
    //BUTTONS
    private JButton addBtn = new JButton("Ajouter");
    private JButton backBtn = new JButton("Retour");

    public RoomListGUI() {
        super();

        setTitle("Gestion des chambres");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        table = new JTable();
        table.setModel(createObjectDataModel());
        table.setAutoCreateRowSorter(true);
        dataProvider = createDataProvider();
        paginatedDecorator = PaginatedTableDecorator.decorate(table, dataProvider, new int[]{15, 30, 50, 75, 100}, 15);


        //LISTENER
        table.getSelectionModel().addListSelectionListener(e -> selectionListener(e));

        addBtn.addActionListener(e -> addBtnListener(e));

        backBtn.addActionListener(e -> backBtnListener(e));


        //PANES
        JPanel rootPane = (JPanel) this.getContentPane();
        JPanel menuPane = new JPanel();
        tablePane = new JPanel();
        //menuPane.setBackground(new Color(250, 250, 250));
        //tablePane.setBackground(new Color(250, 250, 250));


        tablePane.add(paginatedDecorator.getContentPanel());
        tablePane.setLayout(new GridLayout(1, 1));

        menuPane.setLayout(new FlowLayout());
        menuPane.add(addBtn);
        menuPane.add(backBtn);

        rootPane.setLayout(new BoxLayout(rootPane, BoxLayout.PAGE_AXIS));
        //rootPane.setBackground(new Color(250, 250, 250));
        rootPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        rootPane.add(tablePane);
        rootPane.add(menuPane);
    }

    public static void load() {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println("Bug look and feel");
            e.getStackTrace();
        }

        RoomListGUI clientListGUI = new RoomListGUI();
        clientListGUI.setVisible(true);
        clientListGUI.setSize(new Dimension(800, 410));
        clientListGUI.setLocationRelativeTo(null);
    }

    private static TableModel createObjectDataModel() {
        return new ObjectTableModel<Room>() {
            @Override
            public Object getValueAt(Room r, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return r.getNumber();
                    case 1:
                        return r.getOccupation();
                }
                return null;
            }

            @Override
            public int getColumnCount() {
                return 2;
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "Numéro";
                    case 1:
                        return "Occupation";
                }
                return null;
            }
        };
    }

    private static PaginationDataProvider<Room> createDataProvider() {

        final java.util.List<Room> rooms = RoomRepository.readRoom();

        return new PaginationDataProvider<Room>() {
            @Override
            public int getTotalRowCount() {
                return rooms.size();
            }

            @Override
            public List<Room> getRows(int startIndex, int endIndex) {
                return rooms.subList(startIndex, endIndex);
            }
        };
    }

    private void selectionListener(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
            this.dispose();
            ClientEditGUI.load(ClientRepository.getClientById(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString())));
        }
    }

    private void addBtnListener(ActionEvent e) {
        this.dispose();
        ClientAddGUI.load();
    }

    private void backBtnListener(ActionEvent e) {
        System.out.println("Fermeture de l'application...");
        this.dispose();
    }


}
