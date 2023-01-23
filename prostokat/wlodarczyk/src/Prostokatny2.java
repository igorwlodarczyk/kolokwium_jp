import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.Polygon;
import java.util.concurrent.atomic.AtomicInteger;

public class Prostokatny2 implements Runnable, ActionListener {
    // protected int delay;
    protected Graphics2D buffer;
    protected int xPos;
    protected int yPos;

    protected int frequency;
    protected int amplitude;
    protected int phase;
    protected int frequency2;
    protected int amplitude2;
    protected int phase2;
    protected double degree;
    protected Color color;


    protected static final Random rand = new Random();

    Prostokatny2(){}
    Prostokatny2(Graphics2D buf, int f, int a, int p, int f2, int a2, int p2){

        xPos = 0;
        yPos = 110;
        this.frequency = f;
        this.amplitude = a;
        this.phase = p;
        this.frequency2 = f2;
        this.amplitude2 = a2;
        this.phase2 = p2;
        degree = phase;



        buffer = buf;

        color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
    }

    @Override
    public void run() {

        while (true) {
            // przygotowanie nastepnego kadru
            //shape = nextFrame();
            try {
                Thread.sleep(16,67);
            } catch (InterruptedException e) {
            }
        }
    }

    protected Graphics2D nextFrame(){
        buffer.drawLine(0, 110, 400, 110);
        buffer.drawLine(200,0,200,400);

        Polygon sin = new Polygon();
        degree = degree + 0.01*Math.PI;
        degree = degree % (2*Math.PI);
        double degreeS = 0;
        for (int i = -90; i <= 90; i++) {
            degreeS = degree + i * (2*Math.PI);
            degreeS = degreeS % 2*Math.PI;
            int y = (int) (amplitude * Math.signum(Math.sin(degreeS))  + (int) (amplitude2 * Math.signum(Math.sin(degreeS)))+ yPos);
            sin.addPoint(200 + i, y);
        }

        buffer.drawPolyline(sin.xpoints, sin.ypoints, sin.npoints);
        return buffer;
    }



    @Override
    public void actionPerformed(ActionEvent evt) {
        buffer = nextFrame();
    }
}