package cr.una.proyecto.frontend.view;

import cr.una.proyecto.frontend.controller.DoctorOfficeController;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * @author Felipe Guzman Rodriguez
 * @author Manfred Joel Aguero Campos
 */

public class DoctorOfficeView extends JPanel {
    private final DoctorOfficeController controller;
    private final GridBagConstraints constraints = new GridBagConstraints();
    private JLabel nameLabel, daysLabel, hoursLabel, phoneLabel, searchLabel, addressesLabel, phoneTypeLabel;
    private JTextField nameTextField, daysTextField, hoursTextField, phoneTextField, searchTextField, phoneTypeTextField;
    private JTextArea addressesTextArea;
    private JTable consultingRoomsTable;
    private JPanel form, searchPanel;
    private JScrollPane tableScrollPane;
    private JButton addButton, deleteButton, searchButton, updateButton;

    /**
     * Constructor with a parameter, set the controller with the parameter, creates a
     * JFrame, set its size, Default close operation, icon image, visibility and location.
     *
     * @param controller consulting room controller and action listener
     */
    public DoctorOfficeView(DoctorOfficeController controller) {
        super();

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
        initTextField();
        initTextArea();
        initButton();
        initTable();
        initScrollPane();
        locatedObjects();
        initSplitPane();
    }

    /**
     * Initializes the JPanels form and searchPanel and sets the layout of form with
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
     * Initializes each label and aligns them in the center.
     */
    public void initLabel() {
        searchLabel = new JLabel("Search:");
        searchLabel.setHorizontalAlignment(JLabel.CENTER);
        searchLabel.setVerticalAlignment(JLabel.CENTER);

        phoneLabel = new JLabel("Phone number:");
        phoneLabel.setHorizontalAlignment(JLabel.CENTER);
        phoneLabel.setVerticalAlignment(JLabel.CENTER);

        nameLabel = new JLabel("Doctor Office name:");
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setVerticalAlignment(JLabel.CENTER);

        daysLabel = new JLabel("Available days:");
        daysLabel.setHorizontalAlignment(JLabel.CENTER);
        daysLabel.setVerticalAlignment(JLabel.CENTER);

        hoursLabel = new JLabel("Available hours:");
        hoursLabel.setHorizontalAlignment(JLabel.CENTER);
        hoursLabel.setVerticalAlignment(JLabel.CENTER);

        addressesLabel = new JLabel("Doctor's Office addresses:");
        addressesLabel.setHorizontalAlignment(JLabel.CENTER);
        addressesLabel.setVerticalAlignment(JLabel.CENTER);

        phoneTypeLabel = new JLabel("Doctor's Offices phone types: ");
        phoneTypeLabel.setHorizontalAlignment(JLabel.CENTER);
        phoneTypeLabel.setVerticalAlignment(JLabel.CENTER);
    }

    /**
     * Initializes each text field and set its action listener with the controller.
     */
    public void initTextField() {
        searchTextField = new JTextField(15);
        searchTextField.addActionListener(controller);
        searchTextField.setName("search");

        nameTextField = new JTextField(30);
        nameTextField.addActionListener(controller);
        nameTextField.setName("name");

        phoneTextField = new JTextField(20);
        phoneTextField.addActionListener(controller);
        phoneTextField.setName("phone");

        daysTextField = new JTextField(15);
        daysTextField.addActionListener(controller);
        daysTextField.setName("days");

        hoursTextField = new JTextField(15);
        hoursTextField.addActionListener(controller);
        hoursTextField.setName("hours");

        phoneTypeTextField = new JTextField(15);
        phoneTypeTextField.addActionListener(controller);
        phoneTypeTextField.setName("phoneTypes");
    }

    public void initTextArea() {
        addressesTextArea = new JTextArea(4, 15);
        addressesTextArea.setName("addresses");
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
        consultingRoomsTable = new JTable();
        consultingRoomsTable.setModel(controller.getDoctorOfficesTableModel());
    }

    /**
     * Initializes JScrollPane, set its JTable, size and border.
     */
    public void initScrollPane() {
        tableScrollPane = new JScrollPane(consultingRoomsTable);
        tableScrollPane.setPreferredSize(new Dimension(700, 182));
        tableScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Doctor's Offices Data", TitledBorder.CENTER, TitledBorder.TOP));
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
        form.add(nameLabel, constraints);

        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 1;
        constraints.gridy = 0;
        form.add(nameTextField, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 0;
        constraints.gridy = 1;
        form.add(daysLabel, constraints);

        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 1;
        constraints.gridy = 1;
        form.add(daysTextField, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 0;
        constraints.gridy = 2;
        form.add(hoursLabel, constraints);

        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 1;
        constraints.gridy = 2;
        form.add(hoursTextField, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 0;
        constraints.gridy = 3;
        form.add(phoneLabel, constraints);

        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 1;
        constraints.gridy = 3;
        form.add(phoneTextField, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 0;
        constraints.gridy = 5;
        form.add(phoneTypeLabel, constraints);

        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 1;
        constraints.gridy = 5;
        form.add(phoneTypeTextField, constraints);

        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 0;
        constraints.gridy = 6;
        form.add(addressesLabel, constraints);

        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 1;
        constraints.gridy = 6;
        form.add(addressesTextArea, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 7;
        form.add(addButton, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 7;
        form.add(updateButton, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 8;
        constraints.gridwidth = 2;
        form.add(searchButton, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 9;
        constraints.gridwidth = 2;
        form.add(deleteButton, constraints);
    }

    public DoctorOfficeController getController() {
        return controller;
    }

    public GridBagConstraints getConstraints() {
        return constraints;
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public JLabel getDaysLabel() {
        return daysLabel;
    }

    public JLabel getHoursLabel() {
        return hoursLabel;
    }

    public JLabel getPhoneLabel() {
        return phoneLabel;
    }

    public JLabel getSearchLabel() {
        return searchLabel;
    }

    public JLabel getAddressesLabel() {
        return addressesLabel;
    }

    public JLabel getPhoneTypeLabel() {
        return phoneTypeLabel;
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public JTextField getDaysTextField() {
        return daysTextField;
    }

    public JTextField getHoursTextField() {
        return hoursTextField;
    }

    public JTextField getPhoneTextField() {
        return phoneTextField;
    }

    public JTextField getSearchTextField() {
        return searchTextField;
    }

    public JTextField getPhoneTypeTextField() {
        return phoneTypeTextField;
    }

    public JTextArea getAddressesTextArea() {
        return addressesTextArea;
    }

    public JTable getConsultingRoomsTable() {
        return consultingRoomsTable;
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

    public void setNameLabel(JLabel nameLabel) {
        this.nameLabel = nameLabel;
    }

    public void setDaysLabel(JLabel daysLabel) {
        this.daysLabel = daysLabel;
    }

    public void setHoursLabel(JLabel hoursLabel) {
        this.hoursLabel = hoursLabel;
    }

    public void setPhoneLabel(JLabel phoneLabel) {
        this.phoneLabel = phoneLabel;
    }

    public void setSearchLabel(JLabel searchLabel) {
        this.searchLabel = searchLabel;
    }

    public void setAddressesLabel(JLabel addressesLabel) {
        this.addressesLabel = addressesLabel;
    }

    public void setPhoneTypeLabel(JLabel phoneTypeLabel) {
        this.phoneTypeLabel = phoneTypeLabel;
    }

    public void setNameTextField(JTextField nameTextField) {
        this.nameTextField = nameTextField;
    }

    public void setDaysTextField(JTextField daysTextField) {
        this.daysTextField = daysTextField;
    }

    public void setHoursTextField(JTextField hoursTextField) {
        this.hoursTextField = hoursTextField;
    }

    public void setPhoneTextField(JTextField phoneTextField) {
        this.phoneTextField = phoneTextField;
    }

    public void setSearchTextField(JTextField searchTextField) {
        this.searchTextField = searchTextField;
    }

    public void setPhoneTypeTextField(JTextField phoneTypeTextField) {
        this.phoneTypeTextField = phoneTypeTextField;
    }

    public void setAddressesTextArea(JTextArea addressesTextArea) {
        this.addressesTextArea = addressesTextArea;
    }

    public void setConsultingRoomsTable(JTable consultingRoomsTable) {
        this.consultingRoomsTable = consultingRoomsTable;
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
}
