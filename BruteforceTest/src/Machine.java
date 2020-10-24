import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Machine {

    /**
     * Start program
     * @throws IOException
     * @throws AWTException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     */
    public void run() throws IOException, AWTException, InterruptedException, UnsupportedFlavorException {
        // Countdown
        System.out.println("3");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("2");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("1");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("GO");
        TimeUnit.SECONDS.sleep(1);

        // Test passwords
        // Filepath is the darkc0de list
        testCodes("src/list/darkc0de.txt");
    }

    /**
     * @param filePath
     * @throws IOException
     * @throws AWTException
     * @throws UnsupportedFlavorException
     */
    private void testCodes(String filePath) throws IOException, AWTException, UnsupportedFlavorException {
        var reader = new BufferedReader(new FileReader(filePath));
        var robot = new Robot();

        String line;

        // If no passwords on the list are left, exit program
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                System.out.println("Sadly no passwords are left");
                System.exit(0);
            }

            // Paste the copied password
            paste(robot, copyToClipboard(line));

            // Try again
            reset(robot);
        }
    }

    /**
     * Copy the password to the clipboard
     * @param line
     * @return password
     * @throws IOException
     * @throws UnsupportedFlavorException
     */
    private String copyToClipboard(String line) throws IOException, UnsupportedFlavorException {
        StringSelection selection = new StringSelection(line);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
        Transferable transfer = clipboard.getContents(this);

        // Show current password in the Console and return it
        System.out.println((String) transfer.getTransferData(DataFlavor.stringFlavor));
        return (String) transfer.getTransferData(DataFlavor.stringFlavor);
    }

    /**
     * Paste the password in the field
     * @param robot
     * @param content
     */
    private void paste(Robot robot, String content) {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
    }

    /**
     * Go to next try
     * @param robot
     */
    private void reset(Robot robot) {

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(3000);

        for(var i = 0; i < 3; i++) {
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
        }
    }
}
