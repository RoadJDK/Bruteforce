import java.awt.*;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class Program {

    public static void main(String[] args) throws IOException, InterruptedException, AWTException, UnsupportedFlavorException {
        run(args);
    }

    /*
    Start the program
     */
    private static void run(String[] args) throws IOException, InterruptedException, AWTException, UnsupportedFlavorException {
        // New instance of the machine
        Machine machine = new Machine();

        // Start program
        machine.run();
    }
}
