//package cr.una.proyecto.frontend.service;
//
//import cr.una.proyecto.frontend.model.Appointment;
//import cr.una.proyecto.frontend.model.DoctorOffice;
//import cr.una.proyecto.frontend.model.Patient;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Objects;
//
///**
// * @author Felipe Guzman Rodriguez
// * @author Manfred Joel Aguero Campos
// */
//
//public class Service {
//    private final ArrayList<Patient> patients;
//    private final ArrayList<DoctorOffice> consultingRooms;
//    private final ArrayList<Appointment> appointments;
//
//    /**
//     * Default Constructor
//     */
//    public Service() {
//        patients = new ArrayList<>();
//        consultingRooms = new ArrayList<>();
//        appointments = new ArrayList<>();
//    }
//
//    /**
//     * Creates a new Patient with the received parameters.
//     *
//     * @param fullName     patient's full name.
//     * @param address      patient's address.
//     * @param phone        patient's phone number.
//     * @param diseases     patient's diseases.
//     * @param observations any observation of the patient.
//     * @param birthDate    patient's date of birth.
//     */
//    public void newPatient(String fullName, String address, String phone, String diseases, String observations, Date birthDate) {
//        patients.add(new Patient(fullName, address, phone, diseases, observations, birthDate));
//    }
////
////    /**
////     * Creates a new Consulting Room with the received parameters. It would throw an
////     * exception if the days are not valid.
////     *
////     * @param name  consulting room's name.
////     * @param days  consulting room's available days.
////     * @param hour  consulting room's available hours.
////     * @param phone consulting room's phone number.
////     * @throws Exception if days are not valid.
////     */
////    public void newConsultingRoom(String name, String days, String hour, String phone) throws Exception {
////        validateConsultingRoomDays(days);
////        consultingRooms.add(new DoctorOffice(name, days, hour, phone));
////    }
////
////    /**
////     * Creates a new Appointment with the received parameters. It calls a method to
////     * search the patient by the name, the consulting room by its name and calls a
////     * method to validate the date.
////     * <p>
////     * It would throw an exception if the Patient or the consulting room does not
////     * exist. It would also throw an Exception if the date is not valid for that
////     * appointment.
////     *
////     * @param fullName           Patient's full name
////     * @param consultingRoomName Consulting room's name
////     * @param date               Appointment's date
////     * @throws Exception if the appointment's date is not valid
////     */
////    public void newAppointment(String fullName, String consultingRoomName, LocalDateTime date) throws Exception {
////        Patient patient = findPatientByName(fullName);
////        DoctorOffice consultingRoom = findConsultingRoomByName(consultingRoomName);
////        appointmentDateValidator(date, consultingRoom);
////        if (patient != null && consultingRoom != null) appointments.add(new Appointment(patient, consultingRoom, date));
////        else throw new Exception("Patient or Consulting Room not found");
////    }
//
////    /**
////     * It would searches a Patient by its name, based on the String received.
////     *
////     * @param fullName Patient's full name
////     * @return the patient found.
////     */
////    public Patient findPatientByName(String fullName) {
////        return patients.stream().filter(patient -> patient.getFullName().equals(fullName)).findFirst().orElse(null);
////    }
////
//    /**
//     * Searches a Consulting Room by name, based on the String received as a parameter.
//     *
//     * @param name consulting room's name
//     * @return the consulting room found
//     */
//    public DoctorOffice findConsultingRoomByName(String name) {
//        return consultingRooms.stream().filter(consRoom -> consRoom.getName().equals(name)).findFirst().orElse(null);
//    }
////
////    /**
////     * It searches an appointment based on patient's name, Consulting Room's name and
////     * date. if the appointment does not exist it will throw an exception.
////     *
////     * @param patientName        patient's full name
////     * @param consultingRoomName consulting room's name
////     * @param date               Appointment's date
////     * @return the appointment found
////     * @throws Exception if the appointment doesn't exist.
////     */
////    public Appointment findAppointment(String patientName, String consultingRoomName, String date) throws Exception {
////        for (Appointment appointment : appointments) {
////            if (appointment.getPatient().getFullName().equals(patientName) && appointment.getDoctorOffice().getName().
////                    equals(consultingRoomName) && appointment.getDate().toString().equals(date)) {
////                return appointment;
////            }
////        }
////        throw new Exception("This appointment does not exist");
////    }
//
////    /**
////     * Searches a patient by name and deletes it.
////     *
////     * @param nameToDelete name of the patient to delete.
////     */
////    public void deletePatient(String nameToDelete) {
////        patients.removeIf(patient -> nameToDelete.equals(patient.getFullName()));
////    }
//
//    /**
//     * Searches a Consulting Room by name and deletes it.
//     *
//     * @param nameToDelete name of the consulting room to delete
//     */
//    public void deleteConsultingRoom(String nameToDelete) {
//        consultingRooms.removeIf(consRoom -> nameToDelete.equals(consRoom.getName()));
//    }
////
////    /**
////     * Searches an appointment by Patient's name and deletes it.
////     *
////     * @param nameToDelete name of the Appointment's patient to delete
////     */
////    public void deleteAppointment(String nameToDelete) {
////        appointments.removeIf(appointment -> nameToDelete.equals(appointment.getPatient().getFullName()));
////    }
//
//    /**
//     * Checks if the object in the parameter is null. If it's null it changes it to the
//     * String ""
//     *
//     * @param obj object to analyze if it's null
//     * @return the object's toString().
//     */
//    private String checkIfNull(Object obj) {
//        String text;
//
//        if (obj == null) text = "";
//        else text = obj.toString();
//        return text;
//    }
//
////    /**
////     * Converts the patients list to a matrix that will be needed for the Table Model.
////     *
////     * @return the matrix of the list of patients
////     */
////    public Object[][] patientsToMatrix() {
////        Object[][] data = null;
////
////        if (patients.size() > 0) {
////            data = new Object[patients.size()][6];
////
////            int i = 0;
////
////            for (Patient patient : patients) {
////                data[i][0] = checkIfNull(patient.getFullName());
////                data[i][1] = checkIfNull(patient.getPhone());
////                data[i][2] = checkIfNull(patient.getAddress());
////                data[i][3] = checkIfNull(patient.getDiseases());
////                data[i][4] = checkIfNull(patient.getObservations());
////                data[i][5] = checkIfNull(patient.getBirthDate());
////                i++;
////            }
////        }
////        return data;
////    }
////
//    /**
//     * Converts the consulting rooms list to a matrix that will be needed for the Table Model.
//     *
//     * @return the matrix of the list of consulting rooms
//     */
//    public Object[][] consultingRoomsToMatrix() {
//        Object[][] data = null;
//
//        if (consultingRooms.size() > 0) {
//            data = new Object[consultingRooms.size()][6];
//
//            int i = 0;
//
//            for (DoctorOffice consultingRoom : consultingRooms) {
//                data[i][0] = checkIfNull(consultingRoom.getName());
//                data[i][1] = checkIfNull(consultingRoom.getDays());
//                data[i][2] = checkIfNull(consultingRoom.getHour());
//                data[i][3] = checkIfNull(consultingRoom.getPhone());
//                i++;
//            }
//        }
//        return data;
//    }
////
////    /**
////     * Converts the appointments list to a matrix that will be needed for the Table Model.
////     *
////     * @return the matrix of the appointments
////     */
////    public Object[][] appointmentsToMatrix() {
////        Object[][] data = null;
////
////        if (appointments.size() > 0) {
////            data = new Object[appointments.size()][6];
////
////            int i = 0;
////
////            for (Appointment appointment : appointments) {
////                String confirmed;
////                if (appointment.isConfirmed()) confirmed = "Confirmed";
////                else confirmed = "Not confirmed";
////                data[i][0] = checkIfNull(confirmed);
////                data[i][1] = checkIfNull(appointment.getPatient().getFullName());
////                data[i][2] = checkIfNull(appointment.getDoctorOffice().getName());
////                data[i][3] = checkIfNull(appointment.getDate().toString());
////                i++;
////            }
////        }
////        return data;
////    }
////
////    /**
////     * Validates the date received in the parameters for a new appointment. It will
////     * throw an exception if the date is not available or if the Consulting Room is not
////     * available in that day of the week.
////     *
////     * @param date Appointment's date to validate.
////     * @param consultingRoom appointment's consulting room
////     * @throws Exception if the date is not valid.
////     */
////    public void appointmentDateValidator(LocalDateTime date, DoctorOffice consultingRoom) throws Exception {
////        for (Appointment appointment : appointments)
////            if (Objects.equals(date, appointment.getDate())) throw new Exception(date.toString() + " not available");
////            else if (consultingRoom != null && !consultingRoom.getDays().contains(date.getDayOfWeek().toString()))
////                throw new Exception(date.
////                        getDayOfWeek().toString() + " not available in consulting room: " + consultingRoom.getName());
////    }
////
////    /**
////     * Validates the consulting Room Days to make sure that the user don't type an
////     * invalid word. It'll throw an exception if the Text is not valid or does not
////     * contain any day of the week.
////     *
////     * @param days text of days to validate.
////     * @throws Exception if the text doesn't have a valid day.
////     */
////    public void validateConsultingRoomDays(String days) throws Exception {
////        if (!(days.contains("MONDAY") || days.contains("TUESDAY") || days.contains("WEDNESDAY") || days.contains("THURSDAY")
////                || days.contains("FRIDAY") || days.contains("SATURDAY") || days.contains("SUNDAY")))
////            throw new Exception("Invalid day");
////    }
////
////    /**
////     * Returns the list of patients.
////     *
////     * @return the list of patients
////     */
////    public ArrayList<Patient> getPatients() {
////        return patients;
////    }
////
//    /**
//     * Returns the list of Consulting Rooms
//     *
//     * @return the list of Consulting Room
//     */
//    public ArrayList<DoctorOffice> getConsultingRooms() {
//        return consultingRooms;
//    }
////
////    /**
////     * Returns the lis of appointments
////     *
////     * @return the list of appointments.
////     */
////    public ArrayList<Appointment> getAppointments() {
////        return appointments;
////    }
//}
