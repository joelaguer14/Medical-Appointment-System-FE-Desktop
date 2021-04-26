package cr.una.proyecto.frontend.view;

import cr.una.proyecto.frontend.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * @author Felipe Guzman Rodriguez
 * @author Manfred Joel Aguero Campos
 */

public class MainView extends JFrame implements ItemListener {

    String[] comboBoxItems = {"Welcome", "Patients", "Doctor's Offices", "Doctors",
            "Appointments", "Confirm Appointments"};
    JComboBox<String> comboBox;
    JLabel welcomeLabel;
    MainController mainController;
    private JPanel cards, comboBoxPane, welcomePanel;

    /**
     * Constructor. Creates the JFrame, set its size, icon image, JMenuBar, Default
     * close operation and visibility.
     */
    public MainView() {
        super("Medical Appointment System");
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 730);

        initComponents();

        this.setVisible(true);
    }

    /**
     * Calls the others init methods to initialize each component.
     */
    public void initComponents() {
        initController();
        initLabels();
        initPanels();
        initComboBox();
        initLocatedObjects();
    }

    /**
     * Initializes controller.
     */
    public void initController() {
        mainController = MainController.getInstance(this);
        mainController.initController();
    }

    /**
     * Initializes panels.
     */
    public void initPanels() {
        comboBoxPane = new JPanel();
        comboBoxPane.setName("cbPane");
        cards = new JPanel(new CardLayout());
        cards.setName("cards");
        welcomePanel = new JPanel();
        welcomePanel.setName("welcomePanel");
        welcomePanel.setLayout(new GridBagLayout());
        welcomePanel.setBackground(new Color(101, 221, 255, 151));
    }

    /**
     * Initializes the JLabels
     */
    public void initLabels() {
        welcomeLabel = new JLabel("Medical Appointments Control System");
        welcomeLabel.setName("welcomeLabel");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setVerticalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 30));
    }

    /**
     * Initializes comboBox.
     */
    public void initComboBox() {
        comboBox = new JComboBox<>(comboBoxItems);
        comboBox.setEditable(false);
        comboBox.addItemListener(this);

        DefaultListCellRenderer listRenderer;
        listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        comboBox.setRenderer(listRenderer);
    }

    /**
     * Initializes located objects.
     */
    public void initLocatedObjects() {
        welcomePanel.add(welcomeLabel);
        comboBoxPane.add(comboBox);
        cards.add(welcomePanel, comboBoxItems[0]);
        cards.add(mainController.getPatientsController().getPatientsView(), comboBoxItems[1]);
        cards.add(mainController.getDoctorOfficeController().getDoctorOfficeView(), comboBoxItems[2]);
        cards.add(mainController.getDoctorController().getDoctorView(), comboBoxItems[3]);
        cards.add(mainController.getAppointmentsController().getAppointmentsView(), comboBoxItems[4]);
//      cards.add(mainController.getAppointmentsController().getConfirmPatientsView(), comboBoxItems[5]);
        this.add(comboBoxPane, BorderLayout.PAGE_START);
        this.add(cards, BorderLayout.CENTER);
    }

    /**
     * Called just after a state change in the listened-to component.
     *
     * @param evt, The ItemEvent class defines two states: SELECTED and DESELECTED.
     */
    @Override
    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, (String) evt.getItem());
        if (evt.getItem().equals(comboBoxItems[4])) {
            mainController.updateAppointmentsComboBoxes();
        } else if (evt.getItem().equals(comboBoxItems[3])) {
            mainController.updateDoctorComboBox();
        }
    }

    public String[] getComboBoxItems() {
        return comboBoxItems;
    }

    public JComboBox<String> getComboBox() {
        return comboBox;
    }

    public JLabel getWelcomeLabel() {
        return welcomeLabel;
    }

    public MainController getMainController() {
        return mainController;
    }

    public JPanel getCards() {
        return cards;
    }

    public JPanel getComboBoxPane() {
        return comboBoxPane;
    }

    public JPanel getWelcomePanel() {
        return welcomePanel;
    }

    public void setComboBoxItems(String[] comboBoxItems) {
        this.comboBoxItems = comboBoxItems;
    }

    public void setComboBox(JComboBox<String> comboBox) {
        this.comboBox = comboBox;
    }

    public void setWelcomeLabel(JLabel welcomeLabel) {
        this.welcomeLabel = welcomeLabel;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setCards(JPanel cards) {
        this.cards = cards;
    }

    public void setComboBoxPane(JPanel comboBoxPane) {
        this.comboBoxPane = comboBoxPane;
    }

    public void setWelcomePanel(JPanel welcomePanel) {
        this.welcomePanel = welcomePanel;
    }
}