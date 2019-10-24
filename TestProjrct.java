import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.Enum;
import java.util.*;
import javax.swing.JMenuItem;

public class TestProjrct {
    static Timer timer;

    public static void main(String[] args) {
        timer = new Timer();
        GameWindow gw = new GameWindow("Life Game", 800, 600);
        LifeGame l = new LifeGame();
        System.out.println(l.toString());
        l.calc();
        System.out.println(l.toString());
        DrawCanvas dc = new DrawCanvas(l);
        gw.add(dc);// 描画領域の追加
        gw.setVisible(true);
        TimerTask task = new TimerTask() {
            public void run() {
                l.calc();
                // System.out.println(l.toString());
                dc.repaint();
                gw.generationLabel.setText("Generation #" + l.GetGeneration());
                gw.lifeCountLabel.setText("Count: " + l.set.size());
            }
        };
        timer.schedule(task, 0, 1);
        gw.menuitem7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (timer == null) {
                    TimerTask task = new TimerTask() {
                        public void run() {
                            l.calc();
                            // System.out.println(l.toString());
                            dc.repaint();
                            gw.generationLabel.setText("Generation #" + l.GetGeneration());
                            gw.lifeCountLabel.setText("Count: " + l.set.size());
                        }
                    };
                    timer = new Timer();
                    timer.schedule(task, 0, 1);
                }
            }
        });
        gw.menuitem8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                timer.cancel();
                timer = null;
            }
        });
    }
}
