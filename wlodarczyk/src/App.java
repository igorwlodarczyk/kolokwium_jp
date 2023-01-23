import javax.swing.*;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Dimension;
public class App extends JFrame implements ActionListener{
    int a1, f1, p1;
    int a2, f2, p2;
    JTextField texta1 = new JTextField("10");
    JTextField texta2 = new JTextField("50");
    JButton button1 = new JButton("Uruchom wykres 1");
    JButton button2 = new JButton("Uruchom wykres 3");
    JButton button3 = new JButton("Submit all values");
    JButton button5 = new JButton("Uruchom wykres 2");
    CanvaPanel kanwa1;
    CanvaPanel kanwa2;
    CanvaPanel kanwa3;
    JLabel labela1 = new JLabel("Amplituda 1");
    JLabel labela2 = new JLabel("Amplituda 2");

    public App() {
        this.a1 = 10;
        this.a2 = 50;
        this.f1 = 2;
        this.f2 = 100;
        this.p1 = 0;
        this.p2 = 5;

        this.setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int ww = 800, wh = 800;
        setBounds((screen.width-ww)/2, (screen.height-wh)/2, ww, wh);
        this.setVisible(true);
        CanvaPanel kanwa1 = new CanvaPanel();
        kanwa1.setBounds(0,0,400,250);
        this.add(kanwa1);
        CanvaPanel kanwa2 = new CanvaPanel();
        kanwa2.setBounds(400,0,400,250);
        this.add(kanwa2);

        CanvaPanel kanwa3 = new CanvaPanel();
        kanwa3.setBounds(0,400,400,250);
        this.add(kanwa3);

        this.kanwa1 = kanwa1;
        this.kanwa2 = kanwa2;
        this.kanwa3 = kanwa3;

        button1.addActionListener(this);
        button1.setBounds(400, 600, 200, 30);
        this.add(button1);

        button5.addActionListener(this);
        button5.setBounds(400, 650, 200, 30);
        this.add(button5);

        button2.addActionListener(this);
        button2.setBounds(400, 700, 200, 30);
        this.add(button2);

        button3.addActionListener(this);
        button3.setBounds(400, 550, 200, 30);
        this.add(button3);

        texta1.setBounds(400, 350, 150, 30);
        this.add(texta1);
        texta2.setBounds(600, 350, 150, 30);
        this.add(texta2);

        labela1.setBounds(400, 300, 150, 30);
        this.add(labela1);

        labela2.setBounds(600, 300, 150, 30);
        this.add(labela2);





        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                kanwa1.initialize();
                kanwa2.initialize();
                kanwa3.initialize();
            }

        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1){
            kanwa1.addFig(f1, a1, p1);

        }
        if (e.getSource() == button5){
            kanwa2.addFig(f2, a2, p2);

        }

        if (e.getSource() == button2){
            kanwa3.addFig2(f1, a2, p2, f2, a2, p2);
        }

        if (e.getSource() == button3){
            String a1 = texta1.getText();
            String a2 = texta2.getText();
            this.a1 = Integer.valueOf(a1);
            this.a2 = Integer.valueOf(a2);
        }
    }


}
