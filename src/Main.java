import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JApplet implements ActionListener {

    private Timer timer;
    private Game game;
    private StartPanel startPanel;

    private CardLayout cards;

    @Override
    public void init() {

        cards = new CardLayout();

        //  Time in milliSeconds
        timer = new Timer(17, this);
        game = new Game();
        startPanel = new StartPanel();
        setLayout(cards);
        setSize(800, 600);
        add(startPanel, "startPanel");
        add(game, "game");

        startPanel.setStartPanelListener(new StartPanelListener() {
            @Override
            public void startGame() {
                cards.show(Main.this.getContentPane(), "game");
            }
        });

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

        //System.out.println("Timer is Running!");
        game.update();

    }
}
