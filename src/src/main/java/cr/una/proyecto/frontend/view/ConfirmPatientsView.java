//package cr.una.proyecto.frontend.view;
//
//import cr.una.proyecto.frontend.controller.AppointmentsController;
//
//import javax.swing.*;
//import javax.swing.border.TitledBorder;
//import java.awt.*;
//
///**
// * @author Felipe Guzman Rodriguez
// * @author Manfred Joel Aguero Campos
// */
//
//public class ConfirmPatientsView extends JPanel {
//    private final AppointmentsController controller;
//    private JLabel searchLabel;
//    private JTextField searchTextField;
//    private JTable appointmentsTable;
//    private JPanel form, searchPanel;
//    private JScrollPane tableScrollPane;
//    private JButton confirmButton, searchButton;
//
//    /**
//     * Constructor with a parameter, set the controller with the parameter, creates a
//     * JFrame, set its size, Default close operation, icon image, visibility and location.
//     *
//     * @param controller the appointments controller and action listener
//     */
//    public ConfirmPatientsView(AppointmentsController controller) {
//        super();
//
//        setSize(700, 311);
//        this.controller = controller;
//
//        initComponents();
//        setBackground(new Color(101, 221, 255, 151));
//        setVisible(false);
//    }
//
//    /**
//     * Calls the others init methods to initialize each component.
//     */
//    public void initComponents() {
//        initPanel();
//        initLabel();
//        initTextField();
//        initButton();
//        initTable();
//        initScrollPane();
//        locatedObjects();
//        initSplitPane();
//    }
//
//    /**
//     * Initializes JPanel.
//     */
//    public void initPanel() {
//        form = new JPanel();
//        form.setName("form");
//        searchPanel = new JPanel();
//        searchPanel.setName("searchPanel");
//    }
//
//    /**
//     * Initializes each label and aligns them in the center.
//     */
//    public void initLabel() {
//        searchLabel = new JLabel("Search: ");
//        searchLabel.setHorizontalAlignment(JLabel.CENTER);
//        searchLabel.setVerticalAlignment(JLabel.CENTER);
//    }
//
//    /**
//     * Initializes each text field and set its action listener with the controller.
//     */
//    public void initTextField() {
//        searchTextField = new JTextField(25);
//        searchTextField.addActionListener(controller);
//        searchTextField.setName("searchTextField");
//    }
//
//    /**
//     * Initializes each button and set its action listener with the controller.
//     */
//    public void initButton() {
//        confirmButton = new JButton("Confirm");
//        confirmButton.addActionListener(controller);
//        confirmButton.setName("confirmButton");
//
//        searchButton = new JButton("Search");
//        searchButton.addActionListener(controller);
//        searchButton.setName("searchButton");
//    }
//
//    /**
//     * Initializes JTable and set its model with the one that the controller has.
//     */
//    public void initTable() {
//        appointmentsTable = new JTable();
//        appointmentsTable.setModel(controller.getAppointmentsTableModel());
//    }
//
//    /**
//     * Initializes JScrollPane, set its JTable, size and border.
//     */
//    public void initScrollPane() {
//        tableScrollPane = new JScrollPane(appointmentsTable);
//        tableScrollPane.setPreferredSize(new Dimension(700, 182));
//        tableScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Appointments Data", TitledBorder.CENTER, TitledBorder.TOP));
//    }
//
//    /**
//     * Initializes JSplitPanes
//     */
//    public void initSplitPane() {
//        JSplitPane splitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tableScrollPane, form);
//        splitPane2.setEnabled(false);
//        JSplitPane splitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, searchPanel, splitPane2);
//        splitPane1.setEnabled(false);
//        this.add(splitPane1);
//    }
//
//    /**
//     * Locates different components in each panel and in the JFrame.
//     */
//    public void locatedObjects() {
//        searchPanel.add(searchLabel);
//        searchPanel.add(searchTextField);
//
//        form.add(searchButton);
//        form.add(confirmButton);
//    }
//
//    /**
//     * Returns search Button
//     *
//     * @return the search button
//     */
//    public JButton getSearchButton() {
//        return searchButton;
//    }
//
//    /**
//     * Returns search's text field
//     *
//     * @return the search's text field.
//     */
//    public JTextField getSearchTextField() {
//        return searchTextField;
//    }
//
//    /**
//     * Returns confirm button.
//     *
//     * @return the confirm button
//     */
//    public JButton getConfirmButton() {
//        return confirmButton;
//    }
//
//    /**
//     * Returns appointments table.
//     *
//     * @return appointments' table
//     */
//    public JTable getAppointmentsTable() {
//        return appointmentsTable;
//    }
//}
