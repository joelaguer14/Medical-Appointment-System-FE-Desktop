package cr.una.proyecto.frontend.service;

import cr.una.proyecto.Constants;
import cr.una.proyecto.frontend.model.Appointment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppointmentService {

    private List<Appointment> appointments;
    final Logger logger = LogManager.getLogger(DoctorService.class);
    private static final String REST_URI = Constants.WS_ENDPOINT.concat("appointments");
    private final Client client;

    /**
     * Default Constructor
     */
    public AppointmentService() {
        this.appointments = new ArrayList<>();
        client = ClientBuilder.newClient();
    }

    /**
     * @param appointments
     */
    public AppointmentService(List<Appointment> appointments) {
        this.appointments = appointments;
        client = ClientBuilder.newClient();
    }

    /**
     * This method will load all the data from the WS
     *
     * @return the list of Appointments
     */
    public List<Appointment> loadAllAppointments() {
        logger.debug("Getting the whole list of Appointments");
        List<Appointment> appointmentList;
        appointmentList = Arrays.asList(client.target(REST_URI).request(MediaType.APPLICATION_JSON)
                .get(Appointment[].class));
        return appointmentList;
    }

    /**
     * Searches an Appointment by patient's name, based on the String received as a parameter.
     *
     * @param name Appointment's Patient name
     * @return the Appointment found
     */
    public Appointment findAppointmentByPatientName(String name) {
        appointments = loadAllAppointments();
        return appointments.stream().filter(appointment -> appointment.getPatient().getFullName().equals(name)).findFirst().orElse(null);
    }

    /**
     * @param appointment
     * @return
     */
    public Appointment saveAppointment(Appointment appointment) {
        Appointment appointmentSaved;
        appointmentSaved = client.target(REST_URI).request(MediaType.APPLICATION_JSON).post(Entity.entity(appointment,
                MediaType.APPLICATION_JSON), Appointment.class);
        return appointmentSaved;
    }

    /**
     * @param appointment
     * @param id
     * @return
     */
    public Appointment updateAppointment(Appointment appointment, int id) {
        Appointment updatedDoctor;
        updatedDoctor = client.target(REST_URI).path("/{id}").resolveTemplate("id", id).
                request(MediaType.APPLICATION_JSON).put(Entity.entity(appointment, MediaType.APPLICATION_JSON), Appointment.class);
        return updatedDoctor;
    }

    /**
     * @param name
     * @return
     */
    public boolean deleteAppointment(String name) {
        boolean isDeleted;
        Appointment appointmentToDelete = findAppointmentByPatientName(name);
        isDeleted = client.target(REST_URI).path("/{id}")
                .resolveTemplate("id", appointmentToDelete.getId())
                .request(MediaType.APPLICATION_JSON)
                .delete(Boolean.class);

        return isDeleted;
    }

    /**
     * Converts the appointments list to a matrix that will be needed for the Table Model.
     *
     * @return the matrix of the appointments
     */
    public Object[][] appointmentsToMatrix() {
        Object[][] data = null;
        appointments = loadAllAppointments();

        if (appointments.size() > 0) {
            data = new Object[appointments.size()][7];

            int i = 0;

            for (Appointment appointment : appointments) {
                String confirmed;
                if (appointment.isConfirmed()) confirmed = "Confirmed";
                else confirmed = "Not confirmed";
                data[i][0] = checkIfNull(confirmed);
                data[i][1] = checkIfNull(appointment.getPatient().getFullName());
                data[i][2] = checkIfNull(appointment.getDoctorOffice().getName());
                data[i][3] = checkIfNull(appointment.getDoctor().getName());
                data[i][4] = checkIfNull(appointment.getCost());
                data[i][5] = checkIfNull(appointment.getDate().toString());
                data[i][6] = checkIfNull(appointment.getService());
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
