package cr.una.proyecto;

/**
 * @author Felipe Guzman Rodriguez
 * @author Manfred Joel Aguero Campos
 */
public class Constants {

    public static final Object[] PATIENTS_TABLE_HEADER = {"Full Name", "Phone", "Address", "Diseases",
            "Observations", "Date of Birth"};

    public static final Object[] DOCTOR_OFFICES_TABLE_HEADER = {"Name", "Available Days", "Available Hours", "Phone", "Addresses"};

    public static final Object[] APPOINTMENTS_TABLE_HEADER = {"Confirmed", "Patient Name", "Doctor Office Name",
            "Doctor Name", "Cost", "Date", "Service Type"};

    public static final Object[] DOCTOR_TABLE_HEADER = {"Name", "Premium", "Doctor Type"};

    public static final String WS_ENDPOINT = "http://localhost:8083/api/";
}
