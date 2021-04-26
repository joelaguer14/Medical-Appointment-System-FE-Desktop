//import cr.una.proyecto.frontend.controller.MainController;
//import cr.una.proyecto.frontend.model.DoctorOffice;
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
//public class ConsultingRoomMaintenanceTest {
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
//    public ConsultingRoomMaintenanceTest() {
//
//    }
//
//    /**
//     * Set up the consulting rooms maintenance test.
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
//        cbPanel.comboBox().selectItem(2);
//
//        searchPanel = window.panel("cards").panel("searchPanel");
//        panel = window.panel("cards").panel("form");
//        window.show();
//
//        window.resizeTo(new Dimension(WIDTH, HEIGHT));
//    }
//
//    /**
//     * Test the add button, adding a consulting room with the respective attributes.
//     */
//    @Test
//    public void testAdd() {
//        window.moveTo(new Point(0, 0));
//        panel.textBox("name").enterText("Hospital San Juan de Dios");
//        panel.textBox("phone").enterText("25478000");
//        panel.textBox("days").enterText("Monday");
//        panel.textBox("hours").enterText("1-5");
//        panel.button("addButton").click();
//
//        DoctorOffice consultingRoom = service.getConsultingRooms().get(0);
//        Assert.assertEquals(consultingRoom.getName(), "Hospital San Juan de Dios");
//        Assert.assertEquals(consultingRoom.getPhone(), "25478000");
//        Assert.assertEquals(consultingRoom.getDays(), "MONDAY");
//        Assert.assertEquals(consultingRoom.getHour(), "1-5");
//    }
//
//    /**
//     * Test the delete button by searching the name of the consulting room.
//     */
//    @Test
//    public void testDelete() {
//        window.moveTo(new Point(0, 0));
//        searchPanel.textBox("search").enterText("Hospital San Juan de Dios");
//        panel.button("deleteButton").click();
//        Assert.assertEquals(0, service.getConsultingRooms().size());
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
