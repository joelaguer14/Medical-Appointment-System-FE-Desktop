package cr.una.proyecto.frontend.controller;


import cr.una.proyecto.frontend.view.MainView;

/**
 * @author Felipe Guzman Rodriguez
 * @author Manfred Joel Aguero Campos
 *
 *
 */
public class MainController {
    //final Service Service;
    private final MainView mainView;
    private static MainController mainController;
    final PatientsController patientsController;
    final DoctorOfficeController doctorOfficeController;
    final DoctorController doctorController;
    final AppointmentsController appointmentsController;

    /**
     * Constructor which receives a MainView to set it to the attribute and be its
     * ActionListener
     *
     * @param mainView the main window.
     */
    private MainController(MainView mainView) {
        this.mainView = mainView;
        // this.appointmentService = new Service();
        this.patientsController = PatientsController.getInstance();
        this.doctorOfficeController = DoctorOfficeController.getInstance();
        this.doctorController = DoctorController.getInstance();
        this.appointmentsController = AppointmentsController.getInstance();
    }

    /**
     * Initializes the controller by setting its own as an action listener for the
     * JMenuItems from the view.
     */
    public void initController() {
        mainView.add(patientsController.getPatientsView());
        //mainView.add(appointmentsController.getAppointmentsView());
        //mainView.add(appointmentsController.getConfirmPatientsView());
        mainView.add(doctorOfficeController.getDoctorOfficeView());
        mainView.add(doctorController.getDoctorView());
    }

    /**
     * Static method to create instance of Singleton class (MainController class).
     *
     * @param mainView the main window.
     * @return the instance of the Main Controller.
     */
    public static MainController getInstance(MainView mainView) {
        if (null == mainController) mainController = new MainController(mainView);
        return mainController;
    }

    public MainView getMainView() {
        return mainView;
    }

    public static MainController getMainController() {
        return mainController;
    }

    public PatientsController getPatientsController() {
        return patientsController;
    }

    public DoctorOfficeController getDoctorOfficeController() {
        return doctorOfficeController;
    }

    public DoctorController getDoctorController() {
        return doctorController;
    }

    public AppointmentsController getAppointmentsController() {
        return appointmentsController;
    }

    public static void setMainController(MainController mainController) {
        MainController.mainController = mainController;
    }

    public void updateAppointmentsComboBoxes() {
        appointmentsController.updateComboBoxes();
    }

    public void updateDoctorComboBox() {
        doctorController.updateComboBox();
    }
}