import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.Polygon;
import java.util.concurrent.atomic.AtomicInteger;

public class Prostokatny implements Runnable, ActionListener {
    // protected int delay;
    protected Graphics2D buffer;
    protected int xPos;
    protected int yPos;

    protected int frequency;
    protected int amplitude;
    protected int phase;
    protected double degree;
    protected Color color;
    private AtomicInteger frequency1 = new AtomicInteger();
    private AtomicInteger amplitude1 = new AtomicInteger();
    private AtomicInteger phase1 = new AtomicInteger();

    protected static final Random rand = new Random();

    Prostokatny(){}
    Prostokatny(Graphics2D buf, int f, int a, int p){

        xPos = 0;
        yPos = 110;
        frequency1.set(f);
        amplitude1.set(a);
        phase1.set(p);
        degree = phase1.get();



        buffer = buf;

        color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
    }

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(16,67);
            } catch (InterruptedException e) {
            }
        }
    }

    protected Graphics2D nextFrame(){
        buffer.drawLine(0, 110, 400, 110);
        buffer.drawLine(200,0,200,400);
        amplitude = amplitude1.get();
        phase = phase1.get();
        frequency = frequency1.get();

        Polygon sin = new Polygon();
        degree = degree + 0.01*Math.PI;
        degree = degree % (2*Math.PI);
        double degreeS = 0;
        for (int i = -90; i <= 90; i++) {
            degreeS = degree + i * (2*Math.PI);
            degreeS = degreeS % 2*Math.PI;
            int y = (int) (amplitude * Math.signum(Math.sin(degreeS)) + yPos);
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