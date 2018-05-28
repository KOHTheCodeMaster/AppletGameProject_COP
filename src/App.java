import javax.swing.*;

public class App {

    public static void main(String ar[]) {

        System.out.println("Begin.");

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });

        System.out.println("End.");

    }

}
