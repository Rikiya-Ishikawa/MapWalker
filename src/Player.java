import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;

public class Player {

    private int x;
    private int y;
    private BufferedImage image;

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;

        try {
            Path imagePath = Path.of("ref/pipo-charachip029a.png");
            BufferedImage fullSheet = ImageIO.read(imagePath.toFile());
            this.image = fullSheet.getSubimage(0, 0, 32, 32);
        } catch (IOException e) {
            System.err.println("プレイヤー画像の読み込みに失敗しました。四角で代用します。");
            this.image = null;
        }
    }

    /** プレイヤー描画 */
    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, x, y, TileConfig.TILE_SIZE, TileConfig.TILE_SIZE, null);
        } else {
            g.setColor(Color.BLUE);
            g.fillRect(x, y, TileConfig.TILE_SIZE, TileConfig.TILE_SIZE);
        }
    }

    /** プレイヤー移動 */
    public void move(Direction dir, GameMap map) {
        int nextX = x;
        int nextY = y;

        switch (dir) {
            case UP -> nextY -= TileConfig.TILE_SIZE;
            case DOWN -> nextY += TileConfig.TILE_SIZE;
            case LEFT -> nextX -= TileConfig.TILE_SIZE;
            case RIGHT -> nextX += TileConfig.TILE_SIZE;
        }

        // 壁判定（ピクセル座標 → タイル座標へ変換）
        int row = (nextY + TileConfig.TILE_SIZE / 2) / TileConfig.TILE_SIZE;
        int col = (nextX + TileConfig.TILE_SIZE / 2) / TileConfig.TILE_SIZE;

        if (!map.isWall(row, col)) {
            this.x = nextX;
            this.y = nextY;
        }
    }

    public int getCenterX() {
        return x + TileConfig.TILE_SIZE / 2;
    }

    public int getCenterY() {
        return y + TileConfig.TILE_SIZE / 2;
    }
}