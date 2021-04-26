//import cr.una.proyecto.frontend.controller.MainController;
//import cr.una.proyecto.frontend.model.Patient;
//import cr.una.proyecto.frontend.service.Service;
//import cr.una.proyecto.frontend.view.MainView;
//import org.assertj.swing.fixture.FrameFixture;
//import org.assertj.swing.fixture.JPanelFixture;
//import org.junit.AfterClass;
//import org.junit.Assert;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import java.awt.*;
//
///**
// * @author Felipe Guzman Rodriguez
// * @author Manfred Joel Aguero Campos
// */
//
//public class PatientsMaintenanceTest {
//    static final int WIDTH = 800;
//    static final int HEIGHT = 800;
//    private static MainController controller;
//    private static Service service;
//    private static FrameFixture window;
//    private static JPanelFixture panel;
//    private static JPanelFixture searchPanel;
//
//     /**
//     * Default constructor.
//     */
//    public PatientsMaintenanceTest() {
//
//    }
//
//    /**
//     * Set up the patients maintenance test.
//     *
//     */
//    @BeforeClass
//    public static void setUp() {
//        controller = MainController.getInstance(new MainView());
//        service = controller.getService();
//
//        window = new FrameFixture(controller.getMainView());
//
//        JPanelFixture cbPanel = window.panel("cbPane");
//
//        cbPanel.comboBox().selectItem(1);
//
//        searchPanel = window.panel("cards").panel("searchPanel");
//        panel = window.panel("cards").panel("form");
//        window.show();
//
//        window.resizeTo(new Dimension(WIDTH, HEIGHT));
//    }
//
//    /**
//     * Test the add button, adding a patient with
//     * the respective attributes.
//     *
//     */
//    @Test
//    public void testAdd() {
//        window.moveTo(new Point(0, 0));
//        panel.textBox("fullName").enterText("Manfred Joel Aguero Campos");
//        panel.textBox("phone").enterText("88002277");
//        panel.textBox("address").enterText("Lagunilla, Barreal de Heredia, Heredia");
//        panel.textBox("diseases").enterText("Asthmatic");
//        panel.textBox("observations").enterText("Allergic to amoxicillin.");
//        panel.button("addButton").click();
//
//        Patient patient = service.getPatients().get(0);
//        Assert.assertEquals(patient.getFullName(), "Manfred Joel Aguero Campos");
//        Assert.assertEquals(patient.getAddress(), "Lagunilla, Barreal de Heredia, Heredia");
//        Assert.assertEquals(patient.getPhone(), "88002277");
//        Assert.assertEquals(patient.getDiseases(), "Asthmatic" );
//        Assert.assertEquals(patient.getObservations(), "Allergic to amoxicillin.");
//    }
//
//    /**
//     * Test the delete button by searching the name of the patient.
//     *
//     */
//    @Test
//    public void testDelete() {
//        window.moveTo(new Point(0, 0));
//        searchPanel.textBox("search").enterText("Manfred Joel Aguero Campos");
//        panel.button("deleteButton").click();
//        Assert.assertEquals(0, service.getPatients().size());
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