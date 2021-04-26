package cr.una.proyecto.frontend.controller;

import cr.una.proyecto.Constants;
import cr.una.proyecto.frontend.model.*;
import cr.una.proyecto.frontend.service.*;
import cr.una.proyecto.frontend.view.AppointmentsView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//import cr.una.proyecto.frontend.view.ConfirmPatientsView;

/**
 * @author Felipe Guzman Rodriguez
 * @author Manfred Joel Aguero Campos
 */

public class AppointmentsController implements ActionListener {
    final AppointmentService appointmentService;
    final DoctorOfficeService doctorOfficeService;
    final PatientService patientService;
    final DoctorService doctorService;
    final ServiceTypeService serviceTypeService;
    private final DefaultTableModel appointmentsTableModel;
    Object[][] appointmentsMatrix;
    private final AppointmentsView appointmentsView;
    //private final ConfirmPatientsView confirmPatientsView;
    private static AppointmentsController appointmentsController;
    private int counter = 0;
    private Appointment appointment;
    private final static Logger LOGGER = Logger.getLogger("AppointmentsController");

    /**
     * Default constructor
     */
    private AppointmentsController() {
        this.appointmentService = new AppointmentService();
        this.patientService = new PatientService();
        this.doctorOfficeService = new DoctorOfficeService();
        this.doctorService = new DoctorService();
        this.serviceTypeService = new ServiceTypeService();

        this.appointmentsTableModel = new DefaultTableModel();
        appointmentsMatrix = appointmentService.appointmentsToMatrix();
        appointmentsTableModel.setDataVector(appointmentsMatrix, Constants.APPOINTMENTS_TABLE_HEADER);

        this.appointmentsView = new AppointmentsView(this);
        //this.confirmPatientsView = new ConfirmPatientsView(this);
    }

    /**
     * @return
     */
    public DefaultComboBoxModel<String> loadPatientsNames() {
        List<Patient> patientList = patientService.loadAllPatients();
        DefaultComboBoxModel<String> items = new DefaultComboBoxModel();
        items.addElement("");
        if (0 < patientList.size()) {
            for (Patient patient : patientList) {
                items.addElement(patient.getFullName());
            }
        }
        return items;
    }

    /**
     * @return
     */
    public DefaultComboBoxModel<String> loadDoctorsNames() {
        List<Doctor> doctorList = doctorService.loadAllDoctors();
        DefaultComboBoxModel<String> items = new DefaultComboBoxModel();
        items.addElement("");
        if (0 < doctorList.size()) {
            for (Doctor doctor : doctorList) {
                items.addElement(doctor.getName());
            }
        }
        return items;
    }

    /**
     * @return
     */
    public DefaultComboBoxModel<String> loadDoctorOfficesNames() {
        List<DoctorOffice> doctorOfficeList = doctorOfficeService.loadAllDoctorOffices();
        DefaultComboBoxModel<String> items = new DefaultComboBoxModel<>();
        items.addElement("");
        if (0 < doctorOfficeList.size()) {
            for (DoctorOffice doctorOffice : doctorOfficeList) {
                items.addElement(doctorOffice.getName());
            }
        }
        return items;
    }

    /**
     * @return
     */
    public DefaultComboBoxModel<String> loadServiceTypesServices() {
        List<ServiceType> serviceTypeList = serviceTypeService.loadAllServiceTypes();
        DefaultComboBoxModel<String> items = new DefaultComboBoxModel();
        items.addElement("");
        if (0 < serviceTypeList.size()) {
            for (ServiceType serviceType : serviceTypeList) {
                items.addElement(serviceType.getService());
            }
        }
        return items;
    }

    /**
     * Handles the events from the views.
     *
     * @param actionEvent event from the JFrames of the views.
     */

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (source == appointmentsView.getAddButton()) {
            try {
                appointmentService.saveAppointment(new Appointment(patientService.findPatientByName((String) appointmentsView.getPatientComboBox().getSelectedItem()),
                        doctorOfficeService.findDoctorOfficeByName((String) appointmentsView.getDoctorOfficeComboBox().getSelectedItem()),
                        Date.from(appointmentsView.getDateTimePicker().getDateTime().atZone(ZoneId.systemDefault())
                                .toInstant()),
                        doctorService.findDoctorByName((String) appointmentsView.getDoctorComboBox().getSelectedItem()), (String) appointmentsView.getServiceTypesComboBox().getSelectedItem(), 0));
                appointmentsMatrix = appointmentService.appointmentsToMatrix();
                appointmentsTableModel.setDataVector(appointmentsMatrix, Constants.APPOINTMENTS_TABLE_HEADER);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                LOGGER.log(Level.SEVERE, ex.getMessage());
            }

            cleanText();
            LOGGER.log(Level.INFO, "Appointment Maintenance Add Button");

        } else if (source == appointmentsView.getSearchButton()) {
            String searchTerm = appointmentsView.getSearchTextField().getText();
            updateTableSearchTerms(searchTerm);
            cleanText();
            LOGGER.log(Level.INFO, "Appointment Maintenance Search Button");

        } else if (source == appointmentsView.getDeleteButton()) {
            try {
                String nameToDelete = appointmentsView.getSearchTextField().getText();
                appointmentService.deleteAppointment(nameToDelete);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                LOGGER.log(Level.SEVERE, ex.getMessage());
            }
            appointmentsMatrix = appointmentService.appointmentsToMatrix();
            appointmentsTableModel.setDataVector(appointmentsMatrix, Constants.APPOINTMENTS_TABLE_HEADER);
            cleanText();
            LOGGER.log(Level.INFO, "Appointment Maintenance Delete Button");
        } else if (appointmentsView.getUpdateButton() == source) {
            if (counter == 0) {
                try {
                    appointment = appointmentService.findAppointmentByPatientName(appointmentsView.getSearchTextField().getText());
                    appointmentsView.getPatientComboBox().setSelectedItem(appointment.getPatient().getFullName());
                    appointmentsView.getDoctorOfficeComboBox().setSelectedItem(appointment.getDoctorOffice().getName());
                    appointmentsView.getDoctorComboBox().setSelectedItem(appointment.getDoctor().getName());
                    appointmentsView.getServiceTypesComboBox().setSelectedItem(appointment.getService());
                    appointmentsView.getDateTimePicker().setDateTime(Instant.ofEpochMilli(appointment.getDate().getTime())
                            .atZone(ZoneId.systemDefault()).toLocalDateTime());
                    counter++;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                    LOGGER.log(Level.SEVERE, ex.getMessage());
                }
            } else {

                try {
                    appointment.setPatient(patientService.findPatientByName((String) appointmentsView.getPatientComboBox().getSelectedItem()));
                    appointment.setDoctor(doctorService.findDoctorByName((String) appointmentsView.getDoctorComboBox().getSelectedItem()));
                    appointment.setDoctorOffice(doctorOfficeService.findDoctorOfficeByName((String) appointmentsView.getDoctorOfficeComboBox().getSelectedItem()));
                    appointment.setService((String) appointmentsView.getServiceTypesComboBox().getSelectedItem());
                    appointment.setDate(Date.from(appointmentsView.getDateTimePicker().getDateTime().atZone(ZoneId.systemDefault())
                            .toInstant()));
                    appointmentService.updateAppointment(appointment, appointment.getId());

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                    LOGGER.log(Level.SEVERE, ex.getMessage());
                }

                appointmentsMatrix = appointmentService.appointmentsToMatrix();
                appointmentsTableModel.setDataVector(appointmentsMatrix, Constants.APPOINTMENTS_TABLE_HEADER);
                cleanText();
                LOGGER.log(Level.INFO, "Appointments Maintenance Update Button");
                counter = 0;
            }
//        } else if (source == confirmPatientsView.getSearchButton()) {
//            String searchTerm = confirmPatientsView.getSearchTextField().getText();
//            updateTableSearchTerms(searchTerm);
//            confirmPatientsView.getSearchTextField().setText("");
//            LOGGER.log(Level.INFO, "Appointment Confirmation Search Button");
//
//        } else if (source == confirmPatientsView.getConfirmButton()) {
//
//            String patientName = confirmPatientsView.getAppointmentsTable().getValueAt(confirmPatientsView.
//                    getAppointmentsTable().getSelectedRow(), 1).toString();
//            String consultingRoomName = confirmPatientsView.getAppointmentsTable().getValueAt(confirmPatientsView.
//                    getAppointmentsTable().getSelectedRow(), 2).toString();
//            String date = confirmPatientsView.getAppointmentsTable().getValueAt(confirmPatientsView.
//                    getAppointmentsTable().getSelectedRow(), 3).toString();
//            try {
//                appointmentService.findAppointment(patientName, consultingRoomName, date).setConfirmed(true);
//            } catch (Exception ex) {
//                JOptionPane.showMessageDialog(null, ex.getMessage(),
//                        "Error", JOptionPane.ERROR_MESSAGE);
//                LOGGER.log(Level.SEVERE, ex.getMessage());
//            }
//            appointmentsMatrix = appointmentService.appointmentsToMatrix();
//            appointmentsTableModel.setDataVector(appointmentsMatrix, Constants.APPOINTMENTS_TABLE_HEADER);
//            LOGGER.log(Level.INFO, "Appointment Confirmation Confirm Button");
        }
    }

    /**
     * Erase the text of the JTexFields.
     */
    public void cleanText() {
        appointmentsView.getPatientComboBox().setSelectedItem("");
        appointmentsView.getDoctorComboBox().setSelectedItem("");
        appointmentsView.getDoctorOfficeComboBox().setSelectedItem("");
        appointmentsView.getServiceTypesComboBox().setSelectedItem("");
        appointmentsView.getSearchTextField().setText("");
        appointmentsView.getDateTimePicker().clear();
        LOGGER.log(Level.INFO, "Clean Text");
    }

    /**
     * Search a list of appointments based on a string.
     *
     * @param searchTerm the string to search.
     */
    public void updateTableSearchTerms(String searchTerm) {

        appointmentsMatrix = appointmentService.appointmentsToMatrix();

        if ((searchTerm != null) && !"".equals(searchTerm) && (appointmentService.appointmentsToMatrix() != null) && (appointmentsMatrix.length > 1)) {
            Object[][] newData = new Object[appointmentsMatrix.length][];
            int idx = 0;

            for (Object[] obj : appointmentsMatrix) {
                String fullText = obj[0].toString() + obj[1].toString()
                        + obj[2].toString() + obj[3].toString() + obj[4].toString() + obj[5].toString() + obj[6].toString();

                if (fullText.contains(searchTerm.trim())) {
                    newData[idx++] = obj;
                }
            }
            appointmentsTableModel.setDataVector(newData, Constants.APPOINTMENTS_TABLE_HEADER);

        } else {
            JOptionPane.showMessageDialog(null, "Search term is empty",
                    "Error", JOptionPane.ERROR_MESSAGE);
            LOGGER.log(Level.INFO, "Search term is empty");

            appointmentsTableModel.setDataVector(appointmentsMatrix, Constants.APPOINTMENTS_TABLE_HEADER);
        }
    }

    public void updateComboBoxes() {
        appointmentsView.getDoctorComboBox().setModel(loadDoctorsNames());
        appointmentsView.getPatientComboBox().setModel(loadPatientsNames());
        appointmentsView.getDoctorOfficeComboBox().setModel(loadDoctorOfficesNames());
        appointmentsView.getServiceTypesComboBox().setModel(loadServiceTypesServices());
    }

    /**
     * Static method to create instance of Singleton class (AppointmentController class).
     *
     * @return instance of Appointments controller
     */
    public static AppointmentsController getInstance() {
        if (null == appointmentsController) appointmentsController = new AppointmentsController();
        return appointmentsController;
    }

    /**
     * Returns appointments table model.
     *
     * @return the appointments Table Model
     */
    public DefaultTableModel getAppointmentsTableModel() {
        return appointmentsTableModel;
    }

    /**
     * Returns appointments view.
     *
     * @return the Appointments Maintenance View
     */
    public AppointmentsView getAppointmentsView() {
        return appointmentsView;
    }

//    /**
//     * Returns confirm patients view.
//     *
//     * @return the View for patients to confirm an appointment
//     */
//    public ConfirmPatientsView getConfirmPatientsView() {
//        return confirmPatientsView;
//    }
}
