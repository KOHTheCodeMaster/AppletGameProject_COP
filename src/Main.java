import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {

    private Game game;
    private CardLayout cards;

    Main() {

        //  Basic Frame Settings.
        setDefault();

        //  Time in milliSeconds
        Timer timer = new Timer(17, this);

        game = new Game();
        StartPanel startPanel = new StartPanel();

        cards = new CardLayout();
        setLayout(cards);
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

        setSize(800, 600);
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
