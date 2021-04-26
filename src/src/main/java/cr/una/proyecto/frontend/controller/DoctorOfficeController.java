package cr.una.proyecto.frontend.controller;

import cr.una.proyecto.Constants;
import cr.una.proyecto.frontend.model.Address;
import cr.una.proyecto.frontend.model.DoctorOffice;
import cr.una.proyecto.frontend.model.Phone;
import cr.una.proyecto.frontend.service.DoctorOfficeService;
import cr.una.proyecto.frontend.view.DoctorOfficeView;

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
 */

public class DoctorOfficeController implements ActionListener {
    private Object[][] doctorOfficesMatrix;
    final DoctorOfficeService doctorOfficeService;
    private final DefaultTableModel doctorOfficesTableModel;
    private final DoctorOfficeView doctorOfficeView;
    private static DoctorOfficeController doctorOfficeController;
    private int counter = 0;
    private DoctorOffice doctorOffice;
    private final static Logger LOGGER = Logger.getLogger("DoctorOfficeController");

    /**
     * Default Constructor
     */
    private DoctorOfficeController() {
        this.doctorOfficeService = new DoctorOfficeService();
        this.doctorOfficesTableModel = new DefaultTableModel();
        doctorOfficesMatrix = doctorOfficeService.doctorOfficeToMatrix();
        doctorOfficesTableModel.setDataVector(doctorOfficesMatrix, Constants.DOCTOR_OFFICES_TABLE_HEADER);
        this.doctorOfficeView = new DoctorOfficeView(this);
    }

    /**
     * Handles the events from the view.
     *
     * @param actionEvent the event from the view
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (source == doctorOfficeView.getSearchButton()) {
            String searchTerm = doctorOfficeView.getSearchTextField().getText();
            updateTableSearchTerms(searchTerm);
            cleanText();
            LOGGER.log(Level.INFO, "Doctor Office Search Button");

        } else if (source == doctorOfficeView.getAddButton()) {
            DoctorOffice doctorOffice = new DoctorOffice();

            String[] addresses = doctorOfficeView.getAddressesTextArea().getText().split(",");
            String[] phones = doctorOfficeView.getPhoneTextField().getText().split(",");
            String[] phoneTypes = doctorOfficeView.getPhoneTypeTextField().getText().split(",");
            List<Address> addressList = new ArrayList<>();
            List<Phone> phoneList = new ArrayList<>();
            for (String address : addresses) {
                addressList.add(new Address(address, "Doctor Office address"));
            }
            for (int i = 0; i < phones.length; i++) {
                phoneList.add(new Phone(phones[i], phoneTypes[i]));
            }

            try {
                doctorOffice.setName(doctorOfficeView.getNameTextField().getText());
                doctorOffice.setDays(doctorOfficeView.getDaysTextField().getText().toUpperCase());
                doctorOffice.setHour(doctorOfficeView.getHoursTextField().getText());
                doctorOffice.setAddresses(addressList);
                doctorOffice.setPhones(phoneList);
                doctorOfficeService.saveDoctorOffice(doctorOffice);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                LOGGER.log(Level.SEVERE, ex.getMessage());
            }
            doctorOfficesMatrix = doctorOfficeService.doctorOfficeToMatrix();
            doctorOfficesTableModel.setDataVector(doctorOfficesMatrix, Constants.DOCTOR_OFFICES_TABLE_HEADER);
            cleanText();
            LOGGER.log(Level.INFO, "Doctor Office Maintenance Add Button");

        } else if (source == doctorOfficeView.getDeleteButton()) {
            String nameToDelete = doctorOfficeView.getSearchTextField().getText();
            try {
                doctorOfficeService.deleteDoctorOffice(nameToDelete);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Could not delete or it does not exist" +
                        ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                LOGGER.log(Level.SEVERE, ex.getMessage());
            }
            doctorOfficesMatrix = doctorOfficeService.doctorOfficeToMatrix();
            doctorOfficesTableModel.setDataVector(doctorOfficesMatrix, Constants.DOCTOR_OFFICES_TABLE_HEADER);
            cleanText();
            LOGGER.log(Level.INFO, "Doctor Office Maintenance Delete Button");
        } else if (doctorOfficeView.getUpdateButton() == source) {
            if (counter == 0) {
                try {
                    doctorOffice = doctorOfficeService.findDoctorOfficeByName(doctorOfficeView.getSearchTextField().getText());
                    doctorOfficeView.getNameTextField().setText(doctorOffice.getName());
                    StringBuilder phones = new StringBuilder();
                    StringBuilder phoneTypes = new StringBuilder();
                    for (int i = 0; i < doctorOffice.getPhones().size(); i++) {
                        if (i < doctorOffice.getPhones().size() - 1) {
                            phones.append(doctorOffice.getPhones().get(i).getPhone());
                            phones.append(",");
                            phoneTypes.append(doctorOffice.getPhones().get(i).getPhoneType());
                            phoneTypes.append(",");
                        } else {
                            phones.append(doctorOffice.getPhones().get(i).getPhone());
                            phoneTypes.append(doctorOffice.getPhones().get(i).getPhoneType());
                        }

                    }
                    doctorOfficeView.getPhoneTextField().setText(phones.toString());
                    doctorOfficeView.getPhoneTypeTextField().setText(phoneTypes.toString());
                    StringBuilder addresses = new StringBuilder();
                    StringBuilder addressTypes = new StringBuilder();
                    for (int i = 0; i < doctorOffice.getAddresses().size(); i++) {
                        if (i < doctorOffice.getAddresses().size() - 1) {
                            addresses.append(doctorOffice.getAddresses().get(i).getAddress());
                            addresses.append(",");
                            addressTypes.append(doctorOffice.getAddresses().get(i).getType());
                            addressTypes.append(",");
                        } else {
                            addresses.append(doctorOffice.getAddresses().get(i).getAddress());
                            addressTypes.append(doctorOffice.getAddresses().get(i).getType());
                        }
                    }
                    doctorOfficeView.getAddressesTextArea().setText(addresses.toString());
                    doctorOfficeView.getDaysTextField().setText(doctorOffice.getDays());
                    doctorOfficeView.getHoursTextField().setText(doctorOffice.getHour());
                    counter++;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Exception: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                    LOGGER.log(Level.SEVERE, ex.getMessage());
                }
            } else {

                String[] addresses = doctorOfficeView.getAddressesTextArea().getText().split(",");
                String[] phones = doctorOfficeView.getPhoneTextField().getText().split(",");
                String[] phoneTypes = doctorOfficeView.getPhoneTypeTextField().getText().split(",");
                List<Address> addressList = new ArrayList<>();
                List<Phone> phoneList = new ArrayList<>();
                for (String address : addresses) {
                    addressList.add(new Address(address, "Doctor Office address"));
                }
                for (int i = 0; i < phones.length; i++) {
                    phoneList.add(new Phone(phones[i], phoneTypes[i]));
                }

                try {
                    doctorOffice.setName(doctorOfficeView.getNameTextField().getText());
                    doctorOffice.setDays(doctorOfficeView.getDaysTextField().getText().toUpperCase());
                    doctorOffice.setHour(doctorOfficeView.getHoursTextField().getText());
                    doctorOffice.setAddresses(addressList);
                    doctorOffice.setPhones(phoneList);

                    doctorOfficeService.updateDoctorOffice(doctorOffice, doctorOffice.getId());

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Exception: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                    LOGGER.log(Level.SEVERE, ex.getMessage());
                }
                doctorOfficesMatrix = doctorOfficeService.doctorOfficeToMatrix();
                doctorOfficesTableModel.setDataVector(doctorOfficesMatrix, Constants.DOCTOR_OFFICES_TABLE_HEADER);
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
        doctorOfficeView.getSearchTextField().setText("");
        doctorOfficeView.getNameTextField().setText("");
        doctorOfficeView.getDaysTextField().setText("");
        doctorOfficeView.getHoursTextField().setText("");
        doctorOfficeView.getPhoneTextField().setText("");
        doctorOfficeView.getAddressesTextArea().setText("");
        doctorOfficeView.getPhoneTypeTextField().setText("");
        LOGGER.log(Level.INFO, "Clean text");
    }

    /**
     * Search a list of doctor offices based on a string.
     *
     * @param searchTerm the string to search.
     */
    private void updateTableSearchTerms(String searchTerm) {
        doctorOfficesMatrix = doctorOfficeService.doctorOfficeToMatrix();
        if ((searchTerm != null) && !"".equals(searchTerm) && (doctorOfficeService.doctorOfficeToMatrix() != null) && (doctorOfficesMatrix.length > 1)) {
            Object[][] newData = new Object[doctorOfficesMatrix.length][];
            int idx = 0;

            for (Object[] obj : doctorOfficesMatrix) {
                String fullText = obj[0].toString() + obj[1].toString()
                        + obj[2].toString() + obj[3].toString() + obj[4].toString();
                if (fullText.contains(searchTerm.trim())) {
                    newData[idx++] = obj;
                }
            }
            doctorOfficesTableModel.setDataVector(newData, Constants.DOCTOR_OFFICES_TABLE_HEADER);
        } else {
            JOptionPane.showMessageDialog(null, "Search term is empty",
                    "Error", JOptionPane.ERROR_MESSAGE);
            doctorOfficesTableModel.setDataVector(doctorOfficesMatrix, Constants.DOCTOR_OFFICES_TABLE_HEADER);
            LOGGER.log(Level.INFO, "Search term is empty");
        }
    }

    /**
     * Static method to create instance of Singleton class (DoctorOfficeController class).
     *
     * @return the instance of Consulting Room Controller.
     */
    public static DoctorOfficeController getInstance() {
        if (null == doctorOfficeController) doctorOfficeController = new DoctorOfficeController();
        return doctorOfficeController;
    }

    public Object[][] getDoctorOfficesMatrix() {
        return doctorOfficesMatrix;
    }

    public DoctorOfficeService getDoctorOfficeService() {
        return doctorOfficeService;
    }

    public DefaultTableModel getDoctorOfficesTableModel() {
        return doctorOfficesTableModel;
    }

    public DoctorOfficeView getDoctorOfficeView() {
        return doctorOfficeView;
    }

    public static DoctorOfficeController getDoctorOfficeController() {
        return doctorOfficeController;
    }

    public int getCounter() {
        return counter;
    }

    public DoctorOffice getDoctorOffice() {
        return doctorOffice;
    }

    public void setDoctorOfficesMatrix(Object[][] doctorOfficesMatrix) {
        this.doctorOfficesMatrix = doctorOfficesMatrix;
    }

    public static void setDoctorOfficeController(DoctorOfficeController doctorOfficeController) {
        DoctorOfficeController.doctorOfficeController = doctorOfficeController;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void setDoctorOffice(DoctorOffice doctorOffice) {
        this.doctorOffice = doctorOffice;
    }
}
