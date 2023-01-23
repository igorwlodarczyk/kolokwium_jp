import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Figura implements Runnable, ActionListener {
    // wspolny bufor
    protected Graphics2D buffer;
    protected Area area;
    // do wykreslania
    protected Shape shape;
    // przeksztalcenie obiektu
    protected AffineTransform aft;

    // przesuniecie
    private int dx, dy;
    // rozciaganie
    private double sf;
    // kat obrotu
    private double an;
    private AtomicInteger delay = new AtomicInteger();
    private AtomicInteger move = new AtomicInteger();
    private int width;
    private int height;
    private Color clr;

    protected static final Random rand = new Random();
    private double x;
    private double y;

    public Figura(Graphics2D buf, int del, int w, int h) {
        delay.set(del);
        buffer = buf;
        width = w;
        height = h;
        move.set(1);

        dx = 0;
        dy = 1 + rand.nextInt(5);
        sf = 1 + 0.05 * rand.nextDouble();
        an = 0.1 * rand.nextDouble();

        clr = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        // reszta musi być zawarta w realizacji klasy Figure
        // (tworzenie figury i przygotowanie transformacji)

        // Create the sinus wave shape here
        area = new Area();
        x = 0;
        y = 0;
    }

    @Override
    public void run() {
        // przesuniecie na srodek
        aft.translate(100, 100);
        area.transform(aft);
        shape = area;

        while (true) {
            // przygotowanie nastepnego kadru
            shape = nextFrame();
            try {
                Thread.sleep(delay.get());
            } catch (InterruptedException e) {
            }
        }
    }

    protected Shape nextFrame() {
        // zapamietanie na zmiennej tymczasowej
        // aby nie przeszkadzalo w wykreslaniu
        area = new Area();
        aft = new AffineTransform();
        Rectangle bounds = area.getBounds();
        int cx = bounds.x + bounds.width / 2;
        int cy = bounds.y + bounds.height / 2;
        // odbicie
        if(!(bounds.x+dx>=0 && bounds.x+bounds.width+dx<=width && bounds.y+dy>=0 && bounds.y+bounds.height+dy<=height)) {
            if(bounds.y+dy < 0 || bounds.y+dy > 300)
            {
                dy = 0;
            }
            if(bounds.x+dx < 0 || bounds.x+dx > 400){
                dx = 0;
            }
        }
        if(move.get() == 1)
        {
// Move the sinus wave shape here
            x += dx;
            y = height/2 + Math.sin(x) * height/2;
            area.add(new Area(new Rectangle((int)x, (int)y, 1, 1)));
        } return area;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        // wypelnienie obiektu
        buffer.setColor(clr.brighter());
        buffer.fill(shape);
        // wykreslenie ramki
        buffer.setColor(clr.darker());
        buffer.draw(shape);
    }
}