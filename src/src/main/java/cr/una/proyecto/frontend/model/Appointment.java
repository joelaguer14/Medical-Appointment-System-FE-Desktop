package cr.una.proyecto.frontend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;


/**
 * @author Felipe Guzman Rodriguez
 * @author Manfred Joel Aguero Campos
 */

public class Appointment {
    @JsonProperty("id")
    private int id;
    @JsonProperty("patient")
    private Patient patient;
    @JsonProperty("doctorOffice")
    private DoctorOffice doctorOffice;
    @JsonProperty("doctor")
    private Doctor doctor;
    @JsonProperty("date")
    private Date date;
    @JsonProperty("confirmed")
    private boolean confirmed;
    @JsonProperty("service")
    private String service;
    @JsonProperty("totalCost")
    private float totalCost;

    /**
     * Default constructor
     */
    public Appointment() {
    }

    /**
     * Constructor with parameters
     *
     * @param patient,      patient of the appointment
     * @param doctorOffice, doctorOffice of the appointment
     * @param date,         date of the appointment
     * @param doctor,       doctor of the appointment
     */
    public Appointment(Patient patient, DoctorOffice doctorOffice, Date date, Doctor doctor, String service) {
        this.patient = patient;
        this.doctorOffice = doctorOffice;
        this.date = date;
        confirmed = false;
        this.doctor = doctor;
        this.service = service;
    }

    /**
     *
     * @param patient
     * @param doctorOffice
     * @param date
     * @param doctor
     * @param service
     * @param totalCost
     */
    public Appointment(Patient patient, DoctorOffice doctorOffice, Date date, Doctor doctor, String service, float totalCost) {
        this.patient = patient;
        this.doctorOffice = doctorOffice;
        this.date = date;
        confirmed = false;
        this.doctor = doctor;
        this.service = service;
        this.totalCost = totalCost;
    }

    public int getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public DoctorOffice getDoctorOffice() {
        return doctorOffice;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Date getDate() {
        return date;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public String getService() {
        return service;
    }

    public float getCost() {
        return totalCost;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDoctorOffice(DoctorOffice doctorOffice) {
        this.doctorOffice = doctorOffice;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setCost(float cost) {
        this.totalCost = cost;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", patient=" + patient +
                ", doctorOffice=" + doctorOffice +
                ", doctor=" + doctor +
                ", date=" + date +
                ", confirmed=" + confirmed +
                ", service='" + service + '\'' +
                ", totalCost=" + totalCost +
                '}';
    }
}