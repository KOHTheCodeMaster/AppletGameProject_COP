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
    private RoundRectangle2D.Double box1 = new RoundRectangle2D.Double(200, 300, 100, 10, 20, 20);
    private BufferedImage buffer;

    private int xDirectionBall = 1;
    private int yDirectionBall = 1;
    private int batSpeed = 10;

    private boolean isIntersecting = true;

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

        drawUI(g2);

        g.drawImage(buffer, 0, 0, null);

    }

    private void drawUI(Graphics2D g2) {

        //  Set Background Color to Black.
        g2.setColor(Color.black);
        g2.fillRect(0, 0, getWidth(), getHeight());

        //  Set Ball Color to Red.
        g2.setColor(Color.red);
        g2.fill(ball);

        //  Set Bat Color to Cyan.
        g2.setColor(Color.cyan);
        g2.fill(bat);

        boolean drawOnceOnly = true;
        if (drawOnceOnly) {

            //  Draw White Box of Game.
            g2.setColor(Color.white);
            g2.drawRect(getWidth() / 6, 20, getWidth() - 2 * getWidth() / 6, getHeight() - 40);

            drawOnceOnly = false;

        }
    }

    void update() {

        moveBall();

        checkBallOutOfScope();
        checkBatOutOfScope();
        checkIntersection();

        repaint();
    }

    private void checkBatOutOfScope() {

        if (bat.x < 0) {
            bat.x = 0;
        } else if (bat.x > getWidth() - bat.getBounds2D().getWidth()) {
            bat.x = getWidth() - bat.getBounds2D().getWidth();
        }

        if (bat.y < 2) {
            bat.y = 2;
        } else if (bat.y > getHeight() - bat.getBounds2D().getHeight() - 2) {
            bat.y = getHeight() - bat.getBounds2D().getHeight() - 2;
        }

    }

    private void moveBall() {

        double speed = 10.0;
        ball.x += xDirectionBall * speed;
        ball.y += yDirectionBall * speed;

    }

    private void checkBallOutOfScope() {

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

    }

    private void checkIntersection() {

        if (ball.intersects(bat.getBounds2D())) {
            if (isIntersecting) {
                xDirectionBall = -xDirectionBall;
                yDirectionBall = -yDirectionBall;

                isIntersecting = false;
            }
        } else
            isIntersecting = true;

    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }
}