package cr.una.proyecto.frontend.view;

import com.toedter.calendar.JDateChooser;
import cr.una.proyecto.frontend.controller.PatientsController;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * @author Felipe Guzman Rodriguez
 * @author Manfred Joel Aguero Campos
 */

public class PatientsView extends JPanel {
    private JTable patientsTable;
    private JLabel searchLabel, fullNameLabel, addressLabel, addressTypeLabel, phoneLabel, birthDateLabel,
            diseasesLabel, observationsLabel, phoneTypeLabel;
    private JPanel form, searchPanel;
    private JScrollPane tableScrollPane;
    private JButton addButton, deleteButton, searchButton, updateButton;
    private JTextField searchTextField, fullNameTextField, phoneTextField, birthDateTextField, addressTypeTextField,
            phoneTypeTextField;
    private JTextArea addressTextArea, diseasesTextArea, observationsTextArea;
    private final PatientsController controller;
    private final GridBagConstraints constraints = new GridBagConstraints();
    JDateChooser birthDateChooser;

    /**
     * Constructor with the controller param.
     *
     * @param controller patient's controller
     */
    public PatientsView(PatientsController controller) {
        super();
        setSize(700, 700);

        this.controller = controller;

        initComponents();
        setBackground(new Color(101, 221, 255, 151));
        setVisible(false);
    }

    /**
     * Calls the others init methods to initialize each component.
     */
    public void initComponents() {
        initPanel();
        initLabel();
        initDateChooser();
        initTextField();
        initTextArea();
        initButton();
        initTable();
        initScrollPane();
        locatedObjects();
        initSplitPane();
    }

    /**
     * Initializes JPanels form and searchPanel and sets the layout of form with
     * a GridBagLayout.
     */
    public void initPanel() {
        form = new JPanel();
        form.setLayout(new GridBagLayout());
        form.setName("form");
        searchPanel = new JPanel();
        searchPanel.setName("searchPanel");
    }

    /**
     * Initializes JDateChooser.
     */
    public void initDateChooser() {
        birthDateChooser = new JDateChooser();
    }

    /**
     * Initializes each label and aligns them in the center.
     */
    public void initLabel() {
        searchLabel = new JLabel("Search:");
        searchLabel.setHorizontalAlignment(JLabel.CENTER);
        searchLabel.setVerticalAlignment(JLabel.CENTER);

        fullNameLabel = new JLabel("Full Name:");
        fullNameLabel.setHorizontalAlignment(JLabel.CENTER);
        fullNameLabel.setVerticalAlignment(JLabel.CENTER);

        phoneLabel = new JLabel("Phone number:");
        phoneLabel.setHorizontalAlignment(JLabel.CENTER);
        phoneLabel.setVerticalAlignment(JLabel.CENTER);

        addressLabel = new JLabel("Address:");
        addressLabel.setHorizontalAlignment(JLabel.CENTER);
        addressLabel.setVerticalAlignment(JLabel.CENTER);

        birthDateLabel = new JLabel("Date of Birth:");
        birthDateLabel.setHorizontalAlignment(JLabel.CENTER);
        birthDateLabel.setVerticalAlignment(JLabel.CENTER);

        diseasesLabel = new JLabel("Diseases:");
        diseasesLabel.setHorizontalAlignment(JLabel.CENTER);
        diseasesLabel.setVerticalAlignment(JLabel.CENTER);

        observationsLabel = new JLabel("Observations: ");
        observationsLabel.setHorizontalAlignment(JLabel.CENTER);
        observationsLabel.setVerticalAlignment(JLabel.CENTER);

        addressTypeLabel = new JLabel("Address Type: ");
        addressTypeLabel.setHorizontalAlignment(JLabel.CENTER);
        addressTypeLabel.setVerticalAlignment(JLabel.CENTER);

        phoneTypeLabel = new JLabel("Phone Type: ");
        phoneTypeLabel.setHorizontalAlignment(JLabel.CENTER);
        phoneTypeLabel.setVerticalAlignment(JLabel.CENTER);
    }

    /**
     * Initializes each text field and set its action listener with the controller.
     */
    public void initTextField() {
        searchTextField = new JTextField(10);
        searchTextField.addActionListener(controller);
        searchTextField.setName("search");

        fullNameTextField = new JTextField(50);
        fullNameTextField.addActionListener(controller);
        fullNameTextField.setName("fullName");

        phoneTextField = new JTextField(20);
        phoneTextField.addActionListener(controller);
        phoneTextField.setName("phone");

        birthDateTextField = new JTextField(15);
        birthDateTextField.addActionListener(controller);

        addressTypeTextField = new JTextField(30);
        addressTypeTextField.addActionListener(controller);

        phoneTypeTextField = new JTextField(30);
        phoneTypeTextField.addActionListener(controller);
    }

    /**
     * Initializes each text area.
     */
    public void initTextArea() {
        addressTextArea = new JTextArea(4, 15);
        addressTextArea.setName("address");
        diseasesTextArea = new JTextArea(4, 15);
        diseasesTextArea.setName("diseases");
        observationsTextArea = new JTextArea(4, 15);
        observationsTextArea.setName("observations");
    }

    /**
     * Initializes each button and set its action listener with the controller.
     */
    public void initButton() {
        addButton = new JButton("Add");
        addButton.addActionListener(controller);
        addButton.setName("addButton");

        searchButton = new JButton("Search");
        searchButton.addActionListener(controller);
        searchButton.setName("searchButton");

        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(controller);
        deleteButton.setName("deleteButton");

        updateButton = new JButton("Update");
        updateButton.addActionListener(controller);
        updateButton.setName("updateButton");
    }

    /**
     * Initializes JTable and set its model with the one that the controller has.
     */
    public void initTable() {
        patientsTable = new JTable();
        patientsTable.setModel(controller.getPatientsTableModel());
    }

    /**
     * Initializes JScrollPane, set its JTable, size and border.
     */
    public void initScrollPane() {
        tableScrollPane = new JScrollPane(patientsTable);
        tableScrollPane.setPreferredSize(new Dimension(700, 152));
        tableScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Patients Data", TitledBorder.CENTER, TitledBorder.TOP));
    }

    /**
     * Initializes JSplitPanes.
     */
    public void initSplitPane() {
        JSplitPane splitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tableScrollPane, form);
        splitPane2.setEnabled(false);
        JSplitPane splitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, searchPanel, splitPane2);
        splitPane1.setEnabled(false);
        this.add(splitPane1);
    }

    /**
     * Locates different components in each panel and in the JFrame.
     */
    @SuppressWarnings("DuplicatedCode")
    public void locatedObjects() {
        searchPanel.add(searchLabel);
        searchPanel.add(searchTextField);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 0;
        constraints.gridy = 0;
        form.add(fullNameLabel, constraints);

        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 1;
        constraints.gridy = 0;
        form.add(fullNameTextField, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 0;
        constraints.gridy = 1;
        form.add(phoneLabel, constraints);

        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 1;
        constraints.gridy = 1;
        form.add(phoneTextField, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 0;
        constraints.gridy = 2;
        form.add(phoneTypeLabel, constraints);

        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 1;
        constraints.gridy = 2;
        form.add(phoneTypeTextField, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 0;
        constraints.gridy = 3;
        form.add(addressLabel, constraints);

        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 1;
        constraints.gridy = 3;
        form.add(addressTextArea, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 0;
        constraints.gridy = 4;
        form.add(addressTypeLabel, constraints);

        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 1;
        constraints.gridy = 4;
        form.add(addressTypeTextField, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 0;
        constraints.gridy = 5;
        form.add(birthDateLabel, constraints);

        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 1;
        constraints.gridy = 5;
        form.add(birthDateChooser, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 0;
        constraints.gridy = 6;
        form.add(diseasesLabel, constraints);

        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 1;
        constraints.gridy = 6;
        form.add(diseasesTextArea, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 0;
        constraints.gridy = 7;
        form.add(observationsLabel, constraints);

        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 1;
        constraints.gridy = 7;
        form.add(observationsTextArea, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 9;
        form.add(addButton, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 9;
        form.add(updateButton, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 10;
        constraints.gridwidth = 2;
        form.add(searchButton, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 11;
        constraints.gridwidth = 2;
        form.add(deleteButton, constraints);
    }

    public JTable getPatientsTable() {
        return patientsTable;
    }

    public JLabel getSearchLabel() {
        return searchLabel;
    }

    public JLabel getFullNameLabel() {
        return fullNameLabel;
    }

    public JLabel getAddressLabel() {
        return addressLabel;
    }

    public JLabel getAddressTypeLabel() {
        return addressTypeLabel;
    }

    public JLabel getPhoneLabel() {
        return phoneLabel;
    }

    public JLabel getBirthDateLabel() {
        return birthDateLabel;
    }

    public JLabel getDiseasesLabel() {
        return diseasesLabel;
    }

    public JLabel getObservationsLabel() {
        return observationsLabel;
    }

    public JLabel getPhoneTypeLabel() {
        return phoneTypeLabel;
    }

    public JPanel getForm() {
        return form;
    }

    public JPanel getSearchPanel() {
        return searchPanel;
    }

    public JScrollPane getTableScrollPane() {
        return tableScrollPane;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JTextField getSearchTextField() {
        return searchTextField;
    }

    public JTextField getFullNameTextField() {
        return fullNameTextField;
    }

    public JTextField getPhoneTextField() {
        return phoneTextField;
    }

    public JTextField getBirthDateTextField() {
        return birthDateTextField;
    }

    public JTextField getAddressTypeTextField() {
        return addressTypeTextField;
    }

    public JTextField getPhoneTypeTextField() {
        return phoneTypeTextField;
    }

    public JTextArea getAddressTextArea() {
        return addressTextArea;
    }

    public JTextArea getDiseasesTextArea() {
        return diseasesTextArea;
    }

    public JTextArea getObservationsTextArea() {
        return observationsTextArea;
    }

    public PatientsController getController() {
        return controller;
    }

    public GridBagConstraints getConstraints() {
        return constraints;
    }

    public JDateChooser getBirthDateChooser() {
        return birthDateChooser;
    }

    public void setPatientsTable(JTable patientsTable) {
        this.patientsTable = patientsTable;
    }

    public void setSearchLabel(JLabel searchLabel) {
        this.searchLabel = searchLabel;
    }

    public void setFullNameLabel(JLabel fullNameLabel) {
        this.fullNameLabel = fullNameLabel;
    }

    public void setAddressLabel(JLabel addressLabel) {
        this.addressLabel = addressLabel;
    }

    public void setAddressTypeLabel(JLabel addressTypeLabel) {
        this.addressTypeLabel = addressTypeLabel;
    }

    public void setPhoneLabel(JLabel phoneLabel) {
        this.phoneLabel = phoneLabel;
    }

    public void setBirthDateLabel(JLabel birthDateLabel) {
        this.birthDateLabel = birthDateLabel;
    }

    public void setDiseasesLabel(JLabel diseasesLabel) {
        this.diseasesLabel = diseasesLabel;
    }

    public void setObservationsLabel(JLabel observationsLabel) {
        this.observationsLabel = observationsLabel;
    }

    public void setPhoneTypeLabel(JLabel phoneTypeLabel) {
        this.phoneTypeLabel = phoneTypeLabel;
    }

    public void setForm(JPanel form) {
        this.form = form;
    }

    public void setSearchPanel(JPanel searchPanel) {
        this.searchPanel = searchPanel;
    }

    public void setTableScrollPane(JScrollPane tableScrollPane) {
        this.tableScrollPane = tableScrollPane;
    }

    public void setAddButton(JButton addButton) {
        this.addButton = addButton;
    }

    public void setDeleteButton(JButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    public void setSearchButton(JButton searchButton) {
        this.searchButton = searchButton;
    }

    public void setUpdateButton(JButton updateButton) {
        this.updateButton = updateButton;
    }

    public void setSearchTextField(JTextField searchTextField) {
        this.searchTextField = searchTextField;
    }

    public void setFullNameTextField(JTextField fullNameTextField) {
        this.fullNameTextField = fullNameTextField;
    }

    public void setPhoneTextField(JTextField phoneTextField) {
        this.phoneTextField = phoneTextField;
    }

    public void setBirthDateTextField(JTextField birthDateTextField) {
        this.birthDateTextField = birthDateTextField;
    }

    public void setAddressTypeTextField(JTextField addressTypeTextField) {
        this.addressTypeTextField = addressTypeTextField;
    }

    public void setPhoneTypeTextField(JTextField phoneTypeTextField) {
        this.phoneTypeTextField = phoneTypeTextField;
    }

    public void setAddressTextArea(JTextArea addressTextArea) {
        this.addressTextArea = addressTextArea;
    }

    public void setDiseasesTextArea(JTextArea diseasesTextArea) {
        this.diseasesTextArea = diseasesTextArea;
    }

    public void setObservationsTextArea(JTextArea observationsTextArea) {
        this.observationsTextArea = observationsTextArea;
    }

    public void setBirthDateChooser(JDateChooser birthDateChooser) {
        this.birthDateChooser = birthDateChooser;
    }
}
