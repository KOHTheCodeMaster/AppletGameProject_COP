import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;

public class Game extends JComponent {

    private Ellipse2D.Double ball = new Ellipse2D.Double(100, 100, 15, 15);
    private RoundRectangle2D.Double bat = new RoundRectangle2D.Double(200, 200, 100, 10, 20, 20);
    private BufferedImage buffer;

    private int xDirectionBall = 1;
    private int yDirectionBall = 1;
    private int batSpeed = 10;

    public Game() {

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                //Move Bat accoeding to the Mouse Co-ordinates.
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

        Cursor hiddenCursor = getToolkit().createCustomCursor(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(1, 1), "");
        setCursor(hiddenCursor);

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {

                int key = e.getKeyCode();
                switch (key) {
                    case KeyEvent.VK_UP:
                        bat.y -= batSpeed;
                        break;
                    case KeyEvent.VK_DOWN:
                        bat.y += batSpeed;
                        break;
                    case KeyEvent.VK_LEFT:
                        bat.x -= batSpeed;
                        break;
                    case KeyEvent.VK_RIGHT:
                        bat.x += batSpeed;
                        break;
                }

                return false;
            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                buffer = null;
            }
        });

    }

    @Override
    public void paintComponent(Graphics g) {

        //If Buffer is null, update buffer.
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

    void update() {

        double speed = 10.0;
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