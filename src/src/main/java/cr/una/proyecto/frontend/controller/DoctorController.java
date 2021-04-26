package cr.una.proyecto.frontend.controller;

import cr.una.proyecto.Constants;
import cr.una.proyecto.frontend.model.Doctor;
import cr.una.proyecto.frontend.model.DoctorType;
import cr.una.proyecto.frontend.service.DoctorService;
import cr.una.proyecto.frontend.service.DoctorTypeService;
import cr.una.proyecto.frontend.view.DoctorView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Felipe Guzman Rodriguez
 * @author Manfred Joel Aguero Campos
 */
public class DoctorController implements ActionListener {
    private Object[][] doctorsMatrix;
    private final DoctorService doctorService;
    private final DoctorTypeService doctorTypeService;
    private final DefaultTableModel doctorTableModel;
    private final DoctorView doctorView;
    private static DoctorController doctorController;
    private int counter = 0;
    private Doctor doctor;
    private final static Logger LOGGER = Logger.getLogger("DoctorController");

    /**
     * Default Constructor
     */
    private DoctorController() {
        this.doctorService = new DoctorService();
        this.doctorTypeService = new DoctorTypeService();
        this.doctorTableModel = new DefaultTableModel();
        this.doctorsMatrix = doctorService.doctorToMatrix();
        doctorTableModel.setDataVector(doctorsMatrix, Constants.DOCTOR_TABLE_HEADER);
        this.doctorView = new DoctorView(this);
    }

    /**
     * @return
     */
    public DefaultComboBoxModel<String> loadAllDoctorTypesType() {
        List<DoctorType> doctorTypeList = doctorTypeService.loadAllDoctorTypes();
        DefaultComboBoxModel<String> items = new DefaultComboBoxModel<>();
        items.addElement("");
        for (DoctorType doctorType : doctorTypeList) {
            items.addElement(doctorType.getType());
        }
        return items;
    }

    /**
     * Handles the events from the view.
     *
     * @param actionEvent the event from the view
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (source == doctorView.getSearchButton()) {
            String searchTerm = doctorView.getSearchTextField().getText();
            updateTableSearchTerms(searchTerm);
            cleanText();
            LOGGER.log(Level.INFO, "Consulting Room Search Button");

        } else if (source == doctorView.getAddButton()) {
            Doctor doctor = new Doctor();

            try {
                doctor.setName(doctorView.getNameTextField().getText());
                doctor.setMedicalCode((doctorTypeService.findDoctorTypeByName((String) doctorView.getComboBox().getSelectedItem()).getMedicalCode()));
                doctor.setPremium(doctorView.getPremiumCheckBox().isSelected());
                doctorService.saveDoctor(doctor);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                LOGGER.log(Level.SEVERE, ex.getMessage());
            }
            doctorsMatrix = doctorService.doctorToMatrix();
            doctorTableModel.setDataVector(doctorsMatrix, Constants.DOCTOR_TABLE_HEADER);
            cleanText();
            LOGGER.log(Level.INFO, "Consulting Room Maintenance Add Button");

        } else if (source == doctorView.getDeleteButton()) {
            String nameToDelete = doctorView.getSearchTextField().getText();
            try {
                doctorService.deleteDoctor(nameToDelete);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Could not delete or it does not exist" +
                        ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                LOGGER.log(Level.SEVERE, ex.getMessage());
            }
            doctorsMatrix = doctorService.doctorToMatrix();
            doctorTableModel.setDataVector(doctorsMatrix, Constants.DOCTOR_TABLE_HEADER);
            cleanText();
            LOGGER.log(Level.INFO, "Consulting Room Maintenance Delete Button");
        } else if (doctorView.getUpdateButton() == source) {
            if (counter == 0) {
                try {
                    doctor = doctorService.findDoctorByName(doctorView.getSearchTextField().getText());
                    doctorView.getNameTextField().setText(doctor.getName());
                    doctorView.getNameTextField().setText(doctor.getName());
                    for (DoctorType doctorType : doctorTypeService.loadAllDoctorTypes()) {
                        if (doctorType.getMedicalCode().equals(doctor.getMedicalCode())) {
                            doctorView.getComboBox().setSelectedItem(doctorType.getType());
                            break;
                        }
                    }

                    doctorView.getPremiumCheckBox().setSelected(doctor.getPremium());
                    counter++;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                    LOGGER.log(Level.SEVERE, ex.getMessage());
                }
            } else {

                try {
                    doctor.setName(doctorView.getNameTextField().getText());
                    doctor.setMedicalCode((doctorTypeService.findDoctorTypeByName((String) doctorView.getComboBox().getSelectedItem()).getMedicalCode()));
                    doctor.setPremium(doctorView.getPremiumCheckBox().isSelected());
                    doctorService.updateDoctor(doctor, doctor.getId());

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                    LOGGER.log(Level.SEVERE, ex.getMessage());
                }

                doctorsMatrix = doctorService.doctorToMatrix();
                doctorTableModel.setDataVector(doctorsMatrix, Constants.DOCTOR_TABLE_HEADER);
                cleanText();
                LOGGER.log(Level.INFO, "Doctor Office Maintenance Update Button");
                counter = 0;
            }
        }
    }

    /**
     * Erase the text of the JTexFields.
     */
    public void cleanText() {
        doctorView.getSearchTextField().setText("");
        doctorView.getNameTextField().setText("");
        doctorView.getComboBox().setSelectedItem("");
        LOGGER.log(Level.INFO, "Clean text");
    }


    /**
     * Search a list of consulting rooms based on a string.
     *
     * @param searchTerm the string to search.
     */
    private void updateTableSearchTerms(String searchTerm) {

        doctorsMatrix = doctorService.doctorToMatrix();

        if ((searchTerm != null) && !"".equals(searchTerm) && (doctorService.doctorToMatrix() != null) && (doctorsMatrix.length > 1)) {
            Object[][] newData = new Object[doctorsMatrix.length][];
            int idx = 0;

            for (Object[] obj : doctorsMatrix) {
                String fullText = obj[0].toString() + obj[1].toString()
                        + obj[2].toString();

                if (fullText.contains(searchTerm.trim())) {
                    newData[idx++] = obj;
                }
            }
            doctorTableModel.setDataVector(newData, Constants.DOCTOR_TABLE_HEADER);

        } else {
            JOptionPane.showMessageDialog(null, "Search term is empty",
                    "Error", JOptionPane.ERROR_MESSAGE);
            doctorTableModel.setDataVector(doctorsMatrix, Constants.DOCTOR_TABLE_HEADER);
            LOGGER.log(Level.INFO, "Search term is empty");
        }
    }

    /**
     * Static method to create instance of Singleton class (DoctorOfficeController class).
     *
     * @return the instance of Consulting Room Controller.
     */
    public static DoctorController getInstance() {
        if (null == doctorController) doctorController = new DoctorController();
        return doctorController;
    }

    public Object[][] getDoctorsMatrix() {
        return doctorsMatrix;
    }

    public DoctorService getDoctorService() {
        return doctorService;
    }

    public DoctorTypeService getDoctorTypeService() {
        return doctorTypeService;
    }

    public DefaultTableModel getDoctorTableModel() {
        return doctorTableModel;
    }

    public DoctorView getDoctorView() {
        return doctorView;
    }

    public static DoctorController getDoctorController() {
        return doctorController;
    }

    public int getCounter() {
        return counter;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctorsMatrix(Object[][] doctorsMatrix) {
        this.doctorsMatrix = doctorsMatrix;
    }

    public static void setDoctorController(DoctorController doctorController) {
        DoctorController.doctorController = doctorController;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void updateComboBox() {
        doctorView.getComboBox().setModel(loadAllDoctorTypesType());
    }
}

