import java.awt.Graphics;
import java.lang.Enum;
import java.util.*;

public class TestProjrct {
    public static void main(String[] args) {
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
                gw.lifeCountLabel.setText("    Count: " + l.set.size());
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, 1000, 1);
    }
}
