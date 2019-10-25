import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
public class GameWindow extends JFrame implements ActionListener, MouseListener, MouseMotionListener {
    private static final long serialVersionUID = 1L;

    // ステータスバー
    public JPanel statusBar = new JPanel();
    public JMenuBar menuBar = new JMenuBar();
    Timer timer = new Timer();
    JLabel generationLabel = new JLabel("Generation #1");
    JLabel lifeCountLabel = new JLabel(", Count: ?");
    JLabel mouseCoordinateLabel = new JLabel(", Mouse Coordinate: (0, 0)");
    JMenuItem menuitem1 = new JMenuItem("新規作成");
    JMenuItem menuitem2 = new JMenuItem("ファイルを開く");
    JMenuItem menuitem3 = new JMenuItem("保存");
    JMenuItem menuitem4 = new JMenuItem("ペン");
    JMenuItem menuitem5 = new JMenuItem("消しゴム");
    JMenuItem menuitem6 = new JMenuItem("操作");
    JMenuItem menuitem7 = new JMenuItem("神速 (1000 世代/秒)");
    JMenuItem menuitem8 = new JMenuItem("速い (10 世代/秒)");
    JMenuItem menuitem9 = new JMenuItem("普通 (2 世代/秒)");
    JMenuItem menuitem10 = new JMenuItem("ゆっくり (1 世代/秒)");
    JMenuItem menuitem11 = new JMenuItem("停止 (0 世代/秒)");
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
        statusBar.add(mouseCoordinateLabel);
        statusBar.setBackground(new Color(30, 30, 30));
        add(statusBar, BorderLayout.SOUTH);
        // メニューバーの設定
        menuBar.setForeground(new Color(200, 200, 200));
        menuBar.setBackground(new Color(30, 30, 30));
        JMenu menu1 = new JMenu("ファイル");
        JMenu menu2 = new JMenu("編集");
        JMenu menu3 = new JMenu("再生/停止");
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
        menuitem7.setForeground(new Color(200, 200, 200));
        menuitem7.setFont(new Font("メイリオ", Font.BOLD, 16));
        menuitem8.setForeground(new Color(200, 200, 200));
        menuitem8.setFont(new Font("メイリオ", Font.BOLD, 16));
        menuitem9.setForeground(new Color(200, 200, 200));
        menuitem9.setFont(new Font("メイリオ", Font.BOLD, 16));
        menuitem10.setForeground(new Color(200, 200, 200));
        menuitem10.setFont(new Font("メイリオ", Font.BOLD, 16));
        menuitem11.setForeground(new Color(200, 200, 200));
        menuitem11.setFont(new Font("メイリオ", Font.BOLD, 16));
        menuitem1.setBackground(new Color(30, 30, 30));
        menuitem2.setBackground(new Color(30, 30, 30));
        menuitem3.setBackground(new Color(30, 30, 30));
        menuitem4.setBackground(new Color(30, 30, 30));
        menuitem5.setBackground(new Color(30, 30, 30));
        menuitem6.setBackground(new Color(30, 30, 30));
        menuitem7.setBackground(new Color(30, 30, 30));
        menuitem8.setBackground(new Color(30, 30, 30));
        menuitem9.setBackground(new Color(30, 30, 30));
        menuitem10.setBackground(new Color(30, 30, 30));
        menuitem11.setBackground(new Color(30, 30, 30));
        menuitem1.addActionListener(this);
        menuitem2.addActionListener(this);
        menuitem3.addActionListener(this);
        menuitem4.addActionListener(this);
        menuitem5.addActionListener(this);
        menuitem6.addActionListener(this);
        menuitem7.addActionListener(this);
        menuitem8.addActionListener(this);
        menuitem9.addActionListener(this);
        menuitem10.addActionListener(this);
        menuitem11.addActionListener(this);
        menu1.add(menuitem1);
        menu1.add(menuitem2);
        menu1.add(menuitem3);
        menu2.add(menuitem4);
        menu2.add(menuitem5);
        menu2.add(menuitem6);
        menu3.add(menuitem7);
        menu3.add(menuitem8);
        menu3.add(menuitem9);
        menu3.add(menuitem10);
        menu3.add(menuitem11);
        add(menuBar, BorderLayout.NORTH);
        // ステータスバーのフォントの設定
        generationLabel.setForeground(new Color(200, 200, 200));
        generationLabel.setFont(new Font("Arial", Font.BOLD, 16));
        lifeCountLabel.setForeground(new Color(200, 200, 200));
        lifeCountLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mouseCoordinateLabel.setForeground(new Color(200, 200, 200));
        mouseCoordinateLabel.setFont(new Font("Arial", Font.BOLD, 16));
        // ライフゲームの設定
        for (int i = 1; i < 500; i++) {
            lifeGame.set.add(new Coordinate(0, i));
        }
        // ライフゲームの追加
        drawCanvas = new DrawCanvas(lifeGame);
        drawCanvas.addMouseListener(this);
        drawCanvas.addMouseMotionListener(this);
        drawCanvas.offsetOptimization();
        add(drawCanvas);
        TimerTask task = new TimerTask() {
            public void run() {
                lifeGame.calc();
                drawCanvas.repaint();
                generationLabel.setText("Generation #" + lifeGame.GetGeneration());
                lifeCountLabel.setText(", Count: " + lifeGame.set.size());
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
            lifeCountLabel.setText(", Count: 0");
            drawCanvas.repaint();
            drawCanvas.offsetPoint = new Point(0, 0);
            drawCanvas.setCellSize(40);
        } else if (e.getSource() == menuitem2) {
            // ライフゲームのファイルを開く
            JFileChooser filechooser = new JFileChooser("data");
            int selected = filechooser.showOpenDialog(this);
            if (selected == JFileChooser.APPROVE_OPTION) {
                File file = filechooser.getSelectedFile();
                try (FileInputStream f = new FileInputStream(file);
                        BufferedInputStream b = new BufferedInputStream(f);
                        ObjectInputStream in = new ObjectInputStream(b)) {
                    drawCanvas.lifeGame = lifeGame = (LifeGame) in.readObject();
                    generationLabel.setText("Generation #" + lifeGame.GetGeneration());
                    lifeCountLabel.setText(", Count: " + lifeGame.set.size());
                    drawCanvas.offsetOptimization();
                    drawCanvas.repaint();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                } catch (ClassNotFoundException cnfe) {
                    cnfe.printStackTrace();
                }
            }
        } else if (e.getSource() == menuitem3) {
            // ライフゲームのファイルを保存
            JFileChooser filechooser = new JFileChooser("data");
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
        } else if (e.getSource() == menuitem7) {
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
            TimerTask task = new TimerTask() {
                public void run() {
                    lifeGame.calc();
                    drawCanvas.repaint();
                    generationLabel.setText("Generation #" + lifeGame.GetGeneration());
                    lifeCountLabel.setText(", Count: " + lifeGame.set.size());
                }
            };
            timer = new Timer();
            timer.schedule(task, 10, 1);
        } else if (e.getSource() == menuitem8) {
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
            TimerTask task = new TimerTask() {
                public void run() {
                    lifeGame.calc();
                    drawCanvas.repaint();
                    generationLabel.setText("Generation #" + lifeGame.GetGeneration());
                    lifeCountLabel.setText(", Count: " + lifeGame.set.size());
                }
            };
            timer = new Timer();
            timer.schedule(task, 100, 100);
        } else if (e.getSource() == menuitem9) {
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
            TimerTask task = new TimerTask() {
                public void run() {
                    lifeGame.calc();
                    drawCanvas.repaint();
                    generationLabel.setText("Generation #" + lifeGame.GetGeneration());
                    lifeCountLabel.setText(", Count: " + lifeGame.set.size());
                }
            };
            timer = new Timer();
            timer.schedule(task, 500, 500);
        } else if (e.getSource() == menuitem10) {
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
            TimerTask task = new TimerTask() {
                public void run() {
                    lifeGame.calc();
                    drawCanvas.repaint();
                    generationLabel.setText("Generation #" + lifeGame.GetGeneration());
                    lifeCountLabel.setText(", Count: " + lifeGame.set.size());
                }
            };
            timer = new Timer();
            timer.schedule(task, 1000, 1000);
        } else if (e.getSource() == menuitem11) {
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        generationLabel.setText("Generation #" + lifeGame.GetGeneration());
        lifeCountLabel.setText(", Count: " + lifeGame.set.size());
        if (drawCanvas.getEditMode() == EditMode.WRITE || drawCanvas.getEditMode() == EditMode.ERASE) {
            if (timer != null) {
                timer.cancel();
                timer = null;
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
                    lifeCountLabel.setText(", Count: " + lifeGame.set.size());
                }
            };
            timer = new Timer();
            timer.schedule(task, 0, 1);
            pause = false;
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
        generationLabel.setText("Generation #" + lifeGame.GetGeneration());
        lifeCountLabel.setText(", Count: " + lifeGame.set.size());
        if (drawCanvas.getEditMode() == EditMode.WRITE || drawCanvas.getEditMode() == EditMode.ERASE) {
            if (timer != null) {
                timer.cancel();
                timer = null;
                pause = true;
            }
        }
    }

    public void mouseMoved(MouseEvent e) {
        mouseCoordinateLabel.setText(", Mouse Coordinate: " + drawCanvas.convertToCoordinate(e.getPoint()).toString());
    }

}
