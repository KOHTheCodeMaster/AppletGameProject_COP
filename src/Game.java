import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;

public class Game extends JComponent {

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);

        g2.setColor(Color.black);
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setColor(Color.red);
        g2.fill(new Ellipse2D.Double(100, 100, 15, 15));

        g2.setColor(Color.cyan);
        g2.fill(new RoundRectangle2D.Double(200, 200, 100, 10, 20, 20));

    }
}