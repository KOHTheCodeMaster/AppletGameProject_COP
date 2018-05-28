import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPanel extends JPanel {

    private StartPanelListener startPanelListener;

    StartPanel() {

        GridBagLayout gbL = new GridBagLayout();
        //gbL.columnWeights = new double[] {0.0,Double.MIN_VALUE};
        //gbL.rowWeights =  new double[] {0.0,0.0,Double.MIN_VALUE};
        setLayout(new GridBagLayout());

        Font font = new Font("Verdana", Font.BOLD, 20);

        JLabel label = new JLabel("Applet Ball Game!");
        label.setFont(font);
        GridBagConstraints gbc_lbl = new GridBagConstraints();
        gbc_lbl.anchor = GridBagConstraints.SOUTH;
        gbc_lbl.weightx = 0.5;
        gbc_lbl.insets = new Insets(0, 0, 10, 0);
        gbc_lbl.gridx = 0;
        gbc_lbl.gridy = 0;
        add(label, gbc_lbl);

        JButton btnStartGame = new JButton("Start Game!");
        btnStartGame.setFont(font);
        GridBagConstraints gbc_btn = new GridBagConstraints();
        gbc_btn.anchor = GridBagConstraints.NORTH;
        gbc_btn.weightx = 0.5;
        gbc_btn.insets = new Insets(10, 0, 0, 0);
        gbc_btn.gridx = 0;
        gbc_btn.gridy = 1;
        add(btnStartGame, gbc_btn);

        btnStartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireStartGame();
            }
        });

    }

    private void fireStartGame() {
        if (startPanelListener != null)
            startPanelListener.startGame();
    }

    public void setStartPanelListener(StartPanelListener startPanelListener) {
        this.startPanelListener = startPanelListener;
    }

}
