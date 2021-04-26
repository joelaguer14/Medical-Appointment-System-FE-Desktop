//import cr.una.proyecto.Constants;
//import cr.una.proyecto.frontend.controller.MainController;
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
//import java.awt.event.InputEvent;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.util.Calendar;
//import java.util.Date;
//
//public class ConfirmPatientsTest {
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
//    public ConfirmPatientsTest() {
//
//    }
//
//    /**
//     * Set up the Appointments maintenance test.
//     */
//    @SuppressWarnings("unused")
//    @BeforeClass
//    public static void setUp() {
//        controller = MainController.getInstance(new MainView());
//        service = controller.getService();
//
//        //noinspection deprecation
//        service.newPatient("Felipe Guzman", "Pozos, Santa Ana, San José", "88550044",
//                "Rhinitis", "Back Injury", new Date(2000, Calendar.SEPTEMBER, 2));
//        try {
//            service.newConsultingRoom("Hospital San Juan de Dios", "TUESDAY", "8-9", "25478000");
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//
//        LocalDateTime dateTime = LocalDateTime.of(LocalDate.of(2020, 4, 7), LocalTime.of(7, 0));
//
//        try {
//            service.newAppointment(service.getPatients().get(0).getFullName(), service.getConsultingRooms().get(0).getName(), dateTime);
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//
//        Object[][] appointmentsMatrix = service.appointmentsToMatrix();
//        controller.getAppointmentsController().getAppointmentsTableModel().setDataVector(appointmentsMatrix, Constants.APPOINTMENTS_TABLE_HEADER);
//
//        window = new FrameFixture(controller.getMainView());
//
//        JPanelFixture cbPanel = window.panel("cbPane");
//
//        cbPanel.comboBox().selectItem(4);
//
//        panel = window.panel("cards").panel("form");
//
//        searchPanel = window.panel("cards").panel("searchPanel");
//        //searchPanel.textBox("searchTextField").enterText("Felipe Guzman");
//        //panel.button("searchButton").click();
//
//
//        //window.resizeTo(new Dimension(WIDTH, HEIGHT));
//        window.show();
//    }
//
//    /**
//     * Test confirm button by using a robot to move the mouse
//     * and press the patient appointment status.
//     *
//     */
//    @SuppressWarnings("deprecation")
//    @Test
//    public void testConfirm() {
//        window.moveTo(new Point(0, 0));
//        try {
//            Robot robot  = new Robot();
//            robot.mouseMove(100, 160);
//            robot.mousePress(InputEvent.BUTTON1_MASK);
//            panel.button("confirmButton").click();
//            Assert.assertTrue(service.getAppointments().get(0).isConfirmed());
//        } catch (AWTException ex) {
//            ex.printStackTrace();
//        }
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
