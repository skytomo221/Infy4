import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

// ウィンドウクラス
public class GameWindow extends JFrame implements MenuListener, ActionListener, MouseListener, MouseMotionListener {
    private static final long serialVersionUID = 1L;

    // ステータスバー
    public JPanel statusBar = new JPanel();
    public JMenuBar menuBar = new JMenuBar();
    Timer timer = new Timer();
    JLabel generationLabel = new JLabel("Generation #1");
    JLabel lifeCountLabel = new JLabel("Count: ?");
    JMenu menu3 = new JMenu("停止");
    JMenuItem menuitem1 = new JMenuItem("新規作成");
    JMenuItem menuitem2 = new JMenuItem("ファイルを開く");
    JMenuItem menuitem3 = new JMenuItem("保存");
    JMenuItem menuitem4 = new JMenuItem("ペン");
    JMenuItem menuitem5 = new JMenuItem("消しゴム");
    JMenuItem menuitem6 = new JMenuItem("操作");
    LifeGame lifeGame = new LifeGame();
    DrawCanvas drawCanvas;
    Boolean pause = false;

    public GameWindow(String title, int width, int height) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        setResizable(true);
        // ステータスバーの設定
        statusBar.setBorder((Border) new BevelBorder(BevelBorder.LOWERED));
        statusBar.setPreferredSize(new Dimension(this.getWidth(), 30));
        statusBar.setLayout((LayoutManager) new BoxLayout(statusBar, BoxLayout.X_AXIS));
        statusBar.add(generationLabel);
        statusBar.add(lifeCountLabel);
        statusBar.setBackground(new Color(30, 30, 30));
        add(statusBar, BorderLayout.SOUTH);
        // メニューバーの設定
        menuBar.setForeground(new Color(200, 200, 200));
        menuBar.setBackground(new Color(30, 30, 30));
        JMenu menu1 = new JMenu("ファイル");
        JMenu menu2 = new JMenu("編集");
        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);
        menu1.setForeground(new Color(200, 200, 200));
        menu1.setFont(new Font("メイリオ", Font.BOLD, 16));
        menu2.setForeground(new Color(200, 200, 200));
        menu2.setFont(new Font("メイリオ", Font.BOLD, 16));
        menu3.setForeground(new Color(200, 200, 200));
        menu3.setFont(new Font("メイリオ", Font.BOLD, 16));
        menu1.setBackground(new Color(30, 30, 30));
        menu2.setBackground(new Color(30, 30, 30));
        menu3.setBackground(new Color(30, 30, 30));
        menuitem1.setForeground(new Color(200, 200, 200));
        menuitem1.setFont(new Font("メイリオ", Font.BOLD, 16));
        menuitem2.setForeground(new Color(200, 200, 200));
        menuitem2.setFont(new Font("メイリオ", Font.BOLD, 16));
        menuitem3.setForeground(new Color(200, 200, 200));
        menuitem3.setFont(new Font("メイリオ", Font.BOLD, 16));
        menuitem4.setForeground(new Color(200, 200, 200));
        menuitem4.setFont(new Font("メイリオ", Font.BOLD, 16));
        menuitem5.setForeground(new Color(200, 200, 200));
        menuitem5.setFont(new Font("メイリオ", Font.BOLD, 16));
        menuitem6.setForeground(new Color(200, 200, 200));
        menuitem6.setFont(new Font("メイリオ", Font.BOLD, 16));
        menuitem1.setBackground(new Color(30, 30, 30));
        menuitem2.setBackground(new Color(30, 30, 30));
        menuitem3.setBackground(new Color(30, 30, 30));
        menuitem4.setBackground(new Color(30, 30, 30));
        menuitem5.setBackground(new Color(30, 30, 30));
        menuitem6.setBackground(new Color(30, 30, 30));
        menuitem1.addActionListener(this);
        menuitem2.addActionListener(this);
        menuitem3.addActionListener(this);
        menuitem4.addActionListener(this);
        menuitem5.addActionListener(this);
        menuitem6.addActionListener(this);
        menu1.add(menuitem1);
        menu1.add(menuitem2);
        menu1.add(menuitem3);
        menu2.add(menuitem4);
        menu2.add(menuitem5);
        menu2.add(menuitem6);
        menu3.addMenuListener(this);
        add(menuBar, BorderLayout.NORTH);
        // ステータスバーのフォントの設定
        generationLabel.setForeground(new Color(200, 200, 200));
        generationLabel.setFont(new Font("Arial", Font.BOLD, 16));
        lifeCountLabel.setForeground(new Color(200, 200, 200));
        lifeCountLabel.setFont(new Font("Arial", Font.BOLD, 16));
        // ライフゲームの設定
        for (int i = 1; i < 500; i++) {
            lifeGame.set.add(new Coordinate(50, 50 + i));
        }
        // ライフゲームの追加
        drawCanvas = new DrawCanvas(lifeGame);
        drawCanvas.addMouseListener(this);
        drawCanvas.addMouseMotionListener(this);
        add(drawCanvas);
        TimerTask task = new TimerTask() {
            public void run() {
                lifeGame.calc();
                drawCanvas.repaint();
                generationLabel.setText("Generation #" + lifeGame.GetGeneration());
                lifeCountLabel.setText("Count: " + lifeGame.set.size());
            }
        };
        timer.schedule(task, 100, 1);
    }

    public void actionPerformed(ActionListener e) {

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuitem1) {
            drawCanvas.lifeGame = lifeGame = new LifeGame();
            generationLabel.setText("Generation #1");
            lifeCountLabel.setText("Count: 0");
            drawCanvas.repaint();
        } else if (e.getSource() == menuitem2) {
            ;
        } else if (e.getSource() == menuitem3) {
            // ライフゲームのファイルを保存
            JFileChooser filechooser = new JFileChooser("");
            int selected = filechooser.showSaveDialog(this);
            if (selected == JFileChooser.APPROVE_OPTION) {
                File file = filechooser.getSelectedFile();
                try (FileOutputStream f = new FileOutputStream(file);
                        BufferedOutputStream b = new BufferedOutputStream(f);
                        ObjectOutputStream out = new ObjectOutputStream(b)) {
                    out.writeObject(lifeGame);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        } else if (e.getSource() == menuitem4) {
            drawCanvas.setEditMode(EditMode.WRITE);
        } else if (e.getSource() == menuitem5) {
            drawCanvas.setEditMode(EditMode.ERASE);
        } else if (e.getSource() == menuitem6) {
            drawCanvas.setEditMode(EditMode.MOVE);
        }
    }

    public void menuCanceled(MenuEvent e) {
    }

    public void menuDeselected(MenuEvent e) {
    }

    public void menuSelected(MenuEvent e) {
        if (timer == null) {
            TimerTask task = new TimerTask() {
                public void run() {
                    lifeGame.calc();
                    drawCanvas.repaint();
                    generationLabel.setText("Generation #" + lifeGame.GetGeneration());
                    lifeCountLabel.setText("Count: " + lifeGame.set.size());
                }
            };
            timer = new Timer();
            timer.schedule(task, 0, 1);
            menu3.setText("停止");
        } else {
            timer.cancel();
            timer = null;
            menu3.setText("再生");
        }
        menu3.setSelected(false);
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        generationLabel.setText("Generation #" + lifeGame.GetGeneration());
        lifeCountLabel.setText("Count: " + lifeGame.set.size());
        if (drawCanvas.getEditMode() == EditMode.WRITE || drawCanvas.getEditMode() == EditMode.ERASE) {
            if (timer != null) {
                timer.cancel();
                timer = null;
                menu3.setText("再生");
                pause = true;
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (pause) {
            TimerTask task = new TimerTask() {
                public void run() {
                    lifeGame.calc();
                    drawCanvas.repaint();
                    generationLabel.setText("Generation #" + lifeGame.GetGeneration());
                    lifeCountLabel.setText("Count: " + lifeGame.set.size());
                }
            };
            timer = new Timer();
            timer.schedule(task, 0, 1);
            menu3.setText("停止");
            pause = false;
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
        generationLabel.setText("Generation #" + lifeGame.GetGeneration());
        lifeCountLabel.setText("Count: " + lifeGame.set.size());
        if (drawCanvas.getEditMode() == EditMode.WRITE || drawCanvas.getEditMode() == EditMode.ERASE) {
            if (timer != null) {
                timer.cancel();
                timer = null;
                menu3.setText("再生");
                pause = true;
            }
        }
    }

    public void mouseMoved(MouseEvent e) {
    }

}
