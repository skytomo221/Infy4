import java.awt.Graphics;
import java.lang.Enum;

public class TestProjrct {
    public static void main(String[] args) {
        GameWindow gw = new GameWindow("Life Game", 800, 600);
        LifeGame l = new LifeGame();
        System.out.println(l.toString());
        l.calc();
        System.out.println(l.toString());
        gw.add(new DrawCanvas(l));// 描画領域の追加
        gw.setVisible(true);
        while (true) {
            l.calc();
            // System.out.println(l.toString());
            gw.add(new DrawCanvas(l));// 描画領域の追加
            gw.setVisible(true);
        }
    }
}
