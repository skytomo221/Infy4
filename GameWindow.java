import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;

// ウィンドウクラス
public class GameWindow extends JFrame {
    private static final long serialVersionUID = 1L;

    public GameWindow(String title, int width, int height) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        setResizable(true);
    }
}
