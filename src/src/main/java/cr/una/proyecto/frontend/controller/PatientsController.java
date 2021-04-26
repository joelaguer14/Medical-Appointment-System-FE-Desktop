package cr.una.proyecto.frontend.controller;

import cr.una.proyecto.Constants;
import cr.una.proyecto.frontend.model.Address;
import cr.una.proyecto.frontend.model.Disease;
import cr.una.proyecto.frontend.model.Patient;
import cr.una.proyecto.frontend.model.Phone;
import cr.una.proyecto.frontend.service.PatientService;
import cr.una.proyecto.frontend.view.PatientsView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Felipe Guzman Rodriguez
 * @author Manfred Joel Aguero Campos
 *
 *
 */
public class PatientsController implements ActionListener {
    private final static Logger LOGGER = Logger.getLogger("PatientsController");
    private static PatientsController patientsController;
    private final PatientService patientService;
    private final DefaultTableModel patientsTableModel;
    Object[][] patientsMatrix;
    private final PatientsView patientsView;
    private Patient patient;
    private int counter = 0;

    /**
     * Constructor which receives a model by parameter to make sure that all the
     * controllers use the same list of consulting rooms, patients, and appointments.
     */
    private PatientsController() {
        this.patientService = new PatientService();

        this.patientsTableModel = new DefaultTableModel();
        patientsMatrix = patientService.patientsToMatrix();
        patientsTableModel.setDataVector(patientsMatrix, Constants.PATIENTS_TABLE_HEADER);

        this.patientsView = new PatientsView(this);
    }

    /**
     * Static method to create instance of Singleton class (PatientsController class).
     *
     * @return the instance of the controller
     */
    public static PatientsController getInstance() {
        if (null == patientsController) patientsController = new PatientsController();
        return patientsController;
    }

    /**
     * Handles the events from the views.
     *
     * @param actionEvent event from view.
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        Object source = actionEvent.getSource();
        if (source == patientsView.getSearchButton()) {
            String searchTerm = patientsView.getSearchTextField().getText();
            updateTableSearchTerms(searchTerm);
            cleanText();
            LOGGER.log(Level.INFO, "Patient Maintenance Search Button");

        } else if (source == patientsView.getAddButton()) {

            Patient patient = new Patient();

            String[] addressTypes = patientsView.getAddressTypeTextField().getText().split(",");
            String[] addresses = patientsView.getAddressTextArea().getText().split(",");
            List<Address> patientAddresses = new ArrayList<>();
            for (int i = 0; i < addresses.length; i++) {
                patientAddresses.add(new Address(addresses[i], addressTypes[i]));
            }

            String[] phoneTypes = patientsView.getPhoneTypeTextField().getText().split(",");
            String[] phones = patientsView.getPhoneTextField().getText().split(",");
            List<Phone> phonesList = new ArrayList<>();
            for (int i = 0; i < phones.length; i++) {
                phonesList.add(new Phone(phones[i], phoneTypes[i]));
            }

            String[] diseases = patientsView.getDiseasesTextArea().getText().split(",");
            List<Disease> diseaseList = new ArrayList<>();
            for (String disease : diseases) {
                diseaseList.add(new Disease(disease));
            }
            try {
                patient.setFullName(patientsView.getFullNameTextField().getText());
                patient.setBirthDate(patientsView.getBirthDateChooser().getDate());
                patient.setObservations(patientsView.getObservationsTextArea().getText());
                patient.setAddresses(patientAddresses);
                patient.setDiseases(diseaseList);
                patient.setPhones(phonesList);

                patientService.savePatient(patient);
                LOGGER.log(Level.INFO, "Patient added: " + patient.toString());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Exception: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                patientsTableModel.setDataVector(patientsMatrix, Constants.PATIENTS_TABLE_HEADER);
                LOGGER.log(Level.SEVERE, "Exception: " + ex.getMessage());
            }


            patientsMatrix = patientService.patientsToMatrix();
            patientsTableModel.setDataVector(patientsMatrix, Constants.PATIENTS_TABLE_HEADER);
            cleanText();
            LOGGER.log(Level.INFO, "Patient Maintenance Add Button");

        } else if (source == patientsView.getDeleteButton()) {
            String nameToDelete = patientsView.getSearchTextField().getText();
            try {
                patientService.deletePatient(nameToDelete);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Could not delete the patient or it does not exist",
                        "Error", JOptionPane.ERROR_MESSAGE);
                patientsTableModel.setDataVector(patientsMatrix, Constants.PATIENTS_TABLE_HEADER);
                LOGGER.log(Level.SEVERE, "Could not delete de Patient. Exception: " + ex.getMessage());
            }
            patientsMatrix = patientService.patientsToMatrix();
            patientsTableModel.setDataVector(patientsMatrix, Constants.PATIENTS_TABLE_HEADER);
            cleanText();
            LOGGER.log(Level.INFO, "Patient Maintenance Delete Button");

        } else if (patientsView.getUpdateButton() == source) {
            if (counter == 0) {
                try {
                    patient = patientService.findPatientByName(patientsView.getSearchTextField().getText());
                    patientsView.getFullNameTextField().setText(patient.getFullName());
                    StringBuilder phones = new StringBuilder();
                    StringBuilder phoneTypes = new StringBuilder();
                    for (int i = 0; i < patient.getPhones().size(); i++) {
                        if (i < patient.getPhones().size() - 1) {
                            phones.append(patient.getPhones().get(i).getPhone());
                            phones.append(",");
                            phoneTypes.append(patient.getPhones().get(i).getPhoneType());
                            phoneTypes.append(",");
                        } else {
                            phones.append(patient.getPhones().get(i).getPhone());
                            phoneTypes.append(patient.getPhones().get(i).getPhoneType());
                        }

                    }
                    patientsView.getPhoneTextField().setText(phones.toString());
                    patientsView.getPhoneTypeTextField().setText(phoneTypes.toString());
                    StringBuilder addresses = new StringBuilder();
                    StringBuilder addressTypes = new StringBuilder();
                    for (int i = 0; i < patient.getAddresses().size(); i++) {
                        if (i < patient.getAddresses().size() - 1) {
                            addresses.append(patient.getAddresses().get(i).getAddress());
                            addresses.append(",");
                            addressTypes.append(patient.getAddresses().get(i).getType());
                            addressTypes.append(",");
                        } else {
                            addresses.append(patient.getAddresses().get(i).getAddress());
                            addressTypes.append(patient.getAddresses().get(i).getType());
                        }
                    }
                    patientsView.getAddressTextArea().setText(addresses.toString());
                    patientsView.getAddressTypeTextField().setText(addressTypes.toString());
                    patientsView.getBirthDateChooser().setDate(patient.getBirthDate());
                    StringBuilder diseases = new StringBuilder();
                    for (int i = 0; i < patient.getDiseases().size(); i++) {
                        if (i < patient.getDiseases().size() - 1) {
                            diseases.append(patient.getDiseases().get(i).getDisease());
                            diseases.append(",");
                        } else {
                            diseases.append(patient.getDiseases().get(i).getDisease());
                        }
                    }
                    patientsView.getDiseasesTextArea().setText(diseases.toString());
                    patientsView.getObservationsTextArea().setText(patient.getObservations());
                    counter++;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Exception: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                    patientsTableModel.setDataVector(patientsMatrix, Constants.PATIENTS_TABLE_HEADER);
                    LOGGER.log(Level.SEVERE, "Exception: " + ex.getMessage());
                }
            } else {
                String[] addressTypes = patientsView.getAddressTypeTextField().getText().split(",");
                String[] addresses = patientsView.getAddressTextArea().getText().split(",");
                List<Address> patientAddresses = new ArrayList<>();
                for (int i = 0; i < addresses.length; i++) {
                    patientAddresses.add(new Address(addresses[i], addressTypes[i]));
                }

                String[] phoneTypes = patientsView.getPhoneTypeTextField().getText().split(",");
                String[] phones = patientsView.getPhoneTextField().getText().split(",");
                List<Phone> phonesList = new ArrayList<>();
                for (int i = 0; i < phones.length; i++) {
                    phonesList.add(new Phone(phones[i], phoneTypes[i]));
                }

                String[] diseases = patientsView.getDiseasesTextArea().getText().split(",");
                List<Disease> diseaseList = new ArrayList<>();
                for (String disease : diseases) {
                    diseaseList.add(new Disease(disease));
                }
                try {
                    patient.setFullName(patientsView.getFullNameTextField().getText());
                    patient.setBirthDate(patientsView.getBirthDateChooser().getDate());
                    patient.setObservations(patientsView.getObservationsTextArea().getText());
                    patient.setAddresses(patientAddresses);
                    patient.setDiseases(diseaseList);
                    patient.setPhones(phonesList);

                    patientService.updatePatient(patient, patient.getId());

                    LOGGER.log(Level.INFO, "Patient updated: " + patient.toString());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Exception: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                    patientsTableModel.setDataVector(patientsMatrix, Constants.PATIENTS_TABLE_HEADER);
                    LOGGER.log(Level.SEVERE, "Exception: " + ex.getMessage());
                }
                patientsMatrix = patientService.patientsToMatrix();
                patientsTableModel.setDataVector(patientsMatrix, Constants.PATIENTS_TABLE_HEADER);
                counter = 0;
                cleanText();
            }
        }

    }

    /**
     * Erase the text of the JTexFields.
     */
    public void cleanText() {
        patientsView.getSearchTextField().setText("");
        patientsView.getFullNameTextField().setText("");
        patientsView.getAddressTextArea().setText("");
        patientsView.getBirthDateTextField().setText("");
        patientsView.getDiseasesTextArea().setText("");
        patientsView.getObservationsTextArea().setText("");
        patientsView.getPhoneTextField().setText("");
        patientsView.getAddressTypeTextField().setText("");
        patientsView.getPhoneTypeTextField().setText("");
        LOGGER.log(Level.INFO, "Clean Text");
    }

    /**
     * @param searchTerm the string to search
     */
    private void updateTableSearchTerms(String searchTerm) {
        patientsMatrix = patientService.patientsToMatrix();

        if (searchTerm != null && !"".equals(searchTerm) && patientService.patientsToMatrix() != null && patientsMatrix.length > 1) {
            Object[][] newData = new Object[patientsMatrix.length][];
            int idx = 0;

            for (Object[] obj : patientsMatrix) {
                String fullText = obj[0].toString() + obj[1].toString()
                        + obj[2].toString() + obj[3].toString() + obj[4].toString() + obj[5].toString();

                if (fullText.contains(searchTerm.trim())) {
                    newData[idx++] = obj;
                }
            }
            patientsTableModel.setDataVector(newData, Constants.PATIENTS_TABLE_HEADER);

        } else {
            JOptionPane.showMessageDialog(null, "Search term is empty",
                    "Error", JOptionPane.ERROR_MESSAGE);
            patientsTableModel.setDataVector(patientsMatrix, Constants.PATIENTS_TABLE_HEADER);
            LOGGER.log(Level.INFO, "Search term is empty");
        }
    }

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public static PatientsController getPatientsController() {
        return patientsController;
    }

    public PatientService getPatientService() {
        return patientService;
    }

    public DefaultTableModel getPatientsTableModel() {
        return patientsTableModel;
    }

    public Object[][] getPatientsMatrix() {
        return patientsMatrix;
    }

    public PatientsView getPatientsView() {
        return patientsView;
    }

    public Patient getPatient() {
        return patient;
    }

    public int getCounter() {
        return counter;
    }

    public static void setPatientsController(PatientsController patientsController) {
        PatientsController.patientsController = patientsController;
    }

    public void setPatientsMatrix(Object[][] patientsMatrix) {
        this.patientsMatrix = patientsMatrix;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
