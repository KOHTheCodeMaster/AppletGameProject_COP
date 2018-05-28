import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JApplet implements ActionListener {

    private Timer timer;
    private Game game;

    @Override
    public void init() {
        //  Time in milliSeconds
        timer = new Timer(20, this);
        game = new Game();
        setLayout(new BorderLayout());
        setSize(600, 500);
        add(game, BorderLayout.CENTER);

        System.out.println("Init Method Called.");
    }

    @Override
    public void start() {
        System.out.println("Start Method Called.");

        timer.start();
    }

    @Override
    public void stop() {
        System.out.println("Stop Method Called.");
        timer.stop();
    }

    @Override
    public void destroy() {
        System.out.println("Destroy Method Called.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("Timer is Running!");
        game.update();

    }
}
