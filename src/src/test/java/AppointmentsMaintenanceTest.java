//import cr.una.proyecto.frontend.controller.MainController;
//import cr.una.proyecto.frontend.model.Appointment;
//import cr.una.proyecto.frontend.service.Service;
//import cr.una.proyecto.frontend.view.MainView;
//import org.assertj.swing.fixture.FrameFixture;
//import org.assertj.swing.fixture.JPanelFixture;
//import org.junit.AfterClass;
//import org.junit.Assert;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import javax.swing.*;
//import java.awt.*;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.util.Calendar;
//import java.util.Date;
//
//public class AppointmentsMaintenanceTest {
//    static final int WIDTH = 800;
//    static final int HEIGHT = 800;
//    private static MainController controller;
//    private static Service service;
//    private static FrameFixture window;
//    private static JPanelFixture panel;
//    private static JPanelFixture searchPanel;
//
//    /**
//     * Default constructor.
//     */
//    public AppointmentsMaintenanceTest() {
//
//    }
//
//    /**
//     * Set up the Appointments maintenance test.
//     */
//    @SuppressWarnings("deprecation")
//    @BeforeClass
//    public static void setUp() {
//        controller = MainController.getInstance(new MainView());
//        service = controller.getService();
//
//        window = new FrameFixture(controller.getMainView());
//
//        JPanelFixture cbPanel = window.panel("cbPane");
//
//        cbPanel.comboBox().selectItem(3);
//
//        searchPanel = window.panel("cards").panel("searchPanel");
//        panel = window.panel("cards").panel("form");
//        window.show();
//        service.newPatient("Felipe Guzman", "Pozos, Santa Ana, San José", "88550044",
//                "Rhinitis", "Back Injury", new Date(2000, Calendar.SEPTEMBER, 2));
//        try {
//            service.newConsultingRoom("Hospital San Juan de Dios", "TUESDAY", "8-9", "25478000");
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//
//
//
//        window.resizeTo(new Dimension(WIDTH, HEIGHT));
//    }
//
//    /**
//     * Test the add button, adding an appointment with the respective attributes.
//     */
//    @Test
//    public void testAdd() {
//        window.moveTo(new Point(0, 0));
//        panel.textBox("name").enterText("Felipe Guzman");
//        panel.textBox("consultingRoom").enterText("Hospital San Juan de Dios");
//        LocalDateTime dateTime = LocalDateTime.of(LocalDate.of(2020, 4, 7), LocalTime.of(7, 0));
//        controller.getAppointmentsController().getAppointmentsView().getDateTimePicker().setDateTime(dateTime);
//        panel.button("addButton").click();
//
//        Appointment appointment = service.getAppointments().get(0);
//        Assert.assertEquals(appointment.getPatient().getFullName(), "Felipe Guzman");
//        Assert.assertEquals(appointment.getDoctorOffice().getName(), "Hospital San Juan de Dios");
//        Assert.assertEquals(appointment.getDate().toString(), dateTime.toString());
//    }
//
//    /**
//     * Test the delete button by searching the name of the patient.
//     */
//    @Test
//    public void testDelete() {
//        window.moveTo(new Point(0, 0));
//        searchPanel.textBox("search").enterText("Felipe Guzman");
//        panel.button("deleteButton").click();
//        Assert.assertEquals(service.getAppointments().size(), 0);
//    }
//
//    /**
//     * Cleans up any used resources (keyboard, mouse, open windows and ScreenLock)
//     * used by this robot.
//     */
//    @AfterClass
//    public static void tearDown() {
//        window.cleanUp();
//    }
//}
