import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * ライフゲームをただひたすらに描画します。
 */
public class DrawCanvas extends JPanel {
    private static final long serialVersionUID = 1L;
    public LifeGame lifeGame;
    protected int size;

    /**
     * ライフゲームのセルの大きさを指定します。
     * 
     * @param size セルの大きさ
     */
    public void setSize(int size) {
        this.size = (size > 1) ? size : 1;
    }

    public int getSize() {
        return size;
    }

    public DrawCanvas(LifeGame lifeGame) {
        this.lifeGame = lifeGame;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Coordinate c : lifeGame.set) {
            g.fillRect(c.x * 3, c.y * 3, 3, 3); // 矩形の塗りつぶし
        }
    }
}