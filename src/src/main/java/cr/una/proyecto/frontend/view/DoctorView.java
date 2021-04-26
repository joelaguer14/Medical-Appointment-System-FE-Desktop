package cr.una.proyecto.frontend.view;

import cr.una.proyecto.frontend.controller.DoctorController;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class DoctorView extends JPanel {
    private DoctorController controller;
    private final GridBagConstraints constraints = new GridBagConstraints();
    private JLabel nameLabel, searchLabel, doctorTypeLabel, premiumLabel;
    private JTextField nameTextField, searchTextField, doctorTypeTextField;
    private JTable doctorTable;
    private JPanel form, searchPanel;
    private JScrollPane tableScrollPane;
    private JButton addButton, deleteButton, searchButton, updateButton;
    private JComboBox<String> comboBox;
    private JCheckBox premiumCheckBox;

    /**
     * Constructor with a parameter, set the controller with the parameter, creates a
     * JFrame, set its size, Default close operation, icon image, visibility and location.
     *
     * @param controller consulting room controller and action listener
     */
    public DoctorView(DoctorController controller) {
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
        initButton();
        initTable();
        initScrollPane();
        initComboBox();
        initCheckBox();
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

        nameLabel = new JLabel("Doctor name:");
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setVerticalAlignment(JLabel.CENTER);

        doctorTypeLabel = new JLabel("Doctor type:");
        doctorTypeLabel.setHorizontalAlignment(JLabel.CENTER);
        doctorTypeLabel.setVerticalAlignment(JLabel.CENTER);

        premiumLabel = new JLabel("Premium:");
        premiumLabel.setHorizontalAlignment(JLabel.CENTER);
        premiumLabel.setVerticalAlignment(JLabel.CENTER);
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

        doctorTypeTextField = new JTextField(30);
        doctorTypeTextField.addActionListener(controller);
        doctorTypeTextField.setName("doctorType");
        doctorTypeTextField.setEnabled(false);
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
        doctorTable = new JTable();
        doctorTable.setModel(controller.getDoctorTableModel());
    }

    /**
     * Initializes JScrollPane, set its JTable, size and border.
     */
    public void initScrollPane() {
        tableScrollPane = new JScrollPane(doctorTable);
        tableScrollPane.setPreferredSize(new Dimension(700, 182));
        tableScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Doctor's Data", TitledBorder.CENTER, TitledBorder.TOP));
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
     *
     */
    public void initComboBox() {
        comboBox = new JComboBox<>();
        comboBox.setEditable(false);
        comboBox.setModel(controller.loadAllDoctorTypesType());

        DefaultListCellRenderer listRenderer;
        listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        comboBox.setRenderer(listRenderer);
    }

    public void initCheckBox() {
        premiumCheckBox = new JCheckBox();
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
        form.add(doctorTypeLabel, constraints);

        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 1;
        constraints.gridy = 1;
        form.add(comboBox, constraints);

        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 0;
        constraints.gridy = 2;
        form.add(premiumLabel, constraints);

        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 1;
        constraints.gridy = 2;
        form.add(premiumCheckBox, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        form.add(addButton, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        form.add(updateButton, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 2;
        form.add(searchButton, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.gridwidth = 2;
        form.add(deleteButton, constraints);
    }

    /**
     * @return
     */
    public DoctorController getController() {
        return controller;
    }

    /**
     * @return
     */
    public GridBagConstraints getConstraints() {
        return constraints;
    }

    /**
     * @return
     */
    public JLabel getNameLabel() {
        return nameLabel;
    }

    /**
     * @return
     */
    public JLabel getSearchLabel() {
        return searchLabel;
    }

    /**
     * @return
     */
    public JLabel getDoctorTypeLabel() {
        return doctorTypeLabel;
    }

    /**
     * @return
     */
    public JTextField getNameTextField() {
        return nameTextField;
    }

    /**
     * @return
     */
    public JTextField getSearchTextField() {
        return searchTextField;
    }

    /**
     * @return
     */
    public JTextField getDoctorTypeTextField() {
        return doctorTypeTextField;
    }

    /**
     * @return
     */
    public JTable getDoctorTable() {
        return doctorTable;
    }

    /**
     * @return
     */
    public JPanel getForm() {
        return form;
    }

    /**
     * @return
     */
    public JPanel getSearchPanel() {
        return searchPanel;
    }

    /**
     * @return
     */
    public JScrollPane getTableScrollPane() {
        return tableScrollPane;
    }

    /**
     * @return
     */
    public JButton getAddButton() {
        return addButton;
    }

    /**
     * @return
     */
    public JButton getDeleteButton() {
        return deleteButton;
    }

    /**
     * @return
     */
    public JButton getSearchButton() {
        return searchButton;
    }

    /**
     * @return
     */
    public JButton getUpdateButton() {
        return updateButton;
    }

    /**
     * @return
     */
    public JComboBox<String> getComboBox() {
        return comboBox;
    }

    /**
     * @return
     */
    public JCheckBox getPremiumCheckBox() {
        return premiumCheckBox;
    }

    /**
     * @param controller
     */
    public void setController(DoctorController controller) {
        this.controller = controller;
    }

    /**
     * @param nameLabel
     */
    public void setNameLabel(JLabel nameLabel) {
        this.nameLabel = nameLabel;
    }

    /**
     * @param searchLabel
     */
    public void setSearchLabel(JLabel searchLabel) {
        this.searchLabel = searchLabel;
    }

    /**
     * @param doctorTypeLabel
     */
    public void setDoctorTypeLabel(JLabel doctorTypeLabel) {
        this.doctorTypeLabel = doctorTypeLabel;
    }

    /**
     * @param nameTextField
     */
    public void setNameTextField(JTextField nameTextField) {
        this.nameTextField = nameTextField;
    }

    /**
     * @param searchTextField
     */
    public void setSearchTextField(JTextField searchTextField) {
        this.searchTextField = searchTextField;
    }

    /**
     * @param doctorTypeTextField
     */
    public void setDoctorTypeTextField(JTextField doctorTypeTextField) {
        this.doctorTypeTextField = doctorTypeTextField;
    }

    /**
     * @param doctorTable
     */
    public void setDoctorTable(JTable doctorTable) {
        this.doctorTable = doctorTable;
    }

    /**
     * @param form
     */
    public void setForm(JPanel form) {
        this.form = form;
    }

    /**
     * @param searchPanel
     */
    public void setSearchPanel(JPanel searchPanel) {
        this.searchPanel = searchPanel;
    }

    /**
     * @param tableScrollPane
     */
    public void setTableScrollPane(JScrollPane tableScrollPane) {
        this.tableScrollPane = tableScrollPane;
    }

    /**
     * @param addButton
     */
    public void setAddButton(JButton addButton) {
        this.addButton = addButton;
    }

    /**
     * @param deleteButton
     */
    public void setDeleteButton(JButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    /**
     * @param searchButton
     */
    public void setSearchButton(JButton searchButton) {
        this.searchButton = searchButton;
    }

    /**
     * @param updateButton
     */
    public void setUpdateButton(JButton updateButton) {
        this.updateButton = updateButton;
    }

    /**
     * @param comboBox
     */
    public void setComboBox(JComboBox<String> comboBox) {
        this.comboBox = comboBox;
    }

    public void setPremiumCheckBox(JCheckBox premiumCheckBox) {
        this.premiumCheckBox = premiumCheckBox;
    }
}
