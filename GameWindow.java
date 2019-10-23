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

// ウィンドウクラス
public class GameWindow extends JFrame {
    private static final long serialVersionUID = 1L;

    // ステータスバー
    public JPanel statusBar = new JPanel();
    JLabel generationLabel = new JLabel("Generation #1");
    JLabel lifeCountLabel = new JLabel("Count: ?");

    public GameWindow(String title, int width, int height) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        setResizable(true);
        createStatusBar();
        generationLabel.setForeground(new Color(200, 200, 200));
        generationLabel.setFont(new Font("Arial", Font.BOLD, 16));
        lifeCountLabel.setForeground(new Color(200, 200, 200));
        lifeCountLabel.setFont(new Font("Arial", Font.BOLD, 16));
    }

    private void createStatusBar() {
        statusBar.setBorder((Border) new BevelBorder(BevelBorder.LOWERED));
        statusBar.setPreferredSize(new Dimension(this.getWidth(), 30));
        statusBar.setLayout((LayoutManager) new BoxLayout(statusBar, BoxLayout.X_AXIS));
        statusBar.add(generationLabel);
        statusBar.add(lifeCountLabel);
        statusBar.setBackground(new Color(30, 30, 30));
        add(statusBar, BorderLayout.SOUTH);
    }
}
