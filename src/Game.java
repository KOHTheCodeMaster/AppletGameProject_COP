import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;

public class Game extends JComponent {

    private Ellipse2D.Double ball = new Ellipse2D.Double(100, 100, 15, 15);

    private double speed = 10.0;
    private int xDirectionBall = 1;
    private int yDirectionBall = 1;

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);

        g2.setColor(Color.black);
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setColor(Color.red);
        g2.fill(ball);

        g2.setColor(Color.cyan);
        g2.fill(new RoundRectangle2D.Double(200, 200, 100, 10, 20, 20));

    }

    public void update() {

        ball.x += xDirectionBall * speed;
        ball.y += yDirectionBall * speed;

        if (ball.x < 0) {
            xDirectionBall = 1; //  Keep Movement in Right Direction.
            ball.x = 0;
        } else if (ball.x > getWidth() - ball.getBounds().width) {
            xDirectionBall = -1; //  Keep Movement in Left Direction.
            ball.x = getWidth() - ball.getBounds().width;
        }

        if (ball.y < 0) {
            yDirectionBall = 1; //  Keep Movement in Down Direction.
            ball.y = 0;
        } else if (ball.y > getHeight() - ball.getBounds().height) {
            yDirectionBall = -1; //  Keep Movement in Up Direction.
            ball.y = getHeight() - ball.getBounds().height;
        }

        repaint();
    }
}