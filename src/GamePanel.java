import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
    private InputHandler inputHandler = new InputHandler();
    private GameMap gameMap = new GameMap();
    private Player player = new Player(50, 50);
    
    private long lastTime = System.nanoTime();
    private double deltaTime = 0;
    private boolean gameWon = false;

    public GamePanel() {
        setFocusable(true);
        addKeyListener(inputHandler);
        requestFocusInWindow();
        
        new javax.swing.Timer(16, e -> {
            update();
            repaint();
        }).start();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // ★ 背景を白でクリア
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        if (gameWon) {
            int winWidth = getWidth();
            int winHeight = getHeight();
            int centerX = winWidth / 2;
            int centerY = winHeight / 2;
            
            // 背景（中央）
            g.setColor(new Color(0, 0, 0, 220));
            g.fillRoundRect(centerX - 160, centerY - 110, 320, 220, 25, 25);
            
            // フォント設定
            g.setFont(new Font("メイリオ", Font.BOLD, 52));
            g.setColor(Color.YELLOW);
            
            // 「クリア！」の正確な中央
            String clearText = "クリア！";
            int clearWidth = g.getFontMetrics().stringWidth(clearText);
            g.drawString(clearText, centerX - clearWidth / 2, centerY - 30);
            
            // 下のメッセージ
            g.setFont(new Font("メイリオ", Font.BOLD, 28));
            g.setColor(Color.WHITE);
            
            String msg1 = "おめでとうございます！";
            int msg1Width = g.getFontMetrics().stringWidth(msg1);
            g.drawString(msg1, centerX - msg1Width / 2, centerY + 20);
            
            String msg2 = "完璧なクリア！";
            int msg2Width = g.getFontMetrics().stringWidth(msg2);
            g.drawString(msg2, centerX - msg2Width / 2, centerY + 55);
            
            return;
        }
        
        // 通常時の描画
        gameMap.draw(g);
        player.draw(g);
        
        // デバッグ情報
        g.setColor(Color.BLACK);
        g.setFont(new Font("メイリオ", Font.PLAIN, 16));
        int tileX = player.getCenterX() / 50;
        int tileY = player.getCenterY() / 50;
        g.drawString("位置: (" + tileX + "," + tileY + ")", 10, 20);
    }
    
    private void update() {
        if (gameWon) return;
        
        long now = System.nanoTime();
        deltaTime = (now - lastTime) / 1_000_000_000.0;
        lastTime = now;
        
        player.move(inputHandler, gameMap, deltaTime);
        
        // ゴール判定
        int tileX = player.getCenterX() / 50;
        int tileY = player.getCenterY() / 50;
        if (gameMap.isGoalTile(tileY, tileX)) {
            gameWon = true;
            System.out.println("ゴール到達！");
        }
    }
}