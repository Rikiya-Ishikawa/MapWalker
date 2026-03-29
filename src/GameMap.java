import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GameMap {
	private int[][] map = { { 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 0, 0, 0, 0, 0, 0, 1 }, { 1, 0, 1, 1, 1, 1, 0, 1 },
			{ 1, 0, 1, 0, 0, 0, 0, 1 }, { 1, 0, 1, 0, 1, 1, 1, 1 }, { 1, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 1, 1, 0, 1, 1, 2, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1 } };

	private final int TILE_SIZE = 50;
	private BufferedImage wallImage;
	private static final int TILE_WALL = 1;
	private static final int TILE_GOAL = 2;

	public GameMap() {
		try {
			// 画像の読み込みを試みる
			// URL先のグレーレンガ画像を保存し、resフォルダにwall.pngとして配置してください
			wallImage = ImageIO.read(new File("ref/trak2_wall1c.tga.preview.jpg"));
		} catch (IOException e) {
			// ファイルがない、パスが違う等のエラー時に動く
			System.err.println("壁画像の読み込みに失敗しました。");
			wallImage = null;
		}
	}

	// マップを描画する専用メソッド
	public void draw(Graphics g) {
		for (int row = 0; row < map.length; row++) {
			for (int col = 0; col < map[row].length; col++) {
				if (map[row][col] == TILE_WALL) {
					// 【変更箇所】画像があれば画像を描画、なければ従来の黒塗りにする
					if (wallImage != null) {
						// 第4, 5引数にTILE_SIZEを指定することで、大きな画像も50x50に収まります
						g.drawImage(wallImage, col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
					} else {
						// フォールバック（画像がない場合の保険）
						g.setColor(Color.BLACK);
						g.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
					}
				}

				// ★★★ ゴール描画を追加 ★★★
				if (map[row][col] == TILE_GOAL) {
					g.setColor(Color.YELLOW);
					g.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
				}
			}
		}
	}

	// 境界や壁を判定するメソッド
	public boolean isWall(int tileX, int tileY) {  // ← 引数名を修正
	    if (tileX < 0 || tileX >= 8 || tileY < 0 || tileY >= 8) {  // ← 400pxではなく 8 タイルで判定
	        return true;
	    }

	    // 配列のその場所が 1 なら true（壁だ！）と答える
	    return map[tileY][tileX] == TILE_WALL;  // ← row=tileY, col=tileX でそのまま使う
	}

	public boolean isGoalTile(int tileY, int tileX) {  // ← 順序統一
	    if (tileX < 0 || tileX >= 8 || tileY < 0 || tileY >= 8) {
	        return false;
	    }
	    return map[tileY][tileX] == TILE_GOAL;
	}

}
