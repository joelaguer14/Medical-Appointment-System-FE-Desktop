package cr.una.proyecto.frontend.service;

import cr.una.proyecto.Constants;
import cr.una.proyecto.frontend.model.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Service to get the data from the service
 */
public class PatientService {
    final Logger logger = LogManager.getLogger(PatientService.class);
    private List<Patient> patients;
    private static final String REST_URI = Constants.WS_ENDPOINT.concat("patients");
    private final Client client;

    /**
     * Empty Constructor
     */
    public PatientService() {
        patients = new ArrayList<>();
        client = ClientBuilder.newClient();
    }

    /**
     * This method will load all the data from the WS
     *
     * @return the list of Patients
     */
    public List<Patient> loadAllPatients() {
        logger.debug("Getting the whole list of patients");
        List<Patient> patientList;
        patientList = Arrays.asList(client.target(REST_URI).request(MediaType.APPLICATION_JSON)
                .get(Patient[].class));

        return patientList;
    }

    /**
     * It would searches a Patient by its name, based on the String received.
     *
     * @param fullName Patient's full name
     * @return the patient found.
     */
    public Patient findPatientByName(String fullName) {
        patients = loadAllPatients();
        return patients.stream().filter(patient -> patient.getFullName().equals(fullName)).findFirst().orElse(null);
    }

    /**
     * @param patient
     * @return
     */
    public Patient savePatient(Patient patient) {
        Patient patientSaved;
        patientSaved = client.target(REST_URI).request(MediaType.APPLICATION_JSON).post(Entity.entity(patient,
                MediaType.APPLICATION_JSON), Patient.class);
        return patientSaved;
    }

    /**
     * @param patient the patient to update in the backend
     * @param id      id of the patient that's going to be updated
     * @return the updated patient
     */
    public Patient updatePatient(Patient patient, int id) {
        Patient updatedPatient;
        updatedPatient = client.target(REST_URI).path("/{id}").resolveTemplate("id", id).
                request(MediaType.APPLICATION_JSON).put(Entity.entity(patient, MediaType.APPLICATION_JSON), Patient.class);
        return updatedPatient;
    }

    /**
     * @param name
     * @return
     */
    public boolean deletePatient(String name) {
        boolean patientDeleted;
        Patient patientToDelete = findPatientByName(name);
        patientDeleted = client.target(REST_URI).path("/{id}")
                .resolveTemplate("id", patientToDelete.getId())
                .request(MediaType.APPLICATION_JSON)
                .delete(Boolean.class);
        return patientDeleted;
    }

    /**
     * Converts the patients list to a matrix that will be needed for the Table Model.
     *
     * @return the matrix of the list of patients
     */
    public Object[][] patientsToMatrix() {
        patients = loadAllPatients();
        Object[][] data = null;

        if (patients.size() > 0) {
            data = new Object[patients.size()][6];
            int i = 0;
            for (Patient patient : patients) {
                StringBuilder phones = new StringBuilder();
                for (int j = 0; j < patient.getPhones().size(); j++) {
                    if (j < patient.getPhones().size() - 1) {
                        phones.append(patient.getPhones().get(j).getPhone());
                        phones.append(", ");
                    } else {
                        phones.append(patient.getPhones().get(j).getPhone());
                    }

                }

                StringBuilder addresses = new StringBuilder();
                for (int j = 0; j < patient.getAddresses().size(); j++) {
                    if (j < patient.getAddresses().size() - 1) {
                        addresses.append(patient.getAddresses().get(j).getAddress());
                        addresses.append(", ");
                    } else {
                        addresses.append(patient.getAddresses().get(j).getAddress());
                    }
                }

                StringBuilder diseases = new StringBuilder();
                for (int j = 0; j < patient.getDiseases().size(); j++) {
                    if (j < patient.getDiseases().size() - 1) {
                        diseases.append(patient.getDiseases().get(j).getDisease());
                        diseases.append(", ");
                    } else {
                        diseases.append(patient.getDiseases().get(j).getDisease());
                    }
                }

                data[i][0] = checkIfNull(patient.getFullName());
                data[i][1] = checkIfNull(phones);
                data[i][2] = checkIfNull(addresses);
                data[i][3] = checkIfNull(diseases);
                data[i][4] = checkIfNull(patient.getObservations());
                data[i][5] = checkIfNull(patient.getBirthDate());
                i++;
            }
        }
        return data;
    }

    /**
     * Checks if the object in the parameter is null. If it's null it changes it to the
     * String ""
     *
     * @param obj object to analyze if it's null
     * @return the object's toString().
     */
    private String checkIfNull(Object obj) {
        String text;
        if (obj == null) text = "";
        else text = obj.toString();
        return text;
    }
}
