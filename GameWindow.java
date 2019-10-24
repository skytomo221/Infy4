import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import java.awt.Graphics;
import java.awt.LayoutManager;
import javax.swing.border.BevelBorder;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.util.Timer;

// ウィンドウクラス
public class GameWindow extends JFrame {
    private static final long serialVersionUID = 1L;

    // ステータスバー
    public JPanel statusBar = new JPanel();
    public JMenuBar menuBar = new JMenuBar();
    public Timer timer;
    JLabel generationLabel = new JLabel("Generation #1");
    JLabel lifeCountLabel = new JLabel("Count: ?");
    JMenuItem menuitem7 = new JMenuItem("再生");
    JMenuItem menuitem8 = new JMenuItem("停止");

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
        JMenu menu3 = new JMenu("再生");
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
        JMenuItem menuitem1 = new JMenuItem("新規作成");
        JMenuItem menuitem2 = new JMenuItem("ファイルを開く");
        JMenuItem menuitem3 = new JMenuItem("保存");
        JMenuItem menuitem4 = new JMenuItem("ペン");
        JMenuItem menuitem5 = new JMenuItem("消しゴム");
        JMenuItem menuitem6 = new JMenuItem("操作");
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
        menuitem1.setBackground(new Color(30, 30, 30));
        menuitem2.setBackground(new Color(30, 30, 30));
        menuitem3.setBackground(new Color(30, 30, 30));
        menuitem4.setBackground(new Color(30, 30, 30));
        menuitem5.setBackground(new Color(30, 30, 30));
        menuitem6.setBackground(new Color(30, 30, 30));
        menuitem7.setBackground(new Color(30, 30, 30));
        menuitem8.setBackground(new Color(30, 30, 30));

        menu1.add(menuitem1);
        menu1.add(menuitem2);
        menu1.add(menuitem3);
        menu2.add(menuitem4);
        menu2.add(menuitem5);
        menu2.add(menuitem6);
        menu3.add(menuitem7);
        menu3.add(menuitem8);
        add(menuBar, BorderLayout.NORTH);
        // ステータスバーのフォントの設定
        generationLabel.setForeground(new Color(200, 200, 200));
        generationLabel.setFont(new Font("Arial", Font.BOLD, 16));
        lifeCountLabel.setForeground(new Color(200, 200, 200));
        lifeCountLabel.setFont(new Font("Arial", Font.BOLD, 16));
    }

}
