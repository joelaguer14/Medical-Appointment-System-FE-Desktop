package cr.una.proyecto.frontend.service;

import cr.una.proyecto.Constants;
import cr.una.proyecto.frontend.model.DoctorType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DoctorTypeService {
    private List<DoctorType> doctorTypes;
    final Logger logger = LogManager.getLogger(DoctorTypeService.class);
    private static final String REST_URI = Constants.WS_ENDPOINT.concat("doctorTypes");
    private final Client client;

    /**
     * Default Constructor
     */
    public DoctorTypeService() {
        this.doctorTypes = new ArrayList<>();
        client = ClientBuilder.newClient();
    }

    public List<DoctorType> loadAllDoctorTypes() {
        logger.debug("Getting the whole list of Doctor Types");
        List<DoctorType> doctorTypeList;
        doctorTypeList = Arrays.asList(client.target(REST_URI).request(MediaType.APPLICATION_JSON)
                .get(DoctorType[].class));

        return doctorTypeList;
    }

    public DoctorType findDoctorTypeByName(String name) {
        doctorTypes = loadAllDoctorTypes();
        return doctorTypes.stream().filter(doctorType -> doctorType.getType().equals(name)).findFirst().orElse(null);
    }
}
