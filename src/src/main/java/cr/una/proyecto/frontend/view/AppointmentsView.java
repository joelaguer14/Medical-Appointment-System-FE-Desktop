package cr.una.proyecto.frontend.view;

import com.github.lgooddatepicker.datetimepicker.DateTimePicker;
import cr.una.proyecto.frontend.controller.AppointmentsController;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * @author Felipe Guzman Rodriguez
 * @author Manfred Joel Aguero Campos
 */

public class AppointmentsView extends JPanel {
    private final AppointmentsController controller;
    private final GridBagConstraints constraints = new GridBagConstraints();
    DateTimePicker dateTimePicker;
    private JLabel patientLabel, doctorOfficeLabel, dateLabel, searchLabel, serviceTypeLabel, doctorLabel;
    private JTextField searchTextField;
    private JTable appointmentsTable;
    private JPanel form, searchPanel;
    private JScrollPane tableScrollPane;
    private JButton addButton, deleteButton, searchButton, updateButton;
    private JComboBox<String> patientComboBox, doctorOfficeComboBox, doctorComboBox, serviceTypesComboBox;

    /**
     * Constructor with a parameter, set the controller with the parameter, creates a
     * JFrame, set its size, Default close operation, icon image, visibility and location.
     *
     * @param controller Appointments controller and action listener.
     */
    public AppointmentsView(AppointmentsController controller) {
        super();

        setSize(700, 480);
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
        initDateTimePicker();
        initButton();
        initComboBox();
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
     * Initializes dateTimePicker.
     */
    public void initDateTimePicker() {
        dateTimePicker = new DateTimePicker();
    }

    /**
     * Initializes each label.
     */
    public void initLabel() {
        dateLabel = new JLabel("Appointment Date:");
        dateLabel.setHorizontalAlignment(JLabel.CENTER);
        dateLabel.setVerticalAlignment(JLabel.CENTER);


        patientLabel = new JLabel("Patient Name:");
        patientLabel.setHorizontalAlignment(JLabel.CENTER);
        patientLabel.setVerticalAlignment(JLabel.CENTER);

        doctorOfficeLabel = new JLabel("Doctor Office Name:");
        doctorOfficeLabel.setHorizontalAlignment(JLabel.CENTER);
        doctorOfficeLabel.setVerticalAlignment(JLabel.CENTER);

        searchLabel = new JLabel("Search:");
        searchLabel.setHorizontalAlignment(JLabel.CENTER);
        searchLabel.setVerticalAlignment(JLabel.CENTER);

        doctorLabel = new JLabel("Doctor:");
        doctorLabel.setHorizontalAlignment(JLabel.CENTER);
        doctorLabel.setVerticalAlignment(JLabel.CENTER);

        serviceTypeLabel = new JLabel("Type of service:");
        serviceTypeLabel.setHorizontalAlignment(JLabel.CENTER);
        serviceTypeLabel.setVerticalAlignment(JLabel.CENTER);
    }

    /**
     * Initializes each Text Field
     */
    public void initTextField() {
        searchTextField = new JTextField(25);
        searchTextField.addActionListener(controller);
        searchTextField.setName("search");

    }

    /**
     * Initializes each Button.
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
        appointmentsTable = new JTable();
        appointmentsTable.setModel(controller.getAppointmentsTableModel());
    }

    /**
     * Initializes JScrollPane, set its JTable, size and border.
     */
    public void initScrollPane() {
        tableScrollPane = new JScrollPane(appointmentsTable);
        tableScrollPane.setPreferredSize(new Dimension(700, 182));
        tableScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Appointment Data", TitledBorder.CENTER, TitledBorder.TOP));
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
        patientComboBox = new JComboBox<>();
        patientComboBox.setEditable(false);
        patientComboBox.setModel(controller.loadPatientsNames());
        DefaultListCellRenderer listRenderer;
        listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        patientComboBox.setRenderer(listRenderer);


        doctorOfficeComboBox = new JComboBox<>();
        doctorOfficeComboBox.setEditable(false);
        doctorOfficeComboBox.setRenderer(listRenderer);
        doctorOfficeComboBox.setModel(controller.loadDoctorOfficesNames());

        doctorComboBox = new JComboBox<>();
        doctorComboBox.setEditable(false);
        doctorComboBox.setRenderer(listRenderer);
        doctorComboBox.setModel(controller.loadDoctorsNames());

        serviceTypesComboBox = new JComboBox<>();
        serviceTypesComboBox.setEditable(false);
        serviceTypesComboBox.setRenderer(listRenderer);
        serviceTypesComboBox.setModel(controller.loadServiceTypesServices());

    }

    /**
     * Locates different objects in each panel.
     */
    @SuppressWarnings("DuplicatedCode")
    public void locatedObjects() {
        searchPanel.add(searchLabel);
        searchPanel.add(searchTextField);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 0;
        constraints.gridy = 0;
        form.add(patientLabel, constraints);

        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 1;
        constraints.gridy = 0;
        form.add(patientComboBox, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 0;
        constraints.gridy = 1;
        form.add(doctorOfficeLabel, constraints);

        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 1;
        constraints.gridy = 1;
        form.add(doctorOfficeComboBox, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 0;
        constraints.gridy = 2;
        form.add(doctorLabel, constraints);

        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 1;
        constraints.gridy = 2;
        form.add(doctorComboBox, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 0;
        constraints.gridy = 3;
        form.add(serviceTypeLabel, constraints);

        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 1;
        constraints.gridy = 3;
        form.add(serviceTypesComboBox, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 0;
        constraints.gridy = 4;
        form.add(dateLabel, constraints);

        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.gridx = 1;
        constraints.gridy = 4;
        form.add(dateTimePicker, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 5;
        form.add(addButton, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 5;
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
     * Returns  add button.
     *
     * @return the add button
     */
    public JButton getAddButton() {
        return addButton;
    }

    /**
     * Returns delete button.
     *
     * @return the delete button
     */
    public JButton getDeleteButton() {
        return deleteButton;
    }

    /**
     * Returns search Button.
     *
     * @return the search button
     */
    public JButton getSearchButton() {
        return searchButton;
    }

    /**
     * Returns search's text field.
     *
     * @return the search's text field
     */
    public JTextField getSearchTextField() {
        return searchTextField;
    }

    /**
     * Returns dateTimePicker.
     *
     * @return the date & time picker
     */
    public DateTimePicker getDateTimePicker() {
        return dateTimePicker;
    }

    /**
     * @return
     */
    public AppointmentsController getController() {
        return controller;
    }

    /**
     * @return
     */
    public GridBagConstraints getConstraints() {
        return constraints;
    }

    /**
     * @param dateTimePicker
     */
    public void setDateTimePicker(DateTimePicker dateTimePicker) {
        this.dateTimePicker = dateTimePicker;
    }

    /**
     * @return
     */
    public JLabel getPatientLabel() {
        return patientLabel;
    }

    /**
     * @param patientLabel
     */
    public void setPatientLabel(JLabel patientLabel) {
        this.patientLabel = patientLabel;
    }

    /**
     * @return
     */
    public JLabel getDoctorOfficeLabel() {
        return doctorOfficeLabel;
    }

    /**
     * @param doctorOfficeLabel
     */
    public void setDoctorOfficeLabel(JLabel doctorOfficeLabel) {
        this.doctorOfficeLabel = doctorOfficeLabel;
    }

    /**
     * @return
     */
    public JLabel getDateLabel() {
        return dateLabel;
    }

    /**
     * @param dateLabel
     */
    public void setDateLabel(JLabel dateLabel) {
        this.dateLabel = dateLabel;
    }

    /**
     * @return
     */
    public JLabel getSearchLabel() {
        return searchLabel;
    }

    /**
     * @param searchLabel
     */
    public void setSearchLabel(JLabel searchLabel) {
        this.searchLabel = searchLabel;
    }

    /**
     * @param searchTextField
     */
    public void setSearchTextField(JTextField searchTextField) {
        this.searchTextField = searchTextField;
    }

    /**
     * @return
     */
    public JTable getAppointmentsTable() {
        return appointmentsTable;
    }

    /**
     * @param appointmentsTable
     */
    public void setAppointmentsTable(JTable appointmentsTable) {
        this.appointmentsTable = appointmentsTable;
    }

    /**
     * @return
     */
    public JPanel getForm() {
        return form;
    }

    /**
     * @param form
     */
    public void setForm(JPanel form) {
        this.form = form;
    }

    /**
     * @return
     */
    public JPanel getSearchPanel() {
        return searchPanel;
    }

    /**
     * @param searchPanel
     */
    public void setSearchPanel(JPanel searchPanel) {
        this.searchPanel = searchPanel;
    }

    /**
     * @return
     */
    public JScrollPane getTableScrollPane() {
        return tableScrollPane;
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
     * @return
     */
    public JButton getUpdateButton() {
        return updateButton;
    }

    /**
     * @param updateButton
     */
    public void setUpdateButton(JButton updateButton) {
        this.updateButton = updateButton;
    }


    /**
     * @return
     */
    public JComboBox<String> getPatientComboBox() {
        return patientComboBox;
    }

    /**
     * @param patientComboBox
     */
    public void setPatientComboBox(JComboBox<String> patientComboBox) {
        this.patientComboBox = patientComboBox;
    }

    /**
     * @return
     */
    public JComboBox<String> getDoctorOfficeComboBox() {
        return doctorOfficeComboBox;
    }

    /**
     * @param doctorOfficeComboBox
     */
    public void setDoctorOfficeComboBox(JComboBox<String> doctorOfficeComboBox) {
        this.doctorOfficeComboBox = doctorOfficeComboBox;
    }

    /**
     * @return
     */
    public JComboBox<String> getDoctorComboBox() {
        return doctorComboBox;
    }

    /**
     * @param doctorComboBox
     */
    public void setDoctorComboBox(JComboBox<String> doctorComboBox) {
        this.doctorComboBox = doctorComboBox;
    }

    /**
     * @return
     */
    public JComboBox<String> getServiceTypesComboBox() {
        return serviceTypesComboBox;
    }

    /**
     * @param serviceTypesComboBox
     */
    public void setServiceTypesComboBox(JComboBox<String> serviceTypesComboBox) {
        this.serviceTypesComboBox = serviceTypesComboBox;
    }
}
