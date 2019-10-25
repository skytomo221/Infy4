import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

/**
 * ライフゲームをただひたすらに描画します。
 */
public class DrawCanvas extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {
    private static final long serialVersionUID = 1L;
    public LifeGame lifeGame;
    protected int cellSize;
    protected Point mouseStart;
    protected Point offsetPoint;
    EditMode editMode = EditMode.MOVE;

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
        offsetPoint = new Point();
        offsetPoint.setLocation(0, 0);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GREEN);
        for (Coordinate c : lifeGame.set) {
            g.fillRect(c.x * cellSize + offsetPoint.x * cellSize, c.y * cellSize + offsetPoint.y * cellSize, cellSize,
                    cellSize); // 矩形の塗りつぶし
        }
    }

    public void mouseClicked(MouseEvent e) {
        int btn = e.getButton();
        if (btn == MouseEvent.BUTTON1) {
            ;
        } else if (btn == MouseEvent.BUTTON2) {
            ;
        } else if (btn == MouseEvent.BUTTON3) {
            ;
        }
        repaint();
    }

    public void mouseEntered(MouseEvent e) {
        // setCursor(Cursor.getPredefinedCursor(Cursor.CUSTOM_CURSOR));
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        switch (editMode) {
        case ERASE:
            lifeGame.set.remove(new Coordinate((int) (offsetPoint.getX() + e.getX()) / cellSize,
                    (int) (offsetPoint.getY() + e.getY()) / cellSize));
            repaint();
            break;
        case WRITE:
            lifeGame.set.add(new Coordinate((int) (offsetPoint.getX() + e.getX()) / cellSize,
                    (int) (offsetPoint.getY() + e.getY()) / cellSize));
            repaint();
            break;
        case MOVE:
            mouseStart = e.getPoint();
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Image image = toolkit.getImage("icon/grabbing3.png");
            Cursor c = toolkit.createCustomCursor(image, new Point(0, 0), "img");
            setCursor(c);
            break;
        default:
            break;
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (editMode == EditMode.MOVE && mouseStart != null) {
            offsetPoint.translate(-mouseStart.x + e.getPoint().x, -mouseStart.y + e.getPoint().y);
            // マウスカーソル
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Image image = toolkit.getImage("icon/grab2.png");
            Cursor c = toolkit.createCustomCursor(image, new Point(0, 0), "img");
            setCursor(c);
        }
    }

    public Coordinate convertToCoordinate(Point p) {
        return new Coordinate(-offsetPoint.x + p.x / cellSize, -offsetPoint.y + p.y / cellSize);
    }

    public void mouseDragged(MouseEvent e) {
        if (editMode == EditMode.MOVE && mouseStart != null) {
            offsetPoint.translate((-mouseStart.x + e.getPoint().x) / cellSize,
                    (-mouseStart.y + e.getPoint().y) / cellSize);
            mouseStart.setLocation(e.getPoint());
            repaint();
        } else if (editMode == EditMode.WRITE) {
            lifeGame.set.add(convertToCoordinate(e.getPoint()));
            repaint();
        }else if (editMode == EditMode.ERASE) {
            lifeGame.set.remove(convertToCoordinate(e.getPoint()));
            repaint();
        }
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getWheelRotation() > 0 && cellSize > 1) {
            offsetPoint.setLocation((int) (((double) offsetPoint.x + e.getPoint().x / cellSize / (cellSize - 1))),
                    (int) (((double) offsetPoint.y + e.getPoint().y / cellSize / (cellSize - 1))));
            setCellSize(cellSize - 1);
        } else if (e.getWheelRotation() < 0) {
            offsetPoint.setLocation((int) (((double) offsetPoint.x - e.getPoint().x / cellSize / (cellSize + 1))),
                    (int) (((double) offsetPoint.y - e.getPoint().y / cellSize / (cellSize + 1))));
            setCellSize(cellSize + 1);
        }
        repaint();
    }
}