import javax.swing.*;

/*Igor Wlodarczyk 252253*/

public class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new App();
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        });

    }
}