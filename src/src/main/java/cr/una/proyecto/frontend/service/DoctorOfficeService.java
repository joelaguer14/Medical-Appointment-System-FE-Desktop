package cr.una.proyecto.frontend.service;

import cr.una.proyecto.Constants;
import cr.una.proyecto.frontend.model.DoctorOffice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DoctorOfficeService {
    private List<DoctorOffice> doctorOffices;
    final Logger logger = LogManager.getLogger(DoctorOfficeService.class);
    private static final String REST_URI = Constants.WS_ENDPOINT.concat("doctorOffices");
    private final Client client;

    /**
     * Default Constructor
     */
    public DoctorOfficeService() {
        this.doctorOffices = new ArrayList<>();
        client = ClientBuilder.newClient();
    }

    /**
     * @param doctorOffices
     */
    public DoctorOfficeService(List<DoctorOffice> doctorOffices) {
        this.doctorOffices = doctorOffices;
        client = ClientBuilder.newClient();
    }

    /**
     * This method will load all the data from the WS
     *
     * @return the list of Doctor Offices
     */
    public List<DoctorOffice> loadAllDoctorOffices() {
        logger.debug("Getting the whole list of Doctor Offices");

        List<DoctorOffice> doctorOfficeList;

        doctorOfficeList = Arrays.asList(client.target(REST_URI).request(MediaType.APPLICATION_JSON)
                .get(DoctorOffice[].class));

        return doctorOfficeList;
    }

    /**
     * Searches a doctor office by name, based on the String received as a parameter.
     *
     * @param name Doctor's office name
     * @return the Doctor's office found
     */
    public DoctorOffice findDoctorOfficeByName(String name) {
        doctorOffices = loadAllDoctorOffices();
        return doctorOffices.stream().filter(docOffice -> docOffice.getName().equals(name)).findFirst().orElse(null);
    }

    public DoctorOffice saveDoctorOffice(DoctorOffice doctorOffice) {
        DoctorOffice doctorSaved;
        doctorSaved = client.target(REST_URI).request(MediaType.APPLICATION_JSON).post(Entity.entity(doctorOffice,
                MediaType.APPLICATION_JSON), DoctorOffice.class);


        return doctorSaved;
    }

    public DoctorOffice updateDoctorOffice(DoctorOffice doctorOffice, int id) {
        DoctorOffice updatedDoctorOffice;
        updatedDoctorOffice = client.target(REST_URI).path("/{id}").resolveTemplate("id", id).
                request(MediaType.APPLICATION_JSON).put(Entity.entity(doctorOffice, MediaType.APPLICATION_JSON), DoctorOffice.class);
        return updatedDoctorOffice;
    }

    public boolean deleteDoctorOffice(String name) {
        boolean isDeleted;

        DoctorOffice doctorOfficeToDelete = findDoctorOfficeByName(name);


        isDeleted = client.target(REST_URI).path("/{id}")
                .resolveTemplate("id", doctorOfficeToDelete.getId())
                .request(MediaType.APPLICATION_JSON)
                .delete(Boolean.class);

        return isDeleted;
    }

    /**
     * Converts the doctor office list to a matrix that will be needed for the Table Model.
     *
     * @return the matrix of the list of consulting rooms
     */
    public Object[][] doctorOfficeToMatrix() {

        doctorOffices = loadAllDoctorOffices();
        Object[][] data = null;

        if (doctorOffices.size() > 0) {
            data = new Object[doctorOffices.size()][5];
            int i = 0;
            for (DoctorOffice doctorOffice : doctorOffices) {
                StringBuilder phones = new StringBuilder();
                for (int j = 0; j < doctorOffice.getPhones().size(); j++) {
                    if (j < doctorOffice.getPhones().size() - 1) {
                        phones.append(doctorOffice.getPhones().get(j).getPhone());
                        phones.append(", ");
                    } else {
                        phones.append(doctorOffice.getPhones().get(j).getPhone());
                    }

                }
                StringBuilder addresses = new StringBuilder();
                for (int j = 0; j < doctorOffice.getAddresses().size(); j++) {
                    if (i < doctorOffice.getAddresses().size() - 1) {
                        addresses.append(doctorOffice.getAddresses().get(j).getAddress());
                        addresses.append(", ");
                    } else {
                        addresses.append(doctorOffice.getAddresses().get(j).getAddress());
                    }
                }

                data[i][0] = checkIfNull(doctorOffice.getName());
                data[i][1] = checkIfNull(doctorOffice.getDays());
                data[i][2] = checkIfNull(doctorOffice.getHour());
                data[i][3] = checkIfNull(phones);
                data[i][4] = checkIfNull(addresses);
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
