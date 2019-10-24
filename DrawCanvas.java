import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

import java.awt.Image;
import java.awt.Toolkit;

/**
 * ライフゲームをただひたすらに描画します。
 */
public class DrawCanvas extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {
    private static final long serialVersionUID = 1L;
    public LifeGame lifeGame;
    protected int cellSize;
    protected Point mouseStart;
    protected Point padding;

    /**
     * ライフゲームのセルの大きさを指定します。
     * 
     * @param cellSize セルの大きさ
     */
    public void setCellSize(int cellSize) {
        this.cellSize = (cellSize > 1) ? cellSize : 1;
    }

    public int getCellSize() {
        return cellSize;
    }

    public DrawCanvas(LifeGame lifeGame) {
        this.lifeGame = lifeGame;
        cellSize = 1;
        setBackground(new Color(10, 10, 10));
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
        padding = new Point();
        padding.setLocation(0, 0);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GREEN);
        for (Coordinate c : lifeGame.set) {
            g.fillRect(c.x * cellSize + padding.x, c.y * cellSize + padding.y, cellSize, cellSize); // 矩形の塗りつぶし
        }
    }

    public void mouseClicked(MouseEvent e) {
        int btn = e.getButton();
        if (btn == MouseEvent.BUTTON1) {
            cellSize++;
        } else if (btn == MouseEvent.BUTTON2) {
            ;
        } else if (btn == MouseEvent.BUTTON3) {
            setCellSize(cellSize - 1);
        }
        repaint();
    }

    public void mouseEntered(MouseEvent e) {
        // setCursor(Cursor.getPredefinedCursor(Cursor.CUSTOM_CURSOR));
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        mouseStart = e.getPoint();
        // マウスカーソル
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("icon/grabbing2.png");
        Cursor c = toolkit.createCustomCursor(image, new Point(0, 0), "img");
        setCursor(c);
    }

    public void mouseReleased(MouseEvent e) {
        padding.translate(-mouseStart.x + e.getPoint().x, -mouseStart.y + e.getPoint().y);
        // マウスカーソル
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("icon/grab.png");
        Cursor c = toolkit.createCustomCursor(image, new Point(0, 0), "img");
        setCursor(c);
    }

    public void mouseDragged(MouseEvent e) {
        padding.translate(-mouseStart.x + e.getPoint().x, -mouseStart.y + e.getPoint().y);
        mouseStart.setLocation(e.getPoint());
        repaint();
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getWheelRotation() > 0) {
            setCellSize(cellSize - 1);
        } else if (e.getWheelRotation() < 0) {
            setCellSize(cellSize + 1);
        }
        repaint();
    }
}