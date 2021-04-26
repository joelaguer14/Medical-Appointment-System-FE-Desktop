package cr.una.proyecto.frontend.service;

import cr.una.proyecto.Constants;
import cr.una.proyecto.frontend.model.ServiceType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServiceTypeService {
    private List<ServiceType> serviceTypes;
    final Logger logger = LogManager.getLogger(ServiceTypeService.class);
    private static final String REST_URI = Constants.WS_ENDPOINT.concat("serviceTypes");
    private final Client client;

    /**
     * Default Constructor
     */
    public ServiceTypeService() {
        this.serviceTypes = new ArrayList<>();
        client = ClientBuilder.newClient();
    }

    public List<ServiceType> loadAllServiceTypes() {
        logger.debug("Getting the whole list of Service Types");
        List<ServiceType> serviceTypeList;
        serviceTypeList = Arrays.asList(client.target(REST_URI).request(MediaType.APPLICATION_JSON)
                .get(ServiceType[].class));

        return serviceTypeList;
    }

    public ServiceType findServiceTypeByName(String name) {
        serviceTypes = loadAllServiceTypes();
        return serviceTypes.stream().filter(serviceType -> serviceType.getService().equals(name)).findFirst().orElse(null);
    }

}
