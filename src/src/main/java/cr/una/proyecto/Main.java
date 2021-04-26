package cr.una.proyecto;

import cr.una.proyecto.frontend.view.MainView;

import java.util.logging.*;

/**
 * @author Felipe Guzman Rodriguez
 * @author Manfred Joel Aguero Campos
 */

public class Main {
    private final static Logger LOGGER = Logger.getLogger("LOGGER");
    private final static Logger LOG_ROOT = Logger.getLogger("LOG_ROOT");

    /**
     * Main function.
     *
     * @param args arguments from console
     */
    public static void main(String[] args) {
        try {

            Handler consoleHandler = new ConsoleHandler();

            SimpleFormatter simpleFormatter = new SimpleFormatter();

            consoleHandler.setFormatter(simpleFormatter);

            LOG_ROOT.addHandler(consoleHandler);

            consoleHandler.setLevel(Level.ALL);

            LOGGER.log(Level.INFO, "Main Initialization");

            try {
                new MainView();
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, e.getMessage());
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }
    }
}
