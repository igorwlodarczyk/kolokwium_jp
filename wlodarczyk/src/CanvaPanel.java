import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JPanel;
import javax.swing.Timer;

public class CanvaPanel extends JPanel implements ActionListener {
    /**
     *
     */
    private static final long serialVersionUID = 1L;



    // bufor
    Image image;
    // wykreslacz ekranowy
    Graphics2D device;
    // wykreslacz bufora
    Graphics2D buffer;
    Prostokatny fig1;
    Prostokatny2 fig2;

    private int delay = 30;

    private Timer timer;


    public CanvaPanel() {
        super();
        setBackground(Color.WHITE);
        timer = new Timer(delay, this);
        this.setVisible(true);
    }

    public void initialize() {
        int width = getWidth();
        int height = getHeight();
        timer.start();
        image = createImage(width, height);
        buffer = (Graphics2D) image.getGraphics();
        buffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        device = (Graphics2D) getGraphics();
        device.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    void addFig(int f, int a, int p) {
        this.fig1 = new Prostokatny(buffer, f, a, p);
        timer.addActionListener(fig1);
        Thread thread = new Thread(fig1);
        thread.start();
    }

    void addFig2(int f, int a, int p, int f2, int a2, int p2) {
        this.fig2 = new Prostokatny2(buffer, f, a, p, f2, a2, p2);
        timer.addActionListener(fig2);
        Thread thread = new Thread(fig2);
        thread.start();
    }






    @Override
    public void actionPerformed(ActionEvent e) {
        device.drawImage(image, 0, 0, null);
        buffer.clearRect(0, 0, getWidth(), getHeight());
    }


}
