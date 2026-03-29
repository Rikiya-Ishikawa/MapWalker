import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;

public class Player {
    private double x, y;
    private BufferedImage image;
    private int speed = 200;  // 速度調整

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        try {
            Path imagePath = Path.of("ref/pipo-charachip029a.png");
            BufferedImage fullSheet = ImageIO.read(imagePath.toFile());
            this.image = fullSheet.getSubimage(0, 0, 32, 32);
        } catch (IOException e) {
            System.err.println("プレイヤー画像読み込み失敗。四角で代用。");
            this.image = null;
        }
    }

    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, (int)x, (int)y, 50, 50, null);
        } else {
            g.setColor(Color.BLUE);
            g.fillRect((int)x, (int)y, 50, 50);
        }
    }

    public void move(InputHandler input, GameMap map, double deltaTime) {
        double nextX = x;
        double nextY = y;

        if (input.isPressed(KeyEvent.VK_LEFT)) nextX -= speed * deltaTime;
        if (input.isPressed(KeyEvent.VK_RIGHT)) nextX += speed * deltaTime;
        if (input.isPressed(KeyEvent.VK_UP)) nextY -= speed * deltaTime;
        if (input.isPressed(KeyEvent.VK_DOWN)) nextY += speed * deltaTime;

        if (canMove((int)nextX, (int)nextY, map)) {
            x = nextX;
            y = nextY;
        }
    }

    // ★ 改善版壁判定：中心点チェック
    private boolean canMove(int nextX, int nextY, GameMap map) {
        int tileSize = 50;
        
        // シンプルかつ完璧：9点チェック（3x3の中心）
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int checkX = (nextX + tileSize / 2 + dx * 15) / tileSize;
                int checkY = (nextY + tileSize / 2 + dy * 15) / tileSize;
                if (map.isWall(checkX, checkY)) {
                    return false;  // 少しでも壁があればNG
                }
            }
        }
        return true;
    }
    
    
    public int getCenterX() {
        return (int)(x + 25);  // TILE_SIZE/2 = 25
    }

    
    public int getCenterY() {
        return (int)(y + 25);
    }
    
    
}