package cr.una.proyecto.frontend.service;

import cr.una.proyecto.Constants;
import cr.una.proyecto.frontend.model.Doctor;
import cr.una.proyecto.frontend.model.DoctorType;
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
 *
 */
public class DoctorService {
    private List<Doctor> doctors;
    final Logger logger = LogManager.getLogger(DoctorService.class);
    private static final String REST_URI = Constants.WS_ENDPOINT.concat("doctors");
    private final Client client;

    /**
     * Default Constructor
     */
    public DoctorService() {
        this.doctors = new ArrayList<>();
        client = ClientBuilder.newClient();
    }

    /**
     * @param doctors
     */
    public DoctorService(List<Doctor> doctors) {
        this.doctors = doctors;
        client = ClientBuilder.newClient();
    }

    /**
     * This method will load all the data from the WS
     *
     * @return the list of Doctor Offices
     */
    public List<Doctor> loadAllDoctors() {
        logger.debug("Getting the whole list of Doctor");
        List<Doctor> doctorList;
        doctorList = Arrays.asList(client.target(REST_URI).request(MediaType.APPLICATION_JSON)
                .get(Doctor[].class));
        return doctorList;
    }

    /**
     * Searches a Doctors by name, based on the String received as a parameter.
     *
     * @param name Doctor's name
     * @return the Doctor found
     */
    public Doctor findDoctorByName(String name) {
        doctors = loadAllDoctors();
        return doctors.stream().filter(doctor -> doctor.getName().equals(name)).findFirst().orElse(null);
    }

    /**
     * @param doctor
     * @return
     */
    public Doctor saveDoctor(Doctor doctor) {
        Doctor doctorSaved;
        doctorSaved = client.target(REST_URI).request(MediaType.APPLICATION_JSON).post(Entity.entity(doctor,
                MediaType.APPLICATION_JSON), Doctor.class);
        return doctorSaved;
    }

    /**
     * @param doctor
     * @param id
     * @return
     */
    public Doctor updateDoctor(Doctor doctor, int id) {
        Doctor updatedDoctor;
        updatedDoctor = client.target(REST_URI).path("/{id}").resolveTemplate("id", id).
                request(MediaType.APPLICATION_JSON).put(Entity.entity(doctor, MediaType.APPLICATION_JSON), Doctor.class);
        return updatedDoctor;
    }

    /**
     * @param name
     * @return
     */
    public boolean deleteDoctor(String name) {
        boolean isDeleted;
        Doctor doctorToDelete = findDoctorByName(name);
        isDeleted = client.target(REST_URI).path("/{id}")
                .resolveTemplate("id", doctorToDelete.getId())
                .request(MediaType.APPLICATION_JSON)
                .delete(Boolean.class);

        return isDeleted;
    }

    /**
     * Converts the consulting rooms list to a matrix that will be needed for the Table Model.
     *
     * @return the matrix of the list of consulting rooms
     */
    public Object[][] doctorToMatrix() {

        doctors = loadAllDoctors();
        Object[][] data = null;

        if (doctors.size() > 0) {
            data = new Object[doctors.size()][3];

            int i = 0;
            DoctorTypeService service = new DoctorTypeService();
            List<DoctorType> doctorTypes = service.loadAllDoctorTypes();

            for (Doctor doctor : doctors) {

                data[i][0] = checkIfNull(doctor.getName());
                data[i][1] = checkIfNull(doctor.getPremium());
                for (DoctorType doctorType : doctorTypes) {
                    if (doctorType.getMedicalCode().equals(doctor.getMedicalCode())) {
                        data[i][2] = checkIfNull(doctorType.getType());
                    }
                }
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
