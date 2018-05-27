import javax.swing.*;
import java.awt.*;

public class Main extends JApplet {

    @Override
    public void init() {

        setLayout(new BorderLayout());

        add(new Game(), BorderLayout.CENTER);

        System.out.println("Init Method Called.");
    }

    @Override
    public void start() {
        System.out.println("Start Method Called.");
    }

    @Override
    public void stop() {
        System.out.println("Stop Method Called.");
    }

    @Override
    public void destroy() {
        System.out.println("Destroy Method Called.");
    }
}
