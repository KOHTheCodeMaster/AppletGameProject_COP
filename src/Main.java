import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {

    private Timer timer;
    private Game game;
    private StartPanel startPanel;

    private CardLayout cards;

    public Main() {

        setDefault();
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

        timer.start();

        System.out.println("Main Constructor Called.");
    }

    private void setDefault() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //System.out.println("Timer is Running!");
        game.update();

    }
}
