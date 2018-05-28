import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;

public class Game extends JComponent {

    private Ellipse2D.Double ball = new Ellipse2D.Double(100, 100, 15, 15);
    private RoundRectangle2D.Double bat = new RoundRectangle2D.Double(200, 200, 100, 10, 20, 20);
    private BufferedImage buffer;

    private double speed = 10.0;
    private int xDirectionBall = 1;
    private int yDirectionBall = 1;

    public Game() {

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                bat.x = e.getX() - bat.getWidth() / 2;
                bat.y = e.getY() - bat.getHeight() / 2;
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ball.x = e.getX();
                ball.y = e.getY();
            }
        });

    }

    @Override
    public void paintComponent(Graphics g) {

        if (buffer == null) {
            buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        }
        Graphics2D g2 = (Graphics2D) buffer.getGraphics();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);

        g2.setColor(Color.black);
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setColor(Color.red);
        g2.fill(ball);

        g2.setColor(Color.cyan);
        g2.fill(bat);

        g.drawImage(buffer, 0, 0, null);

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

    @Override
    public void update(Graphics g) {
        paint(g);
    }
}