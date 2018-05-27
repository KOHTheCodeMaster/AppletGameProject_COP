import javax.swing.*;
import java.awt.*;

public class Game extends JComponent {

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.black);

        g2.fillRect(0, 0, getWidth(), getHeight());

    }
}